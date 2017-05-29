package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import sun.misc.BASE64Decoder;

public class ConvertUtil 
{
	
	/**
	 * 将图片输入流转换为64位字节码
	 * @param bis
	 * @return
	 * @throws Exception
	 */
	public static byte[] convImg2String(InputStream bis) throws Exception
	{
		BASE64Decoder bd = new BASE64Decoder();
		return bd.decodeBuffer(bis);
	}

	public static byte[] readPublicKey(InputStream in) throws Exception  // 获取公钥文件
	{
		 byte[] buf = new byte[in.available()];
		 in.read(buf);
		 in.close();
		 String tmp = new String(buf,"US-ASCII");
		 BASE64Decoder bd = new BASE64Decoder();
		 buf = bd.decodeBuffer(tmp);
		 return buf;
		 
		// System.out.println(tmp);
		 //return tmp.getBytes();
	}


	public static void main(String[] args) throws Exception
	{
		String img="d:/ip.JPG";
		InputStream is=new FileInputStream(new File(img));
		byte[] aa=readPublicKey(is);
		//String s=new String(aa);
		//System.out.println(s);
		System.out.println((new sun.misc.BASE64Encoder()).encode( aa )); 
		
		
	}
}
