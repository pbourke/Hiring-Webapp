<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<thead>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Date</th>
		<th>Gross</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${testBeans}" var="testBean">
<tr>
	<td><c:out value="${testBean.id}" /></td>
	<td><c:out value="${testBean.name}" /></td>
	<td><c:out value="${testBean.eventDate}" /></td>
	<td><c:out value="${testBean.gross}" /></td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>