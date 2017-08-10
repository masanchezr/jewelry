<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="enabledisableuser" />
			</div>
			<div class="panel-body">
				<div class="row">
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
</div>