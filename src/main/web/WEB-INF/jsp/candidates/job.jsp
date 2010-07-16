<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/app/layout/header">
	<c:param name="title"><c:out value="Candidates / ${candidate.name} for ${job.title}"/></c:param>
</c:import>

<h2><a href="/app/candidates/${candidate.candidateId}"><c:out value="${candidate.name}" /></a> for <a href="/app/jobs/${job.jobId}"><c:out value="${job.title}" /></a></h2>
<p><strong>Email</strong><br/><c:out value="${candidate.email}" /></p>
<fmt:formatDate var="candidateCreationDateFormatted" value="${candidate.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<p><strong>Created</strong><br/>${candidateCreationDateFormatted}</p>

<h3>Interviews</h3>
Create Interview:<br/>
<form action="/app/candidates/${candidate.candidateId}/jobs/${job.jobId}/interviews" method="post">
	Interviewer<br/>
	<select id="interviewerUserId" name="interviewerUserId">
	<c:forEach items="${users}" var="user">
		<option value="${user.userId}"><c:out value="${user.name}"/></option>
	</c:forEach>
	</select><br/>
	Date and Time<br/>
	<input type="text" id="startTime" name="startTime" /><br/>
	Location<br/>
	<input type="text" id="location" name="location" /><br/>
	<input type="submit" value="Add Interview" />
</form>

<h3>Interviews</h3>
<table>
  <tr>
    <th>Start</th>
    <th>Interviewer</th>
    <th>Location</th>
    <th>Skills</th>
  </tr>
<c:forEach items="${interviews}" var="interview">
  <tr>
    <td>${interview.startTime}</td>
    <td><a href="/app/candidates/${interview.candidate.candidateId}/jobs/${interview.job.jobId}/interviews/${interview.interviewId}">${interview.interviewer.name}</a></td>
    <td>${interview.location}</td>
    <td>
    	<!-- Should be able to do this w/ fn:join: -->
    	<c:forEach items="${interview.skills}" var="skill">
    		<span><c:out value="${skill.title}"/></span>
    	</c:forEach>
    </td>
  </tr>
</c:forEach>
</table>

<h3>Add Rating</h3>
<form action="/app/candidates/${candidate.candidateId}/jobs/${job.jobId}/ratings" method="post">
	Rating for <select id="skillId" name="skillId">
	<c:forEach items="${job.skills}" var="skill">
		<option value="${skill.skillId}"><c:out value="${skill.title}" /></option>
	</c:forEach>
	</select>
	is 
	<select id="ratingValue" name="ratingValue">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select>	
	of 5<br/>
	<textarea id="notes" name="notes" rows="3" cols="50"></textarea><br/>
	<input type="submit" value="Add Rating"/>
</form>

<h3>Ratings</h3>
<ul>
<c:forEach items="${ratings}" var="rating">
	<li>
		<c:out value="${rating.skill.title}" /> rating ${rating.rating} / 5 (<c:out value="${rating.user.name}" />)<p><c:out value="${rating.notes}" /></p>
	</li>
</c:forEach>
</ul>

<c:import url="/app/layout/footer" />