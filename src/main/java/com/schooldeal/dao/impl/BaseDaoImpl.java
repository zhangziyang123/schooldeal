package com.schooldeal.dao.impl;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

import com.schooldeal.bean.InputObject;
import com.schooldeal.dao.IBaseDao;

/**
 * 
 * 
 */
public class BaseDaoImpl implements IBaseDao {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<Map<String, Object>> query(String namespace,String statement) {
		long bengin = System.currentTimeMillis();
		List<Map<String, Object>> dataList = sqlSessionTemplate.<Map<String,Object>>selectList(changeStatement(namespace,statement));
		return dataList;
	}

	public List<Map<String, Object>> query(String namespace,String statement,Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		List<Map<String, Object>> dataList = sqlSessionTemplate.<Map<String,Object>>selectList(changeStatement(namespace,statement),paramData);
		return dataList;
	}

	public List<Map<String, Object>> query(String namespace,String statement,Map<String, Object> paramData, int limit, int offset) {
		long bengin = System.currentTimeMillis();
		RowBounds rowBounds = new RowBounds((offset-1)*limit, limit);
		List<Map<String, Object>> dataList = sqlSessionTemplate.<Map<String,Object>>selectList(changeStatement(namespace,statement),paramData,rowBounds);
		return dataList;
	}


	public int count(String namespace,String statement) {
		long bengin = System.currentTimeMillis();
		int i=sqlSessionTemplate.<Integer>selectOne(changeStatement(namespace,statement));
		return i;
	}

	
	public int count(String namespace,String statement, Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		int i=sqlSessionTemplate.<Integer>selectOne(changeStatement(namespace,statement),paramData);
		return i;
	}


	public Map<String, Object> get(String namespace,String statement,Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		Map<String, Object> dataMap =  sqlSessionTemplate.<Map<String, Object>>selectOne(changeStatement(namespace,statement),paramData);
		return dataMap;
	}


	public Map<String, Object> load(String namespace, String key,
			String value) {
		long bengin = System.currentTimeMillis();
		Map<String,String> param = new HashMap<String,String>();
		param.put(key, value);
		Map<String, Object> dataMap = sqlSessionTemplate.<Map<String, Object>>selectOne(changeStatement(namespace,"load"),param);
		return dataMap;
	}

	
	public void insert(String namespace,String statement, Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		sqlSessionTemplate.insert(changeStatement(namespace,statement), paramData);
	}

	public void insertBatch(String namespace,String statement,List  <Map<String, Object>> list) {
		long bengin = System.currentTimeMillis();
		sqlSessionTemplate.insert(changeStatement(namespace,statement), list);
	}
	public int update(String namespace,String statement, Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		int i=sqlSessionTemplate.update(changeStatement(namespace,statement), paramData);
		return i;
	}

	
	public int delete(String namespace,String statement, Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		int i= sqlSessionTemplate.delete(changeStatement(namespace,statement), paramData);
		return i;
	}
	
	private String changeStatement(String namespace,String statement){
		return namespace + "." + statement;
	}

	@SuppressWarnings("unused")
	private void changeDateType(List<Map<String,Object>> dataList){
		if(dataList == null || dataList.isEmpty()){
			return;
		}
		
		for(Map<String,Object> map : dataList){
			for(Map.Entry<String, Object> entry : map.entrySet()){
				if(entry.getValue().getClass() == Timestamp.class){
					map.put(entry.getKey(), sdf.format((Timestamp)entry.getValue()));
				}
			}
		}
		
	}
	
	@SuppressWarnings("unused")
	private void changeDateType(Map<String,Object> dataMap){
		if(dataMap == null || dataMap.size() == 0){
			return;
		}
		
		for(Map.Entry<String, Object> entry : dataMap.entrySet()){
			if(entry.getValue().getClass() == Timestamp.class){
				dataMap.put(entry.getKey(),  sdf.format((Timestamp)entry.getValue()));
			}
		}
			
	}

