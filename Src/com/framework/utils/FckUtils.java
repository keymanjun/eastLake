package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FckUtils 
{
	public  static String CONFIG="resources.strutsCfgResources";
	private static String ROOT="";
	private static String File_TYPES="jpg,png,bmp,gif";
	private static String File_PREFIX="Attach_";
	public static String writeHTML(List<File> file,List<String> fileNames,String [] src,String html,String path,String contextPath,String requestURL,String imgServerUrl)throws Exception
	{
		System.out.println("222");
		 String buf;
		 if(file!=null)
		 {
			 for(int i=0;i<file.size();i++)
			 {
				 buf=uploadFile(file.get(i), fileNames.get(i), path+ROOT+"image/", File_TYPES);
				 html=html.replace(src[i],"<img alt=\"\" madeby=\"fckeditor\" src=\""+imgServerUrl+buf+"\"/>");
			 }
		 }
		// html=filterImage(contextPath,html,true);
		// html=filterImage(requestURL.substring(0,requestURL.indexOf("/","http://".length()))+contextPath,html,true);
		 return html;
	}
	 private static String uploadFile(File file,String fileName,String dir,String fileTypes) throws Exception
	 {
		String newFileName="";
	 	File destfile=null;
	 	FileOutputStream fileOut =null;
	 	InputStream is = null;  
	 	String fileExt="";
	 	try
       {	
	 		fileExt=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
	 		File fDir=new File(dir);
	 		if(!fDir.exists())
	 		{
	 			fDir.mkdirs();
	 		} 		
	 		//newFileName=sOldFileName.substring(0,sOldFileName.indexOf("."))+"_"+getDateString()+"."+fileExt;		
	 		newFileName=File_PREFIX+getDateString()+(int)(Math.random()*9999)+"."+fileExt;
			destfile = new File(filterPath(dir+newFileName));			
            fileOut = new FileOutputStream(destfile);
            is = new FileInputStream(file);  
            byte[] buffer = new byte[400];
            int length = 0;  
            while ((length = is.read(buffer)) > 0)
            {
            	fileOut.write(buffer, 0, length);
            }
       }
       catch(Exception e)
       {
       	  newFileName="";
       	  if(fileOut!=null) fileOut.close();
          throw e;
       }
       finally
       {
    	   if(fileOut!=null) fileOut.close();
       }
       return newFileName;
	 }
	 public static String getDateString()
	 {
		 String strDateStr="";
		 strDateStr=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 return strDateStr;
	 }
	 private static String filterPath(String sPath)
	 {
		 String sFilePath="";
		 String separator=(String)System.getProperties().get("file.separator");
		 if("\\".equals(separator))
		 {
			 sFilePath=sPath.replaceAll("\\/\\/","\\\\").replaceAll("\\/","\\\\");
		 }
		 else
		 {
			 sFilePath=sPath.replaceAll("\\/\\/","/").replaceAll("\\\\","/");
		 }
		 return sFilePath;
	 }
}
