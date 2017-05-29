/**
添加一个商品配件
*/
/*function createAdjRow(goods){
	
	var temp_spec = document.getElementById("temp_adjunct");
 
			var tr = temp_spec.insertRow(-1);
			
			var cell = tr.insertCell(-1);
			cell.innerHTML = goods.name +'<input type="hidden" name="spec_id" value="' + goods.spec_id+'"/><input type="hidden" name="adj_name" value="' + goods.name +'"/><input type="hidden" name="adj_sn" value="' + goods.sn +'"/><input type="hidden" name="adj_price" value="' + goods.price +'"/><input type="hidden" name="adj_store" value="' + goods.store +'"/><input type="hidden" name="adj_goods_id" value="' + goods.goods_id +'"/>';

			
			cell = tr.insertCell(-1);		 
			cell.innerHTML ='<a href="javascript:;" onclick="javascript:deleteAdjRow(this);" >[x]</a>';
			
	 
}
 
function deleteAdjRow(link){
	var tr = link.parentNode.parentNode;
	tr.parentNode.removeChild(tr);
}

function addAdj(id_check_ar,name_ar,price_ar,store_ar,goods_id_ar,spec_id_ar){
	for(var i=0;i<id_check_ar.length;i++){
		var chk  = id_check_ar[i];
		if(chk.checked){
			if(!check_sn(chk.value)){
				var goods = new Object();
				goods.name= name_ar[i].value;
				goods.sn = chk.value;
				goods.price= price_ar[i].value;
				goods.store = store_ar[i].value;
				goods.goods_id = goods_id_ar[i].value;
				goods.spec_id = spec_id_ar[i].value
				createAdjRow(goods);
			}
		}
	}
	
	close_dialog();
}

function check_sn(sn){
	var sn_ar  = document.getElementsByName("adj_sn");
	for(var i=0;i<sn_ar.length;i++){
		
	   var s = sn_ar[i].value;
	   if(s==sn){
	   	 return true;
	   }
	}
	
	return false;
}*/




function zuhe(ar1,ar2){
	var ar = new Array();
	var str = "";
	var k=0;
	for(var i=0;i<ar1.length;i++){
		 str ="";
		for(var j=0;j<ar2.length;j++){
			str = ar1[i] +"," + ar2[j];
			ar[k] = str; 
			k++;
		}
	}
	
	return  ar;
}

function  zuhe1(spec_ar){
	var ar;
	var m =0 ;
	while(m<spec_ar.length-1)
	{
		if(m==0){
			ar = spec_ar[0];
		}
		
		ar = zuhe(ar,spec_ar[m+1]);
		m++;
	}
	return ar;
	
}


function getSpecString(spec){
	var json_str  ="{"
	json_str+="'name':'" + spec.name + "'";
	json_str+=",'options':["
		for(var i=0;i<spec.options.length;i++){
			if(i!=0) json_str+=",";
			json_str+="'" + spec.options[i] +"'";
		}
	json_str+="]"
	json_str+="}";
	
	return json_str;
}


function  getSpecArString(s_ar){
	var json_str  = "[";
	for(var i=0;i<s_ar.length;i++){
		var spec = s_ar[i];
		if(i!=0) json_str+=",";		
		json_str +=getSpecString(spec);
	}
	
	json_str+="]";	
	
	return json_str;
		
}

var specs = new Array(); //规格值的数组
var spec_ar = new Array(); //规格对象数组


function createTitle(spec_name){
	var tr_title = document.getElementById("spec_title");
	var name_cell = tr_title.insertCell(specs.length);
	name_cell.className = "spec_title";
    name_cell.innerHTML=  spec_name +"<a href='javascript:del_spec("+ specs.length  + ")'>[x]</a>";
}

function updateTitle(index){//更新规格title
	
	cleanRowsTitle();
	var tr_title  = document.getElementById("spec_title");
	var name_cell = tr_title.insertCell(0);
	name_cell.className = "spec_title";
    name_cell.innerHTML=  "货号";
	for(var i=1;i<=spec_ar.length;i++){
		 name_cell = tr_title.insertCell(i);
		name_cell.className = "spec_title";
	    name_cell.innerHTML=  spec_ar[i-1].name +"<a href='javascript:del_spec("+ i  + ")'>[x]</a>";
	}

	
	name_cell = tr_title.insertCell(spec_ar.length+1);
	name_cell.className = "spec_title";
    name_cell.innerHTML=  "库存";
    
	name_cell = tr_title.insertCell(spec_ar.length+2);
	name_cell.className = "spec_title";
    name_cell.innerHTML=  "价格";
}

function delTitle(index){
	var tr_title = document.getElementById("spec_title");

	var name_cell = tr_title.deleteCell(index-1);
	
	//重组规格对象
	var spec_ar1 = new Array();
	
	spec_ar1=spec_ar;
	spec_ar=new Array();
	var m=0;
	for(var n=0;n<spec_ar1.length;n++){
		if(n!=index-1){
			spec_ar[m]=spec_ar1[n];
			m++;
		}
	}
	
}

