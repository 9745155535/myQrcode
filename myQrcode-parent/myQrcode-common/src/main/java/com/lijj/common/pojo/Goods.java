package com.lijj.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String qrcode;
	private String goodsId;
	private Date creadTime;
	private Date useTime;
	private boolean judge;
	private long length;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getQrcode() {
		return qrcode;
	}
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	public Date getCreadTime() {
		return creadTime;
	}
	public void setCreadTime(Date creadTime) {
		this.creadTime = creadTime;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public boolean isJudge() {
		return judge;
	}
	public void setJudge(boolean judge) {
		this.judge = judge;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	
}
