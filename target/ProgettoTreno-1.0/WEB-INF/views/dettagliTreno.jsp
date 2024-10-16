<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <h3 class="card-title">Nome: ${trenoDto.nome}</h3>
            <p class="card-text">ID Treno: ${trenoDto.idTreno}</p>
            <p class="card-text">Numero di Vagoni: ${trenoDto.numeroVagoni}</p>
            <p class="card-text">Marca: ${trenoDto.marca}</p>
            <p class="card-text">Sigla: ${trenoDto.sigla}</p>
            <p class="card-text">Utente: ${trenoDto.utente}</p>
            <p class="card-text">Peso: ${trenoDto.peso} kg</p>
            <p class="card-text">Costo: €${trenoDto.costo}</p>
            <p class="card-text">Media Valutazioni: ${trenoDto.mediaValutazioni} / 5</p>
        </div>
    </div>
    <button class="btn btn-primary mt-4" onclick="window.location.href='/treni/visualizza'">Torna ai tuoi treni</button>
</div>

</body>
</html>
