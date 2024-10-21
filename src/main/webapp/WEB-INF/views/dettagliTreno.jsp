<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dettagli Treno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .details-container {
            margin-top: 50px;
        }
    </style>
</head>
<body>

<div class="container details-container">
    <h1 class="text-center">Dettagli Treno</h1>
    <div class="card">
        <div class="card-body">
            <h3 class="card-title">Nome: ${treno.nome}</h3>
            <%-- <p class="card-text">ID Treno: ${treno.idTreno}</p> --%>
            <p class="card-text">Marca: ${treno.marca}</p>
            <p class="card-text">Sigla: ${treno.sigla}</p>
            <p class="card-text">Peso Totale: ${treno.pesoTotale} kg</p>
            <p class="card-text">Costo Totale: €${treno.costoTotale}</p>
            <p class="card-text">Posti Totali: ${treno.postiTotali}</p>
            <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni} / 5</p>
            <%-- <c:if test="${ownerId != null}">
                <p class="card-text">Proprietario: Utente ID ${ownerId}</p>
            </c:if> --%>
        </div>
    </div>
    <button class="btn btn-primary mt-4" onclick="window.location.href='${pageContext.request.contextPath}/dashboard'">Torna alla dashboard</button>
</div>

</body>
</html>
