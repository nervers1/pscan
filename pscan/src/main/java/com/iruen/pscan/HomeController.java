package com.iruen.pscan;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iruen.pscan.mapper.UserMapper;
import com.iruen.pscan.mapper.testMapper;
import com.iruen.pscan.util.SecureUtil;
import com.iruen.pscan.vo.User;

import net.sf.json.JSONObject;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	testMapper test;
	
	@Autowired
	UserMapper userMapper;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public User test(User user) {
		
		user.setName("iruen");
		user.setUserId("iruen");
		user.setPassword("iruen1111");
		user.setEmail("nervers1@gmail.com");
		user.setDeptNm("기업부설연구소");
		user.setDeptNo("0593008");
		user.setPositionNm("수석");
		user.setDescription("이루엔의 개발자입니다.");
		user.setAdminYn("Y");

		String encPassword = SecureUtil.SHA256(user.getPassword());
		
		user.setPassword(encPassword);
		
		
/*		ObjectMapper mapper = new ObjectMapper();
		// Convert object to JSON string and save into file directly 
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.writeValue(new File("D:\\user.json"), user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		//JSONObject obj = JSONObject.fromObject(user);
		
		userMapper.createUser(user);

		//logger.info("{}", obj);
		logger.info("{}", user);
		
		return user;
	}
	
	
	@RequestMapping(value = "/maria", method = RequestMethod.GET)
	@ResponseBody
	public String testMessage() {
		ObjectMapper mapper = new ObjectMapper();
		String message = test.getMessage();
		String result = "";
		try {
			result = mapper.writeValueAsString(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(">>> {} ", message);
		logger.info(">>> {} ", result);
		
		return result;
	}
	
	@RequestMapping(value = "/maria2", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject testMessage2() {
		JSONObject obj = new JSONObject();
		obj.put("result", test.getMessage());
		logger.info(">>> {} ", obj);
		
		return obj;
	}
}
