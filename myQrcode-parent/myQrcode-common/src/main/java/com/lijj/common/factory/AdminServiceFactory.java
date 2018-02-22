package com.lijj.common.factory;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.factory.ServiceFactory;
import com.lijj.common.service.*;

@Repository("AdminServiceFactory")
public class AdminServiceFactory<T> extends ServiceFactory<T> {

	@SuppressWarnings("unchecked")
	@Override
	public service<T> serviceBuild() {
		// TODO Auto-generated method stub
		return (service<T>) this.getService();
	}
	@Resource(name="AdminService")
	public void setAdminService(service<T> adminService){
		this.setService(adminService);
	}
}
