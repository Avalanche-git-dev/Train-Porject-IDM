
 
 
 
 
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
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
            <h5 class="card-title">Nome Treno: ${treno.nome}</h5>
            <%-- <p class="card-text"><strong>ID Treno:</strong> ${treno.idTreno}</p> --%>
            <p class="card-text"><strong>Marca:</strong> ${treno.marca}</p>
            <p class="card-text"><strong>Sigla:</strong> ${treno.sigla}</p>
            <p class="card-text"><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
            <p class="card-text"><strong>Costo Totale:</strong> €${treno.costoTotale}</p>
            <p class="card-text"><strong>Posti Totali:</strong> ${treno.postiTotali}</p>
            <p class="card-text"><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>

            <%-- <c:if test="${ownerId != null}">
                <p class="card-text"><strong>Proprietario:</strong> Utente ID ${ownerId}</p>
            </c:if> --%>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/treni/visualizza" class="btn btn-secondary mt-3">Torna ai Treni dell'Utente</a>
</div>

</body>
</html>
 
 