<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>武汉东湖学院普通专升本报名系统</title>
<%
	String sContext = request.getContextPath(); 
%>
</head>
<frameset rows="34,*" frameborder="no" border="0" framespacing="0">
  <frame src="<%=sContext%>/admin/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
  <frame src="<%=sContext%>/rightsAction!getMenuListForRights.action" name="mainFrame" id="mainFrame" width="100%" scrolling="no" frameborder="0" allowtransparency="true" marginheight="0" marginwidth="0" />
  
</frameset>
<noframes><body>
</body>
</noframes></html>