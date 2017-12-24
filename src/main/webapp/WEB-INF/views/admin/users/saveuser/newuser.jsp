<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="users" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="newuser" /></li>
</ol>
<form:form action="saveuser" modelAttribute="user">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="col-lg-6">
					<div class="row">
						<div class="form-group">
							<spring:message code="username" var="usermessage" />
							<form:input class="form-control" path="username"
								placeholder="${usermessage}" />
							<p class="text-danger">
								<form:errors path="username" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="password" var="passmessage" />
							<form:input class="form-control" path="password"
								placeholder="${passmessage}" />
							<p class="text-danger">
								<form:errors path="password" />
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
							<spring:message code="active" />
							<form:checkbox path="enabled" />
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>