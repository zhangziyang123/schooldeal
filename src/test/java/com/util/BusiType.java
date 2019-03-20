package com.util;

public enum BusiType {
	SICHAUN("280",SiChuanProv.class),HUNAN("731009",HuNanProv.class);
	
	
	private Class<? extends ProvBusi> cla;
	private String code; 
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public Class<? extends ProvBusi> getCla() {
		return cla;
	}
	public void setCla(Class<? extends ProvBusi> cla) {
		this.cla = cla;
	}
	BusiType(String code,Class<? extends ProvBusi> cla){
		this.code=code;
		this.cla=cla;
	}
	
	public static Class<? extends ProvBusi> getProvByCode(String provCode){
		Class<? extends ProvBusi> cla=null;
		for(BusiType busi:BusiType.values()){
			if(provCode.equals(busi.getCode())){
				cla=busi.getCla();
			}
		}
		return cla;
	}
	public static void main(String[] args) {
		Class<? extends ProvBusi> prov = BusiType.getProvByCode("731009");
		try {
			prov.newInstance().activite();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
