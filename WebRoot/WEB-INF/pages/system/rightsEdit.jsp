<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body>
<s:form action="siteinfo" method="post">
<input type="hidden" name="pagerAction" value="siteinfo!execute.action">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="con">
  <tr>
    <td id="con_blue-topL">
  	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="con">
          <tr>
            <td width="32" height="36">&nbsp;</td>
            <td class="th_blue">采集站点维护</td>           
          </tr>
      </table>
    </td>
    <td width="7"><div id="con_blue-topR"></div></td>
  </tr>
  
  <tr>
  	 <td colspan="2">  	 	  
			  <table class="query">
			    <tr height="22">
			    	 <TD width="10%" align="center">站点名称</TD>
             <TD width="20%"><s:textfield id="siteName" name="siteInfo.tiSsitename" cssClass="mytext" theme="simple"/></TD>
             <TD width="10%">&nbsp;</TD>
             <TD width="20%">&nbsp;</TD>
             <TD width="10%">&nbsp;</TD>
             <TD width="20%">&nbsp;</TD>         
			    </tr>
			    <tr>
			    	<td colspan="6" style="text-align:right;">
			    		<input type="submit" name="cmdquery" value="查询" class="mybutton">
			    		<input type="button" name="cmdadd" value="新增" class="mybutton" onclick="doEdit(1)">
            </td>
			    </tr>
			  </table>
				
				<div width="100%" height="1">&nbsp;</div>
				
  		  <div  class="resultDiv">
    	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="result">
					   <tr align="center" class="result_head">
					   	  <td width="5%" class="result_head_dock">
					   	  	<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')"/>
					   	  </td>
					      <td nowrap class="result_head_dock" width="5%">序号</td>
					      <td nowrap class="result_head_dock" width="15%">网站名称</td>
					      <td nowrap class="result_head_dock" width="10%">网站类型</td>	
					      <td nowrap class="result_head_dock" width="20%">网址</td>
					      <td nowrap class="result_head_dock" width="15%">IP</td>					      									      
					      <td nowrap class="result_head_dock" width="12%">作者</td>
					      <td nowrap class="result_head_dock" width="10%">创建时间</td>
					      <td nowrap class="result_head_dock" width="12%">操作</td>
					    </tr>
					    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
	    		   	   <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
							      <td nowrap class="result_content">
							      	<input type="checkbox" id="cbxItem" name="cbxItem" value='<s:property value="id"/>' onclick="selectForCbxItem(this,'cbxAll')"/>
							      </td>
							      <td nowrap class="result_content" width="5%">
							      	<script>document.write(<s:property value="#status.index" />+1)</script>
							      </td>
							      <td nowrap class="result_content"><a href="javascript:doSearchSiteView()"><s:property value="tiSsitename"/></a></td>
							      <td nowrap class="result_content">&nbsp;<s:property value="siteType"/></td>
							      <td nowrap class="result_content">&nbsp;<s:property value="tiSurl"/></td>
							      <td nowrap class="result_content">&nbsp;<s:property value="tiSip"/></td>
							      <td nowrap class="result_content">&nbsp;<s:property value="creater"/></td>
							      <td nowrap class="result_content">&nbsp;<s:property value="createdate"/></td>
							      <td nowrap class="result_content">
							      	<img src="<%=scontext%>/admin/images/modily.gif" onclick="doEdit(2)" alt="修改">&nbsp;&nbsp;
							      	<img src="<%=scontext%>/admin/images/delete.gif" onclick="doEdit(3)" alt="删除">
							      </td>
						     </tr>
	            </s:iterator>
					 </table>
		 		</div>
				<div>
				  	 <%@include file="/admin/commons/pageHibernate.jsp"%>
				</div>
    </td>
  </tr>
  
</table>
</s:form>
</body>
</html>
<script language="javascript">
	function doSearchSiteView()
	{
		
	}
	
	function doEdit(nflag)
	{
		if(nflag==1){
			document.forms[0].action="siteinfo!toEditPage.action";
			document.forms[0].submit();
			return;
		}
		
		var svalue=getCbxValue(document.all.cbxItem);
		if(svalue=="" || svalue.indexOf(",")!=-1) {
		  alert("请选择一条记录。");
		  return;	
		}
		var saction="siteinfo!toEditPage.action?siteIds="+svalue;
		if(nflag==3){
			saction="siteinfo!deleteSite.action?siteIds="+svalue;
		}
		document.forms[0].action=saction;
		document.forms[0].submit();
	}
	
</script>