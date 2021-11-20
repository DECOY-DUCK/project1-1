package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;
import com.ssafy.happyhouse.model.mapper.HouseDealMapper;

@Service
public class HouseDealServiceImpl implements HouseDealService{
	@Autowired
	private HouseDealMapper houseDealMapper;

	@Override
	public List<SidoGugunCode> getSido() {
		return houseDealMapper.selectSido();
	}

	@Override
	public List<SidoGugunCode> getGugunInSido(String sido) {
		return houseDealMapper.selectGugunInSido(sido);
	}

	@Override
	public List<DongCode> getDongInGugun(String gugun) {
		return houseDealMapper.selectDongInGugun(gugun);
	}

	@Override
	public List<HouseInfo> getHouseInfoInDong(Map<String, Object> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		int spp = Integer.parseInt((String)map.get("sizePerPage"));
		int pageNo = Integer.parseInt((String)map.get("pageNo"));
		
		int start = pageNo == 0 ? 0 : spp * pageNo + 1;

		param.put("dongName", (String)map.get("dongName"));
		param.put("gugunCode", (String)map.get("gugunCode"));
		param.put("start", start);
		param.put("sizePerPage", spp);
	
		return houseDealMapper.selectAllHouseInfosInDong(param);
	}
	

	@Override
	public List<HouseDeal> getHouseDealInDong(Map<String, Object> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		int spp = Integer.parseInt((String)map.get("sizePerPage"));
		int pageNo = Integer.parseInt((String)map.get("pageNo"));
		
		int start = pageNo == 0 ? 0 : spp * pageNo + 1;

		param.put("dongName", (String)map.get("dongName"));
		param.put("gugunCode", (String)map.get("gugunCode"));
		param.put("start", start);
		param.put("sizePerPage", spp);
	
		return houseDealMapper.selectAllHouseDealsInDong(param);
	}
	
}
