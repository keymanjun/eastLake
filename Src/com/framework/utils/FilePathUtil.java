package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilePathUtil 
{
	 public static String filterPath(String sPath)
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
	 * 
	* <p>方法名称: uploadFile|描述:上载文件，将文件存于指定路径 </p>
	* @param filePath
	* @param in
	 * @throws IOException 
	 */
	public static void uploadFile(String filePath, InputStream in) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		in.close();
	}
	
	/**
	 * 
	* <p>方法名称: uploadFile|描述:上载文件，将文件存于指定路径 </p>
	* @param filePath
	* @param in
	 * @throws IOException 
	 */
	public static void uploadFile(String filePath, File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len;
		while ((len = fis.read(buf)) > 0) {
			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		fis.close();
	}
	
	/**
	 * 
	* <p>方法名称: createFile|描述: 根据文件路径找到该文件并返回，如果不存在该文件，则创建此文件</p>
	* @param filePath
	* @return
	 * @throws IOException 
	 */
	public static File createFile(String filePath) throws IOException {
		File file = new File(filePath);//保存路径
		if (!file.exists()) {
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				file.getParentFile().mkdirs();   
			}
			file.createNewFile(); 
		}
		return file;
	}
	
	/**
	 * 
	* <p>方法名称: deleteFile|描述: 删除指定路径</p>
	* @param filePath
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if(file.exists()) {
			removeFile(file);//删除文件
		}	
	}
	
	public static void removeFile(String path) {
        removeFile(new File(path));
	}	

	/**
	 * 
	* <p>方法名称: removeFile|描述: 删除文件</p>
	* @param path
	 */
	public static void removeFile(File path) {
	   if (path.isDirectory()) {
	       File[] child = path.listFiles();
	       if (child != null && child.length != 0) {
	           for (int i = 0; i < child.length; i++) {
	               removeFile(child[i]);
	               child[i].delete();
	           }
	       }
	   }
	   path.delete();
	}

}
