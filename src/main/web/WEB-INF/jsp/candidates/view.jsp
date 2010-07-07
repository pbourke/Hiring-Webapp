<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View Candidate ${candidate.candidateId}</title>
</head>
<body>

<ul>
	<li><a href="/app/users">Users</a></li>
	<li><a href="/app/jobs">Jobs</a></li>
	<li><a href="/app/skills">Skills</a></li>
	<li><a href="/app/candidates">Candidates</a></li>	
</ul>

<h2><c:out value="${candidate.name}" /></h2>
<p><strong>Email</strong><br/><c:out value="${candidate.email}" /></p>
<fmt:formatDate var="candidateCreationDateFormatted" value="${candidate.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<p><strong>Created</strong><br/>${candidateCreationDateFormatted}</p>

<h3>Jobs</h3>
<form action="/app/candidates/${candidate.candidateId}/jobs" method="post">
	<select id="jobId" name="jobId">
	<c:forEach items="${jobs}" var="job">
		<option value="${job.jobId}"><c:out value="${job.title}" /></option>
	</c:forEach>
	</select>
	<input type="submit" value="Assign Candidate to Job"/>
</form>

<ul>
<c:forEach items="${candidate.jobs}" var="job">
	<li>
		<a href="/app/candidates/${candidate.candidateId}/jobs/${job.jobId}"><c:out value="${job.title}" /></a>
	</li>
</c:forEach>
</ul>
</body>
</html>