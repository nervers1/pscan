/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iruen.pscan.mapper.NoticeMapper;
import com.iruen.pscan.vo.Notice;

/**
 * @author nerve
 *
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired NoticeMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

	/* 공지사항 생성
	 * @see com.iruen.pscan.appliance.service.NoticeService#noticeWrite(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public int noticeWrite(Notice notice) {
		return mapper.createNotice(notice);
	}

	/* 공지사항 수정
	 * @see com.iruen.pscan.appliance.service.NoticeService#noticeUpdate(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public int noticeUpdate(Notice notice) {
		return mapper.updateNotice(notice);
	}

	/* 공지사항 삭제
	 * @see com.iruen.pscan.appliance.service.NoticeService#noticeDelete(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public int noticeDelete(Notice notice) {
		return mapper.updateNotice(notice);
	}

	/* 공지사항 조회
	 * @see com.iruen.pscan.appliance.service.NoticeService#getNotice(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public Notice getNotice(Notice notice) {
		return mapper.getNotice(notice);
	}

	/* 공지사항 리스트 조회
	 * @see com.iruen.pscan.appliance.service.NoticeService#listNotice(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public List<Notice> listNotice(Notice notice) {
		return mapper.listNotice(notice);
	}
	

}
