package com.lijj.common.pojo;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String adminName;
	private String even;
	private Date time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getEven() {
		return even;
	}
	public void setEven(String even) {
		this.even = even;
	}
	

}
