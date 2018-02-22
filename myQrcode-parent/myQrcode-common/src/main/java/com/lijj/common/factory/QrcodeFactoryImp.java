package com.lijj.common.factory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.lijj.common.src.FileToZip;
import com.lijj.common.src.QRCodeUtil;

public class QrcodeFactoryImp extends QrcodeFactory {
	@Override
	public String build(List<Map<String,String>> date,String imgName,String path) throws Exception {
		// TODO Auto-generated method stub
		setPlan(0);
		setZipPlan(0);
		return this.creatMkdirs(date, imgName,path);
	}
	/**
	 * 生成二维码并打包
	 * @param date
	 * @param imgName
	 * @return
	 * @throws Exception 
	 */
	private String creatMkdirs(List<Map<String,String>> date,String imgName,String path) throws Exception{
		String resourcesFilePath=path+this.getStoragePath()+"/";
		
		String mkdirsName=UUID.randomUUID().toString().replaceAll("\\-", "");
		
		String targetPath= resourcesFilePath+mkdirsName+"/";
		String zipFilePath=resourcesFilePath+"Zip"+"/";
		String imgFilePath=resourcesFilePath+"img"+"/";
		
		File file1 = new File(targetPath);
		File file2 = new File(zipFilePath);
		File file3 = new File(imgFilePath);
		if(!file2.exists())file2.mkdirs();
		if(!file3.exists())file3.mkdirs();
		
        if (file1.mkdirs()) {
        	boolean judge=this.QrcodeBuild(date, imgFilePath+imgName,targetPath);
        	if(judge){
        		judge=FileToZip.fileToZip(targetPath, zipFilePath, mkdirsName,this);
        	}
        	if(!judge){  
        		return null;
        	}
        		
        }
        this.deleteDirectory(file1);
		return mkdirsName;
	}
	private boolean QrcodeBuild(List<Map<String,String>> date,String imgFilePath,String targetPath) throws Exception{
		for(Map<String,String> s:date){
			Set<String> emtry=s.keySet();
			String parameter=null;
			for(String keep:emtry){
				if(parameter!=null) {
					parameter=parameter+"&";
					parameter=parameter+keep+"="+s.get(keep);
				}
				else parameter=keep+"="+s.get(keep);
			}
			
				QRCodeUtil.encode(this.getHttp()+"/page-lockscreen.jsp?"+parameter,imgFilePath,targetPath, true);
				setPlan(getPlan()+1);
			
		}
		QRCodeUtil.setLenght(0);
		return true;
		
	}
	
	private boolean deleteDirectory(File dirFile) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符     
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	    	
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	        	
	            flag = deleteFile(files[i]);  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i]);  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) {
	    	return false; 
	    }
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else { 
	        return false;  
	    }  
	}  
	public  boolean deleteFile(File file) {
        Boolean flag = false;
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
