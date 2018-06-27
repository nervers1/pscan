/**
 * 
 */
package com.iruen.pscan.vo;

import java.io.Serializable;

/**
 * @author nerve
 *
 */
public class Policy implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7372583645337439584L;
	
	private int typeCd;
	private String type;
	private String checkYn;
	private String bgItmNm;
	private String smItmNm;
	private String pattern;
	private String patternEx;
	private int maskCnt;
	private String maskDir;
	private String exprYn;
	
	public int getTypeCd() {
		return typeCd;
	}
	public void setTypeCd(int typeCd) {
		this.typeCd = typeCd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getBgItmNm() {
		return bgItmNm;
	}
	public void setBgItmNm(String bgItmNm) {
		this.bgItmNm = bgItmNm;
	}
	public String getSmItmNm() {
		return smItmNm;
	}
	public void setSmItmNm(String smItmNm) {
		this.smItmNm = smItmNm;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getPatternEx() {
		return patternEx;
	}
	public void setPatternEx(String patternEx) {
		this.patternEx = patternEx;
	}
	public int getMaskCnt() {
		return maskCnt;
	}
	public void setMaskCnt(int maskCnt) {
		this.maskCnt = maskCnt;
	}
	public String getMaskDir() {
		return maskDir;
	}
	public void setMaskDir(String maskDir) {
		this.maskDir = maskDir;
	}
	public String getExprYn() {
		return exprYn;
	}
	public void setExprYn(String exprYn) {
		this.exprYn = exprYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
