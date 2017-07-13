package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

import com.beans.UserDetailInfo;
import com.beans.UserInfo;

public class UserDao {

	public int addUser(UserInfo user, UserDetailInfo detail) {
		int result=0;
		 Connection conn=null;
		 PreparedStatement pst=null;
		 int userid=0;
		 try {
			 conn=DBUtil.getCon();
			 String sql1="insert into user(username,password,role) values(?,?,?) ";
			 pst=conn.prepareStatement(sql1,1);
			 pst.setString(1, user.getUsername());
			 pst.setString(2, user.getPassword());
			 pst.setString(3, user.getRole());
			 conn.setAutoCommit(false);	//		打开事务
			 pst.executeUpdate();
			 ResultSet rs=pst.getGeneratedKeys();//获取产生的自增长的id
			 if(rs.next()){
				 userid=rs.getInt(1) ;
			 }
			 String sql2="insert into userdetail(userid,xb,truename,csrq,phone,email,address) values(?,?,?,?,?,?,?) ";
			 pst=conn.prepareStatement(sql2);
             pst.setInt(1, userid);
             pst.setInt(2, detail.getXb());
             pst.setString(3, detail.getTruename());
             pst.setString(4, detail.getCsrq());
             pst.setString(5, detail.getPhone());
             pst.setString(6, detail.getEmail());
             pst.setString(7, detail.getAddress());
             result= pst.executeUpdate();
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
		return result;
	}
  //用于注册时查询昵称是否可用
	public UserInfo checkUser(String uname) {
		UserInfo user=null;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select *  from user where username=?";
			pst=conn.prepareStatement(sql);
            pst.setString(1, uname);
			rs=pst.executeQuery();
           while(rs.next()){
        	   user=new UserInfo();
        	   user.setUsername(rs.getString("username"));
        	   user.setUserid(rs.getInt("userid"));
        	   user.setRole(rs.getString("role"));
        	   user.setPassword(rs.getString("password"));
        	   
           }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return user;
		
	}
	//用户登录查询
	public UserInfo getUser(UserInfo user) {
		UserInfo user1=null;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select *  from user where username=?  and password=? and  role='customer' ";
			pst=conn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
             pst.setString(2, user.getPassword());
			rs=pst.executeQuery();
           while(rs.next()){
        	   user1=new UserInfo();
        	   user1.setUsername(rs.getString("username"));
        	   user1.setUserid(rs.getInt("userid"));
        	   user1.setRole(rs.getString("role"));
        	   user1.setPassword(rs.getString("password"));
        	   
           }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return user1;
	}
	//修改密码
	public int updatePsw(UserInfo user) {
		int result=0;
		 Connection conn=null;
		 PreparedStatement pst=null;
		 try {
			 conn=DBUtil.getCon();
			 String sql1="update user set password=? where userid=? ";
			 pst=conn.prepareStatement(sql1,1);
			 pst.setString(1, user.getPassword());
			 pst.setInt(2, user.getUserid());
			 result= pst.executeUpdate();
		 }catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, null, pst);
			}
		return result;
	}
	//通过用户id查询用户详细信息
	public UserDetailInfo getUserDetailByUserId(int userid) {
		UserDetailInfo detail=null;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select *  from userdetail where userid=?  ";
			pst=conn.prepareStatement(sql);
            pst.setInt(1, userid);
			rs=pst.executeQuery();
           while(rs.next()){
        	   detail=new UserDetailInfo();
        	   detail.setUserid(userid);
        	   detail.setTruename(rs.getString("truename"));
        	   detail.setXb(rs.getInt("xb"));
        	   detail.setCsrq(rs.getString("csrq"));
        	   detail.setPhone(rs.getString("phone"));
        	   detail.setEmail(rs.getString("email"));
        	   detail.setAddress(rs.getString("address"));
           }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return detail;
	}
	//保存用户详细信息
	public int saveDetail(UserDetailInfo detail) {
		int result=0;
		 Connection conn=null;
		 PreparedStatement pst=null;
		 try {
			 conn=DBUtil.getCon();
			 String sql1="update userdetail set truename=?,xb=?,csrq=?,phone=?,email=?,address=? where userid=? ";
			 pst=conn.prepareStatement(sql1,1);
			 pst.setString(1, detail.getTruename());
			 pst.setInt(2, detail.getXb());
			 pst.setString(3, detail.getCsrq());
			 pst.setString(4, detail.getPhone());
			 pst.setString(5, detail.getEmail());
			 pst.setString(6, detail.getAddress());
			 pst.setInt(7, detail.getUserid());
			 result= pst.executeUpdate();
		 }catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, null, pst);
			}
		return result;
	}
	

}
