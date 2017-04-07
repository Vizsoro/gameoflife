<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="<c:url value="/resources/style/gof.css" />" >
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<title>Game of Life</title>
	
</head>
<body>
<div>
<h1>Game of Life</h1>
<button>Next</button>
<button>Previous</button>
<button>Restart</button>
</div>
<div>
	<table>
		<c:forEach var="i" begin="1" end="${BOARD_SIZE}">
			<tr>
			<c:forEach var="i" begin="1" end="${BOARD_SIZE}">
				<td></td>
			</c:forEach>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>