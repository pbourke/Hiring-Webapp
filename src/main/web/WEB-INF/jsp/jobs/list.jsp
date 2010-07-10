<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title">Jobs</c:param>
	<c:param name="tab">jobs</c:param>
</c:import>

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

<c:import url="/app/layout/footer" />