<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section id="checkout">
	<div class="container">
		<div class="row">
			<form:form action="aceptarcompra" commandName="buyForm">
				<form:hidden path="nif" />
				<c:forEach items="${addressesmailing}" var="address">
					<address>
						<form:radiobutton path="idaddressmailing"
							value="${address.idaddress }" />
						<br>
						<c:out value="${address.address}" />
						<br>
						<c:out value="${address.postalcode}" />
						<br>
						<c:out value="${address.city}" />
						<br>
						<c:out value="${address.country}" />
						<br>
					</address>
				</c:forEach>
				<c:forEach items="${addressesbilling}" var="address">
					<address>
						<form:radiobutton path="idaddressbilling"
							value="${address.idaddress }" />
						<br>
						<c:out value="${address.cif}" />
						<br>
						<c:out value="${address.invoicename}" />
						<br>
						<c:out value="${address.address}" />
						<br>
						<c:out value="${address.postalcode}" />
						<br>
						<c:out value="${address.city}" />
						<br>
						<c:out value="${address.country}" />
						<br>
					</address>
				</c:forEach>
				<a href="#" id="aotherdirection"><spring:message
						code="otherdirection" /></a>
				<div id="otherdirection">
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group col-3">
								<label class="control-label" for="shipping_address"><spring:message
										code="address" /><span>*</span></label>
								<div class="controls">
									<form:input class="form-control" path="address"
										id="shipping_address" name="shipping_address" />
									<form:errors path="address" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group col-3">
								<label class="control-label" for="shipping_postcode"><spring:message
										code="postcode" /><span>*</span></label>
								<form:input class="form-control" path="postalcode"
									id="shipping_postcode" name="shipping_postcode" />
								<form:errors path="postalcode" />
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group col-3">
								<label class="control-label" for="shipping_city"><spring:message
										code="city" /><span>*</span></label>
								<form:input class="form-control" path="city" id="shipping_city"
									name="shipping_city" />
								<form:errors path="city" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group col-3">
								<div id="billing-details-check" class="control-group">
									<div class="controls">
										<label class="checkbox" for="billing_same_as_shipping"><form:checkbox
												id="billing_same_as_shipping" path="idaddressbilling"
												CHECKED="checked" value="" /> <spring:message
												code="billing_same_as_shipping" /></label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid no border">
						<div id="billing_details_drawer">
							<div class="col-md-5">
								<div class="control-group">
									<label class="control-label" for="billing_name"><spring:message
											code="name" /><span>*</span></label>
									<form:input class="form-control" id="billing_name"
										name="billing_name" path="namebilling" />
									<form:errors path="namebilling" />
								</div>
								<div class="control-group">
									<label class="control-label" for="billing_nif"><spring:message
											code="cif" /><span>*</span></label>
									<form:input class="form-control" path="cif" id="billing_nif"
										name="billing_nif" />
									<form:errors path="cif" />
								</div>
								<div class="control-group">
									<label class="control-label" for="billing_address"><spring:message
											code="addressbilling" /><span>*</span></label>
									<form:input class="form-control" path="addressbilling"
										id="billing_address" name="billing_address" />
									<form:errors path="addressbilling" />
								</div>
								<div class="control-group">
									<label class="control-label" for="billing_postcode"><spring:message
											code="postcode" /><span>*</span></label>
									<form:input class="form-control" path="postalcodebilling"
										id="billing_postcode" name="billing_postcode" />
									<form:errors path="postalcodebilling" />
								</div>
								<div class="control-group">
									<label class="control-label" for="billing_city"><spring:message
											code="city" /><span>*</span></label>
									<form:input class="form-control" path="citybilling"
										id="billing_city" name="billing_city" />
									<form:errors path="citybilling" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div id="billing-details-payment" class="control-group">
						<div class="col-md-6">
							<label class="control-label"><spring:message
									code="payments" /></label>
							<c:forEach items="${payments}" var="payment">
								<form:radiobutton id="payment" path="payment"
									value="${payment.idpayment}" />
								<label><c:out value="${payment.name}" /></label>
							</c:forEach>
							<form:errors path="payment" />
						</div>
					</div>
				</div>
				<div>
					<form:button class="btn btn-default btn-primary" name="next"
						value="submit">
						<spring:message code="buy" />
					</form:button>
				</div>
			</form:form>
		</div>
	</div>
</section>