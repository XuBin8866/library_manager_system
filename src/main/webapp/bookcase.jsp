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
        <td height="22" valign="top" class="word_orange">当前位置：系统设置 &gt; 书架设置 &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
            <c:if test="${fflag=='no' }">
              <td height="36" align="center">暂无书架信息！</td>
              </c:if>
              <c:if test="${fflag=='yes' }">
              <td height="36" align="center">书架信息</td>
              </c:if>
            </tr>
          </table>
          <table width="100%"  border="0" cellspacing="0" cellpadding="0">
</table>
 <table width="91%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="84%">&nbsp;      </td>
<td width="16%" align="right">
      <a href="#" onClick="window.open('bookcase_add.jsp','','width=292,height=175')">添加书架信息</a> </td>	  
  </tr>
</table>  
  <table width="91%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="70%" bgcolor="#F9D16B">书架名称</td>
    <td width="16%" bgcolor="#F9D16B">修改</td>
    <td width="14%" bgcolor="#F9D16B">删除</td>
  </tr>
  
  <c:forEach items="${bcfs }" var="bc">
  <input name="id" value="${bc.id }" type="hidden"/>
  <tr>
    <td style="padding:5px;">${bc.name }</td>
    <td align="center"><a href="#" onClick="window.open('bookCase?action=bookCaseModifyQuery&id=${bc.id }&name=${bc.name }','','width=292,height=175')">修改</a></td>
    <td align="center"><a href="bookCase?action=bookCaseDel&id=${bc.id }">删除</a></td>
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
