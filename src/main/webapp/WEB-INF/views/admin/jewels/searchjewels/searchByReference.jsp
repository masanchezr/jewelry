<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="searchjewels" /></li>
</ol>
<form:form action="resultsearchbyreference" modelAttribute="jewelForm">
	<div class="row">
		<div class="col-lg-6">
			<div class="card-body">
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
							<p class="text-danger">
								<form:errors path="reference" />
							</p>
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
							<spring:message code="material" />
							<form:select class="form-control" path="metal.idmetal">
								<form:options items="${materials}" itemValue="idmetal"
									itemLabel="description" />
							</form:select>
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