/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iruen.pscan.mapper.NoticeMapper;
import com.iruen.pscan.vo.Notice;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Response;

/**
 * @author nerve
 *
 */
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired NoticeMapper noticeMapper;

	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

	/* 공지사항 생성
	 * @see com.iruen.pscan.appliance.service.NoticeService#noticeWrite(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public Response createNotice(Notice notice) {
		Response result	= new Response();
		
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession admin = (PSCANSession)session.getAttribute("Session");
		
		notice.setReg_id(admin.getUserId());
		
		int res = noticeMapper.createNotice(notice);
		if (res == 1) {
			result = new Response("OK", notice);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

	@Override
	public Response searchNotice(Map<String, String> map) {
		Response result; 
		List<Notice> noticeList = noticeMapper.searchNotices(map);
		logger.debug("Search Type :"+map.get("type")+", Search Text :"+map.get("text"));
		if (noticeList != null) {
			result = new Response("OK", noticeList);
		} else {
			result = new Response("NONE", null);
		}
		return result;
	}
	
	/* 공지사항 수정
	 * @see com.iruen.pscan.appliance.service.NoticeService#noticeUpdate(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public Response updateNotice(Notice notice) {
		Response result; 
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession admin = (PSCANSession)session.getAttribute("Session");
		
		notice.setMod_id(admin.getUserId());
		int cnt = noticeMapper.updateNotice(notice);
		if (cnt == 1) {
			result = new Response("OK", notice);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

	/* 공지사항 삭제
	 * @see com.iruen.pscan.appliance.service.NoticeService#noticeDelete(com.iruen.pscan.vo.Notice)
	 */
	@Override
	public Response deleteNotice(Notice notice) {
		Response result; 
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession admin = (PSCANSession)session.getAttribute("Session");
		
		notice.setMod_id(admin.getUserId());
		
		int cnt = noticeMapper.deleteNotice(notice);
		if (cnt == 1) {
			result = new Response("OK", notice);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

}
