package com.framework;

import java.util.HashMap;
import java.util.Map;

public final class FrameConstant {
  private FrameConstant(){}
  
  public static final String SESSION_ACEGI_ACCOUNT_KEY="juser";
  public static final String SESSION_ACEGI_USER_KEY="acegiUser";
  
  
  public static final String SYSTEM_MARK="sysmark";
  public static String SYSTEM_MARK_DEFAULT="00";
  
  public static String APP_REAL_PATH="";
  public static String APP_CONTEXT_PATH="";
  
  public static String UPLOAD_IMG_DIR="\\admin\\upload\\images\\";
  public static String UPLOAD_IMG_EXT="gif,jpg,jpeg,png";
  public static String UPLOAD_IMG_SIZE="500";
  
  public static String DATABASE_NAME="mysql";
  
  
  
  //加载配置参数map
  public static Map ParsMap=new HashMap(); 
  
  //加载配置webservice参数map
  public static Map WSMap=new HashMap(); 

  //加载数据库xml文件sql
  public static Map sqlMap=new HashMap();
}
