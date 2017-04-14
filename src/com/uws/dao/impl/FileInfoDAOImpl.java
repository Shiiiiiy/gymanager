package com.uws.dao.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.base.dao.Page;
import com.base.dao.impl.BaseDAOImpl;
import com.base.model.BaseModel2;
import com.uws.dao.IFileInfoDAO;
import com.uws.model.FileInfo;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 企业信息资源管理Dao实现
 * @author 张学彪
 */
@Repository
public class FileInfoDAOImpl extends BaseDAOImpl implements IFileInfoDAO{

	@Override
	public void saveFileInfo(FileInfo fileInfo) {
		this.save2(fileInfo);
	}

	public List<FileInfo> queryFileInfoList(String moduleCode) {
		StringBuffer sql=new StringBuffer("FROM FileInfo WHERE MODULE_CODE = ? AND FILE_TYPE in(?,?)");
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		List<BaseModel2> results = this.findSe(sql.toString(),new String[]{moduleCode,Constants.FILE_SLIDER,Constants.FILE_STATISTICS});
		if(results != null && results.size()>0){
			for(BaseModel2 bm:results){
				fileList.add((FileInfo)bm);
			}
		}
		return fileList;
	}
	
	@Override
	public List<FileInfo> queryFileInfoList(String[] array) {
		StringBuffer sb=new StringBuffer("FROM FileInfo WHERE MODULE_CODE = ? AND PARENT_CODE LIKE '%_MAIN%' AND FILE_TYPE in(");
		if(array!=null && "MODULE_H".endsWith(array[0])){//开特例 创新创业
			sb=new StringBuffer("FROM FileInfo WHERE MODULE_CODE = ? AND PARENT_CODE IS NULL AND FILE_TYPE in(");
		}
		for(int i=1;i<array.length;i++){
			if(i == array.length-1){
				sb.append("?)");
			}else{
				sb.append("?,");
			}
		}
		sb.append(" ORDER BY SORT ASC,CREATE_TIME DESC");
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		List<BaseModel2> results = this.findSe(sb.toString(),array);
		if(results != null && results.size()>0){
			for(BaseModel2 bm:results){
				fileList.add((FileInfo)bm);
			}
		}
		return fileList;
	}
	
	@Override
	public List<FileInfo> queryFileInfoListForCompany(String file_type,String parent_code){
		StringBuffer sb = new StringBuffer("FROM FileInfo WHERE FILE_TYPE = '"+file_type+"' AND parent_code = '"+parent_code+"'  ORDER BY CREATE_TIME DESC");
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		List<BaseModel2> results = this.findSe(sb.toString());
		if(results != null && results.size()>0){
			for(BaseModel2 bm:results){
				fileList.add((FileInfo)bm);
			}
		}
		return fileList;
	}

	@Override
	public void updateFileName(String fileId, String fileTitle,String url,String urlType,String sort) {
		StringBuffer sb = new StringBuffer("UPDATE FILE_INFO SET FILE_TITLE='");
		sb.append(fileTitle).append("' ");
		if("".equals(url)){
			sb.append(", URL = NULL ");
		}else{
			sb.append(", URL ='").append(url).append("' ");
		}
		sb.append(", URL_TYPE ='")
		.append(urlType).append("', SORT =")
		.append(sort).append(" WHERE ID='")
		.append(fileId).append("'");
		this.executeSQL(sb.toString());
	}

	@Override
	public void deleteFileById(String fileId) {
		StringBuffer sb = new StringBuffer("DELETE FROM  FILE_INFO WHERE ID ='").append(fileId).append("'");
		this.executeSQL(sb.toString());
	}
	
	@Override
	public Page getAdver(Map<String, Object> param, String code,String title){
		StringBuffer sql = new StringBuffer("SELECT *  FROM file_info ad WHERE FILE_TYPE LIKE '%AD%'  ");
		/*sql.append("SELECT * FROM file_info WHERE FILE_TITLE like '%广告%' UNION  ");
		sql.append("SELECT * FROM file_info where FILE_TYPE = 'FILE_BANNER') ad  WHERE 1=1 ");*/
		if(!Util.isNull(code)){
			sql.append("AND (ad.MODULE_CODE = '").
			append(code).append("' OR ad.FILE_TYPE like '").
			append(code).append("' ").append("OR ad.PARENT_CODE = '").append(code).append("' )");
		}
		sql.append(" GROUP BY ad.FILE_TYPE ,ad.MODULE_CODE,ad.PARENT_CODE");
		if(!Util.isNull(title)){
			sql.append(" HAVING ad.FILE_TITLE like '%").append(title).append("%' ");
		}
		param.put("sql", sql.toString());
		Page page = this.queryBySql(param);
		
		return page;
	}
	
