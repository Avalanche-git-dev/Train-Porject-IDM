
 
 
 
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
</head>
<body>

    <!-- Navbar Admin -->
    <div class="navbar bg-primary navbar-primary">
        <div class="btn-btn-secondary">
            <!-- Tasti della navbar dell'Admin -->
            <a href="/admin/dashboard">
                <button type="button" class="btn btn-secondary">Dashboard</button>
            </a>
            <a href="/admin/utenti">
                <button type="button" class="btn btn-secondary">Gestisci Utenti</button>
            </a>
            <a href="/admin/transazioni">
                <button type="button" class="btn btn-secondary">Gestisci Transazioni</button>
            </a>
            <a href="/logout">
                <button type="button" class="btn btn-secondary">Logout</button>
            </a>
        </div>
    </div>

    <div class="container">
        <!-- Colonna centrale: Lista Utenti -->
        <div class="user-section">
            <h1>Benvenuto Admin, ${user.nome}</h1>

            <!-- Sezione: Lista Utenti -->
            <section>
                <h2>Lista Utenti</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID Utente</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Stato</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="utente" items="${listaUtenti}">
                            <tr>
                                <td>${utente.userId}</td>
                                <td>${utente.username}</td>
                                <td>${utente.email}</td>
                                <td>${utente.stato}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Sezione: Lista Utenti Bloccati -->
                <section>
                    <h2>Utenti Bloccati</h2>
                    <ul>
                        <c:forEach var="utenteBloccato" items="${listaUtentiBloccati}">
                            <li>${utenteBloccato.username}</li>
                        </c:forEach>
                    </ul>
                </section>
            </section>
        </div>

        <!-- Colonna destra: Lista Transazioni -->
        <div class="transaction-section">
            <h2>Lista Transazioni</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID Transazione</th>
                        <th>Acquirente</th>
                        <th>Venditore</th>
                        <th>Importo</th>
                        <th>Data</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="transazione" items="${listaTransazioni}">
                        <tr>
                            <td>${transazione.idTransazione}</td>
                            <td>${transazione.acquirenteUsername}</td>
                            <td>${transazione.venditoreUsername}</td>
                            <td>${transazione.importo}</td>
                            <td>${transazione.data}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
 