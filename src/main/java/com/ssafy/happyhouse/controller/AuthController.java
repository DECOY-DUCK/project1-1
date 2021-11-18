package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.LoginUser;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.AuthService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AuthService authService;

	// LoginUser는 클라이언트로 보낼 요약 정보 생각하고 작성했어요. 수업에서 한 세션스토리지 저장말고 HttpOnly 쿠키 생각했는데  세션스토리지로 작성하시는 게 편할거에요 
	@ApiOperation(value = "입력받은 회원 정보를 조회한다. db 조회 성공 시 HttpOnly 쿠키에 'token'을 저장하고 LoginUser를 반환한다.", response =  LoginUser.class)
	@PostMapping("login")
	public ResponseEntity<LoginUser> login(User user) {
		return null;
	}

	// 회원가입 성공하면 클라이언트 쪽에서 로그인 페이지로 리다이렉트하는거 생각하고 작성했어요. 
	@ApiOperation(value = "입력받은 회원 정보를 저장한다. db 입력성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response =  String.class)
	@PostMapping("signup")
	public ResponseEntity<String> signup(User user) {
		return null;
	}

	@ApiOperation(value = "입력받은 email 정보를 조회한다. db 조회 성공 여부에 따라 0 또는 1을 반환한다.", response = Integer.class)
	@PostMapping("signup/idcheck")
	public ResponseEntity<Integer> idCheck(String email) {
		return null;
	}

	// 이것도 세션스토리지 사용하면 필요없을듯!
	@ApiOperation(value = "HttpOnly 쿠키에 저장한 'token'를 삭제한다. 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("logout")
	public ResponseEntity<String> logout() {
		return null;
	}

	@ApiOperation(value = "입력받은 회원 정보를 조회한다. db 조회 성공 여부에 따라 회원 이메일로 임시 비밀번호를 발송하고,'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("findpwd")
	public ResponseEntity<String> findPwd(User user) {
		return null;
	}

	@ApiOperation(value = "no와 password에 해당하는 회원 정보를 조회한다. db 조회 성공 시 LoginUser를  반환한다.", response = LoginUser.class)
	@PostMapping("mypage/{no}")
	public ResponseEntity<LoginUser> getUserInfo(@PathVariable int no, String password) {
		return null;
	}

	@ApiOperation(value = "no에 해당하는 회원 정보를 수정한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("mypage/{no}")
	public ResponseEntity<String> updateUserInfo(@PathVariable int no) {
		return null;
	}

	// 회원 탈퇴는 바로 삭제안하고 3개월동안 유지하려고 탈퇴일수정하는거 생각한건데 이렇게 하면 로그인 때 탈퇴일이 null 값이 아니면 로그인 막는 로직이 필요해요. 복잡하면 그냥 삭제하는 방식으로 바꿔주세요!
	@ApiOperation(value = "no에 해당하는 회원의 탈퇴일(deleteDate)를 수정한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("mypage/{no}")
	public ResponseEntity<String> withdrawal(@PathVariable int no) {
		return null;
	}

	
	// 이건 수업에서 @GetMapping("/info/{userid}") getInfo 함수랑 비슷한 기능으로 생각한겁니다.
	@ApiOperation(value = "로그인한 회원의 token의 유효성을 확인한다. 성공 시  회원 정보를 반환한다.", response = Map.class)
	@GetMapping("me")
	public ResponseEntity<Map<String, String>> me() {
		return null;
	}
}
