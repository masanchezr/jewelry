<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="downloadexcel" commandName="searchForm">
	<div class="row">
		<div class="col-lg-7">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="generatexls" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<spring:message code="generatexls" />
							</div>
							<div class="form-group">
								<spring:message code="datefrom" var="from" />
								<div id="sandbox-container">
									<div class="input-daterange input-group" id="datepicker">
										<form:input type="text" class="input-sm form-control"
											path="datefrom" placeholder="${from}" name="start" />
										<span class="input-group-addon"><spring:message
												code="until" /></span>
										<form:input type="text" class="input-sm form-control"
											path="dateuntil" name="end" />
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
		</div>
	</div>
</form:form>