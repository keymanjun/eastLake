<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/admin/commons/taglibs.jsp"%>
<script language="JavaScript" src="<%=scontext%>/admin/js/md5.js"></script>
<script language="JavaScript" src="<%=scontext%>/admin/js/IDvalidation.js"></script>
</head>
<body>
<s:form action="enrollAction" method="post" enctype ="multipart/form-data">
<s:hidden name="enrollStudent.id"/>
<s:hidden name="enrollStudent.targetMajor"/>
<s:hidden name="enrollStudent.picPath"/>
<s:hidden name="enrollStudent.approveComment"/>
<s:hidden name="enrollStudent.commitDate"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top" bgcolor="DEE8F6" class="right">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="right" valign="top" class="czmb_2">
            <div class="tool">
              <table border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
                        <td><a href="#" onclick="doSubmit()">提交</a></td>
                      </tr>
                     </table>
                  </td>
                </tr>
              </table>
            </div>
            <div class="blankH10"></div>
            <table id="enrollInfo" width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="border_blue">
                <tr>
                  <td class="operate_title">
                  	<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="62%">学生报名信息</td>
                        <td width="38%" align="right"><img src="<%=scontext%>/admin/images/zk.gif" width="15" height="14"></td>
                      </tr>
                    </table>
                  </td>
                </tr>
                <tr>
                  <td>                  	
                  	<div style="padding-top:10px; padding-bottom:10px;">
		                   <table width="99%" border="0" align="center" cellpadding="0" cellspacing="2" id="edittb">
								<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center"><font color="red">审批意见</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:property value="enrollStudent.approveComment"/>
									</td>
								</tr>
								<tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  width="12%" align="center">姓名<font color="red">(*)</font></td>
									<td  width="25%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="name" name="enrollStudent.name" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
									<td  width="10%" align="center">学号<font color="red">(*)</font></td>
									<td  width="38%" align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="no" name="enrollStudent.no" cssClass="mytext" theme="simple" maxlength="25"/>
									</td>
									<td  width="15%" align="left" rowspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<div id="preview_fake" style="display:none;width:150px;height:180px"></div>
										<div><img id="uploadPreview" style="width:150px;height:180px" src="<%=scontext%>/<s:property value="enrollStudent.picPath"/>"/></div>
										<s:file id="uploadImage" label="上传登记照" theme="simple" name="uploadImage" onchange="viewPic(this)"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td   align="center">出生日期<font color="red">(*)</font></td>
									<td   align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="brithDate" name="enrollStudent.brithDate" cssClass="mytext" theme="simple" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="true"/>
									</td>
									<td  align="center">性别<font color="red">(*)</font></td>
									<td   align="left">&nbsp;&nbsp;&nbsp;&nbsp;							
											 <s:select label="usersex"
									       name="enrollStudent.sex"
									       headerKey="-1" headerValue="请选择"
									       list="#{'1':'男', '2':'女'}"
									       theme="simple"
									       value="enrollStudent.sex"
									       style="width:80px"/> 
									</td>
									<td   align="center">民族<font color="red">(*)</font></td>
									<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="nation" name="enrollStudent.nation" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">政治面貌<font color="red">(*)</font></td>
									<td  align="left">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="politicalType" name="enrollStudent.politicalType" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
									<td align="center">联系电话<font color="red">(*)</font></td>
									<td align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="phone" name="enrollStudent.phone" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">家庭住址</td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="homeAddress" name="enrollStudent.homeAddress" cssClass="mytext" theme="simple" size="80" maxlength="50"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">身份证号<font color="red">(*)</font></td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="identify" name="enrollStudent.identify" cssClass="mytext" theme="simple" size="80" maxlength="25" onblur="checkIdentify(this)"/>
										<font color="red"><span id="checkContent"></span></font>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">邮箱地址<font color="red">(*)</font></td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="email" name="enrollStudent.email" cssClass="mytext" theme="simple" size="80" maxlength="25"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在院校<font color="red">(*)</font></td>
									<td align="left" colspan="5">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="schoolName" name="enrollStudent.schoolName" cssClass="mytext" theme="simple" size="80" maxlength="50"/>
									</td>
									<td align="center">上传登记照<font color="red">(*) </font>容量小于100KB</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在院校代码<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="schoolCode" name="enrollStudent.schoolCode" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">所在专业<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="major" name="enrollStudent.major" cssClass="mytext" theme="simple" size="80" maxlength="50"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">高考报名号<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="gaokaoCode" name="enrollStudent.gaokaoCode" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td align="center">报考院校<font color="red">(*)</font></td>
									<td align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="targetSchoolName" name="enrollStudent.targetSchoolName" cssClass="mytext" theme="simple" size="80" value="武汉东湖学院" readonly="true"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考院校代码<font color="red">(*)</font></td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:textfield id="targetSchoolCode" name="enrollStudent.targetSchoolCode" cssClass="mytext" theme="simple" value="11798" readonly="true"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考专业<font color="red">(*)</font></td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										
										<s:select id="targetMajor" label="targetMajor" name="enrollStudent.targetMajorCode" headerKey="-1" headerValue="请选择"
								   		list="#request.majorHash" theme="simple" style="width:150px" onchange="changeTargetMajorCode(this)"/>
									</td>
							  </tr>
							  <!--tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">报考专业代码<font color="red">(*)</font></td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
									<s:textfield id="targetMajorCode" name="enrollStudent.targetMajorCode" cssClass="mytext" theme="simple" size="80" readonly="true" maxlength="20"/>	
									</td>
							  </tr-->
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">在校期间受过何种奖励</td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textarea id="awardStuff" name="enrollStudent.awardStuff" cssClass="mytext" theme="simple" rows="4" cols="140"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">有何特长</td>
									<td  align="left" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textarea id="speciality" name="enrollStudent.speciality" cssClass="mytext" theme="simple" rows="4" cols="140"/>
									</td>
							  </tr>
							  <tr align="center" onmouseout="reChangeBackground(this)" onmouseover="changeBackground(this)">
									<td  align="center">联系地址<font color="red">(*)</font></td>
									<td  align="left" colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="contactAddress" name="enrollStudent.contactAddress" cssClass="mytext" theme="simple" size="80" maxlength="50"/>
									</td>
									<td  align="center">邮政编码<font color="red">(*)</font></td>
									<td  align="left" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;
										<s:textfield id="postcode" name="enrollStudent.postcode" cssClass="mytext" theme="simple" maxlength="20"/>
									</td>
							  </tr>
							</table>
		                </div>
		          </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr>
	          <td align="right" valign="top" class="czmb_2">
	          	<div class="tool">
	              <table border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                  <td width="70" style="cursor:hand" onMouseOver="this.style.backgroundImage='url(images/tool_bg1.gif)';this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#DFAF0D'; "onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
	                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td align="center"><img src="<%=scontext%>/admin/images/tj.gif" width="22" height="21"></td>
	                        <td><a href="#" onclick="doSubmit()">提交</a></td>
	                      </tr>
	                     </table>
	                  </td>
	                </tr>
	              </table>
	            </div>
	          </td>
          </tr>
      </table></td>
  </tr>
