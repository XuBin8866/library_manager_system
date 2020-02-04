package com.xxbb.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxbb.actionform.LibraryForm;
import com.xxbb.dao.LibraryDao;
import com.xxbb.dao.impl.LibraryDaoImpl;

/**
 * Servlet implementation class LibraryServlet
 */
@WebServlet("/library")
public class LibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     LibraryDao ld; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryServlet() {
    	ld=new LibraryDaoImpl();
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String action=req.getParameter("action");
		if("libraryQuery".equals(action)) {
			List<LibraryForm> lfs=ld.query(new LibraryForm());
			if(lfs.isEmpty()) {
				req.setAttribute("error", "系统错误，请重试！");
				req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
			}else {
				req.setAttribute("lf", lfs.get(0));
				req.getRequestDispatcher("/library_modify.jsp").forward(req, resp);
			}
			
		}else if("libraryModify".equals(action)) {
			//获取数据
			LibraryForm lf=new LibraryForm();
			lf.setId(Integer.valueOf(req.getParameter("id")));
			lf.setLibraryName(req.getParameter("libraryname"));
			lf.setCurator(req.getParameter("curator"));
			lf.setTelephone(req.getParameter("tel"));
			lf.setEmail(req.getParameter("email"));
			lf.setIntroduce(req.getParameter("introduce"));
			lf.setUrl(req.getParameter("url"));
			lf.setCreateDate(req.getParameter("createDate"));
			lf.setAddress(req.getParameter("address"));
			int result=ld.update(lf);
			if(result==1) {
				req.getRequestDispatcher("library_ok.jsp").forward(req, resp);
			}else{
				req.setAttribute("error", "更新图书馆信息失败，请重试！");
				req.getRequestDispatcher("error.jsp").forward(req, resp);
			}
		}
	}

}
