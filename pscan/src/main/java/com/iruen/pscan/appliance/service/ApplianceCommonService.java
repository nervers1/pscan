/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.iruen.pscan.vo.CheckFileResultInfo;
import com.iruen.pscan.vo.CheckParam;
import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Policy;
import com.iruen.pscan.vo.UploadFile;
import com.iruen.pscan.vo.User;

import kpic.model.CheckPattern;
import kpic.model.FileList;

/**
 * @author nerve
 *
 */
public interface ApplianceCommonService {
	
	public PSCANSession setUserSession(User user);
	
	public int createUploadFile(UploadFile upload);
	
	public UploadFile getFile(UploadFile upload);

	public List<UploadFile> listFiles(UploadFile upload);
	
	public List<Policy> listPolicy(Policy policy);
	
	public List<CheckPattern> listPattern(CheckParam param);

	public FileList getFileList(CheckParam param);

	public List<CheckFileResultInfo> documentChecker(HttpSession session, CheckParam param) throws IOException;
	
}
