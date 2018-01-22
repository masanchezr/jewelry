<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="adjustments" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchadjustment" /></li>
</ol>
<form:form action="resultadjustment" modelAttribute="adjustment">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="number" var="number" />
							<form:input class="form-control" path="idadjustment"
								placeholder="${number}" />
							<p class="text-danger">
								<form:errors path="idadjustment" />
							</p>
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
	</div>
</form:form>