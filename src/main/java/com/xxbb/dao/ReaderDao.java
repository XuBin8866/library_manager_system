package com.xxbb.dao;

import java.util.List;

import com.xxbb.actionform.ReaderForm;

/**  
    * @Title: ReaderDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月30日
    * @version V1.0  
    */

public interface ReaderDao {
	public List<ReaderForm> query(ReaderForm rf);//查询读者信息
	public int update(ReaderForm rf);//修改读者信息
	public int insert(ReaderForm rf);//添加读者信息
	public int delete(ReaderForm rf);//删除读者信息
}
