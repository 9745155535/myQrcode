package com.lijj.common.pojo;

import java.io.Serializable;

public class IndexTap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String indexItme;
	private String adminId;
	private String frimsId;
	private String goodsId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getIndexItme() {
		return indexItme;
	}
	public void setIndexItme(String indexItme) {
		this.indexItme = indexItme;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getFrimsId() {
		return frimsId;
	}
	public void setFrimsId(String frimsId) {
		this.frimsId = frimsId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	

}
