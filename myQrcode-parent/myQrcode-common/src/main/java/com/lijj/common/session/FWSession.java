package com.lijj.common.session;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.factory.ExclFactory;
import com.lijj.common.factory.QrcodeFactory;
import com.lijj.common.factory.ServiceFactory;
import com.lijj.common.factory.Bulid.ServiceFactoryBulid;
@SuppressWarnings("restriction")
@Repository
public class FWSession {
	private QrcodeFactory qrcodeFactory;
	private ServiceFactoryBulid serviceFactoryBulid;
	private ExclFactory exclFactory;
	
	public <T> ServiceFactory<T> ServiceFactoryBulid(Class<T> Type){
		return serviceFactoryBulid.Build(Type);
	}
	public QrcodeFactory qrcodeFactoryBulid(){
		
		return qrcodeFactory;
	}
	public ExclFactory ExclFactoryBuild(){
		return exclFactory;
	}
	@Resource
	public void setQrcodeFactory(QrcodeFactory qrcodeFactory) {
		this.qrcodeFactory = qrcodeFactory;
	}
	@Resource
	public void setServiceFactoryBulid(ServiceFactoryBulid serviceFactoryBulid) {
		this.serviceFactoryBulid = serviceFactoryBulid;
	}
	@Resource
	public void setExclFactory(ExclFactory exclFactory) {
		this.exclFactory = exclFactory;
	}
	
	
	
}
