package com.ssafy.happyhouse.controller;

import java.util.HashMap;
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

import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.dto.QnAReply;
import com.ssafy.happyhouse.model.service.QnAService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("qna")
public class QnAController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnaService;

	@ApiOperation(value = "QnA 글목록", notes = "모든 문의글의 정보를 반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getQnAs(int pageNo, int sizePerPage) {
		return new ResponseEntity<Map<String, Object>>(qnaService.getQnAs(pageNo, sizePerPage), HttpStatus.OK);
	}

	@ApiOperation(value = "QnA 글보기", notes = "글번호에 해당하는 문의글의 정보를 반환한다.", response = QnA.class)
	@GetMapping("/{no}")
	public ResponseEntity<QnA> getQnA(@PathVariable int no, @RequestBody(required = false) String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);

		return new ResponseEntity<QnA>(qnaService.getQnA(map), HttpStatus.OK);
	}

	@ApiOperation(value = "QnA 글작성", notes = "새로운 문의글 정보를 입력한다. db 입력 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createQnA(@RequestBody QnA qna) {
		if (qnaService.createQnA(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "QnA 글수정", notes = "수정할 문의글 정보를 입력한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{no}")
	public ResponseEntity<String> modifyQnA(@PathVariable int no, @RequestBody QnA qna) {
		qna.setNo(no);
		
		if (qnaService.modifyQnA(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "QnA 글삭제", notes = "글번호에 해당하는 문의글의 정보를 삭제한다. db 삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{no}")
	public ResponseEntity<String> deleteQnA(@PathVariable int no) {
		if (qnaService.deleteQnA(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "QnA 댓글 작성", notes = "새로운 댓글 정보를 입력한다. db 입력 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/{qnaNo}/{no}")
	public ResponseEntity<String> createQnAReply(@PathVariable int qnaNo, @PathVariable int no, QnAReply qnaReply) {
		qnaReply.setNo(no);
		qnaReply.setQnaNo(qnaNo);

		if (qnaService.modifyQnAReply(qnaReply)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "QnA 댓글 수정", notes = "수정할 댓글 정보를 입력한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{qnaNo}/{no}")
	public ResponseEntity<String> modifyQnAReply(@PathVariable int qnaNo, @PathVariable int no,
			@RequestBody QnAReply qnaReply) {
		qnaReply.setNo(no);
		qnaReply.setQnaNo(qnaNo);

		if (qnaService.modifyQnAReply(qnaReply)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "QnA 댓글 삭제", notes = "삭제할 댓글 정보를 입력한다. db 삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qnaNo}/{no}")
	public ResponseEntity<String> modifyQnAReply(@RequestParam int no) {
		if (qnaService.deleteQnAReply(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
