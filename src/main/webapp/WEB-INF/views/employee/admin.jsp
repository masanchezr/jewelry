<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-row">
	<div class="col-12">
		<h1>
			<c:forEach items="${messages}" var="message">
				<c:out value="${message.message}" />
			</c:forEach>
		</h1>
	</div>
</div>