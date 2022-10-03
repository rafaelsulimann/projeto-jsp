<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error 500</title>
</head>
<body>
  <h1>Error 500. Entre em contato com o suporte!</h1>
  <p><%out.print(request.getAttribute("msg"));%>
</body>
</html>