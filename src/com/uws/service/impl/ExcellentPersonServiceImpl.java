package com.uws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DataUtil;
import com.uws.dao.IExcellentPersonDAO;
import com.uws.model.ExcellentPerson;
import com.uws.service.IExcellentPersonService;
import com.uws.util.Constants;
/**
 * 创新人才Service实现
 * @author 张学彪
 */
@Service("excellentPersonService")
public class ExcellentPersonServiceImpl implements IExcellentPersonService{

	@Autowired
	private IExcellentPersonDAO excellentPersonDAO;

	@Override
	public Page queryExcellentPersonList(Map<String, Object> param,
			ExcellentPerson person) {
		return excellentPersonDAO.queryExcellentPersonList(param, person);
	}

	@Override
	public ExcellentPerson queryExcellentPersonByPersonId(String personId) {
		ExcellentPerson person = excellentPersonDAO.queryExcellentPersonByPersonId(personId);
		if(DataUtil.isNotNull(person.getPsImage())){
			person.setImageUrl(Constants.FILE_PATH+"/"+person.getPsImage());
		}
		return person;
	}

	@Override
	public void saveExcellentPerson(ExcellentPerson person) {
		excellentPersonDAO.saveExcellentPerson(person);
	}

	@Override
	public void updateExcellentPerson(ExcellentPerson person) {
		excellentPersonDAO.updateExcellentPerson(person);
	}

	@Override
	public void deleteExcellentPersonById(String personId) {
		excellentPersonDAO.deleteExcellentPersonById(personId);
	}

	@Override
	public void deleteExcellentPersonsByIds(String personIds) {
		List<String> idList = new ArrayList<String>();
		if(DataUtil.isNotNull(personIds)){
			String[] idArray = personIds.split(",");
			for(String id:idArray){
				idList.add(id);
			}
		}
		if(idList.size()>0){
			excellentPersonDAO.deleteExcellentPersonsByIds(idList);
		}
	}

	@Override
	public void changeStatus(String personId, String value) {
		excellentPersonDAO.changeStatus(personId, value);
	}

}
