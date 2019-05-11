<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/admin/sb-admin.css"/>"
	rel="stylesheet">
<title><spring:message code="titleAdmin" /></title>
</head>
<body class="bg-dark">
	<tiles:insertAttribute name="body" />
	<!-- Bootstrap core JavaScript-->
	<script src="/resources/js/jquery.min.js"></script>
	<script src="/resources/js/js/bootstrap.bundle.min.js"></script>
	<!-- Core plugin JavaScript-->
	<script src="/resources/js/jquery.easing.min.js"></script>
</body>
</html>