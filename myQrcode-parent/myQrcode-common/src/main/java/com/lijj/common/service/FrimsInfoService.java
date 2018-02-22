package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.service.service;
@Repository("FrimsInfoService")
public class FrimsInfoService<T> implements service<T> {
	private FrimsInfoMapper FrimsInfoMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return (T) FrimsInfoMapper;
	}
	@Resource
	public void setFrimsInfoMapper(FrimsInfoMapper FrimsInfoMapper) {
		this.FrimsInfoMapper = FrimsInfoMapper;
	}
	
	
	
}
