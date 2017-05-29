<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body>
<s:form action="employeeInfoAction" method="post" enctype="multipart/form-data">
<input type="hidden" name="employeeInfo.status" value="1"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            	<div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="70" height="27" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/back.gif" width="22" height="21"></td>
                        <td><a href="#" class="v1" onclick="toGoBack()">返回</a></td>
                      </tr>
                    </table>
                  </td>
                  
                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit()">提交</a></td>
                      </tr>
                     </table>
                  </td>
                </tr>
              </table>
            </div>
            <div class="blankH10"></div>
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="62%">员工信息</td>
                        <td width="38%" align="right"><img src="<%=scontext%>/admin/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div  style="padding-top:10px; padding-bottom:10px;">
		                   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">编号</td>
									<td  width="15%" align="left">
										<s:textfield id="code" name="employeeInfo.code" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">姓名<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="username" name="employeeInfo.username" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">性别<font color="red">(*)</font></td>
									<td  width="15%" align="left">							
											 <s:select id="sex" label="sex" name="employeeInfo.sex" headerKey="-1" headerValue="请选择"
										   list="#{'1':'男', '2':'女'}" theme="simple" value="employeeInfo.sex" style="width:100px"/> 
									</td>
									<td  width="10%" align="center">所属部门<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="diId" label="diId" name="employeeInfo.diId" headerKey="-1" headerValue="请选择"
										   list="#request.departmentList" listKey="diId" listValue="name" theme="simple" 
										   value="employeeInfo.diId" style="width:100px"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">身份证号码</td>
									<td  width="15%" align="left">
										<s:textfield id="identifie" name="employeeInfo.identifie" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">发证机关</td>
									<td  width="15%" align="left">
										<s:textfield id="identifieOffce" name="employeeInfo.identifieOffce" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">身份证有效期</td>
									<td  width="15%" align="left">							
										<s:textfield id="identifieDate" name="employeeInfo.identifieDate" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>	 
									</td>
									<td  width="10%" align="center">教职工岗位</td>
									<td  width="15%" align="left">
										<s:textfield id="identifieDate" name="employeeInfo.teacherPosition" cssClass="mytext" theme="simple" />
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">出生日期<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="birthday" name="employeeInfo.birthday" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
									</td>
									<td  width="10%" align="center">婚姻状况</td>
									<td  width="15%" align="left">
										<s:select id="married" label="married" name="employeeInfo.married" headerKey="-1" headerValue="请选择"
										   list="#{'1':'已婚', '2':'未婚'}" theme="simple" value="employeeInfo.married" style="width:100px"/> 
									</td>
									<td  width="10%" align="center">民族<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="nation" name="employeeInfo.nation" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">籍贯<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="natives" name="employeeInfo.natives" cssClass="mytext" theme="simple" />
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">政治面貌<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="politics" label="politics" name="employeeInfo.politics" headerKey="-1" headerValue="请选择"
										   list="#{'1':'团员', '2':'党员'}" theme="simple" value="employeeInfo.politics" style="width:100px"/>
									</td>
									<td  width="10%" align="center">邮箱地址</td>
									<td  width="15%" align="left">
										<s:textfield id="email" name="employeeInfo.email" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">联系电话</td>
									<td  width="15%" align="left">
										<s:textfield id="phone" name="employeeInfo.phone" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">人员构成<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="employeeComposition" label="employeeComposition" name="employeeInfo.employeeComposition" headerKey="-1" headerValue="请选择"
										   list="#{'1':'校级领导', '2':'机关人员','3':'教学单位管理人员','4':'教学秘书','5':'辅导员','6':'教辅人员','7':'二级单位（不含图书馆）','8':'教师'}" theme="simple" value="employeeInfo.employeeComposition" style="width:100px"/>
									
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">联系地址</td>
									<td  width="15%" align="left" colspan="7">
										<s:textfield id="address" name="employeeInfo.address" cssClass="mytext" theme="simple" size="100"/>
									</td>
							  </tr>
							  
							  
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">职务级别<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="position" label="position" name="employeeInfo.position" headerKey="-1" headerValue="请选择"
										   list="#{'1':'校职', '2':'副校级','3':'正处职','4':'正处级','5':'院长','6':'副院长','7':'副处职','8':'副处级','9':'正科职','10':'正科级','11':'副科职','12':'副科级'}" theme="simple" value="employeeInfo.position" style="width:100px"/>
									</td>
									<td  width="10%" align="center">教师职称<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="jobTitle" label="jobTitle" name="employeeInfo.jobTitle" headerKey="-1" headerValue="请选择"
										   list="#{'1':'正高', '2':'副高','3':'中级','4':'初级'}" theme="simple" value="employeeInfo.jobTitle" style="width:100px"/>
									</td>
									<td  width="10%" align="center">聘用类型<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="hireType" label="hireType" name="employeeInfo.hireType" headerKey="-1" headerValue="请选择"
										   list="#{'1':'全职', '2':'兼职'}" theme="simple" value="employeeInfo.hireType" style="width:100px"/>
									</td>
									<td  width="10%" align="center">工资<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="payroll" name="employeeInfo.payroll" cssClass="mytext" theme="simple" onblur="formatAmount(this,2,1)"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">最高学历<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="education" label="education" name="employeeInfo.education" headerKey="-1" headerValue="请选择"
										   list="#{'1':'大专', '2':'本科','3':'研究生','4':'博士'}" theme="simple" value="employeeInfo.education" style="width:100px"/>
									</td>
									<td  width="10%" align="center">学位<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="degree" name="employeeInfo.degree" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">所学专业<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="major" name="employeeInfo.major" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">毕业院校<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="school" name="employeeInfo.school" cssClass="mytext" theme="simple" />
									</td>
								</tr>
								 <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">第一学历</td>
									<td  width="15%" align="left">
										<s:select id="firstEducation" label="firstEducation" name="employeeInfo.firstEducation" headerKey="-1" headerValue="请选择"
										   list="#{'1':'大专', '2':'本科','3':'研究生','4':'博士'}" theme="simple" value="employeeInfo.firstEducation" style="width:100px"/>
									</td>
									<td  width="10%" align="center">第一学位<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="firstDegree" name="employeeInfo.firstDegree" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">第一专业<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="firstMajor" name="employeeInfo.firstMajor" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">第一毕业院校<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="firstSchool" name="employeeInfo.firstSchool" cssClass="mytext" theme="simple" />
									</td>
								</tr>
								 <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">第二学历<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:select id="secondEducation" label="secondEducation" name="employeeInfo.secondEducation" headerKey="-1" headerValue="请选择"
										   list="#{'1':'大专', '2':'本科','3':'研究生','4':'博士'}" theme="simple" value="employeeInfo.secondEducation" style="width:100px"/>
									</td>
									<td  width="10%" align="center">第二学位<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="secondDegree" name="employeeInfo.secondDegree" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">第二专业<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="secondMajor" name="employeeInfo.secondMajor" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">第二毕业院校<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="secondSchool" name="employeeInfo.secondSchool" cssClass="mytext" theme="simple" />
									</td>
								</tr>
								<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">第三学历<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:select id="thirdEducation" label="thirdEducation" name="employeeInfo.thirdEducation" headerKey="-1" headerValue="请选择"
										   list="#{'1':'大专', '2':'本科','3':'研究生','4':'博士'}" theme="simple" value="employeeInfo.thirdEducation" style="width:100px"/>
									</td>
									<td  width="10%" align="center">第三学位<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="thirdDegree" name="employeeInfo.thirdDegree" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">第三专业<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="thirdMajor" name="employeeInfo.thirdMajor" cssClass="mytext" theme="simple" />
									</td>
									<td  width="10%" align="center">第三毕业院校<font color="red"></font></td>
									<td  width="15%" align="left">
										<s:textfield id="thirdSchool" name="employeeInfo.thirdSchool" cssClass="mytext" theme="simple" />
									</td>
								</tr>
								<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">合同期限<font color="red">(*)</font></td>
									<td  width="15%" align="left" colspan="3">
										从<s:textfield id="startDate" name="employeeInfo.startDate" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
										到<s:textfield id="endDate" name="employeeInfo.endDate" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
									</td>
									<td  width="10%" align="center">上岗日期<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:textfield id="onboardTime" name="employeeInfo.onboardTime" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
									</td>
									<td  width="10%" align="center">在职状态<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:select id="personStatus" label="personStatus" name="employeeInfo.personStatus" headerKey="-1" headerValue="请选择"
										   list="#{'1':'在职', '2':'离职'}" theme="simple" value="employeeInfo.personStatus" style="width:100px"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">户口所在地<font color="red">(*)</font></td>
									<td  width="15%" align="left" colspan="7">
										<s:textfield id="registeredLocal" name="employeeInfo.registeredLocal" cssClass="mytext" theme="simple" size="100"/>
									</td>
								</tr>
							  <tr>
								<td  width="10%" align="center">备注</td>
								<td  width="88%" align="left"colspan="7">
										<s:textarea id="notes" name="employeeInfo.notes" cssClass="mytext" theme="simple" rows="6" cols="150"/>
								</td>
							  </tr>
							  
						  </table>		                    
		                </div>	                
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table>
	 </td>
  </tr>
