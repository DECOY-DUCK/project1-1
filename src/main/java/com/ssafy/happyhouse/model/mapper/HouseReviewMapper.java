package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseReview;

@Mapper
public interface HouseReviewMapper {

	List<HouseReview> selectAllHouseReviews(Map<String, Object> map);

	int selectAllHouseReviewsCount();

	HouseReview selectHouseReview(int no);

	List<Integer> selectHouseReviewLikes(int no);
	
	int insertHouseReview(HouseReview HouseReview);
	
	int updateHouseReview(HouseReview HouseReview);

	int deleteHouseReview(int no);
}
