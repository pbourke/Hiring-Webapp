<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title"><c:out value="${interview.candidate.name}" /> interviewed by <c:out value="${interview.interviewer.name}"/> for <c:out value="${interview.job.title}" /></c:param>
</c:import>

<h3>Interview Details</h3>

<ul>
	<li>Date: ${interview.startTime}</li>
	<li>Location: ${interview.location}</li>
	<li>Candidate: <a href="/app/candidates/${interview.candidate.candidateId}"><c:out value="${interview.candidate.name}"/></a></li>
	<li>Interviewer: <c:out value="${interview.interviewer.name}"/></li>
	<li>Job: <a href="/app/jobs/${interview.job.jobId}"><c:out value="${interview.job.title}"/></a></li>
</ul>

<h3>Skills</h3>
<form action="/app/candidates/${interview.candidate.candidateId}/jobs/${interview.job.jobId}/interviews/${interview.interviewId}/skills" method="post">
	<select id="skillId" name="skillId">
	<c:forEach items="${skills}" var="skill">
		<option value="${skill.skillId}"><c:out value="${skill.title}" /></option>
	</c:forEach>
	</select>
	<input type="submit" value="Add Skill"/>
</form>

<ul>
<c:forEach items="${interview.skills}" var="skill">
	<li><form action="/app/candidates/${interview.candidate.candidateId}/jobs/${interview.job.jobId}/interviews/${interview.interviewId}/skills/${skill.skillId}" method="post"><input type="hidden" id="removeSkill" name="removeSkill" value="true"/><input type="submit" value="remove" /></form>
		<c:out value="${skill.title}"/></li>
</c:forEach>
</ul>

<c:import url="/app/layout/footer" />