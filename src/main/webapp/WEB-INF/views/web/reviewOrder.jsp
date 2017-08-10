<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<SECTION id="checkout" class="main-content">
	<DIV class="container">
		<DIV class="row noborder">
			<form:form class="form-horizontal" method="post">
				<TABLE class="table">
					<THEAD>
						<TR>
							<TH><spring:message code="product"/></TH>
							<TH>Price</TH>
							<TH>Qty</TH>
						</TR>
					</THEAD>
					<TBODY>
						<tr>
							<td colspan="3"><spring:message code="datamailing"/></td>
						</tr>
						<TR>
							<TD>
								<H5>
									<A href="http://danielladraper.com/shop/product/dream-bangle">Dream
										Bangle</A>
								</H5>
								<P>
									<SMALL><B>BANGLE SIZE</B>: SMALL/MEDIUM (20cm) <BR>
										<B>INSCRIPTION</B>: <BR></SMALL>
								</P>
							</TD>
							<TD>£125.00</TD>
							<TD><INPUT class="input-mini" name="items[5703][item_qty]"
								value="1"></TD>
							<TD style="text-align: right;">£125.00</TD>
						</TR>
						<TR>
							<TH colSpan="3">Subtotal inc VAT</TH>
							<TH style="text-align: right;">£125.00</TH>
						</TR>
						<TR>
							<TD colSpan="2">Promo<BR>Code
							</TD>
							<TD><INPUT id="promo_code" name="promo_code" type="text"></TD>
							<TD style="text-align: right;">£0.00</TD>
						</TR>
						<TR>
							<TD colSpan="2">Select<BR>Shipping
							</TD>
							<TD><SMALL><BR>*(1-2 days does not apply to
									personalised items which have 1 week lead time)</SMALL></TD>
							<TD style="text-align: right;">£5.00</TD>
						</TR>
						<TR>
							<TH colSpan="3">Total inc VAT</TH>
							<TH style="text-align: right;">£130.00</TH>
						</TR>
					</TBODY>
				</TABLE>
				<DIV>
					<form:button class="btn btn-default btn-primary" name="next"
						value="<spring:message code="buy"/>" />
				</DIV>

			</form:form>
		</DIV>
	</DIV>
</SECTION>