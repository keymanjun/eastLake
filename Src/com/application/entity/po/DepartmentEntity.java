package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class DepartmentEntity extends BaseEntity{

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = -7037220062877555874L;
	private Long diId;
	private String code;
	private String name;
	
	public Long getDiId() {
		return diId;
	}
	public void setDiId(Long diId) {
		this.diId = diId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
