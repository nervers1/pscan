/**
 * 
 */
package com.iruen.pscan.appliance.service;

import com.iruen.pscan.vo.PSCANSession;
import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.User;

/**
 * @author nerve
 *
 */
public interface ApplianceDashboardService {
	
	public Response dashboardCheckResultLimit(int limit);
	
}
