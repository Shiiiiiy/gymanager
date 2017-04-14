package com.uws.util.file.dto;

/**
 * 数据转输对象,用于与前端通信和交互
 * @author zhouchang
 *
 */
public class UploadResult {

	//数据是否可取
	private boolean result;
	//可取数据的内容(文件在服务器端的绝对地址)
	private Object location;
	//文件原始名称
	private String originalfileName;
	//不可取数据的原因
	private String message;
	//是否是图片文件
	private boolean isPictureFile = false;
	//服务器端保存的文件名
	private String fileNameInServer;
	//文件的相对路径
	private String fileRelativePath;
	
	
	public UploadResult(boolean result, Object location, 
			String message,String originalfileName) {
		super();
		this.result = result;
		this.location = location;
		this.message = message;
		this.originalfileName = originalfileName;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public Object getLocation() {
		return location;
	}

	public void setLocation(Object location) {
		this.location = location;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isPictureFile() {
		return isPictureFile;
	}

	public void setPictureFile(boolean isPictureFile) {
		this.isPictureFile = isPictureFile;
	}

	public String getOriginalfileName() {
		return originalfileName;
	}

	public void setOriginalfileName(String originalfileName) {
		this.originalfileName = originalfileName;
	}

	public String getFileNameInServer() {
		return fileNameInServer;
	}

	public void setFileNameInServer(String fileNameInServer) {
		this.fileNameInServer = fileNameInServer;
	}

	public String getFileRelativePath() {
		return fileRelativePath;
	}

	public void setFileRelativePath(String fileRelativePath) {
		this.fileRelativePath = fileRelativePath;
	}
	
	
}
