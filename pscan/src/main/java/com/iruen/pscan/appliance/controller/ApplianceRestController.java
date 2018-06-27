/**
 * 
 */
package com.iruen.pscan.appliance.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iruen.pscan.appliance.service.ApplianceCommonService;
import com.iruen.pscan.appliance.service.ApplianceUserService;
import com.iruen.pscan.appliance.service.AppliancePolicyService;
import com.iruen.pscan.appliance.service.NoticeService;
import com.iruen.pscan.service.CommonService;
import com.iruen.pscan.util.FileUtil;
import com.iruen.pscan.util.SecureUtil;
import com.iruen.pscan.vo.CheckFileResultInfo;
import com.iruen.pscan.vo.CheckParam;
import com.iruen.pscan.vo.Notice;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Policy;
import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.UploadFile;
import com.iruen.pscan.vo.User;

import kpic.model.FileList;

/**
 * @author nerve
 *
 */
@RestController
public class ApplianceRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplianceRestController.class);

	@Value("#{db['path.dirs']}")
	private String path;
	
	@Value("#{db['path.replaceDirs']}")
	private String replacedPath;


	@Autowired CommonService common;
	@Autowired ApplianceCommonService appService;
	@Autowired ApplianceUserService service;
	@Autowired AppliancePolicyService policyService;	
	@Autowired NoticeService noticeService;
	
	@PostMapping("/api/login")
	public Response login(@RequestBody User user) {
		logger.debug("/api/login .. "); 
		logger.debug("id : {}, pw : {}", user.getUserId(), user.getPassword());
		Response result = service.login(user);
		return result;
	}
	
	@PostMapping("/api/logout")
	public Response logout() {
		logger.debug("/api/logout .. "); 
		return service.logout();
	}
	
	@PostMapping("/api/createUser")
	public Response registerUser(@RequestBody User user) {
		logger.debug("/api/createUser .. "); 
		return service.createUser(user);
	}
	
	@PostMapping("/api/updateUser")
	public Response updateUser(@RequestBody User user) {
		logger.debug("/api/updateUser .. "); 
		return service.updateUser(user);
	}
	
	@PostMapping("/api/deleteUser")
	public Response deleteUser(@RequestBody User user) {
		logger.debug("/api/deleteUser .. "); 
		return service.deleteUser(user);
	}
	
	@PostMapping("/api/searchUser")
	public Response searchUser(@RequestBody HashMap<String, String> map) {
		logger.debug("/api/searchUser .. "); 
		return service.searchUser(map);
	}
	
	@PostMapping("/api/listFiles")
	public Response listFiles() {
		Response res 				= new Response();
		UploadFile upload 			= new UploadFile();
		
		// 세션에서 등록자 정보 
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession user = (PSCANSession)session.getAttribute("Session");
		
		upload.setUserIdx(user.getIdx());
		List<UploadFile> list = appService.listFiles(upload);
		
		res.setData(list);
		res.setStatus("OK");
		
		return res;
	}

	@PostMapping("/api/listPolicy")
	public Response listPolicy() {
		Response res	= new Response();
		Policy policy = new Policy();
		policy.setExprYn("Y");
		
		List<Policy> list = appService.listPolicy(policy);
		/*logger.debug("======================== Policy ");
		for (Policy p :list) {
			logger.debug("SmItmNm --> {}", p.getSmItmNm());
		}*/
		res.setStatus("OK");
		res.setData(list);
		return res;
	}
	
	@PostMapping("/api/checkFiles")
	public Response checkFiles(List<UploadFile> files) {
		Response res	= new Response();
		FileUtil util	= new FileUtil();
		FileList list	= util.transform(files);
		
		// check
		
		return res;
	}
	
	@PostMapping("/api/check")
	public Response check(@RequestBody CheckParam param) throws IOException {
		Response res	= new Response();

		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		
		List<CheckFileResultInfo> checkMapList = appService.documentChecker(session, param);
		
		session.setAttribute("checkMapList", checkMapList);
		
		res.setData(checkMapList);
		res.setStatus("OK");
		return res;
	}
	
	@PostMapping("/api/setPassword")
	public Response setPassword(@RequestBody String password) throws IOException {
		Response res	= new Response();
		logger.debug("password {}", password);
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession session = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession user = (PSCANSession)session.getAttribute("Session");
		
		User chgUser = new User();
		chgUser.setIdx(user.getIdx());
		chgUser.setCheck_yn("Y");
		chgUser.setPassword(SecureUtil.SHA256(password));
		
		return service.setPassword(chgUser);
		
	}
	
	@PostMapping("/api/createNotice")
	public Response createNotice(@RequestBody Notice notice) throws IOException {
		logger.debug("/api/createNotice .. "); 
		return noticeService.createNotice(notice);	
	}
	
	@PostMapping("/api/updateNotice")
	public Response updateNotice(@RequestBody Notice notice) {
		logger.debug("/api/updateNotice .. "); 
		return noticeService.updateNotice(notice);
	}
	
	@PostMapping("/api/deleteNotice")
	public Response deleteNotice(@RequestBody Notice notice) {
		logger.debug("/api/deleteNotice .. "); 
		return noticeService.deleteNotice(notice);
	}
	
	@PostMapping("/api/searchNotice")
	public Response searchNotice(@RequestBody HashMap<String, String> map) {
		logger.debug("/api/searchNotice .. "); 
		return noticeService.searchNotice(map);
	}
	
	@PostMapping("/api/searchPolicy")
	public Response searchPolicy(@RequestBody HashMap<String, String> map) {
		logger.debug("/api/searchPolicy .. "); 
		return policyService.searchPolicy(map);
	}
	
	@PostMapping("/api/updatePolicy")
	public Response updatePolicy(@RequestBody Policy policy) {
		logger.debug("/api/updatePolicy .. "); 
		return policyService.updatePolicy(policy);
	}	
	
}
