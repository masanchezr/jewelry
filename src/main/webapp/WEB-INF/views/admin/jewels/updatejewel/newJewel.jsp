<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="jewels" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="updatejewel" /></li>
</ol>
<form:form action="saveJewel" modelAttribute="jewelForm">
	<form:hidden path="idjewel" />
	<div class="form-row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="form-row">
					<div class="col-lg-4">
						<div class="form-group">
							<form:select class="form-control" path="place.idplace">
								<c:choose>
									<c:when test="${placeselected==null}">
										<spring:message code="place" var="placename" />
										<form:option selected="true" value="" label="${placename}" />
									</c:when>
									<c:otherwise>
										<form:option selected="true" value="${placeselected.idplace}"
											label="${placeselected.description}" />
									</c:otherwise>
								</c:choose>
								<form:options items="${places}" itemValue="idplace"
									itemLabel="description" />
							</form:select>
						</div>
						<div class="form-group">
							<form:select class="form-control" path="metal.idmetal">
								<form:options items="${materials}" itemValue="idmetal"
									itemLabel="description" />
							</form:select>
						</div>
						<div class="form-group">
							<form:select class="form-control" path="category.idcategory">
								<form:options items="${categories}" itemValue="idcategory"
									itemLabel="namecategory" />
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="reference" var="reference" />
							<form:input class="form-control" path="reference"
								placeholder="${reference}" />
							<p class="text-danger">
								<form:errors path="reference" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="price" var="price" />
							<form:input class="form-control" path="price"
								placeholder="${price}" />
							<p class="text-danger">
								<form:errors path="price" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="jewelname" var="name" />
							<form:input class="form-control" path="name"
								placeholder="${name}" />
							<p class="text-danger">
								<form:errors path="name" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="descriptionph" var="description" />
							<form:input class="form-control" path="description"
								placeholder="${description}" />
						</div>
					</div>
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="grams" var="grams" />
							<form:input class="form-control" path="grams"
								placeholder="${grams}" />
						</div>
						<div class="form-group">
							<spring:message code="cost" var="cost" />
							<form:input class="form-control" path="cost"
								placeholder="${cost}" />
						</div>
						<div class="form-group">
							<spring:message code="idwork" var="idwork" />
							<form:input class="form-control" path="work.idworkshop"
								placeholder="${idwork}" />
						</div>
						<!--<form:button value="Examinar">
				<spring:message code="inspect" />
			</form:button>-->
						<div class="form-group">
							<spring:message code="active" />
							<form:checkbox path="active" />
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