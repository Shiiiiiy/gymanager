package com.base.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.FileImageInputStream;

import org.w3c.dom.Element;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class DefaultValue {
	public final  static String SESSION_KEY = "user_key";
	/**字典表启用禁用状态CODE*/
	public final  static String ENABLE_DISABLE = "ENABLE_DISABLE";
	
	/**字典项启用状态CODE*/
	public final  static String DIC_ENABLE = "ENABLE";
	
	/**字典项禁用状态CODE*/
	public final  static String DIC_DISABLE = "DISABLE";
	
	
	/**系统默认密码*/
	public final  static String DEFAULT_PASSWORD = "12345678";
	
	
	/**跳转只中转页面 参数对象存储变量名*/
	public final  static String MESSAGE_RE_NAME = "msg";
	
	/**
	 * 广告位置编码 中文名字对应图
	 */
	public final static Map<String,String> MAP_AD_NAME = new HashMap<String, String>();
	
	/**
	 * 下载专区分类
	 */
	public static final Map<String,String> DOWNLOAD_TYPE = new HashMap<String, String>();
	
	static{
		MAP_AD_NAME.put("MODULE_K", "首页");       
		MAP_AD_NAME.put("MODULE_A", "企业信息");
		MAP_AD_NAME.put("MODULE_D", "产业服务");
		MAP_AD_NAME.put("MODULE_E", "支柱产业");
		MAP_AD_NAME.put("MODULE_F", "产业园区");
		MAP_AD_NAME.put("MODULE_G", "技术创新");
		MAP_AD_NAME.put("MODULE_H", "创新创业");
		MAP_AD_NAME.put("MODULE_I", "产业政策");
		MAP_AD_NAME.put("MODULE_J", "对接服务");
		//
		MAP_AD_NAME.put("MODULE_O", "创新示范企业推广图");
		MAP_AD_NAME.put("MODULE_P", "技术创新成果推广图");
		MAP_AD_NAME.put("MODULE_Q", "技术创新平台推广图");
		MAP_AD_NAME.put("MODULE_H", "创新创业平台推广图");
		MAP_AD_NAME.put("MODULE_M", "产业金融环境推广图");
		MAP_AD_NAME.put("MODULE_N", "创新创业成果推广图");
		//
		MAP_AD_NAME.put("FILE_AD1", "企业信息推广图");
		MAP_AD_NAME.put("FILE_AD2", "新闻详情推广图");
		MAP_AD_NAME.put("FILE_AD3", "新闻列表推广图");
		MAP_AD_NAME.put("FILE_AD4", "产业服务推广图");
		MAP_AD_NAME.put("FILE_AD5", "支柱产业推广图");
		MAP_AD_NAME.put("FILE_AD6", "产业园区推广图");
		MAP_AD_NAME.put("FILE_AD7", "园区项目列表推广图");
		//MAP_AD_NAME.put("FILE__AD8", "创新创业推广图");
		//MAP_AD_NAME.put("FILE__AD9", "技术创新推广图");
		MAP_AD_NAME.put("FILE__AD10", "对接服务推广图");
		MAP_AD_NAME.put("FILE__AD11", "对接服务横幅推广图");
		//
		DOWNLOAD_TYPE.put("FILE_D", "培训课件");
		DOWNLOAD_TYPE.put("FILE_E", "研究报告");
		DOWNLOAD_TYPE.put("FILE_F", "管理案例");
		DOWNLOAD_TYPE.put("FILE_G", "技术创新案例");
		DOWNLOAD_TYPE.put("FILE_I", "其他");
		
	}
	
	

	/**
	 * 从指定Map中获取Value不用考虑Map是否为空，Key是否在Map中存在
	 * @param map
	 * @param key
	 * @return
	 */
	public static final String getValueFromMap(Map map,String key){
		String value = "";
		if(map!=null){
			if(map.get(key)!=null){
				value = map.get(key).toString();
			}
		}
		return value;
	}
	
	
	
	/**
	 * 将图片按照等比压缩DPI
	 * @param formPath 原文件路径        (路径包含文件名称以及后缀)
	 * @param toPath 压缩后文件路径         (路径包含文件名称以及后缀)
	 * @return
	 */
	public static void ImageCompree(String formPath,String toPath) throws Exception{

		File infile = new File(formPath);
		File outfile = new File(toPath);
		ImageReader reader = ImageIO.getImageReadersByFormatName("jpeg").next();
		reader.setInput(new FileImageInputStream(infile), true, false);
		IIOMetadata data = reader.getImageMetadata(0);
		BufferedImage image = reader.read(0);
		int w = image.getWidth(), h = -1;
		Image rescaled = image.getScaledInstance(w, h,Image.SCALE_AREA_AVERAGING);
		BufferedImage output = toBufferedImage(rescaled,BufferedImage.TYPE_INT_RGB);
		Element tree = (Element) data.getAsTree("javax_imageio_jpeg_image_1.0");
		FileOutputStream fos = new FileOutputStream(outfile);
		JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(fos);
		JPEGEncodeParam jpegEncodeParam = jpegEncoder.getDefaultJPEGEncodeParam(output);
		jpegEncodeParam.setDensityUnit(JPEGEncodeParam.DENSITY_UNIT_DOTS_INCH);
		jpegEncodeParam.setXDensity(300);
		jpegEncodeParam.setYDensity(300);
		jpegEncoder.encode(output, jpegEncodeParam);
		fos.close();
	}
	
	public static BufferedImage toBufferedImage(Image image, int type) {
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage result = new BufferedImage(w, h, type);
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return result;
    }
}
