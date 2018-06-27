/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.util.List;

import com.iruen.pscan.vo.Notice;

/**
 * @author nerve
 *
 */
public interface NoticeService {
	public int noticeWrite(Notice notice);
	public int noticeUpdate(Notice notice);
	public int noticeDelete(Notice notice);
	public Notice getNotice(Notice notice);
	public List<Notice> listNotice(Notice notice);
}
