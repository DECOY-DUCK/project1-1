package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.SafeHospital;
import com.ssafy.happyhouse.model.dto.SelectedClinic;

@Mapper
public interface EnvironmentMapper {

	List<SafeHospital> selectAllHospitalsInGugun(String sido, String gugun);

	List<SafeHospital> selectAllHospitalsInSido(String sido);

	List<SelectedClinic> selectAllClinicsInGugun(String sido, String gugun);

	List<SelectedClinic> selectAllClinicsInSido(String sido);
}
