package com.lijj.Backstage.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lijj.common.factory.ServiceFactory;
import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.mapper.DownLogMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.pojo.Admin;
import com.lijj.common.pojo.DownLog;
import com.lijj.common.pojo.GoodsInfo;
import com.lijj.common.service.service;
import com.lijj.common.supper.FWSessionSupper;
import com.lijj.common.tool.ActionTool;
import com.lijj.common.tool.AdminTool;
@Controller
@RequestMapping(value="/mytest")
public class LoginAction extends FWSessionSupper{
	@RequestMapping(value="/test.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String, Object> test(HttpServletRequest request,HttpServletResponse response) throws IOException{
		boolean judge=false;
		Map<String, Object> map = new HashMap<String, Object>();
		String username=request.getParameter("userName");
		String password=request.getParameter("passWord");
		Admin login = AdminTool.login(this, username, password);
		if(login!=null){
			HttpSession session=request.getSession();
			session.setAttribute("userName", username);
			session.setAttribute("PassWord", password);
			judge=true;
		}
		map.put("judge", judge);
	 	return map;
		
	}
	@RequestMapping(value="/home.action",method=RequestMethod.GET)
	public  ModelAndView home(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("index");
	 	return modelAndView;
		
	}
	@RequestMapping(value="/sign.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String, Object> sign(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		boolean judge=false;
		Map<String, Object> map = new HashMap<String, Object>();
		String userName=request.getParameter("userName");
		Admin find = this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().find(userName);
		if(find==null){
			String passWord=request.getParameter("passWord");
			String email=request.getParameter("email");
			String wx=request.getParameter("wx");
			String web=request.getParameter("web");
			Admin createAdmin = AdminTool.createAdmin(this, userName, passWord,email,wx,web);
			if(createAdmin!=null){
				HttpSession session=request.getSession();
				session.setAttribute("userName", userName);
				session.setAttribute("passWord", passWord);
				judge=true;
			}
		}
		map.put("judge", judge);
	 	return map;
		
	}
	@RequestMapping(value="/updata_basicty.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> updata_basicty(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HashMap<String, Object> map = new HashMap<String,Object>();
		service<GoodsMapper> goodsService = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild();
		Admin login = ActionTool.login_check(this, request);	
		long allLenght = login.getAllLenght();
		long useLenght = login.getUseLenght();
		long itemLenght = login.getItemLenght();
		
		long CU1,CU2,UU1,UU2;
		//时间计算
				Calendar beginTime;
				Calendar endTime;
				//初始化时间
				beginTime = Calendar.getInstance();
				endTime =Calendar.getInstance();
				endTime.setTime(new Date());
				beginTime.setTime(endTime.getTime());
				beginTime.set(Calendar.DATE, -1);
				beginTime.set(Calendar.HOUR_OF_DAY,0);
				beginTime.set(Calendar.MINUTE,0);
				beginTime.set(Calendar.SECOND,0);
				CU1=goodsService.getMappe().find_time_lenght(login.getGoods(), beginTime.getTime(), endTime.getTime());
				UU1=goodsService.getMappe().find_time_all(login.getGoods(), beginTime.getTime(), endTime.getTime());
				endTime.setTime(beginTime.getTime());
				beginTime.set(Calendar.DATE, -1);
				CU2=goodsService.getMappe().find_time_lenght(login.getGoods(), beginTime.getTime(), endTime.getTime());
				UU2=goodsService.getMappe().find_time_all(login.getGoods(), beginTime.getTime(), endTime.getTime());
				
		map.put("CU1", CU1);
		map.put("CU2", CU2);
		map.put("UU1", UU1);
		map.put("UU2", UU2);
		map.put("allLenght", allLenght);
		map.put("useLenght", useLenght );
		map.put("itemLenght",itemLenght);
		
		return map;
	}
	@RequestMapping(value="/updata_map.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> updata_map(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] month={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		Admin login = ActionTool.login_check(this, request);
		
		service<GoodsMapper> goodsService = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild();
		//时间计算
		Calendar beginTime;
		Calendar endTime;
		beginTime = Calendar.getInstance();
		endTime =Calendar.getInstance();
		int cDATE;
		//初始化时间
		endTime.setTime(new Date());
		cDATE=endTime.get(Calendar.MONTH);
		beginTime.setTime(endTime.getTime());
		beginTime.set(Calendar.DATE, -1);
		beginTime.set(Calendar.HOUR_OF_DAY,0);
		beginTime.set(Calendar.MINUTE,0);
		beginTime.set(Calendar.SECOND,0);;
		
		List<Long> dateList = new ArrayList<Long>();
		
		List<String> monthList  = new ArrayList<String>();
		
		long date=1;
		while(login.getTime().getTime()<=endTime.getTime().getTime()){
			date=goodsService.getMappe().find_time_lenght(login.getGoods(), beginTime.getTime(), endTime.getTime());
			dateList.add(new Long(date));
			endTime.setTime(beginTime.getTime());
			beginTime.add(Calendar.MONTH, -1);
		}
		dateList.add(Long.valueOf(0));
		
		for(int i=0;i<dateList.size();i++){
			int keep=cDATE-i;
			if(keep<0) keep+=11;
			monthList.add(month[keep]);
		}
		HashMap<String, Object> map = new HashMap<String,Object>();
		Collections.reverse(monthList);
		Collections.reverse(dateList);
		
		
		map.put("monthList", monthList);
		map.put("dateList", dateList);
		
		return map;
	}
	@RequestMapping(value="/updata_compare.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> updata_compare(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] color={"danger","warning","success"};
		Integer compareValue = Integer.valueOf(request.getParameter("compare_count"));
		HashMap<String, Object> map = new HashMap<String,Object>();
		Admin login = ActionTool.login_check(this, request);
		
		ServiceFactory<GoodsInfoMapper> GoodsInfoserviceBulid = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class);
		
