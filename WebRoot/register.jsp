<%@ page contentType="text/html; charset=utf-8"%>
<%
  String contextPath=request.getContextPath();
%>
<html>
<head>
<title>欢迎登录武汉东湖学院普通专升本报名系统</title>
<link rel="stylesheet" href="<%=contextPath%>/admin/css/style.css" type="text/css">
<SCRIPT language="JavaScript" src="<%=contextPath%>/admin/js/control.js"></SCRIPT>
<%if(request.getAttribute("msg")!=null){%>
<script>alert('<%=request.getAttribute("msg")%>');</script>
<%} %>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" align="center" onkeydown="doSubmit()">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td>
	   <table width="593" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td height="65" background="<%=contextPath%>/admin/images/login_top.gif">
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td width="25">　</td>
		            <td height="54" valign="bottom" align="center">
		            	<h1 style="font-size:180%">武汉东湖学院普通专升本报名系统</h1>
		            </td>
		          </tr>
					  </table>
					</td>
				</tr>
				<tr>
				  <td height="277" align="center" background="<%=contextPath%>/admin/images/login_middle.jpg">
				  	 <table width="247" border="0" cellpadding="3" cellspacing="4">
				        <form name="logonform" action="<%=contextPath%>/userAction!saveUser.action" method="post">
				          <tr>
				            <td width="65" nowrap><strong><font color="#000000">帐号：</font></strong></td>
				            <td width="142"> <input type="text" id="useraccount" name="user.useraccount" class="mytext" style="width:140px;" maxlength="20"/></td>
				          </tr>
				          <tr>
				            <td nowrap><strong><font color="#000000">密码：</font></strong></td>
				            <td><input type="password" id="userpwd" name="user.userpwd" class="mytext" style="width:140px;" maxlength="20"/></td>
				          </tr>
				           <tr>
				            <td nowrap><strong><font color="#000000">密码确认：</font></strong></td>
				            <td><input type="password" id="j_password" name="j_password" class="mytext" style="width:140px;" maxlength="20"/></td>
				          </tr>
				           <tr>
				            <td nowrap><strong><font color="#000000">邮箱地址：</font></strong></td>
				            <td><input type="text" id="useremail" name="user.useremail" class="mytext" style="width:140px;" maxlength="20"/></td>
				          </tr>
				          <tr>
				            <td align="center" colspan="2">
				              <input type="button" name="Submit22" class="mybutton" value="注册提交" onclick="doRegster()">
				              <input type="button" name="Submit22" class="mybutton" value="返回" onclick="goBack()">
				            </td>
				          </tr>
				        </form>
				     </table>
				   </td>
				</tr>
				<tr>
				  <td height="68" valign="bottom" background="<%=contextPath%>/admin/images/login_bottom.gif">
				  	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				      <tr>
				        <td height="30" align="center" valign="top">
				        	<font face="Arial, Helvetica, sans-serif">&nbsp;</font>&nbsp;&nbsp;&nbsp;<a href="#" class="syslink2"></a>
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
	function validTextValue()
	{
	  var sMsg="";
	  if(getElem("useraccount").value.trim().length<1){
	     	sMsg+="帐号不能为空。\n";
	  }
	  if(getElem("useraccount").value.trim().length>20){
	     	sMsg+="帐号最大字符长度不能超过20。\n";
	  }
	  
	  if(getElem("userpwd").value.trim().length<1){
	     	sMsg+="密码不能为空。\n";
	  }
	  if(getElem("userpwd").value.trim().length>20){
	     	sMsg+="密码最大字符长度不能超过20。\n";
	  }
	  
	  if(getElem("j_password").value.trim().length<1){
	     	sMsg+="密码确认不能为空。\n";
	  }
	  if(getElem("j_password").value.trim().length>20){
	     	sMsg+="密码确认最大字符长度不能超过20。\n";
	  }
	  if(getElem("userpwd").value.trim()!=getElem("j_password").value.trim()){
	     	sMsg+="您输入的密码与确认密码不相符，请重新输入。\n";
	  }
	  if(getElem("useremail").value.trim().length<1){
	     	sMsg+="邮箱不能为空。\n";
	  }else{
		  if(getElem("useremail").value.trim().length>50){
		     	sMsg+="邮箱最大字符长度不能超过50。\n";
		  }
		  if(!checkEmail(getElem("useremail").value.trim())){
			  sMsg+="邮件的格式不正确。\n";
		  }
	  }
	  return sMsg;
	}
	function doRegster()
	{
		var msg=validTextValue();
		if(msg!=""){
		  alert(msg);
		  return;	
		}
	document.forms[0].action="userAction!saveRegisterUser.action";
	document.forms[0].submit();
	}
	
	function doSubmit()
	{
		if(event.keyCode==13){
			doRegster();
		}
	}
	function goBack(){
		window.location.href="admin.jsp";
	}
	</script>