<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        /* Contenitore per i dettagli */
        .details-container {
            margin-top: 50px;
        }

        /* Card per i dettagli del treno */
        .train-card {
            display: flex;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Sezione dell'immagine a sinistra */
        .train-image {
            flex: 1;
            margin-right: 20px;
            min-width: 200px; /* Mantiene lo spazio vuoto se l'immagine non è presente */
            height: 200px;
            background-color: #f0f0f0; /* Sfondo grigio chiaro in assenza di immagine */
            border-radius: 8px;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Dimensioni dell'immagine */
        .train-image img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }

        /* Sezione delle informazioni del treno */
        .train-info {
            flex: 2;
            text-align: left;
        }

        /* Pulsante Torna indietro */
        .back-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container details-container">
    <h1 class="text-center">Dettagli Treno</h1>

    <!-- Card che contiene immagine e dettagli -->
    <div class="card train-card">
        <!-- Sezione dell'immagine -->
        <div class="train-image">
            <!-- Mostra l'immagine se presente, altrimenti spazio vuoto -->
            <c:choose>
                <c:when test="${not empty treno.immagine}">
                    <img src="${treno.immagine}" alt="Immagine del Treno">
                </c:when>
                <c:otherwise>
                    <span>Nessuna immagine disponibile</span>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Sezione delle informazioni -->
        <div class="train-info">
            <h5 class="card-title">Nome Treno: ${treno.nome}</h5>
            <p class="card-text"><strong>Marca:</strong> ${treno.marca}</p>
            <p class="card-text"><strong>Sigla:</strong> ${treno.sigla}</p>
            <p class="card-text"><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
            <p class="card-text"><strong>Costo Totale:</strong> €${treno.costoTotale}</p>
            <p class="card-text"><strong>Posti Totali:</strong> ${treno.postiTotali}</p>
            <p class="card-text"><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
        </div>
    </div>
    
    <!-- Pulsante Torna alla pagina precedente -->
    <button class="btn btn-primary back-button" onclick="window.history.back()">Torna alla pagina precedente</button>

</div>

</body>
</html>
 --%>
 
 
 
 
 
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
        /* Contenitore per i dettagli */
        .details-container {
            margin-top: 50px;
        }

        /* Card per i dettagli del treno, usa flexbox per layout */
        .train-card {
            display: flex;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            align-items: flex-start; /* Allinea l'immagine e i dettagli in cima */
        }

        /* Sezione dell'immagine a sinistra */
        .train-image {
            flex: 0 0 200px; /* Larghezza fissa per l'immagine */
            height: 200px;
            background-color: #f0f0f0; /* Sfondo grigio chiaro per visualizzare il riquadro dell'immagine */
            border-radius: 8px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-right: 20px; /* Spazio tra immagine e informazioni */
        }

        /* Dimensioni dell'immagine */
        .train-image img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
        }

        /* Sezione delle informazioni del treno */
        .train-info {
            flex: 1;
            text-align: left;
        }

        /* Pulsante Torna indietro */
        .back-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container details-container">
    <h1 class="text-center">Dettagli Treno</h1>

    <!-- Card che contiene immagine e dettagli -->
    <div class="card train-card">
        <!-- Sezione dell'immagine -->
        <div class="train-image">
            <!-- Mostra l'immagine se presente, altrimenti spazio vuoto -->
            <c:choose>
                <c:when test="${not empty treno.immagine}">
                    <img src="${treno.immagine}" alt="Immagine del Treno">
                </c:when>
                <c:otherwise>
                    <span>Nessuna immagine disponibile</span>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Sezione delle informazioni -->
        <div class="train-info">
            <h5 class="card-title">Nome Treno: ${treno.nome}</h5>
            <p class="card-text"><strong>Marca:</strong> ${treno.marca}</p>
            <p class="card-text"><strong>Sigla:</strong> ${treno.sigla}</p>
            <p class="card-text"><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
            <p class="card-text"><strong>Costo Totale:</strong> €${treno.costoTotale}</p>
            <p class="card-text"><strong>Posti Totali:</strong> ${treno.postiTotali}</p>
            <p class="card-text"><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
        </div>
    </div>
    
    <!-- Pulsante Torna alla pagina precedente -->
    <button class="btn btn-primary back-button" onclick="window.history.back()"> Indietro </button>

</div>

</body>
</html>
 
 