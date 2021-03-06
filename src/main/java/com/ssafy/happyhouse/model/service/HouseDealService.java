package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;

public interface HouseDealService {
	/**
	 * 전국의 시도 목록을 받아온다.
	 * 
	 * @return 전국 시도 목록
	 */
	List<SidoGugunCode> getSido();

	/**
	 * 시도 코드를 받아서 해당하는 코드에 속한 구군 목록을 받아온다.
	 * 
	 * @param sido : 선택된 시도 코드
	 * @return 해당 시도의 구군 목록
	 */
	List<SidoGugunCode> getGugunInSido(String sido);

	/**
	 * 구군 코드를 받아서 해당하는 코드에 속한 동 목록을 받아온다.
	 * 
	 * @param gugun : 선택된 구군 코드
	 * @return 해당 구군의 동 목록
	 */
	List<DongCode> getDongInGugun(String gugun);
	
	/**
	 * 법정동 코드를 받아서 해당하는 코드에 속한 시도와 구군을 받아온다.
	 * 
	 * @param dongCode : 선택된 동 코드
	 * @return 해당 동의 시도와 구군 
	 */
	SidoGugunCode getSidoGugunByDong(String dongCode);
	
	/**
	 * 동 이름, 동이 속한 구군의 코드, 현재 페이지 번호를 받아서 해당 지역의 주택 목록을 받아온다.
	 * 
	 * @param map : dong, gugunCode, pageNo, sizePerPage
	 * @return 해당 페이지와 선택된 지역의 주택 목록
	 */
	List<HouseInfo> getHouseInfoInDong(Map<String, Object> map);
	
	/**
	 * 아파트 식별 번호를 받아서  해당하는 아파트 기본 정보를 받아온다.
	 * 
	 * @param no : 아파트 식별 번호
	 * @return 번호에 해당하는 아파트 기본 정보
	 */
	HouseInfo getHouseInfosInNo(int no);
	
	/**
	 * 아파트 이름, 동 이름, 동이 속한 구군의 코드, 현재 페이지 번호를 받아서 해당 지역의 주택 거래 목록을 받아온다.
	 * 
	 * @param map : aptName, dong, gugunCode, pageNo, sizePerPage
	 * @return 해당 페이지와 선택된 지역의 주택 거래 목록
	 */
	List<HouseDeal> getHouseDealInDong(Map<String, Object> map);
}
