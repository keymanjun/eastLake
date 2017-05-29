//trim	
String.prototype.trim = function()
{
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}	
function getElem() {
  var elements = new Array();

  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof element == 'string')
      element = document.getElementById(element);

    if (arguments.length == 1)
      return element;

    elements.push(element);
  }

  return elements;
}

function validUrl(svalue)
{
	return /^[a-zA-Z_.0-9]+$/.test(svalue);
}

/*
	效验对象的值是否为指定的格式
	obj 需要效验的对象
	validateType 格式
		isEnValue  英文格式 允许下划线和点
		isTime24  24小时计时方式的格式 如23:22 0:12
*/
function validateObjValue(obj,validateType)
{
	var validateValue = obj.value.trim();
	if(validateType == 'isEnValue')
		return /^[a-zA-Z_.]+$/.test(validateValue);
	if(validateType == 'isTime24')
		return /^([0-9]|[0-1][0-9]|2[0-3]):[0-5]\d$/.test(validateValue);
	if(validateType == 'tlePhone')
		return /^([0-9]+-[0-9]+$)|(^[0-9]+$)|(^([0-9])[0-9]$)|(^013[0-9]+$)|(^13[0-9])+$/.test(validateValue);
	return false;
}

function getCbxValue(objs)
{
	var sreturn='';
  if(objs==null || objs==undefined) return '';
	if(objs.length>1)
	{
 	  for(var i=0;i<objs.length;i++){ 	     
 	    if(objs[i].checked){
 	      sreturn+=objs[i].value+","; 
 	    }
 	  }
  }
  else{
 	   if(objs.checked){
 	    sreturn+=objs.value+",";
 	  }
  }
  if(sreturn.length>0){
     sreturn=sreturn.substring(0,sreturn.length-1);
  }
  return sreturn; 
}

function selectForCbxAll(cbx,cbxItemName){
 var sreturn='';
 var bl=cbx.checked;
 var cbxitem=eval('document.all.'+cbxItemName);
 if(cbxitem==undefined) return sreturn;
 if(cbxitem.length>1){
 	  for(var i=0;i<cbxitem.length;i++){ 
 	  	if(cbxitem[i].disabled) continue;
 	      cbxitem[i].checked=bl;
 	      sreturn+=cbxitem[i].value+","; 	    
 	  }
 }
 else{
 	   if(cbxitem.disabled) return sreturn;
 	   cbxitem.checked=bl;
 	   sreturn+=cbxitem.value+",";
 }	
  sreturn=sreturn.substring(0,sreturn.length-1);
  return sreturn; 
}

function selectForCbxItem(cbxitem,cbxAllName)
{
	 var sreturn='';
	 var cbxall=eval('document.all.'+cbxAllName);
	 if(cbxitem.checked==false){
	    cbxall.checked=false;	
	 }
	 else
	 {
	 	 var sname=cbxitem.name;
	   var cbxs=eval('document.all.'+sname);
	   if(cbxs==undefined) return;
	   var n=0;
	   if(cbxs.length>1){
	   	  for(var i=0;i<cbxs.length;i++){
	   	    	if(cbxs[i].checked){
	   	    	   n++;	
	   	    	}
	   	  }
	   }
	   else{
	   	  if(cbxs.checked) n=1;
	   }
	 	 if(n==cbxs.length){
	 	 	  cbxall.checked=true;
	 	 }
	 }
}





/**
 * 判断radio是否有选中,如果选中返回选中的值
 *
 * @param radioObj radio对象
 */
	function getRadioCheckedValue(radioObj)
	{
		if(typeof(radioObj.length) == 'undefined')
		{
			return radioObj.value;
		}
		else
		{
			for(var i = 0; i< radioObj.length; i++)
			{
				if(radioObj[i].checked && radioObj[i].disabled==false)
				{
					return radioObj[i].value;
				}
			}
		}
		return '';
	}


