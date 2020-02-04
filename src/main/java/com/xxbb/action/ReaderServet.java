package com.xxbb.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxbb.actionform.ReaderForm;
import com.xxbb.actionform.ReaderTypeForm;
import com.xxbb.dao.ReaderDao;
import com.xxbb.dao.ReaderTypeDao;
import com.xxbb.dao.impl.ReaderDaoImpl;
import com.xxbb.dao.impl.ReaderTypeDaoImpl;
import com.xxbb.util.TimeUtil;

/**
 * Servlet implementation class ReaderServet
 */
@WebServlet("/reader")
public class ReaderServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReaderTypeDao rtd = null;
	private ReaderDao rd = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReaderServet() {
		rtd = new ReaderTypeDaoImpl();
		rd = new ReaderDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		if (action == null || "".equals(action)) {
			req.setAttribute("error", "您的操作有误！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if ("readerTypeAdd".equals(action)) {
			readerTypeAdd(req, resp);
		} else if ("readerTypeQuery".equals(action)) {
			readerTypeQuery(req, resp);
		} else if ("readerTypeModifyQuery".equals(action)) {
			readerTypeModifyQuery(req, resp);
		} else if ("readerTypeModify".equals(action)) {
			readerTypeModify(req, resp);
		} else if ("readerTypeDel".equals(action)) {
			readerTypeDelete(req, resp);
		}else if("readerQuery".equals(action)) {
			readerQuery(req,resp);
		}
		else if("readerDetail".equals(action)) {
			readerDetail(req,resp);
		}
		else if("readerModifyQuery".equals(action)) {
			readerModifyQuery(req,resp);
		}
		else if("readerModify".equals(action)) {
			readerModify(req,resp);
		}
		else if("readerDel".equals(action)) {
			readerDelete(req,resp);
		}
		else if("readerAdd".equals(action)) {
			readerAdd(req,resp);
		}else if("readerAddQuery".equals(action)) {
			readerAddQuery(req,resp);
		}
		
	}

	
	private void readerQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ReaderForm> rfs=rd.query(new ReaderForm());
		if(rfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("reader.jsp").forward(req, resp);
		}else {
			req.setAttribute("fflag", "yes");
			req.setAttribute("rfs", rfs);
			req.getRequestDispatcher("reader.jsp").forward(req, resp);
		}
	}
	private void readerDetail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ReaderForm rf=new ReaderForm();
		rf.setId(Integer.valueOf(req.getParameter("id")));
		List<ReaderForm> rfs=rd.query(rf);
		if(rfs.isEmpty()) {
			req.setAttribute("error", "查看读者具体信息失败，请重试！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else {
			req.setAttribute("rf", rfs.get(0));
			req.getRequestDispatcher("reader_detail.jsp").forward(req, resp);
		}
		
	}
	private void readerModifyQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ReaderForm rf=new ReaderForm();
		List<ReaderTypeForm> rtfs=new ArrayList<ReaderTypeForm>();
		rf.setId(Integer.valueOf(req.getParameter("id")));
		
		List<ReaderForm> rfs=rd.query(rf);
		rtfs=rtd.query(new ReaderTypeForm());
		
		if(rfs.isEmpty()||rtfs.isEmpty()) {
			req.setAttribute("error", "进入修改读者信息页面失败，请重试！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else {
			req.setAttribute("rf", rfs.get(0));
			req.setAttribute("rtfs", rtfs);
			req.getRequestDispatcher("reader_Modify.jsp").forward(req, resp);
		}
	}
	private void readerModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ReaderForm rf=new ReaderForm();
		rf.setId(Integer.valueOf(req.getParameter("id")));
		rf.setName(req.getParameter("name"));
		rf.setSex(req.getParameter("sex"));
		rf.setBarcode(req.getParameter("barcode"));
		rf.setTypeId(Integer.valueOf(req.getParameter("typeid")));
		rf.setVocation(req.getParameter("vocation"));
		rf.setBirthday(req.getParameter("birthday"));
		rf.setPaperType(req.getParameter("paperType"));
		rf.setPaperNO(req.getParameter("paperNO"));
		rf.setTelephone(req.getParameter("tel"));
		rf.setEmail(req.getParameter("email"));
		rf.setOperator(req.getParameter("operator"));
		rf.setRemark(req.getParameter("remark"));
		int result=rd.update(rf);
		if(result==0) {
			req.setAttribute("error", "读者信息修改失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("reader_ok.jsp?para=2").forward(req, resp);
		}
		
	}
	private void readerDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ReaderForm rf=new ReaderForm();
		rf.setId(Integer.valueOf(req.getParameter("id")));
		int result=rd.delete(rf);
		if(result==0){
			req.setAttribute("error", "删除读者信息失败!");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else{
			req.getRequestDispatcher("reader_ok.jsp?para=3").forward(
					req, resp);
		}
		
	}
	private void readerAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ReaderForm rf = new ReaderForm();
		rf.setName(req.getParameter("name"));
		rf.setSex(req.getParameter("sex"));
		rf.setBarcode(req.getParameter("barcode"));
		rf.setVocation(req.getParameter("vocation"));
		rf.setBirthday(req.getParameter("birthday"));
		rf.setPaperType(req.getParameter("paperType"));
		rf.setPaperNO(req.getParameter("paperNO"));
		rf.setTelephone(req.getParameter("tel"));
		rf.setEmail(req.getParameter("email"));
		// 获取系统日期
		String date=TimeUtil.getDate();
		rf.setCreateDate(date);
		rf.setOperator(req.getParameter("operator"));
		rf.setRemark(req.getParameter("remark"));
		rf.setTypeId(Integer.parseInt(req.getParameter("typeid")));
		int result = rd.insert(rf);
		if (result == 0) {
			req.setAttribute("error", "读者信息添加失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if (result == -1) {
			req.setAttribute("error", "该读者信息已经添加！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("reader_ok.jsp?para=1").forward(req, resp);
		}
	}
	private void readerAddQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ReaderTypeForm> rtfs=new ArrayList<ReaderTypeForm>();
		rtfs=rtd.query(new ReaderTypeForm());
		
		if(rtfs.isEmpty()) {
			req.setAttribute("error", "进入添加读者信息页面失败，请重试！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else {
			req.setAttribute("rtfs", rtfs);
			req.getRequestDispatcher("reader_add.jsp").forward(req, resp);
		}
	}
		
	/**
	 * 
	    * @Title: readerTypeAdd
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void readerTypeAdd(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int result=0;
		ReaderTypeForm rtf = new ReaderTypeForm();
		rtf.setName(req.getParameter("name"));
		rtf.setAllowBorrowAmount(Integer.valueOf(req.getParameter("number")));
		result=rtd.insert(rtf);
		if(result==-1) {
			req.setAttribute("error", "该读者类型信息已经添加！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else if(result==0) {
			req.setAttribute("error", "该读者类型信息添加失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("readerType_ok.jsp?para=1").forward(req, resp);
		}
		
	}

	private void readerTypeQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<ReaderTypeForm> rtfs = new ArrayList<ReaderTypeForm>();
		rtfs = rtd.query(new ReaderTypeForm());
		if (rtfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("readerType.jsp").forward(req, resp);
		} else {
			req.setAttribute("rtfs", rtfs);
			req.setAttribute("fflag", "yes");
			req.getRequestDispatcher("readerType.jsp").forward(req, resp);
		}
	}
	private void readerTypeModifyQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String number=req.getParameter("number");
		
		req.setAttribute("id",id);
		req.setAttribute("name", name);
		req.setAttribute("number",number);
		req.getRequestDispatcher("readerType_Modify.jsp").forward(req, resp);
	}
	private void readerTypeModify(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int result = 0;
		ReaderTypeForm rtf = new ReaderTypeForm();
		rtf.setId(Integer.valueOf(req.getParameter("id")));
		rtf.setName(req.getParameter("name"));
		rtf.setAllowBorrowAmount(Integer.valueOf(req.getParameter("number")));
		result = rtd.update(rtf);
		if (result == 0) {
			req.setAttribute("error", "修改学生信息失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("readerType_ok.jsp?para=2").forward(req, resp);
		}
	}
	private void readerTypeDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int result=0;
		ReaderTypeForm rtf=new ReaderTypeForm();
		rtf.setId(Integer.valueOf(req.getParameter("id")));
		result=rtd.delete(rtf);
		if (result== 0) {
			req.setAttribute("error", "删除读者类型信息失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("readerType_ok.jsp?para=3").forward(req, resp);
		}
	}

}
