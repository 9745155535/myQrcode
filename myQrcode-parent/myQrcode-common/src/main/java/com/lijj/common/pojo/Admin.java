package com.lijj.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long allLenght;
	private long useLenght;
	private	long itemLenght;
	private String wx;
	private String web;
	private String email;
	private String userName;
	private String passWord;
	private String frimInfo;
	private String goodsInfo;
	private String goods;
	private String imgName;
	private String downLog;
	private Date time;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFrimInfo() {
		return frimInfo;
	}
	public void setFrimInfo(String frimInfo) {
		this.frimInfo = frimInfo;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getDownLog() {
		return downLog;
	}
	public void setDownLog(String downLog) {
		this.downLog = downLog;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public long getAllLenght() {
		return allLenght;
	}
	public void setAllLenght(long allLenght) {
		this.allLenght = allLenght;
	}
	public long getUseLenght() {
		return useLenght;
	}
	public void setUseLenght(long useLenght) {
		this.useLenght = useLenght;
	}
	public long getItemLenght() {
		return itemLenght;
	}
	public void setItemLenght(long itemLenght) {
		this.itemLenght = itemLenght;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	
}
