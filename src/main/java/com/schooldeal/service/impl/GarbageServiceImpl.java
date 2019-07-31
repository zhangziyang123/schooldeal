package com.schooldeal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.schooldeal.bean.OutputObject;
import com.schooldeal.emuns.GarbageTypeEnums;
import com.schooldeal.emuns.ReturnInfoEnums;
import com.schooldeal.service.GarbageService;


public class GarbageServiceImpl extends BaseServiceImpl implements GarbageService {

	@Override
	public OutputObject queryGarbage(Map<String, Object> param) {
		OutputObject outputObject = new OutputObject();
		try {
			List<Map<String, Object>> garbages = getBaseDao().query("Garbage", "queryGarbage", param);
			for (Map<String, Object> map : garbages) {
				String type = (String) map.get("type");
				map.put("type", GarbageTypeEnums.getType(type));
			}
			outputObject.setExt2(garbages);
			outputObject.setReturnCode(ReturnInfoEnums.QUERY_OK.getCode());
			outputObject.setReturnMessage(ReturnInfoEnums.QUERY_OK.getMessage());
		} catch (Exception e) {
			System.out.println(e);
			outputObject.setReturnCode(ReturnInfoEnums.QUERY_ERROE.getCode());
			outputObject.setReturnMessage(ReturnInfoEnums.QUERY_ERROE.getMessage());
		}
		return outputObject;
	}

	@Override
	public OutputObject queryGarbageByPicture(JSONObject jsonObject) {
		OutputObject outputObject = new OutputObject();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			JSONArray resultArray = jsonObject.getJSONArray("result");
			for(int i=0;i<resultArray.length();i++){
				JSONObject json = resultArray.getJSONObject(i);
				paramMap.put("keyword"+i, json.getString("keyword"));
			}
			List<Map<String, Object>> garbages = getBaseDao().query("Garbage", "queryGarbageByPicture", paramMap);
			
			/*if(garbages.isEmpty()){
				List<Map<String, Object>> garbageList = new ArrayList<>(); 
				for(int i=0;i<resultArray.length();i++){
					Map<String, Object> nonGarbage = new HashMap<>();
					JSONObject json = resultArray.getJSONObject(i);
					nonGarbage.put("keyword", json.getString("keyword"));
					garbageList.add(nonGarbage);
				}
				getBaseDao().insertBatch("Garbage", "insertGarbage", garbageList);
				outputObject.setReturnCode(ReturnInfoEnums.QUERY_OK.getCode());
				outputObject.setReturnMessage(ReturnInfoEnums.QUERY_OK.getMessage());
				return outputObject;
			}*/
			
			for (Map<String, Object> map : garbages) {
				String type = (String) map.get("type");
				map.put("type", GarbageTypeEnums.getType(type));
			}
			outputObject.setExt2(garbages);
			outputObject.setReturnCode(ReturnInfoEnums.QUERY_OK.getCode());
			outputObject.setReturnMessage(ReturnInfoEnums.QUERY_OK.getMessage());
		} catch (Exception e) {
			System.out.println(e);
			outputObject.setReturnCode(ReturnInfoEnums.QUERY_ERROE.getCode());
			outputObject.setReturnMessage(ReturnInfoEnums.QUERY_ERROE.getMessage());
		}
		return outputObject;
	}

}
