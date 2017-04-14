package com.uws.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.dao.ISysDicDao;
import com.uws.model.Dic;
/**
 * @ClassName: SysDicDaoImpl 
 * @author: 石焱 
 * @date: 2017-2-15 下午3:44:42
 */
@Repository
public class SysDicDaoImpl extends BaseDAOImpl implements ISysDicDao {
	
	
	/**
	 * 查询字典分类信息详情
	 * */
	public Page search(Map<String, Object> param,Dic dic){
		
		StringBuffer sql = new StringBuffer(" SELECT * FROM SYS_DIC WHERE  P_ID IS NULL  ");
		
		//字典名称模糊查询
		if(DataUtil.isNotNull(dic.getName())){
			sql.append("AND NAME LIKE '%"+dic.getName().trim()+"%' ");
		}
		//字典编码模糊查询
		if(DataUtil.isNotNull(dic.getCode())){
			sql.append("AND CODE LIKE '%"+dic.getCode().trim()+"%' ");
		}
		
		sql.append(" ORDER BY REC_TIME DESC ");
		
		param.put("sql", sql.toString());
		Page page = queryBySql(param);
		return page;
		
	}

	/**
	 * 根据id查对象
	 */
	@Override
	public Dic findById(String id) {
		return (Dic) this.get(Dic.class,Long.parseLong(id));
	}

	/**
	 * 根据字典分类 code 查询所有字典项
	 */
	@Override
	public List<Dic> getDicListByCode(String code) {
		
		//启用状态字典
		Dic enable = this.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_ENABLE);
		
		List<String> parm = new ArrayList<String>();
		
		String hql;
		
		
		
		if(DataUtil.isNotNull(code)){
			hql = " select d from Dic d,Dic pd  where d.pid = pd.id and pd.code = ?  ";
			parm.add(code);
		}
		//如果code为null，查询已启用的字典分类集合
		else{
			 hql = " from Dic d where d.pid is null and d.status = "+enable.getId() ;
		}
		
		List<BaseModel> list = this.find(hql,parm.toArray());
		
		List<Dic> returnList = new ArrayList<Dic>();
	
		for (BaseModel baseModel : list) {
			returnList.add((Dic)baseModel);
		}
	
		return returnList;
	}

	@Override
	public Page searchItem(Map<String, Object> param, Dic dic) {
		
		//启用状态字典
		Dic enable = this.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_ENABLE);
		
		StringBuffer sql = new StringBuffer(" SELECT D.ID AS ID,D.CODE AS CODE,D.NAME AS NAME,D.STATUS AS STATUS,D.VAL AS VAL, PD.NAME AS PNAME FROM SYS_DIC D LEFT JOIN SYS_DIC PD ON D.P_ID = PD.ID WHERE  D.P_ID IS NOT NULL AND PD.STATUS =  "+enable.getId());
		
		//字典分类
		if(DataUtil.isNotNull(dic.getPid())){
			sql.append(" AND D.P_ID = '"+dic.getPid()+"' ");
		}
		//字典启用状态
		if(dic.getStatus() != -1){
			sql.append(" AND D.STATUS = "+dic.getStatus() );
		}
		
		sql.append(" ORDER BY D.REC_TIME DESC ");
		
		param.put("sql", sql.toString());
		Page page = queryBySql(param);
		return page;
	}

	@Override
	public Dic getDicByCodes(String parentCode, String code) {
		
		String hql = " select d from Dic d,Dic pd  where d.pid = pd.id and  pd.code = ? and d.code = ? and pd.status= 2 and d.status = 2 ";
		
		List<BaseModel> list = this.find(hql, new Object[] {parentCode,code});
		
		if ((list == null) || (list.size() == 0)) {
			return null;
		}
		
		return (Dic) list.get(0);
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List queryDicByCode(String code, String pid,String id) {
		
		List<Object> values = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer(" from Dic where  code = ?  ");
		values.add(code);
		
		if(DataUtil.isNotNull(pid)){
			hql.append("  and pid = ? ");
			values.add(Long.parseLong(pid));
		}
		//编辑时，过滤掉自身，否则无法保存
		if(DataUtil.isNotNull(id)){
			hql.append("  and id != ? ");
			values.add(Long.parseLong(id));
		}
		
		List list = this.find(hql.toString(),values.toArray());
		
		return list;
	}
	
}
