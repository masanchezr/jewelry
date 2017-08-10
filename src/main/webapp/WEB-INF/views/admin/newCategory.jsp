<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
	<div>
		<h3>
			<spring:message code="newcategory" />
		</h3>
	</div>
	<form:form action="saveCategory" commandName="category">
		<spring:message code="namecategory" />
		<form:input class="form-control" path="namecategory" />
		<spring:message code="active" />
		<form:checkbox path="active" />
		<form:button class="btn btn-success" value="submit">
			<spring:message code="save" />
		</form:button>
	</form:form>
</div>