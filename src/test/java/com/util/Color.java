package com.util;

public enum Color {

	SICHAUN("280","�Ĵ��ƶ�"),YUNAN("871009","�����ƶ�"),HUNAN("731","�����ƶ�");
	Color(String code,String desc){
		this.code=code;
		this.desc=desc;
	}
	private String code;
	private String desc;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static String getProvByCode(String provCode){
		String desc=null;
		for(Color color:Color.values()){
			if(provCode.equals(color.getCode())){
				 desc=color.getDesc();
			}
		}
		return desc;
	}
	public static void main(String[] args) {
		String prov = Color.getProvByCode("871009");
		System.out.println(prov);
	}
	
}
