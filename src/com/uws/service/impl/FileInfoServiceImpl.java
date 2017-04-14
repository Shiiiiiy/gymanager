package com.uws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.uws.dao.IFileInfoDAO;
import com.uws.model.FileInfo;
import com.uws.service.IFileInfoService;
import com.uws.util.Constants;
/**
 * 企业信息资源管理Service实现
 * @author 张学彪
 */
@Service("fileInfoService")
public class FileInfoServiceImpl implements IFileInfoService{

	@Autowired
	private IFileInfoDAO fileDAO;

	@Override
	public void saveFileInfo(FileInfo fileInfo) {
		fileDAO.saveFileInfo(fileInfo);
	}

	@Override
	public List<FileInfo> queryFileInfoList(String ... str) {
		List<FileInfo> results = new ArrayList<FileInfo>(); 
		String[] array = null;
		if(str != null && str.length > 0){
			array = new String[str.length];
			for(int i=0; i<str.length; i++){
				array[i] = str[i];
			}
		}
		List<FileInfo> list = fileDAO.queryFileInfoList(array);
		if(list != null && list.size()>0){
			for(FileInfo file:list){
				file.setFilePath(Constants.FILE_PATH+"/"+file.getFileName());
				results.add(file);
			}
		}
		return results;
	}

	@Override
	public void updateFileName(String fileId,String fileTitle,String url,String urlType,String sort) {
		fileDAO.updateFileName(fileId, fileTitle, url, urlType, sort);
	}

	@Override
	public void deleteFileById(String fileId) {
		fileDAO.deleteFileById(fileId);
	}
	
	
	/**
	 * 查询一个企业的所有图片
	 * @param file_type
	 * @param parent_code
	 * @return
	 */
	@Override
	public List<FileInfo> queryFileInfoListForCompany(String file_type,String parent_code){
		List<FileInfo> results = new ArrayList<FileInfo>();
		List<FileInfo> list = fileDAO.queryFileInfoListForCompany( file_type, parent_code);
		if(list != null && list.size()>0){
			for(FileInfo file:list){
				file.setFilePath(Constants.FILE_PATH+"/"+file.getFileName());
				results.add(file);
			}
		}
		return results;
	}
	
	/**
	 * 查询广告
	 * @param code
	 * @return
	 */
	public Page getAdver(Map<String, Object> param,String code, String title){
		return fileDAO.getAdver(param,code,title);
	}
	
	/**
	 * 查询广告下面图片
	 * @param file_type
	 * @param module_code
	 * @param parent_code
	 * @return
	 */
	public List<FileInfo> getAdverIamge(String file_type, String module_code,String parent_code){
		List<FileInfo> results = new ArrayList<FileInfo>();
		List<FileInfo> list = fileDAO.getAdverIamge(file_type,module_code,parent_code);
		if(list != null && list.size()>0){
			for(FileInfo file:list){
				file.setFilePath(Constants.FILE_PATH+"/"+file.getFileName());
				results.add(file);
			}
		}
		return results;
	}
	
	@Override
	public List<FileInfo> queryFileInfoListForCompany(String code,
			String parent_code, String... file_type) {
		List<FileInfo> results = new ArrayList<FileInfo>();
		List<FileInfo> list = fileDAO.queryFileInfoListForCompany(code, parent_code, file_type);
		if(list != null && list.size()>0){
			for(FileInfo file:list){
				file.setFilePath(Constants.FILE_PATH+"/"+file.getFileName());
				results.add(file);
			}
		}
		return results;
	}
	
	@Override
	public Page pageQueryFiles(Map<String, Object> param,FileInfo file, String moduleCode,
			String parentCode, String fileType) {
		return fileDAO.pageQueryFiles(param,file,moduleCode,parentCode,fileType);
	}
	
	@Override
	public FileInfo queryFileById(String id) {
		return fileDAO.queryFileById(id);
	}

	@Override
	public void batchDeleteFileByIdString(String id) {
		String[] ids = id.split(",");
		if(ids.length > 0){
			for (String idStr : ids) {
				fileDAO.deleteFileById(idStr);
			}
		}
	}
	/**
	 * 更新文件表 的fileName 通过id
	 * @param id
	 * @param fileName
	 */
	@Override
	public void updateFileNameById(String id, String fileName) {
		fileDAO.updateFileNameById(id,fileName);
	}
}
