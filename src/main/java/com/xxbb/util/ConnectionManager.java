package com.xxbb.util;
/**  
    * @Title: ConnectionManager.java
    * @Package com.xxbb.test
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月26日
    * @version V1.0  
    */

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ConnectionManager {
	/**
	 * 使用单例模式创建数据库连接池
	 */
	private static ConnectionManager instance;
	private static ComboPooledDataSource dataSource;
	
	private ConnectionManager() {
		dataSource=new ComboPooledDataSource();
		//防止通过反射进行实例化而破坏单例
		if(instance!=null) {
			throw new RuntimeException("Object has been instanced!!!");
		}
	}
	public static ConnectionManager getInstance() {
		if(instance==null) {
			synchronized (ConnectionManager.class) {
				try {
					instance=new ConnectionManager();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}	
		}
		return instance;
	}
	public synchronized final Connection getConnection() {
		Connection conn=null;
		try {
			conn=dataSource.getConnection();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
}
