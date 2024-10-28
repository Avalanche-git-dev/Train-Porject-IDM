<%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
 <html lang="it">
 <head>
     <meta charset="UTF-8">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
     <!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css"> -->
    <style>
        .user-section{
            border-radius: 5px;
        }


    </style>
 
    </head>
 <body class="bg-light">
 
     <!-- Navbar Admin -->
    <div class="container mt-4 mb-4">
         <div class="d-flex jusitfy-content-between">
             <!-- Tasti della navbar dell'Admin -->
            <div class="container">
                <a href="${pageContext.request.contextPath}/admin">
                    <button type="button" class="btn btn-secondary">Dashboard</button>
                </a>
            </div>
            <div class="container">
            <a href="${pageContext.request.contextPath}/catalogo">
                 <button type="button" class="btn btn-secondary">Catalogo</button>
             </a>
            </div>
            <div class="container">
             <a href="${pageContext.request.contextPath}/market">
                 <button type="button" class="btn btn-secondary">Gestisci Transazioni</button>
             </a>
            </div>
            <div class="container">
            <a href="${pageContext.request.contextPath}/user/logout">
                 <button type="button" class="btn btn-secondary">Logout</button>
             </a>
            </div> 
         </div>
    </div>
 
    <div class="container full-section">
         <!-- Lista Utenti -->
        <div class="container  user-section bg-primary p-4">
            <div class="d-flex justify-conetent-start text-white">
             <h1 class="mr-1">Benvenuto Admin,<h1 class="text-secondary"> ${user.nome}</h1></h1>
            </div>
            <div class="row mt-4">
                <!-- Lista Utenti-->
                <div class="col-*-* ml-3 pr-3 table-responsive">
                    <h2 class="text-white">Lista Utenti</h2>
                        <div class="">
                            <table class="table table-sm text-white table-striped">
                                <thead class="table-dark">
                                    <tr class="text-secondary">
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
                        </div>
                </div>
                    <!-- Lista Utenti Bloccati -->
                <div class="col-*-* ml-3">
                        <h2 class="text-white">Utenti Bloccati</h2>
                        <ul class="text-secondary">
                            <c:forEach var="utenteBloccato" items="${listaUtentiBloccati}">
                                <li>${utenteBloccato.username}</li>
                            </c:forEach>
                        </ul>
                </div>
                <!-- Lista Transazioni -->
                <div class="col-md-12">    
                    <div class="transaction-section text-white">
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
                    </div>
                </div>       
        </div>
    </div>
    </div>
 </body>
 </html>