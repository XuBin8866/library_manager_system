package com.xxbb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.PublishingForm;
import com.xxbb.dao.PublishingDao;

/**  
    * @Title: PublishingDaoImpl.java
    * @Package com.xxbb.dao.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月3日
    * @version V1.0  
    */

public class PublishingDaoImpl extends BaseDaoImpl implements PublishingDao{
	/**
	 * 
	    * @Title: query  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param pf
		* @return
	 */
	@Override
	public List<PublishingForm> query(PublishingForm pf) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		PublishingForm p=null;
		List<PublishingForm> pfs=new ArrayList<PublishingForm>();
		sb.append("select * from tb_publishing ");
		if(pf.getIsbn()!=null) {
			sb.append("where isbn='");
			sb.append(pf.getIsbn());
			sb.append("'");
			System.out.println("通过isbn查询全部出版社信息："+sb);
		}else if(pf.getPublishName()!=null) {
			sb.append("where publish_name='");
			sb.append(pf.getPublishName());
			sb.append("'");
			System.out.println("通过名称查询出版社信息："+sb);
		}else {
			System.out.println("查询全部出版社信息："+sb);
		}
		try {
			rs=executeQuery(sb.toString());
			while(rs.next()) {
				p=new PublishingForm();
				p.setIsbn(rs.getString(1));
				p.setPublishName(rs.getString(2));
				pfs.add(p);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return pfs;
		
	}
}
