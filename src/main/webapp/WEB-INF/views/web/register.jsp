<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<body>
	<form:form action="/registrado">
		<spring:message code="dni" />
		<form:input class="form-control" path="dni" />
		<form:errors path="dni" />
		<spring:message code="name" />
		<form:input class="form-control" path="name" />
		<form:errors path="name" />
		<br />
		<spring:message code="surname" />
		<form:input class="form-control" path="surname" />
		<form:errors path="surname" />
		<br />
		<spring:message code="email" />
		<form:input class="form-control" path="email" />
		<form:errors path="email" />
		<br />
		<spring:message code="user" />
		<form:input class="form-control" path="userName" />
		<form:errors path="userName" />
		<spring:message code="password" />
		<form:input class="form-control" path="password" />
		<form:errors path="password" />
		<br />
		<br />
		<input type="submit" value="Registrar Usuario" />
	</form:form>
</body>
</html>