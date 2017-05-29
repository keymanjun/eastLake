package com.system.entity.po;


/**
 * AbstractSysLog entity provides the base persistence definition of the SysLog
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysLog implements java.io.Serializable {

	// Fields

	private Long logid;
	private String logacount;
	private Long logtype;
	private String loginfo;
	private String logdate;
	String logmenu;
	String logurl;
	// Constructors

	/** default constructor */
	public SysLog() {
	}

	/** full constructor */
	public SysLog(String logacount, Long logtype, String loginfo,
			String logdate) {
		this.logacount = logacount;
		this.logtype = logtype;
		this.loginfo = loginfo;
		this.logdate = logdate;
	}

	// Property accessors

	public Long getLogid() {
		return this.logid;
	}

	public void setLogid(Long logid) {
		this.logid = logid;
	}

	public String getLogacount() {
		return this.logacount;
	}

	public void setLogacount(String logacount) {
		this.logacount = logacount;
	}

	public Long getLogtype() {
		return this.logtype;
	}

	public void setLogtype(Long logtype) {
		this.logtype = logtype;
	}

	public String getLoginfo() {
		return this.loginfo;
	}

	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}

	public String getLogdate() {
		return this.logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}

	public String getLogmenu() {
		return this.logmenu;
	}

	public String getLogurl() {
		return this.logurl;
	}

	public void setLogmenu(String logmenu) {
		this.logmenu = logmenu;
	}

	public void setLogurl(String logurl) {
		this.logurl = logurl;
	}
	
}