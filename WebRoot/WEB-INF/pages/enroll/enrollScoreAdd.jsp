<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body>
<s:form action="enrollScoreAction" method="post">
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
                <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(admin/images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit(2)">提交</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="70" height="27" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(admin/images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
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
                        <td width="62%">学生报名成绩录入</td>
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
									<td  width="12%" align="center">姓名</td>
									<td  width="25%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.name"/>
									</td>
									<td  width="10%" align="center">学号</td>
									<td  width="38%" align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.no"/>
									</td>
									<td  width="15%" align="left" rowspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<div><img id="uploadPreview" style="width:150px;height:180px" src="<%=scontext%>/<s:property value="enrollStudent.picPath"/>"/></div>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td   align="center">出生日期</td>
									<td   align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.brithDate"/>
									</td>
									<td  align="center">性别</td>
									<td   align="left">&nbsp;&nbsp;&nbsp;&nbsp;							
											<s:property value="enrollStudent.sexName"/>
									</td>
									<td   align="center">民族</td>
									<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.nation"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">政治面貌</td>
									<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.politicalType"/>
									</td>
									<td align="center">联系电话</td>
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
									<td align="center">身份证号</td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.identify"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">邮箱地址</td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.email"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在院校</td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.schoolName"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在院校代码</td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.schoolCode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在专业</td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.major"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">高考报名号</td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.gaokaoCode"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考专业</td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:property value="enrollStudent.targetMajor"/></td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考专业代码</td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:property value="enrollStudent.targetMajorCode"/>
									</td>
							  </tr>
							   <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
								   <s:iterator value="majorScoreList" status="status">
										<td  align="center">
											<s:property value="majorItemName"/>
										</td>
										<td  align="left">
											<s:hidden name="majorScoreList[%{#status.index}].majorItemName" value="%{majorScoreList[#status.index].majorItemName}"/>
											<s:textfield label="majorItemScore" name="majorScoreList[%{#status.index}].majorItemScore" value="%{majorScoreList[#status.index].majorItemScore}" cssClass="mytext" theme="simple" maxlength="20" onblur="isNumericVSPoint(this,5)"/>
										</td>
									</s:iterator>
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
	  for(var i=0;i<3;i++){
		  var idName = "enrollScoreAction_majorScoreList_"+i+"__majorItemScore";
		  var score = getElem(idName).value.trim();
		  if(score==""){
			  sMsg = "成绩不能为空";
			  //getElem(idName).focus();
		  }
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
	  document.forms[0].action="enrollScoreAction!saveEnrollScore.action";
	  document.forms[0].submit();
	}
	
	function toGoBack()
	{	
	  document.forms[0].action="enrollScoreAction!goBack.action";
	  document.forms[0].submit();
	}
	
</script>