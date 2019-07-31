package com.schooldeal.util;

import com.baidu.aip.imageclassify.AipImageClassify;

public class PictureDisc {
	 public static final String APP_ID = "16730625";
	 public static final String API_KEY = "GI3UfkbjwczZU90HBOIL2DOz";
	 public static final String SECRET_KEY = "HsGa26XHD6HiLBupGjvtO0B9Pn0DHzjh";
	 private static AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
	 
	   
	 
	    // 以自己实例为返回值的静态的公有方法，静态工厂方法
	    public static AipImageClassify getSingleton1(){
	        return client;
	    }
	    
	    public static AipImageClassify getclient(){
	    	AipImageClassify client = getSingleton1();
	    	client.setConnectionTimeoutInMillis(2000);
	        client.setSocketTimeoutInMillis(60000);
	        //client.setHttpProxy("https://aip.baidubce.com/rest/2.0/image-classify/v2/advanced_general", 80);
	        return client;
	    }

}
