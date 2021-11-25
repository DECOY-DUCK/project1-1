package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.LoginUser;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.mapper.AuthMapper;

@Service
public class AuthServiceImpl implements AuthService {
	public static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	@Autowired
	private AuthMapper authmapper;

	@Autowired
	private MailService mailService;

	@Override
	public LoginUser login(User user) {

		User data = authmapper.selectUserByEmail(user.getEmail());

		// 탈퇴한 사용자 3개월 유지 하는 방식인 경우 탈퇴날짜가 null이 아니면 return null
		// 이메일만 먼저 찾아서 있는지 확인하고 없으면 리턴 null (컨트롤러에서 401에러 보내기)
		if (data == null) {
			return null;
		} else if (data.getDeleteDate() != null) {

			return null;

			// db에 저장된 bcrypt 암호화된 비밀번호가져와서
			// 클라이언트가 보낸 비밀번호랑 같은지 여기서 확인하고 안맞으면 리턴 null(컨트롤러에서 401에러 보내기)
		} else if (!BCrypt.checkpw(user.getPassword(), data.getPassword())) {

			return null;
		}
		// no, email, password, name, authCode, deleteDate
		// 다맞으면 컨트롤러에서 토큰 생성해서 클라이언트에게 보내려고 했음
		LoginUser loginUser = new LoginUser();

		loginUser.setNo(data.getNo());
		loginUser.setEmail(data.getEmail());
		loginUser.setName(data.getName());
		loginUser.setDeleteDate(data.getDeleteDate());
		loginUser.setAuthCode(data.getAuthCode());

		return loginUser;
	}

	@Override
	public boolean signup(User user) {
		boolean flag = false;
		
		if(user.getAuthCode() == null) {
			user.setAuthCode("U");
		}
	
		// DB에 저장할 비밀번호 암호화
		String encrypted_pwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(encrypted_pwd);
		int result = authmapper.insertUser(user);
		if (result == 1) {
			flag = true;
		}
		return flag;
	}

	@Override
	public int idCheck(String email) {
		int result = 0;
		User user = authmapper.selectUserByEmail(email);
		if (user != null) {
			result = 1;
		}
		return result;
	}

	@Override
	//임시 비번 이메일로 
	//html로 바꾸자
	public int findPwd(User user) {
		int result =0;
		//등록된 계정인지 확인 한다
		User data =authmapper.selectUserByEmail(user.getEmail());
		if(data==null) {
			return result;
		}else{
			result =1;
			//임시비밀번호 만들기
			String pwd = UUID.randomUUID().toString().replaceAll("-", ""); 
			pwd = pwd.substring(0, 10); 
			  
			
			//회원정보 변경하기
			String encrypted_pwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
			data.setPassword(encrypted_pwd);
			authmapper.updateUser(data);
			//이메일 보내기
			logger.debug("임시 비밀번호 보냄");
			StringBuilder cntnt = new StringBuilder();
			cntnt.append("임시비밀번호는 ").append(pwd).append( "입니다");
			mailService.sendEmail("tph01198@naver.com", "안녕하세요,happyHouse입니다 임시비밀번호를 발급해 드립니다", cntnt.toString());
		}
		return result;
	}

	// 마이페이지 들어가기 전에 비번 한번더 확인하는건데 복잡하면 빼세요
	@Override
	public LoginUser getUserInfo(int no, String password) {
		LoginUser loginUser = null;
		User data = authmapper.selectUserByNo(no);
		if (data != null && BCrypt.checkpw(password, data.getPassword())) {
			loginUser = new LoginUser();

			loginUser.setNo(data.getNo());
			loginUser.setEmail(data.getEmail());
			loginUser.setName(data.getName());
			loginUser.setDeleteDate(data.getDeleteDate());
			loginUser.setAuthCode(data.getAuthCode());
		}
		
		return loginUser;
	}

	@Override
	public boolean updateUserInfo(User user) {
		// inser,updata,delete는 row 개수 반환
		boolean flag = false;
		String encrypted_pwd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(encrypted_pwd);
		if (authmapper.updateUser(user) == 1) {
			flag = true;
		}
		return flag;

	}

	@Override
	public boolean withdrawal(int no) {
		boolean flag = false;
		// deleteDate가 null인지 알아보기
		User user = authmapper.selectUserByNo(no);
		// null이 아니면 deleteDate
		if (user.getDeleteDate() == null) {
			authmapper.updateUserDeleteDate(no);
			flag = true;
		}

		return flag;
	}

	// User정보를 확인하고 LoginUser로 바꾼다.
	@Override
	public LoginUser getUserInfobyemail(String email) {
		LoginUser loginUser = new LoginUser();

		User data = authmapper.selectUserByEmail(email);
		loginUser.setNo(data.getNo());
		loginUser.setEmail(data.getEmail());
		loginUser.setName(data.getName());
		loginUser.setDeleteDate(data.getDeleteDate());
		loginUser.setAuthCode(data.getAuthCode());

		return loginUser;
	}

	@Override
	public List<LoginUser> getUserInfoList() {
		List<User> list = authmapper.getUserInfoList();
		List<LoginUser> result = new ArrayList<LoginUser>();
		for (int i = 0; i < list.size(); i++) {
			LoginUser data = new LoginUser();
			data.setNo(list.get(i).getNo());
			data.setEmail(list.get(i).getEmail());
			data.setName(list.get(i).getName());
			data.setAuthCode(list.get(i).getAuthCode());
			data.setDeleteDate(list.get(i).getDeleteDate());
			
			result.add(data);
		}
		
		return result;
	}

	@Override
	public boolean delete(int no) {
		if(authmapper.delete(no) == 1) {
			return true;
		}
		return false;
	}

}
