package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class CertificateEntity extends BaseEntity{

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 1321172860485709878L;
	
	private Long ciId;
	private Long eiId;
	private Long ciStatus;
	private String ciCode;
	private String ciName;
	private String ciOffice;
	private String ciDate;
	private String ciNotes;
	private String ciPerson;
	private String ciAddDate;
	public Long getCiId() {
		return ciId;
	}
	public void setCiId(Long ciId) {
		this.ciId = ciId;
	}
	public Long getEiId() {
		return eiId;
	}
	public void setEiId(Long diId) {
		this.eiId = diId;
	}
	public Long getCiStatus() {
		return ciStatus;
	}
	public void setCiStatus(Long ciStatus) {
		this.ciStatus = ciStatus;
	}
	public String getCiCode() {
		return ciCode;
	}
	public void setCiCode(String ciCode) {
		this.ciCode = ciCode;
	}
	public String getCiName() {
		return ciName;
	}
	public void setCiName(String ciName) {
		this.ciName = ciName;
	}
	public String getCiOffice() {
		return ciOffice;
	}
	public void setCiOffice(String ciOffice) {
		this.ciOffice = ciOffice;
	}
	public String getCiDate() {
		return ciDate;
	}
	public void setCiDate(String ciDate) {
		this.ciDate = ciDate;
	}
	public String getCiNotes() {
		return ciNotes;
	}
	public void setCiNotes(String ciNotes) {
		this.ciNotes = ciNotes;
	}
	public String getCiPerson() {
		return ciPerson;
	}
	public void setCiPerson(String ciPerson) {
		this.ciPerson = ciPerson;
	}
	public String getCiAddDate() {
		return ciAddDate;
	}
	public void setCiAddDate(String ciAddDate) {
		this.ciAddDate = ciAddDate;
	}
}
