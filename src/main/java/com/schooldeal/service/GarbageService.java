package com.schooldeal.service;

import java.util.Map;

import org.json.JSONObject;

import com.schooldeal.bean.OutputObject;

public interface GarbageService {

	/**
	 * ��ѯ��������
	 * @param param
	 * @return
	 */
	public OutputObject queryGarbage(Map<String, Object> param);
	
	/**
	 * ����ͼƬʶ���ѯ����
	 * @param param
	 * @return
	 */
	public OutputObject queryGarbageByPicture(JSONObject jsonObject);
}
