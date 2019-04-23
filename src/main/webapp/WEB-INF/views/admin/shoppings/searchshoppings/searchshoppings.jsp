<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="shoppings" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchshoppings" /></li>
</ol>
<form:form action="resultShoppings" modelAttribute="shoppingForm"
	autocomplete="off">
	<div class="row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="numshopph" var="numshopmessage" />
							<form:input class="form-control" path="numshop"
								placeholder="${numshopmessage}" />
							<p class="text-danger">
								<form:errors path="numshop" />
							</p>
						</div>
						<div class="form-group">
							<form:select class="form-control" path="place.idplace">
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="datefrom" var="messagedatefrom" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="datefrom"
									placeholder="${messagedatefrom}" />
							</div>
							<p class="text-danger">
								<form:errors path="datefrom" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="dateuntil" var="messagedateuntil" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="dateuntil"
									placeholder="${messagedateuntil}" />
							</div>
							<p class="text-danger">
								<form:errors path="dateuntil" />
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