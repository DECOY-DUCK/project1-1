package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.SafeHospital;
import com.ssafy.happyhouse.model.dto.SelectedClinic;
import com.ssafy.happyhouse.model.mapper.EnvironmentMapper;

@Service
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private EnvironmentMapper environmentMapper;

	@Override
	public List<SafeHospital> getNearbyHospitals(String sido, String gugun) {
		// 구군에서 찾기
		List<SafeHospital> list = environmentMapper.selectAllHospitalsInGugun(sido, gugun);

		// 없으면 시에서 찾기
		if (list.isEmpty()) {
			list = environmentMapper.selectAllHospitalsInSido(sido);
		}
		return list;
	};

	@Override
	public List<SelectedClinic> getNearbyClinics(String sido, String gugun) {
		// 구군에서 찾기
		List<SelectedClinic> list = environmentMapper.selectAllClinicsInGugun(sido, gugun);

		// 없으면 시에서 찾기
		if (list.isEmpty()) {
			list = environmentMapper.selectAllClinicsInSido(sido);
		}
		return list;
	};

}
