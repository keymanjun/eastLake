package com.framework.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.TypeReference;

/**
 * @author xiaowang
 */
public class JsonStringUtils {
	
	private static Logger logger = Logger.getLogger(JsonStringUtils.class.getName());
	public final static ObjectMapper mapper = new ObjectMapper();
	
	private JsonStringUtils(){}
	
	/**
	 * list 转换 json string
	 * @param <T>
	 * @param ls
	 * @return String
	 */
	public static <T> String listToJsonString(List<T> ls) {
		String json = "";
		JsonGenerator jgen = null;
		StringWriter sw = new StringWriter();
		
		try {
			jgen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(jgen, ls);			
			jgen.close();
			json = sw.toString();
		} catch (JsonGenerationException e) {
			logger.error("JsonStringUtils " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("JsonStringUtils " + e.getMessage());
		} catch (IOException e) {
			logger.error("JsonStringUtils " + e.getMessage());
		} finally {
			try {
				jgen.close();
				jgen = null;
			} catch (IOException e) {
				logger.error("JsonStringUtils " + e.getMessage());
			}
			try {
				sw.close();
				sw = null;
			} catch (IOException e) {
				logger.error("JsonStringUtils " + e.getMessage());
			}
		}
		return json;
	}
	
	/**
	 * 根据传入的对象 生成json字符串
	 * @param <T>
	 * @param t
	 * @return String
	 */
	public static <T> String objectToJsonString(T t) 
	{
		String json = "";
		JsonGenerator jgen = null;
		StringWriter sw = new StringWriter();
		try 
		{
			jgen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(jgen, t);
			json = sw.toString();
		} catch (JsonGenerationException e) {
			logger.error("JsonStringUtils " + e.getMessage());
		} catch (JsonMappingException e) {
			logger.error("JsonStringUtils " + e.getMessage());
		} catch (IOException e) {
			logger.error("JsonStringUtils " + e.getMessage());
		} finally {
			try {
				jgen.close();
				jgen = null;
			} catch (IOException e) {
				logger.error("JsonStringUtils " + e.getMessage());
			}
			try {
				sw.close();
				sw = null;
			} catch (IOException e) {
				logger.error("JsonStringUtils " + e.getMessage());
			}
		}
		return json;
	}
	
	/**
	 * 根据JSON 字符串 返回 POJO
	 * 
	 * Goods a = JsonStringUtils.fromJsonStringList(jsonString, Goods.class);
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return T
	 * @throws Exception 
	 */
	public static <T> T fromJsonString(String jsonString, Class<T> clazz) throws Exception {
		T t = null;
		try {
			if (StringUtils.isNotEmpty(jsonString)) {
				t = mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true).readValue(jsonString, clazz);
			}
		} catch (JsonParseException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		} catch (JsonMappingException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		} catch (IOException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		}
		return t;
	}
	
	/**
	 * 返回 List Map 封装
	 * 
	 * List<Map<String, String>> ls = JsonStringUtils.fromJsonStringList(jsonString);
	 * @param jsonString
	 * @return List<Map<String, String>>
	 * @throws Exception
	 */
	public static List<Map<String, String>> fromJsonString(String jsonString) throws Exception {
		List<Map<String, String>> ls = null;
		try {
			if(StringUtils.isNotEmpty(jsonString)) {
				ls = mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true).readValue(jsonString,
						TypeFactory.type(new TypeReference<List<Map<String, String>>>() {}));
			}
		} catch (JsonParseException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		} catch (JsonMappingException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		} catch (IOException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		}
		return ls;
	}
	
	/**
	 * 根据 JSON 字符串 返回 LIST 对象，LIST ELEMENT 为 POJO || VO
	 * 
	 * List<Goods> lsa = JsonStringUtils.fromJsonStringList(jsonString, Goods.class);
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param cl
	 * @return List<T>
	 * @throws Exception
	 */
	public static <T> List<T> fromJsonList(String jsonString, Class<T> cl) throws Exception {
		List<T> ls = null;
		try {
			if (StringUtils.isNotEmpty(jsonString)) {
				ls = mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
						.readValue(jsonString, TypeFactory.collectionType(ArrayList.class, cl));
			}
		} catch (JsonParseException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		} catch (JsonMappingException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		} catch (IOException e) {
			logger.error("JsonStringUtils " + e.getMessage());
			throw new Exception(e);
		}
		return ls;
	}
	
	/**
	 * 根据 JSON 字符串 返回 LIST 对象，LIST ELEMENT 为 POJO || VO
	 * 此方法保留，不推荐使用
	 * List<Goods> lsa = JsonStringUtils.fromJsonStringList(jsonString, Goods.class);
	 * @deprecated
	 * @param <T>
	 * @param jsonString 符合规则的字符串
	 * @param cl POJO CLASS
	 * @return List<T>
	 * @throws Exception
	 */
	public static <T> List<T> fromJsonStringList(String jsonString, Class<T> cl) throws Exception {
		List<T> ls = new ArrayList<T>();
		if (StringUtils.isNotEmpty(jsonString)) {
			List<Map<String, String>> lsm = JsonStringUtils.fromJsonString(jsonString);
			if (lsm != null) {
				for (Map<String, String> m : lsm) {
					T t = cl.newInstance();
					BeanUtils.populate(t, m);
					ls.add(t);
				}
			}
		}
		return ls;
	}
}