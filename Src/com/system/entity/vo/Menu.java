package com.system.entity.vo;

import java.util.List;


/**
 * @author User
 *
 */
public class Menu {

	private String menuid;
	
	private String icon = "icon-sys";

	private String menuname;

	private List<MenuElements> menus;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public List<MenuElements> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuElements> menus) {
		this.menus = menus;
	}






	




	
}
