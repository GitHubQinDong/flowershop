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
        //1.获取前台传过来的 品种 id号，当前页currentPage
		String cid= request.getParameter("cid");
		String condition=request.getParameter("searchcondition");
		//condition=condition!=null?new String(condition.getBytes("ISO-8859-1"),"utf-8"):null;
		boolean flag=(cid==null||cid.trim().isEmpty())?false:true;//按照类别查询的标志位，true为按照类别查询，false则是按照搜索条件进行查询
		String currentPage= request.getParameter("currentPage");
        //类型转换
		int currentPage_=currentPage!=null?Integer.parseInt(currentPage):0;
		//2.通过品种 id号查询来所有指定（分页）鲜花
		FlowerDao dao=new FlowerDao();
		//分页对象
		Page page=new Page();
		int rowCount=flag?dao.getFlowerCountSameCatalogid(Integer.parseInt(cid)):dao.getFlowerCountByConditon(condition);
		page.setPageSize(4);//赋值页面大小
		page.setRowCount(rowCount);//赋值记录数
		//在page对象赋值之前约束当前页：若当前页页小于1，就currentPage赋值为1，若当前页页大于总页数，就currentPage赋值为pageCount
		if(currentPage_<1){currentPage_=1;}
		int pageCount=page.getPageCount();
		if(currentPage_>pageCount){currentPage_=pageCount;}
		page.setCurrentPage(currentPage_);//赋值当前页
		
		List<FlowerInfo> flist=flag?dao.getFlowersByCatalogId(Integer.parseInt(cid),page):dao.getFlowersByCondition(condition,page);
		
		//通过catalogid查询鲜花种类名称
		 String catalogname= flag?dao.getCatalogNameByCatalogId(Integer.parseInt(cid)):null;
		 
		//3.请转转发至某个页面，并且把查询出来的内容显示出来
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
