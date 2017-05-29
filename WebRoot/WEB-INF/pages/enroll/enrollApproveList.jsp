<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
</head>
<body><br>
<s:form action="enrollApproveAction" method="post">
<s:hidden name="enrollStudent.orderBy"/>
<s:hidden name="enrollStudent.descOrasc"/>
<input type="hidden" id="pagerAction" name="pagerAction" value="enrollApproveAction!execute.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" >
                <tr height="22">
						 <TD width="10%">学生姓名</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.name" cssClass="mytext" theme="simple"/></TD>
			             <TD width="10%">身份证号</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.identify" cssClass="mytext" theme="simple"/></TD>
			             <TD width="10%">联系电话</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.phone" cssClass="mytext" theme="simple"/></TD>
			             <TD width="10%">邮箱</TD>
			             <TD width="15%"><s:textfield id="name" name="enrollStudent.email" cssClass="mytext" theme="simple"/></TD>   
			             <TD width="30%" align="right">
			             	 <div class="search"><a href="#" onclick="doSubmit()"><img src="<%=scontext%>/admin/images/search.gif" width="60" height="20" border="0"></a></div>
			             </TD>
								</tr>
              </table>
              <%String sContext = request.getContextPath(); %>
              <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		              <tr>
		               <td width="32%">审批查询列表</td>
		               <td width="68%">
		               	<table border="0" align="right" cellpadding="0" cellspacing="0">
		                   <tr>
		                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
		                    <td width="80"><a href="#" onclick="doEdit(1)">批量审批通过</a><br></td>
		                   
		                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
		                    <td width="81"><a href="#" onclick="doEdit(2)">批量审批拒绝</a><br></td>
		                   
		                    <td width="18"><img src="<%=sContext%>/admin/images/edit.gif" width="10" height="10"><br></td>
		                    <td width="81"><a href="#" onclick="doEdit(3)">审批</a><br></td>
		                   </tr>
		                  </table>
		               <br></td>
		              </tr>
		            </table>
                  </td>
                </tr>
                <tr>
                  <td>
                  	<div id="listdiv" class="listdiv">
	                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr align="center">
										   	  <td width="5%" class="list_title">
										   	  	<input type="checkbox" id="cbxAll" name="cbxAll" onclick="selectForCbxAll(this,'cbxItem')"/>
										   	  <br></td>
										      <td class="list_title" nowrap width="3%">序号<br></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','no');">学号<br></a></td>
											  <td class="list_title" nowrap width="5%"><a href=# onclick="javascript:orderBy('enrollApproveAction','name');">姓名<br></a></td>
											  <td class="list_title" nowrap width="5%"><a href=# onclick="javascript:orderBy('enrollApproveAction','status');">状态<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','identify');">身份证<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','email');">邮箱<br></a></td>
											  <td class="list_title" nowrap width="5%"><a href=# onclick="javascript:orderBy('enrollApproveAction','politicalType');">政治面貌<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','phone');">联系电话<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','schoolName');">所在学校姓名<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','major');">所在专业<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','targetMajor');">报考专业<br></a></td>
											  <td class="list_title" nowrap width="10%"><a href=# onclick="javascript:orderBy('enrollApproveAction','commitDate');">创建日期<br></a></td>
										    </tr>
										    <s:iterator value="#request.pagerResult.items" id="pageResult" status="status">					    		  
						    		   	   <tr align="center" onMouseOver="changeto()" onmouseout="changeback()">
												      <td  class="list_content">
												      	<input type="checkbox" id="cbxItem" name="cbxItem" value='<s:property value="id"/>' onclick="selectForCbxItem(this,'cbxAll')"/>
												      <br></td>
												      <td nowrap  class="list_content">
												      	<script>document.write(<s:property value="#status.index" />+1)</script>
												      <br></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<a href="javascript:popupDetail('<s:property value="id"/>')"><s:property value="no"/></a></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<a href="javascript:popupDetail('<s:property value="id"/>')"><s:property value="name"/></a></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="enrollStatusName"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<a href="javascript:popupDetail('<s:property value="id"/>')"><s:property value="identify"/></a></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="email"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="politicalType"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="phone"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="schoolName"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="major"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="targetMajor"/></td>
												      <td class="list_content" nowrap bgcolor="#FFFFFF">&nbsp;<s:property value="commitDate"/></td>
												</tr>
						            </s:iterator>
	                  	</table>
                    </div>
                  </td>
                </tr>
              </table>
	            <div>
	                <%@include file="/admin/commons/pageHibernate.jsp"%>
	            </div>
            </td>
          </tr>
        </table>
    </td>
  </tr>
