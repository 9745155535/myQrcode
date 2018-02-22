package com.lijj.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.FrimsInfo;

public interface FrimsInfoMapper {
	/**
	 * 创建
	 */
	public void create(@Param("Tap")String table);
	/**
	 * 插入条目
	 */
	public void insert(@Param("Tap")String table,@Param("date")FrimsInfo frimInfo);
	/**
	 * 批量查找
	 * @param userNames
	 * @return
	 */
	public void del(@Param("Tap")String frimsInfoTab,@Param("list")List<String> frimsInfoId);
	public List<FrimsInfo> pagingFind(@Param("Tap")String Tab,@Param("count")int count,@Param("fixed")int fixed);
	public long count(@Param("Tap")String Tab);
	public List<FrimsInfo> find(@Param("Tap")String table,@Param("List")List<String> list);
	public List<FrimsInfo> find_key(@Param("Tap")String table,@Param("key")String key);
	public List<FrimsInfo> all(@Param("Tap")String table);
	/**
	 * 通过FrimsInfo的属性frimId更新
	 * @param frims
	 */
	public void upDate(@Param("Tap")String table,@Param("date")FrimsInfo frimInfo);
}
