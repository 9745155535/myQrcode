package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.IndexTapMapper;
import com.lijj.common.service.service;
@Repository("IndexTapService")
public class IndexTapService<T> implements service<T> {
	private IndexTapMapper indexTapMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return  (T) indexTapMapper;
	}
	@Resource
	public void setIndexTapMapper(IndexTapMapper indexTapMapper) {
		this.indexTapMapper = indexTapMapper;
	}
	public IndexTapMapper getIndexTapMapper() {
		return indexTapMapper;
	}
	
	
	
}
