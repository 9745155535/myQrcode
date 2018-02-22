package com.lijj.common.factory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Repository;

@SuppressWarnings("deprecation")
@Repository("ExclFactory")
public class ExclFactoryImp extends ExclFactory {
	
	@Override
	public HSSFWorkbook build(String head,List<String> one,List<List<String>> date) throws IOException{
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		String exclName=UUID.randomUUID().toString();
		@SuppressWarnings("unused")
		String[] abc={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","S","Y","Z"};
		HSSFWorkbook excl = new HSSFWorkbook();
		HSSFRow row;
		HSSFCell Cell;
		//创建excl 表
		HSSFSheet sheet=excl.createSheet("DataList");
		//标题
		row=sheet.createRow(0); 
		Cell = row.createCell(0);
		Cell.setCellValue(head);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,one.size()));
		HSSFCellStyle Style1 = Style(excl, 1);
		row.setRowStyle(Style1);
		row.setHeightInPoints(38);
		Cell.setCellStyle(Style1);
		
		//添加表头
		addToExcl(sheet, one, 1);
		sheet.getRow(1).getCell(0).setCellValue("序号");
		
		HSSFCellStyle Style2 = Style(excl, 2);
		row = sheet.getRow(1);
		row.setHeightInPoints(26);
		for(int i=0;i<=one.size();i++){
			Cell= row.getCell(i);
			Cell.setCellStyle(Style2);
		}
		//
		
		//添加数据
		List<Integer> judgeList = new ArrayList<Integer>();
		if(date.size()!=0){
			//
			List<String> list=date.get(0);
			for(int i=0;i<list.size();i++){
				if(isNumeric(list.get(i))) {
					judgeList.add(i);
					
				}
			}
			
			for(int i=0;i<date.size();i++){
				List<String> keepDate=date.get(i);
				addToExcl(sheet, keepDate, i+2,judgeList);
			}
		
			HSSFCellStyle Style3 = Style(excl, 3);	
			for(int j=0;j<date.size();j++){
				row = sheet.getRow(j+2);
				row.setHeightInPoints(22);
				for(int i=0;i<=one.size();i++){
					Cell = row.getCell(i);
					Cell.setCellStyle(Style3);
				}
			}
			//下标
			HSSFCellStyle Style4 = Style(excl, 4);
			int h=date.size()+3;
			int w=date.get(0).size()-1;
			HSSFRow downRow = sheet.createRow(++h);
			downRow.setHeightInPoints(18);
			downRow.createCell(w).setCellValue("数据条目");
			downRow.createCell(w+1).setCellValue(date.size());
			downRow.getCell(w).setCellStyle(Style4);
			downRow.getCell(w+1).setCellStyle(Style4);
			for(int i:judgeList){
				HSSFRow jsRow = sheet.createRow(++h);
				jsRow.setHeightInPoints(18);
				HSSFCell jCell;
	
				jCell = jsRow.createCell(w);
				jCell.setCellStyle(Style4);
				jCell.setCellValue(one.get(i)+"总量");
				jCell = jsRow.createCell(w+1);
				jCell.setCellStyle(Style4);
				
				long keep=0;
				for(int j=2;j<(date.size()+2);j++){
					keep=keep+(long)sheet.getRow(j).getCell(i+1).getNumericCellValue();
				}
				jCell.setCellValue(keep);
			}
		}
		
		//宽度自适应
		for(int i=0;i<=one.size();i++){
			sheet.autoSizeColumn(i);			
		}
		/**
		 * 测试
		 */
		
		
		return excl;
	}
	
	private void addToExcl(HSSFSheet sheet,List<String> li,int line){
		HSSFRow row=sheet.createRow(line);     
		row.createCell(0).setCellValue(line-1);
	    for(int i=0;i<li.size();i++){
	   	  	row.createCell(i+1).setCellValue(li.get(i)); 
	    }
	}
	private void addToExcl(HSSFSheet sheet,List<String> li,int line,List<Integer> list){
		HSSFRow row=sheet.createRow(line);     
		row.createCell(0).setCellValue(line-1);
	    for(int i=0;i<li.size();i++){
	    	String string = li.get(i);
	    	if(list.size()!=0)
	    	{	
	    		if(isInt(i,list))
	    			row.createCell(i+1).setCellValue(Integer.valueOf(string)); 
	    		else
	    			row.createCell(i+1).setCellValue(string); 
	    	}
	    	else
	   	  	row.createCell(i+1).setCellValue(string); 
	    }
	}
	
	private HSSFCellStyle Style(HSSFWorkbook wb,int type){
		HSSFCellStyle Style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		if(type==1){
			font.setFontName("仿宋_GB2312");    
			font.setFontHeightInPoints((short) 18);//设置字体大小
			font.setColor(HSSFColor.GREEN.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.GREY_80_PERCENT.index);
			Style.setFont(font);
			Style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		else if(type==2){
			Style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
			Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
			Style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
			Style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			font.setFontName("黑体");    
			font.setFontHeightInPoints((short) 20);//设置字体大小
			font.setColor(HSSFColor.GREEN.index);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setColor(HSSFColor.GREY_80_PERCENT.index);
			Style.setFont(font);
			Style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}
		else if(type==3){
			Style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框    
			Style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框    
			Style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框    
			Style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
			font.setFontName("黑体");    
			font.setFontHeightInPoints((short) 16);//设置字体大小
			font.setColor(HSSFColor.BLACK.index);
			Style.setFont(font);
			Style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		}else if(type==4){
			font.setFontName("黑体");    
			font.setFontHeightInPoints((short) 14);//设置字体大小
			font.setColor(HSSFColor.BLACK.index);
			Style.setFont(font);
			
		}
		
		
		
		return Style;
		
	}
	
	private  boolean isNumeric(String str){
		if(str.length()>8) return false;
		 for (int i = 0; i < str.length(); i++){
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
	}
	private  boolean isInt(int a,List<Integer> list){
		boolean judge=false;
		for(int i:list){
			if(a==i) {
				judge=true;
				return judge;
			}
		}
		return judge;
	}
	
	

}