function cleanRows(){

	var temp_spec= document.getElementById("temp_spec");
	var rows= temp_spec.rows;
	
	for(var i=rows.length-1;i>=1;i--){
		temp_spec.deleteRow(i);
	}
	
	
}
function cleanRowsTitle(){
	
	var temp_spec= document.getElementById("spec_title");	
	var cells= temp_spec.cells;
	
	for(var i=cells.length-1;i>=1;i--){
		temp_spec.deleteCell(i);
	}
	temp_spec.deleteCell(-1);
}


function createSpecHtml(ar_spec){
	
	var temp_spec= document.getElementById('temp_spec');
	
	 cleanRows();
	 
	
	for(var i=0;i<ar_spec.length;i++){
	
			var tr = temp_spec.insertRow(-1);
			
			//货号
			var cell = tr.insertCell(-1);
			cell.className="spec_title";
			cell.innerHTML ='<input type="text" id="spec_sn" name="spec_sn" class="input_text"  style="width:80px" maxlength="20"/>';

			
	
			var spec_val_ar =ar_spec[i].split(',');
			for(var j=0;j< spec_val_ar.length;j++){
						
				//规格名称
				cell = tr.insertCell(-1);
				cell.className="spec_title";
				cell.innerHTML = spec_val_ar[j] +'<input type="hidden" name="spec_value_'+ i +'" value="'+ spec_val_ar[j] +'"  style="width:80px"/>';
	
						
			}
		
			//库存
			cell = tr.insertCell(-1);
			cell.className="spec_title";
			cell.innerHTML ='<input type="text" name="spec_store" class="input_text"  style="width:80px" maxlength="7"/>';
			
			//价格
			cell = tr.insertCell(-1);
			cell.className="spec_title";
			cell.innerHTML ='<input type="text" name="spec_price" class="input_text"  style="width:80px" maxlength="4"/>';			
		
	}

		
}
function cutNull(values){//去除多余的回车
	var j=0;
	var arr= new Array();;
	for(var i=0;i<values.length;i++){
		values[i] = values[i].replace(/\r/i,'');
		if(values[i] != "" && values[i] != '\n' && values[i] != '\r'){
			arr[j]=values[i];
			j++;
		}
	}
	return arr;
}


function makeSpecArray(){
	var row_spec= document.getElementById('temp_spec').rows;
	if(row_spec<2) return ;
	var celllen = row_spec[0].cells.length;
	var j=1;
	for(var j=1;j<celllen-2;j++){
		var tempStr="";
		var oldStr="";
		for(var i=1;i<row_spec.length;i++)
		{
		   var ss=row_spec[i].cells[j].outerText;
		   if(ss!=oldStr){
		     tempStr+=ss+",";
		   }
		   oldStr=ss;
		}
		tempStr=tempStr.substring(0,tempStr.length-1);
		var spec_obj = new Object();
		spec_obj.name = row_spec[j].cells[j].outerText;
		spec_obj.options=tempStr.split(",");
		spec_ar[spec_ar.length] = spec_obj;		
		specs[specs.length] = tempStr.split(",");	
	}
}

function add_spec(name,value_str){

	var values = value_str.split('\n');
	values=cutNull(values);
	var spec_obj = new Object();
	spec_obj.name = name;
	spec_obj.options =  values
	spec_ar[spec_ar.length] = spec_obj; //不断的向数组中压值
	
	if(specs.length==0){ //第一次显示规格定义表格
		var temp_spec = document.getElementById("temp_spec");
		temp_spec.style.display='block';
		//var spec0 = document.getElementById("spec_0");
		//spec0.style.display='none';
	}
	
	
	specs[specs.length] = values;	
	var ar_spec   ;	
	if(specs.length>1){
		ar_spec  = zuhe1(specs);
	} else{
		ar_spec =specs[0];
	}
		
	createTitle(name);
	createSpecHtml(ar_spec);
}



function del_spec(index){
	delTitle(index);
	

	
	//删除要删除的规格,并重组规格
	var temp_ar  = new Array();
	var k=0;
	for(var i=0;i<specs.length;i++){
		if((index-1)!=i){
			temp_ar[k]  = specs[i] ;
			k++;
		}
	}
	
	specs = temp_ar;
	
	if(specs.length==0){ //最后一个了
		cleanRows();
		var temp_spec = document.getElementById("temp_spec");
		temp_spec.style.display='none';
		//var spec0 = document.getElementById("spec_0");
		//spec0.style.display='block';		
		return ;
	}
	
	var ar_spec   ;	
	if(specs.length>1){
		ar_spec  = zuhe1(specs);
	} else{
		ar_spec =specs[0];
	}
	
	updateTitle(index);
	createSpecHtml(ar_spec);
}

