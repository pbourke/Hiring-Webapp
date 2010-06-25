<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List Competences</title>
</head>
<body>

<ul>
	<li><a href="/app/jobs">List Jobs</a></li>
	<li>List Competencies</li>
</ul>

<h2>Add a New Competency</h2>
<form:form commandName="newCompetency" method="POST">
	<p>
		<form:label path="title">Title</form:label><br/>
		<form:input path="title" size="50"/>
	</p>
	
	<p>
		<form:label path="description">Description</form:label><br/>
		<form:textarea path="description" cols="50" rows="10" />
	</p>
	<p>
		<input type="submit" value="Add Competency"/>
	</p>
</form:form>

<h2>Competency List</h2>
<table>
<thead>
	<tr>
		<th>Title</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${competencies}" var="competency">
<tr>
	<td><c:out value="${competency.title}" /></td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>