package com.lijj.Backstage.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lijj.common.factory.QrcodeFactory;
import com.lijj.common.mapper.AdminMapper;
import com.lijj.common.mapper.DownLogMapper;
import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.mapper.IndexTapMapper;
import com.lijj.common.pojo.Admin;
import com.lijj.common.pojo.DownLog;
import com.lijj.common.pojo.FrimsInfo;
import com.lijj.common.pojo.Goods;
import com.lijj.common.pojo.GoodsInfo;
import com.lijj.common.pojo.IndexTap;
import com.lijj.common.service.service;
import com.lijj.common.supper.FWSessionSupper;
import com.lijj.common.tool.ActionTool;
@Controller
@RequestMapping(value="/GainDate")
@Transactional(rollbackFor= RuntimeException.class,propagation=Propagation.REQUIRES_NEW)
public class GainDate extends FWSessionSupper {
	private DataSourceTransactionManager transactionManager;	
	@RequestMapping(value="/frims.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String, Object> frimsUp( HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer Value = Integer.valueOf(request.getParameter("count"));
		Integer number = Integer.valueOf(request.getParameter("number"));
		String parameter = request.getParameter("judge");
		Map<String, Object> map = new HashMap<String, Object>();
		String[] frims={"#","公司名字","商品种类数","创建时间"};
		String[] goods={"#","商品名字","所属公司","创建时间"};
		long count;
		Admin login = ActionTool.login_check(this, request);
		
		if("frims".equals(parameter)){
			service<FrimsInfoMapper> FrimsInfoService = this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild();
			List<FrimsInfo> pagingFind = FrimsInfoService.getMappe().pagingFind(login.getFrimInfo(), Value*number, number);
			count = FrimsInfoService.getMappe().count(login.getFrimInfo());
			map.put("thead", frims);
			map.put("date", pagingFind);
			
		}else{
			service<GoodsInfoMapper> FrimsInfoService = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild();
			List<GoodsInfo> pagingFind = FrimsInfoService.getMappe().pagingFind(login.getGoodsInfo(), Value*number, number);
			List<String> fr=new ArrayList<String>();
			for(GoodsInfo g:pagingFind){	
				FrimsInfo frim_one = ActionTool.frim_one(this,login.getFrimInfo(),g.getFrimId());
				if(frim_one!=null){
					fr.add(frim_one.getFrimName());
				}else{
					fr.add("没有指定");
				}		
			}
			count = FrimsInfoService.getMappe().count(login.getGoodsInfo());
			map.put("thead", goods);
			map.put("date", pagingFind);
			map.put("frimsdate", fr);
		}
		map.put("count", count);
	 	return map;	
	}
	@RequestMapping(value="/load_frims.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String, Object> load_frims( HttpServletRequest request,HttpServletResponse response) throws IOException{		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Admin login = ActionTool.login_check(this, request);
		
		service<FrimsInfoMapper> FrimsInfoService = this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild();
		List<FrimsInfo> pagingFind = FrimsInfoService.getMappe().pagingFind(login.getFrimInfo(), 0,1000);
		map.put("date", pagingFind);
	 	return map;	
	}
	@RequestMapping(value="/del.action",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> del( HttpServletRequest request,HttpServletResponse response) throws IOException{
		String parameter = request.getParameter("judge");
		String ids = request.getParameter("ids");
		JSONArray js=JSONArray.fromObject(ids);
		
		@SuppressWarnings("unchecked")
		List<String> idsList = (List<String>)JSONArray.toCollection(js,String.class);
		Map<String, Object> map = new HashMap<String, Object>();

		Admin login = ActionTool.login_check(this, request);
		
		if("frims".equals(parameter)){
			service<FrimsInfoMapper> FrimsService = this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild();
			FrimsService.getMappe().del(login.getFrimInfo(),idsList );
		}else{
			service<GoodsInfoMapper> GoodsService = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild();
			List<GoodsInfo> goodsList = GoodsService.getMappe().find(login.getGoodsInfo(),idsList );
			GoodsService.getMappe().del(login.getGoodsInfo(),idsList );
			login.setItemLenght(login.getItemLenght()-idsList.size());
			long keep1=0,keep2=0;
			List<String> goodsIdList = new ArrayList<String>();
			List<String> indexItme = new ArrayList<String>();
			for(GoodsInfo i:goodsList){
				goodsIdList.add(i.getGoodsId());
				indexItme.add(i.getIndexItme());
				keep1=keep1+i.getAllNumber();
				keep2=keep2+i.getUseNumber();
				FrimsInfo one = ActionTool.frim_one(this,login.getFrimInfo(), i.getFrimId());
				if(one!=null){
					one.setNumber(one.getNumber()-1);
					one.setAllNumber(one.getAllNumber()-i.getAllNumber());
					one.setUseNumber(one.getUseNumber()-i.getUseNumber());
					this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), one);
				}
				
			}
			this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild().getMappe().del(login.getDownLog(), goodsIdList);
			this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().del(login.getGoods(), goodsIdList);
			this.getFwSission().ServiceFactoryBulid(IndexTapMapper.class).serviceBuild().getMappe().del( indexItme);
			login.setAllLenght(login.getAllLenght()-keep1);
			login.setUseLenght(login.getUseLenght()-keep2);
			this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().upDate(login);

		}
		return map;
		
	}
	@RequestMapping(value="/upDate.action",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> upDate( HttpServletRequest request,HttpServletResponse response) throws IOException{
		String parameter = request.getParameter("judge");	
		Map<String, Object> map = new HashMap<String, Object>();
		Admin login = ActionTool.login_check(this, request);
		
		if("frims".equals(parameter)){
		
			FrimsInfo bean = ActionTool.frim_one(this,login.getFrimInfo(),request.getParameter("id"));
			
			bean.setEmail(request.getParameter("email"));
			bean.setWeb(request.getParameter("web"));
			bean.setFrimName(request.getParameter("name"));
			bean.setIntroduction(request.getParameter("text"));
			String img=request.getParameter("img");
			String wx=request.getParameter("wx");
			if(wx!=null)
				bean.setWx(wx);
			if(img!=null)
				bean.setIcon(img);
			
			this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), bean);
		}else{
			String frimid;
			GoodsInfo bean = ActionTool.goods_one(this,login.getGoodsInfo(),request.getParameter("id"));
			frimid=bean.getFrimId();
			bean.setGoodsName(request.getParameter("name"));
			bean.setIntroduction(request.getParameter("text"));
			String img=request.getParameter("img");
			bean.setFrimId(request.getParameter("select"));
			if(img!=null)
			bean.setIcon(img);
			
			service<GoodsInfoMapper> GoodsService = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild();
			GoodsService.getMappe().upDate(login.getGoodsInfo(), bean);
			//改变原来frim数据
			if(bean.getFrimId().equals(frimid)){
				
			}else{
				FrimsInfo frim1=ActionTool.frim_one(this,login.getFrimInfo(),frimid);
				if(frim1!=null){
					frim1.setNumber(frim1.getNumber()-1);
					frim1.setAllNumber(frim1.getAllNumber()-bean.getAllNumber());
					frim1.setUseNumber(frim1.getUseNumber()-bean.getUseNumber());
					this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), frim1);
				}
				FrimsInfo frim2 = ActionTool.frim_one(this,login.getFrimInfo(),bean.getFrimId());
				frim2.setNumber(frim2.getNumber()+1);
				frim2.setAllNumber(frim2.getAllNumber()+bean.getAllNumber());
				frim2.setUseNumber(frim2.getUseNumber()+bean.getUseNumber());
				this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), frim2);
				//下载文件
				List<DownLog> downLog = this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild().getMappe().find_goodsId(login.getDownLog(), bean.getGoodsId());
				for(DownLog d:downLog){
					d.setFrimId(bean.getFrimId());
					this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild().getMappe().upDate(login.getDownLog(), d);
				}
				//索引
				IndexTapMapper indexTapMapper = this.getFwSission().ServiceFactoryBulid(IndexTapMapper.class).serviceBuild().getMappe();
				IndexTap indexTap = indexTapMapper.find(bean.getIndexItme());
				indexTap.setFrimsId(bean.getFrimId());
				indexTapMapper.upDate(indexTap);
				
			}
			//
			
		}
		return map;
		
	}
	@RequestMapping(value="/create.action",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> create( HttpServletRequest request,HttpServletResponse response) throws IOException{
		String parameter = request.getParameter("judge");
		
		
		Map<String, Object> map = new HashMap<String, Object>();

		Admin login = ActionTool.login_check(this, request);
		
		
		if("frims".equals(parameter)){
			FrimsInfo bean = new FrimsInfo();
			bean.setFrimId(UUID.randomUUID().toString().replaceAll("\\-", ""));
			bean.setFrimName(request.getParameter("name"));
			bean.setIntroduction(request.getParameter("text"));
			String email=request.getParameter("email");
			String web=request.getParameter("web");
			bean.setTime(new Date());
			String img=request.getParameter("img");
			String wx=request.getParameter("wx");
			System.out.println(wx.length());
			if(wx!="")
				bean.setWx(wx);
			else
				bean.setWx(login.getWx());
			if(web!="")
				bean.setWeb(web);
			else
				bean.setWeb(login.getWeb());
			if(email!="")
				bean.setEmail(email);
			else
				bean.setEmail(login.getEmail());
			if(img!=null)
			bean.setIcon(img);
			
			service<FrimsInfoMapper> FrimsService = this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild();
			FrimsService.getMappe().insert(login.getFrimInfo(), bean);
			
		}else{
			String img=request.getParameter("img");
			String select = request.getParameter("select");
			String uuid=UUID.randomUUID().toString().replaceAll("\\-", "");
			String index=String.valueOf(new Date().getTime()); //查找索引
			
			GoodsInfo bean = new GoodsInfo();
			bean.setGoodsId(uuid);
			bean.setGoodsName(request.getParameter("name"));
			bean.setIntroduction(request.getParameter("text"));
			bean.setTime(new Date());
			bean.setIndexItme(index);
			if(img!=null)
			bean.setIcon(img);
			bean.setFrimId(select);
			service<GoodsInfoMapper> GoodsService = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild();
			GoodsService.getMappe().insert(login.getGoodsInfo(), bean);
			
			login.setItemLenght(login.getItemLenght()+1);
			this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().upDate(login);
			
			FrimsInfo frim_one = ActionTool.frim_one(this,login.getFrimInfo(),select);
			if(frim_one!=null){
				frim_one.setNumber(frim_one.getNumber()+1);
				this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), frim_one);
			}
			IndexTap indexTap = new IndexTap();
			indexTap.setIndexItme(index);
			indexTap.setAdminId(login.getUserName());
			indexTap.setFrimsId(select);
			indexTap.setGoodsId(uuid);
			IndexTapMapper indexTapMapper = this.getFwSission().ServiceFactoryBulid(IndexTapMapper.class).serviceBuild().getMappe();
			indexTapMapper.insert(indexTap);
		}
		return map;
		
	}
	@RequestMapping(value="/skip.action",method=RequestMethod.GET)
	public ModelAndView skip( HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		String parameter = request.getParameter("judge");
		String id = request.getParameter("Id");
		Admin login = ActionTool.login_check(this, request);
		
		
		if("frims".equals(parameter)){
			modelAndView.setViewName("frim");
		}else{
			GoodsInfo goods_one = ActionTool.goods_one(this,login.getGoodsInfo(),id);
			
			modelAndView.addObject("frimId",goods_one.getFrimId());
			modelAndView.setViewName("goods");
		}
		modelAndView.addObject("pojo", id);
		modelAndView.addObject("judge", parameter);
		return modelAndView;
		
	}
	@RequestMapping(value="/upload.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> handleUploadData(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		MultipartHttpServletRequest msr = (MultipartHttpServletRequest)request;
		CommonsMultipartFile file=(CommonsMultipartFile) msr.getFile("file");
		
		if (!file.isEmpty()) {
			   String path =request.getSession().getServletContext().getRealPath("");  //获取本地存储路径
			   path=path+"/resources/img/";
			   String fileName = file.getOriginalFilename();
			   String fileType = fileName.substring(fileName.lastIndexOf("."));
			   fileName=new Date().getTime() + fileType;
			   File file2 = new File(path,new Date().getTime() + fileType); //新建一个文件
			   File file3 = new File(path);
			   if(!file3.exists())file3.mkdirs();
			   try {
				    file.getFileItem().write(file2); //将上传的文件写入新建的文件中
				    map.put("fileName", fileName);
			   } catch (Exception e) {
				    e.printStackTrace();
			   } 
		}
		return map;
	}
	@RequestMapping(value="/eyePlan.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> eyePlan(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		int plan=this.getFwSission().qrcodeFactoryBulid().getPlan();
		map.put("plan",plan);
		return map;
	}
	@RequestMapping(value="/eyeSetout.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> eyeSetout(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		int Setout=this.getFwSission().qrcodeFactoryBulid().getSetout();
		map.put("Setout",Setout);
		return map;
	}
	@RequestMapping(value="/eyeZipPlan.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> eyeZipPlan(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		int ZipPlan=this.getFwSission().qrcodeFactoryBulid().getZipPlan();
		map.put("ZipPlan",ZipPlan);
		return map;
	}
	@RequestMapping(value="/qrcode.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> qrcode(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		//开启事务
		/*
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	      //3.设置事务隔离级别，开启新事务
	      def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	      //4.获得事务状态
	      TransactionStatus status = transactionManager.getTransaction(def);
		*/
		 
		QrcodeFactory qrcodeFactory = this.getFwSission().qrcodeFactoryBulid();
		qrcodeFactory.setPlan(0);
		qrcodeFactory.setSetout(0);
		String path =request.getSession().getServletContext().getRealPath("");
		Map<String, Object> map = new HashMap<String,Object>();
		//商品id
		String goodsId=request.getParameter("id");
		//生成二维码数目
		
		
		int length=Integer.valueOf(request.getParameter("length"));
		//获取用户信息
		String Item;
		Admin login = ActionTool.login_check(this, request);
		//索引
		GoodsInfo goodsInfo =ActionTool.goods_one(this,login.getGoodsInfo(), goodsId);
		
		Item=goodsInfo.getIndexItme();
		//
		ArrayList<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
		HashMap<String, String> hashMap;
		
		GoodsMapper goodsMapper = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe();
		List<String> goodsKeep=new ArrayList<String>();
		String FileName = null;
	
			for(int i=0;i<length;i++){
				qrcodeFactory.setSetout(qrcodeFactory.getSetout()+1);
				String uuid=UUID.randomUUID().toString().replaceAll("\\-", "");
				Goods goods = new Goods();
				goods.setGoodsId(goodsId);
				goods.setCreadTime(new Date());
				goods.setQrcode(uuid);
				goodsMapper.insert(login.getGoods(), goods);
				hashMap = new HashMap<String,String>();
				goodsKeep.add(goods.getQrcode());
				hashMap.put("item",Item );
				hashMap.put("qr", uuid);
				mapList.add(hashMap);
			}
			
			
			FileName=qrcodeFactory.build(mapList, goodsInfo.getIcon(),path);
			goodsInfo.setAllNumber(goodsInfo.getAllNumber()+length);
			this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().upDate(login.getGoodsInfo(), goodsInfo);
			login.setAllLenght(login.getAllLenght()+length);
			this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().upDate(login);
			FrimsInfo frim_one = ActionTool.frim_one(this,login.getFrimInfo(),goodsInfo.getFrimId());
			if(frim_one!=null){
				frim_one.setAllNumber(frim_one.getAllNumber()+length);
				this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().upDate(login.getFrimInfo(), frim_one);
			}
			
			//记录
			DownLog downLog=new DownLog();
			downLog.setFileName(FileName);
			downLog.setLength(length);
			downLog.setTime(new Date());
			downLog.setFrimId(goodsInfo.getFrimId());
			downLog.setGoodsId(goodsId);
		
			
			this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild().getMappe().insert(login.getDownLog(),downLog);
		
		map.put("file", FileName);
		return map;
	}
	@RequestMapping(value="/downlog.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> downlog(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		
		String judge=request.getParameter("judge");
		String id=request.getParameter("id");
		Admin login = ActionTool.login_check(this, request);
		List<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();
		if(judge!=null){
			if("frims".equals(judge)){
				List<DownLog> find_frimId = this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild().getMappe().find_frimId(login.getDownLog(), id);
				for(DownLog d:find_frimId) goodsList.add(ActionTool.goods_one(this,login.getGoodsInfo(),d.getGoodsId()));
				Collections.reverse(find_frimId);
				map.put("date", find_frimId);
				
			}else{
				List<DownLog> find_goodsId = this.getFwSission().ServiceFactoryBulid(DownLogMapper.class).serviceBuild().getMappe().find_goodsId(login.getDownLog(), id);
				for(DownLog d:find_goodsId) goodsList.add(ActionTool.goods_one(this,login.getGoodsInfo(),d.getGoodsId()));
				Collections.reverse(find_goodsId);
				map.put("date", find_goodsId);
			}
			
		}
		map.put("goodsdate", goodsList);
		return map;
	}
	@RequestMapping(value="/down_info.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> down_info(HttpServletRequest request,HttpServletResponse response){
		boolean have=false;
		Map<String, Object> map = new HashMap<String,Object>();
		String key=request.getParameter("key");
		String judge=request.getParameter("judge");
		Admin login = ActionTool.login_check(this, request);
		if("frims".equals(judge)){
			List<FrimsInfo> find_key = this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().find_key(login.getFrimInfo(), key);
			if(find_key.size()!=0) have=true;
			map.put("downDate", find_key);
		}else{
			List<GoodsInfo> find_key = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().find_key(login.getGoodsInfo(), key);
			if(find_key.size()!=0) have=true;
			map.put("downDate", find_key);
		}
		map.put("have", have);
		map.put("judge", judge);
		return map;
	}
	@RequestMapping(value="/goods_frimId.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> goods_frimId(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		String id=request.getParameter("id");
		Admin login = ActionTool.login_check(this, request);
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(id);
		List<GoodsInfo> find_key = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().find_id(login.getGoodsInfo(), arrayList);
			
		map.put("goodsData", find_key);
		return map;
	}
	@RequestMapping(value="/frim_frimId.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> frim_frimId(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		String id=request.getParameter("id");
		Admin login = ActionTool.login_check(this, request);
		FrimsInfo frimsInfo = ActionTool.frim_one(this,login.getFrimInfo(),id);
		if(frimsInfo==null){
			frimsInfo=new FrimsInfo();
			frimsInfo.setEmail(login.getEmail());
			frimsInfo.setWeb(login.getWeb());
			frimsInfo.setWx(login.getWx());
			frimsInfo.setFrimName("未指定");
		}
		map.put("frim", frimsInfo);
		return map;
	}
	@RequestMapping(value="/goods_goodsId.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> goods_goodsId(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String,Object>();
		String id=request.getParameter("id");
		Admin login = ActionTool.login_check(this, request);
		GoodsInfo goods_one = ActionTool.goods_one(this,login.getGoodsInfo(),id);
			
		map.put("goods", goods_one);
		return map;
	}
	@RequestMapping(value="/goods_map.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> goods_map(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String id=request.getParameter("id");
		String[] month={"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		Admin login = ActionTool.login_check(this, request);
		service<GoodsMapper> goodsService = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild();
		//时间计算
		Calendar beginTime;
		Calendar endTime;
		int cDATE;
		//初始化时间
		beginTime = Calendar.getInstance();
		cDATE=beginTime.get(Calendar.MONTH);
		beginTime.set(Calendar.DATE, 1);
		beginTime.set(Calendar.HOUR_OF_DAY,0);
		beginTime.set(Calendar.MINUTE,0);
		beginTime.set(Calendar.SECOND,0);
		
		endTime =Calendar.getInstance();
		endTime.setTime(beginTime.getTime());
		endTime.add(Calendar.MONTH, 2);
		beginTime.add(Calendar.MONTH, 1);
		List<Long> allList = new ArrayList<Long>();
		List<Long> useList = new ArrayList<Long>();
		List<String> monthList  = new ArrayList<String>();
		
		long alldate=1;
		long usedate=0;
		do{
			endTime.add(Calendar.MONTH, -1);
			beginTime.add(Calendar.MONTH, -1);
			alldate=goodsService.getMappe().time_id_all(login.getGoods(),id, beginTime.getTime(), endTime.getTime());
			if(alldate==0)break;
			usedate=goodsService.getMappe().time_id_use(login.getGoods(),id, beginTime.getTime(), endTime.getTime());
			
			allList.add(new Long(alldate));
			useList.add(new Long(usedate));
		}
		while(alldate!=0);
		
		for(int i=0;i<allList.size();i++){
			int keep=cDATE-i;
			if(keep<0) keep+=11;
			monthList.add(month[keep]);
		}
		HashMap<String, Object> map = new HashMap<String,Object>();
		Collections.reverse(monthList);
		Collections.reverse(allList);
		Collections.reverse(useList);
		map.put("useList", useList);
		map.put("monthList", monthList);
		map.put("allList", allList);
		
		return map;
	}
	@RequestMapping(value="/find.action",method=RequestMethod.GET)
	public  ModelAndView home(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("find");
	 	return modelAndView;
		
	}
	@RequestMapping(value="/admin.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> admin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HashMap<String, Object> map = new HashMap<String,Object>();	
		Admin login = ActionTool.login_check(this, request);
		map.put("login", login);
		return map;
	}
	@RequestMapping(value="/upadmin.action",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> upadmin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HashMap<String, Object> map = new HashMap<String,Object>();	
		Admin login = ActionTool.login_check(this, request);
		String passWord =request.getParameter("password");
		if(!passWord.isEmpty()){
		login.setPassWord(passWord);
		}
		login.setEmail(request.getParameter("email"));
		login.setWeb(request.getParameter("web"));
		login.setWx(request.getParameter("wx"));
		this.getFwSission().ServiceFactoryBulid(AdminMapper.class).serviceBuild().getMappe().upDate(login);
		if(!passWord.isEmpty())
		request.getSession().setAttribute("PassWord",passWord);
		return map;
	}
	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}
	@Resource
	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	
}
