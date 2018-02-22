package com.lijj.common.factory;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lijj.common.factory.ServiceFactory;
import com.lijj.common.service.*;

@Repository("IndexTapServiceFactory")
public class IndexTapServiceFactory<T> extends ServiceFactory<T> {

	@SuppressWarnings("unchecked")
	@Override
	public com.lijj.common.service.service<T> serviceBuild() {
		// TODO Auto-generated method stub
		return (com.lijj.common.service.service<T>) this.getService();
	}
	@Resource(name="IndexTapService")
	public void setIndexTapService(service<T> IndexTapService){
		this.setService(IndexTapService);
	}
}
