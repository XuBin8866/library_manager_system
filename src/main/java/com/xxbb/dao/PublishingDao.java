package com.xxbb.dao;
/**  
    * @Title: PublishDao.java
    * @Package com.xxbb.dao
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月3日
    * @version V1.0  
    */

import java.util.List;

import com.xxbb.actionform.PublishingForm;

public interface PublishingDao {
	public List<PublishingForm> query(PublishingForm pf);//查询出版社信息
}
