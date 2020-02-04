package com.xxbb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.BookForm;
import com.xxbb.dao.BookDao;

/**  
    * @Title: BookDaoImpl.java
    * @Package com.xxbb.dao.impl
    * @Description: TODO(用一句话描述该文件做什么)
    * @author 徐斌
    * @date 2020年1月30日
    * @version V1.0  
    */

public class BookDaoImpl extends BaseDaoImpl implements BookDao{
	
	
	/**
	 * 
	    * @Title: query  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param bf
		* @return
	 */
	@Override
	public List<BookForm> query(BookForm bf) {
		// TODO Auto-generated method stub
		List<BookForm> bfs=new ArrayList<BookForm>();
		StringBuffer sb=new StringBuffer();
		BookForm b=null;
		//查询全部
		if(bf.getId()==null&&bf.getBookBarcode()==null) {
			sb.append("SELECT b.*,p.publish_name,bt.type_name,bc.name from tb_bookinfo b left join tb_booktype bt  on bt.id=b.type_id join tb_bookcase bc on bc.id=b.bookcase join tb_publishing p on p.isbn=b.ISBN");
			System.out.println("查询全部书籍信息："+sb);
		}else if(bf.getId()!=null) {
			sb.append("SELECT b.*,p.publish_name,bt.type_name,bc.name from tb_bookinfo b left join tb_booktype bt  on bt.id=b.type_id join tb_bookcase bc on bc.id=b.bookcase join tb_publishing p on p.isbn=b.ISBN where b.id=");
			sb.append(bf.getId());
			System.out.println("查看书籍详细信息："+sb);
		}
		try {
			rs=executeQuery(sb.toString());
			while(rs.next()) {
				b=new BookForm();
				b.setId(rs.getInt(13));
				b.setBookBarcode(rs.getString(1));
				b.setBookName(rs.getString(2));
				b.setPublishName(rs.getString(14));
				b.setBookTypeName(rs.getString(15));
				b.setBookcaseName(rs.getString(16));
				
				b.setAuthor(rs.getString(4));
				b.setTranslator(rs.getString(5));
				b.setPrice(rs.getDouble(7));
				b.setPage(rs.getInt(8));
				b.setIntime(rs.getString(10));
				b.setOperator(rs.getString(11));
				bfs.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return bfs;
	}
	
	/**
	 * 
	    * @Title: query  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param str
		* @return
	 */
	@Override
	public List<BookForm> query(String[] str) {
		// TODO Auto-generated method stub
		List<BookForm> bfs=new ArrayList<BookForm>();
		BookForm b=null;
		StringBuffer sb=new StringBuffer();
		if("publish_name".equals(str[0])) {
			sb.append("SELECT b.*,p.publish_name,bt.type_name,bc.name from tb_publishing p left join tb_bookinfo b on b.ISBN=p.isbn join tb_booktype bt on bt.id=b.type_id join tb_bookcase bc on bc.id=b.bookcase where p.publish_name='");
			sb.append(str[1]);
			sb.append("'");
			System.out.println("通过出版社信息查询："+sb);
		}else if("bookcase_name".equals(str[0])) {
			sb.append("SELECT b.*,p.publish_name,bt.type_name,bc.name from tb_bookcase bc left join tb_bookinfo b on bc.id=b.bookcase join tb_booktype bt on bt.id=b.type_id join tb_publishing p on p.isbn=b.ISBN where bc.name='");
			sb.append(str[1]);
			sb.append("'");
			System.out.println("通过书架信息查询："+sb);
		}else if("type_name".equals(str[0])){
			sb.append("SELECT b.*,p.publish_name,bt.type_name,bc.name from tb_booktype bt left join tb_bookinfo b on bt.id=b.type_id join tb_bookcase bc on bc.id=b.bookcase join tb_publishing p on p.isbn=b.ISBN where bt.type_name='");
			sb.append(str[1]);
			sb.append("'");
		}else {
			sb.append("SELECT b.*,p.publish_name,bt.type_name,bc.name from tb_bookinfo b left join tb_booktype bt  on bt.id=b.type_id join tb_bookcase bc on bc.id=b.bookcase join tb_publishing p on p.isbn=b.ISBN where b.");
			sb.append(str[0]);
			sb.append("='");
			sb.append(str[1]);
			sb.append("'");
			System.out.println("通过其他条件查询查询："+sb);
		}
		try {
			rs=executeQuery(sb.toString());
			while(rs.next()) {
				b=new BookForm();
				b.setId(rs.getInt(13));
				b.setBookBarcode(rs.getString(1));
				b.setBookName(rs.getString(2));
				b.setPublishName(rs.getString(14));
				b.setBookTypeName(rs.getString(15));
				b.setBookcaseName(rs.getString(16));
				bfs.add(b);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return bfs;
	}
	/**
	 * 
	    * @Title: query  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param bf
		* @return
	 */
	@Override
	public List<BookForm> existQuery(BookForm bf) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		BookForm b=null;
		List<BookForm> bfs=new ArrayList<BookForm>();
		if(bf.getBookBarcode()!=null) {
			sb.append("select * from tb_bookinfo where barcode=");
			sb.append(bf.getBookBarcode());
		}else if(bf.getBookName()!=null) {
			sb.append("select * from tb_bookinfo where book_name=");
			sb.append(bf.getBookName());
		}
		System.out.println(sb);
		try {
			rs=executeQuery(sb.toString());
			while(rs.next()) {
				b=new BookForm();
				b.setBookBarcode(rs.getString(1));
				b.setId(rs.getInt("id"));
				bfs.add(b);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		return bfs;
	}
	/**
	 * 
	    * @Title: delete  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param bf
		* @return
	 */
	@Override
	public int delete(BookForm bf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("delete from tb_bookinfo where id=");
		sb.append(bf.getId());
		System.out.println("删除图书信息："+sb);
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
	    * @Title: insert  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param bf
		* @return
	 */
	@Override
	public int insert(BookForm bf) {
		// TODO Auto-generated method stub
		boolean flag=false;
		int result=0;
		StringBuffer sb=new StringBuffer();
		//查询条形码是否存在
		sb.append("select *from tb_bookinfo where barcode='");
		sb.append(bf.getBookBarcode());
		sb.append("'");
		System.out.println("查询图书条形码是否存在："+sb);
		try {
			rs=executeQuery(sb.toString());
			flag=rs.next();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(flag) {
			System.out.println("该条形码已经存在！！！");
			result=-1;
			
		}else {
			System.out.println("该条形码不存在，允许添加！！！");
			sb.setLength(0);
			sb.append("insert into tb_bookinfo(barcode,book_name,type_id,author,translator,isbn,price,page,bookcase,intime,operator) values");
			sb.append("('");
			sb.append(bf.getBookBarcode());
			sb.append("','");
			sb.append(bf.getBookName());
			sb.append("','");
			sb.append(bf.getBookTypeId());
			sb.append("','");
			sb.append(bf.getAuthor());
			sb.append("','");
			sb.append(bf.getTranslator());
			sb.append("','");
			sb.append(bf.getIsbn());
			sb.append("',");
			sb.append(bf.getPrice());
			sb.append(",'");
			sb.append(bf.getPage());
			sb.append("','");
			sb.append(bf.getBookcaseId());
			sb.append("','");
			sb.append(bf.getIntime());
			sb.append("','");
			sb.append(bf.getOperator());
			sb.append("')");
			System.out.println("添加图书信息："+sb);
			try {
				result=executeUpdate(sb.toString());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				close();
			}
		}
		return result;
	}@Override
	public int update(BookForm bf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("update tb_bookinfo set ");
		sb.append("book_name='");
		sb.append(bf.getBookName());
		sb.append("',type_id=");
		sb.append(bf.getBookTypeId());
		sb.append(",author='");
		sb.append(bf.getAuthor());
		sb.append("',translator='");
		sb.append(bf.getTranslator());
		sb.append("',isbn='");
		sb.append(bf.getIsbn());
		sb.append("',price=");
		sb.append(bf.getPrice());
		sb.append(",page='");
		sb.append(bf.getPage());
		sb.append("',bookcase='");
		sb.append(bf.getBookcaseId());
		sb.append("',operator='");
		sb.append(bf.getOperator());
		sb.append("' where id=");
		sb.append(bf.getId());
		System.out.println("修改图书信息："+sb);
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
