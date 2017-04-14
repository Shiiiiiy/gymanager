package com.uws.util.excel;

/**
 * 对应每列的验证
 * @author zhangaw
 *
 */
public interface ValidateRule {
	/**
	 * 验证字段的方法  需要根据实际业务 在调用时实现
	 * @param cellValue 单元格内的 内容
	 * @return 如果验证不通过  返回提示的信息 通过请返回 null 而不是空字符串 切记
	 */
	String validate(Object cellValue);
}
