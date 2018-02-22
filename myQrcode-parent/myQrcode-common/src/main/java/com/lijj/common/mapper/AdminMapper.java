package com.lijj.common.mapper;


import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.Admin;

public interface AdminMapper {
	/**
	 * 创建table Admin
	 */
	public void create();
	/**
	 * 查询一个数据
	 * @param userName
	 * @return
	 */
	int existTable();
	public Admin find(@Param("userName")String Name);
	/**
	 * 通过Admin创建一个条目
	 * @param date
	 */
	public void insert(@Param("date")Admin admin);
	/**
	 * 更新
	 * @param admin
	 */
	public void upDate(@Param("date")Admin admin);
}
