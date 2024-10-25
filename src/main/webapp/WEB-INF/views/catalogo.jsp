<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .fixed-width-button {
            width: 180px;
        }
        .form-container {
            display: flex;
            justify-content: space-between;
            align-items: center; 
        }
        .custom-form-group {
            display: flex; 
        }
        .custom-form {
            display: inline-block;
            margin-right: 10px; 
            vertical-align: top; 
        }
        .custom-select {
            width: 200px; 
        }
        .separated-form {
            margin-left: 30px; 
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Tutti i Treni Disponibili</h1>

    <!-- Barra di ricerca con applica filtri -->
    <form action="${pageContext.request.contextPath}/filtro" method="get" class="input-group mt-3 mb-3">
        <button type="button" class="btn btn-primary dropdown-toggle fixed-width-button" data-toggle="dropdown">Scegli un'opzione</button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#" data-value="Nome">Nome</a></li>
            <li><a class="dropdown-item" href="#" data-value="Sigla">Sigla</a></li>
            <li><a class="dropdown-item" href="#" data-value="Marca">Marca</a></li>
            <li><a class="dropdown-item" href="#" data-value="Utente">Utente</a></li>
        </ul>

        <input type="text" id="selectedOption" class="form-control" placeholder="Seleziona un'opzione" name="filtroOpzione">
        <input type="hidden" id="hiddenSelectedOption" name="selectedOptionValue">

        <!-- Pulsante per applicare i filtri -->
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
                            <p class="card-text">Propietario: ${treno.idOwner}</p>
                            <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                        </div>
                        <div class="col-md-4 text-right">
                            <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post" class="d-inline">
                                <input type="hidden" name="idTreno" value="${treno.idTreno}" />
                                <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                            </form>

                            <button class="btn btn-success ml-2" onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>

                            <div id="valutaForm${treno.idTreno}" style="display: none; margin-top: 10px;">
                                <form action="${pageContext.request.contextPath}/catalogo/valutaTreno" method="post">
                                    <input type="hidden" name="trenoId" value="${treno.idTreno}">
                                    <label for="voto${treno.idTreno}">Inserisci la tua valutazione (1-5):</label>
                                    <input type="number" id="voto${treno.idTreno}" name="voto" min="1" max="5" class="form-control" required>
                                    <button type="submit" class="btn btn-primary mt-2">Invia Valutazione</button>
                                </form>

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
</div>

<script>
    // Funzione per mostrare/nascondere il form di valutazione
    function toggleValutaForm(trenoId) {
        var form = document.getElementById("valutaForm" + trenoId);
        form.style.display = form.style.display === "none" ? "block" : "none";
    }

    // Aggiungiamo l'event listener per gli elementi del dropdown
    document.querySelectorAll('.dropdown-item').forEach(function(item) {
        item.addEventListener('click', function() {
            var selectedValue = this.getAttribute('data-value');
            document.getElementById('selectedOption').value = selectedValue;
            document.getElementById('hiddenSelectedOption').value = selectedValue;
            document.querySelector('.btn.dropdown-toggle').textContent = selectedValue;
        });
    });

    // Listener per cancellare il valore dell'input quando viene cliccato
    document.getElementById('selectedOption').addEventListener('focus', function() {
        this.value = '';  // Cancella il valore corrente dell'input visibile
    });
    
    document.getElementById('selectedOption').addEventListener('focus', function() {
    	if (event.key === 'Enter') {
    		event.preventDefault();
    		document.querySelector('.input-group-append button').click();
    	}
    })
    
    document.getElementById('pesoFilter').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault(); // Previene l'azione di default per evitare refresh o altre azioni
            document.querySelector('.input-group-append button').click();
        }
    });
    
    document.getElementById('lunghezzaFilter').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault(); // Previene l'azione di default per evitare refresh o altre azioni
            document.querySelector('.input-group-append button').click();
        }
    });
    
    document.getElementById('valutazioniFilter').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            document.querySelector('.input-group-append button').click();
        }
    });
    
    document.getElementById('ordinamentoFilter').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            document.querySelector('.input-group-append button').click();
        }
    });
</script>

<!-- Inclusione di jQuery e Bootstrap JavaScript per far funzionare il menu a tendina -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>