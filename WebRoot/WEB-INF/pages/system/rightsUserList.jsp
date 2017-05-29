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
                	&nbsp;&nbsp;角色名称：${role.rolename}&nbsp;&nbsp;&nbsp;&nbsp;角色描述：${role.roledesc}
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
    	    		 <td width="42%">
    	    		 	   <fieldset>
										 <legend>未分配用户</legend>
	    	    		 	   <div class="listdiv">
			    	    		 	  <table border="0" cellpadding="0" cellspacing="0" width="100%">			    	    		 	  	
			    	    		 	  		<tr align="center">
				    	    		 	  		 <td class="list_title" width="10%"><input type="checkbox" name="cbx11" onclick="selectForCbxAll(this,'cbx12')"></td>
									      			 <td class="list_title" width="25%">帐号</td>
									      			 <td class="list_title" width="30%">姓名</td>
									      			 <td class="list_title" width="30%">所属单位</td>
							    	    		</tr>			    	    		 	  		
				    	    		 	  	<s:iterator value="#request.item1" id="pageResult" status="status">
			    	    		 	  		<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
				    	    		 	  		<td class="list_content" nowrap bgcolor="#FFFFFF"><input type="checkbox" name="cbx12" value='<s:property value="userid"/>' onclick="selectForCbxItem(this,'cbx11')"></td>
											        <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="useraccount"/></td>
				    	    		 	  	  <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="username"/></td>
			    	    		 	  	    <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;</td>
			    	    		 	  	  </tr>
							    	    		</s:iterator>
			    	    		 	  </table>
			    	    		 	</div>
			    	    		</fieldset>
    	    		 	</td>
    	    		     	    		 
    	    		 <td width="4%" align="center">
    	    		 	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    	    		 	  	  <tr>
    	    		 	  	  	<td>&nbsp;&nbsp;&nbsp;<a href="#" onclick="javascript:doEditUserRole(1)" class="syslink">&nbsp;&nbsp;>>&nbsp;&nbsp;</a></td>
    	    		 	  	  </tr>
    	    		 	  	  <tr>
    	    		 	  	  	<td>&nbsp;&nbsp;&nbsp;<a href="#"onclick="javascript:doEditUserRole(2)" class="syslink">&nbsp;&nbsp;<<&nbsp;&nbsp;</a></td>
    	    		 	  	  </tr>
    	    		 	  </table>    	    		 	  
    	    		 </td>
    	    		 
    	    		 <td width="42%">
    	    		 	  <fieldset>
										 <legend>已分配用户</legend>
    	    		 	      <div class="listdiv">
				    	    		 	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
	    	    		 	  		  	 <tr align="center">
				    	    		 	  		 <td class="list_title" width="10%"><input type="checkbox" name="cbx21" onclick="selectForCbxAll(this,'cbx22')"></td>
									      			 <td class="list_title" width="25%">帐号</td>
									      			 <td class="list_title" width="30%">姓名</td>
									      			 <td class="list_title" width="30%">所属单位</td>
				    	    		 	  	</tr>
				    	    		 	  	<s:iterator value="#request.item2" id="pageResult" status="status">
				    	    		 	  		<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
					    	    		 	  		<td class="list_content" nowrap bgcolor="#FFFFFF"><input type="checkbox" name="cbx22" value='<s:property value="userid"/>' onclick="selectForCbxItem(this,'cbx21')"></td>
												        <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="useraccount"/></td>
				    	    		 	  	    <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="username"/></td>
				    	    		 	  	     <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;</td>
				    	    		 	  	  </tr>
				    	    		 	  	</s:iterator>
				    	    		 	  </table>
		    	    		 	  </div>
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
		
	//用户角色操作
	function doEditUserRole(flag)
	{		
		if(flag==1)
		{
			var svalue1=getCbxValue(document.all.cbx11);
			var svalue2=getCbxValue(document.all.cbx12);
			var ss=svalue1+","+svalue2;
			if(ss=="") {
			  alert("请至少选择一条用户新增。");
			  return;
			}
	  }
	  else{
	  	var svalue1=getCbxValue(document.all.cbx21);
			var svalue2=getCbxValue(document.all.cbx22);
			var ss=svalue1+","+svalue2;
			if(ss=="") {
			  alert("请至少选择一条用户移除。");
			  return;	
			}
	  }
		document.forms[0].action="rightsAction!editUserForRights.action?flag="+flag+"&Ids="+ss;
		document.forms[0].submit();
	}
	
	function goBack()
	{
		document.forms[0].action="rightsAction!goBack.action";
		document.forms[0].submit();
	}
</script>