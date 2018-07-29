package com.schooldeal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class InputObject implements Serializable {
	private static final long serialVersionUID = 7181946961034601621L;
	private String busiId;//业务编码
	private HashMap<String, String> params = new HashMap<String, String>();     // 业务入参Map类型
	private HashMap<String, String> logParams = new HashMap<String, String>();// 如需记录登录日志则都在此
	private List<Map<String, Object>> beans = new ArrayList<Map<String, Object>>();// 业务入参List// 类型
	private HashMap<String, String> images = new HashMap<String, String>();
	public String getBusiId() {
		return busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public HashMap<String, String> getImages() {
		return images;
	}

	public void setImages(HashMap<String, String> images) {
		this.images = images;
	}
	
	public HashMap<String, String> getLogParams() {
		return logParams;
	}

	public void setLogParams(HashMap<String, String> logParams) {
		this.logParams = logParams;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}

	public List<Map<String, Object>> getBeans() {
		return beans;
	}

	public void setBeans(List<Map<String, Object>> beans) {
		this.beans = beans;
	}
	
	public static  String inputObjectToString(InputObject in){
		return JSON.toJSONString(in);
	}
	
	public static InputObject stringToInputObject(String json){
		return JSON.parseObject(json, InputObject.class);
	}
}
