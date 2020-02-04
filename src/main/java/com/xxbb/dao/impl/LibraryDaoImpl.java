package com.xxbb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.LibraryForm;
import com.xxbb.dao.LibraryDao;

/**  
    * @Title: LibraryDaoImpl.java
    * @Package com.xxbb.dao.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月1日
    * @version V1.0  
    */

public class LibraryDaoImpl extends BaseDaoImpl implements LibraryDao{
	/**
	 * 
	    * @Title: update  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param lf
		* @return
	 */
	@Override
	public int update(LibraryForm lf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("update tb_library set ");
		sb.append("name='");
		sb.append(lf.getLibraryName());
		sb.append("',curator='");
		sb.append(lf.getCurator());
		sb.append("',telephone='");
		sb.append(lf.getTelephone());
		sb.append("',email='");
		sb.append(lf.getEmail());
		sb.append("',introduce='");
		sb.append(lf.getIntroduce());
		sb.append("',url='");
		sb.append(lf.getUrl());
		sb.append("',create_date='");
		sb.append(lf.getCreateDate());
		sb.append("',address='");
		sb.append(lf.getAddress());
		sb.append("'");
		sb.append(" where id=");
		sb.append(lf.getId());
		System.out.println("更新图书馆信息："+sb);
		try {
			result=executeUpdate(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return result;
	}
	/**
	 * 
	    * @Title: query  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param lf
		* @return
	 */
	@Override
	public List<LibraryForm> query(LibraryForm lf) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		LibraryForm l=null;
		List<LibraryForm> lfs=new ArrayList<LibraryForm>();
		//无条件查询
		if(lf.getId()==null&&lf.getLibraryName()==null) {
			sb.append("select * from tb_library");
		}
		System.out.println("图书馆信息查询:"+sb);
		try {
			rs=executeQuery(sb.toString());
			while(rs.next()) {
				l=new LibraryForm();
				l.setId(rs.getInt(1));
				l.setLibraryName(rs.getString(2));
				l.setCurator(rs.getString(3));
				l.setTelephone(rs.getString(4));
				l.setEmail(rs.getString(5));
				l.setIntroduce(rs.getString(6));
				l.setUrl(rs.getString(7));
				l.setCreateDate(rs.getString(8));
				l.setAddress(rs.getString(9));
				lfs.add(l);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		
		return lfs;
	}
}
