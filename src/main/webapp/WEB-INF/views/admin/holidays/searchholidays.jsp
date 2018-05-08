<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="holidays" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchholidays" /></li>
</ol>
<form:form action="resultHolidays" modelAttribute="holiday">
	<div class="row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="datefrom" var="datefrommessage" />
							<div id="sandbox-container">
								<form:input class="form-control" path="dateholiday"
									placeholder="${datefrommessage}" />
							</div>
							<p class="text-danger">
								<form:errors path="dateholiday" />
							</p>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="search" />
							</form:button>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="dateuntil" var="dateuntilmessage" />
							<div id="sandbox-container">
								<form:input class="form-control" path="untildate"
									placeholder="${dateuntilmessage}" />
							</div>
							<p class="text-danger">
								<form:errors path="untildate" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>