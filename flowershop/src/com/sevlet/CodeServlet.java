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
	    //����Content-Type��Ӧͷ
			response.setContentType("image/jpeg");
		//�������������������ҳ�治����
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache");
			response.setDateHeader("Expires",0);	
			//����ֽ���
		OutputStream out = response.getOutputStream();
		int width=80, height=20;
		//����ָ������BufferedImage����
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);	
		// ��ȡͼ��������
		Graphics g = image.getGraphics();
		
		//���������
		Random random = new Random();
		
		// �趨����ɫ
		g.setColor(getRandColor(200,250));
		//���
		g.fillRect(0, 0, width, height);
		//��������
		g.setFont(new Font("Times New Roman",Font.ITALIC,18));
		//���߿�
      g.setColor(new Color(0,0,0));
      g.drawRect(0,0,width-1,height-1);//������

   // �������100�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
	    g.setColor(getRandColor(10,200));
	    for (int i=0;i<10;i++)
	    {
	     int x = random.nextInt(width);
	     int y = random.nextInt(height);
	            int xl = random.nextInt(12);
	            int yl = random.nextInt(12);
	     g.drawLine(x,y,x+xl,y+yl);
	    }
			
		// ȡ�����������֤��(4λ����)
		String sRand = "";
		for (int i=0;i<4;i++){
		    String rand = String.valueOf(random.nextInt(10));
		    sRand += rand;
		    // ������֤��������ɫ
		    g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));
		    //������֤�뵽ͼ����
		    g.drawString(rand,20*i + 6,16);
		   
		}
		// ����֤�����SESSION
		 request.getSession().setAttribute("code", sRand);
		 // ͼ����Ч
		g.dispose();

		// ���ͼ��ҳ��
		ImageIO.write(image, "JPEG", out);
		
		
	}

	
	//������Χ��������ɫ  
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
