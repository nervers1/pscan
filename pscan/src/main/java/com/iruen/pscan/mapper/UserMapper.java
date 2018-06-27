/**
 * 
 */
package com.iruen.pscan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.iruen.pscan.vo.User;

/**
 * @author nerve
 *
 */
public interface UserMapper {
	
	@Select("select now()")
	public String selectNow();
	
	public List<User> getUsers(User user);
	public User getUser(User user);
	public int createUser(User user);
	public int changeUser(User user);
	public int checkDuplication(User user);
	public List<User> searchUsers(Map<String, String> map);
	
}
