<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header class="shop-header">
	<nav class="container">
		<ul id="nav_categories" class="list-inline categories">
			<li><a class="text-link active"
				href="http://www.joyeriasantodomingo.com/"><spring:message
						code="all" /></a></li>
			<li><spring:url value="/anillos" var="rings"/> <a
				class="text-link" href="${rings}"><spring:message code="rings" /></a></li>
			<li><spring:url value="/collares" var="necklaces"/>
				<a class="text-link" href="${necklaces}"><spring:message
						code="necklaces" /></a></li>
			<li><spring:url value="/pulseras" var="bracelets"/>
				<a class="text-link" href="${bracelets}"><spring:message
						code="bracelets" /></a></li>
			<li><spring:url value="/pendientes" var="earrings"/>
				<a class="text-link" href="${earrings}"><spring:message
						code="earrings" /></a></li>
		</ul>

		<ul class="breadcrumb">
			<li><c:out value="${breadcrumbs}"/></li>
		</ul>

	</nav>
</header>