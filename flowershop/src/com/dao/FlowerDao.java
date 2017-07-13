package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import util.Page;

import com.beans.CatelogInfo;
import com.beans.FlowerInfo;

public class FlowerDao {
	//查询所有种类
	public List<CatelogInfo> getAllCatalogs(){
		List<CatelogInfo> list=new ArrayList<CatelogInfo>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		CatelogInfo catalog=null;
		try {
			conn=DBUtil.getCon();
			String sql="select * from catalog";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				catalog=new CatelogInfo();
				catalog.setCatalogid(rs.getInt("catalogid"));
				catalog.setCatalogname(rs.getString("catalogname"));
				list.add(catalog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	} 
	
	
	//查询最新的鲜花
	public List<FlowerInfo> getNewFlowers(){
		List<FlowerInfo> list=new ArrayList<FlowerInfo>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		FlowerInfo flower=null;
		try {
			conn=DBUtil.getCon();
			String sql="select * from flower  order by flowerid desc limit 0,4";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				flower=new FlowerInfo();
				flower.setFlowerid(rs.getInt(1));
				flower.setFlowername(rs.getString(2));
				flower.setPrice(rs.getInt(3));
				flower.setPicture(rs.getString(4));
				flower.setCatalogid(rs.getInt(5));
				list.add(flower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	}

   //通过品种id号查询鲜花
	public List<FlowerInfo> getFlowersByCatalogId(int cid_, Page page) {
		List<FlowerInfo> list=new ArrayList<FlowerInfo>();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		FlowerInfo flower=null;
		try {
			conn=DBUtil.getCon();
			String sql="select * from flower where catalogid=? limit ?,?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid_);
			pst.setInt(2, page.getBeginRow());
			pst.setInt(3, page.getPageSize());
			rs=pst.executeQuery();
			while(rs.next()){
				flower=new FlowerInfo();
				flower.setFlowerid(rs.getInt(1));
				flower.setFlowername(rs.getString(2));
				flower.setPrice(rs.getInt(3));
				flower.setPicture(rs.getString(4));
				flower.setCatalogid(rs.getInt(5));
				list.add(flower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return list;
	}
	
	//通过条件查询鲜花
		public List<FlowerInfo> getFlowersByCondition(String condition, Page page) {
			List<FlowerInfo> list=new ArrayList<FlowerInfo>();
			Connection conn=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			FlowerInfo flower=null;
			try {
				conn=DBUtil.getCon();
				String sql="select * from flower where flowername like ? limit ?,?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, "%"+condition+"%");
				pst.setInt(2, page.getBeginRow());
				pst.setInt(3, page.getPageSize());
				rs=pst.executeQuery();
				while(rs.next()){
					flower=new FlowerInfo();
					flower.setFlowerid(rs.getInt(1));
					flower.setFlowername(rs.getString(2));
					flower.setPrice(rs.getInt(3));
					flower.setPicture(rs.getString(4));
					flower.setCatalogid(rs.getInt(5));
					list.add(flower);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
			return list;
		}
   //查询相同种类的鲜花记录条数
	public int getFlowerCountSameCatalogid(int cid_) {
		int result=0;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select count(*) from flower where catalogid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid_);
			rs=pst.executeQuery();
           while(rs.next()){
        	   result=rs.getInt(1);	   
           }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		
		
		return result;
	}
	
	//查询鲜花记录条数
		public int getFlowerCountByConditon(String condition) {
			int result=0;
			Connection conn=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			try {
				conn=DBUtil.getCon();
				String sql="select count(*) from flower where flowername like ?";
				pst=conn.prepareStatement(sql);
				pst.setString(1, "%"+condition+"%");
				rs=pst.executeQuery();
	           while(rs.next()){
	        	   result=rs.getInt(1);	   
	           }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				DBUtil.closeAll(conn, rs, pst);
			}
			
			
			return result;
		}

     //通过flowerid查询鲜花
	public FlowerInfo getFlowerByFlowerId(int flowerid) {
		FlowerInfo flower=null;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select * from flower where flowerid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, flowerid);
			rs=pst.executeQuery();
           while(rs.next()){
        	   flower=new FlowerInfo();	   
        	   flower.setFlowerid(rs.getInt("flowerid"));
        	   flower.setFlowername(rs.getString("flowername"));
        	   flower.setPicture(rs.getString("picture"));
        	   flower.setPrice(rs.getInt("price"));
        	   flower.setCatalogid(rs.getInt("catalogid"));
           }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return flower;
	}
	//通过catalogid查询鲜花种类名称
	public String getCatalogNameByCatalogId(int cid) {
		String catalogname="";
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getCon();
			String sql="select * from catalog where catalogid=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cid);
			rs=pst.executeQuery();
           while(rs.next()){
        	   catalogname=rs.getString("catalogname");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(conn, rs, pst);
		}
		return catalogname;
	} 
	
}
