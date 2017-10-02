<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchjewels" /></li>
</ol>
<form:form action="resultSearchRevise" commandName="jewelForm">
	<div class="row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="reference" />
							<form:input class="form-control" path="reference" />
							<form:errors path="reference" />
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="search" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>