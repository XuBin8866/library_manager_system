package com.xxbb.dao;
/**  
    * @Title: BookTypeDao.java
    * @Package com.xxbb.dao.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月3日
    * @version V1.0  
    */

import java.util.List;

import com.xxbb.actionform.BookTypeForm;


public interface BookTypeDao {
	public List<BookTypeForm> query(BookTypeForm btf);//查询图书类型信息
	public int update(BookTypeForm btf);//更新图书类型信息
	public int insert(BookTypeForm btf);//添加图书类型信息
	public int delete(BookTypeForm btf);//删除图书类型信息
}
