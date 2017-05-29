package com.framework.utils;
import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dafei
 *
 */
public class MD5Util 
{
	/**
	 * @throws NoSuchAlgorithmException 
	 *@paramdata
	 *@return
	 */
	 public static final String getMD5Password(String data) throws NoSuchAlgorithmException
	 {
		MessageDigest digest= MessageDigest.getInstance("MD5");		
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	 }
	
	 /**  
	  *@parambytes
	  *@return
	  */
	  public static String encodeHex(byte[] bytes)
	  {
	        StringBuffer buf = new StringBuffer(bytes.length * 2);
	        for (int i = 0; i < bytes.length; i++)
	        {
	            if (((int) bytes[i] & 0xff) < 0x10)
	            {
	               buf.append("0");
	            }
	            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
	        }
	        return buf.toString();
	 }

	/**
	 * @param key
	 * @param source
	 * @return
	 */
	public final static String encrypt(String key, String source){
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			EncryptOutputStream eos = new EncryptOutputStream(key.getBytes(), bos);
//			eos.write (source.getBytes());
			return bos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;		
	}

	/**
	 * @param key
	 * @param source
	 * @return
	 */
	public final static String decrypt(String key, String source){
		try {
//			ByteArrayInputStream bis = new ByteArrayInputStream(source.getBytes());
//			DecryptInputStream dis = new DecryptInputStream(key.getBytes(), bis);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			while ( true ) {
//				int b = dis.read();
//				if(b<0)break;
//				bos.write(b);
//	        }
			return bos.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception
	{
		System.out.print(MD5Util.getMD5Password("123"));
	}
}
