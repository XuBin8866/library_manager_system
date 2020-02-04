package com.xxbb.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.BookcaseForm;
import com.xxbb.dao.BookcaseDao;

/**
 * @Title: BookcaseDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年2月2日
 * @version V1.0
 */

public class BookcaseDaoImpl extends BaseDaoImpl implements BookcaseDao {
	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bcf
	 * @return
	 */
	@Override
	public int delete(BookcaseForm bcf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("delete from tb_bookcase where id=");
		sb.append(bcf.getId());
		System.out.println("删除书架信息：" + sb.toString());
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
	 * @param bcf
	 * @return
	 */
	@Override
	public int insert(BookcaseForm bcf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();

		// 查询是否已经存在
		sb.append("select * from tb_bookcase where name='");
		sb.append(bcf.getName());
		sb.append("'");
		System.out.println("查询书架信息是否存在：" + sb);
		rs = executeQuery(sb.toString());
		try {
			if (rs.next()) {
				result = -1;
			} else {
				sb.setLength(0);
				sb.append("insert into tb_bookcase(name) values('");
				sb.append(bcf.getName());
				sb.append("')");
				System.out.println("添加书架信息：" + sb.toString());
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

	/**
	 * 
	 * @Title: query
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bcf
	 * @return
	 */
	@Override
	public List<BookcaseForm> query(BookcaseForm bcf) {
		BookcaseForm b = null;
		List<BookcaseForm> bcfs = new ArrayList<BookcaseForm>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from tb_bookcase");
		System.out.println("查询书架信息：" + sb.toString());
		try {
			rs = executeQuery(sb.toString());
			while (rs.next()) {
				b = new BookcaseForm();
				b.setId(rs.getInt(1));
				b.setName(rs.getString(2));
				bcfs.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return bcfs;
	}

	/**
	 * 
	 * @Title: update
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bcf
	 * @return
	 */
	@Override
	public int update(BookcaseForm bcf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();

		// 查询是否已经存在
		sb.append("select * from tb_bookcase where name='");
		sb.append(bcf.getName());
		sb.append("'");
		System.out.println("查询书架信息是否存在：" + sb.toString());
		rs = executeQuery(sb.toString());

		try {
			if (rs.next()) {
				result = -1;
			} else {
				sb.setLength(0);
				sb.append("update tb_bookcase set name='");
				sb.append(bcf.getName());
				sb.append("' where id=");
				sb.append(bcf.getId());
				System.out.println("更新书架信息：" + sb);
				result = executeUpdate(sb.toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
