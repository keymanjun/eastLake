function doEdit(nFlag,context)
{
	 		var parentno = getTreeCheckedValue();
	 		var array = new Array();
	 		if(parentno=='')
	 			parentno = "";
	 		else
	 		{
	 			if(nFlag==1 || nFlag==2)
	 			{
	 				array = parentno.split(",");
	 				parentno = array[0];
	 			}
	 		}
	 		// 树状结构新增节点
	 		if(nFlag==1)
	 		{
	 			window.open(context+"/goodsSortPropAction!addNodeInfo.action?parentno="+parentno+"&opertype=1");
	 		}
	 		// 树状结构删除节点
	 		else if(nFlag==3)
	 		{
	 			if(parentno=="")
	 				alert("请选择要删除的节点!");
	 			else
	 			{
	 				if(!confirm("确定要删除该节点及其子节点吗？")) return false;
	 				document.forms[0].action=context+"/goodsSortPropAction!deleteGoodsSortProp.action?parentno="+parentno;
	 				document.forms[0].submit();
	 			}
	 		}
	 		// 右侧节点、属性信息保存
	 		else if(nFlag==4)
	 		{
	 			
	 		}
	 		// 右侧属性信息删除
	 		else if(nFlag==5)
	 		{
	 			var svalue = getCbxValue(document.all.prop);
	 			if(svalue=="") 
	 			{
				  alert("请选择一条记录。");
				  return false;	
				}
	 		}
}
   	function packJson(nFlag, obj)
	{
		var jsonstr = "";
		var propname="";
		var propshowtype="";
		var propinitvalue="";
		var len = document.getElementsByName("propname").length;
		// 编辑、保存属性信息
		if(nFlag==1)
		{
			for(var i=0;i<len;i++)
			{
				propname+=document.getElementsByName("propname")[i].value+":";
				propshowtype+=document.getElementsByName("propshowtype")[i].value+":";
				propinitvalue+=document.getElementsByName("propinitvalue")[i].value+":";
			}
			jsonstr = propname.substring(0,propname.length-1)+";"+propshowtype.substring(0,propshowtype.length-1)+";"+propinitvalue.substring(0,propinitvalue.length-1);
		}
		// 删除属性信息
		else if(nFlag==2)
		{
			for(var i=0;i<len;i++)
			{
				if(obj!=i)
				{
					propname+=document.getElementsByName("propname")[i].value+":";
					propshowtype+=document.getElementsByName("propshowtype")[i].value+":";
					propinitvalue+=document.getElementsByName("propinitvalue")[i].value+":";
				}
			}
			if(len>2)
				jsonstr = propname.substring(0,propname.length-1)+";"+propshowtype.substring(0,propshowtype.length-1)+";"+propinitvalue.substring(0,propinitvalue.length-1);
		}
		return jsonstr;
	}
	
	function validateProp(obj)
	{
		if(obj.value.length<1){
			alert("请输入属性名称。");
			return false;
		}
	}
	
	function canEdit(obj)
	{
		var svalue="";
		if(obj.checked){
			svalue=obj.value;
			document.getElementsByName('propname')[svalue].readOnly = false;
			document.getElementsByName('propshowtype')[svalue].disabled = false;
			document.getElementsByName('propinitvalue')[svalue].readOnly = false;
		}
	}
	
	function createPropHtml($j)
	{
		$j('#selfprops').remove();
		$j('tr[name=selfprop]').remove();
		var html='<tr id="selfprops" name="selfprop">';
		html+= '<td class="czmb_l" bgcolor="#FFFFFF"><input type="radio" id="prop" name="prop" value="-1"></td>';
		html+='<td class="czmb_l" bgcolor="#FFFFFF"><input type="text" id="propname" name="propname" value=""></td>';
		html+='<td class="czmb_l" bgcolor="#FFFFFF">';
		html+='<select id="propshowtype" name="propshowtype">';
		html+=	'<option value="1">单选框</option>';
		html+=	'<option value="2">复选框</option>';
		html+=	'<option value="3">下拉框</option>';
		html+=	'<option value="4">文本框</option>';
		html+='</select></td>';
		html+='<td class="czmb_l" bgcolor="#FFFFFF"><input type="text" id="propinitvalue" name="propinitvalue" value="">多个自定义属性以逗号(,)分隔</td>';
		html+='</tr>';
		$j('#aa').append(html);
	}
	
	function createPropHtmls(jsonprop,$j)
	{
		var html='';
	   	var html2='';
		for(var i=0;i<jsonprop.length;i++)
 		{
 			html+='<tr id="selfprops" name="selfprop">';
 			html+='<td class="czmb_l" bgcolor="#FFFFFF"><input type="radio" id="prop" name="prop" onclick="canEdit(this)" value='+i+'></td>';
 			html+='<td class="czmb_l" bgcolor="#FFFFFF"><input type="text" id="propname" name="propname" readonly="readonly" value='+jsonprop[i].propname+'></td>';
 			html+='<td class="czmb_l" bgcolor="#FFFFFF">';
 			html+='<select id="propshowtype" name="propshowtype" disabled="disabled">';
 			if(jsonprop[i].propshowtype==1)
 			{
				html+=	'<option selected="selected" value="1">单选框</option>';
				html+=	'<option value="2">复选框</option>';
				html+=	'<option value="3">下拉框</option>';
				html+=	'<option value="4">文本框</option>';
			}
			else if(jsonprop[i].propshowtype==2)
 			{
				html+=	'<option value="1">单选框</option>';
				html+=	'<option selected="selected" value="2">复选框</option>';
				html+=	'<option value="3">下拉框</option>';
				html+=	'<option value="4">文本框</option>';
			}
			else if(jsonprop[i].propshowtype==3)
 			{
				html+=	'<option value="1">单选框</option>';
				html+=	'<option value="2">复选框</option>';
				html+=	'<option selected="selected" value="3">下拉框</option>';
				html+=	'<option value="4">文本框</option>';
			}
			else if(jsonprop[i].propshowtype==4)
 			{
				html+=	'<option value="1">单选框</option>';
				html+=	'<option value="2">复选框</option>';
				html+=	'<option value="3">下拉框</option>';
				html+=	'<option selected="selected" value="4">文本框</option>';
			}
			html+='</select></td>';
			html+='<td class="czmb_l" bgcolor="#FFFFFF"><input type="text" id="propinitvalue" name="propinitvalue" readonly="readonly" value='+jsonprop[i].propinitvalue+'>多个自定义属性以逗号(,)分隔</td>';
 			html+='</tr>';
 		}
 		$j('#aa').append(html);
 		html2='<tr id="selfprops" name="selfprop">';
 		html2+= '<td class="czmb_l" bgcolor="#FFFFFF"><input type="radio" name="prop" value="-1"/></td>';
		html2+= '<td class="czmb_l" bgcolor="#FFFFFF"><input type="text" id="propname" onblur="validateProp(this)" name="propname" value=""/></td>';
		html2+= '<td class="czmb_l" bgcolor="#FFFFFF">';
		html2+= '<select id="propshowtype" name="propshowtype">';
		html2+=	  '<option value="1">单选框</option>';
		html2+=	  '<option value="2">复选框</option>';
		html2+=	  '<option value="3">下拉框</option>';
		html2+=	  '<option value="4">文本框</option>';
		html2+= '</select></td>';
		html2+='<td class="czmb_l" bgcolor="#FFFFFF"><input type="text" id="propinitvalue" value=""/>多个自定义属性以逗号(,)分隔</td>';
		html2+='</tr>';
		$j('#aa').append(html2);		
	}
	
	function doSearchSiteView()
	{
		
	}