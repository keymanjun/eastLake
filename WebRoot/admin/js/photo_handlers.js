function fileDialogComplete(numFilesSelected, numFilesQueued){
	this.debug("-------选择了上传文件 "  +  numFilesSelected + "个" );
	this.startUpload();
}

function uploadStart(file) {
	var progress = new FileProgress(file, this.customSettings.progressTarget);
	progress.setStatus("上传" + file.name );
	this.debug("-------上传文件 "  +  file.name );
}

function uploadProgress(file, bytesLoaded, bytesTotal){
	var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
	var progress = new FileProgress(file, this.customSettings.progressTarget);
	progress.setProgress( percent);
	
	this.debug("-------正在上传文件 "  +  file.name +" file id is " + file.id );
}


function uploadSuccess(file, serverData) {

	var names = serverData.split(",");
	var goodsPic = document.getElementById("goodsPicsNames");
	 if(typeof(goodsPic)!="undefined"){
	 	document.getElementById("goodsPicsNames").value += names[1]+",";
	 }
	var progress = new FileProgress(file, this.customSettings.progressTarget);
	 
	 progress.setSuccess(names[1],file.id);
	 if(file.id=='SWFUpload_0_0'){
	 		
	 		viewPic(names[0],file.id);
	 }
	 
 
	this.debug("-------上传文件 "  +  file.name +" 完成 " + serverData );

	 
}

function uploadComplete(file) {
	this.debug("-------上传文件111111 "  +  file.name +" 完成 " +this.getStats().files_queued );
}


function viewPic(thumbName,fileid){

 	 var img  = document.createElement("img");
	 img.src= thumbName;
	 img.width=240;
	 img.height=220;
	 img.id= "img_" + fileid
	 var pre = document.getElementById("imgPrivew");
	 pre.innerHTML ="";
	 pre.appendChild(img);
	
	 document.getElementById("defaultpic").value=thumbName;
}

function deletePic(id,goodsid){
	 var pre = document.getElementById("imgPrivew");
	 var paths = document.getElementById("apppath").value;
	 var defaultpic = pre.childNodes[0];
	var con = document.getElementById(id);
	var imgName= con.getElementsByTagName("img")[0].src;
	var url = paths+'/uploadPhoto!delPhoto.action';
	$.ajax({
		type: "GET",
		url: paths+'/uploadPhoto!delPhoto.action',
		data:"photoName="+imgName+"&goodsid="+goodsid+"&rdm=" + new Date().getTime(),
		dataType:"json",
		success:function(result){
		   if(result.result==0){
				con.parentNode.removeChild(con);
				if(defaultpic.id.replace('img_','') == id.replace('container_','')) { //正在删除默认图片
					var inputs =  document.getElementById("uploadProgress").getElementsByTagName("input");
					
					if(inputs.length>0){
						var defaultInput = inputs[0];
					
					 	var defname = defaultInput.value;
					 	viewPic(defname,defaultInput.id.replace('input_',''));
				 	}else if(inputs.length==0){
				 		pre.innerHTML="<p style=\"margin-top:100px\">此处显示商品页默认图片<br/>[您还未上传商品图片！]</p>";
				 		document.getElementById("defaultpic").value="";
				 	}
				}else{
					//alert(defaultpic.id.replace('img_','') +"--" + id.replace('container_','') );
				}			   
		   }else{
  				alert(result.message);
  			}
		 
		},
		 error :function(res){alert("请求失败" + res);}
	});
	
	if(goodsid) {
		$.ajax({
		type: "GET",
		url: paths+'/goodsAction!delPhoto.action',
		data:"photoName="+imgName+"&goods.goodsId="+goodsid+"&rdm=" + new Date().getTime(),
		dataType:"json",
		success:function(result){
		   
		},
		 error :function(res){}
	});
	}

}


