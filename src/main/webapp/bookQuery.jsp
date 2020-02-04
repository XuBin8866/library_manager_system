<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*"%>
<html>

<head>
<title>图书馆管理系统</title>
<link href="CSS/style.css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock)">
<%@include file="banner.jsp"%>
<%@include file="navigation.jsp"%>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" bgcolor="#FFFFFF"><table width="99%" height="510"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="tableBorder_gray">
  <tr>
    <td height="510" valign="top" style="padding:5px;"><table width="98%" height="487"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="22" valign="top" class="word_orange">当前位置：系统查询 &gt; 图书档案查询 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">
	<form action="book?action=bookQuery" method="post" name="form1">  
 <table width="98%" height="38"  border="0" cellpadding="0" cellspacing="0" bgcolor="#E3F4F7" class="tableBorder_gray">
  <tr>
    <td align="center" bgcolor="#F9D16B">

&nbsp;<img src="Images/search.gif" width="45" height="28"></td>
    <td bgcolor="#F9D16B">请选择查询依据：
      <select name="f" class="wenbenkuang" id="f">
        <option value="book_name" <c:if test="${func=='book_name' }">selected="selected"</c:if>>书名</option>
        <option value="barcode" <c:if test="${func=='barcode' }">selected="selected"</c:if>>条形码</option>
        <option value="type_name" <c:if test="${func=='type_name' }">selected="selected"</c:if>>类别</option> 
        <option value="author" <c:if test="${func=='author' }">selected="selected"</c:if>>作者</option>
        <option value="publish_name" <c:if test="${func=='publish_name' }">selected="selected"</c:if>>出版社</option>
        <option value="bookcase_name" <c:if test="${func=='bookcase_name' }">selected="selected"</c:if>>书架</option>
                  </select>
      <input name="key" type="text" id="key" size="50" value="${value }">
      <input name="Submit" type="submit" class="btn_grey" value="查询"></td>
  </tr>
</table>
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <c:if test="${fflag=='no' }">
              <td height="36" align="center">暂无图书信息！</td>
              </c:if>
            </tr>
          </table> 
  <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="17%" bgcolor="#F9D16B">条形码</td>  
    <td width="31%" bgcolor="#F9D16B">图书名称</td>
    <td width="18%" bgcolor="#F9D16B">图书类型</td>
    <td width="19%" bgcolor="#F9D16B">出版社</td>
    <td width="15%" bgcolor="#F9D16B">书架</td>
    </tr>
  <c:forEach items="${bfs }" var="b">
  <tr>
    <td style="padding:5px;">&nbsp;${b.bookBarcode }</td>  
    <td style="padding:5px;"><a href="book?action=bookDetail&id=${b.id }">${b.bookName }</a></td>
    <td style="padding:5px;">&nbsp;${b.bookTypeName }</td>  
    <td style="padding:5px;">&nbsp;${b.publishName }</td>  
    <td style="padding:5px;">&nbsp;${b.bookcaseName }</td>  
    </tr>
 </c:forEach>
</table>
	</form>
</td>
      </tr>
    </table>
</td>
  </tr>
</table><%@ include file="copyright.jsp"%></td>
  </tr>
</table>
</body>
</html>
