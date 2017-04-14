package com.uws.dao.impl;

import org.springframework.stereotype.Repository;

import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel2;
import com.uws.dao.IIntegrityAgencyDao;
import com.uws.model.IntegrityAgency;

@Repository("integrityAgencyDao")
public class IntegrityAgencyDaoImpl extends BaseDAOImpl implements IIntegrityAgencyDao {

	@Override
	public void save(BaseModel2 ia) {
		this.save2(ia);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Class<T> c,String id) {
		return (T) this.getSe(c, id);
	}

	@Override
	public void deleteIA(BaseModel2 ia) {
		this.deleteSe(ia);
	}

}
