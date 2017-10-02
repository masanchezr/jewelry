<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item active"><spring:message
			code="searchentries" /></li>
</ol>
<form:form action="resultentries" commandName="adminSearchForm">
	<div class="row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="datefrom" var="from" />
							<div id="sandbox-container">
								<form:input class="form-control" path="datefrom"
									placeholder="${from}" />
							</div>
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"><form:errors
										path="datefrom" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="dateuntil" var="until" />
							<div id="sandbox-container">
								<form:input class="form-control" path="dateuntil"
									placeholder="${until}" />
							</div>
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