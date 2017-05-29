/*
 * Created on 2005-7-7 Author Administrator
 *  
 */

package com.framework.utils;


import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import java.util.ResourceBundle;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


/**
 * @author feiluo
 * 
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FTPUtil
{
    //init parameter
    private static String strFTPUrl = "";
    private static String strFTPUser = "";
    private static String strFTPPassword = "";
    
    /**
     * 获取资源文件
     * 
     * @author feiluo
     * @param strPorpertyName
     * @return
     * @throws Exception
     */
    public static ResourceBundle getResourceBundle(String strPorpertyName) throws Exception
    {
        //解析properties文件
        String strPorpertyUrl = strPorpertyName;
        ResourceBundle rb = ResourceBundle.getBundle(strPorpertyUrl);
        return rb;
    }
    /**
     * 初始化FTP，包括初始化属性文件，取得连接
     * 
     * @author feiluo
     * @param strPorpertyName 资源文件的地址
     * @return
     * @throws Exception
     */
    public static FTPClient initFTP(String strPorpertyName) throws Exception
    {
        //FTPClient ftp = null;
        //解析properties文件
        String strPorpertyUrl = strPorpertyName;
        ResourceBundle rb = ResourceBundle.getBundle(strPorpertyUrl);
        return initFTP(rb);
    }
   
    public static FTPClient initFTP(String url,String  user,String password) throws Exception
    {
        FTPClient ftp = null;
        //默认从根下开始寻找
       
        strFTPUrl = StringUtil.formatString(url);
        strFTPUser = StringUtil.formatString(user);
        strFTPPassword = StringUtil.formatString(password);
        try
        {
            ftp = new FTPClient();
            //连接
            System.out.println("开始连接....");
            ftp.connect(strFTPUrl);
            //登陆
            ftp.login(strFTPUser, strFTPPassword);
            System.out.println("成功");
        }
        catch (SocketException e)
        {
            throw new Exception("common.ftp.connect.err");
        }
        catch (IOException e)
        {
            throw new Exception("common.ftp.IOException.err");
        }
      
        return ftp;
    }
    
    
    /**
     * 初始化FTP，包括初始化属性文件，取得连接
     * 
     * @author feiluo
     * @param ResourceBundle rb 配置的资源文件
     * @return
     * @throws Exception
     */
    public static FTPClient initFTP(ResourceBundle rb) throws Exception
    {
        FTPClient ftp = null;
        //默认从根下开始寻找
        if (rb != null)
        {
            strFTPUrl = StringUtil.formatString(rb.getString("url"));
            strFTPUser = StringUtil.formatString(rb.getString("user"));
            strFTPPassword = StringUtil.formatString(rb.getString("password"));
            try
            {
                ftp = new FTPClient();
                //连接
                System.out.println("开始连接....");
                ftp.connect(strFTPUrl);
                //登陆
                ftp.login(strFTPUser, strFTPPassword);
                System.out.println("成功");
            }
            catch (SocketException e)
            {
                throw new Exception("common.ftp.connect.err");
            }
            catch (IOException e)
            {
                throw new Exception("common.ftp.IOException.err");
            }
        }
        return ftp;
    }
    
    /**
     * 关闭与FTP的连接
     * 
     * @author feiluo
     * @param ftpClient
     * @throws Exception
     */
    public static void disconnectFTP(FTPClient ftpClient) throws Exception
    {
        try
        {
            if (ftpClient != null && ftpClient.isConnected())
            {
                ftpClient.disconnect();
            }
        }
        catch (IOException e)
        {
            throw new Exception("common.ftp.IOException.err");
        }
    }

    /**
     * 将FTP上的所有文件下载到本地目录,但不处理其下子目录中的文件，并根据传人参数确定是否在下载到本地后删除 对中文名文件暂不支持
     * 
     * @author feiluo
     * @param ftpClient:FTP客户段对象
     * @param FTPFileName：FTP文件名称
     * @param localFilePath：要存放FTP的本地文件路径，请使用"C:\\Temp\\test" 格式
     * @param isDelete: 是否在下载以后删除，true为删除，fasle为保留 注意：下载的目录为ftpClient当前工作目录
     */
    public static void getAllFileFromFTP(FTPClient ftpClient, String localFilePath, boolean isDelete) throws Exception
    {
        try
        {
            String localFilename = "";
            int FilePathLength = localFilePath.length();
            //拼装本地文件的绝对文件名
            if (localFilePath.substring(FilePathLength - 1, FilePathLength).equals("\\")
                    || localFilePath.substring(FilePathLength - 1, FilePathLength).equals("/"))
            {
                //不处理
            }
            else
            {
                localFilePath = localFilePath + "\\";
            }
            FTPFile[] files = null;
            files = ftpClient.listFiles();
            if (files != null && files.length > 0)
            {
                for (int i = 0; i < files.length; i++)
                {
                    //只用当为文件的时候，才开始读写，^_^
                    if (files[i].isFile())
                    {
                        String FTPFileName = StringUtil.formatString(files[i].getName());
                        System.out.println("FTPFileName: " + FTPFileName);
                        localFilename = localFilePath + FTPFileName;
                        OutputStream ops = new FileOutputStream(localFilename);
                        ftpClient.retrieveFile(FTPFileName, ops);
                        //但删除标志为真时，删除FTP上的文件
                        if (isDelete)
                        {
                            System.out.println("删除文件----" + ftpClient.deleteFile(FTPFileName));
                        }
                        ops.close();
                        ops = null;
                        System.out.println("download is successful");
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new Exception("common.ftp.IOException.err");
        }
    }

    /**
     * 将FTP上的所有文件下载到本地目录,但不处理其下子目录中的文件，并根据传人参数确定是否在下载到本地后删除 对中文名文件暂不支持
     * 
     * @author feiluo
     * @param ftpClient:FTP客户段对象
     * @param FTPFileName：FTP文件名称
     * @param localFilePath：要存放FTP的本地文件路径，请使用"C:\\Temp\\test" 格式
     * @param isDelete: 是否在下载以后删除，true为删除，fasle为保留
     * @param strPorpertyName 要变更到的工作目录-->无用参数
     *  
     */
    public static void getAllFileFromFTP(
        FTPClient ftpClient,
        String strRepdir,
        String localFilePath,
        boolean isDelete,
        String strPorpertyName) throws Exception
    {
        if(strRepdir != null && strRepdir.trim().length() >0)
        {
            ftpClient.changeWorkingDirectory(strRepdir);
        }
        getAllFileFromFTP(ftpClient, localFilePath, isDelete);
    }

    /**
     * 将FTP上的指定文件下载到本地目录,并根据传人参数确定是否在下载到本地后删除 对中文名文件暂不支持
     * 
     * @author feiluo
     * @param ftpClient
     * @param FTPFileName
     * @param localFilePath
     * @param isDelete
     * @throws Exception
     */
    public static void getFileByNameFromFTP1(
        FTPClient ftpClient,
        String FTPFilePath,
        String localFilePath,
        boolean isDelete) throws Exception
    {
        try
        {
            String localFilename = "";
            int FilePathLength = localFilePath.length();
            //拼装本地文件的绝对文件名
            if (localFilePath.substring(FilePathLength - 1, FilePathLength).equals("\\")
                    || localFilePath.substring(FilePathLength - 1, FilePathLength).equals("/"))
            {
                //不处理
            }
            else
            {
                localFilePath = localFilePath + "\\";
            }
            FTPFile[] files = null;
            files = ftpClient.listFiles(FTPFilePath);
            if (files.length > 0)
            {
                for (int i = 0; i < files.length; i++)
                {
                    //只用当为文件的时候，才开始读写，^_^
                    if (files[i].isFile())
                    {
                        String FTPFileName = StringUtil.formatString(files[i].getName());
                        localFilename = localFilePath + FTPFileName;
                        OutputStream ops = new FileOutputStream(localFilename);
                        ftpClient.retrieveFile(FTPFileName, ops);
                        //但删除标志为真时，删除FTP上的文件
                        if (isDelete)
                        {
                            ftpClient.deleteFile(FTPFileName);
                        }
                        System.out.println("download is successful");
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new Exception("common.ftp.IOException.err");
        }
    }

    /**
     * 将信息上传至服务器
     * 
     * @author feiluo
     * @param ftpClient
     * @param strStorePath
     * @param strStoreFilename
     * @param strFilecontent
     * @return
     * @throws Exception
     */
    public static boolean appendToFTP(
        FTPClient ftpClient,
        String strStorePath,
        String strStoreFilename,
        byte[] strFilecontent) throws Exception
    {
        boolean retFlag = false;
        //记录ftp当前路径
        String tempDir = ftpClient.printWorkingDirectory();
        try
        {
            ftpClient.mkd(strStorePath);
            //转入要上传文件的路径
            ftpClient.changeWorkingDirectory(strStorePath);
            FTPFile[] files = null;
            files = ftpClient.listFiles(strStoreFilename);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //当目录下没有相同文件名的文件时才执行上传操作，^_^
            if (files == null || files.length <= 0)
            {
                InputStream input = new ByteArrayInputStream(strFilecontent);
                //上传
                ftpClient.storeFile(strStoreFilename, input);
                input.close();
                retFlag = true;
            }
        }
        catch (IOException e)
        {
            retFlag = false;
            throw new Exception("common.ftp.IOException.err");
        }
        finally
        {
            //处理完成，返回先前路径
            ftpClient.changeWorkingDirectory(tempDir);
        }
        return retFlag;
    }
    
    public static boolean appendToFTP(
            FTPClient ftpClient,
            String strStorePath,
            String strStoreFilename,
            InputStream input) throws Exception
        {
            boolean retFlag = false;
            //记录ftp当前路径
            String tempDir = ftpClient.printWorkingDirectory();
            try
            {
                ftpClient.mkd(strStorePath);
                //转入要上传文件的路径
                ftpClient.changeWorkingDirectory(strStorePath);
                FTPFile[] files = null;
                files = ftpClient.listFiles(strStoreFilename);
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                //当目录下没有相同文件名的文件时才执行上传操作，^_^
                if (files == null || files.length <= 0)
                {
                    //上传
                    ftpClient.storeFile(strStoreFilename, input);
                    input.close();
                    retFlag = true;
                }
            }
            catch (IOException e)
            {
                retFlag = false;
                throw new Exception("common.ftp.IOException.err");
            }
            finally
            {
                //处理完成，返回先前路径
                ftpClient.changeWorkingDirectory(tempDir);
            }
            return retFlag;
        }
        
    public static void deleteFile(String resource,String filepath)throws Exception
    {
    	FTPClient ftpClient=FTPUtil.initFTP(ResourceBundle.getBundle(resource));
    	FTPFile[] files = null;
        files = ftpClient.listFiles(filepath+"image/");
        for(FTPFile file:files)
        {
        	ftpClient.dele(filepath+"image/"+file.getName());
        }
        ftpClient.dele(filepath+"image/");
        ftpClient.dele(filepath);
    }
    public static void deleteDirectory(String resource,String filepath)throws Exception
    {
    	FTPClient ftpClient=FTPUtil.initFTP(ResourceBundle.getBundle(resource));
    	FTPFile[] files = null;
    	files = ftpClient.listFiles(filepath);
        for(FTPFile file:files)
        {
        	if(file.isDirectory())
        	{
        		deleteDirectory(resource, filepath+file.getName());
        	}
        	else
        	{
        		ftpClient.dele(filepath+file.getName());
        	}
        	
        }
        ftpClient.removeDirectory(filepath);
    }
    //feiluo at 2006-03-03 对路径进行转换
    public static String getFilePath(String filepath)
    {
    	String tempfilepath="";
    	int len=0;
    	if(filepath.length()>0)
    	{
	    	len=filepath.length();
	    	//拼装本地文件的绝对文件名
	        if (filepath.substring(len - 1, len).equals("\\")
	                || filepath.substring(len - 1, len).equals("/"))
	        {
	            
	        	tempfilepath=filepath;
	        }
	        else
	        {
	        	tempfilepath = filepath + "\\";
	        }
    	}
    	else
    	{
    		System.out.println("拼本地路径时出错!");
    	}
    	return tempfilepath;    	
    }
}