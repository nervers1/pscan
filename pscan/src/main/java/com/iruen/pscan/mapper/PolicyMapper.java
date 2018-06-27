package com.iruen.pscan.mapper;

import java.util.List;
import java.util.Map;

import com.iruen.pscan.vo.Policy;
import com.iruen.pscan.vo.CheckParam;

import kpic.model.CheckPattern;

public interface PolicyMapper {
	
	public List<Policy> listPolicy(Policy policy);

	public List<CheckPattern> listPattern(CheckParam param);
	
	public int createPolicy(Policy policy);
	public int updatePolicy(Policy policy);
	public List<Policy> searchPolicies(Map<String, String> map);	

}
