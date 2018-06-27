/**
 * 
 */
package com.iruen.pscan.vo;

import java.io.Serializable;

/**
 * @author nerve
 *
 */
public class PSCANSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5590395454332086960L;
	
	private long idx;
	private String userId;
	private String name;
	private String password;
	private String email;
	private String adminYn;
	private String deptNm;
	private String deptNo;
	private String positionNm;
	private String userType;
	private String description;
	private User member;
	private String check_yn;


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the adminYn
	 */
	public String getAdminYn() {
		return adminYn;
	}
	/**
	 * @param adminYn the adminYn to set
	 */
	public void setAdminYn(String adminYn) {
		this.adminYn = adminYn;
	}
	/**
	 * @return the deptNm
	 */
	public String getDeptNm() {
		return deptNm;
	}
	/**
	 * @param deptNm the deptNm to set
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	/**
	 * @return the deptNo
	 */
	public String getDeptNo() {
		return deptNo;
	}
	/**
	 * @param deptNo the deptNo to set
	 */
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	/**
	 * @return the positionNm
	 */
	public String getPositionNm() {
		return positionNm;
	}
	/**
	 * @param positionNm the positionNm to set
	 */
	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the member
	 */
	public User getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(User member) {
		this.member = member;
	}
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	/**
	 * @return the check_yn
	 */
	public String getCheck_yn() {
		return check_yn;
	}
	/**
	 * @param check_yn the check_yn to set
	 */
	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}
	

}
