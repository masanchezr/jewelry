<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="users" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="enabledisableuser" /></li>
</ol>
<form:form action="resultenabledisableuser" modelAttribute="user">
	<div class="form-row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="form-row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="user" />
							<form:input class="form-control" path="username" />
							<form:errors path="username" />
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="change" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>