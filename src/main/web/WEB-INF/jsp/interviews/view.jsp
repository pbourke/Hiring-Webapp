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
	<li>Candidate: <c:out value="${interview.candidate.name}"/></li>
	<li>Job: <c:out value="${interview.job.title}"/></li>
</ul>

<h3>Skills</h3>

<c:import url="/app/layout/footer" />