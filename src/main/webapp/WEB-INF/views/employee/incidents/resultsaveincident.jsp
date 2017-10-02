<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="incidents" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="saved" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="number" /></th>
							<th><spring:message code="description" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${incident.idincident}" /></td>
							<td><c:out value="${incident.description}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>