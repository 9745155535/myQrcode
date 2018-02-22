package com.lijj.Backstage.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lijj.common.mapper.FrimsInfoMapper;
import com.lijj.common.mapper.GoodsInfoMapper;
import com.lijj.common.mapper.GoodsMapper;
import com.lijj.common.pojo.Admin;
import com.lijj.common.pojo.FrimsInfo;
import com.lijj.common.pojo.GoodsInfo;
import com.lijj.common.supper.FWSessionSupper;
import com.lijj.common.tool.ActionTool;
@Controller
@RequestMapping(value="/ExclDownLog")
public class ExclDownLog extends FWSessionSupper {

	@RequestMapping(value="/frimInfo_all.action",method=RequestMethod.GET)
	public  String frimInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Admin login = ActionTool.login_check(this, request);
		String head=dateToString(new Date())+"所有公司统计表";
		List<String> one=new ArrayList<String>();
		one.add("公司名字");
		one.add("商品种类数目");
		one.add("商品数目");
		one.add("使用商品数目");
		one.add("邮箱");
		one.add("微信公众号");
		one.add("创建时间");
		List<FrimsInfo> all = this.getFwSission().ServiceFactoryBulid(FrimsInfoMapper.class).serviceBuild().getMappe().all(login.getFrimInfo());
		List<List<String>> l=new ArrayList<List<String>>();
		for(FrimsInfo f:all){
			List<String> keep=new ArrayList<String>();
			keep.add(f.getFrimName());
			keep.add(String.valueOf(f.getNumber()));
			keep.add(String.valueOf(f.getAllNumber()));
			keep.add(String.valueOf(f.getUseNumber()));
			keep.add(f.getEmail());
			keep.add(f.getWx());
			keep.add(dateToString(f.getTime()));
			l.add(keep);
		}
		HSSFWorkbook wb = this.getFwSission().ExclFactoryBuild().build(head, one, l);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		downLog (response,os);
	 	return null;
		
	}
	@RequestMapping(value="/goodsInfo_all.action",method=RequestMethod.GET)
	public  String goodsInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Admin login = ActionTool.login_check(this, request);
		String head=dateToString(new Date())+"所有商品统计表";
		List<String> one=new ArrayList<String>();
		one.add("商品名字");
		one.add("属于公司");
		one.add("商品数目");
		one.add("使用商品数目");
		one.add("创建时间");
		List<GoodsInfo> all = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().all(login.getGoodsInfo());
		List<String> frimsName=new ArrayList<String>();
		for(GoodsInfo f:all){
			FrimsInfo frim_one = ActionTool.frim_one(this, login.getFrimInfo(),f.getFrimId());
			if(frim_one!=null){
				frimsName.add(frim_one.getFrimName());
			}else{
				frimsName.add("没有指定");
			}
			
		}
		List<List<String>> l=new ArrayList<List<String>>();
		int i=0;
		for(GoodsInfo f:all){
			List<String> keep=new ArrayList<String>();
			keep.add(f.getGoodsName());
			keep.add(frimsName.get(i));
			keep.add(String.valueOf(f.getAllNumber()));
			keep.add(String.valueOf(f.getUseNumber()));
			keep.add(dateToString(f.getTime()));
			l.add(keep);
			i++;
		}
		HSSFWorkbook wb = this.getFwSission().ExclFactoryBuild().build(head, one, l);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		downLog (response,os);
	 	return null;
		
	}
	@RequestMapping(value="/goods_all.action",method=RequestMethod.GET)
	public  String goods_all(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Admin login = ActionTool.login_check(this, request);
		String head=dateToString(new Date())+"商品生成使用统计表";
		List<String> one= new ArrayList<String>();
		one.add("开始时间");
		one.add("结束时间");
		one.add("生成商品数目");
		one.add("使用商品数目");
		
		
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		begin.setTime(end.getTime());
		begin.set(Calendar.DAY_OF_WEEK, 1);
		begin.set(Calendar.HOUR_OF_DAY,0);
		begin.set(Calendar.MINUTE,0);
		begin.set(Calendar.SECOND,0);
		
		List<List<String>> l=new ArrayList<List<String>>();
		//
		
		while(end.getTime().getTime()>=login.getTime().getTime()){
			long all = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().find_time_all(login.getGoods(), begin.getTime(), end.getTime());
			long use = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().find_time_lenght(login.getGoods(), begin.getTime(), end.getTime());
			if(all!=0||use!=0){
				List<String> keep=new ArrayList<String>();
				keep.add(new SimpleDateFormat (" yyyy-MM-dd ").format(begin.getTime()));
				keep.add(new SimpleDateFormat (" yyyy-MM-dd ").format(end.getTime()));
				keep.add(String.valueOf(all));
				keep.add(String.valueOf(use));
				l.add(keep);
				
			}
		
			end.setTime(begin.getTime());
			begin.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
			
		}
	
		HSSFWorkbook wb = this.getFwSission().ExclFactoryBuild().build(head, one, l);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		downLog (response,os);
		
	 	return null;
	  
		
    }
	@RequestMapping(value="/goodsInfo_frimId.action",method=RequestMethod.GET)
	public  String goodsInfo_frimId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String frimid = request.getParameter("frimId");
		Admin login = ActionTool.login_check(this, request);
		FrimsInfo frims=ActionTool.frim_one(this, login.getFrimInfo(), frimid);
		String head=dateToString(new Date())+frims.getFrimName()+"公司商品统计表";
		List<String> one= new ArrayList<String>();
		one.add("商品名字");
		one.add("属于公司");
		one.add("商品数目");
		one.add("使用商品数目");
		one.add("创建时间");
		ArrayList<String> frimsId = new ArrayList<String>();
		frimsId.add(frimid);
		List<GoodsInfo> all = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().find_id(login.getGoodsInfo(), frimsId);
		
		List<List<String>> l=new ArrayList<List<String>>();
		for(GoodsInfo f:all){
			String frimsName=null;
			FrimsInfo frim_one = ActionTool.frim_one(this, login.getFrimInfo(),f.getFrimId());
			if(frim_one!=null){
				frimsName=frim_one.getFrimName();
			}else
				frimsName="没有指定";
			
			List<String> keep=new ArrayList<String>();
			keep.add(f.getGoodsName());
			keep.add(frimsName);
			keep.add(String.valueOf(f.getAllNumber()));
			keep.add(String.valueOf(f.getUseNumber()));
			keep.add(dateToString(f.getTime()));
			l.add(keep);
		}
		HSSFWorkbook wb = this.getFwSission().ExclFactoryBuild().build(head, one, l);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		downLog (response,os);
		
	 	return null;
	  
		
    }
	@RequestMapping(value="/goods.action",method=RequestMethod.GET)
	public  String goods(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String goodsid = request.getParameter("goodsId");
		Admin login = ActionTool.login_check(this, request);
		GoodsInfo goodsInfo=ActionTool.goods_one(this, login.getGoodsInfo(), goodsid);
		String head=dateToString(new Date())+goodsInfo.getGoodsName()+"商品统计表";
		List<String> one= new ArrayList<String>();
		one.add("开始时间");
		one.add("结束时间");
		one.add("生成商品数目");
		one.add("使用商品数目");
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		begin.setTime(end.getTime());
		begin.set(Calendar.DAY_OF_WEEK, 1);
		begin.set(Calendar.HOUR_OF_DAY,0);
		begin.set(Calendar.MINUTE,0);
		begin.set(Calendar.SECOND,0);
		List<List<String>> l=new ArrayList<List<String>>();
		while(end.getTime().getTime()>=login.getTime().getTime()){
			long time_id_all = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().time_id_all(login.getGoods(), goodsid, begin.getTime(), end.getTime());
			long time_id_use =this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().time_id_use(login.getGoods(), goodsid, begin.getTime(), end.getTime());
			if(time_id_all!=0||time_id_use!=0){
				List<String> keep=new ArrayList<String>();
				keep.add(new SimpleDateFormat (" yyyy-MM-dd ").format(begin.getTime()));
				keep.add(new SimpleDateFormat (" yyyy-MM-dd ").format(end.getTime()));
				keep.add(String.valueOf(time_id_all));
				keep.add(String.valueOf(time_id_use));
				l.add(keep);
				
			}
			end.setTime(begin.getTime());
			begin.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
			
			
		}
		
		
		
		
		
		HSSFWorkbook wb = this.getFwSission().ExclFactoryBuild().build(head, one, l);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		downLog (response,os);
		
	 	return null;
	  
		
    }
	@RequestMapping(value="/goods_frimId.action",method=RequestMethod.GET)
	public  String goods_frimId(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String frimId = request.getParameter("frimId");
		Admin login = ActionTool.login_check(this, request);
		FrimsInfo frim_one = ActionTool.frim_one(this, login.getFrimInfo(), frimId);
		String head=dateToString(new Date())+frim_one.getFrimName()+"公司商品统计表";
		List<String> one= new ArrayList<String>();
		one.add("开始时间");
		one.add("结束时间");
		one.add("生成商品数目");
		one.add("使用商品数目");
		
		
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		end.setTime(new Date());
		
		begin.setTime(end.getTime());
		begin.set(Calendar.DAY_OF_WEEK, 1);
		begin.set(Calendar.HOUR_OF_DAY,0);
		begin.set(Calendar.MINUTE,0);
		begin.set(Calendar.SECOND,0);
		List<List<String>> l=new ArrayList<List<String>>();
		//
		List<String> frimsId =new ArrayList<String>();
		frimsId.add(frimId);
		List<GoodsInfo> find_id = this.getFwSission().ServiceFactoryBulid(GoodsInfoMapper.class).serviceBuild().getMappe().find_id(login.getGoodsInfo(), frimsId);
		while(end.getTime().getTime()>=login.getTime().getTime()){
			long all=0,use=0;
			for(GoodsInfo g:find_id){
				long time_id_all = this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().time_id_all(login.getGoods(), g.getGoodsId(), begin.getTime(), end.getTime());
				long time_id_use =this.getFwSission().ServiceFactoryBulid(GoodsMapper.class).serviceBuild().getMappe().time_id_use(login.getGoods(), g.getGoodsId(), begin.getTime(), end.getTime());
				all+=time_id_all;
				use+=time_id_use;
			}
			if(all!=0||use!=0){
				List<String> keep=new ArrayList<String>();
				keep.add(new SimpleDateFormat (" yyyy-MM-dd ").format(begin.getTime()));
				keep.add(new SimpleDateFormat (" yyyy-MM-dd ").format(end.getTime()));
				keep.add(String.valueOf(all));
				keep.add(String.valueOf(use));
				l.add(keep);
				
			}
			all=0;
			use=0;
			end.setTime(begin.getTime());
			begin.add(Calendar.DAY_OF_WEEK_IN_MONTH, -1);
			
			
		}
		
		
		
		
		
		HSSFWorkbook wb = this.getFwSission().ExclFactoryBuild().build(head, one, l);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		downLog (response,os);
		
	 	return null;
	  
		
    }
	
	
	
	private void downLog (HttpServletResponse response,ByteArrayOutputStream os) throws IOException {
		byte[] content = os.toByteArray();
	      InputStream is = new ByteArrayInputStream(content);
	      response.reset();
	      response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((new Date().getTime() + ".xls").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try{
	        bis = new BufferedInputStream(is);
	        bos = new BufferedOutputStream(out);
	        byte[] buff = new byte[2048];
	        int bytesRead;
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	     }catch (final IOException e) {
	            throw e; } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	}
	public static String dateToString(Date time){ 
	    SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
	    String ctime = formatter.format(time); 
	    return ctime; 
	} 
}
