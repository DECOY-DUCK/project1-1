package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.dto.QnAReply;
import com.ssafy.happyhouse.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnAMapper qnaMapper;

	@Override
	public Map<String, Object> getQnAs(int pageNo, int sizePerPage) {
		Map<String, Object> result = new HashMap<String, Object>();
		int start = (pageNo - 1) * sizePerPage;

		List<QnA> list = qnaMapper.selectAllQnAs(start, sizePerPage);
		result.put("list", list);
		result.put("count", qnaMapper.selectAllQnAsCount());

		return result;
	}

	@Override
	public QnA getQnA(@RequestParam Map<String, Object> map) {
		return qnaMapper.selectQnA(map);
	}

	@Override
	public boolean createQnA(QnA qna) {
		
		return qnaMapper.insertQnA(qna) == 1 ;
	}

	@Override
	public boolean modifyQnA(QnA qna) {
		return qnaMapper.updateQnA(qna) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteQnA(int qnaNo) {
		if(!qnaMapper.selectQnAReplies(qnaNo).isEmpty()) {
			if(qnaMapper.deleteQnAReplies(qnaNo) == 0) return false;
		}
		
		return qnaMapper.deleteQnA(qnaNo) == 1;
	}
	
	@Override
	public boolean createQnAReply(QnAReply qnaReply) {
		return qnaMapper.insertQnAReply(qnaReply) == 1;
	}
	
	@Override
	public boolean modifyQnAReply(QnAReply qnaReply) {

		return qnaMapper.updateQnAReply(qnaReply) == 1;
	}
	
	@Override
	public boolean deleteQnAReply(int no) {

		return qnaMapper.deleteQnAReply(no) == 1;
	}
	
}
