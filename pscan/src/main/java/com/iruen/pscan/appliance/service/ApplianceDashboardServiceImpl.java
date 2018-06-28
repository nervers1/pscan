/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iruen.pscan.mapper.FileMapper;
import com.iruen.pscan.mapper.PolicyMapper;
import com.iruen.pscan.vo.CheckFile;
import com.iruen.pscan.vo.CheckFileResultInfo;
import com.iruen.pscan.vo.CheckParam;
import com.iruen.pscan.vo.CheckResultDtl;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Policy;
import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.UploadFile;
import com.iruen.pscan.vo.User;

import kpic.PrivacyCheck;
import kpic.model.CheckPattern;
import kpic.model.CheckResult;
import kpic.model.FileList;
import kpic.model.FileResult;
import kpic.model.PatternMatcher;

/**
 * @author nerve
 *
 */
@Service
public class ApplianceDashboardServiceImpl implements ApplianceDashboardService {

	private static final Logger logger = LoggerFactory.getLogger(ApplianceDashboardServiceImpl.class);

	@Autowired
	FileMapper fileMapper;

	@Override
	public Response dashboardCheckResultLimit(int limit) {
		
		Response result; 
		List<CheckFileResultInfo> checkFileResultList;
		
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		if (pss != null && "Y".equals(pss.getAdminYn())) {
				checkFileResultList = fileMapper.checkFileListLimit(limit);
		} else {
			User searchUser = new User();
			searchUser.setIdx(pss.getIdx());
			searchUser.setUserId(pss.getUserId());
			
			checkFileResultList = fileMapper.checkFileList(searchUser);
		}
		
		if (checkFileResultList != null) {
			result = new Response("OK", checkFileResultList);
		} else {
			result = new Response("NONE", null);
		}
		return result;

	}


}
