<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body>
<s:form action="userAction" method="post">
<s:hidden name="user.userid"/>
<s:hidden name="user.useraccount"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            	<div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="70" height="27" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/back.gif" width="22" height="21"></td>
                        <td><a href="#" class="v1" onclick="toGoBack()">返回</a></td>
                      </tr>
                    </table>
                  </td>
                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/cz.gif" width="22" height="21"></td>
                        <td><a href="#">重置</a></td>
                      </tr>
                    </table>
                  </td>
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
                        <td width="62%">用户信息</td>
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
														<td  width="30%" align="center">帐号<font color="red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<s:property value="user.useraccount"/>
														</td>
									      </tr>
									      
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
														<td  width="30%" align="center">密码<font color="red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<s:textfield id="userpwd" name="user.userpwd" cssClass="mytext" theme="simple"/>
														</td>
									      </tr>
									      <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
														<td  width="30%" align="center">邮箱<font color="red">(*)</font></td>
														<td  width="70%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
															<s:textfield id="useremail" name="user.useremail" cssClass="mytext" theme="simple"/>
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
</s:form>
</body>
</html>
<script language="javascript">
	
	
	function validTextValue()
	{
	  var sMsg="";
	  if(getElem("userpwd").value.trim().length<1){
	     	sMsg+="密码不能为空。\n";
	  }
	  if(getElem("useremail").value.trim().length<1){
	     	sMsg+="邮箱不能为空。\n";
	  }
	  if(getElem("useremail").value.trim().length>50){
	     	sMsg+="邮箱最大字符长度不能超过50。\n";
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
		document.forms[0].action="userAction!saveUser.action";
	  document.forms[0].submit();
	}
	
	function toGoBack()
	{	
	  document.forms[0].action="userAction!goBack.action";
	  document.forms[0].submit();
	}
	
</script>