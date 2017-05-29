function FileProgress(file, targetID) {
	this.fileProgressID = file.id;
	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
	
	 	this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "temp_con";
		this.fileProgressWrapper.id = this.fileProgressID;
				
		var imgc_con = document.createElement("div");
		imgc_con.className = "temp_privew";
		
		this.fileProgressWrapper.appendChild(imgc_con);
		
		document.getElementById(targetID).appendChild(this.fileProgressWrapper);
		
	} else {
		//this.fileProgressElement = this.fileProgressWrapper.firstChild;
	}
	
	
}


FileProgress.prototype.setStatus = function (status) {	
	this.fileProgressWrapper.childNodes[0].innerHTML=status;
};

FileProgress.prototype.setSuccess = function (serverdata,fileid) {
	var temp_data = serverdata.split(",");
	var name=  temp_data[0];
	var theme=  temp_data[1];
	var version=  temp_data[2];
	var author=  temp_data[3];
	var site=  temp_data[4];
	var template_id=  temp_data[5];
    if(temp_data.length > 1){
		var img =document.createElement("img");
		img.src="../../themes/"+theme +"/preview.jpg";
		img.width=120;
		img.height=160;
		this.fileProgressWrapper.childNodes[0].innerHTML="";
		this.fileProgressWrapper.childNodes[0].appendChild(img);
		
		var  textdiv =  document.createElement("div");
		textdiv.innerHTML =name +"("+version +")" +"<br/>"+author;
		textdiv.className = "temp_text";
		this.fileProgressWrapper.appendChild(textdiv);
		
		var  btndiv =  document.createElement("div");
		btndiv.id="temp-div";
		btndiv.innerHTML ="<a href=\"javascript:active("+template_id+");\" class=\"temp_btn\">使用模板</a>&nbsp;<a href=\"/themes/"+theme+".zip\" class=\"temp_btn\">下载模板</a>&nbsp;<a href=\"../template!delete.do?template_id="+template_id+"\" onclick=\"return confirm('确认删除此目标吗?')\" class=\"temp_btn\">删除模板</a>";	
		this.fileProgressWrapper.appendChild(btndiv);
	}else if (name == "null"){
	    alert("上传的模板不符合格式!");
	}else{
	    alert("上传的模板id重复!");
	}
	
};