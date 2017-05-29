<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
 String scontext=request.getContextPath();
%>
<link href="<%=scontext%>/admin/css/style.css" rel="stylesheet" type="text/css"/>
<SCRIPT language="JavaScript" src="<%=scontext%>/admin/js/jquery.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=scontext%>/admin/js/control.js"></SCRIPT>
<script language="JavaScript" src="<%=scontext%>/admin/js/calendar2.js"></script>
<script language="JavaScript" src="<%=scontext%>/admin/js/bgcolor.js"></script>
<script language="JavaScript" src="<%=scontext%>/admin/js/json2.js"></script>
<script language="JavaScript" src="<%=scontext%>/admin/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<s:if test="%{Msg!=null}"><script>alert("${Msg}");</script></s:if>
<%
String userName = (String)session.getAttribute("juser");
if(userName==null || "".equals(userName)){
%>
<script type="text/javascript">
    alert("会话已失效,请重新登录!");
	top.location.href="<%=scontext%>/admin.jsp";	
</script>
<%}%>