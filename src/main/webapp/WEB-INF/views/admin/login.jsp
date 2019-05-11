<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page session="false"%>
<div class="container">
	<div class="card card-login mx-auto mt-5">
		<div class="card-header">
			<spring:message code="login" />
		</div>
		<div class="card-body">
			<form role="form" name='f' action='<c:url value='/login' />'
				method='POST'>
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
				<div class="form-group">
					<label for="exampleInputEmail1"><spring:message code="user" /></label>
					<spring:message code="enteruser" var="enteruser" />
					<input id="exampleInputEmail1" placeholder="${enteruser}"
						class="form-control" type='text' name='username' value=''
						autofocus>
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1"><spring:message
							code="password" /></label>
					<spring:message code="enterpassword" var="enterpass" />
					<input class="form-control" id="exampleInputPassword1"
						type="password" placeholder="${enterpass}" name='password' />
				</div>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="btn btn-primary btn-block"
					name="submit" type="submit" />
			</form>
		</div>
	</div>
</div>