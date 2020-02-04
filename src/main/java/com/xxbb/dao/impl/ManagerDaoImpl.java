package com.xxbb.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.ManagerForm;
import com.xxbb.dao.ManagerDao;

/**
 * @Title: ManagerDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月28日
 * @version V1.0
 */

public class ManagerDaoImpl extends BaseDaoImpl implements ManagerDao {
	/**
	 * 
	    * @Title: updatePassword  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param mgf
		* @return
	 */
	@Override
	public int updatePassword(ManagerForm mgf) {
		// TODO Auto-generated method stub
		int result = 0;
		StringBuffer sb=new StringBuffer();
		sb.append("update tb_manager set ");
		sb.append("password='");
		sb.append(mgf.getPwd());
		sb.append("' where name='");
		sb.append(mgf.getName());
		sb.append("'");
		System.out.println("更新密码："+sb);
		try {
			result=executeUpdate(sb.toString());
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
		* @param mgf
		* @return
	 */
	@Override
	public int insert(ManagerForm mgf) {
		// TODO Auto-generated method stub
		int target=0;
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM tb_manager WHERE name=");
		sb.append("'");
		sb.append(mgf.getName());
		sb.append("'");
		rs=executeQuery(sb.toString());
		try {
			if(rs.next()) {
				target=-1;//管理员账号已存在
			}else {
				sb.setLength(0);
				sb.append("insert into tb_manager(name,password) values('");
				sb.append(mgf.getName());
				sb.append("'");
				sb.append(",");
				sb.append("'");
				sb.append(mgf.getPwd());
				sb.append("')");
				System.out.println(sb.toString());
				target=executeUpdate(sb.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			close();
		}
		
		
		return target;
	}
	/**
	 * 
	 * @Title: query
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param str
	 * @return
	 */
	@Override
	public List<ManagerForm> query(ManagerForm m) {
		// TODO Auto-generated method stub
		ManagerForm mgf = m;
		StringBuffer sb=new StringBuffer();
		List<ManagerForm> mgfs = new ArrayList<ManagerForm>();
		if(mgf.getId()==null&&mgf.getName()==null) {
			// 当参数的值为null时查询全部数据
			sb.append("select m.*,p.system_set,p.reader_set,p.book_set,p.borrow_set,p.system_query ");
			sb.append("from tb_manager m left join tb_purview p ");
			sb.append("on m.id=p.id");
		} else if(mgf!=null&&mgf.getName()!=null){
			sb.append("select m.*,p.system_set,p.reader_set,p.book_set,p.borrow_set,p.system_query ");
			sb.append("from tb_manager m left join tb_purview p ");
			sb.append("on m.id=p.id ");
			sb.append("where m.name='");
			sb.append(mgf.getName());
			sb.append("'");
		}else if(mgf!=null&&mgf.getId()!=null) {
			sb.append("select m.*,p.system_set,p.reader_set,p.book_set,p.borrow_set,p.system_query ");
			sb.append("from tb_manager m left join tb_purview p ");
			sb.append("on m.id=p.id ");
			sb.append("where m.id='");
			sb.append(mgf.getId());
			sb.append("'");
		}
		System.out.println("管理员信息查询："+sb);
		rs=executeQuery(sb.toString());
		try {
			while(rs.next()) {
				mgf=new ManagerForm();
				mgf.setId(rs.getInt(1));
                mgf.setName(rs.getString(2));
                mgf.setPwd(rs.getString(3));
                mgf.setSystemSet(rs.getInt(4));
                mgf.setReaderSet(rs.getInt(5));
                mgf.setBookSet(rs.getInt(6));
                mgf.setBorrowSet(rs.getInt(7));
                mgf.setSystemQuery(rs.getInt(8));
                mgfs.add(mgf);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return mgfs;
	}
	/**
	 * 
	 * @Title: checkManager
	 * @Description: TODO 登录验证
	 * @param mgf
	 * @return
	 */
	@Override
	public boolean checkManager(ManagerForm mgf) {
		// TODO Auto-generated method stub
		boolean flag = false;
		StringBuffer sb=new StringBuffer();
		String name = mgf.getName();
		String password = mgf.getPwd();
		System.out.println("username:  "+name);
		System.out.println("password   "+password);
		sb.append("select *from tb_manager ");
		sb.append("where name='");
		sb.append(name);
		sb.append("'");
		try {
			conn = cm.getConnection();
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			if (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return flag;
	}
	/**
	 * 
	    * @Title: update  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param mgf
		* @return
	 */
	@Override
	public int update(ManagerForm mgf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("SELECT * FROM tb_purview WHERE id=");
		sb.append("'");
		sb.append(mgf.getId());
		sb.append("'");
		System.out.println(sb);
		rs=executeQuery(sb.toString());
		try {
			if(rs.next()) {
				sb.setLength(0);
				sb.append("update tb_purview set system_set=");
				sb.append(mgf.getSystemSet());
				sb.append(",reader_set=");
				sb.append(mgf.getReaderSet());
				sb.append(",book_set=");
				sb.append(mgf.getBookSet());
				sb.append(",borrow_set=");
				sb.append(mgf.getBorrowSet());
				sb.append(",system_query=");
				sb.append(mgf.getSystemQuery());
				sb.append(" where id=");
				sb.append(mgf.getId());
				System.out.println(sb);
				result=executeUpdate(sb.toString());
			}
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
	    * @Title: delete  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param mgf
		* @return
	 */
	@Override
	public int delete(ManagerForm mgf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("delete from tb_manager where id=");
		sb.append(mgf.getId());
		System.out.println(sb);
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
