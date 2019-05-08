<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="generatexls" /></li>
</ol>
<form:form action="downloadexcel" modelAttribute="searchDateForm"
	autocomplete="off">
	<div class="form-row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="form-group">
					<spring:message code="datefrom" var="from" />
					<div id="sandbox-container">
						<div class="input-daterange input-group" id="datepicker">
							<form:input type="text" class="input-sm form-control"
								path="datefrom" placeholder="${from}" name="start" />
							<span class="input-group-addon"><spring:message
									code="until" /></span>
							<form:input type="text" class="input-sm form-control"
								path="dateuntil" name="end" />
						</div>
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
</form:form>