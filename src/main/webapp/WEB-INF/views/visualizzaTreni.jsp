<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Treni dell'Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Collezione Treni dell'Utente</h1>
        <c:forEach var="treno" items="${treniDto}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Peso Totale: ${treno.pesoTotale} kg</p>
                    <a href="${pageContext.request.contextPath}/treni/dettagli/${treno.idTreno}" class="btn btn-primary">Vedi Dettagli</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
 --%>


<%--  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Treni dell'Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Collezione Treni dell'Utente</h1>
        <c:forEach var="treno" items="${treniDto}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni} / 5</p>
                    <a href="${pageContext.request.contextPath}/treni/visualizza/treno" class="btn btn-primary">Vedi Dettagli</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
  --%>



<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Treni dell'Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h1>Collezione Treni dell'Utente</h1>
		<c:forEach var="treno" items="${treniDto}">
			<div class="card mb-3">
				<div class="card-body">
					<h5 class="card-title">${treno.nome}</h5>
					<p class="card-text">Marca: ${treno.marca}</p>
					<p class="card-text">Media Valutazioni:
						${treno.mediaValutazioni} / 5</p>
					<!-- Form per inviare il TrenoDto -->
					<%--  <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="get">
                        <input type="hidden" name="idTreno" value="${treno.idTreno}"/>
                        <input type="hidden" name="nome" value="${treno.nome}"/>
                        <input type="hidden" name="marca" value="${treno.marca}"/>
                        <input type="hidden" name="mediaValutazioni" value="${treno.mediaValutazioni}"/>
                        <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                    </form> --%>
					<form
						action="${pageContext.request.contextPath}/treni/visualizza/treno"
						method="post">
						<input type="hidden" name="idTreno" value="${treno.idTreno}" />
						<button type="submit" class="btn btn-primary">Vedi
							Dettagli</button>
					</form>


				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
