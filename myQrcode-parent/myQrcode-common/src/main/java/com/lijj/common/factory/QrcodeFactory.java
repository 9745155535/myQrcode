package com.lijj.common.factory;

import java.util.List;
import java.util.Map;

public abstract class QrcodeFactory {
	private String http;
	private String storagePath;
	private int plan;
	private int setout;
	private int zipPlan;
	public abstract String build(List<Map<String,String>> date,String imgName,String path) throws Exception;
	public String getHttp() {
		return http;
	}
	public void setHttp(String http) {
		this.http = http;
	}
	public String getStoragePath() {
		return storagePath;
	}
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}
	public int getPlan() {
		return plan;
	}
	public void setPlan(int plan) {
		this.plan = plan;
	}
	public int getSetout() {
		return setout;
	}
	public void setSetout(int setout) {
		this.setout = setout;
	}
	public int getZipPlan() {
		return zipPlan;
	}
	public void setZipPlan(int zipPlan) {
		this.zipPlan = zipPlan;
	}

	
}
