package com.xxbb.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxbb.actionform.ManagerForm;
import com.xxbb.dao.ManagerDao;
import com.xxbb.dao.impl.ManagerDaoImpl;
import com.xxbb.util.StrUtil;

/**
 * Servlet implementation class ManagerSerlvet
 */
@WebServlet(urlPatterns = "/manager")
public class ManagerSerlvet extends HttpServlet {
	// 用于版本控制的
	private static final long serialVersionUID = 1L;
	private ManagerDao md = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagerSerlvet() {
		md = new ManagerDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		if (action == null || "".equals(action)) {
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if ("login".equals(action)) {
			managerLogin(req, resp);
		} else if ("managerQuery".equals(action)) {
			managerQuery(req, resp);
		} else if ("managerAdd".equals(action)) {
			managerAdd(req, resp);
		} else if ("managerModifyQuery".equals(action)) {
			managerModifyQuery(req, resp);
		} else if ("managerModify".equals(action)) {
			managerModify(req, resp);
		} else if ("managerDelete".equals(action)) {
			managerDelete(req, resp);
		}else if("pwdquery".equals(action)) {
			pwdQuery(req,resp);
		}else if("pwdmodify".equals(action)) {
			pwdModify(req,resp);
		}
	}
	/**
	 * 
	    * @Title: pwdModify
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void pwdModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取账号密码
		String name=req.getParameter("name");
		String password=req.getParameter("pwd");
		
		//传递用户信息
		ManagerForm mgf=new ManagerForm();
		mgf.setName(name);
		mgf.setPwd(password);
		
		//获取操作结果
		int result=md.updatePassword(mgf);
		if(result==1) {
			req.getRequestDispatcher("/pwd_ok.jsp").forward(req, resp);
		}else {
			req.setAttribute("error", "密码修改失败，请重试！");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
	}
	
	/**
	 * 
	    * @Title: pwdquery
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void pwdQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取用户名
		HttpSession hs=req.getSession();
		String userName=(String) hs.getAttribute("username");
		ManagerForm mgf=new ManagerForm();
		mgf.setName(userName);
		List<ManagerForm> mgfs=md.query(mgf);
		if(!mgfs.isEmpty()) {
			req.setAttribute("manager", mgfs.get(0));
			req.getRequestDispatcher("/pwd_Modify.jsp").forward(req, resp);
		}else{
			req.setAttribute("error", "系统故障，请重试");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
	}
	/**
	 * 
	 * @Title: managerLogin
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void managerLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取数据并过滤危险字符
		ManagerForm mgf = new ManagerForm();
		mgf.setName(StrUtil.filterStr(req.getParameter("name")));
		mgf.setPwd(StrUtil.filterStr(req.getParameter("pwd")));
		System.out.println("开始进行登录验证！！！");
		boolean flag = md.checkManager(mgf);
		if (flag) {
			// 保存用户登录信息
			HttpSession s = req.getSession();
			s.setAttribute("username", mgf.getName());
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", "账号或密码错误");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	/**
	 * 
	 * @Title: managerQuery
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void managerQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取数据并过滤危险字符
		ManagerForm mgf = new ManagerForm();
		System.out.println("开始进行管理员查询！！！");
		// 查询并传值
		req.setAttribute("managers", md.query(mgf));
		req.getRequestDispatcher("manager.jsp").forward(req, resp);
	}

	/**
	 * 
	 * @Title: managerAdd
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void managerAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取数据并过滤危险字符
		System.out.println("开始进行管理员添加！！！");
		ManagerForm mgf = new ManagerForm();
		mgf.setName(StrUtil.filterStr(req.getParameter("name")));
		mgf.setPwd(StrUtil.filterStr(req.getParameter("pwd")));
		int result = md.insert(mgf);
		if (result == 1) {
			// 转到管理员信息添加成功页面
			req.getRequestDispatcher("manager_ok.jsp?para=1").forward(req, resp);
		} else if (result == -1) {
			// 将错误信息保存到error参数中
			// 转到错误提示页面
			req.setAttribute("error", "该管理员信息已经添加！！！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if (result == 0) {
			// 将错误信息保存到error参数中
			// 转到错误提示页面
			req.setAttribute("error", "添加管理员信息失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}

		// 查询并传值
		ManagerForm m = new ManagerForm();
		req.setAttribute("managers", md.query(m));
		req.getRequestDispatcher("manager.jsp").forward(req, resp);
	}

	private void managerModifyQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取数据并过滤危险字符
		System.out.println("开始进行权限查询操作！！！");
		ManagerForm mgf = new ManagerForm();
		mgf.setId(Integer.valueOf(req.getParameter("id")));
		List<ManagerForm> mgfs = md.query(mgf);
		ManagerForm m = (ManagerForm) mgfs.get(0);
		req.setAttribute("mmq", m);
		req.getRequestDispatcher("/manager_Modify.jsp").forward(req, resp);
	}

	/**
	 * 
	 * @Title: managerModify
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void managerModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("开始进行权限修改操作！！！");
		ManagerForm mgf = new ManagerForm();
		mgf.setId(Integer.parseInt(req.getParameter("id"))); // 获取并设置管理员ID号
		mgf.setSystemSet(req.getParameter("sysset") == null ? 0 : 1);
		mgf.setReaderSet(req.getParameter("readerset") == null ? 0 : 1);
		mgf.setBookSet(req.getParameter("bookset") == null ? 0 : 1);
		mgf.setBorrowSet(req.getParameter("borrowback") == null ? 0 : 1);
		mgf.setSystemQuery(req.getParameter("sysquery") == null ? 0 : 1);
		int result = md.update(mgf);
		if (result == 1) {
			// 转到权限设置成功页面
			req.getRequestDispatcher("manager_ok.jsp?para=2").forward(req, resp);
		} else {
			req.setAttribute("error", "设置管理员权限失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp); // 转到错误提示页面
		}

	}
	/**
	 * 
	    * @Title: managerModify
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void managerDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("开始进行管理员删除操作！！！");
		ManagerForm mgf = new ManagerForm();
		mgf.setId(Integer.parseInt(req.getParameter("id"))); // 获取并设置管理员ID号
		int result = md.delete(mgf);
		if (result == 1) {
			// 转到权限设置成功页面
			req.getRequestDispatcher("manager_ok.jsp?para=3").forward(req, resp);
		} else {
			req.setAttribute("error", "设置管理员删除失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp); // 转到错误提示页面
		}

	}

}
