<%@include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>Add Computer</h1>
				<form:form id="addComputer" action="addComputer" method="POST"
					modelAttribute="computerDTO">
					<fieldset>
						<div class="form-group">
							<label for="name"><spring:message code="dashboard.name"></spring:message></label> <input type="text"
								class="form-control" id="name" name="name"
								placeholder="<spring:message code="dashboard.name"></spring:message>">
							<form:errors path="name" element="div"
								cssClass="alert alert-danger" />
						</div>
						<div class="form-group">
							<label for="introduced"><spring:message code="dashboard.introduced"></spring:message></label> <input
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder="<spring:message code="dashboard.introduced"></spring:message>">
							<form:errors path="introduced" element="div"
								cssClass="alert alert-danger" />
						</div>
						<div class="form-group">
							<label for="discontinued"><spring:message code="dashboard.discontinued"></spring:message></label> <input
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="<spring:message code="dashboard.discontinued"></spring:message>">
							<form:errors path="discontinued" element="div"
								cssClass="alert alert-danger" />
						</div>
						<div class="form-group">
							<label for="companyId"><spring:message code="dashboard.company"></spring:message></label> <select
								class="form-control" name="companyId">
								<option value="0"> - - - - </option>
								<c:forEach var="company" items="${ companies }">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="<spring:message code="add.add"></spring:message>" class="btn btn-primary">
						or <a href="dashboard" class="btn btn-default"><spring:message code="add.cancel"></spring:message></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/addComputer.js"></script>
</body>
</html>