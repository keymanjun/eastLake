package com.system.entity.po;



/**
 * AbstractSysUser entity provides the base persistence definition of the
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VUserRole implements java.io.Serializable {

	// Fields

	private String urid;
	private Long userid;
	private Long roleid;
	String useraccount;
	public String getUrid() {
		return this.urid;
	}
	public Long getUserid() {
		return this.userid;
	}
	public Long getRoleid() {
		return this.roleid;
	}
	public String getUseraccount() {
		return this.useraccount;
	}
	public void setUrid(String urid) {
		this.urid = urid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	
	
	
}