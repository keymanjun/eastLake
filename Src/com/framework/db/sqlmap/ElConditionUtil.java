package com.framework.db.sqlmap;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.framework.db.sqlmap.exception.SqlmapException;

/**
 * el manage
 * 
 * @author User
 * 
 */
public class ElConditionUtil 
{
	/**
	 * 是否为空
	 */
	private final static String IS_NULL = "isNull";

	/**
	 * 非空
	 */
	private final static String NOT_NULL = "notNull";

	/**
	 * 小于 <
	 */
	private final static String LESS_THAN = "lt";

	/**
	 * 大于 >
	 */
	private final static String GREATER_THAN = "gt";

	/**
	 * 小于等于
	 */
	private final static String LESS_EQUEAL = "le";

	/**
	 * 大于等于
	 */
	private final static String GREATER_EQUEAL = "ge";
	
	/**
	 * 连接符
	 */
	private final static String AND = "and";

	/**
	 * 转换含有isNull 的 字符串
	 * 
	 * @param empty
	 * @param parsMap
	 * @return
	 * @throws NoEmptyPropertyException
	 * @throws ELParseException
	 * @throws SqlmapException
	 */
	private static boolean transformEmpty(String empty,Map<String, Object> parsMap) 
		throws SqlmapException
	{
		String[] filterEmpty = empty.trim().split("\\s+");
		if (filterEmpty.length != 2) {
			throw new SqlmapException(
					"the condition length should be two");
		}
		if (!filterEmpty[0].equals(IS_NULL)) {
			throw new SqlmapException("no \"isNull\" property");
		}
		String condition = getElContent(filterEmpty[1]);
		if (parsMap.get(condition) == null) {
			return true;
		}
		return false;
	}

	/**
	 * 转换含有 notNull 的条件
	 * 
	 * @param notEmpty
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws NoEmptyPropertyException
	 */
	private static boolean transformNotEmpty(String notEmpty,
			Map<String, Object> parsMap) throws SqlmapException 
	{
		String[] filterEmpty = notEmpty.trim().split("\\s+");
		if (filterEmpty.length != 2) {
			throw new SqlmapException("the condition:" + notEmpty
					+ " length should be two");
		}
		if (!filterEmpty[0].equals(NOT_NULL)) {
			throw new SqlmapException("no \"notNull\" property");
		}
		String condition = getElContent(filterEmpty[1]);
		if (parsMap.get(condition) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 转换小于 lt 的条件
	 * 
	 * @param ltString
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	private static boolean transformLT(String ltString,
			Map<String, Object> parsMap) throws SqlmapException,
			ParseException {
		String[] filter = ltString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlmapException(
					"the condition length should be 3");
		} else if (!filter[1].equals(LESS_THAN)) {
			throw new SqlmapException("no \"lt\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlmapException("the el[2]:" + filter[2]
					+ " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlmapException("key :" + condition
					+ " no value in parsMap");
		} else if (format.parse(value).doubleValue() < format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}

	/**
	 * 转换大于  gt
	 * @param ltString
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	private static boolean transformGT(String gtString,
			Map<String, Object> parsMap) throws SqlmapException,
			ParseException {
		String[] filter = gtString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlmapException(
					"the condition length should be 3");
		} else if (!filter[1].equals(GREATER_THAN)) {
			throw new SqlmapException("no \"gt\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlmapException("the el[2]:" + filter[2]
					+ " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlmapException("key :" + condition
					+ " no value in parsMap");
		} else if (format.parse(value).doubleValue() > format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}

	
	/**
	 * 转换小于等于 le
	 * @param leString
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	private static boolean transformLE(String leString,
			Map<String, Object> parsMap) throws SqlmapException,
			ParseException {
		String[] filter = leString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlmapException(
					"the condition length should be 3");
		} else if (!filter[1].equals(LESS_EQUEAL)) {
			throw new SqlmapException("no \"le\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlmapException("the el[2]:" + filter[2]
					+ " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlmapException("key :" + condition
					+ " no value in parsMap");
		} else if (format.parse(value).doubleValue() <= format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 转换大于等于 ge
	 * @param geString
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	private static boolean transformGE(String geString,
			Map<String, Object> parsMap) throws SqlmapException,
			ParseException {
		String[] filter = geString.trim().split("\\s+");
		if (filter.length != 3) {
			throw new SqlmapException(
					"the condition length should be 3");
		} else if (!filter[1].equals(GREATER_EQUEAL)) {
			throw new SqlmapException("no \"ge\" property");
		} else if (!isNumeric(filter[2])) {
			throw new SqlmapException("the el[2]:" + filter[2]
					+ " must be number");
		}
		NumberFormat format = NumberFormat.getInstance();
		String condition = getElContent(filter[0]);
		String value = mapTranform(parsMap.get(condition));
		if (value == null) {
			throw new SqlmapException("key :" + condition
					+ " no value in parsMap");
		} else if (format.parse(value).doubleValue() >= format.parse(filter[2])
				.doubleValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 * get ${el} value el
	 * 
	 * @param elString
	 * @return
	 */
	private static String getElContent(String elString) {
		String filterEl = elString.trim().substring(2, elString.length() - 1);
		return filterEl;
	}

	/**
	 * 判断一个String是否为整数
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		String regex = "-?[0-9]+\\.?[0-9]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();

	}

	/**
	 * 根据条件返回转换值
	 * 
	 * @param condition
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws NoEmptyPropertyException
	 * @throws ParseException
	 */
	public static boolean transform(String condition,Map<String, Object> parsMap) throws SqlmapException,ParseException
	{
		// 判断 isNull or notNull or others 条件 得到个true or false
		if (condition.trim().startsWith(IS_NULL)) {
			return transformEmpty(condition, parsMap);
		} else if (condition.trim().startsWith(NOT_NULL)) {
			return transformNotEmpty(condition, parsMap);
		} else if (condition.indexOf(AND)!=-1) {
			String[] ands = condition.split(AND);
			return (ifOperation(ands[0],parsMap) && ifOperation(condition, parsMap));
		} else {
			return ifOperation(condition,parsMap); 
		}

	}

	/**
	 * if循环
	 * @param condition
	 * @param parsMap
	 * @return
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	private static boolean ifOperation(String condition,Map<String, Object> parsMap) throws SqlmapException, ParseException {
		if(condition.indexOf(LESS_THAN)!=-1) {
			return transformLT(condition, parsMap);
		}else if(condition.indexOf(GREATER_THAN)!=-1){
			return transformGT(condition, parsMap);
		}else if(condition.indexOf(LESS_EQUEAL)!=-1) {
			return transformLE(condition, parsMap);			
		}else if(condition.indexOf(GREATER_EQUEAL)!=-1) {
			return transformGE(condition, parsMap);
		}else {
		    throw new SqlmapException("'"+condition+"' no control key");
		
		}
	}
	
	/**
	 * map类
	 * @param obj
	 * @return
	 */
	public static String mapTranform(Object obj) {
		String value = "";
		if(obj instanceof String) {
			value = "'"+(String)obj+"'";
		}else if(obj instanceof Integer) {
			value = ((Integer)obj).toString();
		}else if(obj instanceof Double) {
			value = ((Double)obj).toString();
		}else if(obj instanceof String[]) {
			int i = 0;
			for(String pars : (String[])obj) {
				if(i==0) {
					value += "("+pars;
					i+=1;
				}else if(i==((String[])obj).length-1){
					value += ","+pars+")";
				}
			}
		}
		return value;
	}

}