<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title">Users</c:param>
	<c:param name="tab">users</c:param>
</c:import>

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

<c:import url="/app/layout/footer" />