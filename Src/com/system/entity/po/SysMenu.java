package com.system.entity.po;
/**
 * AbstractSysMenu entity provides the base persistence definition of the
 * SysMenu entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysMenu implements java.io.Serializable {

	// Fields

	private String menucode;
	private String menuname;
	private String menuenname;
	private String parentmenucode;
	private String menuurl;
	private Long menuflag;
	private Long sequ;
	private Long menuenable;
	private String sysmark;
	// Constructors


	/** default constructor */
	public SysMenu() {
	}

	/** full constructor */
	public SysMenu(String menuname, String menuenname,
			String parentmenucode, String menuurl, Long menuflag, Long sequ,
			Long menuenable) {
		this.menuname = menuname;
		this.menuenname = menuenname;
		this.parentmenucode = parentmenucode;
		this.menuurl = menuurl;
		this.menuflag = menuflag;
		this.sequ = sequ;
		this.menuenable = menuenable;
	}

	// Property accessors

	public String getMenucode() {
		return this.menucode;
	}

	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}

	public String getMenuname() {
		return this.menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuenname() {
		return this.menuenname;
	}

	public void setMenuenname(String menuenname) {
		this.menuenname = menuenname;
	}

	public String getParentmenucode() {
		return this.parentmenucode;
	}

	public void setParentmenucode(String parentmenucode) {
		this.parentmenucode = parentmenucode;
	}

	public String getMenuurl() {
		return this.menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Long getMenuflag() {
		return this.menuflag;
	}

	public void setMenuflag(Long menuflag) {
		this.menuflag = menuflag;
	}

	public Long getSequ() {
		return this.sequ;
	}

	public void setSequ(Long sequ) {
		this.sequ = sequ;
	}

	public Long getMenuenable() {
		return this.menuenable;
	}

	public void setMenuenable(Long menuenable) {
		this.menuenable = menuenable;
	}

	
	public String getSysmark() {
		return sysmark;
	}

	public void setSysmark(String sysmark) {
		this.sysmark = sysmark;
	}

	public String getCodeString()
	{
		String str="0";
		if(this.getMenucode().length()>2)
			str = "1";
		return str;		
	}
}