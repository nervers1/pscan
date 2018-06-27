/**
 * 
 */
package com.iruen.pscan.mapper;

import java.util.List;

import com.iruen.pscan.vo.CheckFile;
import com.iruen.pscan.vo.CheckParam;
import com.iruen.pscan.vo.CheckResultDtl;
import com.iruen.pscan.vo.UploadFile;

/**
 * @author nerve
 *
 */
public interface FileMapper {
	
	public List<UploadFile> listFiles(UploadFile file);
	
	public UploadFile getFile(UploadFile file);
	
	public int createFile(UploadFile file);
	
	public int removeFile(int fileId);
	
	public int changeFile(int fileId);
	
	public List<UploadFile> selectFiles(CheckParam param);
	
	public int createCheckFile(CheckFile file);
	
	public int createCheckResult(CheckResultDtl result);
	
	public int updateFileStatus(UploadFile file);
}
