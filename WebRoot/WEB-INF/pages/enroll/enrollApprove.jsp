<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
<script language="JavaScript" src="<%=scontext%>/admin/js/md5.js"></script>
</head>
<body>
<s:form action="enrollApproveAction" method="post">
<s:hidden name="enrollStudent.id"/>
<s:hidden name="enrollStudent.targetMajorCode"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            	<div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit(2)">审批通过</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/cz.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit(3)">审批拒绝</a></td>
                      </tr>
                     </table>
                  </td>
                  <td width="70" height="27" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/back.gif" width="22" height="21"></td>
                        <td><a href="#" class="v1" onclick="toGoBack()">返回</a></td>
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
                        <td width="62%">学生报名信息审批</td>
                        <td width="38%" align="right"><img src="<%=scontext%>/admin/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div style="padding-top:10px; padding-bottom:10px;">
		                   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">
			                    <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
										<td align="center">申请创建日期</td>
										<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
											<s:property value="enrollStudent.commitDate"/>
										</td>
								</tr>
								<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="12%" align="center">姓名<font color="red">(*)</font></td>
									<td  width="25%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.name"/>
									</td>
									<td  width="10%" align="center">学号<font color="red">(*)</font></td>
									<td  width="38%" align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.no"/>
									</td>
									<td  width="15%" align="left" rowspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<div><img id="uploadPreview" style="width:150px;height:180px" src="<%=scontext%>/<s:property value="enrollStudent.picPath"/>"/></div>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td   align="center">出生日期<font color="red">(*)</font></td>
									<td   align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.brithDate"/>
									</td>
									<td  align="center">性别<font color="red">(*)</font></td>
									<td   align="left">&nbsp;&nbsp;&nbsp;&nbsp;							
											<s:property value="enrollStudent.sexName"/>
									</td>
									<td   align="center">民族<font color="red">(*)</font></td>
									<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.nation"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">政治面貌<font color="red">(*)</font></td>
									<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.politicalType"/>
									</td>
									<td align="center">联系电话<font color="red">(*)</font></td>
									<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.phone"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">家庭住址</td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.homeAddress"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">身份证号<font color="red">(*)</font></td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.identify"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">邮箱地址<font color="red">(*)</font></td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.email"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在院校<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.schoolName"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在院校代码<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.schoolCode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在专业<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.major"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">高考报名号<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.gaokaoCode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">报考院校<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.targetSchoolName"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考院校代码<font color="red">(*)</font></td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:property value="enrollStudent.targetSchoolCode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考专业<font color="red">(*)</font></td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:property value="enrollStudent.targetMajor"/></td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考专业代码<font color="red">(*)</font></td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:property value="enrollStudent.targetMajorCode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">在校期间受过何种奖励</td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.awardStuff"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">有何特长</td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.speciality"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">联系地址<font color="red">(*)</font></td>
									<td  align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.contactAddress"/>
									</td>
									<td  align="center">邮政编码<font color="red">(*)</font></td>
									<td  align="left" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.postcode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="20%" align="center">审批意见</td>
									<td  width="30%" align="left" colspan="6">
										<textarea id="approveComment" rows="4" cols="125" name="enrollStudent.approveComment"></textarea>
									</td>
							  </tr>
							</table>
		                </div>
		          </td>
                </tr>
              </table>
            </td>
          </tr>
      </table></td>
  </tr>
</table>
</s:form>
</body>
</html>
<script language="javascript">
		
	function validTextValue(flag)
	{
		
	  var sMsg="";
	  var approveComment = getElem("approveComment").value.trim();
	  if((flag==3)&&(approveComment.length<1)){
	     	sMsg+="当审批拒绝时，审批意见不能为空。\n";
	  }
	  if(approveComment.length > 500){
		  approveComment.substring(0,500);
		  sMsg+="审批意见长度不能超过500个字符。\n";
	  }
	  return sMsg;
	}
	function doSubmit(flag)
	{
		var msg=validTextValue(flag);
		if(msg!=""){
		  alert(msg);
		  return;	
		}
	  document.forms[0].action="enrollApproveAction!updateEnrollApprove.action?flag="+flag;
	  document.forms[0].submit();
	}
	
	function toGoBack()
	{	
	  document.forms[0].action="enrollApproveAction!goBack.action";
	  document.forms[0].submit();
	}
	
</script>