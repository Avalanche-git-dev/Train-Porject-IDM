<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
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
<body class="bg-light">
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
                    <option value="0-50">Da 0 a 50</option>
                    <option value="50-100">Da 50 a 100</option>
                    <option value="100-200">Da 100 a 200</option>
                    <option value="200-500">Da 200 a 500</option>
                    <option value="500-1000">Da 500 a 1.000</option>
                </select>
                <input type="hidden" id="pesoMin" name="pesoMin" value="">
    			<input type="hidden" id="pesoMax" name="pesoMax" value="">
            </form>

            <!-- Filtro Lunghezza -->
            <form class="custom-form">
                <select name="lunghezza" class="custom-select" id="lunghezzaFilter">
                    <option value="">Lunghezza</option>
                    <option value="0-50">Da 0 a 50</option>
                    <option value="50-100">Da 50 a 100</option>
                    <option value="100-200">Da 100 a 200</option>
                    <option value="200-500">Da 200 a 500</option>
                    <option value="500-1000">Da 500 a 1.000</option>
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
    
    document.querySelectorAll('#pesoFilter, #lunghezzaFilter, #valutazioniFilter, #ordinamentoFilter').forEach(function(select) {
        select.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault();
                document.querySelector('.input-group-append button').click(); // Submit the form
            }
        });
    });
    
    document.querySelectorAll('.dropdown-item').forEach(function (item) {
        item.addEventListener('click', function (event) {
            event.preventDefault();
            var selectedValue = this.getAttribute('data-value');
            var input = document.getElementById('selectedOption');
            
            // Imposta il placeholder e il name corretto per l'input in base all'opzione selezionata
            input.placeholder = "Inserisci " + this.textContent;
            input.name = selectedValue;  // Imposta il name su "nome", "sigla", ecc.
            
            // Imposta anche il valore nascosto per poterlo usare nel controller se necessario
            document.getElementById('hiddenSelectedOption').value = selectedValue;
        });
    });
    
    document.getElementById('pesoFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        if (selectedValue) {
            var range = selectedValue.split("-");
            document.getElementById('pesoMin').value = range[0];
            document.getElementById('pesoMax').value = range[1];
            console.log("pesoMin:", document.getElementById('pesoMin').value);
            console.log("pesoMax:", document.getElementById('pesoMax').value);
        } else {
            document.getElementById('pesoMin').value = "";
            document.getElementById('pesoMax').value = "";
        }
    });
    
    document.getElementById('lunghezzaFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        if (selectedValue) {
            var range = selectedValue.split("-");
            document.getElementById('lunghezzaMin').value = range[0];
            document.getElementById('lunghezzaMax').value = range[1];
            console.log("lunghezzaMin:", document.getElementById('lunghezzaMin').value);
            console.log("lunghezzaMax:", document.getElementById('lunghezzaMax').value);
        } else {
            document.getElementById('lunghezzaMin').value = "";
            document.getElementById('lunghezzaMax').value = "";
        }
    });
    
 	// Listener per loggare il valore dell'input nella console
    document.getElementById('selectedOption').addEventListener('input', function() {
        var currentValue = this.value;
        console.log("Valore inserito:", currentValue);
    });
 	
 	// Listener per loggare il valore selezionato per Peso
    document.getElementById('pesoFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        console.log("Peso selezionato:", selectedValue);
    });

    // Listener per loggare il valore selezionato per Lunghezza
    document.getElementById('lunghezzaFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        console.log("Lunghezza selezionata:", selectedValue);
    });

    // Listener per loggare il valore selezionato per Valutazioni
    document.getElementById('valutazioniFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        console.log("Valutazioni selezionate:", selectedValue);
    });

    // Listener per loggare il valore selezionato per Ordinamento
    document.getElementById('ordinamentoFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        console.log("Ordinamento selezionato:", selectedValue);
    });
 	
</script>

<!-- Inclusione di jQuery e Bootstrap JavaScript per far funzionare il menu a tendina -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>