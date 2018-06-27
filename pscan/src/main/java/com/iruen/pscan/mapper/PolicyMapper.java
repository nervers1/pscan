package com.iruen.pscan.mapper;

import java.util.List;

import com.iruen.pscan.vo.Policy;
import com.iruen.pscan.vo.CheckParam;

import kpic.model.CheckPattern;

public interface PolicyMapper {
	
	public List<Policy> listPolicy(Policy policy);

	public List<CheckPattern> listPattern(CheckParam param);

}
