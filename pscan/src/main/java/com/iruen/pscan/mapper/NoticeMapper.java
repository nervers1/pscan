/**
 * 
 */
package com.iruen.pscan.mapper;

import java.util.List;
import java.util.Map;

import com.iruen.pscan.vo.Notice;

/**
 * @author nerve
 *
 */
public interface NoticeMapper {
	
	public int createNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(Notice notice);
	public List<Notice> searchNotices(Map<String, String> map);
	
}
