package com.uws.util.file;

import java.util.HashMap;
import java.util.Map;

//模拟数据库
public class TempDataBase {

	private static Map<String,Map<String,String>> dataBase = new HashMap<String,Map<String,String>>();
	
	public static Map<String,Map<String,String>> getDataBase(){
		return dataBase;
	}
}
 