package com.xxbb.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxbb.actionform.BookcaseForm;
import com.xxbb.dao.BookcaseDao;
import com.xxbb.dao.impl.BookcaseDaoImpl;
import com.xxbb.util.StrUtil;

/**
 * Servlet implementation class BookcaseServlet
 */
@WebServlet("/bookCase")
public class BookcaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookcaseDao bcd = null;

	public BookcaseServlet() {
		bcd = new BookcaseDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action=req.getParameter("action");
		if("bookCaseQuery".equals(action)) {
			bookcaseQuery(req,resp);
		}else if("bookCaseModify".equals(action)) {
			bookcaseModify(req,resp);
		}else if("bookCaseAdd".equals(action)) {
			bookcaseAdd(req,resp);
		}else if("bookCaseDel".equals(action)) {
			bookcaseDelete(req,resp);
		}else if("bookCaseModifyQuery".equals(action)) {
			bookcaseModifyQuery(req,resp);
		}
	}

	/**
	 * 
	 * @Title: bookcaseQuery
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void bookcaseQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookcaseForm> bcfs = new ArrayList<BookcaseForm>();
		bcfs = bcd.query(new BookcaseForm());
		if (bcfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
		} else {
			req.setAttribute("bcfs", bcfs);
			req.setAttribute("fflag", "yes");
			req.getRequestDispatcher("bookcase.jsp").forward(req, resp);
		}
	}

	/**
	 * 
	 * @Title: bookcaseModify
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void bookcaseModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookcaseForm bcf = new BookcaseForm();
		bcf.setId(Integer.valueOf(req.getParameter("id")));
		bcf.setName(req.getParameter("name"));
		result = bcd.update(bcf);
		if (result == 0) {
			req.setAttribute("error", "修改书架信息失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else if(result==-1) {
			req.setAttribute("error", "该书架信息已存在！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("bookcase_ok.jsp?para=2").forward(req, resp);
		}

	}

	/**
	 * 
	 * @Title: bookcaseModifyQuery
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void bookcaseModifyQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		req.setAttribute("id", id);
		req.setAttribute("name", name);
		req.getRequestDispatcher("bookCase_Modify.jsp").forward(req, resp);
	}
	/**
	 * 
	    * @Title: bookcaseAdd
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void bookcaseAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookcaseForm bcf = new BookcaseForm();
		bcf.setName(StrUtil.filterStr(req.getParameter("name")));
		result = bcd.insert(bcf);
		if (result == 0) {
			req.setAttribute("error", "书架信息添加失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if (result == -1) {
			req.setAttribute("error", "该书架信息已经添加！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("bookcase_ok.jsp?para=1").forward(req, resp);
		}
	}
	private void bookcaseDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookcaseForm bcf = new BookcaseForm();
		bcf.setId(Integer.valueOf(req.getParameter("id")));
		result=bcd.delete(bcf);
		if(result==0) {
			 req.setAttribute("error","删除书架信息失败！");
             req.getRequestDispatcher("error.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("bookcase_ok.jsp?para=3").forward(req, resp);
		}
	}
}
