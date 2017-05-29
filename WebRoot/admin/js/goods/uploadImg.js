//关于图片上传
var swfu;
function imgConfig(ctx) {
	var settings = {
		flash_url : ctx+"/admin/js/swfupload.swf",
		upload_url: ctx+"/uploadPhoto!execute.action",	// Relative to the SWF file
		post_params: {"PHPSESSID" : "a"},
		file_size_limit : "5 MB",
		file_types : "*.gif;*.jpg;*.bmp",
		file_types_description : "gif jpg bmp",
		file_upload_limit : 6,
		file_queue_limit : 0,
		custom_settings : {
			progressTarget : "uploadProgress",
			cancelButtonId : "btnCancel"
		},
		debug: false,

		// Button settings
		button_image_url: ctx+"/admin/images/XPButtonUploadText_61x22.png",	// Relative to the Flash file
		button_width: "61",
		button_height: "22",
		button_placeholder_id: "spanButtonPlaceHolder",
		//button_text: '<span class="theFont">上传图片</span>',
		//button_text_style: ".theFont { font-size: 12px; }",
		button_text_left_padding: 12,
		button_text_top_padding: 3,

		file_dialog_complete_handler : fileDialogComplete,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_success_handler : uploadSuccess,
		upload_complete_handler : uploadComplete
	};
	swfu = new SWFUpload(settings);
	//swfu = new SWFUpload();
}
	