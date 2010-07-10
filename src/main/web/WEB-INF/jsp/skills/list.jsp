<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title">Skills</c:param>
	<c:param name="tab">skills</c:param>
</c:import>

<h2>Add a Skill</h2>
<form:form commandName="newSkill" method="POST">
	<p>
		<form:label path="title">Title</form:label><br/>
		<form:input path="title" size="50"/>
	</p>
	
	<p>
		<form:label path="description">Description</form:label><br/>
		<form:textarea path="description" cols="50" rows="10" />
	</p>
	<p>
		<input type="submit" value="Add Skill"/>
	</p>
</form:form>

<h2>Skills List</h2>
<table>
<thead>
	<tr>
		<th>Title</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${skills}" var="skill">
<tr>
	<td><c:out value="${skill.title}" /></td>
</tr>
</c:forEach>
</tbody>
</table>

<c:import url="/app/layout/footer" />