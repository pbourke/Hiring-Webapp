<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View Job ${job.jobId}</title>
</head>
<body>

<ul>
	<li><a href="/app/jobs">Jobs</a></li>
	<li><a href="/app/skills">Skills</a></li>
</ul>

<h2><c:out value="${job.title}" /> (id# ${job.jobId})</h2>
<p><strong>Description</strong><br/><c:out value="${job.description}" /></p>
<fmt:formatDate var="jobCreationDateFormatted" value="${job.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<p><strong>Created</strong><br/>${jobCreationDateFormatted}</p>

<h3>Skills</h3>
<form action="/app/jobs/${job.jobId}/skills" method="post">
	<select id="skillId" name="skillId">
	<c:forEach items="${skills}" var="skill">
		<option value="${skill.competencyId}"><c:out value="${skill.title}" /></option>
	</c:forEach>
	</select>
	<input type="submit" value="Add Skill"/>
</form>

<ul>
<c:forEach items="${job.skills}" var="skill">
	<li><c:out value="${skill.title}"/></li>
</c:forEach>
</ul>
</body>
</html>