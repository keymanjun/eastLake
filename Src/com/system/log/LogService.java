package com.system.log;


import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.StringUtils;
import com.system.entity.po.SysLog;

public class LogService extends BaseService
{        
	 public PaginationSupport loadLoggerList(SysLog entity,int iPageNo,int iPageSize) throws Exception
	 {    	
	    	PaginationSupport pageResult=null;    	
			StringBuffer queryString = new StringBuffer();	
			queryString.append(" from SysLog t where 1=1 ");
			if(entity!=null){
				if(!super.isNullOrEmpty(entity.getLogacount())){
					queryString.append(" and t.logacount like '%"+StringUtils.replace(entity.getLogacount(), "'", "''")+"%'");
				}
			}
			queryString.append(" order by t.logid ");
			pageResult=super.getHbm3Dao().findPageByQuery(queryString.toString(), iPageNo, iPageSize,null);    
	       return pageResult;
	  }
}
