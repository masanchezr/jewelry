<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="cancelparcialsale" modelAttribute="saleForm"
	role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="removeparcialsale" /></li>
	</ol>
	<div class="form-row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="form-group">
					<spring:message code="numberofsaleph" var="numberofsale" />
					<form:input class="form-control" path="numsale"
						placeholder="${numberofsale}" />
					<div class="form-group has-error">
						<label class="control-label" for="inputSuccess"> <form:errors
								path="numsale" /></label>
					</div>
					<div class="form-group">
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="search" />
						</form:button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>