package com.lijj.common.factory;

import com.lijj.common.service.service;

public abstract class ServiceFactory<T> {
	private service<?> service;
	public abstract service<T> serviceBuild();
	public service<?> getService() {
		return service;
	}
	public void setService(service<?> service) {
		this.service = service;
	}
	
}
