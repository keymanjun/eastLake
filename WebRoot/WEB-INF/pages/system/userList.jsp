<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body><br>
<s:form action="userAction" method="post">
<input type="hidden" id="pagerAction" name="pagerAction" value="userAction!execute.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
                <tr height="22">
						 			<TD width="10%">用户名称</TD>
			             <TD width="60%"><s:textfield id="username" name="user.username" cssClass="mytext" theme="simple"/></TD>   
			             <TD width="30%" align="right">
			             	 <div class="search"><a href="#" onclick="doSubmit()"><img src="<%=scontext%>/admin/images/search.gif" width="60" height="20" border="0"></a></div>
			             </TD>
								</tr>
              </table>
              
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		               <td width="62%">查询列表</td>
		               <td width="38%">
		               	<table border="0" align="right" cellpadding="0" cellspacing="0">
		                   <tr>
		                    <td width="18"><img src="<%=scontext%>/admin/images/edit.gif" width="10" height="10"><br></td>
		                    <td width="35"><a href="#" onclick="doEdit(2)">修改</a><br></td>
		                    <td width="18"><img src="<%=scontext%>/admin/images/del.gif" width="10" height="10"><br></td>
		                    <td width="35"><a href="#" onclick="doDelete()">删除</a><br></td>
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
										   	  <td width="5%" class="list_title">
										   	  	<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')"/>
										   	  <br></td>
										      <td class="list_title" nowrap width="10%">序号<br></td>
										      <td class="list_title" nowrap width="30%">帐号<br></td>
										      <td class="list_title" nowrap width="30%">密码<br></td>
										      <td class="list_title" nowrap width="30%">邮箱<br></td>
										    </tr>
										    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
						    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
												      <td  class="list_content">
												      	<input type="checkbox" id="cbxItem" name="cbxItem" value='<s:property value="userid"/>' onclick="selectForCbxItem(this,'cbxAll')"/>
												      <br></td>
												      <td nowrap  class="list_content">
												      	<script>document.write(<s:property value="#status.index" />+1)</script>
												      <br></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF"><s:property value="useraccount"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="userpwd"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="useremail"/></td>
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
			document.forms[0].action="userAction!toEditPage.action";
			document.forms[0].submit();
			return;
		}
		
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		var saction="userAction!toEditPage.action?Ids="+svalue;
		if(nflag==3){
			saction="userAction!deleteUser.action?Ids="+svalue;
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
		document.forms[0].action="userAction!deleteUser.action?Ids="+svalue;
		document.forms[0].submit();
	}
	
	function doSubmit()
	{
	  document.forms[0].submit();	
	}
</script>