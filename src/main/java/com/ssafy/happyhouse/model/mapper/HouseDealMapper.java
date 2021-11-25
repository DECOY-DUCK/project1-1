package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;

@Mapper
public interface HouseDealMapper {
	
	List<SidoGugunCode> selectSido();

	List<SidoGugunCode> selectGugunInSido(String sido);

	List<DongCode> selectDongInGugun(String gugun);
	
	SidoGugunCode selectSidoGugunByDong(String dongCode);

	List<HouseDeal> selectAllHouseDealsInDong(Map<String, Object> map);
	
	List<HouseInfo> selectAllHouseInfosInDong(Map<String, Object> map);
	
	HouseInfo selectHouseInfosInNo(int no);
}
