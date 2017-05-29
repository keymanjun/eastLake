<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<%String sContext = request.getContextPath(); %>
    <title></title>
  </head>
  <body>
    		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
               <td width="62%">查询列表</td>
               <td width="38%">
               	<table border="0" align="right" cellpadding="0" cellspacing="0">
                   <tr>
                    <td width="18"><img src="<%=sContext%>/admin/images/add.gif" width="10" height="10"><br></td>
                    <td width="35"><a href="#" onclick="doEdit(1)">添加</a><br></td>
                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
                    <td width="35"><a href="#" onclick="doEdit(2)">修改</a><br></td>
                    <td width="18"><img src="<%=sContext%>/admin/images/del.gif" width="10" height="10"><br></td>
                    <td width="35"><a href="#" onclick="doDelete()">删除</a><br></td>
                   </tr>
                  </table>
               <br></td>
              </tr>
            </table>
  </body>
</html>
