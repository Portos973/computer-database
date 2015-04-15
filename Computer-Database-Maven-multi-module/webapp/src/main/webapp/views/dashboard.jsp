
<%@include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<section id="main">
	<div class="container">
		<h1 id="homeTitle">${size}
			<spring:message code="dashboard.found"></spring:message>
		</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="dashboard" method="GET"
					class="form-inline">
					<input type="search" id="searchbox" name="search"
						class="form-control"
						placeholder="<spring:message code="dashboard.search.name"></spring:message>" />
					<input type="submit" id="searchsubmit"
						value="<spring:message code="dashboard.search"></spring:message>"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<a class="btn btn-success" id="addComputer" href=addComputer><spring:message
							code="dashboard.add"></spring:message></a>
					<a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message
							code="dashboard.button.edit"></spring:message></a>
				</sec:authorize>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="dashboard" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" /> <span
						style="vertical-align: top;"> - <a href="#"
							id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th><spring:message code="dashboard.name"></spring:message> <a
						href="dashboard?limit=${limit}&index=${index}&order=name&search=${search}&sort=ASC"><i
							class="fa fa-angle-up"></i></a> <a
						href="dashboard?limit=${limit}&index=${index}&order=name&search=${search}&sort=DESC"><i
							class="fa fa-angle-down"></i></a></th>
					<th><spring:message code="dashboard.introduced"></spring:message>
						<a
						href="dashboard?limit=${limit}&index=${index}&order=introduced&search=${search}&sort=ASC"><i
							class="fa fa-angle-up"></i></a> <a
						href="dashboard?limit=${limit}&index=${index}&order=introduced&search=${search}&sort=DESC"><i
							class="fa fa-angle-down"></i></a></th>
					<!-- Table header for Discontinued Date -->
					<th><spring:message code="dashboard.discontinued"></spring:message>
						<a
						href="dashboard?limit=${limit}&index=${index}&order=discontinued&search=${search}&sort=ASC">
							<i class="fa fa-angle-up"></i>
					</a> <a
						href="dashboard?limit=${limit}&index=${index}&order=discontinued&search=${search}&sort=DESC">
							<i class="fa fa-angle-down"></i>
					</a></th>
					<!-- Table header for Company -->
					<th><spring:message code="dashboard.company"></spring:message>
						<a
						href="dashboard?limit=${limit}&index=${index}&order=company&search=${search}&sort=ASC"><i
							class="fa fa-angle-up"></i></a> <a
						href="dashboard?limit=${limit}&index=${index}&order=company&search=${search}&sort=DESC"><i
							class="fa fa-angle-down"></i></a></th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->

			<tbody id="results">

				<c:forEach var="Computer" items="${ Computers }">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value="${Computer.id }"></td>
						<td><sec:authorize ifAnyGranted="ROLE_ADMIN">
								<a
									href="editComputer?id=${Computer.id }&&computerName=${Computer.name}&&introduced=${Computer.introduced }&&discontinued=${Computer.discontinued }"
									onclick="">${Computer.name}</a>
							</sec:authorize></td>
						<td>${Computer.introduced }</td>
						<td>${Computer.discontinued }</td>
						<td>${Computer.companyName }</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
</section>

<%@include file="footer.jsp"%>

<script type="text/javascript">
	var strings = new Array();
	strings['dashboard.button.view'] = "<spring:message code='dashboard.button.view' javaScriptEscape='true' />";
	strings['dashboard.button.edit'] = "<spring:message code='dashboard.button.edit' javaScriptEscape='true' />";
	strings['dashboard.confirm.delete'] = "<spring:message code='dashboard.confirm.delete' javaScriptEscape='true' />";
</script>

</body>
</html>