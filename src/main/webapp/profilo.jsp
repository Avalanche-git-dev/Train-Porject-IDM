<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h2>Profilo Utente</h2>
        <table class="table table-striped">
            <tr>
                <th>Nome:</th>
                <td>${userInfo.nome}</td>
            </tr>
            <tr>
                <th>Cognome:</th>
                <td>${userInfo.cognome}</td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>${userInfo.email}</td>
            </tr>
            <tr>
                <th>Telefono:</th>
                <td>${userInfo.telefono}</td>
            </tr>
            <tr>
                <th>Username:</th>
                <td>${userInfo.username}</td>
            </tr>
            <tr>
                <th>Portafoglio:</th>
                <td>${userInfo.portafoglio}</td>
            </tr>
        </table>

        <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">Torna alla Dashboard</a>
    </div>

</body>
</html>
