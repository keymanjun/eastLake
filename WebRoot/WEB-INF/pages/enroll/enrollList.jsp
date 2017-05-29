<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body><br>
<s:form action="enrollAction" method="post">
<s:hidden name="enrollStudent.orderBy"/>
<s:hidden name="enrollStudent.descOrasc"/>
<input type="hidden" id="pagerAction" name="pagerAction" value="enrollAction!execute.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              
              <%String sContext = request.getContextPath(); %>
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		               <td width="62%">报名信息</td>
		                <s:if test = "#request.pagerResult.items!=null">
			               <s:if test = "#request.pagerResult.items[0].enrollStatus == 3">
				               <td width="38%">
				               	<table border="0" align="right" cellpadding="0" cellspacing="0">
				                   <tr>
				                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
				                    <td width="35"><a href="#" onclick="doEdit(2)">修改</a><br></td>
				                    <td width="18"><img src="<%=sContext%>/admin/images/del.gif" width="10" height="10"><br></td>
				                    <td width="35"><a href="#" onclick="doDelete()">删除</a><br></td>
				                   </tr>
				                  </table>
				               <br></td>
			               </s:if>
			               <s:if test = "#request.pagerResult.items[0].enrollStatus == 2">
				               <td width="38%">
				               	<table border="0" align="right" cellpadding="0" cellspacing="0">
				                   <tr>
				                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
				                    <td width="60"><a href="#" onclick="doExport()">导出准考证</a><br></td>
				                   </tr>
				                  </table>
				               <br></td>
			               </s:if>
		               </s:if>
		              </tr>
		            </table>
                  </td>
                </tr>
                <tr>
                  <td>
                  	<div id="listdiv" class="listdiv">
	                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr align="center">
		                   	 	<s:if test = "#request.pagerResult.items[0].enrollStatus == 3">
							   	  <td width="5%" class="list_title">
							   	  	<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')"/>
							   	  <br></td>
							   	  </s:if>
							      <td class="list_title" nowrap width="5%">序号<br></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','no');">学号<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','name');">姓名<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','enrollStatus');">状态<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','examCode');">考试号<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','examRoom');">考场地址<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','pocliticalType');">政治面貌<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','phone');">联系电话<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','schoolName');">所在学校姓名<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','major');">所在专业<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','targetMajor');">报考专业<br></a></td>
								  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollAction','email');">邮箱地址<br></a></td>
							    </tr>
							    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
				    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
				    		   	   		<s:if test = "#request.pagerResult.items[0].enrollStatus == 3">
									      <td  class="list_content">
									      	<input type="checkbox" id="cbxItem" name="cbxItem" value='<s:property value="id"/>' onclick="selectForCbxItem(this,'cbxAll')"/>
									      <br></td>
								        </s:if>
									      <td nowrap  class="list_content">
									      	<script>document.write(<s:property value="#status.index" />+1)</script>
									      <br></td>
									      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="no"/></td>
									      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="name"/></td>
									      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="enrollStatusName"/></td>
									      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="examCode"/></td>
									      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="examRoom"/></td>
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
	function doEdit(nflag)
	{
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		var saction="enrollAction!toEditPage.action?Ids="+svalue;
		if(nflag==3){
			saction="enrollAction!exportEnroll.action?Ids="+svalue;
		}
		document.forms[0].action=saction;
		document.forms[0].submit();
	}
	
	function doExport()
	{
		saction="enrollAction!exportEnroll.action";
		document.forms[0].action=saction;
		document.forms[0].submit();
	}
	function doDelete()
	{
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="") {
		  alert("请选择一条记录。");
		  return;	
		}
		if(confirm("选择的记录将被删除，请确认")){
			document.forms[0].action="enrollAction!deleteEnroll.action?Ids="+svalue;
			document.forms[0].submit();
		}
	}
	
	function doSubmit()
	{
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
			form.submit();
		}
 }
</script>