package com.lijj.common.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lijj.common.pojo.Goods;


public interface GoodsMapper {
	/**
	 * 创建table
	 */
	public void create(@Param("Tap")String goodsTab);
	/**
	 * 查询一个数据
	 * @param userName
	 * @return
	 */
	
	public Goods find(@Param("Tap")String goodsTab,@Param("qrcode")String qrcode);
	public List<Goods> find_time(@Param("Tap")String goodsTab,@Param("beginTime")Date time1,@Param("endTime")Date time2);
	public long find_time_lenght(@Param("Tap")String goodsTab,@Param("beginTime")Date time1,@Param("endTime")Date time2);
	public long find_time_all(@Param("Tap")String goodsTab,@Param("beginTime")Date time1,@Param("endTime")Date time2);
	public long find_id_lenght(@Param("Tap")String goodsTab,@Param("goodsId")String goodsId);
	public long find_judge_lenght(@Param("Tap")String goodsTab,@Param("judge")int judge,@Param("goodsId")String goodsId);
	
	public long time_id_all(@Param("Tap")String goodsTab,@Param("goodsId") String goodsId,@Param("beginTime")Date time1,@Param("endTime")Date time2);
	public long time_frimId_all(@Param("Tap")String goodsTab,@Param("frimId") String frimId,@Param("beginTime")Date time1,@Param("endTime")Date time2);
	public long time_id_use(@Param("Tap")String goodsTab,@Param("goodsId") String goodsId,@Param("beginTime")Date time1,@Param("endTime")Date time2);
	public void del(@Param("Tap")String Tab,@Param("list")List<String> goodsId);
	public void delQr(@Param("Tap")String Tab,@Param("list")List<String> qrcodes);
	/**
	 * 创建一个条目
	 * @param date
	 */
	public void insert(@Param("Tap")String goodsTab,@Param("date")Goods goods);
	/**
	 * 更新
	 * @param admin
	 */
	public void upDate(@Param("Tap")String goodsTab,@Param("date")Goods goods);
}
