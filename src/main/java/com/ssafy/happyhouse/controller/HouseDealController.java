package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoGugunCode;
import com.ssafy.happyhouse.model.service.HouseDealService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/housedeal")
public class HouseDealController {

	@Autowired
	private HouseDealService houseDealService;

	@ApiOperation(value = "전국의 시도를 조회한다. db 조회 성공 시 전국 시도 목록을  반환한다.", response = List.class)
	@GetMapping("sido")
	public ResponseEntity<List<SidoGugunCode>> getSido() {
		return new ResponseEntity<List<SidoGugunCode>>(houseDealService.getSido(), HttpStatus.OK);
	}

	@ApiOperation(value = "입력받은 시도에 해당하는 구군을 조회한다. db 조회 성공 시 구군 목록을  반환한다.", response = List.class)
	@GetMapping("gugun")
	public ResponseEntity<List<SidoGugunCode>> getGugun(String sidoCode) {
		return new ResponseEntity<List<SidoGugunCode>>(houseDealService.getGugunInSido(sidoCode), HttpStatus.OK);
	}

	@ApiOperation(value = "입력받은 구군에 해당하는 법정동을 조회한다. db 조회 성공 시 법정동 목록을  반환한다.", response = List.class)
	@GetMapping("dong")
	public ResponseEntity<List<DongCode>> getDong(String gugunCode) {
		return new ResponseEntity<List<DongCode>>(houseDealService.getDongInGugun(gugunCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "입력받은 동에 해당하는 시도와 구군을 조회한다. db 조회 성공 시 시도와 구군을  반환한다.", response = SidoGugunCode.class)
	@GetMapping("sidogugun")
	public ResponseEntity<SidoGugunCode> getSidoGugunByDong(String dongCode) {
		return new ResponseEntity<SidoGugunCode>(houseDealService.getSidoGugunByDong(dongCode), HttpStatus.OK);
	}
	

	@ApiOperation(value = "입력받은 지역에 해당하는 전체 아파트 정보를 조회한다. db 조회 성공 시 아파트 정보를  반환한다.", response = List.class)
	@GetMapping("apt")
	public ResponseEntity<List<HouseInfo>> getHouseInfos(@RequestParam Map<String, Object> map) {
		return new ResponseEntity<List<HouseInfo>>(houseDealService.getHouseInfoInDong(map), HttpStatus.OK);
	}
	
	@ApiOperation(value = "입력받은 아파트 이름에 해당하는 아파트 거래 정보를 조회한다. db 조회 성공 시 거래 정보를  반환한다.", response = List.class)
	@GetMapping("{aptName}")
	public ResponseEntity<List<HouseDeal>> getHouseDeals(@PathVariable String aptName, @RequestParam Map<String, Object> map) {
		// 개수가 없으면 공공데이터에서 가져오기 ?? + 3개년 치 공공데이터에서 가져오기? 
		map.put("aptName", aptName);
		return new ResponseEntity<List<HouseDeal>>(houseDealService.getHouseDealInDong(map), HttpStatus.OK);
	}
	
	
}
