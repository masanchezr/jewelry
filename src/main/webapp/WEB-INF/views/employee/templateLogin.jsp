<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link
	href="<spring:url value="/resources/styles/admin/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/admin/sb-admin.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/admin/font-awesome-4.5.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<title><spring:message code="titleAdmin" /></title>
</head>
<body class="bg-dark">
	<tiles:insertAttribute name="body" />
</body>
</html>