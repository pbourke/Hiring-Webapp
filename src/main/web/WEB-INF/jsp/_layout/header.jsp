<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><c:out value="${param.title}" /></title>
</head>
<body>

<c:import url="/app/login/status" />

<ul>
	<li><c:choose><c:when test="${param.tab ne 'users'}"><a href="/app/users">Users</a></c:when><c:otherwise>Users</c:otherwise></c:choose></li>
	<li><c:choose><c:when test="${param.tab ne 'jobs'}"><a href="/app/jobs">Jobs</a></c:when><c:otherwise>Jobs</c:otherwise></c:choose></li>
	<li><c:choose><c:when test="${param.tab ne 'skills'}"><a href="/app/skills">Skills</a></c:when><c:otherwise>Skills</c:otherwise></c:choose></li>
	<li><c:choose><c:when test="${param.tab ne 'candidates'}"><a href="/app/candidates">Candidates</a></c:when><c:otherwise>Candidates</c:otherwise></c:choose></li>
</ul>
