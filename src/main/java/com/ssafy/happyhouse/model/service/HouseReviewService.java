package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.HouseReview;

public interface HouseReviewService {
	/**
	 * 아파트 식별번호, 현재 페이지와 한번에 요청할 데이터 수를 받아서 해당 영역에 속한 리뷰 목록을 받아온다.
	 * 
	 * @param map : aptNo, pageNo, sizePerPage
	 * @return 해당 아파트의 한 페이지 리뷰 목록과 전체 리뷰 수
	 */
	Map<String, Object> getHouseReviews(Map<String, Object> map);

	/**
	 * 식별 번호를 받아서 리뷰을 조회한다.
	 * 
	 * @param no : 리뷰 식별번호
	 * @return 해당하는 리뷰 정보
	 */
	HouseReview getHouseReview(int no);

	/**
	 * 리뷰 정보를 받아서 등록한다.
	 * 
	 * @param map : 리뷰 와 페이지 정보
	 * @return 해당 아파트의 한 페이지 리뷰 목록과 전체 리뷰 수
	 */
	Map<String, Object> createHouseReview(Map<String, Object> map);

	/**
	 * 리뷰 정보를 받아서 업데이트한다.
	 * 
	 * @param map : 리뷰 와 페이지 정보
	 * @return 해당 아파트의 한 페이지 리뷰 목록과 전체 리뷰 수
	 */
	Map<String, Object> updateHouseReview(Map<String, Object> map);

	/**
	 * 리뷰 식별 번호를 받아서 삭제한다.
	 * 
	 * @param no : 리뷰 번호
	 * @param map : 페이지 정보
	 * @return 해당 아파트의 한 페이지 리뷰 목록과 전체 리뷰 수
	 */
	Map<String, Object> deleteHouseReview(int no, Map<String, Object> map);

	/**
	 * 리뷰 식별번호와 회원 식별번호를 받아서 리뷰 좋아요를 등록한다.
	 * 
	 * @param reviewNo : 리뷰 식별 번호
	 * @param userNo   : 회원 식별 번호
	 * @return 해당 글의 좋아요 수 
	 */
	int saveHouseReviewLike(int reviewNo, int userNo);

	/**
	 * 리뷰 식별번호와 회원 식별번호를 받아서 리뷰 좋아요를 해제한다.
	 * 
	 * @param reviewNo : 리뷰 식별 번호
	 * @param userNo   : 회원 식별 번호
	 * @return 해당 글의 좋아요 수
	 */
    int deleteHouseReviewLike(int reviewNo, int userNo);
}
