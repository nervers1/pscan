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
import com.iruen.pscan.vo.CheckParam;
import com.iruen.pscan.vo.CheckResultDtl;
import com.iruen.pscan.vo.CheckResultInfo;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Policy;
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
public class ApplianceCommonServiceImpl implements ApplianceCommonService {

	private static final Logger logger = LoggerFactory.getLogger(ApplianceCommonServiceImpl.class);

	@Autowired
	FileMapper fileMapper;

	@Autowired
	PolicyMapper policyMapper;

	@Value("#{db['path.dirs']}")
	private String path;

	@Value("#{db['path.replaceDirs']}")
	private String replacedPath;

	@Value("#{db['path.prefix']}")
	private String prefix;

	@Value("#{db['path.surfix']}")
	private String surfix;
	
	@Value("#{db['path.originalFileDelete']}")
	private String originalFileDelete;
	

	/*
	 * 세션처리
	 * 
	 * @see
	 * com.iruen.pscan.appliance.service.ApplianceCommonService#setUserSession(com.
	 * iruen.pscan.vo.User)
	 */
	@Override
	public PSCANSession setUserSession(User user) {

		PSCANSession newSession = new PSCANSession();
		newSession.setIdx(user.getIdx());
		newSession.setUserId(user.getUserId());
		newSession.setName(user.getName());
		newSession.setPassword(user.getPassword());
		newSession.setEmail(user.getEmail());
		newSession.setAdminYn(user.getAdminYn());
		newSession.setDeptNm(user.getDeptNm());
		newSession.setDeptNo(user.getDeptNo());
		newSession.setPositionNm(user.getPositionNm());
		newSession.setUserType(user.getUserType());
		newSession.setDescription(user.getDescription());
		newSession.setCheck_yn(user.getCheck_yn());
		newSession.setMember(user);
		return newSession;
	}

	@Override
	public int createUploadFile(UploadFile upload) {
		return fileMapper.createFile(upload);
	}

	@Override
	public UploadFile getFile(UploadFile upload) {
		return fileMapper.getFile(upload);
	}

	@Override
	public List<UploadFile> listFiles(UploadFile upload) {
		return fileMapper.listFiles(upload);
	}

	@Override
	public List<Policy> listPolicy(Policy policy) {
		return policyMapper.listPolicy(policy);
	}

	@Override
	public List<CheckPattern> listPattern(CheckParam param) {
		List<CheckPattern> list = policyMapper.listPattern(param);
		return list;
	}

	@Override
	public FileList getFileList(CheckParam param) {
		return null;
	}

	@Override
	@Transactional
	public void documentChecker(HttpSession session, CheckParam param) throws IOException {

		// 세션에서 등록자 정보 
		PSCANSession admin = (PSCANSession)session.getAttribute("Session");
		
		// 원본파일 삭제여부
		boolean deleteYn;
		if ("Y".equals(originalFileDelete) || "true".equals(originalFileDelete)) {
			deleteYn = true;
		} else {
			deleteYn = false;
		}
		
		// 패턴 리스트 조회
		List<CheckPattern> patterns = policyMapper.listPattern(param);
		
		// PatternMatcher 생성(
		PatternMatcher pm = new PatternMatcher();
		pm.setPatternList(patterns);
		
		
		// 파일 리스트 조회
		List<UploadFile> uploadFiles = fileMapper.selectFiles(param); // 파일 정보(결과 리스트 조합에 필요)
		FileList fileList = transform(uploadFiles);

		// replacedDir 파일경로 설정
		String replaceDir = uploadFiles.get(0).getSavePath().replace("files", "replaced");
		
		// replacedDir 파일경로 생성
		File dir = new File(replaceDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		long idx = 0;
		PrivacyCheck checker = new PrivacyCheck(replaceDir, prefix, surfix, deleteYn);
		List<FileResult> checkResult = checker.checkList(fileList, pm);
		for (FileResult result : checkResult) {
			CheckFile check = new CheckFile();
			// 파일 명을 이용한 fileIdx 정보 추출
			for (UploadFile up :uploadFiles) {
				String upFileName = up.getName().concat(".").concat(up.getExt());
				if (upFileName.equals(result.getFile().getName())) {
					idx = up.getFileId();
					check.setFileId(idx);
					logger.debug("FileIDX : {}", idx);
				}
			}
			
			/*
			 * FileResult Insert!!
			 * (파일별 결과 데이터 저장)
			 */
			check.setCheckCnt(result.getCheckCount());			
			check.setTxt(result.getText());			
			check.setEtc(result.getEtc());			
			check.setReg_id(admin.getUserId());
			
			int checkCnt = fileMapper.createCheckFile(check);
			// 파일별 데이터 로그
			logger.debug("인서트 결과 ----------------{}", checkCnt);
			long checkFileId = check.getCheckFileId();
			if (checkCnt > 0) {
				UploadFile fileFlag = new UploadFile();
				fileFlag.setStatusCd("200");  // 파일 상태( 100: 업로드완료,  200: 개인정보검출 )
				fileFlag.setFileId((int) idx);
				int uploadCheckCnt = fileMapper.updateFileStatus(fileFlag);
				logger.debug("파일 상태 업데이트 건수 ----------------{}", uploadCheckCnt);
				
			}
			
			
			// CheckResult
			if (result.getCheckResultList() != null) {

				logger.debug("---------------------CheckResultList------------");
				for (CheckResult c : result.getCheckResultList()) {

					CheckResultDtl dtl = new CheckResultDtl();
					dtl.setCheckFileId(checkFileId);
					dtl.setFileId(idx);
					dtl.setTYPECD(c.getPattern().getTypeCd());
					dtl.setSearchWord(c.getSearchWord());
					dtl.setReplaceWord(c.getReplaceWord());
					dtl.setPage(c.getPage());
					dtl.setPosition(c.getPos());
					dtl.setStart(c.getStart());
					dtl.setEnd(c.getEnd());
					dtl.setReg_id(admin.getUserId());
					
					fileMapper.createCheckResult(dtl);
				}
			}
		}

	}

	private FileList transform(List<UploadFile> uploadFiles) {
		
		FileList fileList = new FileList(); // 개인정보검출을 위한 파일 리스트
		for (UploadFile f : uploadFiles) {
			String filePath = f.getSavePath() + File.separator + f.getName() + "." + f.getExt();
			fileList.addFile(filePath, true);
			logger.debug("{}", filePath);
		}
		return fileList;
	}

}
