/**
 * 
 */
package com.iruen.pscan.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author nerve
 *
 */
public class CheckParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1924162854284950231L;

	private List<Integer> typeCds;
	private List<Integer> fileIds;
	
	public List<Integer> getTypeCds() {
		return typeCds;
	}
	public void setTypeCds(List<Integer> typeCds) {
		this.typeCds = typeCds;
	}
	public List<Integer> getFileIds() {
		return fileIds;
	}
	public void setFileIds(List<Integer> fileIds) {
		this.fileIds = fileIds;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
