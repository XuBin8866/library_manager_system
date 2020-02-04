<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<%@ page import="java.util.*"%>
<html>
<script type="text/javascript">
	function getDialog() {
		window.open("manager_add.jsp","和预期的小窗口不符","width=292,height=175,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
	}
</script>
<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp" %>
	<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：系统设置 &gt; 管理员设置 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">                      
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td height="36" align="center">管理员信息表</td>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>

  </tr>
</table>
 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="84%">&nbsp;      </td>
<td width="16%">
     
     <a href="#" onclick="getDialog()">添加管理员信息</a></td>
     
  </tr>
</table>  
  <table width="91%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="26%" bgcolor="#F9D16B">管理员名称</td>
    <td width="12%" bgcolor="#F9D16B">系统设置</td>
    <td width="12%" bgcolor="#F9D16B">读者管理</td>
    <td width="12%" bgcolor="#F9D16B">图书管理</td>
    <td width="11%" bgcolor="#F9D16B">图书借还</td>
    <td width="10%" bgcolor="#F9D16B">系统查询</td>
    <td width="9%" bgcolor="#F9D16B">权限设置</td>
    <td width="8%" bgcolor="#F9D16B">删除</td>
  </tr>
	<c:forEach items="${managers }" var="m">
	 <tr>
    <td style="padding:5px;">${m.name }</td>
    <td align="center"><input name="checkbox" type="checkbox" class="noborder" value="checkbox" disabled="disabled" <c:if test="${m.systemSet==1 }">checked="checked"</c:if> ></td>
    <td align="center"><input name="checkbox" type="checkbox" class="noborder" value="checkbox" disabled="disabled" <c:if test="${m.readerSet==1 }">checked="checked"</c:if>></td>
    <td align="center"><input name="checkbox" type="checkbox" class="noborder" value="checkbox" disabled <c:if test="${m.bookSet==1 }">checked="checked"</c:if>></td>
    <td align="center"><input name="checkbox" type="checkbox" class="noborder" value="checkbox" disabled <c:if test="${m.borrowSet==1 }">checked="checked"</c:if>></td>
    <td align="center"><input name="checkbox" type="checkbox" class="noborder" value="checkbox" disabled <c:if test="${m.systemQuery==1 }">checked="checked"</c:if>></td>
    
 		<c:if test="${manager.systemSet==1 }">
 			<td align="center">
 				<a href="#" onClick="window.open('manager?action=managerModifyQuery&id=${m.id}','','width=292,height=175')">权限设置</a>
 			</td>
 			<td align="center">
 				<a href="manager?action=managerDelete&id=${m.id }">删除</a>
 			</td>
 		</c:if>
 		<c:if test="${manager.systemSet!=1 }">
 			<td align="center">&nbsp;</td>
 			<td align="center">&nbsp;</td>
 		</c:if>
  </tr>
	
	</c:forEach>
</table></td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</body>
</html>
