package com.xxbb.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.xxbb.actionform.BookBorrowForm;
import com.xxbb.dao.BookBorrowDao;
import com.xxbb.util.TimeUtil;

/**
 * @Title: BookBorrwoDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月28日
 * @version V1.0
 */

public class BookBorrowDaoImpl extends BaseDaoImpl implements BookBorrowDao {
	
	/**
	 * 
	    * @Title: queryForManager  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param str
		* @return
	 */
	@Override
	public List<BookBorrowForm> queryForManager(String[] str) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		BookBorrowForm b=null;
		List<BookBorrowForm> bbfs=new ArrayList<BookBorrowForm>();
		//通过时间查询
		if("startDate".equals(str[0])) {
			sb.append("SELECT b.book_name,bb.borrow_time,bb.return_time,p.publish_name,bc.name,b.price,b.id as book_id,bb.id,r.barcode as reader_barcode,b.barcode as book_barcaode,r.name as reader_name,bb.if_back ");
			sb.append("FROM tb_bookborrow bb ");
			sb.append("left join tb_bookinfo b on bb.book_id=b.id ");
			sb.append("join tb_publishing p on b.ISBN=p.isbn ");
			sb.append("join tb_bookcase bc on b.bookcase=bc.id  ");
			sb.append("join tb_reader r on bb.reader_id=r.id ");
			sb.append("where borrow_time between '");
			sb.append(str[1]);
			sb.append("' and '");
			sb.append(str[2]);
			sb.append("' order by bb.if_back");
			System.out.println("通过时间查询借阅信息："+sb.toString());
		}
		//通过其他条件查询
		else {
			//通过读者信息
			if("readername".equals(str[0])||"readerbarcode".equals(str[0])) {
				sb.append("SELECT b.book_name,bb.borrow_time,bb.return_time,p.publish_name,bc.name,b.price,b.id as book_id,bb.id,r.barcode as reader_barcode,b.barcode as book_barcaode,r.name as reader_name,bb.if_back ");
				sb.append("FROM tb_reader r ");
				sb.append("left join tb_bookborrow bb on r.id=bb.reader_id ");
				sb.append("join tb_bookinfo b on bb.book_id=b.id join tb_publishing p on b.ISBN=p.isbn join tb_bookcase bc on b.bookcase=bc.id ");
				sb.append("where ");
				if("readername".equals(str[0])) {
					sb.append("r.name='");
				}else if("readerbarcode".equals(str[0])) {
					sb.append("r.barcode='");
				}
				sb.append(str[1]);
				sb.append("' order by bb.if_back");
			}
			//通过图书信息
			else if("barcode".equals(str[0])||"bookname".equals(str[0])||"if_back".equals(str[0])) {
				sb.append("SELECT b.book_name,bb.borrow_time,bb.return_time,p.publish_name,bc.name,b.price,b.id as book_id,bb.id,r.barcode as reader_barcode,b.barcode as book_barcaode,r.name as reader_name,bb.if_back ");
				sb.append("FROM tb_bookinfo b ");
				sb.append("left join tb_bookborrow bb on b.id=bb.book_id ");
				sb.append("join tb_reader r on r.id=bb.reader_id join tb_publishing p on b.ISBN=p.isbn join tb_bookcase bc on b.bookcase=bc.id ");
				sb.append("where ");
				if("barcode".equals(str[0])) {
					sb.append("b.barcode='");
				}else if("bookname".equals(str[0])) {
					sb.append("b.book_name='");
				}else if("if_back".equals(str[0])) {
					sb.append("bb.if_back='");
				}
				
				sb.append(str[1]);
				sb.append("' order by bb.if_back");
			}
			System.out.println("通过条件查询借阅信息："+sb.toString());
		}
		try {
			rs = executeQuery(sb.toString());
			while (rs.next()) {
				b = new BookBorrowForm();
				b.setBookName(rs.getString(1));
				b.setBorrowTime(rs.getString(2));
				b.setReturnTime(rs.getString(3));
				b.setPublishName(rs.getString(4));
				b.setBookcaseName(rs.getString(5));
				b.setPrice(rs.getDouble(6));
				b.setBookId(rs.getInt(7));
				b.setId(rs.getInt(8));
				b.setReaderBarcode(rs.getString(9));
				b.setBookBarcode(rs.getString(10));
				b.setReaderName(rs.getString(11));
				b.setIfBack(rs.getInt(12));
				bbfs.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return bbfs;
	}
	/**
	 * 
	 * @Title: updateBorrow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	@Override
	public int updateBorrow(BookBorrowForm bbf) {
		// TODO Auto-generated method stub
		int result = 0;
		//获取续借后时间
		String bbfDate=bbf.getReturnTime();
		String date=TimeUtil.getLaterDate(bbfDate, 30);
		//构建语句
		StringBuffer sb = new StringBuffer();
		sb.append("update tb_bookborrow set return_time='");
		sb.append(date);
		sb.append("' where id=");
		sb.append(bbf.getId());
		System.out.println("图书续借操作："+sb);
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
	 * @Title: bookBorrowSort
	 * @Description: TODO 图书借阅排行
	 * @return
	 */
	@Override
	public List<BookBorrowForm> bookBorrowSort() {
		// TODO Auto-generated method stub
		// 构建查询语句
		StringBuffer sb = new StringBuffer();
		sb.append("select * from ");
		sb.append("(SELECT book_id,count(book_id) as degree FROM tb_bookborrow group by book_id) as borr ");
		sb.append("join (select b.*,c.name as bookcaseName,p.publish_name,t.type_name from tb_bookinfo b ");
		sb.append(" left join tb_bookcase c ");
		sb.append(" on b.bookcase=c.id ");
		sb.append("join tb_publishing p on b.isbn=p.isbn ");
		sb.append("join tb_booktype t on b.type_id=t.id ");
		sb.append("where b.if_delete=0) as book ");
		sb.append("on borr.book_id=book.id ");
		sb.append("order by borr.degree desc limit 10");
		System.out.println("图书馆借阅排行:" + sb.toString());
		List<BookBorrowForm> bbfs = new ArrayList<BookBorrowForm>();
		BookBorrowForm bbf = null;
		try {
			rs = executeQuery(sb.toString());
			while (rs.next()) {
				bbf = new BookBorrowForm();
				bbf.setId(rs.getInt(1));
				bbf.setDegree(rs.getInt(2));
				bbf.setBookBarcode(rs.getString(3));
				bbf.setBookName(rs.getString(4));
				bbf.setAuthor(rs.getString(6));
				bbf.setPrice(Double.valueOf(rs.getString(9)));
				bbf.setBookcaseName(rs.getString(16));
				bbf.setPublishName(rs.getString(17));
				bbf.setBookType(rs.getString(18));
				bbfs.add(bbf);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			close();
		}

		return bbfs;
	}

	/**
	 * 
	 * @Title: query
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bbf
	 * @return
	 */
	@Override
	public List<BookBorrowForm> query(BookBorrowForm bbf) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		BookBorrowForm b = null;
		List<BookBorrowForm> bbfs = new ArrayList<BookBorrowForm>();
		// 判断传入对象是否为空，若为空则查询全部借阅信息
		if (bbf.getId() == null && bbf.getBookId() == null && bbf.getReaderId() == null) {
			sb.append("SELECT b.book_name,bb.borrow_time,bb.return_time,p.publish_name,bc.name,b.price,b.id as book_id,bb.id,r.barcode as reader_barcode,b.barcode as book_barcaode,r.name as reader_name,bb.if_back ");
			sb.append("FROM tb_bookborrow bb ");
			sb.append("left join tb_bookinfo b on bb.book_id=b.id ");
			sb.append("join tb_publishing p on b.ISBN=p.isbn ");
			sb.append("join tb_bookcase bc on b.bookcase=bc.id  ");
			sb.append("join tb_reader r on bb.reader_id=r.id order by bb.if_back");
			System.out.println("查询全部借阅信息: "+sb);
		} else if (bbf.getReaderId() != null) {
			sb.append(
					"SELECT b.book_name,bb.borrow_time,bb.return_time,p.publish_name,bc.name,b.price,b.id as book_id,bb.id,r.barcode as reader_barcode,b.barcode as book_barcaode,r.name as reader_name,bb.if_back ");
			sb.append("FROM tb_bookborrow bb ");
			sb.append("left join tb_bookinfo b on bb.book_id=b.id ");
			sb.append("join tb_publishing p on b.ISBN=p.isbn ");
			sb.append("join tb_bookcase bc on b.bookcase=bc.id  ");
			sb.append("join tb_reader r on bb.reader_id=r.id ");
			sb.append("where bb.if_back=0 and bb.reader_id=");
			sb.append(bbf.getReaderId());
			System.out.println("查询该读者的图书借阅信息：" + sb);
		}
		try {
			rs = executeQuery(sb.toString());
			while (rs.next()) {
				b = new BookBorrowForm();
				b.setBookName(rs.getString(1));
				b.setBorrowTime(rs.getString(2));
				b.setReturnTime(rs.getString(3));
				b.setPublishName(rs.getString(4));
				b.setBookcaseName(rs.getString(5));
				b.setPrice(rs.getDouble(6));
				b.setBookId(rs.getInt(7));
				b.setId(rs.getInt(8));
				b.setReaderBarcode(rs.getString(9));
				b.setBookBarcode(rs.getString(10));
				b.setReaderName(rs.getString(11));
				b.setIfBack(rs.getInt(12));
				bbfs.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return bbfs;
	}

	/**
	 * 
	 * @Title: insertBorrow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param readerName
	 * @param BookName
	 * @param operator
	 * @return
	 */
	@Override
	public int insertBorrow(int readerId, int bookId, String operator) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		int result = 0;
		int limitedTime = 0;
		// 获取系统日期
		Date d = new Date();
		java.sql.Date borrowDate = new java.sql.Date(d.getTime());
		// 查询可借天数
		sb.append("select bt.limited_time from tb_bookinfo b left join tb_booktype bt on bt.id=b.type_id where b.id=");
		sb.append(bookId);
		try {
			rs = executeQuery(sb.toString());
			if (rs.next()) {
				limitedTime = rs.getInt(1);
			} else {
				return 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		// 计算归还时间
		String returnDate = TimeUtil.getLaterDate(limitedTime);
		// 构建sql语句
		sb.setLength(0);
		sb.append("insert into tb_bookborrow(reader_id,book_id,borrow_time,return_time,operator) values(");
		sb.append(readerId);
		sb.append(",");
		sb.append(bookId);
		sb.append(",'");
		sb.append(borrowDate);
		sb.append("','");
		sb.append(returnDate);
		sb.append("','");
		sb.append(operator);
		sb.append("')");
		System.out.println("插入借阅信息：" + sb);
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
	 * @Title: insertReturn
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param bbf
	 * @return
	 */
	@Override
	public int insertReturn(BookBorrowForm bbf) {
		// TODO Auto-generated method stub
		// 获取当前时间作归还时间
		String date = TimeUtil.getDate();
		int result = 0;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into tb_bookreturn(reader_id,book_id,return_time,operator) values(");
		sb.append(bbf.getReaderId());
		sb.append(",");
		sb.append(bbf.getBookId());
		sb.append(",'");
		sb.append(date);
		sb.append("','");
		sb.append(bbf.getOperator());
		sb.append("')");
		StringBuffer sb2 = new StringBuffer();
		sb2.append("update tb_bookborrow set if_back=1 where id=");
		sb2.append(bbf.getId());
		System.out.println("图书归还操作：" + sb.toString() + "  " + sb2.toString());

		try {
			result = executeUpdate(sb.toString());
			result += executeUpdate(sb2.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
