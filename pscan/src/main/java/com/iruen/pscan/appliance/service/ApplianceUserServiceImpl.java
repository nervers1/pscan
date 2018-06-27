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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iruen.pscan.mapper.UserMapper;
import com.iruen.pscan.util.SecureUtil;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.User;

import kpic.model.FileList;

/**
 * @author nerve
 *
 */
@Service
public class ApplianceUserServiceImpl implements ApplianceUserService {

	@Autowired UserMapper userMapper;
	@Autowired ApplianceCommonService commonService;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplianceUserService.class);

	@Value("#{db['path.dirs']}")
	private String path;
	@Value("#{db['user.defaultpassword']}")
	private String userPassword;
	@Value("#{db['admin.defaultpassword']}")
	private String adminPassword;
	
	/* 사용자 로그인 처리
	 * @see com.iruen.pscan.appliance.service.ApplianceUserService#login(com.iruen.pscan.vo.User)
	 */
	@Override
	public Response login(User user) {
		Response result; 
		String encPassword = SecureUtil.SHA256(user.getPassword());
		User pscanUser = userMapper.getUser(user);
		
		if (pscanUser != null) {
			
			if ("Y".equals(pscanUser.getUseYn())) {
				if (encPassword.equals(pscanUser.getPassword())) {
					// 파일 리스트 생성
					String defaultFileSavePath = path + File.separator + pscanUser.getUserId();
					FileList fileList = new FileList();
					fileList.addFile(defaultFileSavePath, true); // 파일리스트 강제생성
					
					// 세션에 사용자 정보 저장
					ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
							.currentRequestAttributes();
					HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
					httpSession.setAttribute("Session", commonService.setUserSession(pscanUser)); // 사용자 정보
					httpSession.setAttribute("PSCAN_FILELIST", fileList);                         // 디폴트 파일 리스트 저장
					
					result = new Response("OK", pscanUser);
				} else {
					result = new Response("DISMATCH", user);
				}
			} else {
				result = new Response("NOUSE", user);
			}
		} else {
			result = new Response("NOTEXIST", user);
		}
		return result;
	}

	/* 로그아웃
	 * @see com.iruen.pscan.appliance.service.ApplianceUserService#logout(com.iruen.pscan.vo.User)
	 */
	@Override
	public Response logout() {
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		servletRequestAttribute.getRequest().getSession(true).invalidate();
		Response result = new Response("LOGOUT", "");
		return result;
	}

	@Override
	public Response createUser(User user) {
		
		Response result; 
		String defaultPassword = user.getAdminYn() == "Y" ?  adminPassword : userPassword;
		String encPassword = SecureUtil.SHA256(defaultPassword);
		logger.debug("encPassword : {}", encPassword);
		
		// 세션에서 등록자 정보 
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession admin = (PSCANSession)httpSession.getAttribute("Session");
		user.setReg_id(admin.getUserId());
		user.setPassword(encPassword);
		
		// 아이디 중복 체크
		User duplication = new User();
		duplication.setUserId(user.getUserId());
		int cnt = userMapper.checkDuplication(duplication);
		if (cnt > 0) {
			result = new Response("DUP", "");
		} else {
			int res = userMapper.createUser(user);
			if (res == 1) {
				result = new Response("OK", user);
			} else {
				result = new Response("Error", "");
			}
		}
		
		return result;
	}

	@Override
	public Response searchUser(Map<String, String> map) {
		Response result; 
		List<User> userList = userMapper.searchUsers(map);
		if (userList != null) {
			result = new Response("OK", userList);
		} else {
			result = new Response("NONE", null);
		}
		return result;
	}

	/* 사용자 수정
	 * @see com.iruen.pscan.appliance.service.ApplianceUserService#updateUser(com.iruen.pscan.vo.User)
	 */
	@Override
	public Response updateUser(User user) {
		Response result; 
		int cnt = userMapper.changeUser(user);
		if (cnt == 1) {
			result = new Response("OK", user);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

	/* 사용자 삭제 : 사용자 삭제는 해당 사용자의 사용여부를 'N'으로 업데이트 한다.
	 * @see com.iruen.pscan.appliance.service.ApplianceUserService#deleteUser(com.iruen.pscan.vo.User)
	 */
	@Override
	@Transactional
	public Response deleteUser(User user) {
		Response result; 
		user.setUseYn("N");						
		int cnt = userMapper.changeUser(user);
		if (cnt == 1) {
			result = new Response("OK", user);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

	/* 초기 패스워드 변경
	 * @see com.iruen.pscan.appliance.service.ApplianceUserService#setPassword(com.iruen.pscan.vo.User)
	 */
	@Override
	public Response setPassword(User chgUser) {
		Response result; 
		
		// 세션에서 등록자 정보 
		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);
		PSCANSession admin = (PSCANSession)httpSession.getAttribute("Session");
		
		int cnt = userMapper.changeUser(chgUser);
		if (cnt == 1) {
			User user = new User();
			user.setIdx(admin.getIdx());
			User newUser = userMapper.getUser(user);

			String defaultFileSavePath = path + File.separator + newUser.getUserId();
			FileList fileList = new FileList();
			fileList.addFile(defaultFileSavePath, true); // 파일리스트 강제생성
			
			httpSession.removeAttribute("Session");
			httpSession.removeAttribute("PSCAN_FILELIST");
			
			httpSession.setAttribute("Session", commonService.setUserSession(newUser)); // 사용자 정보
			httpSession.setAttribute("PSCAN_FILELIST", fileList);                         // 디폴트 파일 리스트 저장
			
			result = new Response("OK", chgUser);
		} else {
			result = new Response("Error", "");
		}
		return result;
	}

}
