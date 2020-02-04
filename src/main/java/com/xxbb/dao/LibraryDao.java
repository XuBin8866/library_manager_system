package com.xxbb.dao;
/**  
    * @Title: LibraryDao.java
    * @Package com.xxbb.dao.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月1日
    * @version V1.0  
    */

import java.util.List;

import com.xxbb.actionform.LibraryForm;

public interface LibraryDao {
	public List<LibraryForm> query(LibraryForm lf);//查询图书馆信息
	public int update(LibraryForm lf);//更新图书馆信息
}
