package com.lijj.common.tool;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.pojo.Admin;
import com.lijj.common.pojo.FrimsInfo;
import com.lijj.common.pojo.GoodsInfo;
import com.lijj.common.supper.FWSessionSupper;

public class ActionTool {

	public ActionTool() {
		// TODO Auto-generated constructor stub
	}
	public static Admin login_check(FWSessionSupper Fwsession,HttpServletRequest request){
		HttpSession session = request.getSession();
		String userName= (String) session.getAttribute("userName");
		String passWord= (String) session.getAttribute("PassWord");
		return AdminTool.login(Fwsession,userName, passWord);
	}
	
	public static GoodsInfo goods_one (FWSessionSupper Fwsession,String tap,String id) {
		GoodsInfo goodsInfo=null;
		ArrayList<String> list = new ArrayList<String>();
		list.add(id);
		GoodsInfoMapper mappe = Fwsession.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe();
		
		List<GoodsInfo> find = mappe.find(tap, list);
		if(find.size()!=0){
			goodsInfo=find.get(0);
		}
		return goodsInfo;
	}
	public static FrimsInfo frim_one (FWSessionSupper Fwsession,String tap,String id) {
		FrimsInfo frimsInfo=null;
		ArrayList<String> list = new ArrayList<String>();
		list.add(id);
		FrimsInfoMapper mappe = Fwsession.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe();
		
		List<FrimsInfo> find = mappe.find(tap, list);
		if(find.size()!=0){
			frimsInfo=find.get(0);
		}
		return frimsInfo;
	}

}
