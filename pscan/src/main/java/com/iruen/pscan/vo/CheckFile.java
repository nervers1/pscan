/**
 * 
 */
package com.iruen.pscan.vo;

/**
 * @author nerve
 *
 */
public class CheckFile extends CommonVO {
	
	/**
	 * 파일별 개인정보 검출 결과
	 */
	private static final long serialVersionUID = -779965871437126775L;
	private long checkFileId;
	private long fileId;
	private long checkCnt;
	private String txt;
	private String etc;
	
	/**
	 * @return the checkFileId
	 */
	public long getCheckFileId() {
		return checkFileId;
	}
	/**
	 * @param checkFileId the checkFileId to set
	 */
	public void setCheckFileId(long checkFileId) {
		this.checkFileId = checkFileId;
	}
	/**
	 * @return the fileId
	 */
	public long getFileId() {
		return fileId;
	}
	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	/**
	 * @return the checkCnt
	 */
	public long getCheckCnt() {
		return checkCnt;
	}
	/**
	 * @param checkCnt the checkCnt to set
	 */
	public void setCheckCnt(long checkCnt) {
		this.checkCnt = checkCnt;
	}
	/**
	 * @return the txt
	 */
	public String getTxt() {
		return txt;
	}
	/**
	 * @param txt the txt to set
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}
	/**
	 * @param etc the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
