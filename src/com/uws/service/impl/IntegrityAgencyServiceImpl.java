package com.uws.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.model.BaseModel2;
import com.uws.dao.IIntegrityAgencyDao;
import com.uws.model.IntegrityAgency;
import com.uws.service.IIntegrityAgencyService;

@Service("integrityAgencyService")
public class IntegrityAgencyServiceImpl implements IIntegrityAgencyService {

	@Autowired(required=true)
	private IIntegrityAgencyDao integrityAgencyDao;
	
	@Override
	public void save(BaseModel2 ia) {
		integrityAgencyDao.save(ia);
	}

	@Override
	public <T> T getById(Class<T> c ,String id) {
		return integrityAgencyDao.getById(c,id);
	}

	@Override
	public void deleteIA(List<BaseModel2> iaList) {
		for (BaseModel2 ia : iaList) {
			if(ia != null)
				integrityAgencyDao.deleteIA(ia);
		}
		
	}

}
