/**
 * 
 */
package com.iruen.pscan.appliance.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iruen.pscan.appliance.service.ApplianceCommonService;
import com.iruen.pscan.appliance.service.ApplianceUserService;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.User;


/**
 * @author nerve
 *
 */
@Controller
@SessionAttributes("Session")
@RequestMapping(value = "/appliance")
public class ApplianceMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplianceMainController.class);

	
	@Autowired ApplianceCommonService common;
	@Autowired ApplianceUserService service;
	
	@ModelAttribute
	public PSCANSession sessionInfo(HttpSession session) {
		return (PSCANSession) session.getAttribute("Session"); 
	}

	@RequestMapping(value = "invalidate", method = RequestMethod.GET)
	public String invalidate(Model model, HttpSession session) throws Exception {
		if (session.isNew()) {
			logger.debug("New Session Started!");
		} else {
			session.invalidate();
		}
		return "appliance/main/main";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String applianceMain(Model model, HttpSession session) throws Exception {
		String returnURL = "appliance/main/main";
		model.addAttribute("contents", "main");
		PSCANSession current = (PSCANSession)session.getAttribute("Session");
		logger.debug("{}", current);
		if (current != null) {
			String adminYn = current.getAdminYn();
			if ("Y".equals(adminYn)) {
				returnURL = "appliance/main/adminMain";
			} else {
				returnURL = "appliance/main/memberMain";
			}
		}
		
		logger.info("Entering Appliance Main Page.");
		return returnURL;
	}
	
	/**
	 * 인클루드 해더
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/header")
	public String header(Model model, HttpSession session) throws Exception {
		model.addAttribute("User", (PSCANSession)session.getAttribute("Session"));
		return "appliance/include/header";
	}
	
	/**
	 * 메인섹션(로그인 전)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sectionMain")
	public String sectionMain(Model model, HttpSession session) throws Exception {
		String page = "appliance/include/sectionMain";
		
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		model.addAttribute("User", pss);
		
		if (pss != null && "Y".equals(pss.getAdminYn())) {
			page = "appliance/include/admin/sectionMain";
			logger.debug("관리자 로그인");
		} else if (pss != null && "N".equals(pss.getAdminYn())) {
			page = "appliance/include/member/sectionMain";
		} else {
			page = "appliance/include/sectionMain";
		}
		return page;
	}
	
	/**
	 * 메인섹션(사용자 로그인 후)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sectionMember")
	public String sectionMember(Model model, HttpSession session) throws Exception {
		String page;
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		if (pss != null && "N".equals(pss.getAdminYn())) {
			logger.debug("사용자 로그인");
			model.addAttribute("User", (PSCANSession)session.getAttribute("Session"));
			page = "appliance/include/member/sectionMain";
		} else {
			page = "appliance/include/sectionMain";
		}
		return page;
	}
	
	/**
	 * 메인섹션(관리자 로그인 후)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sectionAdmin")
	public String sectionAdmin(Model model, HttpSession session) throws Exception {
		String page;
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		if (pss != null && "Y".equals(pss.getAdminYn())) {
			logger.debug("관리자 로그인");
			model.addAttribute("User", (PSCANSession)session.getAttribute("Session"));
			page = "appliance/include/admin/sectionMain";
		} else {
			page = "appliance/include/sectionMain";
		}
		return page;
	}
	
	/**
	 * 메인섹션(Blank)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sectionMainBlank")
	public String sectionMainBlank(Model model, HttpSession session) throws Exception {
		model.addAttribute("User", (PSCANSession)session.getAttribute("Session"));
		return "appliance/include/sectionMainBlank";
	}
	
	/**
	 * 풋터
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/footer")
	public String footer() throws Exception {
		return "appliance/include/footer";
	}
	
	/**
	 * 로그인 처리
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(User user, Model model) throws Exception {
		logger.info("Entering Appliance Login Page.");
		logger.info("User : {}", user);
		return "appliance/main/main";
	}

	/**
	 * Appliance 설정
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(Model model) throws Exception {
		return "appliance/main/setting";
	}
	
	/**
	 * 사용자관리
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(Model model) throws Exception {
		model.addAttribute("contents", "user");
		return "appliance/main/main";
	}
	
	/**
	 * 정책관리
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public String policy(Model model) throws Exception {
		return "appliance/main/policy";
	}
	
	/**
	 * 정책관리 컨텐츠(Admin)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/policyAdmin", method = RequestMethod.GET)
	public String policyAdmin(Model model) throws Exception {
		return "appliance/include/admin/policy";
	}
	
	/**
	 * 사용자관리 컨텐츠(Admin)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberAdmin", method = RequestMethod.GET)
	public String memberAdmin(Model model) throws Exception {
		model.addAttribute("contents", "member");
		return "appliance/include/admin/member";
	}
	
	/**
	 * 설정 컨텐츠(Admin)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/settingAdmin", method = RequestMethod.GET)
	public String settingAdmin(Model model) throws Exception {
		model.addAttribute("contents", "setting");
		return "appliance/include/admin/setting";
	}

	@RequestMapping(value = "/sectionFileUpload", method = RequestMethod.GET)
	public String sectionFileUpload(Model model, HttpSession session) throws Exception {
		String page;
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		if (pss != null && "Y".equals(pss.getAdminYn())) {
			model.addAttribute("User", (PSCANSession)session.getAttribute("Session"));
			page = "appliance/include/admin/sectionFileUpload";
		} else {
			page = "appliance/include/member/sectionFileUpload";
		}
		return page;
	}

	/** 
	 * File Upload
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String file(Model model) throws Exception {
		model.addAttribute("contents", "upload");
		return "appliance/main/upload";
	}
	
	/**
	 * 개인정보검출 컨텐츠
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkContents", method = RequestMethod.GET)
	public String checkContents(Model model) throws Exception {
		return "appliance/include/check/checkContents";
	}
	
	/**
	 * 개인정보검출 컨텐츠
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkResultContents", method = RequestMethod.GET)
	public String checkResultContents(Model model) throws Exception {
		return "appliance/include/check/checkResultContents";
	}
	
	/**
	 * 개인정보검출 컨텐츠
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/historyContents", method = RequestMethod.GET)
	public String historyContents(Model model) throws Exception {
		return "appliance/include/check/historyContents";
	}
	
	/**
	 * 개인정보검출
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String check(Model model) throws Exception {
		model.addAttribute("contents", "check");
		return "appliance/main/main";
	}
	
	/**
	 * 개인정보검출결과
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkResult", method = RequestMethod.GET)
	public String checkResult(Model model) throws Exception {
		model.addAttribute("contents", "checkResult");
		return "appliance/main/main";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET) 
	public String dashboard() throws Exception {
		return "appliance/main/dashboard";
	}
	
	@RequestMapping(value = "/dashboardContents", method = RequestMethod.GET) 
	public String dashboardContents(HttpSession session) throws Exception {
		String page;
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		if (pss != null && "Y".equals(pss.getAdminYn())) {
			page = "appliance/include/dashboard/dashboardAdmin";
		} else {
			page = "appliance/include/dashboard/dashboardMember";
		}
		return page;
	}
	
	@RequestMapping(value = "/history", method = RequestMethod.GET) 
	public String history(Model model) throws Exception {
		model.addAttribute("contents", "history");
		return "appliance/main/main";
	}
	
	/**
	 * 공지사항관리(Admin)
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/notice", method = RequestMethod.GET) 
	public String notice(Model model, HttpSession session) throws Exception {
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		if (pss != null && "Y".equals(pss.getAdminYn())) {
			model.addAttribute("contents", "noticeManager");
		} else {
			model.addAttribute("contents", "notice");
		}
		
		return "bbs/notice";
	}
	
	
}
