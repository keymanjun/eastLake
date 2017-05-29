package com.application.entity.po;

import com.system.entity.po.BaseEntity;

public class EmployeeEntity extends BaseEntity{
	
	
	/**
	 * @param serialVersionUID long
	 */
	private static final long serialVersionUID = -8049422873741884153L;
	private Long eiId;
	private Long diId;
	private Integer status;
	private String code;
	private String username;
	private String photo;
	private Integer sex;
	private String birthday;
	//身份证号码
	private String identifie;
    //发证机关
	private String identifieOffce;
	private String identifieDate;
	private Integer married;
	private String nation;
	private String natives;
	private Integer politics;
	private String email;
	private String phone;
	private String address;
	private Integer position;
	private Integer jobTitle;
	private Double payroll;
	private Integer hireType;
	private Integer education;
	private String major;
	private String school;
	private String degree;
	private String onboardTime;
	private String onboardTimeEnd;
	private Integer personStatus;
	private String startDate;
	private String endDate;
	private String notes;
	private String registeredLocal;
	private Integer employeeComposition;
	
	private Integer firstEducation;
	private String firstMajor;
	private String firstSchool;
	private String firstDegree;
	
	private Integer secondEducation;
	private String secondMajor;
	private String secondSchool;
	private String secondDegree;
	
	private Integer thirdEducation;
	private String thirdMajor;
	private String thirdSchool;
	private String thirdDegree;
	
	private String departmentName;
	//教职工岗位
	private String teacherPosition;
	
	
	public EmployeeEntity(){}
	
