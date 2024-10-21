<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista Utenti</title>
</head>
<body>
    <h1>Lista degli Utenti</h1>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Stato</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="utente" items="${utenti}">
                <tr>
                    <td>${utente.id}</td>
                    <td>${utente.nome}</td>
                    <td>${utente.email}</td>
                    <td>${utente.stato}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
