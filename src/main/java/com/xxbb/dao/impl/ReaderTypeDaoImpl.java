package com.xxbb.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.ReaderTypeForm;
import com.xxbb.dao.ReaderTypeDao;

/**
 * @Title: ReaderTypeDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年2月2日
 * @version V1.0
 */

public class ReaderTypeDaoImpl extends BaseDaoImpl implements ReaderTypeDao {
	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param rtf
	 * @return
	 */
	@Override
	public int delete(ReaderTypeForm rtf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("delete from tb_readertype where id=");
		sb.append(rtf.getId());
		try {
			result = executeUpdate(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	/**
	 * 
	 * @Title: insert
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param rtf
	 * @return
	 */
	@Override
	public int insert(ReaderTypeForm rtf) {
		int result = 0;
		StringBuffer sb = new StringBuffer();

		// 查询是否已经存在
		sb.append("select * from tb_readertype where name='");
		sb.append(rtf.getName());
		sb.append("'");
		System.out.println("查询读者类型信息是否存在：" + sb);
		rs = executeQuery(sb.toString());
		try {
			if (rs.next()) {
				result = -1;
				System.out.println("读者类型信息存在");
			} else {
				sb.setLength(0);
				sb.append("insert into tb_readertype(name,allow_borrow_amount) values('");
				sb.append(rtf.getName());
				sb.append("',");
				sb.append(rtf.getAllowBorrowAmount());
				sb.append(")");
				System.out.println("添加读者类型信息：" + sb.toString());
				result = executeUpdate(sb.toString());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	@Override
	public List<ReaderTypeForm> query(ReaderTypeForm rtf) {
		ReaderTypeForm r = null;
		List<ReaderTypeForm> rtfs = new ArrayList<ReaderTypeForm>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from tb_readertype");
		System.out.println("查询读者类型信息：" + sb.toString());
		try {
			rs = executeQuery(sb.toString());
			while (rs.next()) {
				r = new ReaderTypeForm();
				r.setId(rs.getInt(1));
				r.setName(rs.getString(2));
				r.setAllowBorrowAmount(rs.getInt(3));
				rtfs.add(r);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return rtfs;
	}

	/**
	 * 
	 * @Title: update
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param rtf
	 * @return
	 */
	@Override
	public int update(ReaderTypeForm rtf) {
		int result = 0;
		StringBuffer sb = new StringBuffer();

		sb.append("update tb_readertype set name='");
		sb.append(rtf.getName());
		sb.append("',allow_borrow_amount=");
		sb.append(rtf.getAllowBorrowAmount());
		sb.append(" where id=");
		sb.append(rtf.getId());
		System.out.println("更新读者类型信息：" + sb);
		try {

			result = executeUpdate(sb.toString());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
