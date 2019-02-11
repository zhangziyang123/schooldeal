package com.thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class Test {
	public static void main(String[] args) {
		List<Map<String, Object>> phoneList=new ArrayList<Map<String,Object>>();
		
		/*JSONObject paramsJson = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(phoneList);
		Map<String, Object> reqMap = new HashMap<String, Object>();
		JSONObject reqJson = new JSONObject();
		reqJson.put("params", paramsJson);
		reqJson.put("beans", jsonArray);
		reqMap.put("req_json", reqJson.toString());
		System.out.println(reqMap);*/
		
		/*for(int i=0;i<478;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("phoneNum", String.valueOf(i));
			map.put("cityCode", String.valueOf(i));
			phoneList.add(map);
		}
		
		int count=0;
		if(phoneList.size() > 5){
			int group=phoneList.size()%5==0?phoneList.size()/5:phoneList.size()/5+1;
			for(int i=0;i<group;i++){
				int index=count;
				count=count+5;
				if(count>phoneList.size()){
					count=phoneList.size();
				}
				System.out.println(phoneList.subList(index, count));
			}
		}else{
			System.out.println(phoneList);
		}
		
		String flag="true";
		boolean a=Boolean.valueOf( flag);
		System.out.println(a);
		
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		System.out.println(list.isEmpty());
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("busi_id", "a");
		System.out.println(map.get("BUSI_ID"));
		
		String j =" ";
		j="240";
		System.out.println(j.equals("240"));*/
		/*Random rand=new Random();
		int index=rand.nextInt(1);
		System.out.println(index);*/
		/*List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		Map map=new HashMap<String, Object>();
		map.put("1", "1");
		Map map1=new HashMap<String, Object>();
		map1.put("2", "2");
		Map map2=new HashMap<String, Object>();
		map2.put("3", "3");
		list.add(map);
		list.add(map1);
		list.add(map2);
		
		List<Map<String, Object>> list2=new ArrayList<Map<String,Object>>();
		Map map3=new HashMap<String, Object>();
		map3.put("1", "1");
		Map map4=new HashMap<String, Object>();
		map4.put("3", "3");
		list2.add(map3);
		list2.add(map4);
		list.removeAll(list2);
		System.out.println(list.toString());*/
		/*List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("guojia","\"chian\"");
		map.put("guojia2","\"china2\"");
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("guojia","\"china\"");
		map2.put("guojia2","\"china2\"");
		list.add(map);
		list.add(map2);
		String req=list.toString();
		System.out.println(req);
		JSONArray jsonArray=JSONArray.fromObject(req);
		System.out.println(jsonArray);*/
		Calendar calendar=Calendar.getInstance(); 
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(date));
		
	}
	
}
