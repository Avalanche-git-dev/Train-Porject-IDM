<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Treno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">Modifica Treno</h1>

    <form action="/treni/salvaModifica" method="post">
        <div class="form-group">
            <label for="marca">Marca</label>
            <input type="text" class="form-control" id="marca" name="marca" value="${treno.marca}">
        </div>
        <div class="form-group">
            <label for="sigla">Sigla</label>
            <input type="text" class="form-control" id="sigla" name="sigla" value="${treno.sigla}">
        </div>
        <div class="form-group">
            <label for="prezzoVendita">Prezzo di Vendita</label>
            <input type="number" class="form-control" id="prezzoVendita" name="prezzoVendita" value="${treno.prezzoVendita}">
        </div>
        <div class="form-group">
            <label for="peso">Peso (kg)</label>
            <input type="number" class="form-control" id="peso" name="peso" value="${treno.peso}">
        </div>
        <div class="form-group">
            <label for="lunghezza">Lunghezza (metri)</label>
            <input type="number" class="form-control" id="lunghezza" name="lunghezza" value="${treno.lunghezza}">
        </div>

        <!-- Pulsante per salvare le modifiche -->
        <button type="submit" class="btn btn-primary">Salva Modifiche</button>
    </form>
</div>

</body>
</html>
