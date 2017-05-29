package com.framework.base;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.framework.FrameConstant;
import com.framework.utils.DateUtil;
import com.framework.utils.FileUtils;
import com.framework.utils.StringUtils;
import com.framework.utils.ThumbnailUtils;
import com.opensymphony.xwork2.ActionSupport;

public class WWWAction  extends ActionSupport implements SessionAware
{
	protected Map session = null;
	protected static final String JSON_MESSAGE = "json_show";
	private static final String allowTYpe = "gif,jpg,bmp";
	protected String json;


	public Map getSession() {
		return session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	public String getValue(String key)
	{
		return (String)FrameConstant.ParsMap.get(key);
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	
	
	private String getThumbpath(String file){
		String fStr =  "";
		if(!file.trim().equals("")){
		String[] arr = file.split("/");
		fStr ="/"+arr[0]+"/"+arr[1]+"/thumb/"+arr[2];
		}
		return fStr;
	}
	
	/**
	 * 上传图片
	 * 
	 * @param file
	 *            要上传的图片文件
	 * @param fileFileName
	 *            上传的图片文件本地名称
	 * @param subFolder
	 *            如果要创建在某个子文件夹下则指定，不指定的话则不创建子文件夹，按照常用规范创建、存储
	 * @param createThumb
	 *            是否要创建缩略图
	 * @return 不生缩略图返回上包含上传后生成的文件名组成的全路径的一维数组 如果生成缩略图二个大小的字串数组，0为上述路径，1为缩略图路径
	 */
	protected String[] upload(File file, String fileFileName, String subFolder, boolean createThumb) 
	{
		if (subFolder != null && !subFolder.equals("") && !subFolder.endsWith("/"))
			subFolder = subFolder + "/";

		String fileName = null;
		String filePath = "";
		String thumbPath = "";
		String[] path = new String[2];
		if (file != null && fileFileName != null) 
		{
			String ext = FileUtils.getFileExt(fileFileName);
			//filePath = getValue("upload.img.domain.prifx") + "/" + subFolder;
			filePath = subFolder;
			fileName = DateUtil.toString(new Date(), "yyyyMMddHHmmss") + StringUtils.getRandStr(4) + "." + ext;
			filePath += fileName;
			FileUtils.createFile(file, FileUtils.getRootPath() + "/"+ filePath);
			path[0] = filePath;
			if (createThumb) 
			{
				thumbPath = createThumb(fileName, subFolder);
				path[1] = thumbPath;
			}
		}
		return path;
	}
	
	
	/**
	 * 生成缩略图
	 * 
	 * @param imgName
	 * @return
	 */
	private String createThumb(String imgName, String subFolder) 
	{
		// subFolder 最后面 必须带 /
		if (subFolder != null && !subFolder.equals("") && !subFolder.endsWith("/"))
			subFolder = subFolder + "/";
		ThumbnailUtils thum;
		try {

			//String thubmPath = FileUtils.getRootPath() + "/"+ getValue("upload.img.domain.prifx")  + "/" + subFolder + "thumb/";
			String thubmPath = FileUtils.getRootPath() + "/"+ subFolder + "thumb/";
			
			FileUtils.createFolder(thubmPath);

			//String thumbName = getValue("upload.img.domain.prifx")  + "/" + subFolder + "thumb/" + imgName;
			String thumbName = subFolder + "thumb/" + imgName;

			//thum = new ThumbnailUtils(FileUtils.getRootPath()+ "/" + getValue("upload.img.domain.prifx")  + "/" + subFolder + imgName, FileUtils.getRootPath()+ "/" + thumbName);
			thum = new ThumbnailUtils(FileUtils.getRootPath()+ "/" + subFolder + imgName, FileUtils.getRootPath()+ "/" + thumbName);

			thum.resizeFix(200, 200);
			return thumbName;

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "";
	}
}
