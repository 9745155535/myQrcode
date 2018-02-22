package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.service.service;
@Repository("GoodsInfoService")
public class GoodsInfoService<T> implements service<T> {
	private GoodsInfoMapper GoodsInfoMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return (T) GoodsInfoMapper;
	}
	@Resource
	public void setGoodsInfoMapper(GoodsInfoMapper GoodsInfoMapper) {
		this.GoodsInfoMapper = GoodsInfoMapper;
	}
	
	
	
}
