<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="updatejewels" /></li>
</ol>
<form:form action="resultSearchUpdateJewels" modelAttribute="jewelForm">
	<div class="form-row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="form-group">
					<spring:message code="reference" />
					<form:input class="form-control" path="reference" />
				</div>
				<div class="form-group">
					<spring:message code="category" />
					<form:select class="form-control" path="category.idcategory">
						<form:options items="${categories}" itemValue="idcategory"
							itemLabel="namecategory" />
					</form:select>
				</div>
				<form:button class="btn btn-primary" value="submit">
					<spring:message code="search" />
				</form:button>
			</div>
		</div>
	</div>
</form:form>