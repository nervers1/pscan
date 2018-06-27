/**
 * 
 */
package com.iruen.pscan.appliance.service;

import java.util.Map;

import com.iruen.pscan.vo.Response;
import com.iruen.pscan.vo.User;

/**
 * @author nerve
 *
 */
public interface ApplianceUserService {

	public Response login(User user);

	public Response logout();	
	
	public Response createUser(User user);
	
	public Response updateUser(User user);
	
	public Response deleteUser(User user);

	public Response searchUser(Map<String, String> map);

	public Response setPassword(User chgUser);

}
