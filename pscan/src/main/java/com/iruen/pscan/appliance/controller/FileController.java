package com.iruen.pscan.appliance.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iruen.pscan.appliance.service.ApplianceCommonService;
import com.iruen.pscan.util.FileUtil;
import com.iruen.pscan.util.StringUtil;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.UploadFile;

import kpic.model.FileList;

@Controller
@RequestMapping("/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Value("#{db['path.dirs']}")
	private String path;
	
	@Value("#{db['path.replaceDirs']}")
	private String replacedPath;

	@Autowired ApplianceCommonService common;
	
	

	/***************************************************
	 * URL: /file/upload upload(): receives files
	 * 
	 * @param request
	 *            : MultipartHttpServletRequest auto passed
	 * @param response
	 *            : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody FileList upload(MultipartHttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		// 세션정보
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		// 사용자 아이디
		String user_id = pss.getUserId();
		// 사용자 일련번호
		long userIdx = pss.getIdx();
		// 파일 유틸 객체 초기화
		FileUtil fileUtil = new FileUtil();
		// 파일리스트(검출파라미터용 객체)
		FileList list = fileUtil.getFileList(session);
		// DataBase 저장을 위한 객체
		UploadFile upload;
		
		//FileList list = getFileList( session ) ; // 세션에 정의된 파일 리스트 생성
		
		// 파일 업로드 경로 생성
		String defaultFileSavePath = path + File.separator + user_id + File.separator + StringUtil.getCurrentDateString();
		String filePath;
		
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			
			String ext = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
			String encFileName = StringUtil.getRandomString(50);
			upload = new UploadFile();
			upload.setOrgName(mpf.getOriginalFilename().substring(0, mpf.getOriginalFilename().lastIndexOf(".")));
			upload.setSize(mpf.getSize() / 1024);
			upload.setMimeType(mpf.getContentType());
			upload.setExt(ext);
			upload.setSavePath(defaultFileSavePath);
			upload.setName(encFileName);
			upload.setUserIdx(userIdx);
			upload.setReg_id(user_id);
			upload.setStatusCd("100"); 			// 상태코드(100: 업로드)
			
			common.createUploadFile(upload);
			filePath = defaultFileSavePath + File.separator + upload.getName() + "." + upload.getExt();
			
			logger.debug("Name : {}, Path : {}", mpf.getOriginalFilename(), filePath);
			
			
			try {
				upload.setBytes(mpf.getBytes());

				File dirs = new File(defaultFileSavePath);
				if (!dirs.exists()) {
					dirs.mkdirs();
				}
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));

			} catch (IOException e) {
				e.printStackTrace();
			} 
			list.addFile(filePath, false);
			
		}
		fileUtil.setFileList(session, list);
		return list;
	}

	/***************************************************
	 * URL: /rest/controller/get/{value} get(): get file as an attachment
	 * 
	 * @param response
	 *            : passed by the server
	 * @param value
	 *            : value from the URL(파일 아이디를 받는다)
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
	public void get(HttpServletResponse response, HttpSession session, @PathVariable int value) {
		// 파일 메타정보 생성
		UploadFile file = new UploadFile();
		// 세션정보
		PSCANSession pss = (PSCANSession)session.getAttribute("Session");
		// 사용자 일련번호 파라미터 설정
		file.setUserIdx(pss.getIdx());
		// 파일아이디 파라미터 설정
		file.setFileId(value);
		// 파일 메타정보 조회
		UploadFile dest = common.getFile(file);
		// 파일 경로
		String filePath = dest.getSavePath() + File.separator + dest.getName() + "." + dest.getExt();
		File uFile = new File(filePath);
		int fSize = (int)uFile.length();
		
		try {
			if ( fSize > 0) {
				response.setBufferSize(fSize);
				response.setContentType("application/x-msdownload;charset=utf-8");
				// response.setContentType(dest.getMimeType());
				response.setContentLength(fSize);
				response.setHeader("Content-disposition", "attachment; filename=\"" + dest.getOrgName() + "\"");
				
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
				FileCopyUtils.copy(in, response.getOutputStream());
				//FileCopyUtils.copy(dest.getBytes(), response.getOutputStream());
				
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}