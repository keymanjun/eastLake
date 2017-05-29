<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<%
	String sContext = request.getContextPath(); 
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%= sContext%>/admin/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%= sContext%>/admin/js/themes/default/easyui.css" rel="stylesheet" type="text/css" />
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div>
</noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 2px;
        background: url(admin/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        
    </div>
         
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
			  <div class="easyui-accordion" fit="true" border="false">
				<!--  导航内容 -->
				
				</div>
    </div>
    
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden:width:100%;">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
					
		    </div>
    </div>
    
    <!-- page footer -->
    <div id="footer" region="south" split="true" style="height: 30px; background: #D2E0F2; " align="center">
       	<font> 武汉东湖学院计算机学院&copy;版权所有 开发者 赵莉</font>
    </div>
    
		<!--div id="mm" class="easyui-menu" style="width:150px; display:none;">
			<div id="mm-tabclose">关闭</div>
			<div id="mm-tabcloseall">全部关闭</div>
			<div id="mm-tabcloseother">除此之外全部关闭</div>
			<div class="menu-sep"></div>
			<div id="mm-exit">退出</div>
		</div-->
		
</body>
</html>
<script type="text/javascript" src="<%=sContext%>/admin/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=sContext%>/admin/js/jquery.easyui.pack.js"></script>
<script type="text/javascript" src='<%=sContext%>/admin/js/outlook2.js'></script>
<script language="javascript">
//init menus
setMenusJson(<%=request.getAttribute("menus")%>);
//init footer
</script>