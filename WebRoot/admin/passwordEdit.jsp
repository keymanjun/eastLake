<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
 String scontext=request.getContextPath();
%>
<link href="<%=scontext%>/admin/css/style.css" rel="stylesheet" type="text/css"/>
<SCRIPT language="JavaScript" src="<%=scontext%>/admin/js/control.js"></SCRIPT>
<script language="JavaScript" src="<%=scontext%>/admin/js/md5.js"></script>

</head>
<body>
<form action="<%=scontext%>/rightsAction" method="post">
<input type="hidden" id="userpwd" name="userpwd" value="${acegiUser.userpwd}"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            	<div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>                  
                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit()">提交</a></td>
                      </tr>
                     </table>
                  </td>
                </tr>
              </table>
              </div>
              <div class="blankH10"></div>
              <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="62%">密码信息</td>
                        <td width="38%" align="right"><img src="<%=scontext%>/admin/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div  style="padding-top:10px; padding-bottom:10px;">
		                   <table width="90%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">									       
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
														<td  width="30%" align="center">旧密码<font style="color:red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="password" id="oldpwd" name="oldpwd" style="width:20%;"/>
														</td>
									      </tr>
									      
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
														<td  width="30%" align="center">新密码<font style="color:red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="password" id="newpwd" name="newpwd" style="width:20%;"/>
														</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
														<td  width="30%" align="center">确认密码<font style="color:red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<input type="password" id="newpwd1" name="newpwd1" style="width:20%;"/>
														</td>
									      </tr>
								      </table>
		                </div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
      </table></td>
  </tr>
</table>
</form>
</body>
</html>
<script language="javascript">
	function validTextValue()
	{
	  var sMsg="";
	  if(getElem("oldpwd").value.trim().length<1){
	     	sMsg+="旧密码不能为空。\n";
	  }
	  else
	  {
	  	if(getElem("oldpwd").value!=getElem("userpwd").value){
	     	sMsg+="旧密码输入不正确。\n";
	    }
	  }	  
	  
	  if(getElem("newpwd").value.trim().length<1){
	     	sMsg+="新密码不能为空。\n";
	  }
	  if(getElem("newpwd1").value.trim().length<1){
	     	sMsg+="确认密码不能为空。\n";
	  }
	  
	  if(getElem("newpwd").value!=getElem("newpwd1").value){
	     	sMsg+="新密码与确认密码不一致。\n";
	  }
	  return sMsg;
	}
	
	function doSubmit()
	{
		var msg=validTextValue();
		if(msg!=""){
		  alert(msg);
		  return;	
		}
		//getElem("newpwd").value=hex_md5(getElem("newpwd").value);
		document.forms[0].action="<%=scontext%>/rightsAction!savePassword.action";
	  document.forms[0].submit();
	}
</script>