package com.xxbb.dao;
/**  
    * @Title: BookcaseDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月2日
    * @version V1.0  
    */

import java.util.List;

import com.xxbb.actionform.BookcaseForm;

public interface BookcaseDao {
	public List<BookcaseForm> query(BookcaseForm bcf);//查询书架信息
	public int update(BookcaseForm bcf);//更新书架信息
	public int insert(BookcaseForm bcf);//添加书架信息
	public int delete(BookcaseForm bcf);//删除书架信息
}
