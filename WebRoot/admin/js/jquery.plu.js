(function($) {     
	$.extend({      
	     getValueStrSelected: function(selectFlag) {   
	     	var ValueStr = "";
            var selectedArray = $("input[id='" + selectFlag + "']:checked");
            $.each(selectedArray, function(i, item){
            	ValueStr += $(item).attr("id") + "=" + $(item).val() + "&"; 
            })
			return ValueStr == "" ? ValueStr : ValueStr.substring(0, ValueStr.lastIndexOf('&'));
	     },
	     getSelectedValue: function(selectFlag) { 
		    var selectedArray = $('input[id="cbxItem"]:checked');
		  	var idArray = new Array();
			$.each(selectedArray,function(index, domEle){
				var id = $(domEle).val();
				idArray.push(id);
		  	});
		  	return idArray.join(",");
	  	},
	     checkedSelected: function(selectFlag) {
	     	var selectedArray = $("input[id='" + selectFlag + "']:checked");
			if(selectedArray.length == 0) {
				alert("请选择操作数据！");
				return false;
			}else {
				return true;
			}
	     },
	     oneCheckedSelected: function(selectFlag) {
	     	var selectedArray = $("input[id='" + selectFlag + "']:checked");
			if(selectedArray.length <= 0) {
				alert("请选择操作数据！");
				return false;
			}
			if(selectedArray.length > 1) {
				alert("一次只能操作一条数据！");
				return false;
			}
			return selectedArray[0];
	     },
	     validTextValue: function() {
	     	var sMsg = "";
	     	//非空校验
	     	$('input[demand]').each(function(index, domEle){
	     		var value = $.trim($(domEle).val());
	     		var message = "";
	     		if(!value) {
	     			var msg = $(domEle).attr("message");
	     			if(msg) message = msg; 
	     			sMsg += message+"不能为空!\n";
	     		}
	     	});
	     	//电话号码校验
	     	$('input[tel]').each(function(index, domEle){
	     		var value = $.trim($(domEle).val());
	     		var reg = /(^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)/; 												    				
		 		if(value) {
			 		if(!reg.test(value)) {
			 			var message = "电话号码";
			 			var msg = $(domEle).attr("format");
			 			if(msg) {
			 				sMsg += msg+"!\n";
			 			}else {
			 				msg = $(domEle).attr("message");
			 				if(msg) message = msg; 
		     				sMsg += message+"格式不对!\n";
			 			}
		 			}
		 		}
	     	});
	     	//电子邮件校验
	     	$('input[email]').each(function(index, domEle){
	     		var value = $(domEle).val();
	     		var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		 		if(value) {
			 		if(!reg.test(value)) {
			 			var message = "电子邮件";
			 			var msg = $(domEle).attr("format");
			 			if(msg) {
			 				sMsg += msg+"!\n";
			 			}else {
			 				msg = $(domEle).attr("message");
			 				if(msg) message = msg; 
		     				sMsg += message+"格式不对!\n";
			 			}
		 			}
		 		}
	     	});
	     	//邮政编码校验
	     	$('input[zip]').each(function(index, domEle){
	     		var value = $(domEle).val();
	     		var reg = /^[1-9]{1}[0-9]{5}$/;
		 		if(value) {
			 		if(!reg.test(value)) {
			 			var message = "邮政编码";
			 			var msg = $(domEle).attr("format");
			 			if(msg) {
			 				sMsg += msg+"!\n";
			 			}else {
			 				msg = $(domEle).attr("message");
			 				if(msg) message = msg; 
		     				sMsg += message+"格式不对!\n";
			 			}
		 			}
		 		}
	     	});
	     	return sMsg;
	     }
	});  
})(jQuery);   

$(document).ready(function() {
	$('#checkAll').click(function() {
		$("input[name='selectFlag']").attr("checked", this.checked); 
		$("input[name='selectFlag']:disabled").attr("checked", false);
	});	
	$('input[type="text"][!readonly]').each(function(i){
    	$(this).bind('focus',function(){
    		this.select();
    	});
    });
    $('input[float]').each(function() {
	 	$(this).bind('keyup',function(){
	 		var value = $.trim($(this).val());
	 		if((value.length <= 1) && (!(/[\d]/.test(value)))) $(this).val("");
	 		if(value.indexOf(".")>0) {
	 			var valueArray = value.split(".");
	 			var value1 = "";
	 			var value2 = "";
	 			if(valueArray[0]) {
	 				value1 = valueArray[0].replace(/[^\d]/g,'');
	 				var integer = $.trim($(this).attr("integer"));
	 				if(integer && parseFloat(value1.length)>parseFloat(integer)) {
	 					value1 = value1.substring(0,parseFloat(integer))
	 				}
	 			}
	 			if(valueArray[1]) {
	 				value2 = valueArray[1].replace(/[^\d]/g,'');
	 				var point = $.trim($(this).attr("point"));
	 				if(point && parseFloat(value2.length)>parseFloat(point)) {
	 					value2 = value2.substring(0,parseFloat(point))
	 				}
	 			}
	 			
	 			$(this).val(value1+"."+value2); 
	 		}else {
	 			var value1 = value.replace(/[^\d]/g,'');
	 		    var integer = $.trim($(this).attr("integer"));
	 			if(integer && parseFloat(value.length)>parseFloat(integer)) {
	 				value1 = value1.substring(0,parseFloat(integer))
	 			}
	 			$(this).val(value1);
	 		}
	 		
		});
		$(this).bind('blur',function(){
			var value = $.trim($(this).val());
	 		if(value.indexOf(".")>0) {
	 			var valueArray = value.split(".");
	 			var value2 = "00";
	 			if(valueArray[1]) {
	 				value2 = valueArray[1].replace(/[^\d]/g,'');
	 			}
	 			$(this).val(valueArray[0].replace(/[^\d]/g,'')+"."+value2); 
	 		}else {
	 			$(this).val($(this).val().replace(/[^\d]/g,''));
	 		}
		});
	});	
	$('input[num]').each(function() {
	 	$(this).bind('keyup',function(){
			$(this).val($(this).val().replace(/[^\d]/g,'')); 
		});
		$(this).bind('blur',function(){
			var value = $.trim($(this).val());
			if(value) {
				$(this).val($(this).val().replace(/[^\d]/g,''));
				var maxvalue = $(this).attr("maxvalue");
				if(maxvalue) {
					if(parseFloat($(this).val()) > parseFloat(maxvalue)) $(this).val(maxvalue);
				}
			}else {
				$(this).val("0");
			}
		});
	});	
});
