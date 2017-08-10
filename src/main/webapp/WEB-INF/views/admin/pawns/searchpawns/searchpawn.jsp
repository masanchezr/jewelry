<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container">
	<form:form action="resultPawns" commandName="pawnForm">
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="searchpawns" />
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<spring:message code="numpawn" />
									<form:input class="form-control" path="numpawn" />
									<div class="form-group has-error">
										<label class="control-label" for="inputSuccess"><form:errors
												path="numpawn" /></label>
									</div>
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