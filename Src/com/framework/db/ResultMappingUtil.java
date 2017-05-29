package com.framework.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * @author feiluo
 *
 */
public final class ResultMappingUtil 
{
	private ResultMappingUtil(){}
	
	/**
	 * ����¼�洢��ӳ���ʵ����
	 * @param rsResult
	 * @param entityName
	 * @param st              �����¼�в�����clob��blob����ʱ,��������Ϊ��,�˲�����Ҫ����clob��blob����
	 * @return
	 * @throws Exception
	 */
	public static List mappintEntityList(ResultSet rsResult, String entityName,PreparedStatement st) throws Exception
    {
		List resultList=new ArrayList();
		List keylist=new ArrayList();
		int keylen=0;
		try
		{
			while (rsResult.next())
	        {
				ResultSetMetaData rsMetaData = rsResult.getMetaData();
				if(keylen<1)
				{
					keylen=rsMetaData.getColumnCount();
					for(int j=0;j<keylen;j++){
						if("".equals(rsMetaData.getColumnLabel(j+1))){
							keylist.add(rsMetaData.getColumnName(j+1));
							continue;
						}
						keylist.add(rsMetaData.getColumnLabel(j+1));
					}
				}
				Object entityobj=Class.forName(entityName).newInstance();
				for(int i=0;i<keylen;i++)
				{
				   Object obj=rsResult.getObject(keylist.get(i).toString());
				   if(st!=null){ 
					   //����clob������Ҫ���⴦��
					   if(obj instanceof byte[]){
						   obj=getClob2String(rsResult,keylist.get(i).toString(),st);
					   }
				   }
				   BeanUtils.copyProperty(entityobj, keylist.get(i).toString(), obj);				   
				}
				resultList.add(entityobj);
	        }
		}
		catch(Exception e){
			throw e;
		}
		return resultList;
    }
	
	/** ����clob�����ַ���
	 * add by dafei at 2009-8-10
	 * @param rsResult
	 * @param key
	 * @param st
	 * @return
	 * @throws Exception
	 */
	public static String getClob2String(ResultSet rsResult,String key,PreparedStatement st) throws Exception
	{
		Reader clobReader = rsResult.getCharacterStream(key);
		if (clobReader == null) {
			return null;
		}
		String str = new String();
		BufferedReader bufferedClobReader = new BufferedReader(clobReader);
		try
		{
			String line = null;
			while ((line = bufferedClobReader.readLine()) != null) {
				str += line;
			}
			bufferedClobReader.close();
		} catch (IOException e) {
			throw new SQLException(e.toString());
		}
		return str;
	}
	
	
}
