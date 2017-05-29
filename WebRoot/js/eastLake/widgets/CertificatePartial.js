dojo.provide("eastLake.widgets.CertificatePartial");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit.form.ComboBox");
dojo.require("dijit.form.TextBox");
dojo.require("dijit.form.Button");
dojo.require("dijit.layout.BorderContainer");
dojo.require("eastLake.widgets.CertificatePartialList");

dojo.declare("eastLake.widgets.CertificatePartial", [dijit._Widget,dijit._Templated], {
	widgetsInTemplate: true,
    templatePath: dojo.moduleUrl("eastLake", "widgets/templates/certificatePartial.html"),
    // summary:
	//		initialize
	// description:
	//		Initialize some widget
    postCreate: function(){
        this.closeButton = new dijit.form.Button({
            label:"关闭",
            onClick:function(){
            	dijit.byId("formDialog").hide();
            }
        });
    	this.button_div.addChild(this.closeButton);
    },
    initialize: function(){
    	this.sumbitCertificate();
    },
    
    sumbitCertificate: function(){
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
                	var certList = eval('(' + data.JSONLIST + ')');
                	var partialList = new eastLake.widgets.CertificatePartialList({
                		certificateListData:certList
                	});
                	dijit.byId("list_div").addChild(partialList);
                	partialList.initialize();
//              	dojo.byId("list_div").innerHTML = result;
              	dijit.byId('formDialog').hide();
                },
                error: function(error) {
                    //We'll 404 in the demo, but that's okay.  We don't have a 'postIt' service on the
                    //docs server.
                    dojo.byId("list_div").innerHTML = error;
                    dijit.byId('formDialog').hide();
                }
            }
            //Call the asynchronous xhrPost
            dojo.byId("list_div").innerHTML = "数据正在载入中。。。。。。。。。。。";
            var deferred = dojo.xhrPost(xhrArgs);
        });
    }
})