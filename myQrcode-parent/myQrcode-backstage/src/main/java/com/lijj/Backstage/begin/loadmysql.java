package com.lijj.Backstage.begin;

import javax.annotation.Resource;



import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.mapper.IndexTapMapper;
import com.lijj.common.factory.ServiceFactory;
import com.lijj.common.session.FWSession;

public class loadmysql {
	private FWSession fwSession;
	public loadmysql() {
		// TODO Auto-generated constructor stub
	}
	public FWSession getFwSession() {
		return fwSession;
	}
	@Resource
	public void setFWSession(FWSession fwSession) {
		
		ServiceFactory<AdminMapper> AdminBean = fwSession.ServiceFactoryBulid(AdminMapper.class);
		AdminMapper AdminMapper =AdminBean.serviceBuild().getMappe();
		
		ServiceFactory<IndexTapMapper> IndexTapBean = fwSession.ServiceFactoryBulid(IndexTapMapper.class);
		IndexTapMapper IndexTapMapper = IndexTapBean.serviceBuild().getMappe();
		
		AdminMapper.create();
	    IndexTapMapper.create();
		
		this.fwSession = fwSession;
	}
	

}
