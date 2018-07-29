package com.schooldeal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @version V 1.2  
 * @author G3-YX-SHIL
 */
public class OutputObject implements Serializable {
	private static final long serialVersionUID = 3862163719814136067L;
	private String returnCode;
	private String returnMessage;
	private Map<String, String> bean;
	private Map<String, Object> images;
	private Map<String, Object> ext1;
	private List<Map<String, String>> beans;
	private List<Map<String, Object>> ext2;
    
	
	public Map<String, Object> getImages() {
		return images;
	}

	public void setImages(Map<String, Object> images) {
		this.images = images;
	}
	
	
	public OutputObject() {
		this.returnMessage="sucess";
		this.returnCode="0000";
		
	}

	public OutputObject(String returnCode, String returnMessage) {
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}
   
	
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public Map<String, String> getBean() {
		return bean;
	}

	public void setBean(Map<String, String> bean) {
		this.bean = bean;
	}

	public List<Map<String, String>> getBeans() {
		return beans;
	}

	public void setBeans(List<Map<String, String>> beans) {
		this.beans = beans;
	}
	
	public Map<String, Object> getExt1() {
		return ext1;
	}

	public void setExt1(Map<String, Object> ext1) {
		this.ext1 = ext1;
	}
	public List<Map<String, Object>> getExt2() {
		return ext2;
	}

	public void setExt2(List<Map<String, Object>> ext2) {
		this.ext2 = ext2;
	}
	

	/**
	 * 
	 * @param out
	 * @return
	 */
	public static String outObjectToString(OutputObject out) {
		return JSON.toJSONString(out);
	}

	/**
	 * 
	 * @param out
	 * @return
	 */
	public static String ObjectToString(Object obj) {
		return JSON.toJSONString(obj);
	}

	/**
	 * @param json
	 * @return
	 */
	public static OutputObject StringToOutObject(String json) {
		return JSON.parseObject(json, OutputObject.class);
	}

	/**
	 * @param json
	 * @return
	 */
	public static Object StringToOutObject(String json, Object obj) {
		return JSON.parseObject(json, obj.getClass());
	}
}
