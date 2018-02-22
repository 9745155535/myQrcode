package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.service.service;
@Repository("GoodsService")
public class GoodsService<T> implements service<T> {
	private GoodsMapper GoodsMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return (T) GoodsMapper;
	}
	@Resource
	public void setGoodsMapper(GoodsMapper GoodsMapper) {
		this.GoodsMapper = GoodsMapper;
	}
	
	
	
}
