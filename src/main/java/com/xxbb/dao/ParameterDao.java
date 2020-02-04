package com.xxbb.dao;
/**  
    * @Title: ParameterDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月2日
    * @version V1.0  
    */

import java.util.List;

import com.xxbb.actionform.ParameterForm;

public interface ParameterDao {
	public List<ParameterForm> query();//查询办证参数
	public int update(ParameterForm pf);//修改办证信息
}
