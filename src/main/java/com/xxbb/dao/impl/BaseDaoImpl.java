package com.xxbb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xxbb.dao.BaseDao;
import com.xxbb.util.ConnectionManager;

/**
 * @Title: BaseDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月28日
 * @version V1.0
 */

public class BaseDaoImpl implements BaseDao {
	protected ConnectionManager cm;
	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	public BaseDaoImpl() {
		
		// 初始化数据库连接池
		cm = ConnectionManager.getInstance();
		
	}
	/**
	 * 
	    * @Title: close  
		* @Description: TODO(这里用一句话描述这个方法的作用)
	 */
	public void close() {
		// TODO Auto-generated method stub
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(System.err);
		}
	}
	/**
	 * 
	    * @Title: executeQuery  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param sql
		* @return rs
	 */
	@Override
	public ResultSet executeQuery(String sql) {
		// TODO Auto-generated method stub
		conn=cm.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return rs;
	}
	/**
	 * 
	    * @Title: executeUpdate  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param sql
		* @return result
	 */
	@Override
	public int executeUpdate(String sql) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			conn=cm.getConnection();
			ps=conn.prepareStatement(sql);
			result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
		return result;
	}

}