/**
比较日期 ，返回 。。。天。。。小时。。。分。。。秒
asStartDate：起始日期
asEndDate：结束日期
diffDate: 预设施检差
author:jianwu
*/
function DateCompare(asStartDate,asEndDate,diffDate)
{
  var miStart=Date.parse(asStartDate.replace(/\-/g,'/'));
  var miEnd=Date.parse(asEndDate.replace(/\-/g,'/'));
	if(miEnd-miStart-diffDate < 0)
	{
		return "已过结束时间";
	}
	var day = parseInt((miEnd-miStart-diffDate)/(1000*24*3600));
	var ho = parseInt((miEnd-miStart-diffDate - (1000*24*3600*day))/(1000*3600));
	var mi = parseInt((miEnd-miStart-diffDate - (1000*24*3600*day)-(1000*3600*ho))/(1000*60));	
	var se = parseInt((miEnd-miStart-diffDate - (1000*24*3600*day)-(1000*3600*ho)-(1000*60*mi))/(1000));		
  	return parseInt((miEnd-miStart-diffDate)/(1000*24*3600))+"天"+ho+"小时"+mi+"分"+se+"秒";
}
/**
比较日期 ，返回毫秒数
asStartDate：起始日期
asEndDate：结束日期
author:jianwu
*/
function DateDiff(asStartDate,asEndDate){
  	var miStart=Date.parse(asStartDate.replace(/\-/g,'/'));
  	var miEnd=Date.parse(asEndDate.replace(/\-/g,'/'));
  	return (miEnd-miStart);
}
/**
将日期类型格式化为yyyy-mm-dd hh:mm:ss
add by jianwu
*/
function formatDateHHMMSS(dtDate)
{
	var y=dtDate.getFullYear();
    var m=dtDate.getMonth()+1;
    var d=dtDate.getDate();
    var h=dtDate.getHours();
    var mi=dtDate.getMinutes();
    var s=dtDate.getSeconds();		
	if (m<10) m="0"+m;
	if (d<10) d="0"+d;
	if(h<10) h ="0"+h;
	if(mi<10) mi="0"+mi;
	if(s<10) s="0"+s;
	var myday=y + "-" + m + "-" + d +" "+h+":"+mi+":"+s;
	return myday;
}
	
/*
 *当鼠标指到某条数据时，高亮显示当前数据
 */
 var  initObjectBackground;
 function changeBackground(obj)
 {
 	//initObjectBackground=obj.style.background;
	obj.style.background='#f0f0f0'; 

    //#3A6EA5桌面颜色
    //#ffff66
 }
 //当鼠标离开某条数据时，恢复显示当前数据背景
 function reChangeBackground(obj)
 {
 	//obj.style.background=initObjectBackground;
	obj.style.background='#ffffff';
 }
   
 /*
  *add by jianwu
  *当定义了分层以后，决定是否显示分层内容
  *@param div1 div ID
  *@param relateDiv 相应需要调整大小的div ID
  *@param hiddenHeight 相应需要调整大小的div 隐藏div1时的高度
  *@param viewHeight 相应需要调整大小的div  不隐藏div1时的高度
  */ 
 function expands(div1,relateDiv,hiddenHeight,viewHeight) 
{
		var whichEl1=eval(div1);
		var relateDiv1=eval(relateDiv);
		if (whichEl1.style.display=="none")
		{	
			whichEl1.style.display="block";
			document.images["view"].src = ROOT_PATH+"/images/up.gif";
			if(relateDiv != "")
			relateDiv1.style.height=validWorkAreaHeight*hiddenHeight;
			getTheFirstFocus();
		}
		else{
			whichEl1.style.display="none";
			document.images["view"].src = ROOT_PATH+"/images/down.gif";
			if(relateDiv != "")
			relateDiv1.style.height=validWorkAreaHeight*viewHeight;
		}		
}	


	
/**
* Reference: Sandeep V. Tamhankar (stamhankar@hotmail.com),
* http://javascript.internet.com
*/
	function checkEmail(emailStr) 
	{
		if (emailStr.length == 0) 
		{
			return true;
		}
		var emailPat=/^(.+)@(.+)$/;
		var specialChars="\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
		var validChars="\[^\\s" + specialChars + "\]";
		var quotedUser="(\"[^\"]*\")";
		var ipDomainPat=/^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
		var atom=validChars + '+';
		var word="(" + atom + "|" + quotedUser + ")";
		var userPat=new RegExp("^" + word + "(\\." + word + ")*$");
		var domainPat=new RegExp("^" + atom + "(\\." + atom + ")*$");
		var matchArray=emailStr.match(emailPat);
		if (matchArray == null) 
		{
			return false;
		}
		var user=matchArray[1];
		var domain=matchArray[2];
		if (user.match(userPat) == null)
		{
			return false;
		}
		var IPArray = domain.match(ipDomainPat);
		if (IPArray != null) 
		{
			for (var i = 1; i <= 4; i++) 
			{
				if (IPArray[i] > 255) 
				{
					return false;
				}
			}
			return true;
		}
		var domainArray=domain.match(domainPat);
		if (domainArray == null) 
		{
			return false;
		}
		var atomPat=new RegExp(atom,"g");
		var domArr=domain.match(atomPat);
		var len=domArr.length;
		if ((domArr[domArr.length-1].length < 2) ||(domArr[domArr.length-1].length > 3)) 
		{
			return false;
		}
		if (len < 2) 
		{
			return false;
		}
		return true;
	}



