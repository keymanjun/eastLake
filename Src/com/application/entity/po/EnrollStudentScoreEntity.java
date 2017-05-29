package com.application.entity.po;

import java.util.ArrayList;
import java.util.List;

public class EnrollStudentScoreEntity extends EnrollStudentEntity {

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = -7556141462380113384L;

	private List<EnrollMajorScoreEntity> list = new ArrayList<EnrollMajorScoreEntity>();
	

	public List<EnrollMajorScoreEntity> getList() {
		return list;
	}

	public void setList(List<EnrollMajorScoreEntity> list) {
		this.list = list;
	}}
