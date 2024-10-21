 

  
<%--   
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVenditaMax}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
                 <div class="form-group col-md-3">
                    <label for="nomeOwner">Propietario</label>
                    <input type="text" id="NomeOwner" name="nomeOwner" value="${trenoFilter.nomeOwner}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                    <!-- Pulsante per Vedere i Dettagli del Treno -->
                    <a href="${pageContext.request.contextPath}/treni/visualizza/treno" class="btn btn-primary">Vedi Dettagli</a>
                    <!-- Pulsante "Valuta" -->
                    <a href="${pageContext.request.contextPath}/catalogo/valutaTreno" class="btn btn-success ml-2">Valuta</a>
                    
                    
                    
                </div>
            </div>
        </c:forEach>
    </div>
    
    
    
    
    <script>
    
    //Convertitore tutta la vita finchè non imparo sintassi
        function toggleValutaForm(trenoId) {
            var form = document.getElementById("valutaForm" + trenoId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
</body>
</html>

 --%>

<%-- 
<%@page import="org.springframework.ui.Model"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVenditaMax}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
                 <div class="form-group col-md-3">
                    <label for="nomeOwner">Propietario</label>
                    <input type="text" id="NomeOwner" name="nomeOwner" value="${trenoFilter.nomeOwner}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                    <!-- Pulsante per Vedere i Dettagli del Treno -->
                    <a href="${pageContext.request.contextPath}/treni/visualizza/treno" class="btn btn-primary">Vedi Dettagli</a>
                    
                    <!-- Pulsante "Valuta" -->
                    <div>
                        <button class="btn btn-success ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>

                        <!-- Form di valutazione, inizialmente nascosto -->
                        <div id="valutaForm${treno.idTreno}" style="display: none; margin-top: 10px;">
                            <form action="${pageContext.request.contextPath}/catalogo/valutaTreno" method="post">
                                <input type="hidden" name="trenoId" value="${treno.idTreno}">
                                <label for="voto${treno.idTreno}">Inserisci la tua valutazione (1-5):</label>
                                <input type="number" id="voto${treno.idTreno}" name="voto" min="1" max="5" class="form-control" required>
                                <button type="submit" class="btn btn-primary mt-2">Invia Valutazione</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <script>
        // Funzione JavaScript per mostrare/nascondere il form di valutazione
        function toggleValutaForm(trenoId) {
            var form = document.getElementById("valutaForm" + trenoId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
</body>
</html>

 --%>



<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVenditaMax}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="nomeOwner">Proprietario</label>
                    <input type="text" id="NomeOwner" name="nomeOwner" value="${trenoFilter.nomeOwner}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>

                    <!-- Form per vedere i dettagli del treno -->
                    <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post">
                        <input type="hidden" name="treno.nome" value="${treno.nome}">
                        <input type="hidden" name="treno.marca" value="${treno.marca}">
                        <input type="hidden" name="treno.idTreno" value="${treno.idTreno}">
                        <input type="hidden" name="treno.mediaValutazioni" value="${treno.mediaValutazioni}">
                        <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                    </form>

                    <!-- Pulsante "Valuta" -->
                    <div>
                        <button class="btn btn-success ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>

                        <!-- Form di valutazione, inizialmente nascosto -->
                        <div id="valutaForm${treno.idTreno}" style="display: none; margin-top: 10px;">
                            <form action="${pageContext.request.contextPath}/catalogo/valutaTreno" method="post">
                                <input type="hidden" name="treno.idTreno" value="${treno.idTreno}">
                                <label for="voto${treno.idTreno}">Inserisci la tua valutazione (1-5):</label>
                                <input type="number" id="voto${treno.idTreno}" name="voto" min="1" max="5" class="form-control" required>
                                <button type="submit" class="btn btn-primary mt-2">Invia Valutazione</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <script>
        // Funzione JavaScript per mostrare/nascondere il form di valutazione
        function toggleValutaForm(trenoId) {
            var form = document.getElementById("valutaForm" + trenoId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
</body>
</html>


   --%>
   
   
<%--    
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVenditaMax}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
                 <div class="form-group col-md-3">
                    <label for="nomeOwner">Proprietario</label>
                    <input type="text" id="NomeOwner" name="nomeOwner" value="${trenoFilter.nomeOwner}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body d-flex justify-content-between">
                    <div>
                        <h5 class="card-title">${treno.nome}</h5>
                        <p class="card-text">Marca: ${treno.marca}</p>
                        <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                        <!-- Pulsante per Vedere i Dettagli del Treno -->
                        <a href="${pageContext.request.contextPath}/treni/visualizza/treno" class="btn btn-primary">Vedi Dettagli</a>
                    </div>
                    
                    <!-- Nuovo Div per il pulsante "Valuta" a destra -->
                    <div class="valuta-section">
                        <button class="btn btn-success ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>
                        <!-- Form di valutazione, inizialmente nascosto -->
                        <div id="valutaForm${treno.idTreno}" style="display: none; margin-top: 10px;">
                            <form action="${pageContext.request.contextPath}/catalogo/valutaTreno" method="post">
                                <input type="hidden" name="treno.idTreno" value="${treno.idTreno}">
                                <label for="voto${treno.idTreno}">Inserisci la tua valutazione (1-5):</label>
                                <input type="number" id="voto${treno.idTreno}" name="voto" min="1" max="5" class="form-control" required>
                                <button type="submit" class="btn btn-primary mt-2">Invia Valutazione</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <script>
        // Funzione per mostrare/nascondere il form di valutazione
        function toggleValutaForm(trenoId) {
            var form = document.getElementById("valutaForm" + trenoId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
</body>
</html> --%>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVenditaMax}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
                 <div class="form-group col-md-3">
                    <label for="nomeOwner">Proprietario</label>
                    <input type="text" id="NomeOwner" name="nomeOwner" value="${trenoFilter.nomeOwner}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body d-flex justify-content-between">
                    <div>
                        <h5 class="card-title">${treno.nome}</h5>
                        <p class="card-text">Marca: ${treno.marca}</p>
                        <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                        <!-- Pulsante per Vedere i Dettagli del Treno -->
                        <a href="${pageContext.request.contextPath}/treni/visualizza/treno" class="btn btn-primary">Vedi Dettagli</a>
                    </div>
                    
                    <!-- Nuovo Div per il pulsante "Valuta" a destra -->
                    <div class="valuta-section">
                        <button class="btn btn-success ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>
                        <!-- Form di valutazione, inizialmente nascosto -->
                        <div id="valutaForm${treno.idTreno}" style="display: none; margin-top: 10px;">
                            <form action="${pageContext.request.contextPath}/catalogo/valutaTreno" method="post">
                                <!-- Passaggio dell'ID del treno come input nascosto -->
                                <input type="hidden" name="trenoId" value="${treno.idTreno}">
                                <label for="voto${treno.idTreno}">Inserisci la tua valutazione (1-5):</label>
                                <input type="number" id="voto${treno.idTreno}" name="voto" min="1" max="5" class="form-control" required>
                                <button type="submit" class="btn btn-primary mt-2">Invia Valutazione</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <script>
        // Funzione per mostrare/nascondere il form di valutazione
        function toggleValutaForm(trenoId) {
            var form = document.getElementById("valutaForm" + trenoId);
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
</body>
</html>

   