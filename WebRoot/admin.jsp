<%@ page contentType="text/html; charset=utf-8"%>
<%
  String contextPath=request.getContextPath();
%>
<html>
<head>
<title>欢迎登录武汉东湖学院普通专升本报名系统</title>
<link rel="stylesheet" href="<%=contextPath%>/admin/css/style.css" type="text/css">
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
				        <form name="logonform" action="<%=contextPath%>/rightsAction!checkLogin.action" method="post">
				          <tr>
				            <td width="65" nowrap><strong><font color="#000000">帐号：</font></strong></td>
				            <td width="142"> <input type="text" id="j_username" name="j_username" class="mytext" style="width:140px;"/></td>
				          </tr>
				          <tr>
				            <td nowrap><strong><font color="#000000">密码：</font></strong></td>
				            <td><input type="password" id="j_password" name="j_password" class="mytext" style="width:140px;"/></td>
				          </tr>
				          <tr>
				            <td>&nbsp;</td>
				            <td nowrap>
				            	<input type="button" name="Submit3" class="mybutton" value="登录" onclick="doLogin()">
				              <input type="button" name="Submit22" class="mybutton" value="注册" onclick="doRegster()">
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
<script language="JavaScript" src="<%=request.getContextPath()%>/admin/js/md5.js"></script>
<script language="javascript">
	function doLogin()
	{
		var uname=document.getElementById('j_username').value;
		var upwd=document.getElementById('j_password').value;
		if(uname.length<1  || upwd.length<1 ) return;
		//document.getElementById('j_password').value=hex_md5(upwd);
		document.logonform.submit();		
	}
	function doRegster()
	{
		window.location.href="register.jsp";
	}
	function doSubmit()
	{
		if(event.keyCode==13){
			doLogin();
		}
	}
	</script>