<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<title>Tutti i Treni Disponibili</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<style>
 body {
        font-family: 'Roboto', sans-serif;
        background-color: #f8f8ff; 
    }</style>
</head>
<body>
    <div class="container">
        <h1 class="mt-3">Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni - barra orizzontale -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="form-inline mb-4">
            <div class="form-row">
                <div class="form-group mx-sm-2">
                    <input type="text" name="nome" placeholder="Nome" value="${filter.nome}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="text" name="sigla" placeholder="Sigla" value="${filter.sigla}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="text" name="marca" placeholder="Marca" value="${filter.marca}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="text" name="usernameProprietario" placeholder="Proprietario" value="${filter.usernameProprietario}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" name="pesoMin" placeholder="Peso Min" value="${filter.pesoMin}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" name="pesoMax" placeholder="Peso Max" value="${filter.pesoMax}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" name="lunghezzaMin" placeholder="Lunghezza Min" value="${filter.lunghezzaMin}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" name="lunghezzaMax" placeholder="Lunghezza Max" value="${filter.lunghezzaMax}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" step="0.1" name="mediaValutazioniMin" placeholder="Media Valutazioni Min" value="${filter.mediaValutazioniMin}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" name="costoTotaleMin" placeholder="Costo Min" value="${filter.costoTotaleMin}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <input type="number" name="costoTotaleMax" placeholder="Costo Max" value="${filter.costoTotaleMax}" class="form-control">
                </div>
                <div class="form-group mx-sm-2">
                    <select name="ordine" class="form-control">
                        <option value="">Ordina per...</option>
                        <option value="sigla" ${filter.ordine == 'sigla' ? 'selected' : ''}>Sigla</option>
                        <option value="prezzo" ${filter.ordine == 'prezzo' ? 'selected' : ''}>Prezzo</option>
                        <option value="lunghezza" ${filter.ordine == 'lunghezza' ? 'selected' : ''}>Lunghezza</option>
                        <option value="peso" ${filter.ordine == 'peso' ? 'selected' : ''}>Peso</option>
                    </select>
                </div>
                <div class="form-group mx-sm-2">
                    <select name="direzione" class="form-control">
                        <option value="ASC" ${filter.direzione == 'ASC' ? 'selected' : ''}>Ascendente</option>
                        <option value="DESC" ${filter.direzione == 'DESC' ? 'selected' : ''}>Discendente</option>
                    </select>
                </div>
                <div class="form-group mx-sm-2">
                    <button type="submit" class="btn btn-primary">Filtra</button>
                </div>
            </div>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8">
                            <h5 class="card-title">${treno.nome}</h5>
                            <p class="card-text">Marca: ${treno.marca}</p>
                            <p class="card-text">Sigla: ${treno.sigla}</p>
                            <p class="card-text">Proprietario: ${sessionScope.usernameOwner}</p>
                            <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                        </div>
                        <div class="col-md-4 text-right">
                            <!-- Form per visualizzare i dettagli del treno -->
                            <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post" class="d-inline">
                                <input type="hidden" name="idTreno" value="${treno.idTreno}" />
                                <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                            </form>
                            <!-- Pulsante per mostrare il form di valutazione -->
                            <button class="btn btn-success ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>
                            <!-- Form di valutazione nascosto inizialmente -->
                            <div id="valutaForm${treno.idTreno}" style="display: none; margin-top: 10px;">
                                <form action="${pageContext.request.contextPath}/catalogo/valutaTreno" method="post">
                                    <input type="hidden" name="trenoId" value="${treno.idTreno}">
                                    <label for="voto${treno.idTreno}">Inserisci valutazione (1-5):</label>
                                    <input type="number" id="voto${treno.idTreno}" name="voto" min="1" max="5" class="form-control" required>
                                    <button type="submit" class="btn btn-primary mt-2">Invia Valutazione</button>
                                </form>
                                <!-- Mostra messaggi di successo o errore se presenti -->
                                <c:if test="${not empty successMessage}">
                                    <div class="alert alert-success mt-2">${successMessage}</div>
                                </c:if>
                                <c:if test="${not empty errorMessage}">
                                    <div class="alert alert-danger mt-2">${errorMessage}</div>
                                </c:if>
                            </div>
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
            form.style.display = (form.style.display === "none") ? "block" : "none";
        }
    </script>
</body>
</html>
 --%>
 
 
 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%-- <%@ include file="navbar.jsp"%> --%>




<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:if test="${not permessi}">
    <jsp:include page="navbar.jsp"/>
</c:if>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        .fixed-width-button { width: 180px; }
        .form-container { display: flex; justify-content: space-between; align-items: center; }
        .custom-form-group { display: flex; }
        .custom-form { display: inline-block; margin-right: 10px; vertical-align: top; }
        .custom-select { width: 200px; }
        .separated-form { margin-left: 30px; }
    </style>
</head>
<body class="bg-light">

<!-- Controllo per mostrare la navbar solo agli utenti con privilegio -->


