/**
 * 
 */
package com.iruen.pscan.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;

import com.iruen.pscan.vo.UploadFile;

import kpic.model.FileList;

/**
 * @author nerve
 *
 */
public class FileUtil {

	@Value("#{db['path.dirs']}")
	private String path;
	
	@Value("#{db['path.replaceDirs']}")
	private String replacedPath;

	//private String defaultFileSavePath = path + File.separator + user_id + File.separator + StringUtil.getCurrentDateString();
	
	public FileList getFileList(String filename, HttpSession session){
		if(filename != null) {
			FileList list = getFileList(session);
			FileList part = list.getFileList(filename) ;
			return part;
		}
		else{
			return getFileList(session);
		}
	}
	public FileList getFileList(HttpSession session){
		return getFileList( session, false );
	}

	public FileList getFileList(HttpSession session, boolean force) {
		String tempdir = path + File.separator + session.getAttribute("userId") + File.separator + StringUtil.getCurrentDateString();
		return getFileList(session, tempdir, force );
	}

	public FileList getFileList(HttpSession session, String tempdir , boolean force) {
		FileList list = (FileList) session.getAttribute("PSCAN_FILELIST");
		if( force || list == null ) {
			list = new FileList();
	    	list.addFile(tempdir, true);
	    	setFileList( session, list) ;
		}
		return list;
	}


	public void setFileList(HttpSession session, FileList list) {
		session.setAttribute("PSCAN_FILELIST", list);
	}
	
	public FileList transform(List<UploadFile> files) {
		FileList list = new FileList();
		
		for (UploadFile file : files) {
			String tempPath = file.getSavePath() + File.separator + file.getName() + "." + file.getExt();
			list.addFile(tempPath, false);
		}
		
		return list;
	}
}
