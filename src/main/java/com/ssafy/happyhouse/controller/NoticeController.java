package com.ssafy.happyhouse.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.happyhouse.config.MultipartProperties;
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.dto.NoticeFile;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notice")
public class NoticeController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private MultipartProperties multipartProperties;

	@ApiOperation(value = "입력받은 영역에 해당하는 공지사항을 조회한다. db 조회 성공 시 공지사항 목록과 전체 공지사항 수를 담은 map 객체를  반환한다.", response = Map.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getNotices(int pageNo, int sizePerPage) {

		return new ResponseEntity<Map<String, Object>>(noticeService.getNotices(pageNo, sizePerPage), HttpStatus.OK);
	}

	@ApiOperation(value = "입력받은 공지사항 정보를 저장한다. db 입력 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createNotice(Notice notice, MultipartFile attach) throws IOException {
		setImageFile(notice, attach);

		if (noticeService.createNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "no에 해당하는 공지사항을 조회한다. db 조회 성공 시 공지사항을 반환한다.", response = Notice.class)
	@GetMapping("{no}")
	public ResponseEntity<Notice> getNotice(@PathVariable int no) {
		// 유저 처리하는 거 보고 읽는 유저 no랑 authorNo 비교해서 update cnt 할 수 있도록 수정
		return new ResponseEntity<Notice>(noticeService.getNotice(no), HttpStatus.OK);
	}

	@ApiOperation(value = "입력받은 정보로 no에 해당하는 공지사항을 수정한다. db 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateNotice(@PathVariable int no, Notice notice, MultipartFile attach)
			throws IOException {
		setImageFile(notice, attach);
		notice.setNo(no);

		if (noticeService.updateNotice(notice, servletContext.getRealPath(""))) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	private void setImageFile(Notice notice, MultipartFile attach) throws IOException {
		if (attach == null)
			return;

		NoticeFile image = new NoticeFile();
		String fileName = System.currentTimeMillis() + "_" + attach.getOriginalFilename();
		image.setOriginFile(attach.getOriginalFilename());
		image.setSaveFile(fileName);
		image.setSaveFolder("/upload/notice");

		Resource resource = resourceLoader.getResource("file:" + multipartProperties.getLocation() + "upload/notice");
		File dir = new File(resource.getFile().getCanonicalPath());

		if (!dir.exists())
			dir.mkdirs();

		attach.transferTo(new File(dir, fileName));

		notice.setImage(image);
	}

	@ApiOperation(value = "no에 해당하는 공지사항들을 삭제한다. db 삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{nos}")
	public ResponseEntity<String> deleteNotice(@PathVariable String nos) {
		if (noticeService.deleteNotice(nos, servletContext.getRealPath(""))) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
