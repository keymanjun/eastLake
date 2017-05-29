package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class EnrollGenerateExamNoEntity extends BaseEntity {

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 5097151926349658229L;
	private Long id;
	private String majorNo;
	private Integer roomNo;
	private Integer seatNo;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMajorNo() {
		return majorNo;
	}
	public void setMajorNo(String majorNo) {
		this.majorNo = majorNo;
	}
	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	public Integer getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}
	
	
	
	
	
	
}