		List<GoodsInfo> FindData = GoodsInfoserviceBulid.serviceBuild().getMappe().sort(login.getGoodsInfo(), compareValue*4, 4);
		
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		for(int i=0;i<FindData.size();i++){
			Map<String, Object> colorMap = new HashMap<String, Object>();
			GoodsInfo goodsInfo = FindData.get(i);
			long find_id_lenght = goodsInfo.getAllNumber();
			long find_judge_lenght =goodsInfo.getUseNumber();
			double compare=((double)find_judge_lenght/find_id_lenght)*100;
			if(compare<20) colorMap.put("color", color[0]);
			else if(compare<50) colorMap.put("color", color[1]);
			else colorMap.put("color", color[2]);
			
			colorMap.put("length", compare);
			dataList.add(colorMap);			
		}
		long count = GoodsInfoserviceBulid.serviceBuild().getMappe().count(login.getGoodsInfo());
		map.put("count", count);
		map.put("FindData",FindData);
		map.put("dataList",dataList);
		
		return map;
	}
	@RequestMapping(value="/updata_downLog.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> updata_downLog(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HashMap<String, Object> map = new HashMap<String,Object>();
		Integer downLogValue = Integer.valueOf(request.getParameter("downLog_count"));
		Admin login = ActionTool.login_check(this, request);	
		
		service<DownLogMapper> DownLogService = this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild();
		long downLog_count=DownLogService.getMappe().count(login.getDownLog());
		List<DownLog> downLogPaging = DownLogService.getMappe().sort(login.getDownLog(),downLogValue*7 , 7);
		
		List<GoodsInfo> find =new ArrayList<GoodsInfo>();
		for(DownLog d:downLogPaging){
			GoodsInfo goodsInfo=ActionTool.goods_one(this,login.getGoodsInfo(),d.getGoodsId());
			find.add(goodsInfo);		
		}
		map.put("goodsInfo", find);
		map.put("downLogPaging", downLogPaging);
		map.put("count", downLog_count);
		return map;
	}
	@RequestMapping(value="table_judge.action",method=RequestMethod.GET)
	public  ModelAndView table_judge(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String judge  = request.getParameter("judge");
	//	HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("judge", judge);
		modelAndView.setViewName("table");
		return modelAndView;
	}
	
	
}
