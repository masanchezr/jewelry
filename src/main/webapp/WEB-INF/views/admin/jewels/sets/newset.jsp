<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="addset" commandName="setForm">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="newset" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="referencering" />
								<form:input class="form-control" path="referencering" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="referencering" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="referenceearrings" />
								<form:input class="form-control" path="referenceearrings" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="referenceearrings" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="referencependant" />
								<form:input class="form-control" path="referencependant" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="referencependant" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="referencebrazalet" />
								<form:input class="form-control" path="referencebrazalet" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="referencebrazalet" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="metal" />
								<form:select class="form-control" path="metal.idmetal">
									<form:options items="${metals}" itemValue="idmetal"
										itemLabel="description" />
								</form:select>
							</div>
							<div class="form-group">
								<spring:message code="place" />
								<form:select class="form-control" path="place.idplace">
									<form:options items="${places}" itemValue="idplace"
										itemLabel="description" />
								</form:select>
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="referencechoker" />
								<form:input class="form-control" path="referencechoker" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="referencechoker" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="referencetiepin" />
								<form:input class="form-control" path="referencetiepin" />
								<form:errors path="referencetiepin" />
							</div>
							<div class="form-group">
								<spring:message code="referencecufflinks" />
								<form:input class="form-control" path="referencecufflinks" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="referencecufflinks" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="referencediamondring" />
								<form:input class="form-control" path="referencediamondring" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="referencediamondring" /></label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>