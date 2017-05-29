package com.system.entity.po;
import java.util.ArrayList;
import java.util.List;


/**
 * AbstractSysRole entity provides the base persistence definition of the
 * SysRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysRole implements java.io.Serializable {

	// Fields

	private Long roleid;
	private String rolename;
	private String roledesc;
	private Long roletype;
	private String roleorgcode;
	private Long roleenable;
    String creater;
    String createtime;
	
	private List<SysUser> userList=new ArrayList<SysUser>();
	
	// Constructors

	/** default constructor */
	public SysRole() {
	}

	/** full constructor */
	public SysRole(String rolename, String roledesc, Long roletype,
			String roleorgcode, Long roleenable) {
		this.rolename = rolename;
		this.roledesc = roledesc;
		this.roletype = roletype;
		this.roleorgcode = roleorgcode;
		this.roleenable = roleenable;
	}

	// Property accessors

	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledesc() {
		return this.roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public Long getRoletype() {
		return this.roletype;
	}

	public void setRoletype(Long roletype) {
		this.roletype = roletype;
	}

	public String getRoleorgcode() {
		return this.roleorgcode;
	}

	public void setRoleorgcode(String roleorgcode) {
		this.roleorgcode = roleorgcode;
	}

	public Long getRoleenable() {
		return this.roleenable;
	}

	public void setRoleenable(Long roleenable) {
		this.roleenable = roleenable;
	}

	public List<SysUser> getUserList() {
		return this.userList;
	}

	public void setUserList(List<SysUser> userList) {
		this.userList = userList;
	}
    
	
	public String getCreater() {
		return this.creater;
	}

	

	public void setCreater(String creater) {
		this.creater = creater;
	}

	
	
	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}