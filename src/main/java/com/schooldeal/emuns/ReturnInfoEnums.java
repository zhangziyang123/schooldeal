package com.schooldeal.emuns;


/**
 * 返回客户端信息枚举
 */
public enum ReturnInfoEnums {

    QUERY_OK("0000", "查询成功"),
    QUERY_ERROE("9999", "查询失败");
   
    private String code;
    private String message;
    private ReturnInfoEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
