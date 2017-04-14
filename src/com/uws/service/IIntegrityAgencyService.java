package com.uws.service;

import java.util.List;

import com.base.model.BaseModel2;
import com.uws.model.IntegrityAgency;

public interface IIntegrityAgencyService {
	
	public void save(BaseModel2 ia);

	public <T> T getById(Class<T> c ,String id);

	public void deleteIA(List<BaseModel2> iaList);
}
