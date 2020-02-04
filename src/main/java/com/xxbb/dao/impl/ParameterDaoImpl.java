package com.xxbb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.ParameterForm;
import com.xxbb.dao.ParameterDao;

/**  
    * @Title: ParameterDaoImpl.java
    * @Package com.xxbb.dao.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年2月2日
    * @version V1.0  
    */

public class ParameterDaoImpl extends BaseDaoImpl implements ParameterDao{
	/**
	 * 
	    * @Title: query  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @return
	 */
	@Override
	public List<ParameterForm> query() {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		ParameterForm p=null;
		List<ParameterForm> pfs=new ArrayList<ParameterForm>();
		
		sb.append("select * from tb_parameter");
		System.out.println("查询办证参数："+sb);
		
		try {
			rs=executeQuery(sb.toString());
			while(rs.next()) {
				p=new ParameterForm();
				p.setId(rs.getInt(1));
				p.setCost(rs.getInt(2));
				p.setValidity(rs.getInt(3));
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
	/**
	 * 
	    * @Title: update  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param pf
		* @return
	 */
	@Override
	public int update(ParameterForm pf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("update tb_parameter set cost='");
		sb.append(pf.getCost());
		sb.append("',validity='");
		sb.append(pf.getValidity());
		sb.append("' where id=");
		sb.append(pf.getId());
		
		System.out.println("办证参数修改："+sb);
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
}
