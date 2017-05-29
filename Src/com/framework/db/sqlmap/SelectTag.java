package com.framework.db.sqlmap;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;
import org.dom4j.Node;

import com.framework.db.sqlmap.exception.SqlmapException;

/**
 * 解析xml
 * @author User
 *
 */
public class SelectTag {
	/**
	 * id 的属性 sql 
	 */
	private final static String ID_SQL = "sql";
	/**
	 * id 的属性 if 
	 */
	private final static String ID_IF = "if";
	/**
	 * if 的属性 test 
	 */
	private final static String IF_TEST = "test";
	
	
	/**
	 * 通过id 得到sql
	 * @param id  想要得到的sql的id
	 * @param parsMap 存储了参数值
	 * @param valueBuffer 预处理存储值
	 * @return
	 * @throws SqlmapException 
	 * @throws NoEmptyPropertyException 
	 * @throws ParseException 
	 */
	public static String getSql(String id,Map<String,Object> parsMap,StringBuffer valueBuffer) throws SqlmapException, ParseException 
	{
		String condition = null;
		String textValue = null;
		String sql = "";
		Node node = null;
		List<Element> elements = (List)SqlMapUtil.sqlmap.get(id);
		
		for(Element element:elements) 
		{
			node = element.detach();
			String nodeName = node.getName();
			if(nodeName.equals(ID_SQL)) 
			{
				sql = LableUtil.filterEl(node.getStringValue(),parsMap);
			}
			else if(nodeName.equals(ID_IF))
			{
				condition = element.attributeValue(IF_TEST);
				textValue = node.getStringValue();
				sql += " "+LableUtil.fileterIf(condition,textValue,parsMap,valueBuffer);
			}
		}
		return sql;
	}
	
	/**
	 * @param id
	 * @param parsMap
	 * @return
	 * @throws NoEmptyPropertyException
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	public static String getSql(String id,Map<String,Object> parsMap) throws SqlmapException, ParseException {
		StringBuffer valueBuffer = new StringBuffer();
		String sql = getSql(id,parsMap,valueBuffer);
		String values[] = valueBuffer.toString().split(";");
		String regex = "\\?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		while(matcher.find()) {
			matcher.appendReplacement(buffer, values[i]);
			i+=1;
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
	
	/**
	 * 通过id 得到缓存中的sql
	 * @param id
	 * @return
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	public static String getSql(String id) throws SqlmapException, ParseException {
		return  getSql(id,null);
	}
	
	
}
