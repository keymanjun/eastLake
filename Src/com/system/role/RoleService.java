package com.system.role;


import java.util.Date;
import java.util.List;

import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.MiscUtil;
import com.framework.utils.StringUtils;
import com.system.entity.po.SysRole;


public class RoleService extends BaseService
{        
	@SuppressWarnings("unchecked")
	public void saveRole(SysRole role,String sessionUser) throws Exception
	{
		role.setCreater(sessionUser);
		role.setCreatetime(MiscUtil.formatDate(new Date(),MiscUtil.FMT_DATE_YYYYMMDDHHmmss));
		role.setRoleenable(new Long(1));
		super.getHbm3Dao().save(role);;
	}
	
	public void deleteRole(String roleIds) throws Exception
	{
		String hql=" delete from SysUserRole t where t.roleid in("+roleIds+")";
		super.getHbm3Dao().batchUpdateByHql(hql);
		
	
		hql=" delete from SysMenuRole t where t.roleid in("+roleIds+")";
		super.getHbm3Dao().batchUpdateByHql(hql);
		

		hql=" delete from SysRole t where t.roleid in("+roleIds+")";
		super.getHbm3Dao().batchUpdateByHql(hql);
	}
    
    
    /**
     * @param entity
     * @param iPageNo
     * @param iPageSize
     * @return
     * @throws Exception
     */
    public PaginationSupport loadRoleList(SysRole entity,int iPageNo,int iPageSize) throws Exception
    {    	
    	PaginationSupport pageResult=null;    	
		StringBuffer queryString = new StringBuffer();	
		queryString.append(" from SysRole role where role.roleenable = 1 ");
		if(entity!=null){
			if(!super.isNullOrEmpty(entity.getRolename())){
				queryString.append(" and role.rolename like '%"+StringUtils.replace(entity.getRolename(), "'", "''")+"%'");
			}
		}
		queryString.append(" order by role.roleid");
		pageResult=super.getHbm3Dao().findPageByQuery(queryString.toString(), iPageNo, iPageSize,null);    
       return pageResult;
    }
        
    public SysRole getRoleEntity(SysRole entity) 
	{		
		String hql=" from SysRole r where 1=1 ";
		if(entity!=null){
			if(entity.getRoleid()!=null){
				hql+=" and r.roleid="+entity.getRoleid();
			}
			if(!super.isNullOrEmpty(entity.getRolename())){
				hql+=" and r.rolename = '"+StringUtils.replace(entity.getRolename(), "'", "''")+"'";
			}
		}
		List<SysRole> list= super.getHbm3Dao().find(hql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
}
