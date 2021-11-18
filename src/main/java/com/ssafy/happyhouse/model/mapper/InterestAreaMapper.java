package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.InterestArea;

@Mapper
public interface InterestAreaMapper {

	List<InterestArea> selectAllInterestAreas(int userNo);

	InterestArea selectInterestArea(Map<String, Object> map);

	int insertInterestArea(Map<String, Object> map);

	int deleteInterestArea(Map<String, Object> map);
}
