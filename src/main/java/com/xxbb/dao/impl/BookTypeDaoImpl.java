package com.xxbb.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.BookTypeForm;
import com.xxbb.dao.BookTypeDao;

/**
 * @Title: BookTypeDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年2月3日
 * @version V1.0
 */

public class BookTypeDaoImpl extends BaseDaoImpl implements BookTypeDao {
	/**
	 * 
	 * @Title: delete
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bcf
	 * @return
	 */
	@Override
	public int delete(BookTypeForm btf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("delete from tb_booktype where id=");
		sb.append(btf.getId());
		System.out.println("删除图书类型信息：" + sb.toString());
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
	public int insert(BookTypeForm btf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();

		// 查询是否已经存在
		sb.append("select * from tb_booktype where type_name='");
		sb.append(btf.getTypeName());
		sb.append("'");
		System.out.println("查询图书类型是否存在：" + sb);
		rs = executeQuery(sb.toString());
		try {
			if (rs.next()) {
				result = -1;
			} else {
				sb.setLength(0);
				sb.append("insert into tb_booktype(type_name,limited_time) values('");
				sb.append(btf.getTypeName());
				sb.append("',");
				sb.append(btf.getLimitedTime());
				sb.append(")");
				System.out.println("添加图书类型信息：" + sb.toString());
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
	public List<BookTypeForm> query(BookTypeForm btf) {
		BookTypeForm b = null;
		List<BookTypeForm> btfs = new ArrayList<BookTypeForm>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from tb_booktype ");
		System.out.println("查询图书类型信息：" + sb.toString());
		try {
			rs = executeQuery(sb.toString());
			while (rs.next()) {
				b = new BookTypeForm();
				b.setId(rs.getInt(1));
				b.setTypeName(rs.getString(2));
				b.setLimitedTime(rs.getInt(3));
				btfs.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return btfs;
	}

	/**
	 * 
	 * @Title: update
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bcf
	 * @return
	 */
	@Override
	public int update(BookTypeForm btf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_booktype set limited_time=");
		sb.append(btf.getLimitedTime());
		sb.append(" where id=");
		sb.append(btf.getId());
		System.out.println("更新图书类型信息：" + sb);
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
