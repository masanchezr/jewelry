<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
	<form:form action="resultsearchbyreference" commandName="jewelForm">
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="searchjewels" />
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<spring:message code="category" />
									<form:select class="form-control" path="category.idcategory">
										<form:options items="${categories}" itemValue="idcategory"
											itemLabel="namecategory" />
									</form:select>
								</div>
								<div class="form-group">
									<spring:message code="reference" />
									<form:input class="form-control" path="reference" />
									<div class="form-group has-error">
										<label class="control-label" for="inputSuccess"> <form:errors
												path="reference" /></label>
									</div>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<spring:message code="place" />
									<form:select class="form-control" path="place.idplace">
										<form:options items="${places}" itemValue="idplace"
											itemLabel="description" />
									</form:select>
								</div>
								<div class="form-group">
									<spring:message code="metal" />
									<form:select class="form-control" path="metal.idmetal">
										<form:options items="${metals}" itemValue="idmetal"
											itemLabel="description" />
									</form:select>
								</div>
								<div class="form-group">
									<form:button class="btn btn-success" value="submit">
										<spring:message code="search" />
									</form:button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>