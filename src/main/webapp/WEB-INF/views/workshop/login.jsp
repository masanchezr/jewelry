<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="login" />
					</h3>
				</div>
				<div class="panel-body">
					<form role="form" name='f'
						action='<c:url value='jspringsecuritycheckws' />' method='POST'>
						<c:if test="${param.error != null}">
							<p>
								<spring:message code="invaliduser" />
							</p>
						</c:if>
						<c:if test="${param.logout != null}">
							<p>
								<spring:message code="sessionout" />
							</p>
						</c:if>
						<fieldset>
							<div class="form-group">
								<input class="form-control" type='text' name='username' value=''
									autofocus>
							</div>
							<div class="form-group">
								<input class="form-control" type='password' name='password' />
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> <input
								class="btn btn-lg btn-success btn-block" name="submit"
								type="submit" />
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>