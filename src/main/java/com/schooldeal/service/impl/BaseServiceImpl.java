package com.schooldeal.service.impl;

import com.schooldeal.dao.impl.BaseDaoImpl;
import com.schooldeal.service.IBaseService;

/**
 * @
 * @
 */
public class BaseServiceImpl implements IBaseService  {
	private BaseDaoImpl baseDao;



	public BaseDaoImpl getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDaoImpl baseDao) {
		this.baseDao = baseDao;
	}
}
