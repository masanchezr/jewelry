<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="holidays" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newholiday" /></li>
</ol>
<form:form action="addHoliday" modelAttribute="holiday">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="date" var="date" />
							<div id="sandbox-container">
								<form:input class="form-control" type="text" path="dateholiday"
									placeholder="${date}" />
							</div>
							<p class="text-danger">
								<form:errors path="dateholiday" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="description" var="desc" />
							<form:input class="form-control" path="description"
								placeholder="${desc}" />
							<p class="text-danger">
								<form:errors path="description" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="place" />
							<form:select class="form-control" path="place.idplace">
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>