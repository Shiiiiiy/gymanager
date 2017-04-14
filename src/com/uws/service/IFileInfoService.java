package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.FileInfo;

/**
 * 企业信息资源管理Service
 * @author 张学彪
 */
public interface IFileInfoService {

	/**
	 * 保存上传的图片信息
	 */
	public void saveFileInfo(FileInfo fileInfo);
	
	/**
	 * 查询企业信息-资源管理
	 */
	public List<FileInfo> queryFileInfoList(String ... s);
	
	/**
	 * 修改图片title
	 */
	public void updateFileName(String fileId,String fileTitle,String url,String urlType,String sort);
	
	/**
	 * 删除选中图片
	 */
	public void deleteFileById(String fileId);
	
	/**
	 * 查询一个企业的所有图片
	 * @param file_type
	 * @param parent_code
	 * @return
	 */
	public List<FileInfo> queryFileInfoListForCompany(String file_type,String parent_code);
	
	/**
	 * 查询广告
	 * @param code
	 * @return
	 */
	public Page getAdver(Map<String, Object> param,String code, String title);
	
	
	/**
	 * 查询广告下面图片
	 * @param file_type
	 * @param module_code
	 * @param parent_code
	 * @return
	 */
	public List<FileInfo> getAdverIamge(String file_type, String module_code,String parent_code);
	
	/**
	 * 重载的文件查询方法 --zhouchang
	 * @param file_type
	 * @param parent_code
	 * @return
	 */
	public List<FileInfo> queryFileInfoListForCompany(String code, String parent_code, String... file_types);

	/**
	 * 分页按条件查询文件表
	 * @param file
	 * @param moduleCode
	 * @param parentCode
	 * @param fileType
	 * @return
	 */
	public Page pageQueryFiles(Map<String, Object> param,FileInfo file, String moduleCode, String parentCode,
			String fileType);

	/**
	 * 查询单个的文件
	 * @param id
	 * @return
	 */
	public FileInfo queryFileById(String id);

	/**
	 * 批量删除文件
	 * @param id
	 */
	public void batchDeleteFileByIdString(String id);
	/**
	 * 更新文件表 的fileName 通过id
	 * @param id
	 * @param fileName
	 */
	public void updateFileNameById(String id, String fileName);


}
