<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchgramspawns" /></li>
</ol>
<form:form action="quarterpawns" modelAttribute="searchDateForm">
	<div class="row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="datefrom" var="datefrommessage" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="datefrom"
									placeholder="${datefrommessage}" />
							</div>
							<p class="text-danger">
								<form:errors path="datefrom" />
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
								<form:input class="form-control" type="text" path="dateuntil"
									placeholder="${dateuntilmessage}" />
							</div>
							<p class="text-danger">
								<form:errors path="dateuntil" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>