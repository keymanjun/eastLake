package com.system.entity.po;



/**
 * AbstractSysUser entity provides the base persistence definition of the
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRoleMenu implements java.io.Serializable {

	// Fields

	private Long id;
	private Long roleid;
	private String menucode;
	public Long getId() {
		return this.id;
	}
	public Long getRoleid() {
		return this.roleid;
	}
	public String getMenucode() {
		return this.menucode;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public void setMenucode(String menucode) {
		this.menucode = menucode;
	}
	
}