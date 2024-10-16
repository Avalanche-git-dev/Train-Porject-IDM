<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .profile-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            position: absolute;
            top: 20px;
            right: 20px;
            width: 300px;
        }

        .train-list {
            margin-top: 100px;
        }

        .train-item {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 15px;
            background-color: #f8f9fa;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .train-item img {
            width: 80px;
            height: auto;
            margin-right: 20px;
        }

        .details-btn {
            background-color: #0275d8;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .details-btn:hover {
            background-color: #025aa5;
        }
    </style>
</head>
<body>

    <div class="container mt-5">
        <h2>Profilo Utente</h2>
        
        <!-- Scheda Profilo Utente in alto a destra -->
        <div class="profile-info">
            <h5>Informazioni Utente</h5>
            <table class="table">
                <tr><th>Nome:</th><td>${userInfo.nome}</td></tr>
                <tr><th>Cognome:</th><td>${userInfo.cognome}</td></tr>
                <tr><th>Email:</th><td>${userInfo.email}</td></tr>
                <tr><th>Telefono:</th><td>${userInfo.telefono}</td></tr>
                <tr><th>Username:</th><td>${userInfo.username}</td></tr>
                <tr><th>Portafoglio:</th><td>${userInfo.portafoglio}</td></tr>
            </table>
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">Torna alla Dashboard</a>
        </div>

        <!-- Lista scorrevole dei treni -->
       <div class="train-list">
    <h3>I tuoi Treni</h3>
    <div class="row">
        <c:forEach var="treno" items="${trainList}">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img class="card-img-top" src="${treno.imageUrl}" alt="Immagine Treno">
                    <div class="card-body">
                        <h5 class="card-title">${treno.nome}</h5>
                        <p class="card-text">Prezzo: ${treno.prezzo}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <button class="btn btn-sm btn-outline-primary" 
                                    onclick="window.location.href='${pageContext.request.contextPath}/treni/dettagli/${treno.id}'">Dettagli</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


    </div>

</body>
</html>
