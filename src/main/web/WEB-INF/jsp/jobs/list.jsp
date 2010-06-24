<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="/resources/stylesheet.css" />
<title>List Jobs</title>
</head>
<body>

<h2>Add a New Job</h2>
<form:form commandName="newJob" method="POST">
	<p>
		<form:label path="title">Title</form:label><br/>
		<form:input path="title" size="50"/>
	</p>
	
	<p>
		<form:label path="description">Description</form:label><br/>
		<form:textarea path="description" cols="50" rows="10" />
	</p>
	<p>
		<input type="submit" value="Add Job"/>
	</p>
</form:form>

<h2>Jobs List</h2>
<table>
<thead>
	<tr>
		<th>Title</th>
		<th>Date Added</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${jobs}" var="job">
<fmt:formatDate var="jobCreationDateFormatted" value="${job.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<c:url value="jobs/${job.jobId}" var="jobDetailUrl" />
<tr>
	<td><a href="${jobDetailUrl}"><c:out value="${job.title}" /></a></td>
	<td>${jobCreationDateFormatted}</td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>