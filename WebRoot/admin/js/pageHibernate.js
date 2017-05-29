//
function checkNumber(no,min,max)
{
var tmp=/\d{1,20}/;
if(no.match(tmp)==null)
return false;
if(no.match(tmp)!=null)
{	
if(no<=min)
	return false;
if(no>=max)
	return false;
if(no>min && no<max)
	return true;
}
}

function locatePage(flag)
{
if(document.getElementById("pagerAction")==undefined || document.getElementById("pagerAction").value==""){
  alert("请在表单项里设置名称为pagerAction的隐藏域,用于分页所要调用的分页方法.")
  return;	
}
var iPageNo=document.all.currentPage.value;   
var iPageCount=document.all.pageCount.value;   
var iPageSize=document.all. pageSize.value;     	
if(flag==1)
{
  if(iPageNo!=1) {
  	iPageNo=1;
  	document.forms[0].startIndex.value="0";
  } 
	else
	{
		alert("已经是第一页！");
		return;
	}
}
else if(flag==2)
{
	if(iPageNo!=1){
	   iPageNo=parseInt(iPageNo)-1;
	   if(iPageNo==1) document.all.startIndex.value=0;
	   else{
	   	document.forms[0].startIndex.value=""+(parseInt(iPageNo-1) * parseInt(iPageSize));
	  }
	}
	else
	{
		alert("已经是第一页！");
		return;
	}
}
else if(flag==3){
	if(iPageNo!=iPageCount){
		document.forms[0].startIndex.value=""+(parseInt(iPageNo) * parseInt(iPageSize));
		iPageNo=parseInt(iPageNo)+1;  				
	}
	else
	{	
		alert("已经是最后一页！");
		return;
	}
}
else if(flag==4)
{
  var pageTotal=iPageCount;
	if(iPageNo!=pageTotal){
		iPageNo=pageTotal;
		document.forms[0].startIndex.value=""+(parseInt(pageTotal-1) * parseInt(iPageSize));
	}
	else
	{	
		alert("已经是最后一页！");
		return;
	}      			
}
document.getElementById("pageNo").value=iPageNo;
document.forms[0].action=document.getElementById("pagerAction").value;
document.forms[0].submit();
}

function jump(){
 if(!checkNumber(document.getElementById("pageNo").value,0,1000)){
  	alert("请输入数字！");
  	return;
 }
 document.forms[0].action=document.getElementById("pagerAction").value;
 document.forms[0].submit();
}

function changesize(){

  if(!checkNumber(document.getElementById("pageSize").value,0,1000)){
  	alert("请输入数字！");
  	return;
  }
  document.forms[0].action=document.getElementById("pagerAction").value;
  document.forms[0].submit();	   		
}
   
function isHasNextPage(pageTotal)
{
  if(pageTotal>1)
  {
    return true;
  }
  return false;
}