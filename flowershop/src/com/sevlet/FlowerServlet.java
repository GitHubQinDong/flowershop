package com.sevlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Page;

import com.beans.FlowerInfo;
import com.dao.FlowerDao;

public class FlowerServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        //1.��ȡǰ̨�������� Ʒ�� id�ţ���ǰҳcurrentPage
		String cid= request.getParameter("cid");
		String condition=request.getParameter("searchcondition");
		//condition=condition!=null?new String(condition.getBytes("ISO-8859-1"),"utf-8"):null;
		boolean flag=(cid==null||cid.trim().isEmpty())?false:true;//��������ѯ�ı�־λ��trueΪ��������ѯ��false���ǰ��������������в�ѯ
		String currentPage= request.getParameter("currentPage");
        //����ת��
		int currentPage_=currentPage!=null?Integer.parseInt(currentPage):0;
		//2.ͨ��Ʒ�� id�Ų�ѯ������ָ������ҳ���ʻ�
		FlowerDao dao=new FlowerDao();
		//��ҳ����
		Page page=new Page();
		int rowCount=flag?dao.getFlowerCountSameCatalogid(Integer.parseInt(cid)):dao.getFlowerCountByConditon(condition);
		page.setPageSize(4);//��ֵҳ���С
		page.setRowCount(rowCount);//��ֵ��¼��
		//��page����ֵ֮ǰԼ����ǰҳ������ǰҳҳС��1����currentPage��ֵΪ1������ǰҳҳ������ҳ������currentPage��ֵΪpageCount
		if(currentPage_<1){currentPage_=1;}
		int pageCount=page.getPageCount();
		if(currentPage_>pageCount){currentPage_=pageCount;}
		page.setCurrentPage(currentPage_);//��ֵ��ǰҳ
		
		List<FlowerInfo> flist=flag?dao.getFlowersByCatalogId(Integer.parseInt(cid),page):dao.getFlowersByCondition(condition,page);
		
		//ͨ��catalogid��ѯ�ʻ���������
		 String catalogname= flag?dao.getCatalogNameByCatalogId(Integer.parseInt(cid)):null;
		 
		//3.��תת����ĳ��ҳ�棬���ҰѲ�ѯ������������ʾ����
		request.setAttribute("flowerlist", flist);
		request.setAttribute("catalogid", cid);
		request.setAttribute("searchcondition", condition);
		request.setAttribute("catalogname", catalogname);
		request.setAttribute("page",  page);
		request.getRequestDispatcher("customer/flower.jsp").forward(request, response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
