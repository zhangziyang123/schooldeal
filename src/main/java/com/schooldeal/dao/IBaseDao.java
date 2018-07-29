package com.schooldeal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.schooldeal.bean.InputObject;

/**
 * Dao�ӿڻ���
 */
public interface IBaseDao {
	  public static final String MYBATIS_DAO = "daoMybatis";
	  /**
	   * ��ѯȫ������  list
	   * @param namespace
	   * @param statement
	   * @return
	   */
	  public  List<Map<String,Object>> query(String namespace,String statement);
	  /**
	   * ��ҳ��ѯ   list
	   * @param namespace
	   * @param statement
	   * @param limit
	   * @param offset
	   * @return
	   */
	  public  List<Map<String,Object>> query(String namespace,String statement,int limit, int offset);
      /**
       * ����������ѯ   list
       * @param namespace
       * @param statement
       * @param paramData
       * @return
       */
	  public  List<Map<String,Object>> query(String namespace,String statement, Map<String,Object> paramData);
      /**
       * ����������ҳ��ѯ
       * @param namespace
       * @param statement
       * @param paramData
       * @param limit
       * @param offset
       * @return
       */
	  public  List<Map<String,Object>> query(String namespace,String statement,Map<String,Object> paramData, int limit, int offset);
      /**
       * ��ѯ����
       * @param namespace
       * @param statement
       * @return
       */
	  public  int count(String namespace,String statement);
      /**
       * ����������ѯ���� 
       * @param namespace
       * @param statement
       * @param paramData
       * @return
       */
	  public  int count(String namespace,String statement, Map<String,Object> paramData);
      /**
       * ����������ѯ Map
       * @param namespace
       * @param statement
       * @param paramData
       * @return
       */
	  public  Map<String,Object> get(String namespace,String statement, Map<String,Object> paramData);

	  public  Map<String,Object> load(String namespace, String key, String value);

	  public  void insert(String namespace,String statement, Map<String,Object> paramData);

	  public  int  update(String namespace,String statement, Map<String,Object> paramData);

	  public  int  delete(String namespace,String statement, Map<String,Object> paramData);
	  
	  public  String getSql(String namespace,String statement, Map<String,Object> paramData);
	  /**
	   * mybatise 
	   * @param namespace
	   * @param statement
	   * @param in
	   */
	  public void batchInsertOrm( String namespace,String statement, final InputObject in);
	  /**
	   * mybatise 
	   * @param namespace
	   * @param statement
	   * @param in
	   */
	  public void batchUpdateOrm( String namespace,String statement, final InputObject in);
	  
	  /**
	   * mybatise 
	   * @param namespace
	   * @param statement
	   * @param in
	   */
	  public void batchDeleteOrm( String namespace,String statement, final InputObject in);
	  
	  /**
	     * ��������
	     * �����������������£�Ч��û���������ļ��ϵĸߣ������������ļ�������
	     * @param statementName
	     * @param list
	     * @throws DataAccessException
	     * @comment
	     */
	  public void batchUpdate( String namespace,String statement, final InputObject in)  throws DataAccessException;
	  /**
	     * ��������
	     * �����������������루Ч��û���������ļ��ϵĸߣ������������ļ�������
	     * @param statement
	     * @param list
	     * @throws DataAccessException
	     * @date 2013-4-3 ����11:27:39
	     * @comment
	     */
	    public void batchInsert(String namespace,String statement, final InputObject in)  throws DataAccessException;
	    /**
	     * ����ɾ��
	     * ��������������ɾ����Ч��û���������ļ��ϵĸߣ� �����������ļ�������
	     * @param statement
	     * @param in
	     * @throws DataAccessException
	     * @date 2013-4-3 ����11:29:53
	     * @comment
	     */
	    public void batchDelete( String namespace,String statement, final InputObject in)  throws DataAccessException;

}
