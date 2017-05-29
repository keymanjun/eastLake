package com.application.entity.po;

import java.util.HashSet;
import java.util.Set;

import com.system.entity.po.BaseEntity;

public class EnrollDicEntity extends BaseEntity {

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 5097151926323423429L;
	private Long id;
	private String name;
	private String value;
	private String dicType;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDicType() {
		return dicType;
	}
	public void setDicType(String dicType) {
		this.dicType = dicType;
	}
	
}
