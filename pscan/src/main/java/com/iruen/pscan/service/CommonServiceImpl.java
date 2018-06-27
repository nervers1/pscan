/**
 * 
 */
package com.iruen.pscan.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iruen.pscan.mapper.UserMapper;
import com.iruen.pscan.mapper.testMapper;
import com.iruen.pscan.vo.User;

/**
 * @author nerve
 *
 */
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired testMapper test;

	@Autowired UserMapper user;

	/* 세션정보 읽어오기
	 * @see com.iruen.pscan.service.CommonService#getHttpSession()
	 */
	@Override
	public HttpSession getHttpSession() throws Exception {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return servletRequestAttribute.getRequest().getSession(true);
	}


}
