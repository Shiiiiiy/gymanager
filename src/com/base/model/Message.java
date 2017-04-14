package com.base.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 此类主要用于更新数据操作后跳转至中转页面使用
 * @author MaXiaoDong
 *
 */
public class Message {


	private List<Map> messageParamList;
	
	private String url;
	
	private String tips;

	public Message(String url){
		this.url = url;
	}
	
	
	/**
	 * 往参数集合中追加参数
	 * @param key 变量名
	 * @param value 变量对应的值
	 */
	public void addParamForward(String key,String value){
		if(messageParamList==null){
			messageParamList = new ArrayList<Map>();
		}
		Map map = new HashMap<String, String>();
		map.put("key", key);
		map.put("val", value);
		messageParamList.add(map);
	}


	public List<Map> getMessageParamList() {
		return messageParamList;
	}


	public void setMessageParamList(List<Map> messageParamList) {
		this.messageParamList = messageParamList;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTips() {
		return tips;
	}


	public void setTips(String tips) {
		this.tips = tips;
	}
	
	
	
	
}
