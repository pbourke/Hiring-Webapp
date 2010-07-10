<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title">Candidates</c:param>
	<c:param name="tab">candidates</c:param>
</c:import>

<h2>Add a New Candidate</h2>
<form:form commandName="newCandidate" method="POST" action="/app/candidates">
	<p>
		<form:label path="name">Name</form:label><br/>
		<form:input path="name" size="50"/>
	</p>
	<p>
		<form:label path="email">Email</form:label><br/>
		<form:input path="email" size="50"/>
	</p>	
	<p>
		<input type="submit" value="Add Candidate"/>
	</p>
</form:form>

<h2>Candidates List</h2>
<table>
<thead>
	<tr>
		<th>Name</th>
		<th>Date Added</th>
	</tr>
</thead>
<tbody>
<c:forEach items="${candidates}" var="candidate">
<fmt:formatDate var="candidateCreationDateFormatted" value="${candidate.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<tr>
	<td><a href="/app/candidates/${candidate.candidateId}"><c:out value="${candidate.name}" /></a></td>
	<td>${candidateCreationDateFormatted}</td>
</tr>
</c:forEach>
</tbody>
</table>

<c:import url="/app/layout/footer" />