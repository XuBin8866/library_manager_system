<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.xxbb.dao.impl.ManagerDaoImpl"%>
<%@ page import="com.xxbb.dao.ManagerDao"%>
<%@ page import="com.xxbb.actionform.ManagerForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String navigationUserName = (String)session.getAttribute("username");
	ManagerDao mgd = new ManagerDaoImpl();
	ManagerForm temp=new ManagerForm();
	temp.setName(navigationUserName);
	List<ManagerForm> mgfs = mgd.query(temp);
	ManagerForm mgf = (ManagerForm) mgfs.get(0);
	request.setAttribute("manager", mgf);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="JS/onclock.JS"></script>
<script src="JS/menu.JS"></script>
<div class=menuskin id=popmenu
	onmouseover="clearhidemenu();highlightmenu(event,'on')"
	onmouseout="highlightmenu(event,'off');dynamichide(event)"
	style="Z-index: 100; position: absolute;"></div>
<table width="778" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr bgcolor="#DFA40C">
		<td width="3%" height="27" bgcolor="#c69a0a">&nbsp;</td>
		<td width="29%" bgcolor="#c69a0a"><div id="bgclock"
				class="word_white"></div></td>
		<script language="javascript">
			function quit() {
				if (confirm("是否退出系统?")) {
					window.location.href = "logout.jsp";
				}
			}
		</script>
		<td width="66%" align="right" bgcolor="#ad7c28" class="word_white"><a
			href="main.jsp" class="word_white">首页</a> | <%
			if (mgf.getSystemSet() == 1) {
		%><a
			onmouseover=showmenu(event,sysmenu) onmouseout=delayhidemenu()
			class="word_white" style="CURSOR: hand">系统设置</a> | <%
			}
		%>
			<%
				if (mgf.getReaderSet() == 1) {
			%><a onmouseover=showmenu(event,readermenu)
			onmouseout=delayhidemenu() style="CURSOR: hand" class="word_white">读者管理</a>
			| <%
				}
			%>
			<%
				if (mgf.getBookSet() == 1) {
			%><a onmouseover=showmenu(event,bookmenu)
			onmouseout=delayhidemenu() class="word_white" style="CURSOR: hand">图书管理</a>
			| <%
				}
			%>
			<%
				if (mgf.getBorrowSet() == 1) {
			%><a onmouseover=showmenu(event,borrowmenu)
			onmouseout=delayhidemenu() class="word_white" style="CURSOR: hand">图书借还</a>
			| <%
				}
			%>
			<%
				if (mgf.getSystemQuery() == 1) {
			%><a onmouseover=showmenu(event,querymenu)
			onmouseout=delayhidemenu() class="word_white" style="CURSOR: hand">系统查询</a>
			| <%
				}
			%><a href="manager?action=pwdquery" class="word_white">更改口令</a> |
			<a href="#" onClick="quit()" class="word_white">退出系统</a></td>
		<td width="2%" bgcolor="#ad7c28">&nbsp;</td>
	</tr>
	<tr bgcolor="#DFA40C">
		<td height="9" colspan="4"
			background="Images/navigation_bg_bottom.gif"></td>
	</tr>
</table>
