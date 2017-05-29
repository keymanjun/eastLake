package com.framework.servlet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.framework.Entry;
import com.framework.FrameConstant;
import com.framework.db.sqlmap.SqlMapUtil;

/**
 * 
 * @author keymanjun@yahoo.com.cn
 *
 */
public class InitParsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
    public void init() throws ServletException
    {
    	try
    	{
    		//加载资源参数
    		loadParsMap();
    		
    		//加载数据库sql的xml文件
    		if("true".equals(super.getInitParameter("initSqlmap")))
    		{
    			SqlMapUtil.initSqlMap((String)FrameConstant.ParsMap.get("app.classpath"));  
    		}
    		
    		//设置系统代码,包括子系统代码
    		setSystemCodes(super.getInitParameter(FrameConstant.SYSTEM_MARK));
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    @SuppressWarnings("unchecked")
	private void loadParsMap()
    {
    	ResourceBundle rb=ResourceBundle.getBundle("resources.strutsCfgResources");
    	Enumeration enum1=rb.getKeys();
    	while(enum1.hasMoreElements()){
    		String skey=enum1.nextElement().toString();
    		String svalue=rb.getString(skey);
    		FrameConstant.ParsMap.put(skey,svalue);
    	}
    	FrameConstant.APP_REAL_PATH=super.getServletConfig().getServletContext().getRealPath("/");
    	FrameConstant.ParsMap.put("app.dir",FrameConstant.APP_REAL_PATH);
    	FrameConstant.ParsMap.put("app.classpath",filterPath(FrameConstant.APP_REAL_PATH+"WEB-INF/classes"));
    	
    	
    }
    
    //加载系统代码,包括子系统代码
    private void setSystemCodes(String s)
    {
    	if(s==null || "".equals(s) || s.indexOf(";")==-1) return;
    	String[] codes=s.split(";");
    	if(codes.length<1) return;
    	List list=new ArrayList();
    	for(int i=0;i<codes.length;i++){
    		Entry entry=new Entry();
    		String[] arrValue=codes[i].split(",");
    		entry.setKey(arrValue[0].replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", ""));
    		entry.setValue(arrValue[1]);
    		list.add(entry);
    		if(i==0){
    			FrameConstant.SYSTEM_MARK_DEFAULT=arrValue[0].replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "");
    		}
    	}
    	FrameConstant.ParsMap.put(FrameConstant.SYSTEM_MARK,list);
    }
   
    
    private static String filterPath(String spath){
    	return spath.replaceAll("\\\\", "/").replaceAll("//", "/");
    }
    
    public static void main(String[] args)
    {
    	Properties p=System.getProperties();
    	System.out.println("aaaaaaa");
    }
}
