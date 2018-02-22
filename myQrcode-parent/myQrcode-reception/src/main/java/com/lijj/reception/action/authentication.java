package com.lijj.reception.action;

import java.io.IOException;
import java.util.Date;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.mapper.IndexTapMapper;
import com.lijj.common.pojo.Admin;
import com.lijj.common.pojo.FrimsInfo;
import com.lijj.common.pojo.Goods;
import com.lijj.common.pojo.GoodsInfo;
import com.lijj.common.pojo.IndexTap;
import com.lijj.common.supper.FWSessionSupper;
import com.lijj.common.tool.ActionTool;
@Controller
@RequestMapping(value="/authentication")
@Transactional(rollbackFor={Exception.class, RuntimeException.class},propagation=Propagation.REQUIRES_NEW)
public class authentication extends FWSessionSupper {

	@SuppressWarnings("unused")
	@RequestMapping(value="/begin.action",method=RequestMethod.GET)
	public  Map<String,Object> begin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		boolean judge=true;
		String indexItme=request.getParameter("item");
		String qrcode=request.getParameter("qr");
		
		//获取indexTap对象
		IndexTap index=this.getFwSission().ServiceFactoryBulid(IndexTapMapper.class).serviceBuild().getMappe().find(indexItme);
		if(index!=null){
		//admin对象
			
			Admin login =this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().find(index.getAdminId());
			
		//goods
			GoodsMapper goodsmapper = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe();
			Goods goods = goodsmapper.find(login.getGoods(), qrcode);
			HttpSession session = request.getSession();
			String aqr = (String) session.getAttribute("qr");
			if(goods!=null&&qrcode.equals(aqr));
			
			else if(goods!=null){
				if(goods.isJudge()) judge=false;
				else{
					
					goods.setUseTime(new Date());
					goods.setJudge(true);
				}
				goods.setLength(goods.getLength()+1);
				//frimsInfo
				FrimsInfo frimsInfo = ActionTool.frim_one(this, login.getFrimInfo(), index.getFrimsId());
				GoodsInfo goodsInfo = ActionTool.goods_one(this,login.getGoodsInfo(), index.getGoodsId());
				if(frimsInfo==null){
					frimsInfo=new FrimsInfo();
					frimsInfo.setEmail(login.getEmail());
					frimsInfo.setWeb(login.getWeb());
					frimsInfo.setWx(login.getWx());
					frimsInfo.setFrimName("未指定");
				}
				
				synchronized(this) {
					// todo 同步代码块
					goodsmapper.upDate(login.getGoods(), goods);
					if(goods.getUseTime()==null){			
						goodsInfo.setUseNumber(goodsInfo.getUseNumber()+1);
						this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().upDate(login.getGoodsInfo(), goodsInfo);
						if(frimsInfo!=null){
							frimsInfo.setUseNumber(frimsInfo.getUseNumber()+1);
							this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), frimsInfo);
						}
							
						login.setUseLenght(login.getUseLenght()+1);
						this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().upDate(login);
						
					}
				}
				
				session.setAttribute("qr", qrcode);
					
			}
		}
		
	 	return hashMap;
		
	}
	
	@RequestMapping(value="/end.action",method=RequestMethod.GET)
	@ResponseBody
	public  ModelAndView end(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		String indexItme=request.getParameter("item");
		String qrcode=request.getParameter("qr");
		boolean judge=true;
		IndexTap index=this.getFwSission().ServiceFactoryBulid(IndexTapMapper.class).serviceBuild().getMappe().find(indexItme);
		//admin对象
		Admin login = this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().find(index.getAdminId());
		//goods
		GoodsMapper mapper = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe();
		Goods goods = mapper.find(login.getGoods(), qrcode);
		if(goods!=null){
			//frimsInfo
			if(goods.getLength()==1) judge=false;
			FrimsInfo frimsInfo = ActionTool.frim_one(this, login.getFrimInfo(), index.getFrimsId());
			GoodsInfo goodsInfo = ActionTool.goods_one(this,login.getGoodsInfo(), index.getGoodsId());
			if(frimsInfo==null){
				frimsInfo=new FrimsInfo();
				frimsInfo.setEmail(login.getEmail());
				frimsInfo.setWeb(login.getWeb());
				frimsInfo.setWx(login.getWx());
				frimsInfo.setFrimName("未指定");
			}
			if(goods.isJudge()){
				modelAndView.addObject("frim", JSONObject.fromObject(frimsInfo).toString());
				modelAndView.addObject("goodsInfo",JSONObject.fromObject(goodsInfo).toString());
				modelAndView.addObject("goods",JSONObject.fromObject(goods).toString());
				modelAndView.addObject("userName", login.getUserName());
				modelAndView.addObject("judge", judge);
				modelAndView.setViewName("use");
				modelAndView.setViewName("use");
			}
			else 
				modelAndView.setViewName("failure");
			
		}else
			modelAndView.setViewName("failure");
		
		
		
		
		return modelAndView;
		
	}
	
}
