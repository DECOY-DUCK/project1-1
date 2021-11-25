package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.LoginUser;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.AuthService;
import com.ssafy.happyhouse.model.service.JwtService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtService jwtService;

	
	@ApiOperation(value = "회원 전부를 조회한다. db 조회 성공 시 전체 회원 목록을  반환한다.", response = List.class)
	@GetMapping("userinfo")
	public ResponseEntity<List<LoginUser>> getUserInfo(){
		return new ResponseEntity<List<LoginUser>>(authService.getUserInfoList(), HttpStatus.OK);
	}




	// LoginUser는 클라이언트로 보낼 요약 정보 생각하고 작성했어요. 수업에서 한 세션스토리지 저장말고 HttpOnly 쿠키 생각했는데  세션스토리지로 작성하시는 게 편할거에요 
	@ApiOperation(value = "입력받은 회원 정보를 조회한다. db 조회 성공 시 HttpOnly 쿠키에 'token'을 저장하고 LoginUser를 반환한다.", response =  LoginUser.class)
	@PostMapping("login")
	public ResponseEntity<LoginUser> login(@RequestBody User user) {
		
		HttpStatus status = null;
		LoginUser loginUser =null;
				
		try {
			loginUser = authService.login(user);
			
			if(loginUser !=null) {
				//loginUser에 token 추가
				
				String token = jwtService.create("email", user.getEmail(), "access-token");
				loginUser.setToken(token);
//				logger.info(token);
				status = HttpStatus.ACCEPTED;
			
			}else {
				status = HttpStatus.UNAUTHORIZED;
			}
			
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
			
			return new ResponseEntity<>(loginUser,status);
	}
	// 회원가입 성공하면 클라이언트 쪽에서 로그인 페이지로 리다이렉트하는거 생각하고 작성했어요. 
	@ApiOperation(value = "입력받은 회원 정보를 저장한다. db 입력성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response =  String.class)
	@PostMapping("signup")
	public ResponseEntity<String> signup(@RequestBody User user) {
		String result = "";
		HttpStatus status =null;
		
		try {
			if(authService.signup(user)){
				
				status = HttpStatus.ACCEPTED;
				result = SUCCESS;
			}else {

				status = HttpStatus.ACCEPTED;
				result = FAIL;
			}
			
			
		} catch (Exception e) {
			logger.error("회원가입 실패:{}",e);
			status= HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, status);
	}

	@ApiOperation(value = "입력받은 email 정보를 조회한다. db 조회 성공 여부에 따라 0 또는 1을 반환한다.", response = Integer.class)
	@PostMapping("signup/idcheck")
	public ResponseEntity<Integer> idCheck(@RequestBody HashMap<String, String> email) {
		
		int result = authService.idCheck(email.get("email"));
	
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
	
	@ApiOperation(value = "입력받은 회원가입번호를 조회한다. db 조회 성공 여부에 따라 0 또는 1을 반환한다.", response = Integer.class)
	@PostMapping("signup/authkeycheck")
	public ResponseEntity<Integer> authkeycheck(@RequestBody User user) {
		
		int result = authService.authkeyCheck(user);
		
		return new ResponseEntity<Integer>(result,HttpStatus.OK);
	}
		
	// 이것도 세션스토리지 사용하면 필요없을듯!
//	@ApiOperation(value = "HttpOnly 쿠키에 저장한 'token'를 삭제한다. 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PostMapping("logout")
//	public ResponseEntity<String> logout() {
//		return null;
//	}

	@ApiOperation(value = "입력받은 회원 정보를 조회한다. db 조회 성공 여부에 따라 회원 이메일로 임시 비밀번호를 발송하고,'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("findpwd")
	public ResponseEntity<String> findPwd(@RequestBody User user) {
		String msg ="";
		HttpStatus status = null;
				
		try {
			if(authService.findPwd(user)==1) {
				msg = SUCCESS;
				status = HttpStatus.ACCEPTED;
			}else {
				msg = FAIL;
				status = HttpStatus.ACCEPTED;
			}
		}catch (Exception e) {
			
			msg = FAIL;
			status = HttpStatus.UNAUTHORIZED;
		}
		
		
		return new ResponseEntity<String>(msg,status);
	}

	@ApiOperation(value = "no와 password에 해당하는 회원 정보를 조회한다. db 조회 성공 시 LoginUser를  반환한다.", response = LoginUser.class)
	@PostMapping("mypage/{no}")
	public ResponseEntity<LoginUser> getUserInfo(@PathVariable int no,@RequestBody User user) {
		
		LoginUser loginUser = authService.getUserInfo(no, user.getPassword());
		return new ResponseEntity<LoginUser>(loginUser,HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "no에 해당하는 회원 정보를 수정한다. db 수정 성공 여부에 따라 loginUser를 반환한다.", response = String.class)
	@PutMapping("mypage/{no}")
	public ResponseEntity<LoginUser> updateUserInfo(@PathVariable int no,@RequestBody User user) {
		
		LoginUser loginUser =null;
		HttpStatus status = null;
		user.setNo(no);
		
		if(authService.updateUserInfo(user)) {
//			logger.debug("변경성공");
			loginUser=authService.getUserInfo(no, user.getPassword());
			
			status = HttpStatus.ACCEPTED;
			logger.debug(loginUser.getEmail());
		}
		return new ResponseEntity<LoginUser>(loginUser,status);
	}

	// 회원 탈퇴는 바로 삭제안하고 3개월동안 유지하려고 탈퇴일수정하는거 생각한건데 이렇게 하면 로그인 때 탈퇴일이 null 값이 아니면 로그인 막는 로직이 필요해요. 복잡하면 그냥 삭제하는 방식으로 바꿔주세요!
	@ApiOperation(value = "no에 해당하는 회원의 탈퇴일(deleteDate)를 수정한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("mypage/{no}")
	public ResponseEntity<String> withdrawal(@PathVariable int no) {
		String msg =FAIL;
		if( authService.withdrawal(no)) {
			msg=SUCCESS;
		}
		logger.debug(msg);
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "no에 해당하는 회원의 탈퇴일(deleteDate)를 수정한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("admin/{no}")
	public ResponseEntity<String> exileUser(@PathVariable int no) {
		String msg =FAIL;
		if( authService.delete(no)) {
			msg=SUCCESS;
		}
		return new ResponseEntity<String>(msg,HttpStatus.ACCEPTED);
	}
	// 이건 수업에서 @GetMapping("/info/{useremail}") getInfo 함수랑 비슷한 기능으로 생각한겁니다.
	@ApiOperation(value = "로그인한 회원의 token의 유효성을 확인한다. 성공 시  회원 정보를 반환한다.", response = Map.class)
	@GetMapping("me/{email}")
	public ResponseEntity<Map<String, Object>> me(@PathVariable("email") @ApiParam(value = "인증할 회원의 아이디.", required = true) String email,
		HttpServletRequest request) {
		

	Map<String, Object> resultMap = new HashMap<>();
	HttpStatus status = HttpStatus.ACCEPTED;
	if (jwtService.isUsable(request.getHeader("access-token"))) {
		logger.info("사용 가능한 토큰!!!");
		try {
//			로그인 사용자 정보.
			LoginUser user = authService.getUserInfobyemail(email);
			resultMap.put("userInfo", user);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
	} else {
		logger.error("사용 불가능 토큰!!!");
		resultMap.put("message", FAIL);
		status = HttpStatus.UNAUTHORIZED; 
	}
	
	return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
