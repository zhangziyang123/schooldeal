package com.schooldeal.emuns;


/**
 * ���ؿͻ�����Ϣö��
 */
public enum ReturnInfoEnums {

    QUERY_OK("0000", "��ѯ�ɹ�"),
    QUERY_ERROE("9999", "��ѯʧ��");
   
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
