<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="/productoSeleccionado" var="selectedJewel"></spring:url>
<div class="container">
	<div class="content">
		<div class="row">
			<div class="col-md-12 text-center">
				<h2>
					<spring:message code="products" />
				</h2>
			</div>
		</div>
		<div class="row">
			<c:forEach items="${jewels}" var="jewel">
				<div class="col-md-2">
					<div class="grid">
						<div class="portfolio app mix_all" data-cat="app"
							style="display: inline-block; opacity: 1;">
							<div class="portfolio-wrapper">
								<spring:url value="/image/" var="urlimg" />
								<a data-toggle="modal"
									data-target=".bs-example-modal-md<c:out value="${jewel.idjewel}"/>"
									href="#" class="b-link-stripe b-animate-go thickbox"><img
									width="150" height="142"
									src="<c:out value="${urlimg}"/><c:out value="${jewel.idjewel}" />.jpg" />
									<div class="b-wrapper">
										<h2 class="b-animate b-from-left b-delay03 ">
											<img
												src="<spring:url value="/resources/img/web/link-ico.png"/>"
												alt="" />
										</h2>
									</div> </a>
							</div>
						</div>
						<p class="text-center">
							<c:out value="${jewel.name}" />
						</p>
						<h2 class="text-center">
							<c:out value="${jewel.price}" />
							&euro;
						</h2>
						<p class="text-center">
							<a href="${selectedJewel}<c:out value="${jewel.idjewel}" />"><spring:message
									code="buy" /></a>
						</p>
					</div>
				</div>
				<!----start-model-box---->
				<a data-toggle="modal"
					data-target=".bs-example-modal-md<c:out value="${jewel.idjewel}"/>"
					href="#"> </a>
				<div
					class="modal fade light-box bs-example-modal-md<c:out value="${jewel.idjewel}"/>"
					tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-md">
						<div class="modal-content light-box-info">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								<img src="<spring:url value="/resources/img/web/close.png"/>"
									title="close" />
							</button>
							<h3>
								<spring:message code="description" />
							</h3>
							<p>
								<c:out value="${jewel.description}" />
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:if test="${not empty jewels}">
			<c:url var="firstUrl" value="/" />
			<c:url var="lastUrl" value="/page/${deploymentLog.totalPages}" />
			<c:url var="prevUrl" value="/page/${currentIndex - 1}" />
			<c:url var="nextUrl" value="/page/${currentIndex + 1}" />
			<ul class="options list-unstyled">
				<c:choose>
					<c:when test="${currentIndex == 1}">
						<li class="disabled"><a href="#">&lt;&lt;</a></li>
						<li class="disabled"><a href="#">&lt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${firstUrl}">&lt;&lt;</a></li>
						<li><a href="${prevUrl}">&lt;</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					<c:url var="pageUrl" value="/page/${i}" />
					<c:choose>
						<c:when test="${i == currentIndex}">
							<li class="active"><a href="${pageUrl}"><c:out
										value="${i}" /></a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${currentIndex == deploymentLog.totalPages}">
						<li class="disabled"><a href="#">&gt;</a></li>
						<li class="disabled"><a href="#">&gt;&gt;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${nextUrl}">&gt;</a></li>
						<li><a href="${lastUrl}">&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</c:if>
		<c:if test="${empty jewels}">
			<spring:message code="noresults" />
		</c:if>
	</div>
	<!--/.row -->
	<footer class="row"> </footer>
	<!--/.row -->
</div>