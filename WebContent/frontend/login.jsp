<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<jsp:directive.include file="header.jsp"/>
<div align="center">
<h3>Please login:</h3>

<form action="">
Email:<input type="text" size="10"><br/>
Password:<input typ="text" size="10"><br/>
<input type="submit" value="login">
</form>
</div>
<jsp:directive.include file="footer.jsp" />

</body>
</html>