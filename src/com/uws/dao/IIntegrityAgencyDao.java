package com.uws.dao;

import com.base.dao.IBaseDAO;
import com.base.model.BaseModel2;

public interface IIntegrityAgencyDao extends IBaseDAO {
	
	public void save(BaseModel2 ia);

	public <T> T getById(Class<T> c,String id);

	public void deleteIA(BaseModel2 ia);
}
