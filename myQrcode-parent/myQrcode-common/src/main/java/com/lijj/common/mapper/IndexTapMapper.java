package com.lijj.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.IndexTap;



public interface IndexTapMapper {
	/**
	 * 创建table Admin
	 */
	public void create();
	/**
	 * 查询一个数据
	 * @param userName
	 * @return
	 */
	public IndexTap find(@Param("name")String index);
	/**
	 * 通过Admin创建一个条目
	 * @param date
	 */
	public void insert(@Param("date")IndexTap indexTap);
	
	public void upDate(@Param("date")IndexTap indexTap);
	
	public void del(@Param("list")List<String> indexItme);
}
