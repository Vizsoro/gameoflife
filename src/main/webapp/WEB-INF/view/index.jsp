<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/style/gof.css" var="gofCss" />

<html>
<head>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${gofCss}">

<title>Game of Life</title>

</head>
<body>
	<div class="container">
		<div class="page-header">
			<div class="row">
				<div class="col-sm-6">
					<h1>Game of Life</h1>
				</div>

				<div class="col-sm-6">
					<h1>
						<c:out value="${error }" />
					</h1>
					<h2>
						The current cycle of the game:
						<c:out value="${currentCycle }"></c:out>
					</h2>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<div class="btn-group-horizontal">
				<a href="/gameoflife/nextcycle" class="btn btn-default next">Next</a>
				<c:if test="${previous}">
					<a href="/gameoflife/previouscycle" class='btn btn-default previous'>Previous</a>
				</c:if>
				<a href="/gameoflife" class="btn btn-default restart">Restart</a>
			</div>
			<div class="details">
				<div>
					<span>The initial probability of a living cell :</span>
					<p class="info">${probability }</p>
				</div>
				<div>
					<span>A living cell die if has more living neighbour than:</span>
					<p class="info">${dieUpper }</p>
				</div>
				<div>
					<span>A living cell die if has less living neighbour than:</span>
					<p class="info">${dieLower }</p>
				</div>
				<div>
					<span>A died cell come alive when its neighbours:</span>
					<p class="info">${comeAlive }</p>
				</div>
			</div>
		</div>
		<div class="col-sm-8">
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
	</div>
	<div class="container footer">
		<div class="jumbotron">
			<p>IT@Challenges - 2017</p>
		</div>
	</div>

</body>
</html>