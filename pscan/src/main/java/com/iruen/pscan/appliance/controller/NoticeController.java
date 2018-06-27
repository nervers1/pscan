package com.iruen.pscan.appliance.controller;

import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 공지사항 컨트롤러 비즈니스 로직
 * @author nerve
 *
 */
@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@RequestMapping(value = "/bbs/noticeContent", method = RequestMethod.GET) 
	public String content(Model model) throws Exception {
		return "bbs/noticeContent";
	}
	@RequestMapping(value = "/bbs/noticeManager", method = RequestMethod.GET) 
	public String manager(Model model) throws Exception {
		return "bbs/noticeManager";
	}

	@RequestMapping(value = "/noticeList", method = RequestMethod.GET) 
	public String noticeList(Model model, HttpSession session) throws Exception {
		model.addAttribute("contents", "noticeList");
		return "bbs/notice";
	}
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.GET) 
	public String noticeWrite(Model model, HttpSession session) throws Exception {
		model.addAttribute("contents", "noticeWrite");
		return "bbs/notice";
	}
	@RequestMapping(value = "/noticeWrite", method = RequestMethod.POST) 
	public String noticeWritePost(Model model, @RequestParam HashMap<String, String> map, HttpSession session) throws Exception {
		model.addAttribute("contents", "noticeWrite");
		Set<String> paramSet = map.keySet();
		for (String s : paramSet) {
			logger.debug("param : {}", map.get(s));
			
		}
		return "bbs/notice";
	}
	@RequestMapping(value = "/noticeView", method = RequestMethod.GET) 
	public String noticeView(Model model, HttpSession session) throws Exception {
		model.addAttribute("contents", "noticeView");
		return "bbs/notice";
	}
	@RequestMapping(value = "/bbs/noticeList", method = RequestMethod.GET) 
	public String list(Model model) throws Exception {
		return "bbs/noticeList";
	}
	@RequestMapping(value = "/bbs/noticeView", method = RequestMethod.GET) 
	public String view(Model model) throws Exception {
		return "bbs/noticeView";
	}
	@RequestMapping(value = "/bbs/noticeWrite", method = RequestMethod.GET) 
	public String write(Model model) throws Exception {
		return "bbs/noticeWrite";
	}
}
