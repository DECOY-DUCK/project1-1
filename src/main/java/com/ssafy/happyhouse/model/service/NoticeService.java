package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.Notice;

public interface NoticeService {
	/**
	 * 현재 페이지와 한번에 요청할 데이터 수를 받아서 해당 영역에 속한 공지사항 목록을 받아온다.
	 * 
	 * @param pageNo      : 현재 페이지
	 * @param sizePerPage : 한 페이지 당 데이터 수
	 * @return 해당 영역에 속한 공지사항 목록과 전체 공지사항 수
	 */
	Map<String, Object> getNotices(int pageNo, int sizePerPage);

	/**
	 * 식별 번호를 받아서 공지사항을 조회한다.
	 * 
	 * @param no : 공지사항 식별번호
	 * @return 해당하는 공지사항 정보
	 */
	Notice getNotice(int no);

	/**
	 * 식별 번호를 받아서 조회 수를 올린다.
	 * 
	 * @param no : 공지사항 식별번호
	 */
	void updateNoticeViewCnt(int no);

	/**
	 * 공지사항 정보를 받아서 등록한다.
	 * 
	 * @param notice : 공지사항 정보
	 * @return 등록 성공 여부
	 */
	boolean createNotice(Notice notice);

	/**
	 * 공지사항 정보를 받아서 업데이트한다.
	 * 
	 * @param notice : 공지사항 정보
	 * @param path   : 기존 파일이 저장된 경로
	 * @return 업데이트 성공 여부
	 */
	boolean updateNotice(Notice notice, String path);

	/**
	 * 공지사항 식별 번호들을 받아서 삭제한다.
	 * 
	 * @param nos  : 공지사항 번호
	 * @param path : 기존 파일이 저장된 경로
	 * @return 삭제 성공 여부
	 */
	boolean deleteNotice(String nos, String path);
}
