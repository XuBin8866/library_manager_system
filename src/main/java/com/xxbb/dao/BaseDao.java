package com.xxbb.dao;
/**  
    * @Title: BaseDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

import java.sql.ResultSet;

public interface BaseDao {
	public ResultSet executeQuery(String sql);//基础查询
	public int executeUpdate(String sql);//基础更新数据
	public void close();//关闭连接
	
}
