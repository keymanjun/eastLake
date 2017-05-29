package com.framework.utils;

import java.lang.reflect.Method;
import java.util.List;
/**
 * 
 * @author dafei
 *
 */
public final class DSUitility 
{
	private DSUitility(){}
	
	/**根据字段名反射取出实体内相应的属性值,将实体值转换为简单柱状图数组数据格式
	 * 
	 * @param rowkey
	 * @param list
	 * @param columns
	 * @return
	 * @throws Exception
	 */
	public static String conv2ExtGridData(List list,String[] columns) throws Exception
	{
		String sReturn="[";
		if(list==null || list.size()<1) return "[]";
		try
		{			
			for(int i=0;i<list.size();i++)
			{
				Object bean=list.get(i);
				if(bean==null) continue;
				String arrs="[";
				for(int j=0;j<columns.length;j++)
				{
					Method method=bean.getClass().getMethod(columns[j], null);
					Object obj=method.invoke(bean, null);
					if(obj instanceof String){
						arrs=arrs+"'"+(obj==null?"":obj.toString())+"',";
						continue;
					}
					arrs=arrs+"'"+(obj==null?"0":obj.toString())+"',";
				}
				sReturn+=arrs.substring(0,arrs.length()-1)+"],";				
			}
			sReturn=sReturn.substring(0,sReturn.length()-1)+"]";
		}
		catch(Exception e){
			throw e;
		}
		return sReturn;
	}
	
}
