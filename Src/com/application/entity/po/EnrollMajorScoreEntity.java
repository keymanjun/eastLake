package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class EnrollMajorScoreEntity extends BaseEntity{

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 2028441060435724205L;
	private Long id;
	private Long enrollId;
	private String majorItemName;
	private String majorItemScore;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(Long enrollId) {
		this.enrollId = enrollId;
	}
	public String getMajorItemName() {
		return majorItemName;
	}
	public void setMajorItemName(String majorItemName) {
		this.majorItemName = majorItemName;
	}
	public String getMajorItemScore() {
		return majorItemScore;
	}
	public void setMajorItemScore(String majorItemScore) {
		this.majorItemScore = majorItemScore;
	}
	
}
