package com.system.entity.vo;

/**
 * @author User
 *
 */
public class MenuElements {
	
	private String menuname;
	
	private String icon = "L3";

	private String url;

	public MenuElements() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuElements(String menuname) {
		this.menuname = menuname;
	}
	
	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
