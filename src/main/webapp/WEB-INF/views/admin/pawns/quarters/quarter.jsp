<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			<spring:message code="summary" />
			<spring:message code="pawns" />
			<c:out value="${searchForm.place.description}" />
			<c:out value="${searchForm.datefrom}" />
			<c:out value="${searchForm.dateuntil}" />
		</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-body">
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
		<div class="panel panel-default">
			<div class="panel-body">
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
</div>