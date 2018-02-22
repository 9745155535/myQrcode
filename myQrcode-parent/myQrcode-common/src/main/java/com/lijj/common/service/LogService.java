package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.LogMapper;
import com.lijj.common.service.service;
@Repository("LogService")
public class LogService<T> implements service<T> {
	private LogMapper logMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return (T) logMapper;
	}
	@Resource
	public void setLogMapper(LogMapper logMapper) {
		this.logMapper = logMapper;
	}
	
	
	
}
