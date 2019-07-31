package com.thread;

import java.util.HashMap;
import java.util.Map;

public class StaticData {

	public static Map<String, Object> map=new HashMap<>();
	
	public static void a(Map map){
		map= new HashMap<>();
	}
}
