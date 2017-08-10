<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>

<!-- Bootstrap Core CSS -->
<link
	href="<spring:url value="/resources/styles/admin/bootstrap.min.css"/>"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<spring:url value="/resources/styles/admin/metisMenu.min.css"/>"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<spring:url value="/resources/styles/admin/sb-admin-2.css"/>"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/admin/font-awesome-4.5.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">

<!-- Timeline CSS -->
<link href="<spring:url value="/resources/styles/admin/timeline.css"/>"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<spring:url value="/resources/styles/admin/morris.css"/>"
	rel="stylesheet">
<title><spring:message code="titleAdmin" /></title>
</head>
<body>
	<tiles:insertAttribute name="body" />
</body>
</html>