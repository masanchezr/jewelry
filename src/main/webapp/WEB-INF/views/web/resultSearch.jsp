<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	jQuery(document)
			.ready(
					function($) {
						$('#etalage')
								.etalage(
										{
											thumb_image_width : 300,
											thumb_image_height : 400,
											show_hint : true,
											click_callback : function(
													image_anchor, instance_id) {
												alert('Callback example:\nYou clicked on an image with the anchor: "'
														+ image_anchor
														+ '"\n(in Etalage instance: "'
														+ instance_id + '")');
											}
										});
						// This is for the dropdown list example:
						$('.dropdownlist').change(
								function() {
									etalage_show($(this)
											.find('option:selected').attr(
													'class'));
								});
					});
</script>
<form:form commandName="jewel" action="addProduct" method="post">
	<form:hidden path="idjewel" />
	<div class="single">
		<div class="container">
			<div class="rsidebar span_1_of_left">
				<!-- section class="sky-form"-->
			</div>
			<div class="cont span_2_of_3">
				<div class="labout span_1_of_a1">
					<!-- start product_slider -->
					<ul id="etalage">
						<li><a href="optionallink.html"> <img
								class="etalage_thumb_image"
								src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
								<img class="etalage_source_image"
								src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</a></li>
						<li><img class="etalage_thumb_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
							<img class="etalage_source_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</li>
						<li><img class="etalage_thumb_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
							<img class="etalage_source_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</li>
						<li><img class="etalage_thumb_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
							<img class="etalage_source_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</li>
						<li><img class="etalage_thumb_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
							<img class="etalage_source_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</li>
						<li><img class="etalage_thumb_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
							<img class="etalage_source_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</li>
						<li><img class="etalage_thumb_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
							<img class="etalage_source_image"
							src="<spring:url value="/image/" /><c:out value="${jewel.idjewel}" />.jpg" />
						</li>
					</ul>
					<!-- end product_slider -->
				</div>
				<div class="cont1 span_2_of_a1 pull-right">
					<h3 class="m_3">
						<c:out value="${jewel.name}" />
					</h3>
					<div class="price_single">
						<span class="actual"><c:out value="${jewel.price}" />&euro;</span>
					</div>
					<div class="btn_form">
						<form:button class="btn btn-default" value='submit'>
							<spring:message code="addcart" />
						</form:button>
					</div>
					<p class="m_desc">
						<c:out value="${jewel.description}" />
					</p>
					<div class="social_single">
						<ul class="list-unstyled">
							<li class="fb"><a href="#"><span> </span></a></li>
							<li class="tw"><a href="#"><span> </span></a></li>
							<li class="g_plus"><a href="#"><span> </span></a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="toogle">
				<h3 class="m_3">
					<spring:message code="productdetails" />
				</h3>
				<p class="m_text">
					<c:out value="${jewel.description}" />
				</p>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
</form:form>