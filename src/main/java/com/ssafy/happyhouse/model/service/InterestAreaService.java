package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.InterestArea;

public interface InterestAreaService {
	/**
	 * 회원의 식별번호를 받아서 관심지역 전체 목록을 받아온다.
	 * 
	 * @param userNo : 회원 식별번호
	 * @return 해당 회원의 관심지역 전체 목록
	 */
	List<InterestArea> getInterestAreas(int userNo);
	
	/**
	 * 회원의 식별번호와 법정동 코드를 받아서 관심지역 여부를 확인한다.
	 * 
	 * @param map : userNo, dongCode
	 * @return 존재 여부에 따라 0 또는 1 
	 */
	int getInterestArea(Map<String, Object> map);
	
	/**
	 * 회원의 식별번호와 법정동 코드를 받아서  관심지역으로 등록한다.
	 * 
	 * @param map : userNo, dongCode
	 * @return 성공 여부 
	 */
	boolean saveInterestArea(Map<String, Object> map);
	
	/**
	 * 회원의 식별번호와 법정동 코드를 받아서  관심지역에서 해제한다.
	 * 
	 * @param map : userNo, dongCode
	 * @return 성공 여부 
	 */
	boolean deleteInterestArea(Map<String, Object> map);
}
