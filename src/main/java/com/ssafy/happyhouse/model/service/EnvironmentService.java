package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.SafeHospital;
import com.ssafy.happyhouse.model.dto.SelectedClinic;

public interface EnvironmentService {
	/**
	 * 선택된 시도 이름과 구군 이름을 받아서 주소에 해당 지역이 포함된 안심병원 목록을 받아온다.
	 * 
	 * @param sido 	: 시도 
	 * @param gugun	: 구군
	 * @return 해당 지역 안심병원 목록
	 */
	List<SafeHospital> getNearbyHospitals(String sido, String gugun);

	/**
	 * 선택된 시도 이름과 구군 이름을 받아서 주소에 해당 지역이 포함된 선별진료소 목록을 받아온다.
	 * 
	 * @param sido 	: 시도 
	 * @param gugun	: 구군
	 * @return 해당 지역 선별 진료소 목록
	 */
	List<SelectedClinic> getNearbyClinics(String sido, String gugun);
}
