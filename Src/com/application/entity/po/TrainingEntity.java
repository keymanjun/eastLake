package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class TrainingEntity extends BaseEntity{

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 2190823312372374561L;
	
	private Long tiId;
	private Long diId;
	private Long tiType;
	private Long tiStatus;
	private String tiDate;
	private String tiContent;
	private String tiAddress;
	private String tiNotes;
	private String tiPerson;
	private String tiAddDate;
	public Long getTiId() {
		return tiId;
	}
	public void setTiId(Long tiId) {
		this.tiId = tiId;
	}
	public Long getDiId() {
		return diId;
	}
	public void setDiId(Long diId) {
		this.diId = diId;
	}
	public Long getTiType() {
		return tiType;
	}
	public void setTiType(Long tiType) {
		this.tiType = tiType;
	}
	public Long getTiStatus() {
		return tiStatus;
	}
	public void setTiStatus(Long tiStatus) {
		this.tiStatus = tiStatus;
	}
	public String getTiDate() {
		return tiDate;
	}
	public void setTiDate(String tiDate) {
		this.tiDate = tiDate;
	}
	public String getTiContent() {
		return tiContent;
	}
	public void setTiContent(String tiContent) {
		this.tiContent = tiContent;
	}
	public String getTiAddress() {
		return tiAddress;
	}
	public void setTiAddress(String tiAddress) {
		this.tiAddress = tiAddress;
	}
	public String getTiNotes() {
		return tiNotes;
	}
	public void setTiNotes(String tiNotes) {
		this.tiNotes = tiNotes;
	}
	public String getTiPerson() {
		return tiPerson;
	}
	public void setTiPerson(String tiPerson) {
		this.tiPerson = tiPerson;
	}
	public String getTiAddDate() {
		return tiAddDate;
	}
	public void setTiAddDate(String tiAddDate) {
		this.tiAddDate = tiAddDate;
	}
	
	
}
