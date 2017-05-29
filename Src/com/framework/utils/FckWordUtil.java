package com.framework.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.framework.FrameConstant;

public class FckWordUtil 
{
	private static String File_PREFIX="Attach_";
	public  static String CONFIG="resources.strutsCfgResources";
	private static String File_TYPES="jpg,png,bmp,gif";
	private static String HEADER="header";
	private static String FOOTER="footer";
	private static String HEADERNC="headerNoComment";
	private static String FOOTERNC="footerNoComment";
	private static String HEADERINN="headerinner";
	
	private static Map<String,String> staticString=new HashMap<String, String>();//����̶�HTML
	 /**
	  * �ϴ�word��ʽHTML��ͼƬ
	  * @param fileName
	  * @param file
	  * @param filenames
	  * @param dir
	  * @param src
	  * @param html
	  * @return
	  * @throws Exception
	  */
	
	 public static String writeHTML(List<File> file,List<String> fileNames,String fckDir,String [] src,String html,String path,String contextPath,String requestURL,String imgServerUrl)throws Exception
	 {
		 System.out.println("2");
		 String buf;
		 if(file!=null)
		 {
			 for(int i=0;i<file.size();i++)
			 {
				 buf=uploadFile(file.get(i), fileNames.get(i), path+fckDir+"image/", File_TYPES);
				 html=html.replace(src[i],"<img alt=\"\" madeby=\"fckeditor\" src=\""+imgServerUrl+buf+"\"/>");
			 }
		 }
		 html=filterImage(contextPath,html,true,fckDir);
		 html=filterImage(requestURL.substring(0,requestURL.indexOf("/","http://".length()))+contextPath,html,true,fckDir);
		 return html;
		 //write(html,fileName+".html",path+ROOT+URL+fileName+"/");
	 }
	 /**
	  * ��ȡHTML
	  * @param fileName
	  * @throws Exception
	  */
	 public static String readHTML(String html,String contextPath,String URL,String path,String fckDir) throws Exception
	 {
		 //String html=read(path+ROOT+URL+fileName+"/"+fileName+".html");
		 html=filterImage(contextPath,html,false,fckDir);
		 html=filterCode(html);
		 return html;
	 }
	 public static void cancelSendHTML(String fileName,String URL) throws Exception
	 {
		 //FTPUtil.deleteDirectory(CONFIG,ROOT+URL+fileName+"/");
	 }
	 /**
	  * ����HTML�ļ�
	  * @param fileName
	  */
	 public static void sendHTML(String fileName,String URL,String path,Map<String,String> params,boolean hasfh,boolean hasComment) throws Exception
	 {
		 params.put("dhshow",ResourceBundle.getBundle(CONFIG).getString("dhshow"));
		 if(hasfh)params.put("bbsurl",ResourceBundle.getBundle(CONFIG).getString("bbs"));
		 StringBuffer buf=new StringBuffer();
		 if(hasfh)
			 buf.append(writeParam(params,getString(path, hasComment?HEADER:HEADERNC)));
		 else
		 {
			 buf.append(writeParam(params,getString(path,HEADERINN)));
		 }
			 
		/* buf.append(read(filterPath(path+ROOT+URL+fileName+"/"+fileName+".html")));
		 if(hasfh)buf.append(writeParam(params,getString(path, hasComment?FOOTER:FOOTERNC)));*/
		
		 /*FTPUtil.appendToFTP(FTPUtil.initFTP(CONFIG),
				 ROOT+URL+fileName,fileName+".html",buf.toString().getBytes("UTF-8"));*/
		 
		 /*File filedesc=new File(filterPath(path+ROOT+URL+fileName+"/image"));
		 if(filedesc.listFiles()==null)return;
		 for(File file:filedesc.listFiles())
		 {
			 if(file.isFile())
				 sendFTP(file.getName(),ROOT+URL+fileName+"/image",file);
		 }*/
	 }
	 /**
	  * ͼƬ��ַת��
	  * @param root
	  * @param value
	  * @return
	  */
	 public static String filterImage(String contextPath,String value,boolean turn,String fckDir)
	 {
		if(turn)
			return value.replaceAll("<input src=\""+contextPath+fckDir,"<input src=\"../").replaceAll("<img alt=\"\" madeby=\"fckeditor\" src=\""+contextPath+fckDir+"image/","<img alt=\"\" madeby=\"fckeditor\" src=\"image/");
		else
			return value.replaceAll("<input src=\"../","<input src=\""+contextPath+fckDir).replaceAll("<img alt=\"\" madeby=\"fckeditor\" src=\"image/","<img alt=\"\" madeby=\"fckeditor\" src=\""+contextPath+fckDir+"image/");
	 }
	 /**
	  * ת���ַ�
	  * @param value
	  * @return
	  */
	 public static String filterCode(String value)
	 {
		 return value.replaceAll("\\r","").replaceAll("\\n","").replaceAll("'", "\"");
	 }
	 public static String getDateString()
	 {
		 String strDateStr="";
		 strDateStr=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 return strDateStr;
	 }
	 /**
	  * д�ļ�
	  * @param html
	  * @param fileName
	  * @param path
	  * @throws Exception
	  */
	 private static void write(String content,String fileName,String path) throws Exception
	 {
		 OutputStream fileOut=null;
		 File file=null;
		 File fDir=new File(path);
		 if(!fDir.exists())
		 {
			 fDir.mkdirs();
		 }
		 try
		 {
			 file=new File(filterPath(path+fileName));
			 fileOut = new FileOutputStream(file);
			 fileOut.write(content.getBytes());
		 }
		 catch(Exception e)
		 {
			 if(fileOut!=null) fileOut.close();
			 throw e;
		 }
		 finally
		 {
			 if(fileOut!=null) fileOut.close();
		 }
	 }
	 /**
	  * ���ļ�
	  * @param fileName
	  * @param path
	  * @return
	  * @throws Exception
	  */
	 private static String read(String filePath) throws Exception
	 {
		 File file=null;
		 FileReader fileReader=null;
		 StringBuffer buf=new StringBuffer();
		 try
		 {
			 file=new File(filterPath(filePath));
			 if(!file.exists())return "";
			 fileReader = new FileReader(file);    
	         BufferedReader br = new BufferedReader(fileReader);    
	         String str;    
	         while((str = br.readLine() ) != null)    
	         {    
	        	 buf.append(str);    
	         }
             return buf.toString();
		 }
		 catch(Exception e)
		 {
			 throw e;
		 }
		 finally
		 {
			 if(fileReader!=null) fileReader.close();
		 }
	 }
	 /**
	  * �ϴ�ͼƬ
	  * @param file
	  * @param fileName
	  * @param dir
	  * @param fileTypes
	  * @return
	  * @throws Exception
	  */
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
	 		/** �ж�Ŀ¼�Ƿ���� **/
	 		File fDir=new File(dir);
	 		if(!fDir.exists())
	 		{
	 			fDir.mkdirs();
	 		}
			/***ƴװ�ϴ��ļ������Ŀ��·�����ļ���***/	 		
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
	 /**
	  * ���͵�FTP������
	  * @param fileName
	  * @param path
	  * @param file
	  * @throws Exception
	  */
	 private static void sendFTP(String fileName,String path,File file) throws Exception
	 {
		 FileInputStream fileIn=null;
		 fileIn=new FileInputStream(file);
		 int length;
		 ByteArrayOutputStream   bos   =   new   ByteArrayOutputStream(); 
		 while((length=fileIn.read())!=-1)
		 {
			 bos.write(length);
		 }
		 fileIn.close();
		 FTPUtil.appendToFTP(FTPUtil.initFTP(CONFIG),
				 path, file.getName(),bos.toByteArray());
	 }
	 private static String writeParam(Map<String,String> params,String value)
	 {
		 for(String paramName:params.keySet())
		 {
			 value=value.replace("${"+paramName+"}", params.get(paramName));
		 }
		 return value;
	 }
	 private static String getString(String realPath,String key) throws Exception
	 {
		 String value=staticString.get(key);
		 if(value==null)
		 {
			 value=read(realPath+ResourceBundle.getBundle(CONFIG).getString(key));
			 staticString.put(key, value);
		 }
		 return value;
	 }
	 /**
	  * ���˷ָ�� 
	  * @param sPath
	  * @return
	  */
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
	 public static void main(String[] args)
	 {
		 System.out.println("${s}12${s}".replace("${s}", "����"));
	 }
}
