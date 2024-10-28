
 
 
 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        .user-section { border-radius: 5px; display: none; }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        .sidebar { width: 200px; position: fixed; left: 0; top: 0; height: 100%; background-color: #343a40; color: white; padding-top: 20px; }
        .sidebar a { display: block; color: white; padding: 10px; text-decoration: none; }
        .sidebar a:hover { background-color: #495057; }
        .main-content { margin-left: 220px; padding: 20px; }
        .pagination { display: flex; justify-content: center; margin-top: 20px; }
        
        
        
.sidebar { 
        width: 200px;
        position: fixed;
        left: 0;
        top: 90px; /* Modifica questa riga per spostare la sidebar più in basso */
        height: calc(100% - 70px); /* Aggiorna per adattare l’altezza alla nuova posizione */
        background-color: #343a40;
        color: white;
        padding-top: 20px;
    }

    /* Posiziona la sezione dei filtri più in basso */
    .filter-section {
        position: fixed;
        right: 0;
        top: 90px; /* Modifica questa riga per spostare la sezione dei filtri più in basso */
        width: 200px;
        background-color: #f8f9fa;
        height: calc(100% - 70px); /* Aggiorna per adattare l’altezza alla nuova posizione */
        padding: 20px;
    }        
        
        
        
        .table-responsive { max-height: 400px; overflow-y: hidden; }
        .filter-section input[type="text"] { width: 100%; margin-bottom: 10px; }
        .modal-content { color: black; }
         body {
        font-family: 'Roboto', sans-serif;
        background-color: #f8f8ff; 
    }
    </style>
</head>
<body class="bg-light">

    <!-- Sidebar -->
    <div class="sidebar">
        <h3 class="text-center">Admin Panel</h3>
        <a href="#" onclick="showSection('userList')">Lista Utenti</a>
        <a href="#" onclick="showSection('utentiBloccati')">Utenti Bloccati</a>
        <a href="#" onclick="showSection('transazioni')">Transazioni</a>
    </div>

    <div class="main-content">
    
    <c:if test="${not empty message}">
    <div class="alert ${messageType == 'success' ? 'alert-success' : 'alert-danger'} mx-auto text-center" style="max-width: 800px;">
        ${message}
    </div>
</c:if>
    
        <!-- Lista Utenti con paginazione -->
        <div id="userList" class="container user-section bg-primary p-4" style="display: block;">
            <div class="d-flex justify-content-start text-white">
                <h1 class="mr-1">Benvenuto Admin,</h1><h1 class="text-secondary">${user.nome}</h1>
            </div>
            <h2 class="text-white">Lista Utenti</h2>
            <table class="table table-sm text-white table-striped">
                <thead class="table-dark">
                    <tr class="text-secondary">
                        <th>ID Utente</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Stato</th>
                        <th>Gestione</th>
                    </tr>
                </thead>
                <tbody id="userTableBody">
                    <c:forEach var="utente" items="${listaUtenti}">
                        <tr>
                            <td>${utente.userId}</td>
                            <td>${utente.username}</td>
                            <td>${utente.email}</td>
                            <td>${utente.stato}</td>
                            <td>
                                <button type="button" class="btn btn-light btn-sm" data-toggle="modal" data-target="#gestioneModal${utente.userId}">Gestione</button>
                            </td>
                        </tr>

                        <!-- Modal Gestione -->
                        <div class="modal fade" id="gestioneModal${utente.userId}" tabindex="-1" role="dialog" aria-labelledby="gestioneModalLabel${utente.userId}" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="gestioneModalLabel${utente.userId}">Gestione Utente: ${utente.username}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <%-- <ul class="list-group">
                                            <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/nominaAdmin?userId=${utente.userId}">Nomina Admin</a></li>
                                            <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/bloccaUser?userId=${utente.userId}">Blocca User</a></li>
                                            <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/sbloccaUser?userId=${utente.userId}">Sblocca User</a></li>
                                            <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/controllaTransazioni?userId=${utente.userId}">Controlla Transazioni</a></li>
                                            <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/cercaUtente">Cerca Utente</a></li>
                                        </ul> --%>
                                        
                                        
                                        <ul class="list-group">
                    <li class="list-group-item">
                        <form method="post" action="${pageContext.request.contextPath}/admin/nominaAdmin">
                            <input type="hidden" name="userId" value="${utente.userId}">
                            <button type="submit" class="btn btn-link p-0">Nomina Admin</button>
                        </form>
                    </li>
                    <li class="list-group-item">
                        <form method="post" action="${pageContext.request.contextPath}/admin/blocca">
                            <input type="hidden" name="userId" value="${utente.userId}">
                            <button type="submit" class="btn btn-link p-0">Blocca User</button>
                        </form>
                    </li>
                    <li class="list-group-item">
                        <form method="post" action="${pageContext.request.contextPath}/admin/sblocca">
                            <input type="hidden" name="userId" value="${utente.userId}">
                            <button type="submit" class="btn btn-link p-0">Sblocca User</button>
                        </form>
                    </li>
                    <!-- Altri link di gestione -->
                    <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/controllaTransazioni?userId=${utente.userId}">Controlla Transazioni</a></li>
                    <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/cercaUtente">Cerca Utente</a></li>
                </ul>
            </div>
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                        
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </tbody>
            </table>

            <!-- Paginazione -->
            <div class="pagination justify-content-center">
                <button id="firstBtn" class="btn btn-secondary mx-2">First</button>
                <button id="prevBtn" class="btn btn-secondary mx-2">Prev</button>
                <button id="nextBtn" class="btn btn-secondary mx-2">Next</button>
                <button id="lastBtn" class="btn btn-secondary mx-2">Last</button>
            </div>
        </div>

       <%--  <!-- Lista Transazioni -->
        <div id="transazioni" class="container bg-primary text-white p-4" style="display: none;">
            <h2>Lista Transazioni</h2>
            <table class="table table-responsive table-striped text-white">
                <thead class="table-dark">
                    <tr class="text-secondary">
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
        </div> --%>
        
        
        
        
        
        <!-- Lista Transazioni -->
<!-- Lista Transazioni -->
<div id="transazioni" class="bg-primary text-white p-4" style="display: none; width: 90%; max-width: 875px; margin: auto;">
    <h2>Lista Transazioni</h2>
    <!-- Contenitore esterno con overflow per lo scorrimento -->
    <div style="width: 90%; max-width: 875px; max-height: 650px; overflow-y: auto; margin: auto;">
       <table class="table table-striped text-white" style="width: 100%;">
            <thead class="table-dark">
                <tr class="text-secondary">
                    <th>ID Transazione</th>
                    <th>Acquirente</th>
                    <th>Venditore</th>
                    <th>Importo</th>
                    <th>Data</th>
                    <th>Gestione</th> <!-- Colonna per il tasto di annullamento -->
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
                        <td>
                            <!-- Form per annullare la transazione -->
                            <form method="post" action="${pageContext.request.contextPath}/admin/annulla" style="display: inline;">
                                <input type="hidden" name="idTransazione" value="${transazione.idTransazione}">
                                <button type="submit" class="btn btn-secondary btn-sm">Annulla</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>


        
        
        
        
        
        
        
        
        
        
        
        

        <!-- Lista Utenti Bloccati -->
        <div id="utentiBloccati" class="container bg-primary text-white p-4" style="display: none;">
            <h2>Utenti Bloccati</h2>
            <ul class="text-secondary">
                <c:forEach var="utenteBloccato" items="${listaUtentiBloccati}">
                    <li>${utenteBloccato.username}</li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <!-- Colonna Filtro -->
    <div class="filter-section">
        <h3>Filtra Utenti</h3>
        <input type="text" placeholder="Cerca per ID" />
        <input type="text" placeholder="Cerca per Username" />
        <input type="text" placeholder="Cerca per Email" />
        <button class="btn btn-secondary btn-block mt-2">Filtra</button>
    </div>

    <!-- JavaScript per cambiare le sezioni nella sidebar -->
    <script>
        function showSection(sectionId) {
            document.getElementById('userList').style.display = 'none';
            document.getElementById('transazioni').style.display = 'none';
            document.getElementById('utentiBloccati').style.display = 'none';
            document.getElementById(sectionId).style.display = 'block';
        }

        const rowsPerPage = 15;
        let currentPage = 0;

        function renderPage(page) {
            const rows = document.querySelectorAll("#userTableBody tr");
            const totalPages = Math.ceil(rows.length / rowsPerPage);
            const start = page * rowsPerPage;
            const end = start + rowsPerPage;

            rows.forEach((row, index) => {
                row.style.display = (index >= start && index < end) ? "table-row" : "none";
            });

            document.getElementById("firstBtn").disabled = (page === 0);
            document.getElementById("prevBtn").disabled = (page === 0);
            document.getElementById("nextBtn").disabled = (page === totalPages - 1);
            document.getElementById("lastBtn").disabled = (page === totalPages - 1);
        }

        document.getElementById("firstBtn").addEventListener("click", () => {
            currentPage = 0;
            renderPage(currentPage);
        });

        document.getElementById("prevBtn").addEventListener("click", () => {
            if (currentPage > 0) {
                currentPage--;
                renderPage(currentPage);
            }
        });

        document.getElementById("nextBtn").addEventListener("click", () => {
            const totalPages = Math.ceil(document.querySelectorAll("#userTableBody tr").length / rowsPerPage);
            if (currentPage < totalPages - 1) {
                currentPage++;
                renderPage(currentPage);
            }
        });

        document.getElementById("lastBtn").addEventListener("click", () => {
            const totalPages = Math.ceil(document.querySelectorAll("#userTableBody tr").length / rowsPerPage);
            currentPage = totalPages - 1;
            renderPage(currentPage);
        });

        document.addEventListener("DOMContentLoaded", () => renderPage(currentPage));
    </script>

    <!-- Inclusione di Bootstrap JavaScript -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
</body>
</html>

