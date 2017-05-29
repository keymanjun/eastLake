<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body><br>
<s:form action="employeeInfoAction" method="post">
<input type="hidden" id="pagerAction" name="pagerAction" value="employeeInfoAction!execute.action">
<s:hidden name="employeeInfo.orderBy"/>
<s:hidden name="employeeInfo.descOrasc"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr >
					 <td width="6%" class="filter">姓名 ：</td>
					 <td width="14%" class="filter"><s:textfield id="username" name="employeeInfo.username" cssClass="mytext" theme="simple"/></td>
					 <td width="6%" class="filter">性别 ：</td>
					 <td width="14%" class="filter"><s:select id="sex" label="sex" name="employeeInfo.sex" headerKey="-1" headerValue="请选择"
										   list="#{'1':'男', '2':'女'}" theme="simple" value="employeeInfo.sex" style="width:100px"/></td>
					 <td width="6%" class="filter">出生年月 ：</td>
					 <td width="14%" class="filter"><s:textfield id="birthday" name="employeeInfo.birthday" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/></td>
					 <td width="6%" class="filter">籍贯 ：</td>
					 <td width="14%" class="filter"><s:textfield id="natives" name="employeeInfo.natives" cssClass="mytext" theme="simple"/></td>
					 <td width="6%" class="filter">民族 ：</td>
					 <td width="14%" class="filter"><s:textfield id="nation" name="employeeInfo.nation" cssClass="mytext" theme="simple"/></td>
				</tr>
				<tr >
					 <td width="6%" class="filter">政治面貌 ：</td>
					 <td width="14%" class="filter"><s:select id="politics" label="politics" name="employeeInfo.politics" headerKey="-1" headerValue="请选择"
										   list="#{'1':'团员', '2':'党员'}" theme="simple" value="employeeInfo.politics" style="width:100px"/></td>
					 <td width="6%" class="filter">毕业学校 ：</td>
					 <td width="14%" class="filter"><s:textfield id="shool" name="employeeInfo.school" cssClass="mytext" theme="simple"/></td>
					 <td width="6%" class="filter">上岗时间 ：</td>
					 <td width="14%" class="filter" colspan="5">从<s:textfield id="onboardTime" name="employeeInfo.onboardTime" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
					 到 <s:textfield id="onboardTimeEnd" name="employeeInfo.onboardTimeEnd" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/></td>
				</tr>
				<tr>
					 <td width="100%" align="right" colspan="10">
						 <div class="search"><a href="#" onclick="doSubmit()"><img src="<%=scontext%>/admin/images/search.gif" width="60" height="20" border="0"></a>
						 </div>
					</td>
				</tr>
				</table>
              
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<%@include file="/admin/commons/pageToolBar.jsp"%>	
                  </td>
                </tr>
                <tr>
                  <td>
                  	<div id="listdiv" class="listdiv">
	                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr align="center">
										   	  <td width="5%" class="list_title">
										   	  	<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')"/>
										   	  <br></td>
										      <td class="list_title" nowrap width="5%">序号<br></td>
										      <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','username');">姓名<br></a></td>
										      <td class="list_title" nowrap width="5%"><a href=# onclick="javascript:orderBy('employeeInfoAction','sex');">性别<br></a></td>				      									      
										      <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','birthday');">出生年月<br></a></td>
										      <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','natives');">籍贯<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','nation');">民族<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','politics');">政治面貌<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','school');">毕业学校<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('employeeInfoAction','onboardTime');">上岗时间<br></a></td>
										    </tr>
										    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
						    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
												      <td  class="list_content">
												      	<input type="checkbox" id="cbxItem" name="cbxItem" value='<s:property value="eiId"/>' onclick="selectForCbxItem(this,'cbxAll')"/>
												      <br></td>
												      <td nowrap  class="list_content">
												      	<script>document.write(<s:property value="#status.index" />+1)</script>
												      <br></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="username"/></td>
												      	<s:if test="sex==2">
												      		<td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;女</td>
												      	</s:if>
												        <s:else>
												      		<td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;男</td>
												      	</s:else>
												     
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="birthday"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="natives"/></td>
													  <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="nation"/></td>
													  <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="politicsString"/></td>
													  <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="school"/></td>
													  <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="onboardTime"/></td>
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
		if(nflag==1){
			document.forms[0].action="employeeInfoAction!toEditPage.action";
			document.forms[0].submit();
			return;
		}
		
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		var saction="employeeInfoAction!toEditPage.action?Ids="+svalue;
		if(nflag==3){
			saction="employeeInfoAction!deleteEmployee.action?Ids="+svalue;
		}
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
		if(confirm("您确认删除选中的记录吗？")){
			document.forms[0].action="employeeInfoAction!deleteEmployee.action?Ids="+svalue;
			document.forms[0].submit();
		}
	}
	
	function doSubmit()
	{
	  document.forms[0].submit();	
	}
	function orderBy(thisForm,columnName){
 	var form;
 	if(typeof(thisForm)=='string'){
 		form = document.getElementById(thisForm);
 		form.elements['employeeInfo.orderBy'].value = columnName;
 		if(form.elements['employeeInfo.descOrasc'].value=="desc"){
 			form.elements['employeeInfo.descOrasc'].value = "asc";
 		}else{
 			form.elements['employeeInfo.descOrasc'].value = "desc";
 		}
 		form.submit();
 	}
 	
 }
</script>