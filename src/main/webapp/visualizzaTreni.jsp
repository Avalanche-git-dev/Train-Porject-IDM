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
    <div class="row" id="trainList">
         <c:forEach var="treno" items="${treniDto}">
            <div class="col-md-4 train-card">
                <div class="card">
                    <img src="${treno.immagine}" class="card-img-top" alt="Treno ID: ${treno.id}">
                    <div class="card-body">
                        <h5 class="card-title">${treno.id}</h5>
                        <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni} / 5</p>
                        <button class="details-btn" onclick="window.location.href='/treni/dettagli/${treno.id}'">Dettagli</button>
                    </div>
                </div>
            </div>
        </c:forEach> 
    </div>
</div>

</body>
</html>
