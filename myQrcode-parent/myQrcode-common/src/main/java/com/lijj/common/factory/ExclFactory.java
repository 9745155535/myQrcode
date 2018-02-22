package com.lijj.common.factory;

import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public abstract class ExclFactory {
	private String storagePath;
	public abstract  HSSFWorkbook build(String head,List<String> one,List<List<String>> date) throws IOException;
	
	public String getStoragePath() {
		return storagePath;
	}
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}
	
}
