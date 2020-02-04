<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<html>
<script language="javascript">
     alert("图书借阅成功!");

	window.location.href="borrow?action=bookborrow&barcode=<%=request.getAttribute("bar")%>";
</script>
</html>