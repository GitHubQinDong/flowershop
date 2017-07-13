package com.sevlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    //设置Content-Type响应头
			response.setContentType("image/jpeg");
		//下面三条语句用于设置页面不缓存
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires",0);	
			//输出字节流
		OutputStream out = response.getOutputStream();
		int width=80, height=20;
		//建立指定宽、高BufferedImage对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);	
		// 获取图形上下文
		Graphics g = image.getGraphics();
		
		//生成随机类
		Random random = new Random();
		
		// 设定背景色
		g.setColor(getRandColor(200,250));
		//填充
		g.fillRect(0, 0, width, height);
		//设置字体
		g.setFont(new Font("Times New Roman",Font.ITALIC,18));
		//画边框
      g.setColor(new Color(0,0,0));
      g.drawRect(0,0,width-1,height-1);//画矩形

   // 随机产生100条干扰线，使图象中的认证码不易被其它程序探测到
	    g.setColor(getRandColor(10,200));
	    for (int i=0;i<10;i++)
	    {
	     int x = random.nextInt(width);
	     int y = random.nextInt(height);
	            int xl = random.nextInt(12);
	            int yl = random.nextInt(12);
	     g.drawLine(x,y,x+xl,y+yl);
	    }
			
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i=0;i<4;i++){
		    String rand = String.valueOf(random.nextInt(10));
		    sRand += rand;
		    // 设置认证码的随机颜色
		    g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
		    //绘制认证码到图象中
		    g.drawString(rand,20*i + 6,16);
		   
		}
		// 将认证码存入SESSION
		 request.getSession().setAttribute("code", sRand);
		 // 图象生效
		g.dispose();

		// 输出图象到页面
		ImageIO.write(image, "JPEG", out);
		
		
	}

	
	//给定范围获得随机颜色  
	 Color getRandColor(int fc,int bc){
		Random random = new Random();
		if(fc>255) fc=255;
		if(bc>255) bc=255;  
		if(fc<0) fc=0;
		if(bc<0) bc=255;                     
		int r=fc+random.nextInt(bc-fc);  
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
		}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	
	}

}