	public EmployeeEntity(Long eiId){
		this.eiId = eiId;
	}
	public EmployeeEntity(String code,String username,Integer sex,String birthday,String natives,String nation,Integer politics,Integer firstEducation,String firstDegree,
			String firstSchool,String firstMajor,Integer education,String degree,String school,String major,Integer jobTitle,Integer position,String registeredLocal,
			String onboardTime,Integer employeeComposition,String departmentName){
		this.code = code;
		this.username = username;
		this.sex = sex;
		this.birthday = birthday;
		this.natives = natives;
		this.nation = nation;
		this.politics = politics;
		this.firstEducation = firstEducation;
		this.firstDegree = firstDegree;
		this.firstSchool = firstSchool;
		this.firstMajor = firstMajor;
		this.education = education;
		this.degree = degree;
		this.school = school;
		this.major = major;
		this.jobTitle = jobTitle;
		this.position = position;
		this.registeredLocal = registeredLocal;
		this.onboardTime = onboardTime;
		this.employeeComposition = employeeComposition;
		this.departmentName = departmentName;
	}
	public Long getEiId() {
		return eiId;
	}
	public void setEiId(Long eiId) {
		this.eiId = eiId;
	}
	public Long getDiId() {
		return diId;
	}
	public void setDiId(Long diId) {
		this.diId = diId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIdentifie() {
		return identifie;
	}
	public void setIdentifie(String identifie) {
		this.identifie = identifie;
	}
	public Integer getMarried() {
		return married;
	}
	public void setMarried(Integer married) {
		this.married = married;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNatives() {
		return natives;
	}
	public void setNatives(String natives) {
		this.natives = natives;
	}
	public Integer getPolitics() {
		return politics;
	}
	public String getPoliticsString() {
		String politicsString = "";
		switch(politics){
		case 1:
			politicsString = "团员";
			break;
		case 2:
			politicsString = "党员";
			break;
		default :
			politicsString = "团员";
			break;
		}
		return politicsString;
	}
	public void setPolitics(Integer politics) {
		this.politics = politics;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(Integer jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Double getPayroll() {
		return payroll;
	}
	public void setPayroll(Double payroll) {
		this.payroll = payroll;
	}
	public Integer getHireType() {
		return hireType;
	}
	public void setHireType(Integer hireType) {
		this.hireType = hireType;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getOnboardTime() {
		return onboardTime;
	}
	public void setOnboardTime(String onboardTime) {
		this.onboardTime = onboardTime;
	}
	public Integer getPersonStatus() {
		return personStatus;
	}
	public void setPersonStatus(Integer personStatus) {
		this.personStatus = personStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRegisteredLocal() {
		return registeredLocal;
	}

	public void setRegisteredLocal(String registeredLocal) {
		this.registeredLocal = registeredLocal;
	}

	public Integer getEmployeeComposition() {
		return employeeComposition;
	}

	public void setEmployeeComposition(Integer employeeComposition) {
		this.employeeComposition = employeeComposition;
	}

	public String getOnboardTimeEnd() {
		return onboardTimeEnd;
	}

	public void setOnboardTimeEnd(String onboardTimeEnd) {
		this.onboardTimeEnd = onboardTimeEnd;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getFirstEducation() {
		return firstEducation;
	}

	public void setFirstEducation(Integer firstEducation) {
		this.firstEducation = firstEducation;
	}

	public String getFirstMajor() {
		return firstMajor;
	}

	public void setFirstMajor(String firstMajor) {
		this.firstMajor = firstMajor;
	}

	public String getFirstSchool() {
		return firstSchool;
	}

	public void setFirstSchool(String firstSchool) {
		this.firstSchool = firstSchool;
	}

	public String getFirstDegree() {
		return firstDegree;
	}

	public void setFirstDegree(String firstDegree) {
		this.firstDegree = firstDegree;
	}

	public Integer getSecondEducation() {
		return secondEducation;
	}

	public void setSecondEducation(Integer secondEducation) {
		this.secondEducation = secondEducation;
	}

	public String getSecondMajor() {
		return secondMajor;
	}

	public void setSecondMajor(String secondMajor) {
		this.secondMajor = secondMajor;
	}

	public String getSecondSchool() {
		return secondSchool;
	}

	public void setSecondSchool(String secondSchool) {
		this.secondSchool = secondSchool;
	}

	public String getSecondDegree() {
		return secondDegree;
	}

	public void setSecondDegree(String secondDegree) {
		this.secondDegree = secondDegree;
	}

	public Integer getThirdEducation() {
		return thirdEducation;
	}

	public void setThirdEducation(Integer thirdEducation) {
		this.thirdEducation = thirdEducation;
	}

	public String getThirdMajor() {
		return thirdMajor;
	}

	public void setThirdMajor(String thirdMajor) {
		this.thirdMajor = thirdMajor;
	}

	public String getThirdSchool() {
		return thirdSchool;
	}

	public void setThirdSchool(String thirdSchool) {
		this.thirdSchool = thirdSchool;
	}

	public String getThirdDegree() {
		return thirdDegree;
	}

	public void setThirdDegree(String thirdDegree) {
		this.thirdDegree = thirdDegree;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTeacherPosition() {
		return teacherPosition;
	}

	public void setTeacherPosition(String teacherPosition) {
		this.teacherPosition = teacherPosition;
	}

	public String getIdentifieOffce() {
		return identifieOffce;
	}

	public void setIdentifieOffce(String identifieOffce) {
		this.identifieOffce = identifieOffce;
	}

	public String getIdentifieDate() {
		return identifieDate;
	}

	public void setIdentifieDate(String identifieDate) {
		this.identifieDate = identifieDate;
	}

	public Integer getSexIdbyString(String sex){
		Integer sexId = -1;
		if(sex!=null && !"".equals(sex)){
			if("男".equals(sex)){
				sexId = 1;
			} else if("女".equals(sex)){
				sexId = 2;
			} else{
				sexId = -1;
			}
		}
		return sexId;
	}
	public Integer getEducationIdbyString(String education){
		Integer educationId = -1;
		if(education!=null && !"".equals(education)){
			if("大专".equals(education)){
				educationId = 1;
			} else if("本科".equals(education)){
				educationId = 2;
			} else if("研究生".equals(education)){
				educationId = 3;
			} else if("博士".equals(education)){
				educationId  = 4;
			} else{
				educationId = -1;
			}
		}
		return educationId;
	}
	public String getEducationString(Integer education){
		String educationString = "";
		switch(education){
		case 1:
			educationString = "大专";
			break;
		case 2:
			educationString = "本科";
			break;
		case 3:
			educationString = "研究生";
			break;
		case 4:
			educationString = "博士";
			break;
		default:educationString = "未知";
		}
		return educationString;
	}
	public String getJobTitleString(){
		String jotTitleString = "";
		switch(this.getJobTitle()){
		case 1:
			jotTitleString = "正高";
			break;
		case 2:
			jotTitleString = "副高";
			break;
		case 3:
			jotTitleString = "中级";
			break;
		case 4:
			jotTitleString = "初级";
			break;
//		case 1:
//			jotTitleString = "教授";
//			break;
//		case 2:
//			jotTitleString = "副教授";
//			break;
//		case 3:
//			jotTitleString = "讲师";
//			break;
//		case 4:
//			jotTitleString = "助教";
//			break;
//		case 5:
//			jotTitleString = "研究员";
//			break;
//		case 6:
//			jotTitleString = "高级记者";
//			break;
//		case 7:
//			jotTitleString = "副译审";
//			break;
//		case 8:
//			jotTitleString = "高级工程师";
//			break;
//		case 9:
//			jotTitleString = "高级实验师";
//			break;
//		case 10:
//			jotTitleString = "高级讲师";
//			break;
//		case 11:
//			jotTitleString = "高级会计师";
//			break;
//		case 12:
//			jotTitleString = "高级技师";
//			break;
//		case 13:
//			jotTitleString = "金融经济师（中级）";
//			break;
//		case 14:
//			jotTitleString = "中教1级";
//			break;
//		case 15:
//			jotTitleString = "助理工程师";
//			break;
//		case 16:
//			jotTitleString = "助理经济师";
//			break;
//		case 17:
//			jotTitleString = "其他";
//			break;
		}
		return jotTitleString;
	}
	public Integer getJobTitleIdbyString(String jobTitle){
		Integer jobTitleId = -1;
		if(jobTitle!=null && !"".equals(jobTitle)){
			if("正高".equals(jobTitle)){
				jobTitleId = 1;
			} else if("副高".equals(jobTitle)){
				jobTitleId = 2;
			} else if("中级".equals(jobTitle)){
				jobTitleId = 3;
			} else if("初级".equals(jobTitle)){
				jobTitleId  = 4;
//			if("教授".equals(jobTitle)){
//				jobTitleId = 1;
//			} else if("副教授".equals(jobTitle)){
//				jobTitleId = 2;
//			} else if("讲师".equals(jobTitle)){
//				jobTitleId = 3;
//			} else if("助教".equals(jobTitle)){
//				jobTitleId  = 4;
//			} else if("研究员".equals(jobTitle)){
//				jobTitleId = 5;
//			} else if("高级记者".equals(jobTitle)){
//				jobTitleId = 6;
//			} else if("副译审".equals(jobTitle)){
//				jobTitleId  = 7;
//			} else if("高级工程师".equals(jobTitle)){
//				jobTitleId = 8;
//			} else if("高级实验师".equals(jobTitle)){
//				jobTitleId = 9;
//			} else if("高级讲师".equals(jobTitle)){
//				jobTitleId  = 10;
//			} else if("高级会计师".equals(jobTitle)){
//				jobTitleId = 11;
//			} else if("高级技师".equals(jobTitle)){
//				jobTitleId = 12;
//			} else if("金融经济师（中级）".equals(jobTitle)){
//				jobTitleId  = 13;
//			} else if("中教1级".equals(jobTitle)){
//				jobTitleId = 14;
//			} else if("助理工程师".equals(jobTitle)){
//				jobTitleId = 15;
//			} else if("助理经济师".equals(jobTitle)){
//				jobTitleId  = 16;
//			} else if("其他".equals(jobTitle)){
//				jobTitleId = 17;
			} else{
				jobTitleId = -1;
			}
		}
		return jobTitleId;
	}
	
	public String getPositionString(){
		String positionString = "";
		switch(this.getPosition()){
		case 1:
			positionString = "校职";
			break;
		case 2:
			positionString = "副校级";
			break;
		case 3:
			positionString = "正处职";
			break;
		case 4:
			positionString = "正处级";
			break;
		case 5:
			positionString = "院长";
			break;
		case 6:
			positionString = "副院长";
			break;
		case 7:
			positionString = "副处职";
			break;
		case 8:
			positionString = "副处级";
			break;
		case 9:
			positionString = "正科职";
			break;
		case 10:
			positionString = "正科级";
			break;
		case 11:
			positionString = "副科职";
			break;
		case 12:
			positionString = "副科级";
			break;
		default:
			positionString = "未知";
		}
		return positionString;
	}
	public Integer getPositionIdbyString(String position){
		Integer positionId = -1;
		if(position!=null && !"".equals(position)){
			if("校职".equals(position)){
				positionId = 1;
			} else if("副校级".equals(position)){
				positionId = 2;
			} else if("正处职".equals(position)){
				positionId = 3;
			} else if("正处级".equals(position)){
				positionId  = 4;
			} else if("院长".equals(position)){
				positionId = 5;
			} else if("副院长".equals(position)){
				positionId = 6;
			} else if("副处职".equals(position)){
				positionId  = 7;
			} else if("副处级".equals(position)){
				positionId = 8;
			} else if("正科职".equals(position)){
				positionId = 9;
			} else if("正科级".equals(position)){
				positionId  = 10;
			} else if("副科职".equals(position)){
				positionId = 11;
			} else if("副科级".equals(position)){
				positionId = 12;
			} else{
				positionId = -1;
			}
		}
		return positionId;
	}
	public String getEmployeeCompositionString(){
		String employeeCompositionString = "";
		switch(this.getEmployeeComposition()){
			case 1:
				employeeCompositionString = "校级领导";
				break;
			case 2:
				employeeCompositionString = "机关人员";
				break;
			case 3:
				employeeCompositionString = "教学单位管理人员";
				break;
			case 4:
				employeeCompositionString = "教学秘书";
				break;
			case 5:
				employeeCompositionString = "辅导员";
				break;
			case 6:
				employeeCompositionString = "教辅人员";
				break;
			case 7:
				employeeCompositionString = "二级单位（不含图书馆）";
				break;
			case 8:
				employeeCompositionString = "教师";
				break;
			default:
				employeeCompositionString = "未知";
		}
		return employeeCompositionString;
	}
	public Integer getEmployeeCompositionIdbyString(String employeeComposition){
		Integer employeeCompositionId = -1;
		if(employeeComposition!=null && !"".equals(employeeComposition)){
			if("校级领导".equals(employeeComposition)){
				employeeCompositionId = 1;
			} else if("机关人员".equals(employeeComposition)){
				employeeCompositionId = 2;
			} else if("教学单位管理人员".equals(employeeComposition)){
				employeeCompositionId = 3;
			} else if("教学秘书".equals(employeeComposition)){
				employeeCompositionId  = 4;
			} else if("辅导员".equals(employeeComposition)){
				employeeCompositionId = 5;
			} else if("教辅人员".equals(employeeComposition)){
				employeeCompositionId = 6;
			} else if("二级单位（不含图书馆）".equals(employeeComposition)){
				employeeCompositionId  = 7;
			} else if("教师".equals(employeeComposition)){
				employeeCompositionId = 8;
			} else{
				employeeCompositionId = -1;
			}
		}
		return employeeCompositionId;
	}
}
