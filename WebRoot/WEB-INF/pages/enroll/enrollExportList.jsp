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
<input type="hidden" id="pagerAction" name="pagerAction" value="enrollApproveAction!toExportEnroll.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
                <tr height="22">
					<td >审批状态</td>
		             <td>
		             	<select name="enrollStudent.enrollStatus" id="enrollStatus" style="width:150px">
						    <option value="-1">请选择</option>
						     <option value="1">待审批</option>
						    <option value="2">审批通过</option>
						    <option value="3">审批拒绝</option>
						</select>
		   			</td>
		   			<td >报考专业</td>
		             <td>
		             	<s:select id="targetMajor" label="targetMajor" name="enrollStudent.targetMajorCode" headerKey="-1" headerValue="请选择"
				   			list="#request.majorHash" theme="simple" style="width:150px"/>
		   			</td>
		   			</tr>
		   			<tr height="22">
						 <TD width="10%">学生姓名</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.name" cssClass="mytext" theme="simple"/></TD>
			             <TD width="10%">身份证号</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.identify" cssClass="mytext" theme="simple"/></TD>
			             <TD width="10%">联系电话</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.phone" cssClass="mytext" theme="simple"/></TD>
			             <TD width="10%">邮箱</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.email" cssClass="mytext" theme="simple"/></TD>   
			             <TD width="30%" align="right">
			             	 <div class="search"><a href="#" onclick="doSubmit()"><img src="<%=scontext%>/admin/images/search.gif" width="60" height="20" border="0"></a></div>
			             </TD>
								</tr>
				</tr>
              </table>
              <%String sContext = request.getContextPath(); %>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		               <td width="62%">学生报名信息列表</td>
		               <td width="38%">
		               	<table border="0" align="right" cellpadding="0" cellspacing="0">
		                   <tr>
		                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
		                    <td width="35"><a href="#" onclick="doExport()">导出</a><br></td>
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
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','identify');">身份证<br></a></td>
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
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="identify"/></td>
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
		if(confirm("是否导出学生的报名信息?")){
			var saction="enrollApproveAction!exportEnroll.action";
			document.forms[0].action=saction;
			document.forms[0].submit();
		}
	}
	
	function doSubmit()
	{
		var saction="enrollApproveAction!toExportEnroll.action";
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