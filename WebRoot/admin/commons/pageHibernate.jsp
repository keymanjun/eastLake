<%@ page contentType="text/html; charset=utf-8"%>
<link href="<%=request.getContextPath()%>/admin/css/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/admin/js/pageHibernate.js"></script>
<%@ page import="com.framework.components.pager.PaginationSupport"%>
<%
	PaginationSupport pageResult=(PaginationSupport)request.getAttribute("pagerResult");	
%>
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="query_tb">
   <tr> 
    <td height="30" width="100%" colspan="2" align="right">
          <script>
             var sHtml1='';
             if(!isHasNextPage(<%=pageResult.getPageCount()%>))
             {
             	 sHtml1='';
             	 sHtml1+='<table border="0" align="right" cellpadding="0" cellspacing="0">';
               sHtml1+='<tr>';
               sHtml1+='<td width="50"><img src="<%=request.getContextPath()%>/admin/images/page_first_1.gif" width="48" height="20" /></td>';
               sHtml1+='<td width="60"><img src="<%=request.getContextPath()%>/admin/images/page_back_1.gif" width="55" height="20" /></td>';
               sHtml1+='<td width="60"><img src="<%=request.getContextPath()%>/admin/images/page_next_1.gif" width="58" height="20" /></td>';
               sHtml1+='<td width="60"><img src="<%=request.getContextPath()%>/admin/images/page_last_1.gif" width="52" height="20" /></td>';
               sHtml1+='<td width="30">页数</td>';
               sHtml1+='<td width="60" align="left"><font color="#993333"><%=pageResult.getCurrentPage()%>&nbsp;/&nbsp;<%=pageResult.getPageCount()%></font></td>';
               sHtml1+='</tr>';
               sHtml1+='</table>';
             }
             else
             {
             	 sHtml1='';
             	 sHtml1+='<table border="0" align="right" cellpadding="0" cellspacing="0">';
               sHtml1+='<tr>';
               sHtml1+='<td width="50"><a id="firstpage2" onclick="javascript:locatePage(1)" style="cursor:hand"><img src="<%=request.getContextPath()%>/admin/images/page_first.gif" width="48" height="20" /></a></td>';
               sHtml1+='<td width="60"><a id="lastpage2" onclick="locatePage(2)" style="cursor:hand"><img src="<%=request.getContextPath()%>/admin/images/page_back.gif" width="55" height="20" /></a></td>';
               sHtml1+='<td width="60"><a id="nextpage2" onclick="locatePage(3)" style="cursor:hand"><img src="<%=request.getContextPath()%>/admin/images/page_next.gif" width="58" height="20" /></a></td>';
               sHtml1+='<td width="60"><a id="endpage2" onclick="locatePage(4)" style="cursor:hand"><img src="<%=request.getContextPath()%>/admin/images/page_last.gif" width="52" height="20" /></a></td>';
               sHtml1+='<td width="30">页数</td>';
               sHtml1+='<td width="60" align="left"><font color="#993333"><%=pageResult.getCurrentPage()%>&nbsp;/&nbsp;<%=pageResult.getPageCount()%></font></td>';
            
              sHtml1+='<td>转第<input id="pageNo" name="currentPage" type="text" class="mynumbertext" value="<%=pageResult.getCurrentPage()%>" size="4"/>页';
              sHtml1+='<input name="Submit" type="button" value="跳转" class="mybutton" onClick="jump()">&nbsp;&nbsp;';
				      sHtml1+='</td>';
              
               sHtml1+='</tr>';
               sHtml1+='</table>';
             }
             document.write(sHtml1);             
          </script>
	  </td>
  </tr>
</table>
<input type="hidden" id="pageCount"  name="pageCount" value='<%=pageResult.getPageCount()%>'>
<input type="hidden" id="startIndex"  name="startIndex" value='<%=pageResult.getStartIndex()%>'>
<input type="hidden" id="pageSize" name="pageSize" value="<%=pageResult.getPageSize()%>" >