</table>
</s:form>


<div id="dialogDetail" class="dialog">
    	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" id="edittb">
			  <tr align="center">
					<td  width="12%" align="center">姓名</td>
					<td  width="25%" align="left" id="popName"></td>
					<td  width="10%" align="center">学号</td>
					<td  width="38%" align="left" colspan="3" id="popNo"></td>
					<td  width="15%" align="left" rowspan="6" id="popPic"></td>
			  </tr>
			  <tr align="center">
					<td align="center">出生日期</td>
					<td align="left" id="popBirthDate"></td>
					<td align="center">性别</td>
					<td align="left" id="popsexName"></td>
					<td align="center">民族</td>
					<td align="left" id="popNation"></td>
			  </tr>
			  <tr align="center">
					<td  align="center">政治面貌</td>
					<td  align="left" id="popPoliticalType"></td>
					<td align="center">联系电话</td>
					<td align="left" colspan="3" id="popPhone"></td>
			  </tr>
			  <tr align="center">
					<td align="center">家庭住址</td>
					<td align="left" colspan="5" id="popHomeAddress"></td>
			  </tr>
			  <tr align="center">
					<td align="center">身份证号</td>
					<td align="left" colspan="5" id="popIdentify"></td>
			  </tr>
			  <tr align="center">
					<td align="center">邮箱地址</td>
					<td align="left" colspan="5" id="popEmail"></td>
			  </tr>
			  <tr align="center">
					<td align="center">所在院校</td>
					<td align="left" colspan="2" id="popSchoolName"></td>
					<td align="center">所在院校代码</td>
					<td align="left" colspan="3" id="popSchoolCode"></td>
			  </tr>
			  <tr align="center">
					<td align="center">所在专业</td>
					<td align="left" colspan="2" id="popMajor"></td>
					<td align="center">高考报名号</td>
					<td align="left" colspan="3" id="popGaokaoCode"></td>
			  </tr>
			  <tr align="center">
					<td  align="center">报考专业</td>
					<td  align="left" colspan="2" id="popTargetMajor"></td>
					<td  align="center">报考专业代码</td>
					<td  align="left" colspan="3" id="poptargetCode"></td>
			  </tr>
			  <tr align="center">
					<td  align="center">在校期间受过何种奖励</td>
					<td  align="left" colspan="6" id="popAwardStuff"></td>
			  </tr>
			  <tr align="center">
					<td  align="center">有何特长</td>
					<td  align="left" colspan="6" id="popSpeciality"></td>
			  </tr>
			  <tr align="center">
					<td  align="center">联系地址</td>
					<td  align="left" colspan="3" id="popContactAddress"></td>
					<td  align="center">邮政编码</td>
					<td  align="left" colspan="2" id="popPostCode"></td>
			  </tr>
			  <tr align="center">
					<td  width="20%" align="center">审批意见</td>
					<td  width="30%" align="left" colspan="6" id="approvalComment"></td>
			  </tr>
			  <tr align="center">
					<td align="center">申请日期</td>
					<td align="left" id="popCommitDate"></td>
					<td align="center">考场号</td>
					<td align="left" id="popExamRoom"></td>
					<td align="center">考试号</td>
					<td align="left" id="popExamCode" colspan="2"></td>
			  </tr>
		</table>
    </div>

