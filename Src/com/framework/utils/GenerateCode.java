/**
 * 
 */
package com.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author wangjun
 * THis is class is used for generating product code
 *
 */
public class GenerateCode {

//	/**
//	 * @test method
//	 */
//	public static void main(String[] args) {
//		GenerateCode generateCode = new GenerateCode();
//		generateCode.getProductCode();
//
//	}
	
	public static String getProductCode(){
		StringBuffer code = new StringBuffer();
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("MMddHHmmss");
		code.append(time.format(date));
		Random r = new Random(System.currentTimeMillis());
		code.append(r.nextInt(9));
		return code.toString();
	}

}
