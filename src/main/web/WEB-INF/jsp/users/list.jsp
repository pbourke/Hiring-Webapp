<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List Skills</title>
</head>
<body>

<ul>
	<li>Users</li>
	<li><a href="/app/jobs">Jobs</a></li>
	<li><a href="/app/users">Skills</a></li>
	<li><a href="/app/candidates">Candidates</a></li>
</ul>

<h2>Add a new User</h2>
<form:form commandName="newUser" method="POST">
	<p>
		<form:label path="name">Name</form:label><br/>
		<form:input path="name" size="50"/>
	</p>
	<p>
		<form:label path="email">Email</form:label><br/>
		<form:input path="email" size="50"/>
	</p>	
	<p>
		<form:label path="passwordPlaintext">Password</form:label><br/>
		<form:input path="passwordPlaintext" size="50"/>
	</p>	
	<p>
		<input type="submit" value="Add User"/>
	</p>
</form:form>

<h2>User List</h2>
<table>
<thead>
	<tr>
		<th>Name</th>
		<th>Email</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${users}" var="u">
<tr>
	<td><c:out value="${u.name}" /></td>
	<td><c:out value="${u.email}" /></td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>