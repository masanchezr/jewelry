<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="resultSales" commandName="searchForm">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="sales" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="datefrom" var="messagedatefrom" />
								<div id="sandbox-content">
									<form:input class="form-control" type="text" path="datefrom"
										placeholder="${messagedatefrom}" />
								</div>
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="datefrom" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="dateuntil" var="messagedateuntil" />
								<div id="sandbox-content">
									<form:input class="form-control" type="text" path="dateuntil"
										placeholder="${messagedateuntil}" />
								</div>
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="dateuntil" /></label>
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