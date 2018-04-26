<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="dailies" /></a></li>
	<li class="breadcrumb-item active"><fmt:formatDate
			value="${datedaily}" type="date" /></li>
	<li class="breadcrumb-item active"><c:out
			value="${daily.numoperations}" /> <spring:message code="operations" /></li>
	<li class="breadcrumb-item active"><c:out value="${place}" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="concept" /></th>
							<th><spring:message code="number" /></th>
							<th><spring:message code="amount" /></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${daily.sales}" var="sale">
							<tr>
								<td><spring:message code="sale" /></td>
								<td><spring:url value="/showsale" var="showsale" /><a
									href="${showsale}<c:out value="${sale.idsale}" />"><c:out
											value="${sale.numsale}" /></a></td>
								<td><c:out value="${sale.total}" /><i class="fa fa-euro-sign"></i></td>
								<td><c:out value="${sale.payments}" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.lsalespost}" var="salepost">
							<tr>
								<td><spring:message code="salepostponed" /></td>
								<td><spring:url value="/showsalepost" var="showsalepost" /><a
									href="${showsalepost}<c:out value="${salepost.idsale}" />"><c:out
											value="${salepost.idsale}" /></a></td>
								<td><c:out value="${salepost.total}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:out value="${salepost.payments}" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.retiredpawns}" var="pawn">
							<tr>
								<td><spring:message code="pawn" /></td>
								<td><spring:url value="/searchpawn" var="searchpawn" /> <a
									href="${searchpawn}<c:out value="${pawn.id}" />"> <c:out
											value="${pawn.numpawn}" />
								</a></td>
								<td><c:out value="${pawn.amount}" /><i class="fa fa-euro-sign"></i></td>
								<td><spring:message code="retired" /> <spring:url
										value="/renovations" var="renovations" /> <a
									href="${renovations}<c:out value="${pawn.id}" />"><spring:message
											code="renovations" /></a></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.newpawns}" var="pawn">
							<tr>
								<td><spring:message code="pawn" /></td>
								<td><spring:url value="/searchpawn" var="searchpawn" /> <a
									href="${searchpawn}<c:out value="${pawn.id}" />"> <c:out
											value="${pawn.numpawn}" />
								</a></td>
								<td><c:out value="${pawn.amount}" /><i class="fa fa-euro-sign"></i></td>
								<td><spring:message code="newpawn" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.adjustments}" var="adjustment">
							<tr>
								<td><spring:message code="adjustment" /> <c:out
										value="${adjustment.description}" /></td>
								<td><c:out value="${adjustment.idadjustment}" /></td>
								<td><c:out value="${adjustment.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:if test="${adjustment.payment.idpayment==3}">
										<c:out value="${adjustment.payment.name}" />
									</c:if></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.adjustmentswork}" var="adjustmentwork">
							<tr>
								<td><spring:message code="adjustmentwork" /> <c:out
										value="${adjustmentwork.description}" /></td>
								<td><c:out value="${adjustmentwork.idadjustment}" /></td>
								<td><c:out value="${adjustmentwork.amountwork}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.shoppings}" var="shopping">
							<tr>
								<td><spring:message code="shopping" /> <c:out
										value="${shopping.description}" /></td>
								<td><spring:url value="/updateShopping"
										var="updateShopping" /><a
									href="${updateShopping}<c:out value="${shopping.id}" />"><c:out
											value="${shopping.numshop}" /></a></td>
								<td><c:out value="${shopping.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:out value="${shopping.payments}" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.othersconcepts}" var="concept">
							<tr>
								<td><c:out value="${concept.description}" /></td>
								<td></td>
								<td><c:out value="${concept.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.renovations}" var="renovation">
							<tr>
								<td><spring:message code="renovationpawn" /></td>
								<td><c:out value="${renovation.numpawn}" /></td>
								<td><c:out value="${renovation.renovationamount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.cancelsales}" var="cancelsale">
							<tr>
								<td><spring:message code="cancelsales" /></td>
								<td><c:out value="${cancelsale.numsale}" /></td>
								<td><c:out value="${cancelsale.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:out value="${cancelsale.parcial}" /> <c:out
										value="${cancelsale.payment.name}" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.entriesmoney}" var="entrymoney">
							<tr>
								<td><spring:message code="entrymoney" /></td>
								<td></td>
								<td><c:out value="${entrymoney.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.batteries}" var="battery">
							<tr>
								<td><spring:message code="putbattery" /></td>
								<td><c:out value="${battery.numsale}" /></td>
								<td><c:out value="${battery.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:if test="${battery.payment.idpayment==3}">
										<c:out value="${battery.payment.name}" />
									</c:if></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.straps}" var="strap">
							<tr>
								<td><spring:message code="putstrap" /></td>
								<td><c:out value="${strap.numsale}" /></td>
								<td><c:out value="${strap.amount}" /><i class="fa fa-euro-sign"></i></td>
								<td><c:if test="${strap.payment.idpayment==3}">
										<c:out value="${strap.payment.name}" />
									</c:if></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.rentals}" var="rental">
							<tr>
								<td><spring:message code="localrental" /></td>
								<td><c:out value="${rental.rentaldate}" /></td>
								<td><c:out value="${rental.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.discounts}" var="discount">
							<tr>
								<td><spring:message code="iddiscount" /></td>
								<td><c:out value="${discount.iddiscount}" /></td>
								<td><c:out value="${discount.discount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.recordings}" var="recording">
							<tr>
								<td><spring:message code="recording" /></td>
								<td><c:out value="${recording.numsale}" /></td>
								<td><c:out value="${recording.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:out value="${recording.pay.name}" /></td>
							</tr>
						</c:forEach>
						<c:if test="${daily.payroll!=null}">
							<tr>
								<td><spring:message code="payroll" /></td>
								<td></td>
								<td><c:out value="${daily.payroll.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="form-group col-3">
						<spring:message code="totalamount" />
						<c:out value="${daily.finalamount}" />
						<i class="fa fa-euro-sign"></i>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="form-group col-3">
						<spring:url value="/beforeday" var="beforeday" />
						<a
							href="${beforeday}<fmt:formatDate value="${datedaily}" pattern="yyyyMMdd" />/<c:out value="${place}" />"><button
								class="btn btn-primary" type="button">
								<i class="fa fa-arrow-circle-left"></i>
								<spring:message code="beforeday" />
							</button></a>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="pull-right">
						<spring:url value="/againday" var="againday" />
						<a
							href="${againday}<fmt:formatDate value="${datedaily}" pattern="yyyyMMdd" />/<c:out value="${place}" />"><button
								class="btn btn-primary" type="button">
								<spring:message code="againday" />
								<i class="fa fa-arrow-circle-right"></i>
							</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>