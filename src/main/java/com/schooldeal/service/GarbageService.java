package com.schooldeal.service;

import java.util.Map;

import org.json.JSONObject;

import com.schooldeal.bean.OutputObject;

public interface GarbageService {

	/**
	 * 查询垃圾分类
	 * @param param
	 * @return
	 */
	public OutputObject queryGarbage(Map<String, Object> param);
	
	/**
	 * 根据图片识别查询垃圾
	 * @param param
	 * @return
	 */
	public OutputObject queryGarbageByPicture(JSONObject jsonObject);
}
