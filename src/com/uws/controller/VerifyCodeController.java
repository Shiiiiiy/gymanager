package com.uws.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uws.util.Constants;

/**
 * 验证码生成控制器
 * @author zhouchang
 *
 */
@Controller
public class VerifyCodeController {

	private static final int IMG_WIDTH = 90; //定义验证码图片的宽度 
	private static final int IMG_HEIGHT = 46; // 定义验证码图片的高度
	private static Random random = new Random(); //定义一个Random对象 
	private static Font font = new Font("宋体", Font.BOLD, 38); //定义字体对象
	
	@RequestMapping("/verify")
	public void generateVerifyCode(HttpServletRequest request, HttpServletResponse response){
		
		response.setContentType("images/jpeg");
		//创建一个图片缓冲流对象
		BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();//获取到画笔 
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);//填充矩形框 
		g.setColor(Color.BLACK);		// 设置画笔的颜色 
		g.drawRect(0, 0, IMG_WIDTH - 1, IMG_HEIGHT - 1);// 绘制一个矩形框 
		
		//绘制干拢线
		for (int i = 0; i < 50; i++){
			// 设置画笔的颜色(颜色是随机生成) 
			g.setColor(new Color(180 + random.nextInt(75), 
								 180 + random.nextInt(75),
								 180 + random.nextInt(75)));
			// 第一点
			int x1 = 2 + random.nextInt(IMG_WIDTH - 4);
			int y1 = 2 + random.nextInt(IMG_HEIGHT - 4);
			// 第二点
			int x2 = 2 + random.nextInt(IMG_WIDTH - 4);
			int y2 = 2 + random.nextInt(IMG_HEIGHT - 4);
			g.drawLine(x1, y1, x2, y2);
		}
		
		/** 绘制验证码(随机生成四个验证码) */
		g.setFont(font); // 设置字体
		String code = ""; // 保存最终生成的验证码
		for (int i = 0; i < 4; i++){
			String temp = generatorVerify();
			code += temp;

			g.setColor(new Color(random.nextInt(20), 
								 random.nextInt(40),
								 random.nextInt(20)));
			
			int offsetLeft = transferFrom(g);
			
			g.drawString(temp, 18 * i + offsetLeft, 35);
		}
		request.getSession().setAttribute(Constants.VERIFY_CODE, code);
		g.dispose();// 消毁画笔 
		// 输出 
		try {
			ImageIO.write(image, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 画笔位置倾斜方法
	 * @param g
	 * @return
	 */
	private int transferFrom(Graphics g) {
		Graphics2D gr = (Graphics2D)g;
		AffineTransform tr =  gr.getTransform();
		// 随机生成倾斜率
		double shx = Math.random();
		// 保证倾斜率在(0.25-0.55)之间
		if (shx < 0.25) shx = 0.25;
		if (shx > 0.55) shx = 0.55;
		// 随机向右是左倾斜
		int temp = random.nextInt(2);
		int offsetLeft = 2;
		if (temp == 0){
			shx = 0 - shx;
			offsetLeft = 10;
		}
		tr.setToShear(shx, 0);
		gr.setTransform(tr);
		return offsetLeft;
	}
	
	/**
	 * 随机生成一个验证码(大写字母、小写字母、数字、汉字)
	 * @return
	 */
	private String generatorVerify(){
		/** 随机生成0-3之间的数字 */
		int witch = Constants.VERIFY_CODE_TYPE;
		switch (witch){
			case 0: // 生成大写字母(A-Z|65-90)
				long temp = Math.round(Math.random() * 25 + 65);
				return String.valueOf((char)temp);
			case 1: // 生成小写字母(a-z|97-122)
				temp = Math.round(Math.random() * 25 + 97);
				return String.valueOf((char)temp);
			case 2: // 生成数字(0-9)
				return String.valueOf(Math.round(Math.random() * 9));
			default: // 生成汉字(0x4E00-0x9FBF)
				temp = Math.round(Math.random() * 500 + 0x4E00);
				return String.valueOf((char)temp);
		}
	}
}
