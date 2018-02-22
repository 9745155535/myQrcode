package com.lijj.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.GoodsInfo;



public interface GoodsInfoMapper {
	/**
	 * 创建table Admin
	 */
	public void create(@Param("Tap")String goodsInfoTab);
	/**
	 * 查询一个数据
	 * @param userName
	 * @return
	 */
	public List<GoodsInfo> find(@Param("Tap")String goodsInfoTab,@Param("list")List<String> goodsId);
	public List<GoodsInfo> sort(@Param("Tap")String goodsInfoTab,@Param("count")int count,@Param("fixed")int fixed);
	public List<GoodsInfo> find_key(@Param("Tap")String goodsInfoTab,@Param("key")String key);
	public List<GoodsInfo> all(@Param("Tap")String goodsInfoTab);
	public List<GoodsInfo> find_id(@Param("Tap")String goodsInfoTab,@Param("list")List<String> frimsId);
	public List<GoodsInfo> pagingFind(@Param("Tap")String goodsInfoTab,@Param("count")int count,@Param("fixed")int fixed);
	public long count(@Param("Tap")String goodsInfoTab);
	/**
	 * 通过Admin创建一个条目
	 * @param date
	 */
	public void insert(@Param("Tap")String goodsInfoTab,@Param("date")GoodsInfo goodsInfo);
	
	public void del(@Param("Tap")String goodsInfoTab,@Param("list")List<String> goodsInfoId);
	
	/**
	 * 更新
	 * @param admin
	 */
	public void upDate(@Param("Tap")String goodsInfoTab,@Param("date")GoodsInfo goodsInfo);
}
