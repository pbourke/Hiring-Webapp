<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>

<form action="/app/login" method="post">
	<p><h5>Email</h5><br/>
	<input name="email" id="password" type="text" /></p>
	
	<p><h5>Password</h5><br/>
	<input name="password" id="password" type="password" /></p>
	<input type="submit" value="Sign In"/>
</form>

</body>
</html>