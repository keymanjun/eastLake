package com.system.entity.po;

/**
 * AbstractSysDict entity provides the base persistence definition of the
 * SysDict entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysDict implements java.io.Serializable {

	// Fields

	private Long ddid;
	private String ddcode;
	private String ddname;
	private Long ddtype;
	private String dddesc;
	private Long ddenable;
	private Long ddseq;
	private String ddcreatedate;
	private Long ddcreateuser;
	private String ddeditdate;
	private Long ddedituser;

	// Constructors

	/** default constructor */
	public SysDict() {
	}

	/** full constructor */
	public SysDict(
							String ddcode, 
							String ddname, 
							Long ddtype, 
							String dddesc, 
							Long ddenable, 
							Long ddseq, 
							String ddcreatedate, 
							Long ddcreateuser, 
							String ddeditdate, 
							Long ddedituser) {
		this.ddcode = ddcode;
		this.ddname = ddname;
		this.ddtype = ddtype;
		this.dddesc = dddesc;
		this.ddenable = ddenable;
		this.ddseq = ddseq;
		this.ddcreatedate = ddcreatedate;
		this.ddcreateuser = ddcreateuser;
		this.ddeditdate = ddeditdate;
		this.ddedituser = ddedituser;
	}
	
	
	// Property accessors

	//===============get

	public Long getDdid() {
		return ddid;
	}

	public String getDdcode() {
		return ddcode;
	}

	public String getDdname() {
		return ddname;
	}

	public Long getDdtype() {
		return ddtype;
	}

	public String getDddesc() {
		return dddesc;
	}

	public Long getDdenable() {
		return ddenable;
	}

	public Long getDdseq() {
		return ddseq;
	}

	public String getDdcreatedate() {
		return ddcreatedate;
	}

	public Long getDdcreateuser() {
		return ddcreateuser;
	}

	public String getDdeditdate() {
		return ddeditdate;
	}

	public Long getDdedituser() {
		return ddedituser;
	}
	
	
	//===============set

	public void setDdid(Long ddid) {
		this.ddid = ddid;
	}

	public void setDdcode(String ddcode) {
		this.ddcode = ddcode;
	}

	public void setDdname(String ddname) {
		this.ddname = ddname;
	}

	public void setDdtype(Long ddtype) {
		this.ddtype = ddtype;
	}

	public void setDddesc(String dddesc) {
		this.dddesc = dddesc;
	}

	public void setDdenable(Long ddenable) {
		this.ddenable = ddenable;
	}

	public void setDdseq(Long ddseq) {
		this.ddseq = ddseq;
	}

	public void setDdcreatedate(String ddcreatedate) {
		this.ddcreatedate = ddcreatedate;
	}

	public void setDdcreateuser(Long ddcreateuser) {
		this.ddcreateuser = ddcreateuser;
	}

	public void setDdeditdate(String ddeditdate) {
		this.ddeditdate = ddeditdate;
	}

	public void setDdedituser(Long ddedituser) {
		this.ddedituser = ddedituser;
	}
}