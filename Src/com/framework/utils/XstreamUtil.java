package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**

 * @author feiluo
 *
 */
public final class XstreamUtil 
{
	private static XStream xstream=null;
	
	private XstreamUtil(){}
	public static XStream getXstream(){
		if(xstream==null){
			return new XStream(new DomDriver());
		}
		return xstream;
	}
	
	public static void toXmlFromObject(String asname,Object obj,String sclassname,String savefilename) throws Exception
	{
		if(obj==null) return;
		FileOutputStream fs=null;
		try{
			//getXstream().aliasField(asname, Class.forName(sclassname),asname);
			getXstream().alias(asname, Class.forName(sclassname));
			fs = new FileOutputStream(savefilename);
			getXstream().toXML(obj, fs);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(fs!=null){
				fs.close();
			}
		}
	}
	
	/**
	 * 
	 * @param list
	 * @param sclassname
	 * @param savefilename
	 * @throws Exception
	 */
	public static void toXmlFromList(List list,String sclassname,String savefilename) throws Exception
	{
		if(list==null || list.size()<1) return;
		FileOutputStream fs=null;
		try{
			getXstream().alias("list", Class.forName(sclassname));
			fs = new FileOutputStream(savefilename);
			getXstream().toXML(list, fs);
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(fs!=null){
				fs.close();
			}
		}
	}
	
	/**
	 * 
	 * @param filename
	 * @param sclassname
	 * @return
	 * @throws Exception
	 */
	public static List toListFromXml(String filename,String sclassname) throws Exception
	{
		List list=null;
		if(filename==null || "".equals(filename)) return null;
		FileOutputStream fs=null;
		try{
			list=(List)getXstream().fromXML(new FileInputStream(filename));
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(fs!=null){
				fs.close();
			}
		}
		return list;
	}

	
	public static Object toObjectForXml(List list,String sclassname) throws Exception
	{
		Object obj=null;
		return obj;
	}
	
	public static void main(String[] args) throws Exception{
//		ExtractRuleVO vo=new ExtractRuleVO();
//		List list1=new ArrayList();
//		List list2=new ArrayList();
//		List list3=new ArrayList();
//		vo.setPersistences(list1);
//		vo.setCommonMetas(list2);
//		vo.setSiteList(list3);
//		toXmlFromObject("sites",vo,ExtractRuleVO.class.getName(),"d:/abc.xml");
//		
//	
	}
	
}
