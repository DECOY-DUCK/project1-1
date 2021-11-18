package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.dto.QnAReply;

public interface QnAService {

	/**
	 * 현재 페이지와 한번에 요청할 데이터 수를 받아서 해당 영역에 속한 qna 목록을 받아온다.
	 * @param pageNo : 현재 페이지 
	 * @param sizePerPage : 한 페이지 당 데이터 수  
	 * @return 해당 영역에 속한 qna 목록과 전체 qna 수
	 */	
	Map<String, Object> getQnAs(int pageNo, int sizePerPage);

	/**
	 * 식별 번호와 비밀번호를 받아서 qna을 조회한다.
	 * 
	 * @param map : no와 password 포함
	 * @return 해당하는 qna 정보
	 */
	QnA getQnA(Map<String, Object> map);

	/**
	 * qna 정보를 받아서 등록한다.
	 * 
	 * @param qna : qna 정보 
	 * @return 입력 성공 여부
	 */
	boolean createQnA(QnA qna);

	/**
	 * qna 정보를 받아서 수정한다.
	 * @param qna : qna 정보 
	 * @return 수정 성공 여부
	 */
	boolean modifyQnA(QnA qna);

	/**
	 * qna 식별 번호를 받아서 삭제한다.
	 * @param qnaNo : qna 식별번호 
	 * @return 삭제 성공 여부
	 */
	boolean deleteQnA(int qnaNo);
	
	/**
	 * qnaReply 정보를 받아서 등록한다.
	 * 
	 * @param qnaReply : qnaReply 정보
	 * @return 저장 성공 여부
	 */
	boolean createQnAReply(QnAReply qnaReply);

	/**
	 * qnaReply 정보를 받아서 업데이트한다.
	 * @param qnaReply : qnaReply 정보 
	 * @return 수정 성공 여부
	 */
	boolean modifyQnAReply(QnAReply qnaReply);

	/**
	 * qnaReply 식별번호를 받아서 삭제한다.
	 * @param no : qnaReply 식별번호
	 * @return 삭제 성공 여부
	 */
	boolean deleteQnAReply(int no);
}