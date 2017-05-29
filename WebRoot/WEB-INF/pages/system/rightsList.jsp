<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body>
<s:form action="rightsAction" method="post">
<input type="hidden" name="pagerAction" value="rightsAction!execute.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
                <tr height="22">
						 <TD width="10%">角色名称</TD>
			             <TD width="60%"><s:textfield id="rolename" name="role.rolename" cssClass="mytext" theme="simple"/></TD>
			             <TD width="30%" align="right">
			             	 <div class="search"><a href="#" onclick="doSubmit()"><img src="<%=scontext%>/admin/images/search.gif" width="60" height="20" border="0"></a></div>
			             </TD>
				</tr>
              </table>
              
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<%@include file="/admin/commons/pageToolBarTitle.jsp"%>
                  </td>
                </tr>
                
                <tr>
                  <td>
                  	<div id="listdiv" class="listdiv">
	                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr align="center">
						      <td class="list_title" nowrap width="5%">序号<br></td>
						      <td class="list_title" nowrap width="10%">角色名称<br></td>
						      <td class="list_title" nowrap width="30%">角色描述<br></td>
						      <td class="list_title" nowrap width="10%">创建人<br></td>					      									      
						      <td class="list_title" nowrap width="10%">创建日期<br></td>
						      <td class="list_title" nowrap width="12%">操作</td>
						    </tr>
						 <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
		    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
								      <td nowrap  class="list_content">
								      	<script>document.write(<s:property value="#status.index" />+1)</script>
								      <br></td>
								      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="rolename"/></td>
								      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="roledesc"/></td>
								      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="creater"/></td>
								      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="createdate"/></td>
								      <td nowrap class="list_content">
								      	<a href="#" onclick="addUserForRole(${roleid})">分配用户</a>&nbsp;
								      	<a href="#" onclick="addMenuForRole(${roleid})">分配菜单</a>&nbsp;
								      </td>
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
	//分配用户
	function addUserForRole(roleId)
	{
		document.forms[0].action="rightsAction!toEditUserRolePage.action?roleId="+roleId;
		document.forms[0].submit();
	}
	
	//分配菜单
	function addMenuForRole(roleId)
	{
		document.forms[0].action="rightsAction!toEditRoleRightsPage.action?roleId="+roleId;
		document.forms[0].submit();
	}
	
	function doSubmit()
	{
		document.forms[0].submit();
	}
	
</script>