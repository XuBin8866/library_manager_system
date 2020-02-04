<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

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
        <td height="22" valign="top" class="word_orange">当前位置：系统查询 &gt; 借阅到期提醒  &gt;&gt;&gt;</td>
      </tr>
      <tr>
        <td align="center" valign="top">
          <table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
            <tr>
              <c:if test="${fflag=='no' }">
              <td height="36" align="center">暂无到期提醒信息！</td>
              </c:if>
            </tr>
          </table>

          <table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
  <tr align="center" bgcolor="#e3F4F7">
    <td width="15%" bgcolor="#F9D16B">图书条形码</td>
    <td width="28%" bgcolor="#F9D16B">图书名称</td>
    <td width="17%" bgcolor="#F9D16B">读者条形码</td>
    <td width="9%" bgcolor="#F9D16B">读者名称</td>
    <td width="15%" bgcolor="#F9D16B">借阅时间</td>
    <td width="16%" bgcolor="#F9D16B">应还时间</td>
    </tr>
<c:forEach items="${bbfs }" var="bb">
  <tr>
    <td style="padding:5px;">&nbsp;${bb.bookBarcode }</td>
    <td style="padding:5px;">${bb.bookName }</td>
    <td style="padding:5px;">&nbsp;${bb.readerBarcode }</td>
    <td style="padding:5px;">&nbsp;${bb.readerName }</td>
    <td style="padding:5px;">&nbsp;${bb.borrowTime }</td>
    <td style="padding:5px;">&nbsp;${bb.returnTime }</td>
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
