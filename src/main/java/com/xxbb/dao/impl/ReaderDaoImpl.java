package com.xxbb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.xxbb.actionform.ReaderForm;
import com.xxbb.dao.ReaderDao;

/**
 * @Title: ReaderDaoImpl.java
 * @Package com.xxbb.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 徐斌
 * @date 2020年1月30日
 * @version V1.0
 */

public class ReaderDaoImpl extends BaseDaoImpl implements ReaderDao {
	/**
	 * 
	    * @Title: delete  
		* @Description: TODO(这里用一句话描述这个方法的作用)
		* @param rf
		* @return
	 */
	@Override
	public int delete(ReaderForm rf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("delete from tb_reader where id=");
		sb.append(rf.getId());
		System.out.println("正在删除学生信息："+sb.toString());
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
		* @param rf
		* @return
	 */
	@Override
	public int insert(ReaderForm rf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		//查询读者信息是否存在
		sb.append("select * from tb_reader where paper_no='");
		sb.append(rf.getPaperNO());
		sb.append("'");
		System.out.println("查询读者信息是否存在:"+sb);
		rs=executeQuery(sb.toString());
		try {
			if(rs.next()) {
				result=-1;
				System.out.println("该读者信息存在");
			}else {
				//添加读者信息
				sb.setLength(0);
				sb.append("insert into tb_reader(id,name,sex,barcode,vocation,birthday,paper_type,paper_no,telephone,email,create_date,operator,remark,type_id) values(");
				sb.append(rf.getId());
				sb.append(",'");
				sb.append(rf.getName());
				sb.append("','");
				sb.append(rf.getSex());
				sb.append("','");
				sb.append(rf.getBarcode());
				sb.append("','");
				sb.append(rf.getVocation());
				sb.append("','");
				sb.append(rf.getBirthday());
				sb.append("','");
				sb.append(rf.getPaperType());
				sb.append("','");
				sb.append(rf.getPaperNO());
				sb.append("','");
				sb.append(rf.getTelephone());
				sb.append("','");
				sb.append(rf.getEmail());
				sb.append("','");
				sb.append(rf.getCreateDate());
				sb.append("','");
				sb.append(rf.getOperator());
				sb.append("','");
				sb.append(rf.getRemark());
				sb.append("',");
				sb.append(rf.getTypeId());
				sb.append(")");
				System.out.println("更新读者信息："+sb);
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
	@Override
	public int update(ReaderForm rf) {
		// TODO Auto-generated method stub
		int result=0;
		StringBuffer sb=new StringBuffer();
		sb.append("update tb_reader set ");
		sb.append("name='");
		sb.append(rf.getName());
		sb.append("',sex='");
		sb.append(rf.getSex());
		sb.append("',barcode='");
		sb.append(rf.getBarcode());
		sb.append("',vocation='");
		sb.append(rf.getVocation());
		sb.append("',birthday='");
		sb.append(rf.getBirthday());
		sb.append("',paper_type='");
		sb.append(rf.getPaperType());
		sb.append("',paper_no='");
		sb.append(rf.getPaperNO());
		sb.append("',telephone='");
		sb.append(rf.getTelephone());
		sb.append("',email='");
		sb.append(rf.getEmail());
		sb.append("',operator='");
		sb.append(rf.getOperator());
		sb.append("',remark='");
		sb.append(rf.getRemark());
		sb.append("',type_id=");
		sb.append(rf.getTypeId());
		sb.append(" where id=");
		sb.append(rf.getId());
		System.out.println("修改读者信息："+sb);
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
	 * @param rf
	 * @return
	 */
	@Override
	public List<ReaderForm> query(ReaderForm rf) {
		// TODO Auto-generated method stub
		List<ReaderForm> rfs = new ArrayList<ReaderForm>();
		ReaderForm temprf = null;
		StringBuffer sb = new StringBuffer();
		if (rf.getId() == null && rf.getBarcode() == null) {
			sb.append("select r.*,rt.* from tb_reader r left join tb_readertype rt on r.type_id=rt.id");

			try {
				rs = executeQuery(sb.toString());
				while (rs.next()) {
					temprf = new ReaderForm();
					temprf.setId(rs.getInt(1));
					temprf.setName(rs.getString(2));
					temprf.setSex(rs.getString(3));
					temprf.setBarcode(rs.getString(4));
					temprf.setVocation(rs.getString(5));
					temprf.setBirthday(rs.getString(6));
					temprf.setPaperType(rs.getString(7));
					temprf.setPaperNO(rs.getString(8));
					temprf.setTelephone(rs.getString(9));
					temprf.setEmail(rs.getString(10));
					temprf.setCreateDate(rs.getString(11));
					temprf.setOperator(rs.getString(12));
					temprf.setRemark(rs.getString(13));
					temprf.setTypeId(rs.getInt(14));
					temprf.setTypeName(rs.getString(16));
					temprf.setAllowBorrowAmount(rs.getInt(17));
					rfs.add(temprf);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				close();
			}
		} else if (rf != null && rf.getBarcode() != null) {
			sb.setLength(0);
			sb.append(
					"select r.*,rt.name as type_name,rt.allow_borrow_amount from tb_reader r left join tb_readertype rt on r.type_id=rt.id where r.barcode='");
			sb.append(rf.getBarcode());
			sb.append("'");
			System.out.println("借阅时个人读者信息查询" + sb);
			try {
				rs = executeQuery(sb.toString());
				while (rs.next()) {
					temprf = new ReaderForm();
					temprf.setId(rs.getInt(1));
					temprf.setName(rs.getString(2));
					temprf.setSex(rs.getString(3));
					temprf.setBarcode(rs.getString(4));
					temprf.setVocation(rs.getString(5));
					temprf.setBirthday(rs.getString(6));
					temprf.setPaperType(rs.getString(7));
					temprf.setPaperNO(rs.getString(8));
					temprf.setTelephone(rs.getString(9));
					temprf.setEmail(rs.getString(10));
					temprf.setCreateDate(rs.getString(11));
					temprf.setOperator(rs.getString(12));
					temprf.setRemark(rs.getString(13));
					temprf.setTypeId(rs.getInt(14));
					temprf.setTypeName(rs.getString(15));
					temprf.setAllowBorrowAmount(rs.getInt(16));
					rfs.add(temprf);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				close();
			}
		} else if (rf != null && rf.getId() != null) {
			sb.append("select r.*,rt.* from tb_reader r left join tb_readertype rt on r.type_id=rt.id where r.id=");
			sb.append(rf.getId());
			try {
				rs = executeQuery(sb.toString());
				while (rs.next()) {
					temprf = new ReaderForm();
					temprf.setId(rs.getInt(1));
					temprf.setName(rs.getString(2));
					temprf.setSex(rs.getString(3));
					temprf.setBarcode(rs.getString(4));
					temprf.setVocation(rs.getString(5));
					temprf.setBirthday(rs.getString(6));
					temprf.setPaperType(rs.getString(7));
					temprf.setPaperNO(rs.getString(8));
					temprf.setTelephone(rs.getString(9));
					temprf.setEmail(rs.getString(10));
					temprf.setCreateDate(rs.getString(11));
					temprf.setOperator(rs.getString(12));
					temprf.setRemark(rs.getString(13));
					temprf.setTypeId(rs.getInt(14));
					temprf.setTypeName(rs.getString(16));
					temprf.setAllowBorrowAmount(rs.getInt(17));
					rfs.add(temprf);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				close();
			}
		}

		return rfs;
	}
}
