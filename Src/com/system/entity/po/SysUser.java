package com.system.entity.po;

import java.util.ArrayList;
import java.util.List;


/**
 * AbstractSysUser entity provides the base persistence definition of the
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

	// Fields

	private Long userid;
	private String useraccount;
	private String username;
	private String userpwd;
	private Long usersex;
	private String userimg;
	private String userjob;
	private String usertel;
	private String userfax;
	private String usermp;
	private String useremail;
	private String userqq;
	private String usermsn;
	private String usercity;
	private Long usertype;
	private Long userlevel;
	private String userdesc;
	private String userboth;
	private Long isenable;
	
	
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** full constructor */
	public SysUser(String useraccount, String username, String userpwd,
			Long usersex, String userimg, String userjob, String usertel,
			String userfax, String usermp, String useremail, String userqq,
			String usermsn, String usercity, Long usertype, Long userlevel,
			String userdesc, String userboth,Long isenable) {
		this.useraccount = useraccount;
		this.username = username;
		this.userpwd = userpwd;
		this.usersex = usersex;
		this.userimg = userimg;
		this.userjob = userjob;
		this.usertel = usertel;
		this.userfax = userfax;
		this.usermp = usermp;
		this.useremail = useremail;
		this.userqq = userqq;
		this.usermsn = usermsn;
		this.usercity = usercity;
		this.usertype = usertype;
		this.userlevel = userlevel;
		this.userdesc = userdesc;
		this.userboth=userboth;
		this.isenable = isenable;
	}

	// Property accessors

	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUseraccount() {
		return this.useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return this.userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public Long getUsersex() {
		return this.usersex;
	}

	public void setUsersex(Long usersex) {
		this.usersex = usersex;
	}

	public String getUserimg() {
		return this.userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public String getUserjob() {
		return this.userjob;
	}

	public void setUserjob(String userjob) {
		this.userjob = userjob;
	}

	public String getUsertel() {
		return this.usertel;
	}

	public void setUsertel(String usertel) {
		this.usertel = usertel;
	}

	public String getUserfax() {
		return this.userfax;
	}

	public void setUserfax(String userfax) {
		this.userfax = userfax;
	}

	public String getUsermp() {
		return this.usermp;
	}

	public void setUsermp(String usermp) {
		this.usermp = usermp;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserqq() {
		return this.userqq;
	}

	public void setUserqq(String userqq) {
		this.userqq = userqq;
	}

	public String getUsermsn() {
		return this.usermsn;
	}

	public void setUsermsn(String usermsn) {
		this.usermsn = usermsn;
	}

	public String getUsercity() {
		return this.usercity;
	}

	public void setUsercity(String usercity) {
		this.usercity = usercity;
	}

	public Long getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Long usertype) {
		this.usertype = usertype;
	}

	public Long getUserlevel() {
		return this.userlevel;
	}

	public void setUserlevel(Long userlevel) {
		this.userlevel = userlevel;
	}

	public String getUserdesc() {
		return this.userdesc;
	}

	public void setUserdesc(String userdesc) {
		this.userdesc = userdesc;
	}
	
	public String getUserboth() {
		return this.userboth;
	}

	public void setUserboth(String userboth) {
		this.userboth = userboth;
	}

	public Long getIsenable() {
		return this.isenable;
	}

	public void setIsenable(Long isenable) {
		this.isenable = isenable;
	}

	
}