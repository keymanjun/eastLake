package com.system.user;

import java.util.List;

import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.MD5Util;
import com.framework.utils.StringUtils;
import com.system.entity.po.SysUser;

/**
 * 
 * @author dafei
 *
 */
public class UserService extends BaseService
{		
	public PaginationSupport loadUserList(SysUser user, int pageSize,int pageNo) throws Exception
	{
		String hql=" from SysUser u  where 1=1";
		if(user!=null){
			if(!super.isNullOrEmpty(user.getUsername())){
				hql+=" and u.useraccount like '%"+StringUtils.replace(user.getUsername(), "'", "''")+"%'";
			}
		}
		hql+=" order by u.useraccount ";
		return super.getHbm3Dao().findPageByQuery(hql, pageSize, pageNo, null);
	}
	

	public void saveUser(SysUser user) throws Exception
	{
		user.setIsenable(new Long(1));
		super.getHbm3Dao().save(user);
	}
	
	public void savePassword(SysUser user) throws Exception
	{
		user.setIsenable(new Long(1));
		
		if(!super.isNullOrEmpty(user.getUserpwd()))
		{
			user.setUserpwd(MD5Util.getMD5Password(user.getUserpwd()));
		}
		super.getHbm3Dao().save(user);
	}
	
	public void deleteUser(String userIds) throws Exception
	{
		String hql=" delete from SysUser t where t.userid in("+userIds+")";
		super.getHbm3Dao().batchUpdateByHql(hql);		
	}


	public SysUser getUserEntity(SysUser user) 
	{		
		String hql=" from SysUser u where 1=1 ";
		if(user!=null){
			if(!super.isNullOrEmpty(user.getUsername())){
				hql+=" and u.username = '"+StringUtils.replace(user.getUsername(), "'", "''")+"'";
			}
			if(!super.isNullOrEmpty(user.getUseraccount())){
				hql+=" and u.useraccount = '"+StringUtils.replace(user.getUseraccount(), "'", "''")+"'";
			}	
			if(user.getUserid()!=null){
				hql+=" and u.userid="+user.getUserid();
			}
		}
		List<SysUser> list= super.getHbm3Dao().find(hql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

}
