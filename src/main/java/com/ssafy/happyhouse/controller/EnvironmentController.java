package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.SafeHospital;
import com.ssafy.happyhouse.model.dto.SelectedClinic;
import com.ssafy.happyhouse.model.service.EnvironmentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {
	
	@Autowired
	private EnvironmentService environmentService;
	
	@ApiOperation(value = "입력받은 시도와 구군에 해당하는 국민안심병원을 조회한다. db 조회 성공 시 국민안심병원 목록을  반환한다.", response = List.class)
	@GetMapping("safehospital")
	public ResponseEntity<List<SafeHospital>> getNearbyHospitals(
			@RequestParam("sido") String sido,
			@RequestParam("gugun") String gugun
		){
		return new ResponseEntity<List<SafeHospital>>(environmentService.getNearbyHospitals(sido, gugun), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "입력받은 시도와 구군에 해당하는 선별진료소을 조회한다. db 조회 성공 시 선별진료소 목록을  반환한다.", response = List.class)
	@GetMapping("selectedclinic")
	public ResponseEntity<List<SelectedClinic>> getNearbyClinics(
			@RequestParam("sido") String sido,
			@RequestParam("gugun") String gugun
		){
		return new ResponseEntity<List<SelectedClinic>>(environmentService.getNearbyClinics(sido, gugun), HttpStatus.OK);
	}
}