//对字符串进行处理
//oralString 传入的字符串
function dealString(oralString)
{
	return oralString.replace("\"","\\\"");
}

function imgChange(imgObj)
{
	
	var theRate = imgObj.height/imgObj.width;
	if(theRate>1)
	{
		alert(1);
		imgObj.height = 450;
		imgObj.width = imgObj.width/theRate;
	}
	else
	{
		imgObj.width = 450;
		imgObj.height = imgObj.height*theRate;
	}

}


/**设置快捷键*/
var keyArray = new Array();
var objArray = new Array();
function addArray(key,objName)
{
	 var nLength = keyArray.length;
	 keyArray[nLength] = key;
	 objArray[nLength] = objName;
}


function trigEasyCode()
{		
	if(keyArray == null || objArray == null || keyArray.length !=objArray.length)
	{
			return;
	}
	for(var i = 0; i < keyArray.length; i++)
	{
		if(event.keyCode==keyArray[i]&&event.altKey)
		{
			if(isNaN(eval("document.forms[0].elements[\'"+objArray[i]+"\']").length)||eval("document.forms[0].elements[\'"+objArray[i]+"\']").type=="select-one")
			{
                if(eval("document.forms[0].elements[\'"+objArray[i]+"\']") != null
                && eval("document.forms[0].elements[\'"+objArray[i]+"\']") != undefined)
                {
				    eval("document.forms[0].elements[\'"+objArray[i]+"\']").focus();
			    }
            }
			else
			{
                if(eval("document.forms[0].elements[\'"+objArray[i]+"\']") != null
                && eval("document.forms[0].elements[\'"+objArray[i]+"\']") != undefined)
                {
					eval("document.forms[0].elements[\'"+objArray[i]+"\']")[0].focus();
                }
			}
			return;	
		}
	}	
}  

function escOut()
{
	if(event.keyCode == 27)	
		window.close();
}

	//add:feiluo
	//按整数位算出最大的值
	function getIntDigit(intDigit)
	{
		var intValue=0;
		if(intDigit==3)
		{
			intValue=999;
		}
		
		if(intDigit==5)
		{
			intValue=99999;
		}
		if(intDigit==6)
		{
			intValue=999999;
		}
		if(intDigit==7)
		{
			intValue=9999999;
		}
		if(intDigit==8)
		{
			intValue=99999999;
		}
		if(intDigit==9)
		{
			intValue=999999999;
		}
		if(intDigit==10)
		{
			intValue=9999999999;
		}		
		return intValue;		
	}

