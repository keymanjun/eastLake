package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class LeaveEntity extends BaseEntity{

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 6308084481539038170L;
	
	private Long liId;
	private Long eiId;
	private Long liType;
	private Long liStatus;
	private String liStartDate;
	private String liEndDate;
	private String liNotes;
	private String liPerson;
	private String liDate;
	public Long getLiId() {
		return liId;
	}
	public void setLiId(Long liId) {
		this.liId = liId;
	}
	public Long getEiId() {
		return eiId;
	}
	public void setEiId(Long eiId) {
		this.eiId = eiId;
	}
	public Long getLiType() {
		return liType;
	}
	public void setLiType(Long liType) {
		this.liType = liType;
	}
	public Long getLiStatus() {
		return liStatus;
	}
	public void setLiStatus(Long liStatus) {
		this.liStatus = liStatus;
	}
	public String getLiStartDate() {
		return liStartDate;
	}
	public void setLiStartDate(String liStartDate) {
		this.liStartDate = liStartDate;
	}
	public String getLiEndDate() {
		return liEndDate;
	}
	public void setLiEndDate(String liEndDate) {
		this.liEndDate = liEndDate;
	}
	public String getLiNotes() {
		return liNotes;
	}
	public void setLiNotes(String liNotes) {
		this.liNotes = liNotes;
	}
	public String getLiPerson() {
		return liPerson;
	}
	public void setLiPerson(String liPerson) {
		this.liPerson = liPerson;
	}
	public String getLiDate() {
		return liDate;
	}
	public void setLiDate(String liDate) {
		this.liDate = liDate;
	}
	
}
