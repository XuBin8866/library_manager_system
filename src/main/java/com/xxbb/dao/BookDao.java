package com.xxbb.dao;

import java.util.List;

import com.xxbb.actionform.BookForm;

/**
 * @Title: BookDao.java
 * @Package com.xxbb.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月30日
 * @version V1.0
 */

public interface BookDao {
	public List<BookForm> existQuery(BookForm bf);

	public List<BookForm> query(String[] str);//用于系统查询功能

	public List<BookForm> query(BookForm bf); //用于图书管理功能
	//图书信息的增删改
	public int insert(BookForm bf);
	public int update(BookForm bf);
	public int delete(BookForm bf);
}
