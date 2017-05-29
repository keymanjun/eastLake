package com.framework.base;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.framework.FrameConstant;
import com.framework.components.pager.PaginationSupport;
import com.framework.utils.FilePathUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Action基类
 * <p>
 * 主要储存上下文中常用的对象及公共方法,如: session,request,response,
 * pagersupport分页对象的属性:pagesize,currentpage
 * </p>
 * 
 * @author dafei
 * 
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	protected Map request = null;
	@SuppressWarnings("unchecked")
	protected Map session = null;
	public final static String PAGER_RESULT_KEY = "pagerResult";
	Integer pageSize;
	Integer startIndex;
	protected String Msg;
	private String orderBy;
	private String descOrasc = "desc";

	// HttpServletResponse response = ServletActionContext.getResponse();

	public Integer getPageSize() {
		return this.pageSize == null ? new Integer(PaginationSupport.PAGESIZE)
				: this.pageSize;
	}

	public Integer getStartIndex() {
		return this.startIndex == null ? new Integer(0) : this.startIndex;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	@SuppressWarnings("unchecked")
	public Map getRequest() {
		if (this.request == null) {
			this.request = (Map) ActionContext.getContext().get("request");
		}
		initConstantValues();
		return request;
	}

	@SuppressWarnings("unchecked")
	public Map getSession() {
		if (this.session == null) {
			this.session = (Map) ActionContext.getContext().getSession();
		}
		return this.session;
	}

	public Object getUser() {
		return this.getHttpSession().getAttribute(
				FrameConstant.SESSION_ACEGI_ACCOUNT_KEY);
	}

	public boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s);
	}

	@SuppressWarnings("unchecked")
	public void setRequest(Map request) {
		this.request = request;
	}

	@SuppressWarnings("unchecked")
	public void setSession(Map session) {
		this.session = session;
	}

	public HttpServletRequest getReq() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResp() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getHttpSession() {
		return ServletActionContext.getRequest().getSession();
	}



	// 初始化常量值
	public void initConstantValues() {
		if (isNullOrEmpty(FrameConstant.APP_REAL_PATH)) {
			FrameConstant.APP_REAL_PATH = getReq().getRealPath("");
		}
		if (isNullOrEmpty(FrameConstant.APP_CONTEXT_PATH)) {
			FrameConstant.APP_CONTEXT_PATH = getReq().getContextPath();
		}
	}

	/**
	 * 此方法兼容Ajax,用于支持Json数据格式
	 * 
	 * @param list
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void saveToJSON(JSONArray list, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("itemlist", list);
			response.setContentType("text/html; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			System.out.println(json.toString());
			response.getWriter().print(json.toString());
		} catch (Exception e) {
			throw e;
		}
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String key) 
	{
		Msg = super.getText(key);
	}
	
	public void setMsg(String key,String[] pars) 
	{
		Msg = super.getText(key, pars);
	}


	/**
	 * 生成静态页面主方法
	 * 
	 * @param context
	 *            ServletContext
	 * @param data
	 *            一个Map的数据结果集
	 * @param templatePath
	 *            ftl模版路径
	 * @param targetHtmlPath
	 *            生成静态页面的路径
	 */
	public static void crateHTML(Map<String, Object> data, String templatePath,
			String targetHtmlPath) {
		Configuration freemarkerCfg = new Configuration();
		ServletContext context = ServletActionContext.getServletContext();
		// 加载模版
		freemarkerCfg.setServletContextForTemplateLoading(context, "/");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		try {
			// 指定模版路径
			Template template = freemarkerCfg
					.getTemplate(templatePath, "UTF-8");
			template.setEncoding("UTF-8");
			// 静态页面路径
			String htmlPath = context.getRealPath("/html") + "/"
					+ targetHtmlPath;
			File htmlFile = new File(htmlPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			// 处理模版
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* <p>方法名称: getFilePath|描述: 获得文件路径</p>
	* @param args
	* @return
	 * @throws IOException 
	 */
	public String getFilePath(String filedirect, String filename) throws IOException {
		String filepath = filedirect;
		if(!this.isNullOrEmpty(filename)) filepath += File.separator + filename;
		File file = FilePathUtil.createFile(getRealPath(filepath));//保存路径
		return file.getPath();
	}
	
	public File getFile(String filedic) throws IOException {
		File file = FilePathUtil.createFile(getRealPath(filedic));//保存路径
		return file;
	}
	
	public String getRealPath(String filepath) {
		return ServletActionContext.getServletContext().getRealPath(filepath);
	}
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDescOrasc() {
		return descOrasc;
	}

	public void setDescOrasc(String descOrasc) {
		this.descOrasc = descOrasc;
	}
	protected byte[] readImageintoByte(String image){
		ByteArrayOutputStream baos = null;
		String realPath = this.getRealPath("/");
		try{
			FileInputStream fis=new FileInputStream(realPath+image);
			BufferedInputStream bis=new BufferedInputStream(fis);
			baos=new ByteArrayOutputStream();
			int c=bis.read();//读取bis流中的下一个字节
			while(c!=-1){
			     baos.write(c);
			     c=bis.read();
			}
			bis.close();
	}catch(Exception e){
		e.printStackTrace();
	}
		return baos.toByteArray();
	}
}
