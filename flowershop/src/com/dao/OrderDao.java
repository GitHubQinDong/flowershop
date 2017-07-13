package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import com.beans.OrderInfo;


public class OrderDao {

	public int addOrder(OrderInfo order) {
		 Connection conn=null;
		 PreparedStatement pst=null;
		 int orderid=0;
		 try {
			 conn=DBUtil.getCon();
			 String sql1="insert into orders(orderdate,userid,totalprice) values(?,?,?) ";
			 pst=conn.prepareStatement(sql1,1);
             pst.setTimestamp(1, order.getOrderdate());
             pst.setInt(2, order.getUserid());
             pst.setFloat(3, order.getTotalprice());
			 conn.setAutoCommit(false);	//		打开事务
			 pst.executeUpdate();
			 ResultSet rs=pst.getGeneratedKeys();//获取产生的自增长的id
			 if(rs.next()){
				 orderid=rs.getInt(1) ;
			 }
			 for(int i=0;i<order.getOrderitems().size();i++){
			 String sql2="insert into orderitem(orderid,flowerid,quantity) values(?,?,?) ";
			 pst=conn.prepareStatement(sql2);
             pst.setInt(1, orderid);
             pst.setInt(2, order.getOrderitems().get(i).getFlower().getFlowerid());
             pst.setInt(3,order.getOrderitems().get(i).getQuantity() );
             pst.executeUpdate();
			 }
             conn.commit();//			事务提交
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();//事务回滚
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			DBUtil.closeAll(conn, null, pst);
		}
		return orderid;
	}
    //通过userid查询订单
	public  List<OrderInfo> selectOrdersByUserId(int userid) {
		List<OrderInfo> list=new ArrayList<OrderInfo>();
		OrderInfo order=null;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select * from orders where userid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, userid);
			rs=pst.executeQuery();
           while(rs.next()){
        	   order=new OrderInfo();	   
        	   order.setOrderdate(rs.getTimestamp("orderdate"));
        	   int orderid=rs.getInt("orderid");
        	   order.setOrderid(orderid);
        	   order.setTotalprice(rs.getFloat("totalprice"));
        	   order.setUserid(userid);
        	  
        	   list.add(order);
           }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	}


}
