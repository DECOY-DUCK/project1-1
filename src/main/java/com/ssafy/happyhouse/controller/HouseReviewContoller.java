package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.HouseReview;
import com.ssafy.happyhouse.model.service.HouseReviewService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/housereview")
public class HouseReviewContoller {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private HouseReviewService houseReviewService;

	@ApiOperation(value = "입력받은 아파트 식별번호에 해당하는 리뷰를 조회한다. db 조회 성공 시 리뷰 목록과 전체 리뷰 수를 담은 map 객체를  반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getHouses(@RequestParam Map<String, Object> map) {

		return new ResponseEntity<Map<String, Object>>(houseReviewService.getHouseReviews(map), HttpStatus.OK);
	}

	@ApiOperation(value = "입력받은 리뷰 정보를 저장한다. db 입력 성공 시 리뷰 목록과 전체 리뷰 수를 담은  map 객체를  반환한다.", response = Map.class)
	@PostMapping
	public ResponseEntity<Map<String, Object>> createHouseReview(@RequestBody Map<String, Object> map) {

		return new ResponseEntity<Map<String, Object>>(houseReviewService.createHouseReview(map), HttpStatus.OK);
	}

	@ApiOperation(value = "no에 해당하는 리뷰를 조회한다. db 조회 성공 시 리뷰를 반환한다.", response = HouseReview.class)
	@GetMapping("{no}")
	public ResponseEntity<HouseReview> getHouseReview(@PathVariable int no) {
		return new ResponseEntity<HouseReview>(houseReviewService.getHouseReview(no), HttpStatus.OK);
	}

	@ApiOperation(value = "입력받은 정보로 no에 해당하는 리뷰를 수정한다. db 수정 성공 시 리뷰 목록과 전체 리뷰 수를 담은  map 객체를  반환한다.", response = Map.class)
	@PutMapping("{no}")
	public ResponseEntity<Map<String, Object>> updateHouseReview(@PathVariable int no,
			@RequestBody Map<String, Object> map) {
		map.put("no", no);

		return new ResponseEntity<Map<String, Object>>(houseReviewService.updateHouseReview(map), HttpStatus.OK);
	}

	@ApiOperation(value = "no에 해당하는 리뷰를 삭제한다. db 삭제 성공 시 리뷰 목록과 전체 리뷰 수를 담은  map 객체를  반환한다.", response = Map.class)
	@DeleteMapping("{no}")
	public ResponseEntity<Map<String, Object>> deleteHouseReview(@PathVariable int no,
			@RequestBody Map<String, Object> map) {

		return new ResponseEntity<Map<String, Object>>(houseReviewService.deleteHouseReview(no, map), HttpStatus.OK);
	}

	@ApiOperation(value = "reviewNo와 userNo에 해당하는 좋아요를 저장한다. db 등록 성공 여부에 따라 현재 리뷰의 좋아요 개수 또는 -1을 반환한다.", response = Integer.class)
	@PostMapping("{reviewNo}/{userNo}")
	public ResponseEntity<Integer> saveInterestArea(@PathVariable int reviewNo, @PathVariable int userNo) {

		return new ResponseEntity<Integer>(houseReviewService.saveHouseReviewLike(reviewNo, userNo), HttpStatus.OK);
	}

	@ApiOperation(value = "reviewNo와 userNo에 해당하는 좋아요를 삭제한다. db 삭제성공 여부에 따라 현재 리뷰의 좋아요 개수 또는 -1을 반환한다.", response = Integer.class)
	@DeleteMapping("{reviewNo}/{userNo}")
	public ResponseEntity<Integer> deleteInterestArea(@PathVariable int reviewNo, @PathVariable int userNo) {

		return new ResponseEntity<Integer>(houseReviewService.deleteHouseReviewLike(reviewNo, userNo), HttpStatus.OK);
	}

}
