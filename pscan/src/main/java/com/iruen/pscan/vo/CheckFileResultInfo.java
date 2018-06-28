/**
 * 
 */
package com.iruen.pscan.vo;

/**
 * @author nerve
 *
 */
public class CheckFileResultInfo extends CommonVO {

	private static final long serialVersionUID = 5277529306484448813L;
	private long check_file_id;
	private String org_name;
	private String ext;
	private String save_path;
	private String name;
	private String size;
	private long check_cnt;
	private String userId;
	private String userName;
	private String regdt;
	
	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * @return the check_file_id
	 */
	public long getCheck_file_id() {
		return check_file_id;
	}
	/**
	 * @param check_file_id the check_file_id to set
	 */
	public void setCheck_file_id(long check_file_id) {
		this.check_file_id = check_file_id;
	}
	/**
	 * @return the org_name
	 */
	public String getOrg_name() {
		return org_name;
	}
	/**
	 * @param org_name the org_name to set
	 */
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}
	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
	/**
	 * @return the save_path
	 */
	public String getSave_path() {
		return save_path;
	}
	/**
	 * @param save_path the save_path to set
	 */
	public void setSave_path(String save_path) {
		this.save_path = save_path;
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
	 * @return the check_cnt
	 */
	public long getCheck_cnt() {
		return check_cnt;
	}
	/**
	 * @param check_cnt the check_cnt to set
	 */
	public void setCheck_cnt(long check_cnt) {
		this.check_cnt = check_cnt;
	}
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the regdt
	 */
	public String getRegdt() {
		return regdt;
	}
	/**
	 * @param regdt the regdt to set
	 */
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