</table>
</s:form>
</body>
</html>
<script language="javascript">
		
	function validTextValue()
	{
	  var sMsg="";
	  if(getElem("name").value.trim().length<1){
	     	sMsg+="姓名不能为空。\n";
	  }
	  if(getElem("no").value.trim().length<1){
	     	sMsg+="学号不能为空。\n";
	  }
	  if(getElem("brithDate").value.trim().length<1){
	     	sMsg+="出生日期不能为空。\n";
	  }
	  if(document.forms[0].elements["enrollStudent.sex"].value ==-1){
	     	sMsg+="请选择性别。\n";
	  }
	  if(getElem("nation").value.trim().length<1){
	     	sMsg+="民族不能为空。\n";
	  }
	  if(getElem("politicalType").value.trim().length<1){
	     	sMsg+="政治面貌不能为空。\n";
	  }
	  if(getElem("phone").value.trim().length<1){
	     	sMsg+="联系电话不能为空。\n";
	  }
	  if(getElem("homeAddress").value.trim().length<1){
	     	sMsg+="家庭地址不能为空。\n";
	  }
	  if(getElem("identify").value.trim().length<1){
	     	sMsg+="身份证号不能为空。\n";
	  }else{
		  if(!IdCardValidate(getElem("identify").value)){
			  sMsg+="身份证号不正确，请确认。\n";
		  }else{
			  var identify = document.getElementById("identify");
			  if(checkIdentify(identify)){
				  sMsg+="身份证号已经注册，请确认。\n";
			  }
		  }
	  }
	  if(getElem("email").value.trim().length<1){
	     	sMsg+="邮箱地址不能为空。\n";
	  }else if(!checkEmail(getElem("email").value.trim())){
		  sMsg+="邮箱地址的格式不正确，请修改。\n";
	  }
	  if(getElem("schoolName").value.trim().length<1){
	     	sMsg+="所在院校不能为空。\n";
	  }
	  if(getElem("schoolCode").value.trim().length<1){
	     	sMsg+="所在院校代码不能为空。\n";
	  }
	  if(getElem("major").value.trim().length<1){
	     	sMsg+="所在专业不能为空。\n";
	  }
	  if(getElem("gaokaoCode").value.trim().length<1){
	     	sMsg+="高考报名号不能为空。\n";
	  }else {
		  var gaokao = getElem("gaokaoCode").value.trim();
		  if(isNaN(gaokao)){
			  sMsg+="高考报名号必须为数字。\n";
		  }
		  if(gaokao.length!=14){
			  sMsg+="高考报名号必须14位。\n";
		  }
	  }
	  if(getElem("targetMajor").value.trim()==-1){
	     	sMsg+="请选择报考专业。\n";
	  }
	  if(getElem("awardStuff").value.trim().length > 500){
		  getElem("awardStuff").value.trim().substring(0,500);
		  sMsg+="在校期间 奖励描述长度不能超过500个字符。\n";
	  }
	  if(getElem("speciality").value.trim().length > 500){
		  getElem("speciality").value.trim().substring(0,500);
		  sMsg+="有何特长描述长度不能超过500个字符。\n";
	  }
	  if(getElem("contactAddress").value.trim().length<1){
	     	sMsg+="联系地址不能为空。\n";
	  }
	  if(getElem("postcode").value.trim().length<1){
	     	sMsg+="邮政编码不能为空。\n";
	  }
	  return sMsg;
	}
	function doSubmit()
	{
		var msg=validTextValue();
		if(msg!=""){
		  alert(msg);
		  return;	
		}
	  document.forms[0].action="enrollAction!saveEnroll.action";
	  document.forms[0].submit();
	}
	
	function changeTargetMajorCode(major){
		if(major.value==-1){
			getElem("enrollAction_enrollStudent_targetMajor").value = "";
		}else{
			getElem("enrollAction_enrollStudent_targetMajor").value = major.options[major.selectedIndex].text
		}
	}
	function toGoBack()
	{	
	  document.forms[0].action="enrollAction!goBack.action";
	  document.forms[0].submit();
	}
	function viewPic(pic) {
		var tempPic = pic.value.substr(pic.value.length - 3, 3);
		if(checkExt(tempPic)){
			uploadPreview = document.getElementById("uploadPreview");
			uploadImage = document.getElementById("uploadImage");
			uploadPreviewDiv = document.getElementById("preview_fake");
			if(pic.files&&pic.files[0]){
				if(pic.files[0].size <= 102400){
					var oFReader = new FileReader();
					oFReader.onload = function(e) {
						uploadPreview.src = e.target.result;
					}
					if (uploadImage.files.length === 0) { return; }
					var oFile = uploadImage.files[0];
					oFReader.readAsDataURL(oFile);
				}else{
					 pic.value="";
					 alert("图片尺寸请不要大于100KB");
				}
			}else{
				//IE下，使用滤镜
				//var filePath = pic.value;
                //var image=new Image();
               // image.src=filePath;
               // filesize=image.fileSize;
               // if(filesize <=102400){
	                //pic.select();
	               // var imgSrc = document.selection.createRange().text;
	               // document.getElementById("uploadPreview").src = imgSrc;
	               // document.selection.empty();
               // }else{
                	//alert("图片尺寸请不要大于100KB");
                //}
               uploadPreview.style.display="none";
               pic.select();
               var imgSrc = document.selection.createRange().text;
               uploadPreviewDiv.filters.item(
               'DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
               uploadPreviewDiv.style.display="block";
			}
		}else{
			pic.value="";
			alert("请上传合法格式的照片");
		}
	}
	// 检查扩展名是否合法,合法返回True
	function checkExt(ext) {
		// 这里设置允许的扩展名
		var AllowExt = "jpg|gif|peg|png|bmp";
		var ExtOK = false;
		var ArrayExt;
		if (AllowExt.indexOf('|') != -1) {
			ArrayExt = AllowExt.split('|');
			for (i = 0; i < ArrayExt.length; i++) {
				if (ext.toLowerCase() == ArrayExt[i]) {
					ExtOK = true;
					break;
				}
			}
		} else {
			ArrayExt = AllowExt;
			if (ext.toLowerCase() == ArrayExt) {
				ExtOK = true;
			}
		}
		return ExtOK;
	}
	
	function checkIdentify(idenftify){
		var identifyNO = false;
		var id = document.getElementById("enrollAction_enrollStudent_id");
		if(idenftify.value.trim()!=""){
			$.ajax({
				url:"<%=scontext%>/enrollCheckAction!checkIdentify.action",
	            dataType:"text",
	            async:false,
	            data:{identify:idenftify.value,id:id.value},
	            success:function(data){
	            	var value = eval("("+data+")");
	            	if(value.JSONRETURN=="false"){
	            		$("#checkContent").text("");
	            	}else{
	            		$("#checkContent").text("身份证号已经注册，不能重复注册!");
	            		identifyNO = true;
	            	}
	            }
			});
		}else{
			$("#checkContent").text("");
		}
		return identifyNO;
	}
</script>