<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="salespostponed" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchsalepostponed" /></li>
</ol>
<form:form action="resultsalepostponed" modelAttribute="saleForm"
	role="form">
	<div class="form-row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="form-row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="numberofsaleph" var="number" />
							<form:input class="form-control" path="idsalepostponed"
								placeholder="${number}" />
							<p class="text-danger">
								<form:errors path="idsalepostponed" />
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