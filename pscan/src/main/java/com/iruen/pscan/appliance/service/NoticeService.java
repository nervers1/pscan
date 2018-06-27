/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.util.List;
import java.util.Map;

import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.Notice;

/**
 * @author nerve
 *
 */
public interface NoticeService {
	public Response createNotice(Notice notice);
	public Response updateNotice(Notice notice);
	public Response deleteNotice(Notice notice);
	public Response searchNotice(Map<String, String> map);
}