</body>
</html>
<script language="javascript">
$(function() {
	$( "#dialogDetail" ).dialog({
		title:"详细信息",
		width:"80%",
		autoOpen: false,
		show: {
			effect: "blind",
			duration: 500
		},
		hide: {
			effect: "blind",
			duration: 300
		}
	});
});
	function doEdit(nflag)
	{
		var svalue=getCbxValue(document.all.cbxItem);
		//alert(svalue.indexOf(","));
		if(svalue=="" ) {
		  alert("请选择一条记录。");
		  return;	
		}
		var saction="enrollApproveAction!toEditPage.action?Ids="+svalue;
		if(nflag===1){
			 if(confirm("确定要全部审批通过吗？")){
				saction="enrollApproveAction!updateEnrollBulkApprove.action?approveStatus=2&Ids="+svalue;
				document.forms[0].action=saction;
				document.forms[0].submit();
			 }
		} else if(nflag===2){
			 if(confirm("确定要全部审批拒绝吗？")){
				saction="enrollApproveAction!updateEnrollBulkApprove.action?approveStatus=3&Ids="+svalue;
				document.forms[0].action=saction;
				document.forms[0].submit();
			 }
		}else{
			document.forms[0].action=saction;
			document.forms[0].submit();
		}
	}
	
	function doSubmit()
	{
	  document.forms[0].submit();	
	}
	function orderBy(thisForm,columnName)
	{
		var form;
		if(typeof(thisForm)=='string'){
			form = document.getElementById(thisForm);
			form.elements['enrollStudent.orderBy'].value = columnName;
			if(form.elements['enrollStudent.descOrasc'].value=="desc"){
				form.elements['enrollStudent.descOrasc'].value = "asc";
			}else{
				form.elements['enrollStudent.descOrasc'].value = "desc";
			}
			form.submit();
		}
 }
	function popupDetail(id){
		if(id.trim()!=""){
			$.ajax({
				url:"<%=scontext%>/enrollCheckAction!popEnrollDetail.action",
	            dataType:"text",
	            async:false,
	            data:{id:id.trim()},
	            success:function(data){
	            	var obj = eval("("+data+")");
            		var jsObject = eval("("+obj.JSONRETURN.replace(new RegExp(/\r\n/g),"|")+")");
	            	if(jsObject!=""){
	            		//$( "#dialogDetail" ).text(jobj.JSONRETURN);
	            		$("#popName").text(jsObject.name);
	            		$("#popNo").text(jsObject.no);
	            		$("#popPic").empty();
	            		$("#popPic").append("<div><img id='uploadPreview' style='width:150px;height:180px' src='"+jsObject.picPath+"'/></div>");
	            		$("#popBirthDate").text(jsObject.brithDate);
	            		$("#popsexName").text(jsObject.sexName);
	            		$("#popNation").text(jsObject.nation);
	            		$("#popPoliticalType").text(jsObject.politicalType);
	            		$("#popPhone").text(jsObject.phone);
	            		$("#popHomeAddress").text(jsObject.homeAddress);
	            		$("#popIdentify").text(jsObject.identify);
	            		$("#popEmail").text(jsObject.email);
	            		$("#popSchoolName").text(jsObject.schoolName);
	            		$("#popSchoolCode").text(jsObject.schoolCode);
	            		$("#popMajor").text(jsObject.major);
	            		$("#popGaokaoCode").text(jsObject.gaokaoCode);
	            		$("#popTargetMajor").text(jsObject.targetMajor);
	            		$("#poptargetCode").text(jsObject.targetMajorCode);
	            		$("#popAwardStuff").text(jsObject.awardStuff);
	            		$("#popSpeciality").text(jsObject.speciality);
	            		$("#popContactAddress").text(jsObject.contactAddress);
	            		$("#popPostCode").text(jsObject.postCode);
	            		$("#approvalComment").text(jsObject.approveComment=='null'?"":jsObject.approveComment);
	            		$("#popCommitDate").text(jsObject.commitDate);
	            		$("#popExamRoom").text(jsObject.examRoom);
	            		$("#popExamCode").text(jsObject.examCode);
	            		$( "#dialogDetail" ).dialog( "open" );
	            	}
	            }
			});
			
		}
	}
</script>