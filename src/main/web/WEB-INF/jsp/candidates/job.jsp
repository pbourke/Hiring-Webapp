<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>${candidate.name} for ${job.title}</title>
</head>
<body>

<ul>
	<li><a href="/app/jobs">Jobs</a></li>
	<li><a href="/app/skills">Skills</a></li>
	<li><a href="/app/candidates">Candidates</a></li>	
</ul>

<h2><a href="/app/candidates/${candidate.candidateId}"><c:out value="${candidate.name}" /></a> for <a href="/app/jobs/${job.jobId}"><c:out value="${job.title}" /></a></h2>
<p><strong>Email</strong><br/><c:out value="${candidate.email}" /></p>
<fmt:formatDate var="candidateCreationDateFormatted" value="${candidate.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<p><strong>Created</strong><br/>${candidateCreationDateFormatted}</p>

<!--
<h3>Ratings</h3>
<form action="/app/candidates/${candidate.candidateId}/jobs" method="post">
	<select id="jobId" name="jobId">
	<c:forEach items="${jobs}" var="job">
		<option value="${job.jobId}"><c:out value="${job.title}" /></option>
	</c:forEach>
	</select>
	<input type="submit" value="Assign Candidate to Job"/>
</form>
-->

<h3>Ratings</h3>
<ul>
<c:forEach items="${ratings}" var="rating">
	<li>
		<c:out value="${rating.skill.title}" /> rating ${rating.rating} / 5
	</li>
</c:forEach>
</ul>

</body>
</html>