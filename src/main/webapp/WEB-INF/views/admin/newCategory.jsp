<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="categories" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newcategory" /></li>
</ol>
<form:form action="saveCategory" commandName="category">
	<div class="form-group">
		<spring:message code="namecategory" />
		<form:input class="form-control" path="namecategory" />
	</div>
	<div class="form-group">
		<spring:message code="active" />
		<form:checkbox path="active" />
	</div>
	<div class="form-group">
		<form:button class="btn btn-primary" value="submit">
			<spring:message code="save" />
		</form:button>
	</div>
</form:form>