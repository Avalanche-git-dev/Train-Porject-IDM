<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Treni</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
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
<body>

	<div class="container">
    <h1>Tutti i treni in vendita</h1>
    
    <form action="${pageContext.request.contextPath}/market/filtro" method="get" class="input-group mt-3 mb-3">
        <button type="button" class="btn btn-primary dropdown-toggle fixed-width-button" data-toggle="dropdown">Scegli un'opzione</button>
        <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#" data-value="Nome">Nome</a></li>
            <li><a class="dropdown-item" href="#" data-value="Sigla">Sigla</a></li>
            <li><a class="dropdown-item" href="#" data-value="Marca">Marca</a></li>
        </ul>

        <input type="text" id="selectedOption" class="form-control" placeholder="Seleziona un'opzione" name="filtroOpzione">
        <input type="hidden" id="hiddenSelectedOption" name="selectedOptionValue">
        <input type="hidden" id="pesoMin" name="pesoMin">
    	<input type="hidden" id="pesoMax" name="pesoMax">
    	<input type="hidden" id="lunghezzaMin" name="lunghezzaMin">
    	<input type="hidden" id="lunghezzaMax" name="lunghezzaMax">
    	<input type="hidden" id="valutazioneMin" name="valutazioneMin">
    	<input type="hidden" id="valutazioneMax" name="valutazioneMax">
    	<input type="hidden" id="prezzoMin" name="prezzoMin">
		<input type="hidden" id="prezzoMax" name="prezzoMax">
		
		<input type="hidden" id="ordinamento" name="ordinamento" value="pesoTotale">
    	<input type="hidden" id="ascendente" name="ascendente" value="false">
    	

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
        
        	<!-- Prezzo -->
        	<form class="custom-form">
                <select name="prezzo" class="custom-select" id="prezzoFilter">
                    <option value="">Prezzo</option>
                    <option value="0-10000">Da 0 a 10.000</option>
                    <option value="10000-20000">Da 10.000 a 20.000</option>
                    <option value="20000-30000">Da 20.000 a 30.000</option>
                    <option value="30000-40000">Da 30.000 a 40.000</option>
                    <option value="40000-50000">Da 40.000 a 50.000</option>
                </select>
            </form>
        </div>
        
        <!-- Ordinamento -->
        <form class="separated-form">
            <select name="ordinamento" class="custom-select" id="ordinamentoFilter">
                <option value="">Filtra per ordinamento</option>
                <option value="piuPesante-true">Più pesante</option>
                <option value="menoPesante-false">Meno pesante</option>
                <option value="piuLungo-true">Più lungo</option>
                <option value="menoLungo-false">Meno lungo</option>
                <option value="piuVotato-true">Più votato</option>
                <option value="menoVotato-false">Meno votato</option>
                <option value="piuCostoso-true">Più costoso</option>
                <option value="menoCostoso-false">Meno costoso</option>
            </select>
        </form>
    </div>
    
    <!-- Sezione per visualizzare i treni aggiornati -->
    <div id="elencoTreni">
        <c:forEach var="treno" items="${treniInVendita}" varStatus="status">
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
                            <form action="${pageContext.request.contextPath}/market/acquista" method="post" class="d-inline">
                            	<input type="hidden" name="idTreno" value="${treno.idTreno}"/>
                            	<button type="submit" class="btn btn-success ml-2">Acquista Treno</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

	<!-- Footer -->
	<footer class="text-center">
		<p>&copy; 2024 YourWebsite. All rights reserved.</p>
	</footer>
</div>

<script>
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
    
    document.querySelectorAll('#pesoFilter, #lunghezzaFilter, #valutazioniFilter, #ordinamentoFilter, #prezzoFilter').forEach(function(select) {
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
    
    document.getElementById('valutazioniFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        if (selectedValue) {
            var range = selectedValue.split("-");
            document.getElementById('valutazioneMin').value = range[0];
            document.getElementById('valutazioneMax').value = range[1];
            console.log("valutazioneMin:", document.getElementById('valutazioneMin').value);
            console.log("valutazioneMax:", document.getElementById('valutazioneMax').value);
        } else {
            document.getElementById('valutazioneMin').value = "";
            document.getElementById('valutazioneMax').value = "";
        }
    });
    
    document.getElementById('prezzoFilter').addEventListener('change', function() {
        var selectedValue = this.value;
        if (selectedValue) {
            var range = selectedValue.split("-");
            document.getElementById('prezzoMin').value = range[0];
            document.getElementById('prezzoMax').value = range[1];
            console.log("prezzoMin:", document.getElementById('prezzoMin').value);
            console.log("prezzoMax:", document.getElementById('prezzoMax').value);
        } else {
            document.getElementById('prezzoMin').value = "";
            document.getElementById('prezzoMax').value = "";
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
    
    document.getElementById('prezzoFilter').addEventListener('change', function() {
    	var selectedValue = this.value;
    	console.log("Prezzo selezionato:", selectedValue);
    });
    
 	// Event listener per il filtro ordinamento
    document.getElementById('ordinamentoFilter').addEventListener('change', function() {
    	var selectedValue = this.value.split('-');
        document.getElementById('ordinamento').value = selectedValue[0];
        document.getElementById('ascendente').value = selectedValue[1];
        
        console.log("Ordinamento selezionato:", selectedValue[0]);
        console.log("Direzione ascendente:", selectedValue[1]);
    });
 	
</script>

	<!-- Inclusione di jQuery e Bootstrap JavaScript per far funzionare il menu a tendina -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
