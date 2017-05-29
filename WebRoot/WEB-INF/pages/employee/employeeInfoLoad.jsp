﻿<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
<SCRIPT language="JavaScript" src="<%=scontext%>/admin/js/filename.js"></SCRIPT>
</head>
<body>
<s:form action="employeeInfoAction" method="post" enctype="multipart/form-data">
<input type="hidden" name="employeeInfo.status" value="1"/>
<input type="hidden" name="fname" value=""/>
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
                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit()">导入</a></td>
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
                        <td width="62%">员工信息导入</td>
                        <td width="38%" align="right"><img src="<%=scontext%>/admin/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div  style="padding-top:10px; padding-bottom:10px;">
		                   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="10%" align="center">请选择文件导入<font color="red">(*)</font></td>
									<td  width="15%" align="left">
										<s:file id="fileName" name="fileName" cssClass="mytext" theme="simple"/>
									</td>
								</tr>
							</table>		                    
		                </div>		                
                  </td>
                </tr>
              </table>
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
		
	function validTextValue()
	{
	  var sMsg="";
	  if(getElem("fileName").value.trim().length<1){
	     	sMsg+="请选择文件\n";
	  }
	  return sMsg;
	}
	function isExcel(file){
		if(!file) return false;
		if(file.indexOf("xls") == -1){
			alert("只能上传EXCEL文件，请重新选择");
			return false;
		}
		return true;
	}
	function doSubmit()
	{
		var filename = getElem("fileName");
		var fname = document.forms[0].fname;
		var msg=validTextValue();
		if(msg!=""){
		  alert(msg);
		  return;	
		}
		if(isExcel(filename.value.trim())){
			if (navigator.userAgent.indexOf("Firefox")!=-1) {
				fname.value = readFileFirefox(filename); 
			} else{
				fname.value = filename.value;
			}
			document.forms[0].action="employeeInfoAction!loadEmployeeInfo.action";
			document.forms[0].submit();
		}
	}
	
	function toGoBack()
	{	
	  document.forms[0].action="employeeInfoAction!goBack.action";
	  document.forms[0].submit();
	}
	
</script>