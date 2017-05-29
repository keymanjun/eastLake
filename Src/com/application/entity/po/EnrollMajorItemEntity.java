package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class EnrollMajorItemEntity extends BaseEntity{
	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String orginalNo;
	private String name;
	private String examTime;
	
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
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	
}
