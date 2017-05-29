package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class EnrollMajorEntity extends BaseEntity{
	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String orginalNo;
	private String name;
	private String no;
	private String examDate;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrginalNo() {
		return orginalNo;
	}
	public void setOrginalNo(String orginalNo) {
		this.orginalNo = orginalNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
}
