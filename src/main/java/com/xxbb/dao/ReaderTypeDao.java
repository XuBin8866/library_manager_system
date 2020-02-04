package com.xxbb.dao;

import java.util.List;


import com.xxbb.actionform.ReaderTypeForm;

/**  
    * @Title: ReaderTypeDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月2日
    * @version V1.0  
    */

public interface ReaderTypeDao {
	public List<ReaderTypeForm> query(ReaderTypeForm rtf);//查询读者类型信息
	public int update(ReaderTypeForm rtf);//更新读者类型信息
	public int insert(ReaderTypeForm rtf);//添加读者类型信息
	public int delete(ReaderTypeForm rtf);//删除读者类型信息
}