	@Override
	public List<FileInfo> getAdverIamge(String file_type,String module_code,String parent_code ){
		StringBuffer sql = new StringBuffer("FROM FileInfo WHERE 1 = 1 ");
		if(!Util.isNull(file_type)){
			sql.append("AND file_type = '").append(file_type).append("'");
		}else{
			sql.append("AND file_type IS NULL ");
		}
		if(!Util.isNull(module_code)){
			sql.append("AND module_code = '").append(module_code).append("'");
		}else{
			sql.append("AND module_code IS NULL ");
		}
		if(!Util.isNull(parent_code)){
			sql.append("AND parent_code = '").append(parent_code).append("'");
		}else{
			sql.append("AND parent_code IS NULL ");
		}
		sql.append("ORDER BY sort");
		List<BaseModel2> list = this.findSe(sql.toString());
		List<FileInfo> listResult = new ArrayList<FileInfo>();
		for(BaseModel2 model :list){
			listResult.add((FileInfo)model);
		}
		return listResult;
	}

	@Override
	public List<FileInfo> queryFileInfoListForCompany(String code,
			String parentCode, String... fileType) {
		//StringBuffer sb = new StringBuffer("FROM FileInfo WHERE 1=1  FILE_TYPE = '"+file_type+"' AND parent_code = '"+parent_code+"'  ORDER BY CREATE_TIME DESC");
		StringBuffer hql = new StringBuffer("from FileInfo where 1=1 ");
		if(code != null && !"".equals(code)){
			hql.append(" and moduleCode = '" + code + "'" );
		}
		if(parentCode != null && !"".equals(parentCode)){
			if("N".equals(parentCode)){
				hql.append(" and parentCode is null ");
			}else{
				hql.append(" and parentCode = '" + parentCode + "'" );
			}
		}
		if(fileType != null && fileType.length > 0){
			hql.append(" and fileType in (");
			for (int i = 0; i < fileType.length; i++) {
				if(i == fileType.length - 1){
					hql.append("'" + fileType[i] + "')");
				}else{
					hql.append("'" + fileType[i] + "',");
				}
			}
		}
		hql.append(" order by sort,fileTime desc");
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		List<BaseModel2> results = this.findSe(hql.toString());
		if(results != null && results.size()>0){
			for(BaseModel2 bm:results){
				fileList.add((FileInfo)bm);
			}
		}
		return fileList;
	}

	@Override
	public Page pageQueryFiles(Map<String, Object> param,FileInfo file, String moduleCode,
			String parentCode, String fileType) {
		StringBuffer sql = new StringBuffer("SELECT *,DATE_FORMAT(FILE_TIME,'%Y-%c-%d %h:%i:%s')AS STIME  FROM FILE_INFO F WHERE 1=1 ");
		if(moduleCode != null && !moduleCode.equals("")){
			if("N".equals(moduleCode)){
				sql.append(" AND F.MODULE_CODE IS NULL ");
			}else{
				sql.append(" AND F.MODULE_CODE = '" + moduleCode + "' ");
			}
		}
		
		if(!"".equals(parentCode) && parentCode!=null ){
			if("N".equals(parentCode)){
				sql.append(" AND F.PARENT_CODE IS NULL ");
			}else{
				sql.append(" AND F.PARENT_CODE = '" + parentCode + "' ");
			}
		}
		
		if(!"".equals(fileType) && fileType!=null ){
			if("N".equals(fileType)){
				sql.append(" and f.FILE_TYPE is null ");
			}else{
				sql.append(" and f.FILE_TYPE = '" + fileType + "' ");
			}
		}
		
		if(file != null){
			if(file.getFileTitle() != null 
					&& !file.getFileTitle().equals("")){
				sql.append(" and lower(f.FILE_TITLE) like '%" + file.getFileTitle().toLowerCase() + "%'");
			}
			
			if(file.getFileTime() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				sql.append(" and DATE_FORMAT(f.FILE_TIME,'%Y%m%d') = '")
				   .append(sdf.format(file.getFileTime())).append("'");
			}
		}
		sql.append(" order by f.CREATE_TIME DESC");
		param.put("sql", sql.toString());
		
		return queryBySql(param);
	}
	
	@Override
	public FileInfo queryFileById(String id) {
		return (FileInfo) this.getSe(FileInfo.class, id);
	}
	/**
	 * 更新文件表 的fileName 通过id
	 * @param id
	 * @param fileName
	 */
	@Override
	public void updateFileNameById(String id, String fileName) {
		StringBuffer sql = new StringBuffer("UPDATE FILE_INFO SET FILE_NAME = '");
		sql.append(fileName).append("'").append("  WHERE ID ='").append(id).append("'");
		executeSQL(sql.toString());
	}
	
}