<div class="container">
    <h1>Tutti i Treni Disponibili</h1>

    <!-- Barra di ricerca con applica filtri -->
    <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="input-group mt-3 mb-3">
        <button type="button" class="btn btn-primary dropdown-toggle fixed-width-button" data-toggle="dropdown">Scegli un'opzione</button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#" data-value="Nome">Nome</a></li>
            <li><a class="dropdown-item" href="#" data-value="Sigla">Sigla</a></li>
            <li><a class="dropdown-item" href="#" data-value="Marca">Marca</a></li>
            <li><a class="dropdown-item" href="#" data-value="Utente">Utente</a></li>
        </ul>
        <input type="text" id="selectedOption" class="form-control" placeholder="Seleziona un'opzione" name="filtroOpzione">
        <input type="hidden" id="hiddenSelectedOption" name="selectedOptionValue">
        <input type="hidden" id="pesoMin" name="pesoMin">
        <input type="hidden" id="pesoMax" name="pesoMax">
        <input type="hidden" id="lunghezzaMin" name="lunghezzaMin">
        <input type="hidden" id="lunghezzaMax" name="lunghezzaMax">
        <input type="hidden" id="mediaValutazioni" name="mediaValutazioni">
        
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="submit">Cerca</button>
        </div>
    </form>

    <div class="form-container mb-3">
        <div class="custom-form-group">
            <!-- Filtro Peso -->
            <form class="custom-form">
                <select name="peso" class="custom-select" id="pesoFilter">
                    <option value="">Peso</option>
                    <option value="0-10000">Da 0 a 10.000</option>
                    <option value="10000-20000">Da 10.000 a 20.000</option>
                    <option value="20000-30000">Da 20.000 a 30.000</option>
                    <option value="30000-40000">Da 30.000 a 40.000</option>
                    <option value="40000-50000">Da 40.000 a 50.000</option>
                </select>
                <input type="hidden" id="pesoMin" name="pesoMin" value="">
                <input type="hidden" id="pesoMax" name="pesoMax" value="">
            </form>

            <!-- Filtro Lunghezza -->
            <form class="custom-form">
                <select name="lunghezza" class="custom-select" id="lunghezzaFilter">
                    <option value="">Lunghezza</option>
                    <option value="0-10000">Da 0 a 10.000</option>
                    <option value="10000-20000">Da 10.000 a 20.000</option>
                    <option value="20000-30000">Da 20.000 a 30.000</option>
                    <option value="30000-40000">Da 30.000 a 40.000</option>
                    <option value="40000-50000">Da 40.000 a 50.000</option>
                </select>
            </form>

            <!-- Filtro Valutazioni -->
            <form class="custom-form">
                <select name="valutazioni" class="custom-select" id="valutazioniFilter">
                    <option value="">Valutazioni</option>
                    <option value="1-3">Da 1 a 3 stelle</option>
                    <option value="2-4">Da 2 a 4 stelle</option>
                    <option value="3-5">Da 3 a 5 stelle</option>
                </select>
            </form>
        </div>

        <!-- Ordinamento -->
        <form class="separated-form">
            <select name="ordinamento" class="custom-select" id="ordinamentoFilter">
                <option value="">Filtra per ordinamento</option>
                <option value="piuPesante">Più pesante</option>
                <option value="menoPesante">Meno pesante</option>
                <option value="piuLungo">Più lungo</option>
                <option value="menoLungo">Meno lungo</option>
                <option value="piuVotato">Più votato</option>
                <option value="menoVotato">Meno votato</option>
            </select>
        </form>
    </div>
    
    
     <c:if test="${not empty sessionScope.successMessage}">
                                    <div class="alert alert-success mt-2">${sessionScope.successMessage}</div>
                                </c:if>
                                <c:if test="${not empty sessionScope.errorMessage}">
                                    <div class="alert alert-danger mt-2">${sessionScope.errorMessage}</div>
                                </c:if>

    <!-- Sezione per visualizzare i treni aggiornati -->
    <div id="elencoTreni">
        <c:forEach var="treno" items="${treni}" varStatus="status">
            <div class="card mb-3 treno" 
                 data-nome="${treno.nome}" 
                 data-sigla="${treno.sigla}" 
                 data-marca="${treno.marca}" 
                 data-peso="${treno.pesoTotale}" 
                 data-lunghezza="${treno.lunghezzaTotale}" 
                 data-media-valutazioni="${treno.mediaValutazioni}"
                 data-utente="${treno.idOwner}">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-8">
                            <h5 class="card-title">${treno.nome}</h5>
                            <p class="card-text">Marca: ${treno.marca}</p>
                            <p class="card-text">Sigla: ${treno.sigla}</p>
                            <p class="card-text">Proprietario: ${treno.idOwner}</p>
                            <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                        </div>
                        <div class="col-md-4 text-right">
                            <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post" class="d-inline">
                                <input type="hidden" name="idTreno" value="${treno.idTreno}" />
                                <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                            </form>

                            <button class="btn btn-secondary ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>

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
            </div>
        </c:forEach>
    </div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function toggleValutaForm(trenoId) {
        var form = document.getElementById("valutaForm" + trenoId);
        form.style.display = form.style.display === "none" ? "block" : "none";
    }

    // Funzione per il pulsante Indietro
    function goBack() {
        window.history.back();
    }

    // Script per dropdown e gestione filtro (escluso per brevità)
</script>
</body>
</html>
