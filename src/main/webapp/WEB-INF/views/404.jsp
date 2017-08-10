<!DOCTYPE html>
<html lang="en">
<head>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<title><spring:message code="pagenotfound"/></title>
</head>
<body>
	<img src="<spring:url value="/resources/img/web/404.jpg"/>" alt="Page Not Found (404)."
		style="position: absolute; left: 50%; top: 50%; margin-left: -285px; margin-top: -190px;">
</body>
</html>