package com.schooldeal.emuns;

import com.util.Color;

/**
 * ���ؿͻ�����Ϣö��
 */
public enum GarbageTypeEnums {

	TYPE_1("1", "ʪ����"),
	TYPE_2("2", "������"),
	TYPE_3("3", "�ɻ�������"),
	TYPE_4("4", "�к�����");

   
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
