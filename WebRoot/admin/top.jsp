<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String sContext = request.getContextPath(); 
%>
<link href="<%=sContext%>/admin/css/top.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="100%" height="34" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td align="right" background="./images/top_bj.jpg">
    	<table width="98%" border="0" cellspacing="0" cellpadding="0">
      <tr>
      <th scope="col" align="left">武汉东湖学院普通专升本报名系统</th>
        <th valign="bottom" scope="col">
        	<table width="98%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th align="right" scope="col">
            	<table width="100%" height="40" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <td align="right">
	                	<a href="<%=sContext%>/admin/passwordEdit.jsp" target="mainFrame" class="zi_nh">修改密码</a><span class="zi_nh"> |</span> &nbsp;
	                	<a href="#" class="zi_nh" onclick="exitLogin()">退出</a>
	                </td>
	                <td width="20">&nbsp;</td>
	              </tr>
              </table>
            </th>
          </tr>
        </table></th>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function exitLogin()
{
	if(confirm('系统提示：您确定要退出本次登录吗?'))
	{
		location.href = '<%=request.getContextPath()%>/rightsAction!loginOut.action';
	}
}
</script>