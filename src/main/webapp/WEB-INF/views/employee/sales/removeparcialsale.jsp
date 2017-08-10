<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="cancelparcialsale" commandName="saleForm" role="form">
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="removeparcialsale" />
				</div>
				<div class="panel-body">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="idsale" />
							<form:input class="form-control" path="numsale" />
							<div class="form-group has-error">
								<label class="control-label" for="inputSuccess"> <form:errors
										path="numsale" /></label>
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