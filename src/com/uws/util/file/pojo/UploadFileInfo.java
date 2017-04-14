package com.uws.util.file.pojo;

/**
 * 暴露给调用者的对象,调用者需要new出此对象并补充相应的值,
 * 程序会从此对象获取,并自动下载或预览等
 * @author zhouchang
 *
 */
public class UploadFileInfo {
	
	private String fileLocation;
	private String fileOriginalName;
	
	public UploadFileInfo(String fileLocation, String fileOriginalName) {
		this.fileLocation = fileLocation;
		this.fileOriginalName = fileOriginalName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileOriginalName() {
		return fileOriginalName;
	}

	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	
}
