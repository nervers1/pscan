/**
 * 
 */
package com.iruen.pscan.mapper;

import java.util.List;

import com.iruen.pscan.vo.Notice;

/**
 * @author nerve
 *
 */
public interface NoticeMapper {
	
	public int createNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(Notice notice);
	public Notice getNotice(Notice notice);
	public List<Notice> listNotice(Notice notice);
	
}
