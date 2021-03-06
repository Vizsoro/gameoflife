<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/style/gof.css" var="gofCss" />
<spring:url value="/resources/style/bootstrap.min.css"
	var="bootstrapCss" />
<html>
<head>
<link rel="stylesheet" href="${bootstrapCss }">
<link rel="stylesheet" href="${gofCss}">

<title>Game of Life</title>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4 description">
				<h1>Game of Life</h1>
				<h1>
					<c:out value="${error }" />
				</h1>
				<h2>
					The current cycle of the game:
					<c:out value="${currentCycle }"></c:out>
				</h2>
				<div class="btn-group btn-group-lg" role="group">
					<a href="/gameoflife/nextcycle" class="btn btn-success next">Next</a>
					<c:if test="${previous}">
						<a href="/gameoflife/previouscycle" class='btn btn-info previous'>Previous</a>
					</c:if>
					<a href="/gameoflife" class="btn btn-warning restart">Restart</a>
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
							<c:forEach items="${row.value }" var="entry">
								<td
									class='<c:choose>
									<c:when test="${entry.value.state eq 'LIVE'}">
										<c:choose>
											<c:when test="${entry.value.color eq 'GREEN'}">
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
	</div>
	<div class="row">
		<div class="container footer">
			<div class="jumbotron">
				<p>IT@Challenges - 2017</p>
			</div>
		</div>
	</div>
</body>
</html>