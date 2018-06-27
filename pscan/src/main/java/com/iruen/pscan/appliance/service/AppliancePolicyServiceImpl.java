/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iruen.pscan.mapper.PolicyMapper;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.Policy;

/**
 * @author nerve
 *
 */
@Service
public class AppliancePolicyServiceImpl implements AppliancePolicyService {

	@Autowired PolicyMapper policyMapper;
	@Autowired ApplianceCommonService commonService;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplianceUserService.class);

	@Value("#{db['path.dirs']}")
	private String path;
	@Value("#{db['user.defaultpassword']}")
	private String userPassword;
	@Value("#{db['admin.defaultpassword']}")
	private String adminPassword;
	
	@Override
	public Response createPolicy(Policy policy) {
		
		Response result; 

		// 세션에서 등록자 정보 
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession admin = (PSCANSession)httpSession.getAttribute("Session");
		
		int res = policyMapper.createPolicy(policy);
		if (res == 1) {
			result = new Response("OK", policy);
		} else {
			result = new Response("Error", "");
		}
		
		return result;
	}

	@Override
	public Response searchPolicy(Map<String, String> map) {
		Response result; 
		List<Policy> policyList = policyMapper.searchPolicies(map);
		if (policyList != null) {
			result = new Response("OK", policyList);
		} else {
			result = new Response("NONE", null);
		}
		return result;
	}

	/* 정책 수정
	 * @see com.iruen.pscan.appliance.service.AppliancePolicyService#updatePolicy(com.iruen.pscan.vo.Policy)
	 */
	@Override
	public Response updatePolicy(Policy policy) {
		Response result; 
		int cnt = policyMapper.updatePolicy(policy);
		if (cnt == 1) {
			result = new Response("OK", policy);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

}
