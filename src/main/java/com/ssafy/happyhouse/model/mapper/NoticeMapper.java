package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.dto.NoticeFile;

@Mapper
public interface NoticeMapper {
	
	List<Notice> selectAllNotices(int start, int sizePerPage);
	
	Notice selectNotice(int no);

	int selectAllNoticesCount();

	List<NoticeFile> selectAllFiles(String noticeNos);
	
	NoticeFile selectFile(int noticeNo);

	int insertNotice(Notice notice);

	int insertFile(Notice notice);

	int updateNotice(Notice notice);

	void updateViewCnt(int no);
	
	int updateFile(Notice notice);

	int deleteNotice(String nos);

	int deleteFile(String noticeNos);

}
