/**
 * 
 */
package com.iruen.pscan.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nerve
 *
 */
public class CommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1846320790269061056L;
	
	private String reg_id;
	private Date reg_dt;
	private String mod_id;
	private Date mod_dt;
	/**
	 * @return the reg_id
	 */
	public String getReg_id() {
		return reg_id;
	}
	/**
	 * @param reg_id the reg_id to set
	 */
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	/**
	 * @return the reg_dt
	 */
	public Date getReg_dt() {
		return reg_dt;
	}
	/**
	 * @param reg_dt the reg_dt to set
	 */
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	/**
	 * @return the mod_id
	 */
	public String getMod_id() {
		return mod_id;
	}
	/**
	 * @param mod_id the mod_id to set
	 */
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	/**
	 * @return the mod_dt
	 */
	public Date getMod_dt() {
		return mod_dt;
	}
	/**
	 * @param mod_dt the mod_dt to set
	 */
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
