package com.xxbb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxbb.actionform.BookForm;
import com.xxbb.actionform.BookTypeForm;
import com.xxbb.actionform.BookcaseForm;
import com.xxbb.actionform.PublishingForm;
import com.xxbb.dao.BookDao;
import com.xxbb.dao.BookTypeDao;
import com.xxbb.dao.BookcaseDao;
import com.xxbb.dao.PublishingDao;
import com.xxbb.dao.impl.BookDaoImpl;
import com.xxbb.dao.impl.BookTypeDaoImpl;
import com.xxbb.dao.impl.BookcaseDaoImpl;
import com.xxbb.dao.impl.PublishingDaoImpl;
import com.xxbb.util.TimeUtil;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookTypeDao btd = null;
	private BookDao bd = null;
	private PublishingDao pd = null;
	private BookcaseDao bcd = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookServlet() {
		btd = new BookTypeDaoImpl();
		bd = new BookDaoImpl();
		pd = new PublishingDaoImpl();
		bcd = new BookcaseDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String action = req.getParameter("action");
		if ("bookTypeQuery".equals(action)) {
			bookTypeQuery(req, resp);
		} else if ("bookTypeAdd".equals(action)) {
			bookTypeAdd(req, resp);
		} else if ("bookTypeDel".equals(action)) {
			bookTypeDelete(req, resp);
		} else if ("bookTypeModifyQuery".equals(action)) {
			bookTypeModifyQuery(req, resp);
		} else if ("bookTypeModify".equals(action)) {
			bookTypeModify(req, resp);
		} else if ("bookQuery".equals(action)) {
			bookQuery(req, resp);
		} else if ("bookDetail".equals(action)) {
			bookDetail(req, resp);
		} else if ("bookQueryAll".equals(action)) {
			bookQueryAll(req, resp);
		} else if ("bookModifyQuery".equals(action)) {
			bookModifyQuery(req, resp);
		} else if ("bookModify".equals(action)) {
			bookModify(req, resp);
		} else if ("bookAdd".equals(action)) {
			bookAdd(req, resp);
		} else if ("bookInfoQuery".equals(action)) {
			bookInfoQuery(req, resp);
		} else if ("bookDel".equals(action)) {
			bookDel(req, resp);
		} else if ("bookAddQuery".equals(action)) {
			bookAddQuery(req, resp);
		}

	}

	private void bookTypeQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookTypeForm> btfs = btd.query(new BookTypeForm());
		if (btfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("bookType.jsp").forward(req, resp);
		} else {
			req.setAttribute("fflag", "yes");
			req.setAttribute("btfs", btfs);
			req.getRequestDispatcher("bookType.jsp").forward(req, resp);
		}
	}

	private void bookTypeAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookTypeForm btf = new BookTypeForm();

		btf.setTypeName(req.getParameter("typeName"));
		btf.setLimitedTime(Integer.valueOf(req.getParameter("time")));

		result = btd.insert(btf);
		if (result == 0) {
			req.setAttribute("error", "图书类型信息添加失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if (result == -1) {
			req.setAttribute("error", "该图书类型信息已经添加！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("bookType_ok.jsp?para=1").forward(req, resp);
		}

	}

	private void bookTypeDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookTypeForm btf = new BookTypeForm();

		btf.setId(Integer.valueOf(req.getParameter("id")));

		result = btd.delete(btf);
		if (result == 0) {
			req.setAttribute("error", "删除图书类型信息失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("bookType_ok.jsp?para=3").forward(req, resp);
		}
	}

	private void bookTypeModifyQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("id", req.getParameter("id"));
		req.setAttribute("name", req.getParameter("name"));
		req.setAttribute("time", req.getParameter("time"));
		req.getRequestDispatcher("bookType_Modify.jsp").forward(req, resp);
	}

	private void bookTypeModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookTypeForm btf = new BookTypeForm();
		btf.setId(Integer.valueOf(req.getParameter("id")));
		btf.setLimitedTime(Integer.valueOf(req.getParameter("time")));

		result = btd.update(btf);
		if (result == 0) {
			req.setAttribute("error", "修改图书类型信息失败！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("bookType_ok.jsp?para=2").forward(req, resp);
		}
	}

	private void bookQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] funcs = new String[2];

		funcs[0] = req.getParameter("f");
		funcs[1] = req.getParameter("key");

		List<BookForm> bfs = bd.query(funcs);
		if (bfs.isEmpty()) {
			req.setAttribute("func", funcs[0]);
			req.setAttribute("value", funcs[1]);
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("bookQuery.jsp").forward(req, resp);
		} else {
			req.setAttribute("func", funcs[0]);
			req.setAttribute("value", funcs[1]);
			req.setAttribute("bfs", bfs);
			req.getRequestDispatcher("bookQuery.jsp").forward(req, resp);
		}

	}

	private void bookQueryAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookForm> bfs = bd.query(new BookForm());
		if (bfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("bookQuery.jsp").forward(req, resp);
		} else {
			req.setAttribute("bfs", bfs);
			req.getRequestDispatcher("bookQuery.jsp").forward(req, resp);
		}

	}

	private void bookDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookForm bf = new BookForm();
		bf.setId(Integer.valueOf(req.getParameter("id")));
		List<BookForm> bfs = bd.query(bf);
		if (bfs.isEmpty()) {
			req.setAttribute("error", "查看图书具体信息出错，请重试！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.setAttribute("b", bfs.get(0));
			req.getRequestDispatcher("book_detail.jsp").forward(req, resp);
		}
	}

	private void bookModifyQuery(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		List<BookTypeForm> btfs = btd.query(new BookTypeForm());
		List<PublishingForm> pfs = pd.query(new PublishingForm());
		List<BookcaseForm> bcfs = bcd.query(new BookcaseForm());

		BookForm bf = new BookForm();
		bf.setId(Integer.valueOf(req.getParameter("id")));
		List<BookForm> bfs = bd.query(bf);
		if (btfs.isEmpty() || bcfs.isEmpty() || bfs.isEmpty() || pfs.isEmpty()) {
			req.setAttribute("error", "进入图书信息修改页面出错，请重试！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.setAttribute("b", bfs.get(0));
			req.setAttribute("bcfs", bcfs);
			req.setAttribute("btfs", btfs);
			req.setAttribute("pfs", pfs);
			req.getRequestDispatcher("book_Modify.jsp").forward(req, resp);
		}

	}

	private void bookModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookForm bf = new BookForm();
		HttpSession hs = req.getSession();

		bf.setId(Integer.valueOf(req.getParameter("id")));
		bf.setBookBarcode(req.getParameter("barcode"));
		bf.setBookName(req.getParameter("bookName"));
		bf.setBookTypeId(Integer.valueOf(req.getParameter("typeId")));
		bf.setAuthor(req.getParameter("author"));
		bf.setTranslator(req.getParameter("translator"));
		bf.setIsbn((req.getParameter("isbn")));
		bf.setPrice(Double.valueOf(req.getParameter("price")));
		bf.setPage(Integer.valueOf(req.getParameter("page")));
		bf.setBookcaseId(Integer.valueOf(req.getParameter("bookcaseid")));
		bf.setOperator((String) hs.getAttribute("username"));
		result = bd.update(bf);
		if (result == 0) {
			req.setAttribute("error", "修改图书信息失败，请重试！！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if (result == -1) {
			req.setAttribute("error", "图书条形码已存在，请修改！！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("book_ok.jsp?para=2").forward(req, resp);
		}
	}

	private void bookDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookForm bf = new BookForm();
		bf.setId(Integer.valueOf(req.getParameter("id")));
		result = bd.delete(bf);
		if (result == 0) {
			req.setAttribute("error", "删除图书信息失败，请重试！！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("book_ok.jsp?para=3").forward(req, resp);
		}
	}

	private void bookInfoQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookForm> bfs = bd.query(new BookForm());
		if (bfs.isEmpty()) {
			req.setAttribute("fflag", "no");
			req.getRequestDispatcher("book.jsp").forward(req, resp);
		} else {
			req.setAttribute("bfs", bfs);
			req.getRequestDispatcher("book.jsp").forward(req, resp);
		}
	}

	private void bookAddQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookTypeForm> btfs = btd.query(new BookTypeForm());
		List<PublishingForm> pfs = pd.query(new PublishingForm());
		List<BookcaseForm> bcfs = bcd.query(new BookcaseForm());
		if (btfs.isEmpty() || bcfs.isEmpty() || pfs.isEmpty()) {
			req.setAttribute("error", "进入图书信息修改页面出错，请重试！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.setAttribute("bcfs", bcfs);
			req.setAttribute("btfs", btfs);
			req.setAttribute("pfs", pfs);
			req.getRequestDispatcher("book_add.jsp").forward(req, resp);
		}

	}

	private void bookAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int result = 0;
		BookForm bf = new BookForm();
		HttpSession hs = req.getSession();
		String date = TimeUtil.getDate();

		bf.setBookBarcode(req.getParameter("barcode"));
		bf.setBookName(req.getParameter("bookName"));
		bf.setBookTypeId(Integer.valueOf(req.getParameter("typeId")));
		bf.setAuthor(req.getParameter("author"));
		bf.setTranslator(req.getParameter("translator"));
		bf.setIsbn((req.getParameter("isbn")));
		bf.setPrice(Double.valueOf(req.getParameter("price")));
		bf.setPage(Integer.valueOf(req.getParameter("page")));
		bf.setBookcaseId(Integer.valueOf(req.getParameter("bookcaseid")));
		bf.setOperator((String) hs.getAttribute("username"));
		bf.setIntime(date);

		result = bd.insert(bf);
		if (result == 0) {
			req.setAttribute("error", "添加图书信息失败，请重试！！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else if (result == -1) {
			req.setAttribute("error", "图书条形码已存在，请修改！！");
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("book_ok.jsp?para=1").forward(req, resp);
		}
	}

}
