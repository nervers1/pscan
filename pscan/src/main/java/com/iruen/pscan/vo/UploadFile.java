package com.iruen.pscan.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "bytes" })
public class UploadFile extends CommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 398296286642677490L;

	private int fileId;
	
	private long userIdx;
	
	private String name;
	
	private String orgName;
	
	private String savePath;
	
	private String ext;

	private long size;

	private String mimeType;

	private String statusCd;

	private int detectCnt;

	private byte[] bytes;

	
	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}


	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public int getDetectCnt() {
		return detectCnt;
	}

	public void setDetectCnt(int detectCnt) {
		this.detectCnt = detectCnt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(long userIdx) {
		this.userIdx = userIdx;
	}
	
}
