package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.QnA;
import com.ssafy.happyhouse.model.dto.QnAReply;

@Mapper
public interface QnAMapper {

	List<QnA> selectAllQnAs(int start, int sizePerPage);

	int selectAllQnAsCount();

	List<QnA> selectQnA(int authorNo);

	List<QnAReply> selectQnAReplies(int qnaNo);
	
	int insertQnA(QnA qna);
	
	int insertQnAReply(QnAReply qnaReply);
	
	int updateQnA(QnA qna);
	
	int updateQnAReply(QnAReply qnaReply);
	
	int deleteQnAReply(int qnaReplyNo);

	int deleteQnAReplies(int qnaNo);

	int deleteQnA(int qnaNo);
}
