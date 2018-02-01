<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="newset" /></li>
</ol>
<form:form action="addset" modelAttribute="setForm">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="referencering" />
							<form:input class="form-control" path="referencering" />
							<p class="text-danger">
								<form:errors path="referencering" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="referenceearrings" />
							<form:input class="form-control" path="referenceearrings" />
							<p class="text-danger">
								<form:errors path="referenceearrings" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="referencependant" />
							<form:input class="form-control" path="referencependant" />
							<p class="text-danger">
								<form:errors path="referencependant" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="referencebrazalet" />
							<form:input class="form-control" path="referencebrazalet" />
							<p class="text-danger">
								<form:errors path="referencebrazalet" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="material" />
							<form:select class="form-control" path="material.idmaterial">
								<form:options items="${materials}" itemValue="idmaterial"
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
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="referencechoker" />
							<form:input class="form-control" path="referencechoker" />
							<p class="text-danger">
								<form:errors path="referencechoker" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="referencetiepin" />
							<form:input class="form-control" path="referencetiepin" />
							<p class="text-danger">
								<form:errors path="referencetiepin" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="referencecufflinks" />
							<form:input class="form-control" path="referencecufflinks" />
							<p class="text-danger">
								<form:errors path="referencecufflinks" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="referencediamondring" />
							<form:input class="form-control" path="referencediamondring" />
							<p class="text-danger">
								<form:errors path="referencediamondring" />
							</p>
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