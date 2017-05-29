
	var specName = new Array();
	var specValue = new Array();

	function createSpecHtml(spectd_value, spectr_value)
	{
		var spectable = window.opener.document.getElementById("temp_spec");
		var specpr = spectable.insertRow(0);
		
		createSpecTd(spectd_value, specpr);
		
		spectable.style.display="";
	}
	
	function createSpecTd(spectd_value, specpr)
	{
		var ttd = "";
		for(var i=0;i<spectd_value.length;i++)
		{
			specpr.insertCell(i);
			specpr.innerHTML('<a href="javascript:;" onclick="javascript:deleteAdjRow(this);" >[x]</a>');
		}
		return ttd;
	}
	
	function addSpec(name, values)
	{
		specName = name;
		specValue = values.split('\n');
		specValue=cutNull(specValue);
	}
	
	//去除多余的回车
	function cutNull(values)
	{
		var j=0;
		var arr= new Array();
		for(var i=0;i<values.length;i++)
		{
			values[i] = values[i].replace(/\r/i,'');
			if(values[i] != "" && values[i] != '\n' && values[i] != '\r'){
				arr[j]=values[i];
				j++;
			}
		}
		return arr;
	}