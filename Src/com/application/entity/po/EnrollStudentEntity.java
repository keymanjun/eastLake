package com.application.entity.po;

import java.util.HashSet;
import java.util.Set;

import com.system.entity.po.BaseEntity;

public class EnrollStudentEntity extends BaseEntity {

	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = 5097151926349658229L;
	private Long id;
	private Long userId;
	private Integer enrollStatus;
	private Integer scoreStatus;
	private String examCode = "";
	private String name;
	private String identify;
	private String no;
	private String picPath;
	private String brithDate;
	private Integer sex;
	private String nation;
	private String politicalType;
	private String phone;
	private String homeAddress;
	private String schoolName;
	private String schoolCode;
	private String major;
	private String gaokaoCode;
	private String targetSchoolName;
	private String targetSchoolCode;
	private String targetMajor;
	private String targetMajorCode;
	private String awardStuff;
	private String speciality;
	private String contactAddress;
	private String postcode;
	private String email;
	private String examRoom = "";
	private Integer registerType;
	private String approveComment;
	private String approveScoreComment;
	private String commitDate;//申请提交日期
	
	private Set<EnrollMajorScoreEntity> majorScore = new HashSet<EnrollMajorScoreEntity>();;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getEnrollStatus() {
		return enrollStatus;
	}
	public String getEnrollStatusName(){
		String returnString;
		switch(this.getEnrollStatus()){
		case 1: returnString = "待审批";
			break;
		case 2: returnString = "审批通过";
			break;
		case 3: returnString = "审批拒绝";
			break;
		default:returnString = "待审批";
			break;
		}
		return returnString;
	}
	public void setEnrollStatus(Integer enrollStatus) {
		this.enrollStatus = enrollStatus;
	}
	public String getExamCode() {
		return examCode;
	}
	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getBrithDate() {
		return brithDate;
	}
	public void setBrithDate(String brithDate) {
		this.brithDate = brithDate;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getSexName(){
		String returnString;
		switch(this.getSex()){
		case 1:returnString="男";
			break;
		case 2: returnString = "女";
			break;
		default:returnString = "请选择";
		}
		return returnString;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPoliticalType() {
		return politicalType;
	}
	public void setPoliticalType(String politicalType) {
		this.politicalType = politicalType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGaokaoCode() {
		return gaokaoCode;
	}
	public void setGaokaoCode(String gaokaoCode) {
		this.gaokaoCode = gaokaoCode;
	}
	public String getTargetSchoolName() {
		return targetSchoolName;
	}
	public void setTargetSchoolName(String targetSchoolName) {
		this.targetSchoolName = targetSchoolName;
	}
	public String getTargetSchoolCode() {
		return targetSchoolCode;
	}
	public void setTargetSchoolCode(String targetSchoolCode) {
		this.targetSchoolCode = targetSchoolCode;
	}
	public String getTargetMajor() {
		return targetMajor;
	}
	public void setTargetMajor(String targetMajor) {
		this.targetMajor = targetMajor;
	}
	public String getTargetMajorCode() {
		return targetMajorCode;
	}
	public void setTargetMajorCode(String targetMajorCode) {
		this.targetMajorCode = targetMajorCode;
	}
	public String getAwardStuff() {
		return awardStuff;
	}
	public void setAwardStuff(String awardStuff) {
		this.awardStuff = awardStuff;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRegisterType() {
		return registerType;
	}
	public void setRegisterType(Integer registerType) {
		this.registerType = registerType;
	}
	public String getApproveComment() {
		return approveComment;
	}
	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getExamRoom() {
		return examRoom;
	}
	public void setExamRoom(String examRoom) {
		this.examRoom = examRoom;
	}
	public String getCommitDate() {
		return commitDate;
	}
	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}
	public Integer getScoreStatus() {
		return scoreStatus;
	}
	public void setScoreStatus(Integer scoreStatus) {
		this.scoreStatus = scoreStatus;
	}
	public String getApproveScoreComment() {
		return approveScoreComment;
	}
	public void setApproveScoreComment(String approveScoreComment) {
		this.approveScoreComment = approveScoreComment;
	}
	public String getScoreStatusName(){
		String returnString;
		switch(this.getScoreStatus()){
		case 1: returnString = "待审批";
			break;
		case 2: returnString = "审批通过";
			break;
		case 3: returnString = "审批拒绝";
			break;
		default:returnString = "待审批";
			break;
		}
		return returnString;
	}
	public Set<EnrollMajorScoreEntity> getMajorScore() {
		return majorScore;
	}
	public void setMajorScore(Set<EnrollMajorScoreEntity> majorScore) {
		this.majorScore = majorScore;
	}
	public String toJsonString(){
		StringBuffer returnString = new StringBuffer();
		returnString.append("{");
		returnString.append("id:'"+id+"'");
		returnString.append(", examCode:'" + (examCode==null?"":examCode)+"'");
		returnString.append(", name:'"+name+"'");
		returnString.append(", identify:'"+identify+"'");
		returnString.append(", no:'"+no+"'");
		returnString.append(", picPath:'"+picPath+"'");
		returnString.append(", brithDate:'"+brithDate+"'");
		returnString.append(", sexName:'"+this.getSexName()+"'");
		returnString.append(", nation:'"+nation+"'");
		returnString.append(", politicalType:'"+politicalType+"'");
		returnString.append(", phone:'"+phone+"'");
		returnString.append(", homeAddress:'"+homeAddress+"'");
		returnString.append(", schoolName:'"+schoolName+"'");
		returnString.append(", schoolCode:'"+schoolCode+"'");
		returnString.append(", major:'"+major+"'");
		returnString.append(", gaokaoCode:'"+gaokaoCode+"'");
		returnString.append(", targetMajor:'"+targetMajor+"'");
		returnString.append(", targetMajorCode:'"+targetMajorCode+"'");
		returnString.append(", awardStuff:'"+awardStuff+"'");
		returnString.append(", speciality:'"+speciality+"'");
		returnString.append(", contactAddress:'"+contactAddress+"'");
		returnString.append(", postcode:'"+postcode+"'");
		returnString.append(", approveComment:'"+approveComment+"'");
		returnString.append(", email:'"+email+"'");
		returnString.append(", examRoom:'"+(examRoom==null?"":examRoom)+"'");
		returnString.append(", commitDate:'"+commitDate+"'");
		returnString.append(", postcode:'"+postcode+"'");
		returnString.append("}");
		return returnString.toString();
		
	}
}
