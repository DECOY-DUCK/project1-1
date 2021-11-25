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
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
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
	public Map<String, Object> createHouseReview(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		HouseReview houseReview = new HouseReview();

		houseReview.setAptNo(Integer.parseInt((String)map.get("aptNo")));
		houseReview.setAuthorNo(Integer.parseInt((String)map.get("authorNo")));
		System.out.println(houseReview.getAuthorNo());
		houseReview.setContent((String)map.get("content"));
		System.out.println(houseReview.getContent());
		if(houseReviewMapper.insertHouseReview(houseReview) == 0) {
			result.put("msg", FAIL);
			return result;
		}

		result = getHouseReviews(map);
		result.put("msg", SUCCESS);
		
		return result;
	}

	@Override
	public Map<String, Object> updateHouseReview(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		HouseReview houseReview = new HouseReview();
		
		houseReview.setNo((int)map.get("no"));
		houseReview.setContent((String)map.get("content"));
		
		if(houseReviewMapper.updateHouseReview(houseReview) == 0) {
			result.put("msg", FAIL);
			return result;
		}
	
		result = getHouseReviews(map);
		result.put("msg", SUCCESS);
		
		return result;
	}

	@Override
	public Map<String, Object> deleteHouseReview(int no, Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(houseReviewMapper.deleteHouseReview(no) == 0) {
			result.put("msg", FAIL);
			return result;
		}
	
		result = getHouseReviews(map);
		result.put("msg", SUCCESS);
		
		return result;
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
