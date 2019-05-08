<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8"%>
<form:form action="savediscount" modelAttribute="discountForm"
	role="form">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="sales" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="newdiscount" /></li>
	</ol>
	<div class="form-row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="form-row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="iddiscountph" var="numdiscount" />
							<form:input class="form-control" path="iddiscount"
								placeholder="${numdiscount}" />
							<div class="form-group">
								<label class="control-label" for="inputSuccess"><form:errors
										path="iddiscount" /></label>
							</div>
						</div>
						<div class="form-group">
							<spring:message code="amount" var="holderamount" />
							<form:input class="form-control" path="sdiscount"
								placeholder="${holderamount}" />
							<div class="form-group">
								<label class="control-label" for="inputSuccess"><form:errors
										path="sdiscount" /></label>
							</div>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>