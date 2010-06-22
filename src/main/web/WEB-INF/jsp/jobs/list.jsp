<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List Jobs</title>
</head>
<body>

<form:form commandName="newJob" method="POST">
	Title: <form:input path="title"/><br/>
	Description: <form:input path="description"/><br/>
	<input type="submit" />
</form:form>

<table>
<thead>
	<tr>
		<th>Id</th>
		<th>Title</th>
		<th>Date Added</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${jobs}" var="job">
<fmt:formatDate var="jobCreationDateFormatted" value="${job.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<tr>
	<td>${job.jobId}</td>
	<td><c:out value="${job.title}" /></td>
	<td>${jobCreationDateFormatted}</td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>