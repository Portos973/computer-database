<%@include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- Date Picker -->
<link rel="stylesheet" href="js/datePicker/jquery-ui.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/datePicker/jquery-ui.js"></script>
<script src="js/datePicker/datepicker-fr.js"></script>
<script src="js/datePicker/datepicker-en.js"></script>

<link href="css/main.css" rel="stylesheet" media="screen">

<script>
	$(function() {
		$("#introducedFormated").datepicker({
			changeMonth : true,
			changeYear : true
		});
		$("#introducedFormated").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#introducedFormated").datepicker("setDate",
				"${computerEdit.introduced}");
		$("#introducedFormated").datepicker("option",
				$.datepicker.regional["${lang}"]);
		$("#introducedFormated")
				.datepicker('option', 'altField', "#introduced");
		$("#introducedFormated").datepicker('option', 'altFormat', "yy-mm-dd");

	});
	$(function() {
		$("#discontinuedFormated").datepicker({
			changeMonth : true,
			changeYear : true
		});
		$("#discontinuedFormated").datepicker("option", "dateFormat",
				"yy-mm-dd");
		$("#discontinuedFormated").datepicker("setDate",
				"${computerEdit.discontinued}");
		$("#discontinuedFormated").datepicker("option",
				$.datepicker.regional["${lang}"]);
		$("#discontinuedFormated").datepicker('option', 'altField',
				"#discontinued");
		$("#discontinuedFormated")
				.datepicker('option', 'altFormat', "yy-mm-dd");
	});
</script>


<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1>
					<spring:message code="add"></spring:message>
				</h1>
				<form:form id="addComputer" action="addComputer" method="POST"
					modelAttribute="computerDTO">
					<fieldset>
						<div class="form-group">
							<label for="name"><spring:message code="dashboard.name"></spring:message></label>
							<input type="text" class="form-control" id="name" name="name"
								placeholder="<spring:message code="dashboard.name"></spring:message>">
							<form:errors path="name" element="div"
								cssClass="alert alert-danger" />
						</div>
						<div class="form-group">
							<label for="introducedFormated"><spring:message
									code="dashboard.introduced"></spring:message></label> <input
								type="text" class="form-control" id="introducedFormated"
								readonly="readonly" name="introducedFormated"
								placeholder="<spring:message code="dashboard.introduced"></spring:message>">
							<form:errors path="introduced" element="div"
								cssClass="alert alert-danger" />
						</div>
						<input type="text" id="introduced" name="introduced" hidden="true">
						 <div class="form-group">
							<label for="discontinuedFormated"><spring:message
									code="dashboard.discontinued"></spring:message></label> <input
								type="text" class="form-control" id="discontinuedFormated"
								readonly="readonly" name="discontinuedFormated"
								placeholder="<spring:message code="dashboard.discontinued"></spring:message>">
							<form:errors path="discontinued" element="div"
								cssClass="alert alert-danger" />
						</div>
						<input type="text" id="discontinued" name="discontinued"
							hidden="true">
						<div class="form-group">
							<label for="companyId"><spring:message
									code="dashboard.company"></spring:message></label> <select
								class="form-control" name="companyId">
								<option value="0">- - - -</option>
								<c:forEach var="company" items="${ companies }">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit"
							value="<spring:message code="add.add"></spring:message>"
							class="btn btn-primary"> or <a href="dashboard"
							class="btn btn-default"><spring:message code="add.cancel"></spring:message></a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</section>

<script src="js/jquery.validate.min.js"></script>
<script src="js/addComputer.js"></script>

<script type="text/javascript">
	var strings = new Array();
	strings['lang'] = "<spring:message code='lang' javaScriptEscape='true' />";
</script>

</body>
</html>