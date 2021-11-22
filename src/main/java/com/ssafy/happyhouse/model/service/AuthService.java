package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.LoginUser;
import com.ssafy.happyhouse.model.dto.User;

public interface AuthService {
	/**
	 * 등록된 회원 정보를 받아온다
	 * 
	 * @return
	 */
	List<LoginUser> getUserInfoList();
	/**
	 * 회원 정보(email, password)를 받아서 해당 회원의 정보를 받아온다.
	 * 
	 * @param user : 회원 정보 (email, password) 
	 * @return 해당 회원 정보 (no, name)
	 */
	LoginUser login(User user);

	/**
	 * 회원 정보를 받아서 새로운 회원으로 등록한다.
	 * 
	 * @param user : 회원 정보  
	 * @return 성공 여부 
	 */
	boolean signup(User user);
	
	/**
	 * 회원정보 확인
	 * 
	 * @param email
	 * @return
	 */
	LoginUser getUserInfobyemail(String email);
	
	/**
	 * 이메일을 받아서 해당하는 회원이 있는지 조회한다.
	 * 
	 * @param email : 이메일  
	 * @return 존재 여부에 따라 0 또는 1 
	 */
	int idCheck(String email);

	// 비밀 번호 찾기할 때 쓰려고 작성한 겁니다. 
	/**
	 * 회원 정보(name, email)를 받아서 해당하는 회원이 있는지 조회한다.
	 * 
	 * @param user : 회원 정보(name, email)  
	 * @return 존재 여부에 따라 0 또는 1 
	 */
	int findPwd(User user);

	// 마이페이지 들어갈 때 쓰려고 작성한거 (원래는 로그인한 다음 유저한테 식별번호 no를 줘서 요청하는 url에 붙여서 보내려고 생각했음 )
	/**
	 * 회원 고유 번호와 비밀번호를 받아서 해당하는 회원 정보를 조회한다.
	 * 
	 * @param no		: 회원 식별 번호 
	 * @param password	: 비밀번호 
	 * @return 해당 회원 정보 
	 */
	LoginUser getUserInfo(int no, String password);

	/**
	 * 회원 정보를 받아서 수정한다.
	 * 
	 * @param user : user 정보
	 * @return 성공 여부
	 */
	boolean updateUserInfo(User user);
	
	/**
	 * 회원 정보를 받아서 회원 탈퇴 처리한다.
	 * 
	 * @param no : 회원 고유 번호
	 * @return 성공 여부
	 */
	boolean withdrawal(int no);
	/**
	 * 관리자가 회원을 강제 퇴장 시킨다.
	 * 
	 * @param no
	 * @return 성공 여부
	 */
	boolean delete(int no);
}
