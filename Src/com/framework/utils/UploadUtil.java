package com.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UploadUtil {

	private String sOldFileName;
    private String sDestPath;
    private byte[] bytes=null;
	
    //上传文件前缀
    private final static String File_PREFIX="Attach_";
    /**
     * 
     * @param sOldFileName
     * @param sDestPath
     * @param sRealPath    相对路径，相当于应用程序的部署名称，如workflow
     * @param bytes
     */
    public UploadUtil(String sOldFileName,String sDestPath,byte[] bytes)
    {
    	this.sOldFileName=sOldFileName;
    	this.sDestPath=sDestPath;
    	this.bytes=bytes;
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
    
    /**
     * 文件上传操作
     * @param originFileName
     * @param destFilePath
     * @param dateBytes
     * @return
     * @throws IOException
     */
	 public String uploadFile(String fileTypes) throws Exception
	 {
		String newFileName="";
	 	File destfile=null;
	 	FileOutputStream fileOut =null;
	 	String fileExt="";
	 	try
       {		
	 		/****文件后缀****/
	 		fileExt = getFileExt(sOldFileName);
	 		validFileExt(fileExt.toLowerCase(),fileTypes);
	 		
	 		/** 判断目录是否存在 **/
	 		File fDir=new File(sDestPath);
	 		if(!fDir.exists())
	 		{
	 			fDir.mkdir();
	 		}
	 		
			/***拼装上传文件所存入目标路径及文件名***/	 		
	 		//newFileName=sOldFileName.substring(0,sOldFileName.indexOf("."))+"_"+getDateString()+"."+fileExt;		
	 		newFileName=File_PREFIX+getDateString()+"."+fileExt;
			destfile = new File(filterPath(sDestPath+File.separator+newFileName));			
            fileOut = new FileOutputStream(destfile);
            fileOut.write(bytes);
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
    
    
	 private void validFileExt(String sFileExt,String fileTypes) throws Exception
	 {
		boolean bl=false;
		String[] sFileType=fileTypes.split(",");		
		for(int i=0;i<sFileType.length;i++)
		{
			if(sFileType[i]==null || "".equals(sFileType[i])) continue;
			if(sFileExt.equals(sFileType[i])){
				bl=true;
				break;
			}
		}
		if(bl==false)
			throw new Exception("上传的附件类型不在类型为("+fileTypes+")的范围内。");
	 }
	 
	/**
	 * 返回日期类型字符串如200603020820
	 * @return
	 */
	private static String getDateString()
	{
		String strDateStr="";
		strDateStr=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return strDateStr;
	}
	
	
	 /**
	  * 获得文件后缀
	  * @param fileName
	  * @return
	  */
	 private String getFileExt(String fileName)
	 {
	        String value = new String();
	        int start = 0;
	        int end = 0;
	        if(fileName == null)
	            return null;
	        start = fileName.lastIndexOf(46) + 1;
	        end = fileName.length();
	        value = fileName.substring(start, end);
	        if(fileName.lastIndexOf(46) > 0)
	            return value;
	        else
	            return "";
	   }
}
