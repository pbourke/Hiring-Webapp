<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
<c:when test="${userRequestContext.loggedIn}">
	<p><strong>Logged in as <c:out value="${userRequestContext.userEmail}" /></strong> | <a href="/j_spring_security_logout" >Sign Out</a></p>
</c:when>
<c:otherwise>
	<p><strong>Not logged in</strong> | <a href="/app/login">Sign In</a></p>
</c:otherwise>
</c:choose>
