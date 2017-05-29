<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body><br>
<s:form action="departmentInfoAction" method="post">
<s:hidden name="department.orderBy"/>
<s:hidden name="department.descOrasc"/>
<input type="hidden" name="pagerAction" value="departmentInfoAction!execute.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
                <tr height="22">
						 			<TD width="10%">部门名称</TD>
			             <TD width="60%"><s:textfield id="name" name="department.name" cssClass="mytext" theme="simple"/></TD>   
			             <TD width="30%" align="right">
			             	 <div class="search"><a href="#" onclick="doSubmit()"><img src="<%=scontext%>/admin/images/search.gif" width="60" height="20" border="0"></a></div>
			             </TD>
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
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('departmentInfoAction','code');">编号<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('departmentInfoAction','name');">姓名<br></a></td>
										    </tr>
										    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
						    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
												      <td  class="list_content">
												      	<input type="checkbox" id="cbxItem" name="cbxItem" value='<s:property value="diId"/>' onclick="selectForCbxItem(this,'cbxAll')"/>
												      <br></td>
												      <td nowrap  class="list_content">
												      	<script>document.write(<s:property value="#status.index" />+1)</script>
												      <br></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF"><s:property value="code"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="name"/></td>
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
			document.forms[0].action="departmentInfoAction!toEditPage.action";
			document.forms[0].submit();
			return;
		}
		
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		var saction="departmentInfoAction!toEditPage.action?Ids="+svalue;
		if(nflag==3){
			saction="departmentInfoAction!deleteDepartment.action?Ids="+svalue;
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
		if(confirm("选择的记录将被删除，请确认")){
			document.forms[0].action="departmentInfoAction!deleteDepartment.action?Ids="+svalue;
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
			form.elements['department.orderBy'].value = columnName;
			if(form.elements['department.descOrasc'].value=="desc"){
				form.elements['department.descOrasc'].value = "asc";
			}else{
				form.elements['department.descOrasc'].value = "desc";
			}
			form.submit();
		}
 	
 }
</script>