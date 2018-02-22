package com.lijj.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class FrimsInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String frimId; 
	private String frimName;
	private String introduction;
	private String icon;
	private String web;
	private String email;
	private String wx;
	private long number;
	private long allNumber;
	private long useNumber;
	private Date time;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFrimName() {
		return frimName;
	}
	public void setFrimName(String frimName) {
		this.frimName = frimName;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public String getFrimId() {
		return frimId;
	}
	public void setFrimId(String frimId) {
		this.frimId = frimId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
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
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public String getWx() {
		return wx;
	}
	public void setWx(String wx) {
		this.wx = wx;
	}
	
	
}
