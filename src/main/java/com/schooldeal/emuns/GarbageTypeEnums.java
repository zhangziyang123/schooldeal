package com.schooldeal.emuns;

import com.util.Color;

/**
 * 返回客户端信息枚举
 */
public enum GarbageTypeEnums {

	TYPE_1("1", "湿垃圾"),
	TYPE_2("2", "干垃圾"),
	TYPE_3("3", "可回收垃圾"),
	TYPE_4("4", "有害垃圾");

   
    private String id;
    private String type;
    private GarbageTypeEnums(String id, String type) {
        this.id = id;
        this.type = type;
    }
   
    public String getId() {
		return id;
	}


	public String getType() {
		return type;
	}


	public static String getType(String id){
    	String desc=null;
    	for(GarbageTypeEnums garbage:GarbageTypeEnums.values()){
    		if(id.equals(garbage.getId())){
    			desc=garbage.getType();
    		}
    	}
    	return desc;
    }
}
