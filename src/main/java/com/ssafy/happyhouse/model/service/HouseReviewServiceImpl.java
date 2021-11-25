package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseReview;
import com.ssafy.happyhouse.model.mapper.HouseReviewMapper;
import com.ssafy.happyhouse.utils.Pagination;

@Service
public class HouseReviewServiceImpl implements HouseReviewService {
	@Autowired
	private HouseReviewMapper houseReviewMapper;
	
	@Autowired
	private Pagination pagination;
	
	@Override
	public Map<String, Object> getHouseReviews(Map<String, Object> map) {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		param.put("aptNo", Integer.parseInt((String)map.get("aptNo")));
		pagination.setStartIndex(map, param);
		
		List<HouseReview> list = houseReviewMapper.selectAllHouseReviews(param);

		result.put("list", list);
		result.put("count", houseReviewMapper.selectAllHouseReviewsCount(Integer.parseInt((String)map.get("aptNo"))));

		return result;
	}

	@Override
	public HouseReview getHouseReview(int no) {
		return houseReviewMapper.selectHouseReview(no);
	}

	@Override
	public boolean createHouseReview(HouseReview houseReview) {
		return houseReviewMapper.insertHouseReview(houseReview) == 1;
	}

	@Override
	public boolean updateHouseReview(HouseReview houseReview) {
		return houseReviewMapper.updateHouseReview(houseReview) == 1;
	}

	@Override
	public boolean deleteHouseReview(int no) {
		return houseReviewMapper.deleteHouseReview(no) == 1;
	}

	@Override
	public boolean saveHouseReviewLike(int reviewNo, int userNo) {
		return houseReviewMapper.insertHouseReviewLike(reviewNo, userNo) == 1;
	}

	@Override
	public boolean deleteHouseReviewLike(int reviewNo, int userNo) {
		return houseReviewMapper.deleteHouseReviewLike(reviewNo, userNo) == 1;
	}

}
