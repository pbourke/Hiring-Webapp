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
<c:url value="/app/jobs" var="jobsListUrl" />
<p><a href="${jobsListUrl}">Jobs List</a></p>

<fmt:formatDate var="jobCreationDateFormatted" value="${job.creationDate}" pattern="MM/dd/yyyy" scope="page"/>
<p><strong>Id</strong><br/>${job.jobId}</p>
<p><strong>Title</strong><br/><c:out value="${job.title}" /></p>
<p><strong>Description</strong><br/><c:out value="${job.description}" /></p>
<p><strong>Created</strong><br/>${jobCreationDateFormatted}</p>

</body>
</html>