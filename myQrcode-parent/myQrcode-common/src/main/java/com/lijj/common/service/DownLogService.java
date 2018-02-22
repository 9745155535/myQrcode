package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.DownLogMapper;
import com.lijj.common.service.service;

@Repository("DownLogService")
public class DownLogService<T> implements service<T> {
	private DownLogMapper downLogMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return (T) downLogMapper;
	}
	@Resource
	public void setDownLogMapper(DownLogMapper downLogMapper) {
		this.downLogMapper = downLogMapper;
	}
	
	
	
}
