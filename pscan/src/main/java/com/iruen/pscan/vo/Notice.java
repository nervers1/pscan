/**
 * 
 */
package com.iruen.pscan.vo;

import org.apache.ibatis.type.Alias;

/**
 * @author nerve
 * 공지사항
 */
@Alias("notice")
public class Notice extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2730274735566537341L;
	private long notice_no;
	private String title;
	private String content;
	private String use_yn;
	private String searchType;
	private String searchText;

	/**
	 * @return the notice_no
	 */
	public long getNotice_no() {
		return notice_no;
	}
	/**
	 * @param notice_no the notice_no to set
	 */
	public void setNotice_no(long notice_no) {
		this.notice_no = notice_no;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the use_yn
	 */
	public String getUse_yn() {
		return use_yn;
	}
	/**
	 * @param use_yn the use_yn to set
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return the searchText
	 */
	public String getSearchText() {
		return searchText;
	}
	/**
	 * @param searchText the searchText to set
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
