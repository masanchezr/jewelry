<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="resultHolidays" commandName="holiday">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="searchholidays" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="datefrom" />
								<form:input class="form-control" path="holiday" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="holiday" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="dateuntil" />
								<form:input class="form-control" path="untildate" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"> <form:errors
											path="untildate" /></label>
								</div>
							</div>
						</div>
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
</form:form>