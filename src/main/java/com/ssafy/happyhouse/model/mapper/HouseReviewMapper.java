package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.HouseReview;

@Mapper
public interface HouseReviewMapper {

	List<HouseReview> selectAllHouseReviews(Map<String, Object> map);

	int selectAllHouseReviewsCount(int aptNo);

	HouseReview selectHouseReview(int no);

	List<Integer> selectHouseReviewLikes(int no);

	int selectAllHouseReviewsLikesCount(int no);

	int insertHouseReview(HouseReview houseReview);

	int updateHouseReview(HouseReview houseReview);

	int deleteHouseReview(int no);

	int insertHouseReviewLike(int reviewNo, int userNo);

	int deleteHouseReviewLike(int reviewNo, int userNo);
}