
package com.system.menu;


import com.framework.base.BaseAction;
import com.framework.components.pager.PaginationSupport;
import com.system.entity.po.SysMenu;

public class MenuAction extends BaseAction
{	
	private static final long serialVersionUID = 1L;
	private final static String MENU_LIST="menuList";
	
	MenuService menuService=null;
	SysMenu menu=null;
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception 
	{
		PaginationSupport pageResult = menuService.loadMenuList(this.getMenu(), super.getPageSize().intValue(), super.getStartIndex().intValue());
		this.getRequest().put(PAGER_RESULT_KEY, pageResult);
		this.setMenu(this.getMenu());
		return MENU_LIST;
	}
	
	
	public String deleteMenu() throws Exception 
	{
		String menuCodes=this.getReq().getParameter("menuCodes");
		menuService.deleteMenu(menuCodes);
		return execute();
	}

	public MenuService getMenuService() {
		return this.menuService;
	}

	public SysMenu getMenu() {
		return this.menu;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public void setMenu(SysMenu menu) {
		this.menu = menu;
	}
	
	
	
}