	public List<Map<String, Object>> query(String namespace, String statement,
			int limit, int offset) {
		long bengin = System.currentTimeMillis();
		RowBounds rowBounds = new RowBounds((offset-1)*limit, limit);
		List<Map<String, Object>> dataList = sqlSessionTemplate.<Map<String,Object>>selectList(changeStatement(namespace,statement),null,rowBounds);
		return dataList;
	}


	public String getSql(String namespace, String statement,Map<String, Object> paramData) {
		long bengin = System.currentTimeMillis();
		MappedStatement ms = sqlSessionTemplate.getConfiguration().getMappedStatement(namespace+"."+statement);
		BoundSql boundSql = ms.getBoundSql(paramData);
		return boundSql.getSql();
	}
	
	
	
	 public void batchUpdate( String namespace,String statement, final InputObject in)  throws DataAccessException{
	        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	        try{
	        	List<Map<String,Object>> list=in.getBeans();
	            if(null != list &&  !list.isEmpty()){
	                int size = 100;
	                for (int i = 0, n = list.size(); i < n; i++) {
	                    session.update(changeStatement(namespace,statement), list.get(i));
	                    if (i!=0 && i % size == 0 ) {
	                        //手动每1000个一提交，提交后无法回滚
	                        session.commit();
	                        //清理缓存，防止溢出
	                        session.clearCache();
	                    }
	                }
	                session.commit();
                 //清理缓存，防止溢出
                 session.clearCache();
	            }
	        }catch (Exception e){
	            session.rollback();
	        } finally {
	            session.close();
	        }
	    }
	     
	    public void batchInsert( String namespace,String statement, final InputObject in){
	        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	        List<Map<String,Object>> list=in.getBeans();
	        int size=1000;
	        try{
	            if(null != list &&  !list.isEmpty()){
	                for (int i = 0, n = list.size(); i < n; i++) {
	                    session.insert(changeStatement(namespace,statement), list.get(i));
	                    if (i!=0 && i % size == 0) {
	                        //手动每1000个一提交，提交后无法回滚
	                        session.commit(); 
	                        //清理缓存，防止溢出
	                        session.clearCache();
	                    }
	                }
	                session.commit();
                 //清理缓存，防止溢出
                 session.clearCache();
	            }
	        }catch (Exception e){
	        	 session.rollback();
	        } finally {
	            session.close();
	        }
	    }
	    
	 
	    public void batchDelete( String namespace,String statement, final InputObject in)  throws DataAccessException{
	        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
	        List<Map<String,Object>> list=in.getBeans();
	        int size = 1000;
	        try{
	            if(null != list && !list.isEmpty()){
	                for (int i = 0, n = list.size(); i < n; i++) {
	                	 session.delete(changeStatement(namespace,statement), list.get(i));
	                    if (i!=0 &&  i % size == 0) {
	                        //手动每1000个一提交，提交后无法回滚
	                        session.commit();
	                        //清理缓存，防止溢出
	                        session.clearCache();
	                    }
	                }
	                session.commit();
                 //清理缓存，防止溢出
                 session.clearCache();
	            }
	        }catch (Exception e){
	            session.rollback();
	        } finally {
	            session.close();
	        }
	    }
	    
	public void batchInsertOrm(String namespace, String statement,
			InputObject in) {
		long bengin = System.currentTimeMillis();
		sqlSessionTemplate.insert(changeStatement(namespace,statement), in.getBeans());
		
	}

	public void batchUpdateOrm(String namespace, String statement,
			InputObject in) {
		long bengin = System.currentTimeMillis();
		sqlSessionTemplate.update(changeStatement(namespace,statement), in.getBeans());
		
	}

	public void batchDeleteOrm(String namespace, String statement,
			InputObject in) {
		long bengin = System.currentTimeMillis();
		sqlSessionTemplate.delete(changeStatement(namespace,statement), in.getBeans());
	}
	
	public <T> List<T> queryForList(String sqlId, Map<String, Object> params, Class<T> cls) {
		return getSqlSessionTemplate().selectList(sqlId, params);
	}
}