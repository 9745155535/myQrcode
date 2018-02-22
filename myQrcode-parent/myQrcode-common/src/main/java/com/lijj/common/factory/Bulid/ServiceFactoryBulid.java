package com.lijj.common.factory.Bulid;

import com.lijj.common.factory.ServiceFactory;

public abstract class ServiceFactoryBulid {
	private ServiceFactory<?> ServiceFactory;
	public abstract <T> ServiceFactory<T> Build(Class<T> Type);
	public void setServiceFactory(ServiceFactory<?> mapperServiceFactory) {
		this.ServiceFactory = mapperServiceFactory;
	}
	public ServiceFactory<?> getServiceFactory() {
		return ServiceFactory;
	}
	
}
