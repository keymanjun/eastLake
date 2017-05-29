package com.system.entity.po;

import java.io.Serializable;

public class BaseEntity implements Serializable {
	
	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderBy;
	private String descOrasc;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDescOrasc() {
		return descOrasc;
	}

	public void setDescOrasc(String descOrasc) {
		this.descOrasc = descOrasc;
	}
}
