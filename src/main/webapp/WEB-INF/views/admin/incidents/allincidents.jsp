<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="incidents" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="resultsearch" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty incidents}">
				<div class="table-responsive">
					<form:form action="searchincident" commandName="incident">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th><spring:message code="number" /></th>
									<th><spring:message code="user" /></th>
									<th><spring:message code="description" /></th>
									<th><spring:message code="date" /></th>
									<th><spring:message code="state" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${incidents}" var="incident">
									<tr>
										<td><c:if test="${!incident.state}">
												<form:radiobutton path="idincident"
													value="${incident.idincident}" />
											</c:if> <c:out value="${incident.idincident}" /></td>
										<td><c:out value="${incident.user}" /></td>
										<td><c:out value="${incident.description}" /></td>
										<td><fmt:formatDate value="${incident.date}"
												pattern="yyyy-MM-dd" /></td>
										<td><c:if test="${incident.state}">
												<spring:message code="resolved" />
											</c:if> <c:if test="${!incident.state}">
												<spring:message code="pending" />
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="solve" />
						</form:button>
					</form:form>
				</div>
			</c:if>
			<c:if test="${empty incidents}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>