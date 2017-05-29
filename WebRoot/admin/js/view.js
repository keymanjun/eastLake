function getAnalyseData(sPath,topicId,typeId,topicUrl)
{
	var screenWidth = (screen.availWidth*0.6);
	var screenHeight = (screen.availHeight*0.6);
	var sFeatures="scrollbars=yes,resizable=yes,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=100px,top=100px";
	var strURL=sPath+"/analyzer!getAnalyzerResult.action?topicId=" + topicId+"&typeId="+typeId +"&topicUrl="+topicUrl;
	window.open(strURL,'w_analyse',sFeatures);	
}

function getMoreZFInfo(sPath,typeId)
{
	var screenWidth = (screen.availWidth*0.6);
	var screenHeight = (screen.availHeight*0.6);
	var sFeatures="scrollbars=yes,resizable=yes,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=100px,top=100px";
	var strURL=sPath+"/analyzer!getMoreZFInfo.action?typeId="+typeId;
	window.open(strURL,'w_zfinfo',sFeatures);	
} 

//analyzerResult1.jsp 中调用此方法
function getMoreReviewInfo(sAppPath,nTopicId)
{
	var screenWidth = (screen.availWidth*0.6);
	var screenHeight = (screen.availHeight*0.6);
	var sFeatures="scrollbars=yes,resizable=no,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=100px,top=100px";
	var strURL=sAppPath+"/analyzer!getMoreReviewInfo.action?topicId=" + nTopicId;
	window.open(strURL,'w_morereview',sFeatures);
}	


//站点排名
function getMoreSiteInfo(sAppPath,typeId)
{
	var screenWidth = (screen.availWidth*0.6);
	var screenHeight = (screen.availHeight*0.6);
	var sFeatures="scrollbars=yes,resizable=yes,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=100px,top=100px";
	var strURL=sAppPath+"/analyzer!getMoreSortSiteInfo.action?typeId="+typeId;
	window.open(strURL,'w_siteinfo',sFeatures);	
} 


//analyzerResult1.jsp 中调用此方法
function getMoreSortAreaInfo(sAppPath,ntypeId)
{
	var screenWidth = (screen.availWidth*0.6);
	var screenHeight = (screen.availHeight*0.6);
	var sFeatures="scrollbars=yes,resizable=yes,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=100px,top=100px";
	var strURL=sAppPath+"/analyzer!getMoreSortAreaInfo.action?typeId=" + ntypeId;
	window.open(strURL,'w_morearea',sFeatures);
}

function getMoreAreaMapInfo(sAppPath,ntypeId)
{
	var screenWidth = (screen.availWidth*0.9);
	var screenHeight = (screen.availHeight*0.65);
	var sFeatures="scrollbars=yes,resizable=no,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=10px,top=50px";
	var strURL=sAppPath+"/analyzer!getMoreAreaMapInfo.action?typeId=" + ntypeId;
	window.open(strURL,'w_moreareamap',sFeatures);
}

//analyzerResult1.jsp 中调用此方法
function getLinkTopic(sAppPath,nTopicId)
{
	var screenWidth = (screen.availWidth*0.6);
	var screenHeight = (screen.availHeight*0.6);
	var sFeatures="scrollbars=yes,resizable=no,status=yes,titlebar=no,toolbar=no,menubar=no,location=no,width="+screenWidth+"px,height="+screenHeight+"px,left=100px,top=100px";
	var strURL=sAppPath+"/analyzer!getAnalyzerResult.action?topicId=" + nTopicId;
	//window.open(strURL,'w_morearea',sFeatures);
}

function doSearcher(appurl,svalue)
{
	document.ifrResult.location.href=appurl+svalue;	
}