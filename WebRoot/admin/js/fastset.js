function doZZUrlEdit(zzid)
{
	var stxt=document.getElementById("linkedit1"+zzid).outerText;
	if(stxt=="编辑")
	{
	  document.getElementById("t_"+zzid).disabled=false;
	  document.getElementById("t_"+zzid).focus();
	  document.getElementById("linkedit1"+zzid).innerText="保存";
  }
  else
  {
  	if(document.getElementById("t_"+zzid).value.trim().length<1)
  	{
	    alert("请输入种子URL。");
	    document.getElementById("t_"+zzid).focus();
	    return;
    }
    doZZUrlSave(zzid);
  }
}
function doZZUrlSave(zzid)
{
	document.getElementById("zzurl").value=document.getElementById("t_"+zzid).value;
	document.forms[0].action="fastSetRuleAction!saveZZUrl.action?zzid="+zzid;
	document.forms[0].submit();
}

function doZZUrlDel(zzid)
{
	document.forms[0].action="fastSetRuleAction!delZZUrl.action?zzid="+zzid;
	document.forms[0].submit();
}

function doZZUrlAdd()
{
	if(document.getElementById("zzurl").value.trim().length<1){
	   alert("请输入种子URL。");
	   document.getElementById("zzurl").focus();
	   return;
	}
	document.forms[0].action="fastSetRuleAction!saveZZUrl.action";
	document.forms[0].submit();
}

function doZZUrlReset()
{
	document.getElementById("zzurl").value="";
}

//------------------------------
function doCJEdit(sid)
{
	var stxt=document.getElementById("linkedit"+sid).outerText;
	if(stxt=='编辑')
	{
	  document.getElementById("t1_"+sid).disabled=false;
	  document.getElementById("t2_"+sid).disabled=false;
	  document.getElementById("t3_"+sid).disabled=false;
	  document.getElementById("t1_"+sid).focus();
	  document.getElementById("linkedit"+sid).innerText="保存";
  }
  else{
  	if(validInput("t1_"+sid,"t3_"+sid)==false) return;
  	doSave(sid);
  }
}
function doSave(sid)
{
	document.getElementById("channelname").value=document.getElementById("t1_"+sid).value;
	document.getElementById("channelkeyword").value=document.getElementById("t2_"+sid).value;
	document.getElementById("channelexprsion").value=document.getElementById("t3_"+sid).value;
	document.forms[0].action="fastSetRuleAction!saveSiteChannel.action?sid="+sid;
	document.forms[0].submit();
}

function doCJDelete(nid)
{
	document.forms[0].action="fastSetRuleAction!deleteSiteChannel.action?id="+nid;
	document.forms[0].submit();
}	

function doCJAdd()
{
	if(validInput('sname','sexpression')==false) return;
	
	document.forms[0].action="fastSetRuleAction!addSiteChannel.action";
	document.forms[0].submit();
}	
function doCJReset()
{
	$("sname").value="";
	$("skeyword").value="";
	$("sexpression").value="";
}	

function validInput(key1,key2)
{
	var objs = [2];
	objs[0] = new Object();
	objs[0].key = key1;
	objs[0].name = "栏目名称";
	objs[1] = new Object();
	objs[1].key = key2;
	objs[1].name = "栏目地址";
	
	return valid(objs);
}

function valid(objs) 
{
	var validResult = true;
	for(var i=0; i<objs.length;i++) 
	{
		if($(objs[i].key).value.trim().length<1)
		{
			alert(objs[i].name+"不能为空!");
			$(objs[i].key).focus();
			validResult = false;
			break;
		}
		
	}
	return validResult;
}