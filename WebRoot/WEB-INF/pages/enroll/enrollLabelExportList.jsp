<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body><br>
<s:form action="enrollApproveAction" method="post">
<s:hidden name="enrollStudent.orderBy"/>
<s:hidden name="enrollStudent.descOrasc"/>
<s:hidden name="enrollStudent.enrollStatus"/>
<input type="hidden" id="pagerAction" name="pagerAction" value="enrollApproveAction!toExportLabel.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
                <tr height="22">
					 <TD width="10%">报考专业</TD>
		             <TD width="60%">
		             	<s:select id="targetMajor" label="targetMajor" name="enrollStudent.targetMajorCode" headerKey="-1" headerValue="请选择"
				   			list="#request.majorHash" theme="simple" style="width:150px" onchange="doSubmit()"/></TD> 
				</tr>
              </table>
              <%String sContext = request.getContextPath(); %>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		               <td width="52%">学生报名信息列表</td>
		               <td width="48%">
		               	<table border="0" align="right" cellpadding="0" cellspacing="0">
		                   <tr>
		                    <td width="10%"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
		                    <td width="90%"><a href="#" onclick="doExport()">导出考试标签</a><br></td>
		                   </tr>
		                  </table>
		               <br></td>
		              </tr>
		            </table>
                  </td>
                </tr>
                <tr>
                  <td>
                  	<div id="listdiv" class="listdiv">
	                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr align="center">
						      <td class="list_title" nowrap width="5%">序号<br></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','no');">学号<br></a></td>
							  <td class="list_title" nowrap width="5%"><a href=# onclick="javascript:orderBy('enrollApproveAction','name');">姓名<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','status');">状态<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','nation');">民族<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','politicalType');">政治面貌<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','phone');">联系电话<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','schoolName');">所在学校姓名<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','major');">所在专业<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','targetMajor');">报考专业<br></a></td>
							  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','email');">邮箱地址<br></a></td>
						    </tr>
						    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
			    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
							      <td nowrap  class="list_content">
							      	<script>document.write(<s:property value="#status.index" />+1)</script>
							      <br></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="no"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="name"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="enrollStatusName"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="nation"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="politicalType"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="phone"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="schoolName"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="major"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="targetMajor"/></td>
							      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="email"/></td>
								</tr>
				            </s:iterator>
	                  	</table>
                    </div>
                  </td>
                </tr>
              </table>
	            <div>
	                <%@include file="/admin/commons/pageHibernate.jsp"%>
	            </div>
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
	function doExport()
	{
		var selectNode = document.getElementById("targetMajor");
		if(selectNode.value==-1) {
		  alert("请选择报考专业。");
		  selectNode.focus();
		  return;	
		}
		if(confirm("是否导出学生的报名标签?")){
			var saction="enrollApproveAction!exportLabel.action";
			document.forms[0].action=saction;
			document.forms[0].submit();
		}
	}
	
	function doSubmit()
	{
		var saction="enrollApproveAction!toExportLabel.action";
		document.forms[0].action=saction;
		document.forms[0].submit();	
	}
	function orderBy(thisForm,columnName)
	{
		var form;
		if(typeof(thisForm)=='string'){
			form = document.getElementById(thisForm);
			form.elements['enrollStudent.orderBy'].value = columnName;
			if(form.elements['enrollStudent.descOrasc'].value=="desc"){
				form.elements['enrollStudent.descOrasc'].value = "asc";
			}else{
				form.elements['enrollStudent.descOrasc'].value = "desc";
			}
			form.action = form.elements['pagerAction'].value;
			form.submit();
		}
 }
</script>