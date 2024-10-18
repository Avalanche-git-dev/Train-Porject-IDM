<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>
        <c:forEach var="treno" items="${treni}">
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
