package com.ssafy.happyhouse.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.dto.NoticeFile;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public Map<String, Object> getNotices(int pageNo, int sizePerPage) {
		Map<String, Object> result = new HashMap<String, Object>();
		int start = (pageNo - 1) * sizePerPage;

		List<Notice> list = noticeMapper.selectAllNotices(start, sizePerPage);

		result.put("list", list);
		result.put("count", noticeMapper.selectAllNoticesCount());

		return result;
	}

	@Override
	public Notice getNotice(int no) {
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
	public boolean updateNotice(Notice notice) {
		if (noticeMapper.updateNotice(notice) == 0) {
			return false;
		}

		NoticeFile image = notice.getImage();
		int no = notice.getNo();

		if (image != null) {
			if (noticeMapper.selectAllFiles(no + "").isEmpty()) {
				if (noticeMapper.insertFile(notice) == 0) {
					return false;
				}
			} else {
				if (noticeMapper.updateFile(notice) == 0) {
					return false;
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
		if (noticeMapper.deleteNotice(nos) == 0) {
			return false;
		}

		for (NoticeFile image : images) {
			File file = new File(path + File.separator + image.getSaveFolder() + File.separator + image.getSaveFile());
			file.delete();
		}

		return true;
	}

}
