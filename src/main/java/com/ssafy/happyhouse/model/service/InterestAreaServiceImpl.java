package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.InterestArea;
import com.ssafy.happyhouse.model.mapper.InterestAreaMapper;

@Service
public class InterestAreaServiceImpl implements InterestAreaService{

	@Autowired
	private InterestAreaMapper interestAreaMapper;
	
	@Override
	public List<Map<String, Object>> getInterestAreas(int userNo) {
		return interestAreaMapper.selectAllInterestAreas(userNo);
	}

	@Override
	public int getInterestArea(Map<String, Object> map) {
		return interestAreaMapper.selectInterestArea(map) != null ? 1: 0;
	}

	@Override
	public boolean saveInterestArea(Map<String, Object> map) {
		return interestAreaMapper.insertInterestArea(map) == 1;
	}

	@Override
	public boolean deleteInterestArea(Map<String, Object> map) {
		return interestAreaMapper.deleteInterestArea(map) == 1;
	}


}
