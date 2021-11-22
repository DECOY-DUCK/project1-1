package com.ssafy.happyhouse.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.controller.AuthController;
import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.dto.NoticeFile;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public Map<String, Object> getNotices(int pageNo, int sizePerPage) {
		Map<String, Object> result = new HashMap<String, Object>();
		int start = pageNo == 0 ? 0 : sizePerPage * pageNo + 1;

		List<Notice> list = noticeMapper.selectAllNotices(start, sizePerPage);

		result.put("list", list);
		result.put("count", noticeMapper.selectAllNoticesCount());

		return result;
	}

	@Override
	public Notice getNotice(int no) {
		updateNoticeViewCnt(no);
		
		return noticeMapper.selectNotice(no);
	}

	@Override
	public void updateNoticeViewCnt(int no) {
		noticeMapper.updateViewCnt(no);
	}

	@Override
	public boolean createNotice(Notice notice) {
		if (noticeMapper.insertNotice(notice) == 0) {
			return false;
		}

		NoticeFile image = notice.getImage();

		if (image != null) {
			if (noticeMapper.insertFile(notice) == 0) {
				return false;
			}
		}

		return true;
	}

	@Override
	@Transactional
	public boolean updateNotice(Notice notice, String path) {
		if (noticeMapper.updateNotice(notice) == 0) {
			return false;
		}

		NoticeFile image = notice.getImage();
		int no = notice.getNo();
		List<NoticeFile> images = noticeMapper.selectAllFiles(no + "");
		
		if (image != null) {
			if(images.isEmpty()) {
				if(noticeMapper.insertFile(notice) == 0) {
					return false;
				}
			} else {
				if (noticeMapper.updateFile(notice) == 0) {
					return false;
				}
				
				// 기존 이미지 삭제
				for (NoticeFile img : images) {
					File file = new File(path + File.separator + img.getSaveFolder() + File.separator + img.getSaveFile());
					file.delete();
				}
			}
		}
		
		return true;
	}

	@Override
	@Transactional
	public boolean deleteNotice(String nos, String path) {
		List<NoticeFile> images = noticeMapper.selectAllFiles(nos);
		if (noticeMapper.deleteFile(nos) == 0) {
			
			return false;
		}
		if (noticeMapper.deleteNotice(nos)==0) {
			logger.debug("들어감");
			return false;
		}
		if(images==null)return false;
		for (NoticeFile image : images) {
			File file = new File(path + File.separator + image.getSaveFolder() + File.separator + image.getSaveFile());
			file.delete();
		}

		return true;
	}

}
