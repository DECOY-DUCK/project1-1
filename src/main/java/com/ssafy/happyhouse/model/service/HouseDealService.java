package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
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
	 * 동 이름, 동이 속한 구군의 코드, 현재 페이지 번호를 받아서 해당 지역의 주택 거래 목록을 받아온다.
	 * 
	 * @param gugunCode : 선택된 구군 코드
	 * @param map : dong, pageNo, sizePerPage
	 * @return 해당 페이지와 선택된 지역의 주택 거래 목록
	 */
	List<HouseDeal> getHouseDealInDong(Map<String, Object> map);

}
