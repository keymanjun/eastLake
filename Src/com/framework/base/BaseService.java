package com.framework.base;

import java.util.List;
import java.util.Map;

import com.framework.db.springhibernate3.GenericDao;
import com.framework.db.sqlmap.SelectTag;
import com.framework.utils.DSUitility;


public class BaseService {
	@SuppressWarnings("unchecked")
	GenericDao hbm3Dao=null;

	@SuppressWarnings("unchecked")
	public GenericDao getHbm3Dao() {
		return this.hbm3Dao;
	}

	@SuppressWarnings("unchecked")
	public void setHbm3Dao(GenericDao hbm3Dao) {
		this.hbm3Dao = hbm3Dao;
	}
	
	public boolean isNullOrEmpty(String s){
		return s==null || "".equals(s);
	}
	
	@SuppressWarnings("unchecked")
	public String conv2ExtGridData(List list,String[] columns) throws Exception{
		return DSUitility.conv2ExtGridData(list,columns);
	}
	
	public String getSql(String id,Map parsMap) throws Exception
	{
		return SelectTag.getSql(id, parsMap);
	}
	
}
