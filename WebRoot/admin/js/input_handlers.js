

//加载属性定义的输入html
function loadPropsInput(type_id){
	$.ajax({
	type: "get",
	url:"goods!disPropsInput.do",
	data:"type_id=" + type_id +"&m=" + new Date().getTime(),
	dataType:"html",
	success:function(result){
	   document.getElementById("custom_props").innerHTML = result;
	  
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}



//加载参数定义的输入html
function loadParamsInput(type_id){
	$.ajax({
	type: "get",
	url:"goods!disParamsInput.do",
	data:"type_id=" + type_id +"&m=" + new Date().getTime(),
	dataType:"html",
	success:function(result){
	 
		try{
	   	 document.getElementById("custom_params").innerHTML = result;
	   	 
	    }catch(e){}
	//    alert( document.getElementById("custom_params").innerHTML);
	  
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}


//加载参数定义的输入html
function loadBrandsInput(type_id){
	$.ajax({
	type: "get",
	url:"goods/brand_input_panel.jsp",
	data:"type_id=" + type_id +"&m=" + new Date().getTime(),
	dataType:"html",
	success:function(result){
	   document.getElementById("custom_brands").innerHTML = result;
	  
	},
	 error :function(res){alert("异步读取失败:" + res);}
	});
}


function type_change_event(type_id){
		loadBrandsInput(type_id);
		loadPropsInput(type_id);
		loadParamsInput(type_id);
}

//切换类型
function changeType(cat_id){
	if(confirm("是否根据所选分类的默认类型重新设定商品类型？\n如果重设，可能丢失当前所输入的类型属性、关联品牌、参数表等类型相关数据。")){
		var type_id = types["" + cat_id] ;
		Utils.sel_selected("type_id",type_id);
		type_change_event(type_id);
	}

}