/*create:feiluo 2005-10-24
*校验是否是整数
*intDigit：整数位数*
*/
	function IsNumeric(obj,intDigit)
	{
			var str="0123456789";
			var maxNum=getIntDigit(intDigit);			
			var txt=obj.value;
			if(txt==null||txt=="")
			{
				obj.value="0";
				return obj.value;
			}
			if(txt<=0)
			{
				obj.value="0";
				return obj.value;
			}
	 		//if(txt!=""&&txt!=null)
	 		else
	 		{  		
		 		for(i=0;i<txt.length;i++)
		 		{
		 			var strdata=txt.charAt(i);
		 			if(str.indexOf(strdata)==-1)
		 			{	 		
		 				obj.value="0";
		 				return obj.value;
		 			}
		 		}
		 		//如果整数前面有0存在
		 		if(txt.charAt(0)<=0)
	 			{	 				
		 			for(k=0;k<txt.length;k++)
		 			{		 					 				
			 			if(txt.charAt(k)<=0)
			 			{
			 				newtxt1=txt.substring(k+1,txt.length);			 				
			 			}
			 			else
			 			{
			 				obj.value=newtxt1;			 				
			 				break;
			 			}
			 		}
			 	}
			 	else
			 	{
			 		obj.value=txt;
			 	}		 		
	 			if(obj.value>maxNum)
	 			{
	 				obj.value="0";	 				
	 			}	 			
	 			return obj.value;
	 		}
		 return obj.value;
	}

 /*create:feiluo 2005-10-24
 *intDigit:整数位数
 *pointDight:小数位数
 *校验是否是小数，如果是整数则保留两位小数
 *如果是不合法的字符则为0.00
 */
 function isNumericVSPoint(obj,intDigit)
 { 		
 		var str="0123456789.";
 		var maxNum=getIntDigit(intDigit)+".99"; 		
 		var txt=obj.value; 		
 		if(txt<=0)
		{
				obj.value="0.00";
				return obj.value;
		}
 		if(txt!=""&&txt!=null)
 		{  		
	 		for(i=0;i<txt.length;i++)
	 		{
	 			var strdata=txt.charAt(i);
	 			if(str.indexOf(strdata)==-1)
	 			{	 		
	 				obj.value="0.00";
	 				return obj.value;
	 			}
	 		}
	 		//是否是小数
	 		if(txt.indexOf(".")>-1)
	 		{	 			
	 			if(txt.indexOf(".")==0||(txt.lastIndexOf(".")>txt.indexOf(".")))
	 			{ 				
	 				obj.value="0.00";
	 				return obj.value;
	 			}
	 			inx=txt.indexOf(".");
	 			if((txt.length-1)==inx)
	 			{
	 				txt=txt+"00";
	 			}	 			
	 			//小数点前整数
	 			inttxt=txt.substring(0,inx);	 		
	 			//小数点后整数	 		
	 			pointtxt=txt.substring(inx+1,txt.length);	
	 			//判断小数点前整数是否为合法数.比如00123等
	 			if(inttxt.charAt(0)<=0)
	 			{
	 				var newinttxt="";
	 				for(j=0;j<inttxt.length;j++)
		 			{		 						 				
		 				if(inttxt.charAt(j)<=0)
		 				{
		 					newinttxt=inttxt.substring(j+1,inttxt.length);
		 				}
		 				else
		 				{
		 					inttxt=newinttxt;
		 					break;
		 				}
		 			}
	 			}
	 			if(pointtxt.length==1)
	 			{	 			
	 				pointtxt=pointtxt+"0";
	 			}
	 			obj.value=inttxt+"."+pointtxt;
	 			//四舍五入保留两位
		 		obj.value=getRoundAmount(obj.value,2);		 		
	 			if(parseFloat(obj.value,2)>maxNum)
	 			{ 				 				
	 					obj.value="0.00";
	 					return obj.value;
	 			}
	 		}
	 		else
	 		{	 		
	 			var newtxt1="";	 		
	 			if(txt.charAt(0)<=0)
	 			{	 				
		 			for(k=0;k<txt.length;k++)
		 			{		 					 				
			 			if(txt.charAt(k)<=0)
			 			{
			 				newtxt1=txt.substring(k+1,txt.length);			 				
			 			}
			 			else
			 			{
			 				obj.value=newtxt1+".00";
			 				break;
			 			}
			 		}
			 	}
			 	else
			 	{
			 		obj.value=txt+".00";
			 	}			 
	 			if(parseFloat(obj.value)>maxNum)
	 			{
	 				obj.value="0.00";	 				
	 			}	 			
	 			return obj.value;
	 		}
	 	}
	 	else
	 	{	 		
	 		obj.value="0.00";
	 	}	 		 	
 		return obj.value;
}


/*
*确认信息,可根据项目情况进行封装
*create by jianwu
*/
function issConfirm(sMsg)
{
		if(confirm(sMsg+"\n\n点击\"确定\"按钮继续\n点击\"取消\"按钮返回"))
		{
			return true;
		}
		else
		{
			return false;
		};
}

