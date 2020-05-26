package com.xxbb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxbb.actionform.ParameterForm;
import com.xxbb.dao.ParameterDao;
import com.xxbb.dao.impl.ParameterDaoImpl;

/**
 * Servlet implementation class ParameterServlet
 */
@WebServlet("/parameter")
public class ParameterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  ParameterDao pd=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParameterServlet() {
    	pd=new ParameterDaoImpl();
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String action=req.getParameter("action");
		ParameterForm pf=new ParameterForm();
		if("parameterQuery".equals(action)) {
			List<ParameterForm> pfs=pd.query();
			if(pfs.isEmpty()) {
				req.setAttribute("error", "系统错误，请重试！");
				req.getRequestDispatcher("error.jsp").forward(req, resp);
			}else{
				pf=pfs.get(0);
				req.setAttribute("p",pf);
				req.getRequestDispatcher("parameter_modify.jsp").forward(req, resp);
			}
		}else if("parameterModify".equals(action)) {
			
			pf.setId(Integer.valueOf(req.getParameter("id")));
			pf.setCost(Integer.valueOf(req.getParameter("cost")));
			pf.setValidity(Integer.valueOf(req.getParameter("validity")));
			int result=pd.update(pf);
			if(result==1) {
				req.getRequestDispatcher("parameter_ok.jsp").forward(req, resp);
			}else {
				req.setAttribute("error", "更新信息失败，请重试！");
				req.getRequestDispatcher("error.jsp").forward(req, resp);
			}
		}
	}
}
