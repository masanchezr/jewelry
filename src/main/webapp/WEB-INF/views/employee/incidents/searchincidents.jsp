<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="incidents" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchincidents" /></li>
</ol>
<form:form action="resultIncidents" modelAttribute="searchForm">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group col-3">
							<div id="sandbox-container">
								<spring:message code="datefrom" />
								<form:input class="form-control" path="datefrom" type="text" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="datefrom" /></label>
								</div>
							</div>
						</div>
						<div class="form-group col-3">
							<div id="sandbox-container">
								<spring:message code="dateuntil" />
								<form:input class="form-control" path="dateuntil" type="text" />
								<div class="form-group col-3 has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="dateuntil" /></label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group col-3">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="search" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>