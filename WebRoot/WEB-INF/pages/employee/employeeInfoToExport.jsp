<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
</head>
<body><br>
<s:form action="employeeInfoAction" method="post">
<input type="hidden" id="pagerAction" name="pagerAction" value="employeeInfoAction!execute.action">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" bgcolor="#FFFFFF" class="czmb_l">
              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr >
					 <td width="6%" class="filter">姓名 ：</td>
					 <td width="14%" class="filter"><s:textfield id="username" name="employeeInfo.username" cssClass="mytext" theme="simple"/></td>
					 <td width="6%" class="filter">性别 ：</td>
					 <td width="14%" class="filter"><s:select id="sex" label="sex" name="employeeInfo.sex" headerKey="-1" headerValue="请选择"
										   list="#{'1':'男', '2':'女'}" theme="simple" value="employeeInfo.sex" style="width:100px"/></td>
					 <td width="6%" class="filter">出生年月 ：</td>
					 <td width="14%" class="filter"><s:textfield id="birthday" name="employeeInfo.birthday" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/></td>
					 <td width="6%" class="filter">籍贯 ：</td>
					 <td width="14%" class="filter"><s:textfield id="natives" name="employeeInfo.natives" cssClass="mytext" theme="simple"/></td>
					 <td width="6%" class="filter">民族 ：</td>
					 <td width="14%" class="filter"><s:textfield id="nation" name="employeeInfo.nation" cssClass="mytext" theme="simple"/></td>
				</tr>
				<tr >
					 <td width="6%" class="filter">政治面貌 ：</td>
					 <td width="14%" class="filter"><s:select id="politics" label="politics" name="employeeInfo.politics" headerKey="-1" headerValue="请选择"
										   list="#{'1':'团员', '2':'党员'}" theme="simple" value="employeeInfo.politics" style="width:100px"/></td>
					 <td width="6%" class="filter">毕业学校 ：</td>
					 <td width="14%" class="filter"><s:textfield id="shool" name="employeeInfo.school" cssClass="mytext" theme="simple"/></td>
					 <td width="6%" class="filter">上岗时间 ：</td>
					 <td width="14%" class="filter" colspan="5">从<s:textfield id="onboardTime" name="employeeInfo.onboardTime" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
					 到 <s:textfield id="onboardTimeEnd" name="employeeInfo.onboardTimeEnd" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/></td>
				</tr>
				<tr >
					 <td width="6%" class="filter">排序列 ：</td>
					 <td width="14%" class="filter"><s:select id="orderBy" label="orderBy" name="employeeInfo.orderBy" headerKey="-1" headerValue="请选择"
										   list="#{'departmentName':'部门名称','code':'员工编号','username':'姓名','sex':'性别','birthday':'出生年月','natives':'籍贯','nation':'民族','politics':'政治面貌','firstEducation':'第一学历','firstMajor':'学位','firstSchool':'毕业学校','firstMajor':'专业','education':'最高学历',
					'degree':'学位','school':'毕业学校','major':'专业','jobTitle':'职称','position':'职务','registeredLocal':'户口所在地','onboardTime':'上岗时间','employeeComposition':'人员构成'}" theme="simple" value="employeeInfo.orderBy" style="width:100px"/></td>
					 <td width="6%" class="filter">升序降序 ：</td>
					 <td width="14%" class="filter"><s:select id="descOrasc" label="descOrasc" name="employeeInfo.descOrasc"
										   list="#{'asc':'升序', 'desc':'降序'}" theme="simple" value="employeeInfo.descOrasc" style="width:100px"/></td>
				 </tr>
				<tr><td width="100%" class="filter" colspan="10">文件导出列 ：</td></tr>
				<tr>
					<td colspan="10">
						<div>
                                    <input type="checkbox" name="select_all" value="-1" onclick="selectForCbxAll(this,'titleList')">
                                    <label >全选</label>
                                    <div style="padding-left:50px">
                                        <input type="checkbox"  name="titleList" value="0"/>
                                        <label>部门名称</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="1"/>
                                        <label>员工编号</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="2"/>
                                        <label>姓名</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="3"/>
                                        <label>性别</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="4"/>
                                        <label>出生年月</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="5"/>
                                        <label>籍贯</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="6"/>
                                        <label>民族</label>&nbsp;&nbsp;
                                        </br>
                                        <input type="checkbox"  name="titleList" value="7"/>
                                        <label>政治面貌</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="8"/>
                                        <label>第一学历</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="9"/>
                                        <label>第一学位</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="10"/>
                                        <label>第一毕业学校</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="11"/>
                                        <label>第一专业</label>&nbsp;&nbsp;
                                        </br>
                                        <input type="checkbox"  name="titleList" value="12"/>
                                        <label>最高学历</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="13"/>
                                        <label>学位</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="14"/>
                                        <label>毕业学校</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="15"/>
                                        <label>专业</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="16"/>
                                        <label>职称</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="17"/>
                                        <label>职位</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="18"/>
                                        <label>户口所在地</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="19"/>
                                        <label>上岗时间</label>&nbsp;&nbsp;
                                        <input type="checkbox"  name="titleList" value="20"/>
                                        <label>人员构成</label>&nbsp;&nbsp;
                                    </div>
                                    
                                </div>
				</td></tr>
				<tr>
					 <td width="100%" align="right" colspan="10">
						 <div class="search"><a href="#" onclick="doExport()"><img src="<%=scontext%>/admin/images/export.gif" width="60" height="20" border="0"></a></div>
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
	  var isSelect = 0;
	  var titleList = document.forms[0].titleList
	  if(titleList.length >0){
	  	for(i=0;i< titleList.length >0;i++){
	  		if(titleList[i].checked){
	  			isSelect++;
	  		}
	  	}
	  }
	  if(isSelect == 0){
	  	sMsg = "请选择需要导出的列！";
	  }
	  return sMsg;
	}
	function doExport()
	{
		var msg=validTextValue();
		if(msg!=""){
		  alert(msg);
		  return;	
		}
	  document.forms[0].action="employeeInfoAction!exportEmployee.action";
	  document.forms[0].submit();
	}
</script>