//==============================
//功能:Javascript本地图片预览
//说明:简单的判断了文件的合法性
//适用于:上传文件前预览本地图片
//==============================
function showImage(value, img) {
	// alert(value);
	// 检测盘符
	// alert(value.indexOf(':'));
	// 检测文件是否有扩展名
	// alert(value.length-value.lastIndexOf('.'));
	// 取文件扩展名
	// alert(value.substr(value.length-3,3));
	// 检测文件扩展名是否合法
	alert(checkExt(value.substr(value.length - 3, 3)));

	if (value.length > 5 && value.indexOf(':') == 1
			&& (value.length - value.lastIndexOf('.')) == 4
			&& checkExt(value.substr(value.length - 3, 3))) {
		img.src = value;
		img.alt = "本地图片预览";
		img.style.visibility = "visible";
	} else {
		img.style.visibility = "hidden";
	}
}
// 检查扩展名是否合法,合法返回True
function checkExt(ext) {
	// 这里设置允许的扩展名
	var AllowExt = "jpg|gif|jpeg|png|bmp";
	var ExtOK = false;
	var ArrayExt;
	if (AllowExt.indexOf('|') != -1) {
		ArrayExt = AllowExt.split('|');
		for (i = 0; i < ArrayExt.length; i++) {
			if (ext.toLowerCase() == ArrayExt[i]) {
				ExtOK = true;
				break;
			}
		}
	} else {
		ArrayExt = AllowExt;
		if (ext.toLowerCase() == ArrayExt) {
			ExtOK = true;
		}
	}
	return ExtOK;
}

