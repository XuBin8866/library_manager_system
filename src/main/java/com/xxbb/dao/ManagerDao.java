package com.xxbb.dao;

import java.util.List;

import com.xxbb.actionform.ManagerForm;

/**  
    * @Title: ManagerDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月28日
    * @version V1.0  
    */

public interface ManagerDao {
	public boolean checkManager(ManagerForm mgf);//登录验证
	public List<ManagerForm> query(ManagerForm m);//通过name查询
	public int insert(ManagerForm mgf);//添加管理员账号
	public int update(ManagerForm mgf);//修改管理员权限
	public int delete(ManagerForm mgf);//删除管理员
	public int updatePassword(ManagerForm mgf);//修改管理员密码
}
