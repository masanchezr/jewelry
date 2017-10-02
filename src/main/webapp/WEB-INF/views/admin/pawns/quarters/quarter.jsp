<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="pawns" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="summary" /></li>
	<li class="breadcrumb-item active"><c:out
			value="${searchForm.place.description}" /></li>
	<li class="breadcrumb-item active"><c:out
			value="${searchForm.datefrom}" /></li>
	<li class="breadcrumb-item active"><c:out
			value="${searchForm.dateuntil}" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="gold" /></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><spring:message code="realgrams" /></td>
							<td><c:out value="${quarter.gramsreal}" /></td>
						</tr>
						<tr>
							<td><spring:message code="grossgrams" /></td>
							<td><c:out value="${quarter.grossgrams}" /></td>
						</tr>
						<tr>
							<td><spring:message code="amount" /></td>
							<td><c:out value="${quarter.amount}" /><i
								class="fa fa-euro"></i></td>
						</tr>
						<tr>
							<td><spring:message code="average" /></td>
							<td><c:out value="${quarter.averagegold}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panel card-default">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="metalag" /></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><spring:message code="grams" /></td>
							<td><c:out value="${quarter.gramsAg}" /></td>
						</tr>
						<tr>
							<td><spring:message code="amount" /></td>
							<td><c:out value="${quarter.amountag}" /><i
								class="fa fa-euro"></i></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>