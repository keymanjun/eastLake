package com.application.personnel.service;

import java.util.List;

import com.application.entity.po.DepartmentEntity;
import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.MD5Util;
import com.framework.utils.StringUtils;

/**
 * 
 * @author wangjunw@cn.ibm.com
 *
 */
public class TrainingInfoService extends BaseService
{		
	public PaginationSupport loadDepartmentInfoList(DepartmentEntity entity, int pageSize,int pageNo) throws Exception
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" from DepartmentEntity d  where 1=1");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getCode())){
				queryString.append(" and d.code like '%"+StringUtils.replace(entity.getCode(), "'", "''")+"%'");
			}
			if(!super.isNullOrEmpty(entity.getName())){
				queryString.append(" and d.name like '%"+StringUtils.replace(entity.getName(), "'", "''")+"%'");
			}
		}
		if(!super.isNullOrEmpty(entity.getOrderBy())&&!super.isNullOrEmpty(entity.getDescOrasc())){
			queryString.append(" order by ");
			queryString.append(entity.getOrderBy());
			queryString.append(" ");
			queryString.append(entity.getDescOrasc());
		}
		return super.getHbm3Dao().findPageByQuery(queryString.toString(), pageSize, pageNo, null);
	}
	

	public void saveDepartment(DepartmentEntity department) throws Exception
	{
		super.getHbm3Dao().save(department);
	}
	
	public void deleteDepartment(String ids) throws Exception
	{
		String hql=" delete from DepartmentEntity d where d.diId in("+ids+")";
		super.getHbm3Dao().batchUpdateByHql(hql);		
	}


	public DepartmentEntity getDepartmentEntity(DepartmentEntity department) 
	{		
		StringBuffer queryString = new StringBuffer(" from DepartmentEntity d where 1=1 ");
		if(department!=null){
			if(!super.isNullOrEmpty(department.getCode())){
				queryString.append(" and d.code like '%"+StringUtils.replace(department.getCode(), "'", "''")+"%'");
			}
			if(!super.isNullOrEmpty(department.getName())){
				queryString.append(" and u.name like '%"+StringUtils.replace(department.getName(), "'", "''")+"%'");
			}	
		}
		List<DepartmentEntity> list= super.getHbm3Dao().find(queryString.toString());
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

}
