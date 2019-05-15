<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="numisgoldsl" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="registeremployees" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty register}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="nif" /></th>
								<th><spring:message code="name" /></th>
								<th><spring:message code="date" /></th>
								<th><spring:message code="timeinmorning" /></th>
								<th><spring:message code="timeoutmorning" /></th>
								<th><spring:message code="timeinafternoon" /></th>
								<th><spring:message code="timeoutafternoon" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${register}" var="r">
								<tr>
									<td><c:out value="${r.employee.dni}" /></td>
									<td><c:out value="${r.employee.name}" /></td>
									<td><c:out value="${r.date}" /></td>
									<td><c:out value="${r.timeinmorning}" /></td>
									<td><c:out value="${r.timeoutmorning}" /></td>
									<td><c:out value="${r.timeinafternoon}" /></td>
									<td><c:out value="${r.timeoutafternoon}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:url value="/downloadpdf" var="downloadpdf" />
							<a
								href="${downloadpdf}<c:out value="${searchDateForm.datefrom}" />and<c:out value="${searchDateForm.dateuntil}" />"><button
									class="btn btn-primary" type="button">
									<spring:message code="downloadpdf" />
								</button></a>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${empty register}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>