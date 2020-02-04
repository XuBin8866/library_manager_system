 package com.xxbb.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.xxbb.util.ConnectionManager;

/**  
    * @Title: ConnectionPoolTest.java
    * @Package com.xxbb.test
    * @Description: TODO 测试数据连接池
    * @author 徐斌
    * @date 2020年1月25日
    * @version V1.0  
    */

public class ConnectionPoolTest {
	public static void main(String[] args) {
		ConnectionManager cm=ConnectionManager.getInstance();
		System.out.println("数据库连接池"+cm);
		for(int i=0;i<11;i++) {
			System.out.print("这是第"+(i+1)+"次   ");
			Connection conn=cm.getConnection();
			
			System.out.println(conn);
			if(i==5) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
