<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1>
	<spring:message code="ordernumber" />
	<c:out value="${orderNumber}" />
</h1>
<h2>
	<spring:message code="thanks" />
</h2>