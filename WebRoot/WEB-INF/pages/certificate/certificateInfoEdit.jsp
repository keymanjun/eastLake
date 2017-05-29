﻿﻿﻿﻿﻿<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/admin/commons/taglibs.jsp"%>
	<script>
	dojo.require("dijit.Dialog");
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.Button");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dijit.form.SimpleTextarea");
	dojo.require("dijit.form.DateTextBox");
    dojo.require("dijit.form.Button");
    dojo.require("dijit.layout.BorderContainer");
    dojo.require("eastLake.widgets.CertificatePartialList");
    dojo.addOnLoad(function(){
    	var employeeId = dojo.byId("certificateInfoAction_employeeInfo_eiId").value;
    	var certificatePartialList = new eastLake.widgets.CertificatePartialList({
    		employeeId:employeeId
    	},dojo.byId("list_container"));
    	certificatePartialList.initialize();
    });
	</script>
</head>
<body class="tundra">
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
							    </tr>
							  </table>
							</div>
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
										<s:form action="certificateInfoAction" method="post">
											<s:hidden name="employeeInfo.eiId"/>
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">
											   <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
											    <td  width="10%" align="center">编号</td>
											    <td  width="15%" align="left">&nbsp;
											            <s:property value="employeeInfo.code"/>
											    </td>
											    <td  width="10%" align="center">姓名<font color="red">(*)</font></td>
											    <td  width="15%" align="left">&nbsp;
											            <s:property value="employeeInfo.username"/>
											    </td>
											    <td  width="10%" align="center">性别<font color="red">(*)</font></td>
											    <td  width="15%" align="left"><s:if test="employeeInfo.sex==1">"男"</s:if><s:else>女</s:else>					
											    </td>
											    <td  width="10%" align="center">身份证号码</td>
											    <td  width="15%" align="left">&nbsp;
											            <s:property value="employeeInfo.identifie"/>
											    </td>
											        </tr>
											  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
											    <td  width="10%" align="center">出生日期</td>
											    <td  width="15%" align="left">&nbsp;
											            <s:property value="employeeInfo.birthday"/>
											    </td>
											    <td  width="10%" align="center">婚姻状况</td>
											    <td  width="15%" align="left">
											            <s:if test="employeeInfo.married==1">已婚</s:if><s:else>未婚</s:else>
											    </td>
											    <td  width="10%" align="center">民族</td>
											    <td  width="15%" align="left">&nbsp;
											            <s:property value="employeeInfo.nation"/>
											    </td>
											    <td  width="10%" align="center">籍贯</td>
											    <td  width="15%" align="left">&nbsp;
											            <s:property value="employeeInfo.natives"/>
											    </td>
											  </tr>
											 </table>
										 </s:form>
						            </div>
								       </br>    
						            <div id="list_container" width=98%></div>
								  </td>
						       </tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
<script language="javascript">
	function toGoBack()
	{	
	  document.forms[0].action="certificateInfoAction!goBack.action";
	  document.forms[0].submit();
	}
</script>