</table>
</s:form>
</body>
</html>
<script language="javascript">
		
	function validTextValue()
	{
	  var sMsg="";
	  if(getElem("username").value.trim().length<1){
	     	sMsg+="姓名不能为空\n";
	  }
	  if(getElem("username").value.trim().length>20){
	     	sMsg+="姓名最大字符长度不能超过20\n";
	  }
	  if(getElem("sex").value==-1){
	     	sMsg+="请选择性别\n";
	  }
	  if(getElem("diId").value == -1){
	     	sMsg+="请选择所属部门\n";
	  }
	  if(getElem("birthday").value.trim().length<1){
	     	sMsg+="出生日期不能为空\n";
	  }
	  if(getElem("nation").value.trim().length<1){
	     	sMsg+="民族不能为空\n";
	  }
	  if(getElem("natives").value.trim().length<1){
	     	sMsg+="籍贯不能为空\n";
	  }
	  if(getElem("politics").value==-1){
	     	sMsg+="请选择政治面貌\n";
	  }
	  if(getElem("employeeComposition").value==-1){
	     	sMsg+="请选择人员构成\n";
	  }
	  if(getElem("position").value==-1){
	     	sMsg+="请选择职务级别\n";
	  }
	  if(getElem("jobTitle").value==-1){
	     	sMsg+="请选择教师职称\n";
	  }
	  if(getElem("payroll").value <= 0){
	     	sMsg+="请输入正确的工资\n";
	  }
	  if(getElem("email").value.trim().length > 0){
	      if(getElem("email").value.trim().length>50){
				sMsg+="邮箱最大字符长度不能超过50\n";
		  }
		  if(!checkEmail(getElem("email").value.trim())){
				sMsg+="邮箱地址格式不正确\n"
		  }
	  }
	  if(getElem("phone").value.trim().length > 0){
		if (!validateObjValue(getElem("phone"),'tlePhone')){
		sMsg+="电话号码只允许为数字\n";
		}
	  }
	  if(getElem("position").value.trim().length<1){
	     	sMsg+="职位不能为空\n";
	  }
	  if(getElem("jobTitle").value.trim().length<1){
	     	sMsg+="职称不能为空\n";
	  }
	  if(getElem("payroll").value < 0){
	     	sMsg+="工资不能为空\n";
	  }
	  if(getElem("hireType").value==-1){
	     	sMsg+="请选择聘用类型\n";
	  }
	  if(getElem("education").value==-1){
	     	sMsg+="请选择最高学历\n";
	  }
	  if(getElem("degree").value.trim().length<1){
	     	sMsg+="学位不能为空\n";
	  }
	  if(getElem("major").value.trim().length<1){
	     	sMsg+="所学专业不能为空\n";
	  }
	  if(getElem("school").value.trim().length<1){
	     	sMsg+="毕业院校不能为空\n";
	  }
	  if(getElem("onboardTime").value.trim().length<1){
	     	sMsg+="上岗日期不能为空\n";
	  }
	  if(getElem("personStatus").value==-1){
	     	sMsg+="请选择在职状态\n";
	  }
	  if(getElem("startDate").value.trim().length<1){
	     	sMsg+="合同开始不能为空\n";
	  }
	  if(getElem("endDate").value.trim().length<1){
	     	sMsg+="合同结束时间不能为空\n";
	  }
	  if(getElem("startDate").value.trim().length > 0 && getElem("endDate").value.trim().length > 0){
		if(DateDiff(getElem("startDate").value.trim(),getElem("endDate").value.trim()) <= 0){
			sMsg+="合同结束时间不能早于合同开始时间\n";
		}
	  }
	  
	  if(getElem("registeredLocal").value.trim().length<1){
	     	sMsg+="户口所在地不能为空\n";
	  }
	  
	  return sMsg;
	}
	function doSubmit()
	{
		var msg=validTextValue();
		if(msg!=""){
		  alert(msg);
		  return;	
		}
	document.forms[0].action="employeeInfoAction!saveEmployee.action";
	  document.forms[0].submit();
	}
	
	function toGoBack()
	{	
	  document.forms[0].action="employeeInfoAction!goBack.action";
	  document.forms[0].submit();
	}
	
</script>