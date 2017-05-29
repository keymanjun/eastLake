package com.system.entity.po;



/**
 * AbstractSysUser entity provides the base persistence definition of the
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUserRole implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long roleid;
	public Long getId() {
		return this.id;
	}
	public Long getUserid() {
		return this.userid;
	}
	public Long getRoleid() {
		return this.roleid;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	
	
	
}