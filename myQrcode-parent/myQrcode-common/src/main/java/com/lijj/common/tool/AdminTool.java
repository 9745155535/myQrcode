package com.lijj.common.tool;


import java.util.Date;
import java.util.UUID;

import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.mapper.DownLogMapper;
import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.pojo.Admin;
import com.lijj.common.service.service;
import com.lijj.common.supper.FWSessionSupper;

public class AdminTool {

	static public Admin login(FWSessionSupper context,String userName,String passWord){
		service<AdminMapper> adminService = context.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild();
		Admin ad = adminService.getMappe().find(userName);
		Admin admin=null;
		if(ad!=null){
			if(ad.getPassWord().equals(passWord)){
				admin=ad;
			}
				
		}
		return admin;
	}
	static public Admin createAdmin(FWSessionSupper context,String userName,String passWord,String email,String wx,String web){
		String frimsInfo = UUID.randomUUID().toString().replaceAll("\\-", "");
		String goodsInfo = UUID.randomUUID().toString().replaceAll("\\-", "");
		String goods = UUID.randomUUID().toString().replaceAll("\\-", "");
		String downLog = UUID.randomUUID().toString().replaceAll("\\-", "");
		Admin admin = new Admin();
		try {
			service<AdminMapper> adminService = context.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild();
			service<FrimsInfoMapper> frimsInfoService = context.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild();
			service<GoodsInfoMapper> goodsInfoService = context.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild();
			service<GoodsMapper> goodsService = context.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild();
			service<DownLogMapper> downLogService = context.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild();
			frimsInfoService.getMappe().create(frimsInfo);
			goodsInfoService.getMappe().create(goodsInfo);
			goodsService.getMappe().create(goods);
			downLogService.getMappe().create(downLog);
			admin.setFrimInfo(frimsInfo);
			
			admin.setGoodsInfo(goodsInfo);
			admin.setGoods(goods);
			admin.setDownLog(downLog);
			admin.setEmail(email);
			admin.setWx(wx);
			admin.setWeb(web);
			admin.setTime(new Date());
			admin.setUserName(userName);
			admin.setPassWord(passWord);
			adminService.getMappe().insert(admin);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return admin;
	}

}
