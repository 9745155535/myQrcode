package com.lijj.common.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.Log;



public interface LogMapper {
	/**
	 * 创建table
	 */
	public void create();
	/**
	 * 以adminName查询数据
	 * @param adminName
	 * @return
	 */
	public List<Log> find_admin(@Param("List")List<String> adminName);
	/**
	 * 用时间段查询
	 * @param time1
	 * @param time2
	 * @return
	 */
	public List<Log> find_time(@Param("beginTime")Date time1,@Param("endTime")Date time2);
	/**
	 * 创建一个条目
	 * @param date
	 */
	public void insert(@Param("date")Log log);
	
}
