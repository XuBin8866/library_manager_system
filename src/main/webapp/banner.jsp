<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String username = (String) session.getAttribute("username");
	//验证用户是否登录
	if (username == null || "".equals(username)) {
		response.sendRedirect("login.jsp");
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<table width="778" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td height="118" valign="bottom" background="Images/top_bg.gif"
			bgcolor="#EEEEEE"><table width="73%" height="79" border="0"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="69" align="right" valign="bottom">当前登录用户：<%=username%></td>
				</tr>
			</table></td>
	</tr>
</table>
