package com.xxbb.dao;

import java.util.List;

import com.xxbb.actionform.BookBorrowForm;

/**  
    * @Title: BookBorrowDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public interface BookBorrowDao {
	public List<BookBorrowForm> bookBorrowSort();//图书借阅排行
	public List<BookBorrowForm> query(BookBorrowForm bbf);//个人借阅查询
	public int insertBorrow(int readerId, int bookId,String operator);//添加借阅信息
	public int insertReturn(BookBorrowForm bbf);//归还书籍
	public int updateBorrow(BookBorrowForm bbf);//续借书籍
	public List<BookBorrowForm> queryForManager(String[] str);//图书借阅查询
}
