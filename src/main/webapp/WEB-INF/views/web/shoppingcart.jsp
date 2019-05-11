<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
	<form:form modelAttribute="dataForm" action="comprar">
		<c:if test="${not empty cart}">
			<div class="form-row">
				<div class="col-lg-12">
					<div class="panel card-default">
						<div class="card-heading">
							<spring:message code="shoppingcart" />
						</div>
						<!-- /.card-heading -->
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th><spring:message code="category" /></th>
											<th><spring:message code="nameclient" /></th>
											<th><spring:message code="price" /></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<spring:url value="/eliminar" var="delete" />
										<c:forEach items="${cart}" var="jewel" varStatus="status">
											<tr>
												<td><c:out value="${jewel.category.namecategory}" /></td>
												<td><c:out value="${jewel.name}" /></td>
												<td><c:out value="${jewel.price}" /></td>
												<td><form:hidden path="jewels[${status.index}].idjewel" /><a
													href="${delete}<c:out value="${jewel.idjewel}" />"><button>
															<spring:message code="delete" />
														</button></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="form-row">
									<div class="col-lg-12">
										<div class="col-lg-3">
											<div class="form-group">
												<label class="control-label" for="shipping_name"><spring:message
														code="name" /><span>*</span></label>
												<form:input class="form-control" id="shipping_name"
													name="shipping_name" path="name" />
												<form:errors path="name" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="control-label" for="shipping_nif"><spring:message
														code="nif" /><span>*</span></label>
												<form:input path="nif" class="form-control"
													id="shipping_nif" name="shipping_nif" />
												<form:errors path="nif" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="control-label" for="shipping_email"><spring:message
														code="email" /></label>
												<form:input class="form-control" path="email"
													id="shipping_email" name="shipping_email" />
												<form:errors path="email" />
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<label class="control-label" for="shipping_telephone"><spring:message
														code="telephone" /></label>
												<form:input class="form-control" path="telephone"
													id="shipping_telephone" name="shipping_telephone" />
												<form:errors path="telephone" />
											</div>
										</div>
									</div>
								</div>
								<div class="form-row">
									<div class="col-lg-12">
										<div class="form-group">
											<form:button class="btn btn-default" value="submit">
												<spring:message code="continue" />
											</form:button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${empty cart}">
			<h3>
				<spring:message code="emptycart" />
			</h3>
		</c:if>
	</form:form>
</div>