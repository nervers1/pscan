/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.util.Map;

import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.Policy;

/**
 * @author nerve
 *
 */
public interface AppliancePolicyService {

	public Response createPolicy(Policy policy);
	
	public Response updatePolicy(Policy policy);
	
	public Response searchPolicy(Map<String, String> map);

}