/**
 * 反向格式化金额,去掉逗号
 * 自动设置焦点
 * @param strData 需要格式化的数据
 * @return 返回反格式化的金额
 */
 function reverseFormatAmount(thisPoint,lIsMin)
 {
		var i,strTemp;
		var strData = thisPoint.value;
		//去掉所有的","
		strData = reverseFormatAmountString(strData);
		thisPoint.value=strData;
		thisPoint.select();
 }
 /**
 *strAmount 需要反向格式化的金额字符串
 *return 反向格式化好的金额字符串
 */
 function reverseFormatAmountString(strAmount)
 {
 		//去掉所有的","
		strTemp=new String(strAmount);
		strAmount="";
		for(var i=0;i<strTemp.length;i++)
		{
			var cData;
			cData=strTemp.charAt(i);
			if (cData!=",")
			{
				strAmount=strAmount+cData;
			}
		}
		return strAmount;
 }
 
 /**
 * 格式化金额，自动设置焦点
 * @param strData 需要格式化的数据控件指针
  * @param lIsMin 是否可以为负数，1表示为正，-1表示为负，0表示可以为正可以为负
 * @return 设置数据控件的值为格式化后的金额字符串
 */
 function formatAmount(thisPoint,lScale,lIsMin)
 {
 	var strData = thisPoint.value;
	strData = reverseFormatAmountString(strData);
	strData = parseFloat(strData);
	if (strData>999999.99)
		strData = 0.00;
	strData = formatAmountString(strData,lScale,lIsMin);
	thisPoint.value=strData;
 }
 /*
 *
 */
 function getRoundAmount(strData,lScale)
 {
 	var lRectify = 1;
	if (lScale==1)
		lRectify = 10;
	else if (lScale==2)
		lRectify = 100;
	else if (lScale==3)
		lRectify = 1000;
	else if (lScale==4)
		lRectify = 10000;
	else if (lScale==5)
		lRectify = 100000;
	return Math.round(parseFloat(strData)*lRectify)/lRectify;
	/*var strDataCopy = strData;
	strDataCopy = Math.abs(strData);
	var add = 0;
    var s,temp;
    var s1 = strDataCopy + "";
    var start = s1.indexOf(".");
    if(start > 0 && s1.substr(start+lScale+1,1)>=5)add=1;
    var temp = Math.pow(10,lScale);
    s = Math.floor(strDataCopy * temp) + add;
	if (strData >= 0)
    	return s/temp;
	else
		return -1 * s/temp;*/
 }
 /*
 *strData 
 * * @param lIsMin 是否可以为负数，1表示为正，-1表示为负，0表示可以为正可以为负
 */
 function formatAmountString(strData,lScale,lIsMin)
 {
	if(!isNaN(parseFloat(strData)))
 	{
		if(strData!=null)
 		{
			var i,strTemp;

			//去掉所有的","
			strTemp=new String(strData);
			strData="";
			var isValidZero = true;
			var isValidComma = true;
			for(i=0;i<strTemp.length;i++)
			{
				var cData;
				cData=strTemp.charAt(i);
				if(cData=="-" && i==0 && (lIsMin==null || lIsMin=="undefined" || lIsMin == 0 || lIsMin == -1))
				{
					strData = "-";
				}
				else 
				if (cData=="0")
				{
					if (isValidZero)
						strData = strData + cData;
				}
				else
				if (cData==".")
				{
					if (strData!="" && isValidComma)
					{
						strData = strData + cData; 
						isValidComma=true;
					}
				} 
				else
				if (cData!="," && cData!=" ")
				{
					if (!isNaN(cData) || cData==".")
					{
						strData=strData+cData;
						isValidZero = true;
					}
					else
					{
						strData="";
						i=10000;
					}
				}
			}
		}
		if(strData!="")
 		{
			var strRoundAmunt ;
			strRoundAmunt = getRoundAmount(strData,lScale);
			strData = "" + strRoundAmunt;
			//将小数点前和后的数据分别取出来
	 		var nPoint;
	 		nPoint=strData.indexOf(".");
	 		var strFront=strData,strEnd="";
	 		if(nPoint!=-1)
	 		{
	 			strFront=strData.substring(0,nPoint);
	 			strEnd=strData.substring(nPoint+1,strData.length);
	 		}

			//小数点前面的数据加","
			strTemp=new String(strFront);
			var bHaveMinus=false;
			if(strFront.substring(0,1)=="-")
			{
				bHaveMinus=true;
				strTemp=strTemp.substring(1,strTemp.length);
			}
			strFront="";
			var nNum;
			nNum=0;
			for(i=strTemp.length-1;i>=0;i--)
			{
				if(nNum==3)
				{
					strFront=","+strFront ;
					nNum=0;
				}
				nNum++;
				var cData;
				cData=strTemp.charAt(i);
				strFront=cData+strFront;
			}
			if(bHaveMinus)
			{
				strFront="-" + strFront;
			}

			//补或者截小数点后面的值，保持两位
	 		if(strEnd.length>lScale)
	 		{
	 			strEnd=strEnd.substring(0,lScale);
	 		}
	 		else
	 		{
	 			for (i=strEnd.length;i<lScale;i++)
					strEnd = strEnd + "0";
	 		}
			//如果需要小数位
			if (parseInt(lScale,10)> 0)
				{
			 		strData=strFront+"." + strEnd;
				}
			else
				{
					strData=strFront;
				}

 		}
		else
		{
			strData="";
		}
	}
	else
	{
		strData = "";
	}

	if (strData=="")
	{
		if (parseInt(lScale,10)> 0)
			{
				strData="0.";
				for (i=0;i<lScale;i++)
					{
						strData=strData+"0";
					}
			}
		else
			{
				strData="0";
			}
	}
	return strData;
 }
 
 
  
