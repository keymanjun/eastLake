package com.system.dict;


import java.util.List;

import com.framework.base.BaseService;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.StringUtils;
import com.system.entity.po.SysMenu;

public class DictService extends BaseService
{        
	public PaginationSupport loadMenuList(SysMenu menu, int pageSize,int pageNo) throws Exception
	{
		String hql=" from SysMenu t where 1=1";
		if(menu!=null){
			if(super.isNullOrEmpty(menu.getMenuname())){
				hql+=" and t.menuname like '%"+StringUtils.replace(menu.getMenuname(), "'", "''")+"'%";
			}			
		}
		hql+=" order by t.menucode ";
		return super.getHbm3Dao().findPageByQuery(hql, pageSize, pageNo, null);
	}
	
	@SuppressWarnings("unchecked")
	public void saveMenu(SysMenu menu) throws Exception
	{
		super.getHbm3Dao().save(menu);
	}
	
	public void deleteMenu(String menucodes) throws Exception
	{
		String hql=" delete from SysMenu t where t.menucode in("+menucodes+")";
		super.getHbm3Dao().batchUpdateByHql(hql);	
	}
    
    public List<SysMenu> getMenuList(String parentCode) throws Exception{
    	List<SysMenu> list = null;
    	try{
    		StringBuffer hql = new StringBuffer();
    		hql.append("from SysMenu m where m.menuenable = 1 and m.parentmenucode = '"+parentCode+"'");
    		list = super.getHbm3Dao().find(hql.toString(),null);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		throw e;
    	}
    	return list;
    }
}
