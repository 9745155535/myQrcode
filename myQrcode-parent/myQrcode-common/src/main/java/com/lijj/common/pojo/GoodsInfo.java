package com.lijj.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class GoodsInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String goodsId;
	private String frimId;
	private String indexItme;
	private String goodsName;
	private String introduction;
	private long allNumber;
	private long useNumber;
	private Date time;
	private String icon;
	public long getAllNumber() {
		return allNumber;
	}
	public void setAllNumber(long allNumber) {
		this.allNumber = allNumber;
	}
	public long getUseNumber() {
		return useNumber;
	}
	public void setUseNumber(long useNumber) {
		this.useNumber = useNumber;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getFrimId() {
		return frimId;
	}
	public void setFrimId(String frimId) {
		this.frimId = frimId;
	}
	public String getIndexItme() {
		return indexItme;
	}
	public void setIndexItme(String indexItme) {
		this.indexItme = indexItme;
	}
	
	
	
	
}