function formatAmountNoComma(thisPoint,lScale,lIsMin)
 {
 	var strData = thisPoint.value;
	strData = reverseFormatAmountString(strData);
	strData = parseFloat(strData);
	if (strData>999999999999.99)
		strData = 0.00;
	strData = formatAmountStringNoComma(strData,lScale,lIsMin);
	thisPoint.value=strData;
 }
function formatAmountStringNoComma(strData,lScale,lIsMin)
 {
	if(!isNaN(parseFloat(strData)))
 	{
		if(strData!=null)
 		{
			var i,strTemp;

			//去掉所有的","
			strTemp=new String(strData);
			strData="";
			var isValidZero = true;
			var isValidComma = true;
			for(i=0;i<strTemp.length;i++)
			{
				var cData;
				cData=strTemp.charAt(i);
				if(cData=="-" && i==0 && (lIsMin==null || lIsMin=="undefined" || lIsMin == 0 || lIsMin == -1))
				{
					strData = "-";
				}
				else 
				if (cData=="0")
				{
					if (isValidZero)
						strData = strData + cData;
				}
				else
				if (cData==".")
				{
					if (strData!="" && isValidComma)
					{
						strData = strData + cData; 
						isValidComma=true;
					}
				} 
				else
				if (cData!="," && cData!=" ")
				{
					if (!isNaN(cData) || cData==".")
					{
						strData=strData+cData;
						isValidZero = true;
					}
					else
					{
						strData="";
						i=10000;
					}
				}
			}
		}
		if(strData!="")
 		{
			var strRoundAmunt ;
			strRoundAmunt = getRoundAmount(strData,lScale);
			strData = "" + strRoundAmunt;
			//将小数点前和后的数据分别取出来
	 		var nPoint;
	 		nPoint=strData.indexOf(".");
	 		var strFront=strData,strEnd="";
	 		if(nPoint!=-1)
	 		{
	 			strFront=strData.substring(0,nPoint);
	 			strEnd=strData.substring(nPoint+1,strData.length);
	 		}

			//补或者截小数点后面的值，保持两位
	 		if(strEnd.length>lScale)
	 		{
	 			strEnd=strEnd.substring(0,lScale);
	 		}
	 		else
	 		{
	 			for (i=strEnd.length;i<lScale;i++)
					strEnd = strEnd + "0";
	 		}
			//如果需要小数位
			if (parseInt(lScale,10)> 0)
				{
			 		strData=strFront+"." + strEnd;
				}
			else
				{
					strData=strFront;
				}

 		}
		else
		{
			strData="";
		}
	}
	else
	{
		strData = "";
	}

	if (strData=="")
	{
		if (parseInt(lScale,10)> 0)
			{
				strData="0.";
				for (i=0;i<lScale;i++)
					{
						strData=strData+"0";
					}
			}
		else
			{
				strData="0";
			}
	}
	return strData;
 }

/**
 * 格式化金额，自动设置焦点
 * @param strData 需要格式化的数据控件指针
  * @param lIsMin 是否可以为负数，1表示为正，-1表示为负，0表示可以为正可以为负
 * @return 设置数据控件的值为格式化后的金额字符串
 */
 function formatAmount(thisPoint,lScale,lIsMin)
 {
 	var strData = thisPoint.value;
	strData = reverseFormatAmountString(strData);
	strData = parseFloat(strData);
	if (strData>999999999999.99)
		strData = 0.00;
	strData = formatAmountString(strData,lScale,lIsMin);
	thisPoint.value=strData;
 }
 
// function orderBy(thisForm,columnName){
//// 	var form;
//// 	if(typeof(thisForm)=='String'){
//// 		form = document.getElementById(thisForm);
//// 		form.orderBy.value = columnName;
//// 		form.submit();
//// 	}
// 	
// }
