package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.InterestArea;
import com.ssafy.happyhouse.model.service.InterestAreaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/interest")
public class InterestAreaController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private InterestAreaService interestAreaService;
	
	@ApiOperation(value = "userNo에 해당하는 관심지역을 조회한다. db 조회 성공 시 관심지역 목록을  반환한다.", response = List.class)
	@GetMapping("{userNo}")
	public ResponseEntity<List<InterestArea>> getInterestAreas(@PathVariable int userNo) {
		return new ResponseEntity<>(interestAreaService.getInterestAreas(userNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "userNo와 dongCode에 해당하는 관심지역 여부를 조회한다. db 조회 성공 시 존재 여부에 따라 0 또는 1을 반환한다.", response = Integer.class)
	@GetMapping("{userNo}/{dongCode}")
	public ResponseEntity<Integer> getInterestArea(@PathVariable int userNo, @PathVariable String dongCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("dongCode", dongCode);
		
		return new ResponseEntity<>(interestAreaService.getInterestArea(map), HttpStatus.OK);
	}

	@ApiOperation(value = "userNo와 dongCode에 해당하는 관심지역을 저장한다. db 입력 성공 여부에 따라'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("{userNo}/{dongCode}")
	public ResponseEntity<String> saveInterestArea(@PathVariable int userNo, @PathVariable String dongCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("dongCode", dongCode);
		
		if (interestAreaService.saveInterestArea(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	@ApiOperation(value = "userNo와 dongCode에 해당하는 관심지역을 삭제한다. db 삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{userNo}/{dongCode}")
	public ResponseEntity<String> deleteInterestArea(@PathVariable int userNo, @PathVariable String dongCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userNo", userNo);
		map.put("dongCode", dongCode);
		
		if (interestAreaService.deleteInterestArea(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
