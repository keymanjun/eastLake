dojo.provide("eastLake.widgets.CertificatePartialList");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.Button");
dojo.require("dijit.layout.BorderContainer");

dojo.declare("eastLake.widgets.CertificatePartialList", [dijit._Widget,dijit._Templated], {
	widgetsInTemplate: true,
    templatePath: dojo.moduleUrl("eastLake", "widgets/templates/certificatePartialList.html"),
    certificateListData:null,
    employeeId:-1,
    contentTitle:"",
    contentString:"",
    // summary:
	//		initialize
	// description:
	//		Initialize some widget
    postCreate: function(){
    	this.contentTitle = "<table width='100%' border='0' cellpadding='0' cellspacing='0'>"+
							"<tr align='center'>"+
								"<td width='5%' class='list_title'>"+
								"<input type='checkbox' id='cbxAll' name='cbxAll' onclick=\"selectForCbxAll(this,'cbxItem')\"/><br></td>"+
								"<td class='list_title' nowrap width='5%'>序号<br></td>"+
								"<td class='list_title' nowrap width='10%'><a href=# onclick=\"javascript:orderBy('certificateInfoAction','ciCode');\">证书编号<br></a></td>"+
								"<td class='list_title' nowrap width='5%'><a href=# onclick=\"javascript:orderBy('certificateInfoAction','ciName');\">证书名称<br></a></td>"+				      									      
								"<td class='list_title' nowrap width='10%'><a href=# onclick=\"javascript:orderBy('certificateInfoAction','ciOffice');\">发证机关<br></a></td>"+
								"<td class='list_title' nowrap width='10%'><a href=# onclick=\"javascript:orderBy('certificateInfoAction','ciDate');\">发证日期<br></a></td>"+
								"<td class='list_title' nowrap width='10%'><a href=# onclick=\"javascript:orderBy('certificateInfoAction','ciNotes');\">备注<br></a></td></tr>";
		
		this.addButton = new dijit.form.Button({
			label:"添加"
		});
		this.button_div.addChild(this.addButton);
		dojo.connect(this.addButton,"onClick",this,"toAddCertificate");
		
		this.updateButton = new dijit.form.Button({
			label:"修改"
		});
		this.button_div.addChild(this.updateButton);
		dojo.connect(this.updateButton,"onClick",this,"toUpdateCertificate");
		
		this.delButton = new dijit.form.Button({
			label:"删除"
		});
		this.button_div.addChild(this.delButton);
		dojo.connect(this.delButton,"onClick",this,"deleteCertificate");
    },
    initialize: function(){
    	this.updateDataGrid();
    	this.sumbitCertificate();
    },
    updateDataGrid:function(){
    	var that = this;
    	var param = {id:(this.employeeId)};
    	var xhrArgs = {
    			url:"certificateInfoPartialAction.action",
                handleAs: "json",
                content:param,
                load: function(data) {
                	this.certificateListData = eval('(' + data.JSONLIST + ')');
                	if(this.certificateListData!=null){
                		var length = this.certificateListData.length;
                		that.contentString="";
                		for(var i=0;i<length;i++){
                			that.contentString = that.contentString+"<tr align='center' onmouseout='reChangeBackground(this)' onmouseover='changeBackground(this)'>"+
            					                "<td class='list_content'>"+
            					                "<input type='checkbox' id='cbxItem' name='cbxItem' value='"+this.certificateListData[i].ciId+"' onclick=\"selectForCbxItem(this,'cbxAll')\"/><br></td>"+
            					                "<td nowrap  class='list_content'>"+(i+1)+"<br></td>"+
            					                "<td class='list_content' nowrap>&nbsp;"+this.certificateListData[i].ciCode+"</td>"+
            					                "<td class='list_content' nowrap>&nbsp;"+this.certificateListData[i].ciName+"</td>"+
            					                "<td class='list_content' nowrap>&nbsp;"+this.certificateListData[i].ciOffice+"</td>"+
            					                "<td class='list_content' nowrap>&nbsp;"+this.certificateListData[i].ciDate+"</td>"+
            					                "<td class='list_content' nowrap>&nbsp;"+this.certificateListData[i].ciNotes+"</td></tr>"
                		}
                	}
                	contentString = contentString+"</table>";
                	that.data_list.innerHTML = that.contentTitle+that.contentString;
//                	var partialList = new eastLake.widgets.CertificatePartialList({
//                		certificateListData:certList
//                	},dojo.byId("list_div"));
//              	dijit.byId('formDialog').hide();
                },
                error: function(error) {
                    //We'll 404 in the demo, but that's okay.  We don't have a 'postIt' service on the
                    //docs server.
                	that.data_list.innerHTML = error;
//                    dijit.byId('formDialog').hide();
                }
            }
            //Call the asynchronous xhrPost
            this.data_list.innerHTML = "数据正在载入中。。。。。。。。。。。";
            var deferred = dojo.xhrPost(xhrArgs);
    },
    toAddCertificate:function(){
        dojo.byId("ciId").value = "";
        dojo.byId("eiId").value = this.employeeId;
        dojo.byId("ciStatus").value = 1;
        dojo.byId("ciCode").value = "";
        dojo.byId("ciName").value = "";
        dojo.byId("ciOffice").value = "";
        dojo.byId("ciDate").value = "";
        dojo.byId("ciNotes").value = "";
    	dijit.byId("formDialog").show();
    },
	toUpdateCertificate:function(){
		var chxList = dojo.query('input[type=checkbox]:checked');
		if(chxList.length ==1){
            if(chxList[0].name=="cbxItem"){
                var param = {id:chxList[0].value};
                dojo.xhrGet({
                    url:"certificateInfoPartialAction!selectEntityById.action",
                    handAs:"json",
                    content:param,
                    load:function(response){
                    	var certObj = eval('(' + response + ')');
                    	dojo.byId("ciId").value = certObj.JSONRETURN.ciId;
                        dojo.byId("eiId").value = certObj.JSONRETURN.eiId;
                        dojo.byId("ciStatus").value = certObj.JSONRETURN.ciStatus;
                        dojo.byId("ciCode").value = certObj.JSONRETURN.ciCode;
                        dojo.byId("ciName").value = certObj.JSONRETURN.ciName;
                        dojo.byId("ciOffice").value = certObj.JSONRETURN.ciOffice;
                        dojo.byId("ciDate").value = certObj.JSONRETURN.ciDate;
                        dojo.byId("ciNotes").value = certObj.JSONRETURN.ciNotes;
                        dijit.byId("formDialog").show();
                    },
                    error:function(response){
                        dojo.byId("DialogContext").innerHTML = response;
                        dijit.byId("formDialog").show();
                    }
                });
            }
        }else{
        	alert("请选择一条记录!");
        	return;
        }
    },
	deleteCertificate:function(){
		var that = this;
		var chxList = dojo.query('input[type=checkbox]:checked');
		if(chxList.length >0){
			if(confirm("是否将此证书信息删除?")){
				var chxlen = chxList.length;
				var returnString = "";
				for(var i=0;i<chxlen;i++){
					if(chxList[i].name=="cbxItem"){
						returnString = returnString+chxList[i].value+",";
					}
				}
            	if(returnString!=null){
	                var param = {id:returnString.substring(0,returnString.length-1)};
	                dojo.xhrGet({
	                    url:"certificateInfoPartialAction!deleteEntityById.action",
	                    handAs:"json",
	                    content:param,
	                    load:function(response){
	                    	alert(" 删除成功！");
	                    that.updateDataGrid();	
	                    },
	                    error:function(response){
	                        dojo.byId("DialogContext").innerHTML = response;
	                        
	                    }
	                });
            	}
			}
        }else{
        	alert("请选择一条记录!");
        	return;
        }	
    },
	sumbitCertificate: function(){
		var that = this;
    	var form = dojo.byId("certificatePartialForm");
        dojo.connect(form, "onsubmit", function(event) {
            //Stop the submit event since we want to control form submission.
            dojo.stopEvent(event);
            //The parameters to pass to xhrPost, the form, how to handle it, and the callbacks.
            //Note that there isn't a url passed.  xhrPost will extract the url to call from the form's
            //'action' attribute.  You could also leave off the action attribute and set the url of the xhrPost object
            //either should work.
            var xhrArgs = {
                form: dojo.byId("certificatePartialForm"),
                handleAs: "json",
                load: function(data) {
//                	var certList = eval('(' + data.JSONLIST + ')');
//                	var partialList = new eastLake.widgets.CertificatePartialList({
//                		certificateListData:certList
//                	});
//                	dijit.byId("list_div").addChild(partialList);
//                	partialList.initialize();
                	that.updateDataGrid();
                	dijit.byId("formDialog").hide();
                },
                error: function(error) {
                    //We'll 404 in the demo, but that's okay.  We don't have a 'postIt' service on the
                    //docs server.
                    dojo.byId("data_list").innerHTML = error;
                    dijit.byId("formDialog").hide();
                }
            }
            //Call the asynchronous xhrPost
            that.data_list.innerHTML = "数据正在载入中。。。。。。。。。。。";
            var deferred = dojo.xhrPost(xhrArgs);
        });
    }
})