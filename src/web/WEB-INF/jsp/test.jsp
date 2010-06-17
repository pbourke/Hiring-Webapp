<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Object List</title>
<style type="text/css">
	td.money {
		text-align: right;
	}
</style>
</head>
<body>
<table>
<thead>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Date</th>
		<th>Gross</th>
		<th>From Email</th>
		<th>Transaction Id</th>
		<th>Item Title</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${testBeans}" var="testBean">
<fmt:formatDate var="testBeanEventDateFormatted" value="${testBean.eventDate}" pattern="MM/dd/yyyy" scope="page"/>
<tr>
	<td><c:out value="${testBean.id}" /></td>
	<td><c:out value="${testBean.name}" /></td>
	<td><c:out value="${testBeanEventDateFormatted}" /></td>
	<td class="money"><c:out value="${testBean.gross}" /></td>
	<td><c:out value="${testBean.fromEmail}" /></td>
	<td><c:out value="${testBean.transactionId}"/></td>
	<td><c:out value="${testBean.itemTitle}"/></td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>