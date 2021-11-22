package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.User;

@Mapper
public interface AuthMapper {

	// 로그인할 때 비밀번호는 service에서 확인하려고 했음
	User selectUserByEmail(String email);

	User selectUserByNo(int no);

	int selectUserCountByEmailName(User user);

	int insertUser(User user);

	int updateUser(User user);

	int updateUserDeleteDate(int no);
	
	List<User> getUserInfoList();
}
