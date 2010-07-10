<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title">Jobs / <c:out value="${job.title}" /></c:param>
</c:import>

<h2><c:out value="${job.title}" /> (id# ${job.jobId})</h2>
<p><strong>Description</strong><br/><c:out value="${job.description}" /></p>
<fmt:formatDate var="jobCreationDateFormatted" value="${job.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<p><strong>Created</strong><br/>${jobCreationDateFormatted}</p>

<h3>Skills</h3>
<form action="/app/jobs/${job.jobId}/skills" method="post">
	<select id="skillId" name="skillId">
	<c:forEach items="${skills}" var="skill">
		<option value="${skill.skillId}"><c:out value="${skill.title}" /></option>
	</c:forEach>
	</select>
	<input type="submit" value="Add Skill"/>
</form>

<ul>
<c:forEach items="${job.skills}" var="skill">
	<li><form action="/app/jobs/${job.jobId}/skills/${skill.skillId}" method="post"><input type="hidden" id="removeSkill" name="removeSkill" value="true"/><input type="submit" value="remove" /></form>
		<c:out value="${skill.title}"/></li>
</c:forEach>
</ul>

<c:import url="/app/layout/footer" />