<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchshoppings" /></li>
</ol>
<form:form action="resultPosibleRepeated" modelAttribute="searchDateForm">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel card-default">
				<div class="card-heading">
					<spring:message code="searchshoppings" />
				</div>
				<div class="card-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="datefrom" />
								<form:input class="form-control" path="datefrom" />
								<p class="text-danger">
									<form:errors path="datefrom" />
								</p>
							</div>
							<div class="form-group">
								<spring:message code="dateuntil" />
								<form:input class="form-control" path="dateuntil" />
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
	</div>
</form:form>