package com.system.rights;

import java.util.ArrayList;
import java.util.List;

import com.framework.base.BaseService;
import com.framework.utils.StringUtils;
import com.system.entity.po.SysMenu;
import com.system.entity.po.SysRoleMenu;
import com.system.entity.po.SysUser;
import com.system.entity.po.SysUserRole;
import com.system.user.UserAction;
/**
 * Ȩ��service
 * @author dafei
 *
 */
public class RightsService extends BaseService
{	
	@SuppressWarnings("unchecked")
	public SysUser checkLogin(String username,String password) throws Exception
	{
		String hql=" from SysUser u where u.useraccount='"+StringUtils.replace(username, "'", "''")+"' and  u.userpwd='"+password+"'";
		List<SysUser> list= super.getHbm3Dao().find(hql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	public SysUser checkName(String username) 
	{
		String hql=" from SysUser u where u.useraccount='"+StringUtils.replace(username, "'", "''")+"'";
		List<SysUser> list= super.getHbm3Dao().find(hql);
		if(list==null || list.size()<1) return null;
		return list.get(0);
	}
	
	public void savePassword(SysUser user) throws Exception
	{
		super.getHbm3Dao().save(user);
	}
	
	public List loadMenuRightsByUser(String sysmark,SysUser user) throws Exception
	{
		List list=new ArrayList();
		try{
			String roleIds="";
			if("admin".equals(user.getUseraccount().toLowerCase()))
			{
				return loadSuperRights(sysmark);
			}else if(user.getUsertype()==null || user.getUsertype().equals(UserAction.REGISTER_USER)){ //注册的学生指定权限
				return loadRegisterStudentRights(sysmark);
			}
			
			List<SysUserRole> rolelist= super.getHbm3Dao().find("from SysUserRole ur where ur.userid="+user.getUserid());
			if(rolelist==null || rolelist.size()<1) return list;
			for(int i=0;i<rolelist.size();i++)
			{
				roleIds+=rolelist.get(i).getRoleid().longValue()+",";
			}
			roleIds=roleIds.substring(0,roleIds.length()-1);
			list=loadMenusForRoles(sysmark,roleIds);
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	public List loadSuperRights(String sysmark) throws Exception
	{
		String sql=" select t.* from sys_menu t where t.sysmark='"+sysmark+"' and t.menuenable=1";
		return super.getHbm3Dao().loadResultsForSql(sql,SysMenu.class.getName());	
	}
	
	public List loadRegisterStudentRights(String sysmark) throws Exception //专升本注册学生的指定权限
	{
		
		return loadMenusForRoles(sysmark,UserAction.REGISTER_USER_ROLE);	
	}

	/**
	 * 
	 * @param roleId
	 */
	public List loadWFPUserForRole(Long roleId) throws Exception
	{
		List list=new ArrayList();
//		String hql=" select u from SysUser u where  u.isenable=1 and u.userid not in ";
//		hql+=" (select v1.userid from VUserRole v1 where v1.roleid="+roleId+")";
		
		String hql=" select u from SysUser u where  u.isenable=1 and u.userid not in ";
		hql+=" (select v1.userid from SysUserRole v1 where v1.roleid="+roleId+")";
		try{
			list=super.getHbm3Dao().find(hql);
            
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	/**
	 * @param roleId
	 */
	public List loadYFPUserForRole(Long roleId) throws Exception
	{
		List list=new ArrayList();
		String hql=" select u from SysUser u ,SysUserRole v1 where u.userid=v1.userid and v1.roleid="+roleId;
		try{
			list=super.getHbm3Dao().find(hql);
            
		}
		catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return list;
	}
    
	/**
	 * 
	 * @param roleId
	 * @param userIds
	 * @param isAdd   true
	 * @throws Exception
	 */
	public void updateUserRoleForRights(Long roleId,String userIds,boolean isAdd) throws Exception
	{
		String[] arrUserId=userIds.split(",");
		if(isAdd){			
			for(int i=0;i<arrUserId.length;i++){
				if(super.isNullOrEmpty(arrUserId[i])) continue;
				SysUserRole ur=new SysUserRole();
				ur.setRoleid(Long.valueOf(roleId));
				ur.setUserid(Long.valueOf(arrUserId[i]));
				super.getHbm3Dao().save(ur);
			}
		}else{
			String ids="";
			for(int i=0;i<arrUserId.length;i++){
				if(super.isNullOrEmpty(arrUserId[i])) continue;
				ids+=arrUserId[i]+",";
			}
			if(super.isNullOrEmpty(ids)) return;
			ids=ids.substring(0,ids.length()-1);
			String hql=" delete  from SysUserRole ur where ur.userid in("+ids+") and ur.roleid="+roleId;
			super.getHbm3Dao().batchUpdateByHql(hql);
		}
	}
	
	
    /**
     * 查询未分配的菜单
	 * @param roleId
	 */
	public List loadLeftMenuForRole(String sysmark,Long roleId) throws Exception
	{	
		List list=null;
    	StringBuffer sbsql=new StringBuffer();
    	sbsql.append("select * from (")
    		 .append("	select * from sys_menu t1 where t1.menucode not in ")
    		 .append("	(")
    		 .append("	  select t.menucode from sys_role_menu t where t.roleid="+roleId.longValue())
    		 .append("    union")
    		 .append("    select t2.menucode from V_MENURIGHTS t1,sys_menu t2 where t1.parentmenucode=t2.menucode and t1.roleid="+roleId.longValue())
    		 .append("   )")
    		 .append("  union")
    		 .append("   select distinct t2.* from sys_menu t1,sys_menu t2")
    		 .append("   where t1.menucode not in")
    		 .append("	 (")
    		 .append("	  select t.menucode from sys_role_menu t where t.roleid="+roleId.longValue())
    		 .append("    union")
    		 .append("    select t2.menucode from V_MENURIGHTS t1,sys_menu t2 where t1.parentmenucode=t2.menucode and t1.roleid="+roleId.longValue())
    		 .append("   )")
    		 .append("   and t1.parentmenucode=t2.menucode")
    		 .append(") t")
    		 .append(" where 1=1 and t.sysmark='"+sysmark+"' ")
    		 .append(" order by t.menucode asc,t.sequ asc");
    	 
		list=super.getHbm3Dao().loadResultsForSql(sbsql.toString(),SysMenu.class.getName());    
		if(list==null || list.size()<1) return null;
		return list;		
	}
	
	/** 
	 * 查询已分配的菜单
	 * @param roleId
	 */
    public List loadRightMenuForRole(String sysmark,Long roleId) throws Exception
    {
    	List list=new ArrayList();
    	StringBuffer sbsql=new StringBuffer();
    	sbsql.append(" select t.* from ( ");
    	
    	sbsql.append(" select t1.* from sys_role_menu t,sys_menu t1 ");
    	sbsql.append(" where t.menucode=t1.menucode and t.roleid="+roleId.longValue());
    	sbsql.append("   and t1.sysmark='"+sysmark+"' "); 
    	
    	sbsql.append(" union ")
    	     .append(" select distinct t2.* from sys_menu t2 WHERE t2.menucode in ( ")
    	     .append(" SELECT tt.parentmenucode FROM sys_role_menu tt1, sys_menu tt WHERE tt1.menucode = tt.menucode ");
    	sbsql.append("   and tt1.roleid="+roleId.longValue()); 
    	sbsql.append("   and tt.sysmark='"+sysmark+"') "); 
    	
    	sbsql.append(" ) t order by t.menucode asc,t.sequ asc ");    	    	
		list=super.getHbm3Dao().loadResultsForSql(sbsql.toString(),SysMenu.class.getName());    
		if(list==null || list.size()<1) return null;
		return list;
	}
    
    /**查询多个角色的菜单ܲ˵�
	 * @param roleIds
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public List loadMenusForRoles(String sysmark,String roleIds) throws Exception
    {
		List list=new ArrayList();
    	StringBuffer sbsql=new StringBuffer();
    	sbsql.append(" select distinct t.* from ( ");    	
    	sbsql.append(" select distinct t1.* from sys_role_menu t,sys_menu t1 ");
    	sbsql.append(" where t.menucode=t1.menucode and t.roleid in("+roleIds+")");
    	sbsql.append("   and t1.sysmark='"+sysmark+"' "); 
    	
    	sbsql.append(" union ")
    	     .append(" select t2.* from sys_menu t2 where t2.menucode in (select tt.parentmenucode from sys_role_menu tt1,sys_menu tt where tt1.menucode=tt.menucode ");
    	sbsql.append("   and tt1.roleid in("+roleIds+")"); 
    	sbsql.append("   and tt.sysmark='"+sysmark+"')"); 
    	
    	sbsql.append(" ) t order by t.menucode asc,t.sequ asc ");    	    	
		list=super.getHbm3Dao().loadResultsForSql(sbsql.toString(),SysMenu.class.getName());    
		if(list==null || list.size()<1) return null;
		return list;
	}	
    
	/**
	 * 
	 * @param roleId
	 * @param menuIds
	 * @param isAdd   true
	 * @throws Exception
	 */
	public void addRightsForRole(String sysmark,Long roleId,String menuIds) throws Exception
	{
		String[] arrMenuId=menuIds.split(",");
		String ids=markMenucodes(menuIds);	
		for(int i=0;i<arrMenuId.length;i++)
		{
			if(super.isNullOrEmpty(arrMenuId[i])) continue;
			
			SysRoleMenu mr=new SysRoleMenu();
            mr.setRoleid(Long.valueOf(roleId));
            mr.setMenucode(arrMenuId[i]);
			super.getHbm3Dao().save(mr);
		}
	}
	
	/**
	 * 移除权限操作
	 * @throws Exception
	 */
	public void delRightsForRole(String sysmark,Long roleId,String menuIds) throws Exception
	{
		String ids=markMenucodes(menuIds);
		String hql=" delete from SysRoleMenu mr where mr.menucode in("+ids+") and mr.roleid="+roleId.longValue();
		super.getHbm3Dao().batchUpdateByHql(hql);
	}

	private String markMenucodes(String menuIds)
	{
		String[] arrMenuId=menuIds.split(",");		
		String ids="";
		for(int i=0;i<arrMenuId.length;i++){
			if(super.isNullOrEmpty(arrMenuId[i])) continue;
			ids+="'"+arrMenuId[i]+"',";
		}
		ids=ids.substring(0,ids.length()-1);
		return ids;
	}
}
