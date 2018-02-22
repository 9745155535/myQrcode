package com.lijj.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.DownLog;


public interface DownLogMapper {
	/**
	 * 创建table
	 */
	public void create(@Param("Tap")String table);
	/**
	 * 以adminName查询数据
	 * @param adminName
	 * @return
	 */
	public List<DownLog> find(@Param("Tap")String table,@Param("count")int count,@Param("fixed")int fixed);
	public List<DownLog> find_frimId(@Param("Tap")String table,@Param("frimId")String frimId);
	public List<DownLog> find_goodsId(@Param("Tap")String table,@Param("goodsId")String goodsId);
	/**
	 * 创建一个条目
	 * @param date
	 */
	public List<DownLog> sort(@Param("Tap")String table,@Param("count")int count,@Param("fixed")int fixed);
	public void insert(@Param("Tap")String table,@Param("date")DownLog downLog);
	public void del(@Param("Tap")String Tab,@Param("list")List<String> goodsId);
	public List<DownLog> pagingFind(@Param("Tap")String Tab,@Param("count")int count,@Param("fixed")int fixed);
	
	public long count(@Param("Tap")String Tab);
	public void upDate(@Param("Tap")String table,@Param("date")DownLog downLog);
}
