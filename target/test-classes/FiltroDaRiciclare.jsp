<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizza Treni</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .train-card {
            margin-bottom: 20px;
        }
        .train-card img {
            max-width: 100%;
            height: auto;
        }
        .details-btn {
            background-color: #0275d8;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }
        .details-btn:hover {
            background-color: #025aa5;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">I tuoi treni</h1>

    <!-- Form per il filtro -->
    <form action="/treni/filtro" method="get" class="mb-5">
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="prezzoMin">Prezzo Min</label>
                <input type="number" class="form-control" id="prezzoMin" name="prezzoMin" value="${filtro.prezzoMin}">
            </div>
            <div class="form-group col-md-3">
                <label for="prezzoMax">Prezzo Max</label>
                <input type="number" class="form-control" id="prezzoMax" name="prezzoMax" value="${filtro.prezzoMax}">
            </div>
            <div class="form-group col-md-3">
                <label for="pesoMin">Peso Min</label>
                <input type="number" class="form-control" id="pesoMin" name="pesoMin" value="${filtro.pesoMin}">
            </div>
            <div class="form-group col-md-3">
                <label for="pesoMax">Peso Max</label>
                <input type="number" class="form-control" id="pesoMax" name="pesoMax" value="${filtro.pesoMax}">
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-3">
                <label for="marca">Marca</label>
                <input type="text" class="form-control" id="marca" name="marca" value="${filtro.marca}">
            </div>
            <div class="form-group col-md-3">
                <label for="sigla">Sigla</label>
                <input type="text" class="form-control" id="sigla" name="sigla" value="${filtro.sigla}">
            </div>
            <div class="form-group col-md-3">
                <label for="valutazioni">Valutazioni Minime</label>
                <input type="number" step="0.1" class="form-control" id="valutazioni" name="valutazioni" value="${filtro.valutazioni}">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Filtra</button>
    </form>

    <!-- Lista dei treni -->
    <div class="row" id="trainList">
        <c:forEach var="treno" items="${treniDto}">
            <div class="col-md-4 train-card">
                <div class="card">
                    <img src="${treno.immagine}" class="card-img-top" alt="Treno ID: ${treno.idTreno}">
                    <div class="card-body">
                        <h5 class="card-title">Treno ID: ${treno.idTreno}</h5>
                        <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni} / 5</p>
                        <button class="details-btn" onclick="window.location.href='/treni/dettagli/${treno.idTreno}'">Dettagli</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
