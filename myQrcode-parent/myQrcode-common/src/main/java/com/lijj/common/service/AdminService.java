package com.lijj.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.service.service;
@Repository("AdminService")
public class AdminService<T> implements service<T> {
	private AdminMapper adminMapper;
	@SuppressWarnings("unchecked")
	@Override
	public T getMappe() {
		// TODO Auto-generated method stub
		return (T) adminMapper;
	}
	@Resource
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	
	
	
}
