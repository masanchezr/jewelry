<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="users" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="enabledisableuser" /></li>
</ol>
<div class="form-row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="form-row">
				<div class="col-lg-6">
					<div class="form-group">
						<spring:message code="user" />
						<c:out value="${user.username}" />
						<c:out value="${user.enabled}" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>