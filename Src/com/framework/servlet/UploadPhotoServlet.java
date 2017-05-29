
package com.framework.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framework.FrameConstant;
import com.framework.utils.DateUtil;
import com.framework.utils.FileUtils;
import com.framework.utils.StringUtils;
import com.framework.utils.ThumbnailUtils;


public class UploadPhotoServlet extends HttpServlet 
{
	private File filedata;
	private String filedataFileName;
	private String photoName;
	protected static final String JSON_MESSAGE = "json_show";
	private static final String allowTYpe = "gif,jpg,bmp";

	protected String script = "";

	protected String json;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		doPost(request,response);
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		execute(request,response);
    }
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
	{
		if(filedata!=null)
		{
		
			String[] names = this.upload(filedata, filedataFileName, "goods", false);
			String contextPath = request.getContextPath();
			if(contextPath != null && !contextPath.equals(""))
			  this.json=  contextPath +"/"+ names[0] + "," +contextPath +"/"+ names[0];
			else
			  this.json=  "/"+ names[0] + "," +"/"+ names[0];
		}		
		return this.JSON_MESSAGE;
	}

	
	public String delPhoto(){
		String filePath  = FileUtils.getRootPath() +"/"+ getValue("upload.img.domain.prifx") +"" + getName(photoName);
		//System.out.println(filePath); 
		FileUtils.delete(filePath);
		//System.out.println("photoName is " + photoName);
		this.json="{'result':0,'message:':'图片删除成功'}";
		return this.JSON_MESSAGE;
	}
	
	private static String getName(String path){
		String regEx = "(/goods/)(.*)";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(path);
		String name = "";
		 
		while(m.find()){
			name= m.group();
		 
		}
		return name;
	}
	
	public String getValue(String key)
	{
		return (String)FrameConstant.ParsMap.get(key);
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
			filePath = getValue("upload.img.domain.prifx") + "/" + subFolder;
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

			String thubmPath = FileUtils.getRootPath() + "/"+ getValue("upload.img.domain.prifx")  + "/" + subFolder + "thumb/";

			FileUtils.createFolder(thubmPath);

			String thumbName = getValue("upload.img.domain.prifx")  + "/" + subFolder + "thumb/" + imgName;

			thum = new ThumbnailUtils(FileUtils.getRootPath()+ "/" + getValue("upload.img.domain.prifx")  + "/" + subFolder + imgName, FileUtils.getRootPath()+ "/" + thumbName);

			thum.resizeFix(200, 200);
			return thumbName;

		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args){
		
		String path = "http://www.javashop.com/attachment/goods/200901020201052381.jpg" ;

		
		String name = getName(path);
		//System.out.println(name);
	}
	
	
	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}


	public String getPhotoName() {
		return photoName;
	}


	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
 
	
	
}
