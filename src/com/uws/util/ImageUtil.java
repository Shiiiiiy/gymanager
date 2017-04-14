package com.uws.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

public class ImageUtil {
	public static BufferedImage createAuthcodeImage(String str) {
		int width = 70;
		int height = 28;

		// 生成白底空图片
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, width, height);

		// 干扰线
		for (int i = 0; i < 10; i++) {
			int x1 = (int) (Math.random() * width);
			int y1 = (int) (Math.random() * height);
			int x2 = (int) (Math.random() * (width - 3));
			int y2 = (int) (Math.random() * (height - 3));

			Line2D line = new Line2D.Double(x1, y1, x2, y2);
			int r = (int) (Math.random() * 255);
			int g = (int) (Math.random() * 255);
			int b = (int) (Math.random() * 255);
			g2.setColor(new Color(r, g, b));
			g2.draw(line);
		}

		// 将字符拆散，设置随机大小和倾斜角度
		for (int i = 0; i < str.length(); i++) {
			String ch = String.valueOf(str.charAt(i));
			// 字体颜色
			int r = (int) (Math.random() * 128);
			int g = (int) (Math.random() * 128);
			int b = (int) (Math.random() * 128);
			// 字体大小
			int rnd = (int) (Math.random() * 5);
			int fontSize = 20;
			// 设置Y方向的偏移量
			int y = (int) (Math.random() * 6);
			if (y % 2 == 1) {
				y = y * (-1);
			}
			// 设置旋转角度
			AffineTransform trans = new AffineTransform();
			double angle = Math.random() * 0.2;
			rnd = (int) (Math.random() * 100);
			if (rnd % 2 == 1) {
				angle = angle * (-1);
			}
			trans.rotate(angle, 4 + i * 16, height / 2);
			trans.scale(1, 1);
			g2.setTransform(trans);
			g2.setFont(new Font("Arial", Font.BOLD, fontSize));
			g2.setColor(new Color(r, g, b));
			g2.drawString(ch, 5 + i * 16, 21 + y);
		}

		// 释放画笔对象
		g2.dispose();

		return bi;
	}
	
	public static void writeImage(BufferedImage bi, HttpServletResponse resp)
			throws IOException {
		try {
			ImageIO.write(bi, "JPEG", resp.getOutputStream());
		} catch (IOException e) {
		}
	}

}
