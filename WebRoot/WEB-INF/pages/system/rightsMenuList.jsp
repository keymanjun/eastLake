<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body>
<s:form action="rightsAction" method="post">
<s:hidden name="role.roleid"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="con">
  <tr>
    <td>
		    <div class="tool">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
              	<td width="95%">
                	&nbsp;&nbsp;角色名称：${role.rolename}&nbsp;&nbsp;&nbsp;&nbsp;角色描述：${role.roledesc}&nbsp;&nbsp;&nbsp;&nbsp;
                </td>
                <td width="70" height="27" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D';" onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td align="center"><img src="<%=scontext%>/admin/images/back.gif" width="22" height="21"></td>
                      <td><a href="#" class="v1" onclick="goBack()">返回</a></td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
         </div>
      </td>
  </tr>
  
  <tr>
  	 <td colspan="2">
    	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	    	<tr>
    								    	    		 	  					 		      
		 	    		 <td width="40%">
		    	    		  	<fieldset>
												 <legend>未分配菜单</legend>
						    	    	<iframe id="lefttree" name="lefttree" src="<%=scontext%>/rightsAction!getRoleRights.action?flag=left&roleid=${role.roleid}&sysmark=${sysmark}" width="100%" scrolling="no" frameborder="0" allowtransparency="true" marginheight="0" marginwidth="0" onload="document.all('lefttree').height=400;"></iframe>
				    	    		</fieldset>
    	    		 </td>
		    	    		 	
    	    		 <td width="4%" align="center">
    	    		 	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	    		 	  	  <tr>
    	    		 	  	  	<td>&nbsp;&nbsp;&nbsp;<a href="#" onclick="javascript:doEditMenuRole('addMenus')" class="syslink">&nbsp;&nbsp;>>&nbsp;&nbsp;</a></td>
    	    		 	  	  </tr>
    	    		 	  	  <tr>
    	    		 	  	  	<td>&nbsp;&nbsp;&nbsp;<a href="#"onclick="javascript:doEditMenuRole('delMenus')" class="syslink">&nbsp;&nbsp;<<&nbsp;&nbsp;</a></td>
    	    		 	  	  </tr>
    	    		 	  </table>
    	    		 	  
    	    		 </td>
    	    		 
    	    		 <td width="40%">
    	    		  	<fieldset>
										 <legend>已分配菜单</legend>
						    	   <iframe id="righttree" name="righttree" src="<%=scontext%>/rightsAction!getRoleRights.action?flag=right&roleid=${role.roleid}&sysmark=${sysmark}" width="100%" scrolling="no" frameborder="0" allowtransparency="true" marginheight="0" marginwidth="0" onload="document.all('righttree').height=400;"></iframe>
				    	    </fieldset>    	    		  	
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
 
  document.forms[0].sysmark.value='${sysmark}';
  
	//新增、移除菜单操作
	function doEditMenuRole(flag)
	{
		var svalue="";
		if(flag=='addMenus')
		{
			svalue=document.frames['lefttree'].window.getTreeCheckedValue();
			if(svalue=="") {
			  alert("请至少选择一条菜单新增。");
			  return;	
			}
	  }
	  else
	  {
	  	svalue=document.frames['righttree'].window.getTreeCheckedValue();
			if(svalue=="") {
			  alert("请至少选择一条菜单移除。");
			  return;
			}
	  }
	  var sysmark='${sysmark}';
		document.forms[0].action="rightsAction!editMenuForRights.action?sysmark="+sysmark+"&flag="+flag+"&Ids="+svalue;
		document.forms[0].submit();
	}
	
	function changeRightsMenu(svalue)
	{		
		document.forms[0].action="rightsAction!toEditRoleRightsPage.action?sysmark="+svalue;
		document.forms[0].submit();		
	}
	
	function goBack()
	{
		document.forms[0].action="rightsAction!goBack.action";
		document.forms[0].submit();
	}
 
</script>