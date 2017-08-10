<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savediscount" commandName="discountForm" role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newdiscount" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="iddiscount" />
								<form:input class="form-control" path="iddiscount" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="iddiscount" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="amount" />
								<form:input class="form-control" path="discount" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="discount" /></label>
								</div>
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>