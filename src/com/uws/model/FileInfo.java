package com.uws.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.base.model.BaseModel2;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FileInfo extends BaseModel2 implements Serializable{

	private static final long serialVersionUID = 917047077685164010L;

	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 文件路径(点击图片跳的路径)
	 */
	private String url;
	
	/**
	 * 链接类型（站内和外链接）
	 */
	private String urlType;
	
	/**
	 * 上传时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date fileTime;
	
	/**
	 * 文件状态
	 */
	private String status;
	
	/**
	 * 文件title（鼠标移上去显示的title）
	 */
	private String fileTitle;
	
	/**
	 * 文件类型
	 */
	private String fileType;
	
	/**
	 * 大模块编码
	 */
	private String moduleCode;
	
	/**
	 * 父编码（相对小类）
	 */
	private String parentCode;
	
	/**
	 * 备注
	 */
	private String comments;
	
	/**
	 * 图片显示路径
	 */
	public String filePath;
	
	/**
	 * 虚拟  广告位置名称
	 */
	private String adLoacName;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	public Date getFileTime() {
		return fileTime;
	}
	public void setFileTime(Date fileTime) {
		this.fileTime = fileTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFileTitle() {
		return fileTitle;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getAdLoacName() {
		return adLoacName;
	}
	public void setAdLoacName(String adLoacName) {
		this.adLoacName = adLoacName;
	}
	
}
