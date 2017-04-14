package com.uws.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.uws.model.SysMenu;

/**
 * 通用工具类
 */
public class Util {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            字符串
	 * @return true：为空； false：非空
	 */
	public static boolean isNull(String str) {
		if (str != null && !str.trim().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * MD5 加密
	 */
	public static String encryptMD5(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
	
	public static String UUId() {
		 return UUID.randomUUID().toString().replace("-","");
	}

	/**
	 * 得到抛异常的信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable t) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		StringBuffer buffer = stringWriter.getBuffer();
		return buffer.toString();
	}

	public static void main(String[] args) {
		//System.out.println(Util.encryptMD5("123456"));
		//System.out.println(UUID.randomUUID().toString().replace("-", ""));
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		System.out.println("格式化======="+nf.format(10000000));
	}
	
	/**
	 * 对用户权限下的所有菜单进行分级和封装
	 * @param list
	 * @return 返回 四层嵌套的集合, 树型结构
	 */
	@SuppressWarnings({ "rawtypes", "unchecked"})
	public static Map<String,List<Map<String,String>>> resortMenus(List<Map> list,SysMenu rootMenu){
		
		//结果集合,将传递给前台
		Map<String,List<Map<String,String>>> resultMap = new LinkedHashMap<String, List<Map<String,String>>>();
		//存放一级菜单
		List<Map<String,String>> firstLevelList = new ArrayList<Map<String,String>>();
		//暂存
		List<Map> bufferList = new ArrayList<Map>();
		bufferList.addAll(list);
		
		//取一级菜单
		for (int i = 0; i < list.size(); i++) {
			Map map = list.get(i);
			String parentId = (String) map.get("P_ID");
			if(rootMenu.getId().toString().equals(parentId)){
				firstLevelList.add(map);
				//去掉一级菜单,方便后面遍历
				bufferList.remove(map);
			}
		}
		
		//取二级
		for(int i = 0; i < firstLevelList.size(); i++){
			Map<String,String> map = firstLevelList.get(i);
			String id = map.get("ID");
			
			List<Map<String,String>> secondLevelResultList = new ArrayList<Map<String,String>>();
			for (Map m : bufferList) {
				String parentId = (String) m.get("P_ID");
				if(id != null && id.equals(parentId)){
					secondLevelResultList.add(m);
				}
			}
			
			resultMap.put(map.get("TITLE"), secondLevelResultList);
		}
		
		return resultMap;
	}
	
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
}
