
<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<ul class="pagination">
			<c:choose>
				<c:when test="${index > 1}">
					<li><a
						href="./dashboard?index=${index-1}&&limit=${limit}&search=${search}&order=${order}&sort=${sort}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:when>
			</c:choose>
			<c:forEach var="index" begin="1" end="${nbPages}">
				<li><a
					href="./dashboard?index=${index}&limit=${limit}&search=${search}&order=${order}&sort=${sort}">${index}</a></li>
			</c:forEach>
			<c:choose>
				<c:when test="${index != nbPages}">
					<li><a
						href="./dashboard?index=${index+1}&limit=${limit}&search=${search}&order=${order}&sort=${sort}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:when>
			</c:choose>
		</ul>


		<div class="btn-group btn-group-sm pull-right" role="group">
			<form action="dashboard?index=${index}" method="get">
				<input type="submit" id="10" name="limit" value="10"
					class="btn btn-default"> <input type="submit" id="50"
					name="limit" value="50" class="btn btn-default"> <input
					type="submit" id="100" name="limit" value="100"
					class="btn btn-default">
			</form>
		</div>
	</div>
</footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>