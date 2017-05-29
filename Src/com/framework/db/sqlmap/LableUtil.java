package com.framework.db.sqlmap;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.framework.db.sqlmap.exception.SqlmapException;

/**
 * 标签处理
 * 
 * @author User
 * 
 */
public class LableUtil 
{
	public static String filterSql(String sqlString) {
		// String sqlFilter = "";
		return sqlString;
	}

	/**
	 * 处理if标签
	 * 
	 * @param condition
	 * @param textValue
	 * @param parsMap
	 * @return
	 * @throws NoEmptyPropertyException
	 * @throws SqlmapException
	 * @throws ParseException
	 */
	public static String fileterIf(String condition, String textValue,
			Map<String, Object> parsMap,StringBuffer valueBuffer) throws SqlmapException,ParseException
	{
		String filterIf = "";
		// 将所有的${} 转换成 parsMap.get();
		if (ElConditionUtil.transform(condition, parsMap)) {
			filterIf = filterEqual(textValue.trim(), parsMap,valueBuffer);
//			filterIf = filterEl(filterIf.trim(), parsMap);
		}
		return filterIf;
	}

	/**
	 * 解析el ${}
	 * 
	 * @param conditon
	 * @param parsMap
	 * @return
	 */
	public static String filterEl(String textValue, Map<String, Object> parsMap) {
		String reg = "\\$\\{([a-zA-Z0-9]+)\\}";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(textValue);
		StringBuffer buffer = new StringBuffer();
		String value = null;
		while (matcher.find()) {
			value = ElConditionUtil.mapTranform(parsMap.get(matcher.group(1)));
			matcher.appendReplacement(buffer, value);
			
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}

	/**
	 * 解析 el  = ${}
	 * @param textValue
	 * @param parsMap
	 * @param valueBuffer
	 * @return
	 */
	private static String filterEqual(String textValue,
			Map<String, Object> parsMap, StringBuffer valueBuffer) {
		String reg = "(<=|>=|<|>|=)\\s*'?\\$\\{([a-zA-Z0-9]+)\\}'?";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(textValue);
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			if (valueBuffer.toString().equals("")) {
				valueBuffer.append(ElConditionUtil.mapTranform(parsMap.get(matcher.group(2))));
			} else {
				valueBuffer.append(";" + ElConditionUtil.mapTranform(parsMap.get(matcher.group(2))));
			}
			
			matcher.appendReplacement(buffer, matcher.group(1)+" ?");
		}
		matcher.appendTail(buffer);
		return buffer.toString();
	}
	
	public static void main(String[] args) {
//		filterEl("and t.account = ${name}",new HashMap<String, Object>());
		filterEqual("and t.account = '${name}'",new HashMap<String, Object>(),new StringBuffer());
	}

}