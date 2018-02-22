package com.lijj.common.bulid;

import javax.annotation.Resource;

import com.lijj.common.factory.ServiceFactory;

import org.springframework.stereotype.Repository;

import com.lijj.common.factory.Bulid.ServiceFactoryBulid;
import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.mapper.DownLogMapper;
import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.mapper.IndexTapMapper;
import com.lijj.common.mapper.LogMapper;
@Repository("ServiceFactoryBulid")
public class ServiceFactoryBulidImp extends ServiceFactoryBulid {
	
	private ServiceFactory<?> adminServiceFactory;
	private ServiceFactory<?> frimsInfoServiceFactory;
	private ServiceFactory<?> goodsInfoServiceFactory;
	private ServiceFactory<?> goodsServiceFactory;
	private ServiceFactory<?> logServiceFactory;
	private ServiceFactory<?> DownLogServiceFactory;
	private ServiceFactory<?> IndexTapServiceFactory;
	@SuppressWarnings("unchecked")
	@Override
	public <T> ServiceFactory<T> Build(Class<T> type) {
		// TODO Auto-generated method stub
		ServiceFactory<T> date;
		if(type==AdminMapper.class)date=(ServiceFactory<T>) adminServiceFactory;
		else if(type==FrimsInfoMapper.class)date=(ServiceFactory<T>) frimsInfoServiceFactory;
		else if(type==GoodsInfoMapper.class)date=(ServiceFactory<T>) goodsInfoServiceFactory;
		else if(type==GoodsMapper.class)    date=(ServiceFactory<T>) goodsServiceFactory;
		else if(type==LogMapper.class) date=(ServiceFactory<T>) logServiceFactory;
		else if(type==DownLogMapper.class) date=(ServiceFactory<T>) DownLogServiceFactory;
		else if(type==IndexTapMapper.class) date=(ServiceFactory<T>) IndexTapServiceFactory;
		else date=null;
		return date;
	}
	@Resource(name="AdminServiceFactory")
	public void setAdminServiceFactory(ServiceFactory<?> adminServiceFactory) {
		this.adminServiceFactory = adminServiceFactory;
	}
	@Resource(name="FrimsInfoServiceFactory")
	public void setFrimsInfoServiceFactory(ServiceFactory<?> frimsInfoServiceFactory) {
		this.frimsInfoServiceFactory = frimsInfoServiceFactory;
	}
	@Resource(name="GoodsInfoServiceFactory")
	public void setGoodsInfoServiceFactory(ServiceFactory<?> goodsInfoServiceFactory) {
		this.goodsInfoServiceFactory = goodsInfoServiceFactory;
	}
	@Resource(name="GoodsServiceFactory")
	public void setGoodsServiceFactory(ServiceFactory<?> goodsServiceFactory) {
		this.goodsServiceFactory = goodsServiceFactory;
	}
	@Resource(name="LogServiceFactory")
	public void setLogServiceFactory(ServiceFactory<?> logServiceFactory) {
		this.logServiceFactory = logServiceFactory;
	}
	@Resource(name="DownLogServiceFactory")
	public void setDownLogServiceFactory(ServiceFactory<?> downLogServiceFactory) {
		DownLogServiceFactory = downLogServiceFactory;
	}
	@Resource(name="IndexTapServiceFactory")
	public void setIndexTapServiceFactory(ServiceFactory<?> indexTapServiceFactory) {
		IndexTapServiceFactory = indexTapServiceFactory;
	}
	
	
}
