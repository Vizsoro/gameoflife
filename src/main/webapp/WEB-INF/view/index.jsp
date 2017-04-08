<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/style/gof.css" var="gofCss" />

<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${gofCss}">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<title>Game of Life</title>
<style type="text/css">
table {
	margin: auto;
	border: 2px solid read;
}

table td {
	height: 5px;
	width: 5px;
}

.live-green {
	background-color: green;
}

.live-blue {
	background-color: blue;
}

.dead {
	background-color: black;
}
</style>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<div class="row">
				<div class="col-sm-4">
					<h1>Game of Life</h1>
				</div>
				<div class="col-sm4">
					<div class="btn-group-vertical">
						<a href="/gameoflife/nextcycle" class="btn btn-default next">Next</a>
						<a href="/gameoflife/previouscycle" class="btn btn-default previous">Previous</a>
						<a href="/gameoflife" class="btn btn-default restart">Restart</a>
					</div>
				</div>
				<div class="col-sm4">
					<p>
						The current cycle of the game:
						<c:out value="${currentCycle }"></c:out>
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<table>
			<c:forEach items="${board}" var="row">
				<tr>
					<c:forEach items="${row }" var="cell">
						<td
							class='<c:choose>
								<c:when test="${cell.state eq 'LIVE'}">
									<c:choose>
										<c:when test="${cell.color eq 'GREEN'}">
											live-green
										</c:when>
										<c:otherwise>
											live-blue
										</c:otherwise>
									</c:choose>
							   </c:when>
						   	   <c:otherwise>
						   	   		dead
						   	   </c:otherwise>
						   </c:choose>		   
						'></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>