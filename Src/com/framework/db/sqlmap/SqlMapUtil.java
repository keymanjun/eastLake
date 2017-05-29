package com.framework.db.sqlmap;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author dafei
 *
 */
public final class SqlMapUtil 
{
   private SqlMapUtil(){}
   final static String XML_PREFIX="sqlmap_";
   public static Map sqlmap=null;
   public static Map keymap=null;
   public static StringBuffer msg=new StringBuffer();
   
   /**
    * @param dirName
    * @throws Exception
    */
   public static void initSqlMap(String dirname) throws Exception
   {
	   if(isNullOrEmpty(dirname)) return;
	   List filelist=new ArrayList();
	   refreshFileList(dirname.replaceAll("\\\\", "/"),filelist);
	   if(filelist==null || filelist.size()<1) return;
	   try
	   {
		   for(int i=0;i<filelist.size();i++)
		   {
			   String sfilename=filelist.get(i).toString();
			   putSqlByFilename(sfilename.replaceAll("\\\\", "/"));	
		   }
	   }
	   catch(Exception e){
		   System.out.println(msg.toString());
		   e.printStackTrace();
	   }
   }
   
   /**
    * @param filename
    * @throws Exception
    */
   public static void putSqlByFilename(String filename) throws Exception
   {
	  if(keymap==null) keymap=new HashMap();
	  if(sqlmap==null) sqlmap=new HashMap();
	  List list=parseXml(filename);
	  if(list==null || list.size()<1) return;
	  for(int i=0;i<list.size();i++)
	  {
		  Element element=(Element)list.get(i);
		  if(element.elements()==null || element.elements().size()<1) continue;
		  //String sql=element.getTextTrim();
		  //if(isNullOrEmpty(sql)) continue;
		  
		  String skey=element.attributeValue("id");
		  if(keymap.get(skey)!=null){
			  msg.append("ID为:"+keymap.get(skey).toString()+"的文件:"+filename+"与ID为:"+skey+"的文件存在相同ID.");
		      continue;
		  }
		  keymap.put(skey, filename);
		  sqlmap.put(skey, element.elements());
	  }
   }
   
   /**
    * @param filename     c:/catalog/catalog.xml
    * @return
    * @throws Exception
    */
   private static List parseXml(String filename) throws Exception
   {
	   SAXReader saxReader = new SAXReader();
	   Document docu= saxReader.read(new File(filename));
	   return docu.getRootElement().elements();
   }
   

   public static String getSqlById(String key) throws Exception
   {
	   return (String)sqlmap.get(key);
   }
   
   /**
    * 
    * @param key
    * @param args
    * @return 
    * @throws Exception
    */
   public static String getSqlById(String key,String[] args) throws Exception
   {
	   String sql= (String)sqlmap.get(key);
	   if(isNullOrEmpty(sql)) return "";
	   if(args==null || args.length<1) return sql;
	   int[] idx=new int[args.length];
	   String result=MessageFormat.format(sql,formatParameters(args));
	   return result;
   }
   
   public static String[] formatParameters(String[] args)
   {
	   for(int i=0;i<args.length;i++)
	   {
		   if(isNullOrEmpty(args[i])) continue;
		    args[i]="'"+args[i]+"'";
	   }
	   return args;
   }
    
   
   public static boolean isNullOrEmpty(String s){
	   return s==null||"".equals(s);
   }
   
   public static void refreshFileList(String strPath,List filelist) 
   { 
       File dir = new File(strPath); 
       File[] files = dir.listFiles(); 
       if (files == null)  return; 
       for (int i = 0; i < files.length; i++) 
       { 
           if (files[i].isDirectory()) {
               refreshFileList(files[i].getAbsolutePath(),filelist); 
           } else { 
               String strFileName = files[i].getAbsolutePath().toLowerCase();
               if(strFileName.endsWith(".xml") && files[i].getName().startsWith(XML_PREFIX)){
            	   System.out.println("---"+strFileName);
            	   filelist.add(files[i].getAbsolutePath());
               }
           } 
       } 
   }

   
   public static void main(String[] args) throws Exception
   {
	   //String dir="D:/kyhuaqing/yqsys/Src/WebRoot/WEB-INF/classes//com/operation/news/entity";
	   //initSqlMap(dir);
	   System.out.println(String.class.getName());
   }
}
