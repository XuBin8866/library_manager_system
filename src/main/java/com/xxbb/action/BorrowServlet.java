package com.xxbb.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxbb.actionform.BookBorrowForm;
import com.xxbb.actionform.BookForm;
import com.xxbb.actionform.ReaderForm;
import com.xxbb.dao.BookBorrowDao;
import com.xxbb.dao.BookDao;
import com.xxbb.dao.ReaderDao;
import com.xxbb.dao.impl.BookBorrowDaoImpl;
import com.xxbb.dao.impl.BookDaoImpl;
import com.xxbb.dao.impl.ReaderDaoImpl;
import com.xxbb.util.StrUtil;

/**
 * Servlet implementation class BorrowServlet
 */
@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookBorrowDao bbd;
	private ReaderDao rd;
	private BookDao bd;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowServlet() {
		bbd = new BookBorrowDaoImpl();
		rd = new ReaderDaoImpl();
		bd = new BookDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getParameter("action");

		if ("bookborrow".equals(action)) {
			bookBorrow(req, resp);
		}else if("bookreturnquery".equals(action)) {
			bookReturnQuery(req,resp);
		}else if("bookreturn".equals(action)) {
			bookReturn(req,resp);
		}else if("bookrenewquery".equals(action)) {
			bookRenewQuery(req,resp);
		}else if("bookrenew".equals(action)) {
			bookRenew(req,resp);
		}else if("borrowQuery".equals(action)) {
			borrowInfoQuery(req,resp);
		}else if("borrowRemind".equals(action)) {
			borrowRemind(req,resp);
		}
	}
	/**
	 * 
	    * @Title: borrowInfoQueryAll
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void borrowInfoQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//获取复选框的值
		String[] flag=req.getParameterValues("flag");
		//查询条件
		if(flag!=null) {
			//判断查询方式
				//以条件查询
			if("item".equals(flag[0])) {
				String[] strs=new String[2];
				strs[0]=req.getParameter("f");
				strs[1]=req.getParameter("key");
				List<BookBorrowForm> bbfs=bbd.queryForManager(strs);
				if(bbfs.isEmpty()) {
					req.setAttribute("error", "未查询到借阅信息，请检查输入条件是否正确！");
					req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
				}else {
					//传回查询条件和条件内容
					req.setAttribute("supfunction", flag[0]);
					req.setAttribute("function", strs[0]);
					req.setAttribute("value", strs[1]);
					//查询结果
					System.out.println(bbfs);
					req.setAttribute("borrowquery", bbfs);
					
					req.getRequestDispatcher("/borrowQuery.jsp").forward(req, resp);
				}
			}else if("date".equals(flag[0])) {
				String[] strs=new String[3];
				strs[0]="startDate";
				strs[1]=req.getParameter("sdate");
				strs[2]=req.getParameter("edate");
				List<BookBorrowForm> bbfs=bbd.queryForManager(strs);
				if(bbfs.isEmpty()) {
					req.setAttribute("error", "未查询到借阅信息，请检查输入条件是否正确！");
					req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
				}else {
					//传回查询条件和条件内容
					req.setAttribute("supfunction", "date");
					req.setAttribute("startValue", strs[1]);
					req.setAttribute("endValue", strs[2]);
					//查询结果
					System.out.println(bbfs);
					req.setAttribute("borrowquery", bbfs);
					
					req.getRequestDispatcher("/borrowQuery.jsp").forward(req, resp);
				}
				
			}
		}
		//不附带查询条件，通过菜单栏进入该页面的情况
		else {
			BookBorrowForm temp=new BookBorrowForm();
			List<BookBorrowForm> bbfs=bbd.query(temp);
			if(bbfs.isEmpty()) {
				req.setAttribute("error", "系统错误，请重试！");
				req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
			}else {
				req.setAttribute("borrowquery", bbfs);
				req.getRequestDispatcher("/borrowQuery.jsp").forward(req, resp);
			}
		}
	}
	/**
	 * 
	    * @Title: bookRenew
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void bookRenew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ReaderForm rf = new ReaderForm();
		// 获取读者条形码并过滤字符
		rf.setBarcode(StrUtil.filterStr(req.getParameter("barcode")));
		List<ReaderForm> rfs = rd.query(rf);
		if (!rfs.isEmpty()) {
			ReaderForm r = rfs.get(0);
			BookBorrowForm b = new BookBorrowForm();
			b.setReaderId(r.getId());
			List<BookBorrowForm> bbfs = bbd.query(b);
			req.setAttribute("borrowinfo", bbfs);
			req.setAttribute("readerinfo", rfs.get(0));
			
			//进行图书续借
			BookBorrowForm b2=new BookBorrowForm();
			b2.setId(Integer.valueOf(req.getParameter("id")));
			b2.setReturnTime(req.getParameter("return_time"));
			int result=bbd.updateBorrow(b2);
			if(result==0) {
				req.setAttribute("error", "续借图书失败!");
				req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
			}else {
				req.setAttribute("bar", r.getBarcode());
				req.getRequestDispatcher("/bookRenew_ok.jsp").forward(req, resp);// 转到归还成功页面
			}
		}else {
			req.setAttribute("error", "请勿修改读者条形码!");
			req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
		}
	}
	/**
	 * 
	    * @Title: bookRenewQuery
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void bookRenewQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ReaderForm rf = new ReaderForm();
		// 获取读者条形码并过滤字符
		rf.setBarcode(StrUtil.filterStr(req.getParameter("barcode")));
		List<ReaderForm> rfs = rd.query(rf);
		if (!rfs.isEmpty()) {
			ReaderForm r = rfs.get(0);
			BookBorrowForm b = new BookBorrowForm();
			b.setReaderId(r.getId());
			List<BookBorrowForm> bbfs = bbd.query(b);
			req.setAttribute("borrowinfo", bbfs);
			req.setAttribute("readerinfo", rfs.get(0));
			
			req.getRequestDispatcher("bookRenew.jsp").forward(req, resp);
		}else{
			req.setAttribute("error", "未查询到该读者!");
			req.getRequestDispatcher("error.jsp").forward(req, resp); // 转到错误提示页面
		}
	}
	/**
	 * 
	    * @Title: bookReturn
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void bookReturn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ReaderForm rf = new ReaderForm();
		// 获取读者条形码并过滤字符
		rf.setBarcode(StrUtil.filterStr(req.getParameter("barcode")));
		List<ReaderForm> rfs = rd.query(rf);
		if (!rfs.isEmpty()) {
			ReaderForm r = rfs.get(0);
			BookBorrowForm b = new BookBorrowForm();
			b.setReaderId(r.getId());
			List<BookBorrowForm> bbfs = bbd.query(b);
			req.setAttribute("borrowinfo", bbfs);
			req.setAttribute("readerinfo", rfs.get(0));
			
			//进行图书归还
			BookBorrowForm b2 = new BookBorrowForm();
			b2.setId(Integer.valueOf(req.getParameter("id")));
			b2.setReaderId(Integer.valueOf(req.getParameter("reader_id")));
			b2.setBookId(Integer.valueOf(req.getParameter("book_id")));
			b2.setOperator(req.getParameter("operator"));
			int result=bbd.insertReturn(b2);
			if(result==0) {
				req.setAttribute("error", "归还图书失败!");
				req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
			}else {
				req.setAttribute("bar", r.getBarcode());
				req.getRequestDispatcher("/bookBack_ok.jsp").forward(req, resp);// 转到归还成功页面
			}
		}else {
			req.setAttribute("error", "请勿修改读者条形码!");
			req.getRequestDispatcher("/error.jsp").forward(req, resp); // 转到错误提示页面
		}
	}
	/**
	 * 
	    * @Title: bookReturnQuery
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param req
	    * @param resp
	    * @throws ServletException
	    * @throws IOException
	    * @return void    返回类型
	 */
	private void bookReturnQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ReaderForm rf = new ReaderForm();
		// 获取读者条形码并过滤字符
		rf.setBarcode(StrUtil.filterStr(req.getParameter("barcode")));
		List<ReaderForm> rfs = rd.query(rf);
		if (!rfs.isEmpty()) {
			ReaderForm r = rfs.get(0);
			BookBorrowForm b = new BookBorrowForm();
			b.setReaderId(r.getId());
			List<BookBorrowForm> bbfs = bbd.query(b);
			req.setAttribute("borrowinfo", bbfs);
			req.setAttribute("readerinfo", rfs.get(0));
			
			req.getRequestDispatcher("bookBack.jsp").forward(req, resp);
		}else{
			req.setAttribute("error", "未查询到该读者!");
			req.getRequestDispatcher("error.jsp").forward(req, resp); // 转到错误提示页面
		}
	}
	/**
	 * 
	 * @Title: bookBorrow
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @return void 返回类型
	 */
	private void bookBorrow(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 查询读者信息及其借阅信息
		ReaderForm rf = new ReaderForm();
		// 获取读者条形码并过滤字符
		rf.setBarcode(StrUtil.filterStr(req.getParameter("barcode")));
		List<ReaderForm> rfs = rd.query(rf);
		if (!rfs.isEmpty()) {
			ReaderForm r = rfs.get(0);
			BookBorrowForm b = new BookBorrowForm();
			b.setReaderId(r.getId());
			List<BookBorrowForm> bbfs = bbd.query(b);
			req.setAttribute("borrowinfo", bbfs);
			req.setAttribute("readerinfo", rfs.get(0));

		}
		// 借阅图书
		String f = req.getParameter("f");
		String key = req.getParameter("inputkey");
		if (key != null &&(!"".equals(key))) {
			System.out.println("正在进行图书借阅！！！");
			HttpSession hs = req.getSession();
			String operator = (String) hs.getAttribute("username");
			BookForm b = new BookForm();
			System.out.println("此时操作用户："+operator);
			// 两种借阅方式
			if ("barcode".equals(f)) {
				b.setBookBarcode(key);
			} else if ("bookname".equals(f)) {
				b.setBookName(key);
			}
			List<BookForm> bfs = bd.existQuery(b);
			if (!bfs.isEmpty()) {
				int result = bbd.insertBorrow(rfs.get(0).getId(), bfs.get(0).getId(), operator);
				if (result == 1) {
					req.setAttribute("bar", req.getParameter("barcode"));
					req.getRequestDispatcher("bookBorrow_ok.jsp").forward(req, resp);// 转到借阅成功页面
				} else {
					req.setAttribute("error", "添加借阅信息失败!");
					req.getRequestDispatcher("error.jsp").forward(req, resp); // 转到错误提示页面
				}
			} else {
				req.setAttribute("error", "没有该图书!");
				req.getRequestDispatcher("error.jsp").forward(req, resp); // 转到错误提示页面
			}
		}else {
			req.getRequestDispatcher("bookBorrow.jsp").forward(req, resp); //转到图书借阅页面
		}

	}
	private void borrowRemind(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<BookBorrowForm> bbfs=bbd.queryForManager(new String[] {"if_back","0"});
		if(bbfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("bremind.jsp").forward(req, resp);
		}else {
			req.setAttribute("bbfs", bbfs);
			req.getRequestDispatcher("bremind.jsp").forward(req, resp);
		}
	}
	
}
