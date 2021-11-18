package com.ssafy.happyhouse.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.LoginUser;
import com.ssafy.happyhouse.model.dto.User;

@Service
public class AuthServiceImpl implements AuthService{

	@Override
	public LoginUser login(User user) {
		// 탈퇴한 사용자 3개월 유지 하는 방식인 경우 탈퇴날짜가 null이 아니면 return null
		
		// 구상했던 거 
		// 이메일만 먼저 찾아서 있는지 확인하고 없으면 리턴 null (컨트롤러에서 401에러 보내기)
		// db에 저장된 bcrypt 암호화된 비밀번호가져와서 
		// 클라이언트가 보낸 비밀번호랑 같은지 여기서 확인하고 안맞으면 리턴 null(컨트롤러에서 401에러 보내기)
		
		// 다맞으면  컨트롤러에서 토큰 생성해서 클라이언트에게 보내려고 했음

		return null;
	}

	@Override
	public boolean signup(User user) {
		// AuthCode 기본값 U로 넣어주세요 
		return false;
	}

	@Override
	public int idCheck(String email) {
		return 0;
	}

	@Override
	public int findPwd(User user) {
		return 0;
	}

	
	// 마이페이지 들어가기 전에 비번 한번더 확인하는건데 복잡하면 빼세요
	@Override
	public User getUserInfo(int no, String password) {
		return null;
	}

	@Override
	public boolean updateUserInfo(User user) {
		return false;
	}

	@Override
	public String withdrawal(int no) {
		return null;
	}

}
