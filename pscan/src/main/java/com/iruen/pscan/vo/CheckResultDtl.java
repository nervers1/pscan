/**
 * 
 */
package com.iruen.pscan.vo;

/**
 * @author nerve
 *
 */
public class CheckResultDtl extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7087119756681893058L;
	private long checkIdx;             
	private long checkFileId;          
	private long fileId;               
	private int TYPECD;                
	private String searchWord;         
	private String replaceWord;        
	private int page;                  
	private int position;              
	private int start;                 
	private int end;
	
	/**
	 * @return the checkIdx
	 */
	public long getCheckIdx() {
		return checkIdx;
	}
	/**
	 * @param checkIdx the checkIdx to set
	 */
	public void setCheckIdx(long checkIdx) {
		this.checkIdx = checkIdx;
	}
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
	 * @return the tYPECD
	 */
	public int getTYPECD() {
		return TYPECD;
	}
	/**
	 * @param tYPECD the tYPECD to set
	 */
	public void setTYPECD(int tYPECD) {
		TYPECD = tYPECD;
	}
	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return searchWord;
	}
	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	/**
	 * @return the replaceWord
	 */
	public String getReplaceWord() {
		return replaceWord;
	}
	/**
	 * @param replaceWord the replaceWord to set
	 */
	public void setReplaceWord(String replaceWord) {
		this.replaceWord = replaceWord;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}
	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}    
	
}