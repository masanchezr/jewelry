<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchmissingshoppings" /></li>
</ol>
<form:form action="resultmissingshoppings"
	modelAttribute="searchmissingshoppings">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="numfrom" />
							<form:input class="form-control" path="numfrom" />
							<p class="text-danger">
								<form:errors path="numfrom" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="numuntil" />
							<form:input class="form-control" path="numuntil" />
							<p class="text-danger">
								<form:errors path="numuntil" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="year" />
							<form:input class="form-control" path="year" />
							<p class="text-danger">
								<form:errors path="year" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="place" />
							<form:select class="form-control" path="place.idplace">
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select>
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