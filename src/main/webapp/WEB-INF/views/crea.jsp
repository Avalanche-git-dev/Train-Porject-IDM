<%-- 
 
 
 
 
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Treni - Crea e Modifica Treno</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        .btn-modifica {
            background-color: #28a745; /* Verde per la modifica */
            color: white;
        }
        #modificaPanel {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
    </style>
    <script>
        function validaTreno(input) {
            if (input[0] !== 'H') {
                return false;
            }
            if (input[1] !== 'P' && input[1] !== 'C') {
                return false;
            }
            let rCount = 0;
            for (let i = 0; i < input.length; i++) {
                if (input[i] === 'R') {
                    rCount++;
                }
            }
            if (rCount > 1) {
                return false;
            }
            return true;
        }

        function confermaCreazione(event) {
            event.preventDefault();
            var inputTreno = document.getElementById("input").value;

            if (!validaTreno(inputTreno)) {
                alert("Errore: La stringa del treno deve iniziare con 'H', la seconda lettera deve essere 'P' o 'C', e può contenere una sola 'R'.");
                return false;
            }

            var conferma = confirm("Treno creato con successo! Vuoi procedere?");
            if (conferma) {
                document.getElementById("creaTrenoForm").submit();
            }
        }

        function toggleModificaTreno() {
            const modificaPanel = document.getElementById("modificaPanel");
            modificaPanel.style.display = (modificaPanel.style.display === "none" || modificaPanel.style.display === "") ? "block" : "none";
        }

        function confermaModifica(event) {
            event.preventDefault();
            var inputModifica = document.getElementById("modificaInput").value;

            if (!validaTreno(inputModifica)) {
                alert("Errore: La stringa del treno deve iniziare con 'H', la seconda lettera deve essere 'P' o 'C', e può contenere una sola 'R'.");
                return false;
            }

            var conferma = confirm("Vuoi salvare le modifiche al treno?");
            if (conferma) {
                document.getElementById("modificaTrenoForm").submit();
            }
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <h2>Gestione Treni</h2>

    <!-- Gestione messaggi di conferma o errori -->
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <!-- Form per la creazione del treno -->
    <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea/guest" method="post" onsubmit="confermaCreazione(event)">
        <div class="form-group">
            <label for="nomeTreno">Nome Treno</label>
            <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
        </div>
        <div class="form-group">
            <label for="input">Sigla Treno</label>
            <input type="text" class="form-control" id="input" name="input" required
                   title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                   placeholder="Es. HPPPP o HCCCCC, una R">
        </div>
        <div class="form-group">
            <label for="marca">Marca</label>
            <select class="form-control" id="marca" name="marca" required>
                <option value="Italiano">Italiano</option>
                <option value="Francese">Francese</option>
                <option value="Tedesco">Tedesco</option>
            </select>
        </div>
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Crea Treno</button>
            <c:if test="${guest != null}">
                <a href="${pageContext.request.contextPath}/user/registrati" class="btn btn-warning">Registrati</a>
            </c:if>
        </div>
    </form>

    <!-- Bottone per modificare un treno esistente, visibile solo se non guest -->
    <c:if test="${guest == null}">
        <button class="btn btn-modifica mt-4" onclick="toggleModificaTreno()">Modifica Treno</button>
    </c:if>

    <button class="btn btn-primary back-button mt-4" onclick="window.history.back()">Indietro</button>

    <!-- Pannello per la modifica del treno -->
    <c:if test="${guest != null}">
        <div id="modificaPanel" style="display: none;">
            <form id="modificaTrenoForm" action="${pageContext.request.contextPath}/treni/modifica" method="post" onsubmit="confermaModifica(event)">
                <div class="form-group">
                    <label for="trenoId">ID Treno</label>
                    <input type="text" class="form-control" id="trenoId" name="trenoId" required
                           placeholder="Inserisci l'ID del treno da modificare">
                </div>
                <div class="form-group">
                    <label for="modificaNome">Nome Treno</label>
                    <input type="text" class="form-control" id="modificaNome" name="modificaNome" required
                           placeholder="Inserisci il nuovo nome del treno">
                </div>
                <div class="form-group">
                    <label for="modificaInput">Sigla Treno</label>
                    <input type="text" class="form-control" id="modificaInput" name="modificaInput" required
                           title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                           placeholder="Es. HPPPP o HCCCCC, una R">
                </div>
                <button type="submit" class="btn btn-success">Salva Modifiche</button>
            </form>
        </div>
    </c:if>
</div>

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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Treni - Crea e Modifica Treno</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        .container {
            display: flex;
            gap: 20px;
            margin-top: 40px;
        }
        .form-section, .edit-section {
            flex: 1;
            padding: 20px;
            border-radius: 8px;
            background-color: #f9f9f9;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-section h3, .edit-section h3 {
            color: #333;
            margin-bottom: 20px;
        }
        .btn-create, .btn-modify, .btn-action {
            width: 100%;
            margin-top: 15px;
        }
        .actions-panel {
            display: none;
            margin-top: 20px;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
    </style>
    <script>
        function toggleActionsPanel() {
            const panel = document.getElementById("actionsPanel");
            panel.style.display = panel.style.display === "none" || panel.style.display === "" ? "block" : "none";
        }

        function confermaRicerca(event) {
            event.preventDefault();
            // Verifica credenziali (username e password) qui o nel backend.
            toggleActionsPanel();
        }
    </script>
</head>
<body>

<div class="container">
    <!-- Sezione sinistra: Creazione Treno -->
    <div class="form-section">
        <h3>Crea Nuovo Treno</h3>
        <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea" method="post">
            <div class="form-group">
                <label for="nomeTreno">Nome Treno</label>
                <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
            </div>
            <div class="form-group">
                <label for="input">Sigla Treno</label>
                <input type="text" class="form-control" id="input" name="input" required
                       title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                       placeholder="Es. HPPPP o HCCCCC, una R">
            </div>
            <div class="form-group">
                <label for="marca">Marca</label>
                <select class="form-control" id="marca" name="marca" required>
                    <option value="Italiano">Italiano</option>
                    <option value="Francese">Francese</option>
                    <option value="Tedesco">Tedesco</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-create">Crea Treno</button>
        </form>
    </div>

    <!-- Sezione destra: Modifica Treno -->
    <div class="edit-section">
        <h3>Modifica Treno</h3>
        <!-- Form di Ricerca -->
        <form id="ricercaTrenoForm" action="${pageContext.request.contextPath}/treni/ricerca" method="post" onsubmit="confermaRicerca(event)">
            <div class="form-group">
                <label for="trainName">Nome Treno</label>
                <input type="text" class="form-control" id="trainName" name="trainName" required placeholder="Inserisci il nome del treno">
            </div>
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" required placeholder="Inserisci il tuo username">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="Inserisci la tua password">
            </div>
            <button type="submit" class="btn btn-primary btn-modify">Cerca e Modifica</button>
        </form>

        <!-- Pannello di Azioni (inizialmente nascosto) -->
        <div id="actionsPanel" class="actions-panel">
            <h4>Azioni Disponibili</h4>
            <form action="${pageContext.request.contextPath}/treni/inverti" method="post">
                <input type="hidden" name="trainName" value="${trainName}">
                <button type="submit" class="btn btn-warning btn-action">Inverti Treno</button>
            </form>
            <form action="${pageContext.request.contextPath}/treni/copia" method="post">
                <input type="hidden" name="trainName" value="${trainName}">
                <button type="submit" class="btn btn-success btn-action">Copia Treno</button>
            </form>
            <form action="${pageContext.request.contextPath}/treni/aggiungiVagone" method="post">
                <input type="hidden" name="trainName" value="${trainName}">
                <button type="submit" class="btn btn-primary btn-action">Aggiungi Vagone</button>
            </form>
            <form action="${pageContext.request.contextPath}/treni/rimuoviVagone" method="post">
                <input type="hidden" name="trainName" value="${trainName}">
                <button type="submit" class="btn btn-danger btn-action">Rimuovi Vagone</button>
            </form>
        </div>
    </div>
</div>

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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Treni - Crea Treno</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        /* Posizionamento del contenitore principale per la creazione del treno */
        .container {
            display: flex;
            justify-content: flex-start;
            margin-top: 30px; /* Margine dall'alto */
            margin-left: 30px; /* Margine dal lato sinistro */
        }

        /* Sezione Creazione Treno */
        .form-section {
            width: 100%; /* Occupare l’intera larghezza */
            max-width: 500px; /* Limitare la larghezza massima */
            padding: 20px;
            border-radius: 8px;
            background-color: #f9f9f9;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-section h3 {
            color: #333;
            margin-bottom: 20px;
        }

        /* Bottoni */
        .btn-create {
            width: 100%;
            margin-top: 15px;
        }

        /* Dettagli del treno appena creato */
        .train-details-card {
            margin-top: 20px;
            padding: 20px;
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        
         body {
        font-family: 'Roboto', sans-serif;
        background-color: #f8f8ff; 
    }
    </style>
</head>
<body>

<div class="container">
    <!-- Sezione: Creazione Treno -->
    <div class="form-section">
        <h3>Crea Nuovo Treno</h3>
        <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea" method="post">
            <div class="form-group">
                <label for="nomeTreno">Nome Treno</label>
                <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
            </div>
            <div class="form-group">
                <label for="input">Sigla Treno</label>
                <input type="text" class="form-control" id="input" name="input" required
                       title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                       placeholder="Es. HPPPP o HCCCCC, una R">
            </div>
            <div class="form-group">
                <label for="marca">Marca</label>
                <select class="form-control" id="marca" name="marca" required>
                    <option value="Italiano">Italiano</option>
                    <option value="Francese">Francese</option>
                    <option value="Tedesco">Tedesco</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-create">Crea Treno</button>
        </form>
        
        <c:if test="${not empty errorMessage}">
			<div class="text-danger">${errorMessage}</div>
		</c:if>

        <!-- Card dei dettagli del treno, visibile solo dopo la creazione -->
        <c:if test="${not empty nuovoTreno}">
            <div class="train-details-card">
                <h4>Dettagli del Treno Creato</h4>
                <p><strong>Nome:</strong> ${nuovoTreno.nome}</p>
                <p><strong>Sigla:</strong> ${nuovoTreno.sigla}</p>
                <p><strong>Marca:</strong> ${nuovoTreno.marca}</p>
                <img src="${nuovoTreno.immagine}" alt="Immagine del Treno" class="train-image" style="max-width: 100%; height: auto; border-radius: 5px;">
            </div>
        </c:if>
    </div>
</div>



</body>
</html> --%>

<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Treni - Crea Treno</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    
    <style>
        /* Layout della pagina */
        .page-content {
            display: flex;
            justify-content: space-between;
            padding: 20px;
        }

        /* Container Creazione Treno (a sinistra) */
        .form-section-container {
            width: 50%;
            margin-right: 20px;
        }

        /* Container Lista Treni (a destra) */
        .train-list-container {
            width: 20%; /* Ridotta come richiesto */
            margin-left: auto;
        }

        /* Styling del form di creazione */
        .form-section {
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-section h3 {
            color: #333;
            margin-bottom: 20px;
        }

        /* Styling delle card */
        .train-card {
            margin-bottom: 15px;
            padding: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        /* Modal Overlay Styling */
        .custom-modal, .operation-modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
            z-index: 1000;
        }

        .custom-modal-content, .operation-modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            max-width: 400px;
            width: 90%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .close-button {
            float: right;
            font-size: 1.5rem;
            font-weight: bold;
            cursor: pointer;
            color: #333;
        }
    </style>
</head>
<body>

<div class="page-content">
    <!-- Sezione: Creazione Treno (a sinistra) -->
    <div class="form-section-container">
        <div class="form-section">
            <h3>Crea Nuovo Treno</h3>
            <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea" method="post">
                <div class="form-group">
                    <label for="nomeTreno">Nome Treno</label>
                    <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
                </div>
                <div class="form-group">
                    <label for="input">Sigla Treno</label>
                    <input type="text" class="form-control" id="input" name="input" required
                           title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                           placeholder="Es. HPPPP o HCCCCC, una R">
                </div>
                <div class="form-group">
                    <label for="marca">Marca</label>
                    <select class="form-control" id="marca" name="marca" required>
                        <option value="Italiano">Italiano</option>
                        <option value="Francese">Francese</option>
                        <option value="Tedesco">Tedesco</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary btn-create">Crea Treno</button>
            </form>
            
            <c:if test="${not empty errorMessage}">
                <div class="text-danger">${errorMessage}</div>
            </c:if>

            <!-- Dettagli del treno appena creato -->
            <c:if test="${not empty nuovoTreno}">
                <div class="train-details-card">
                    <h4>Dettagli del Treno Creato</h4>
                    <p><strong>Nome:</strong> ${nuovoTreno.nome}</p>
                    <p><strong>Sigla:</strong> ${nuovoTreno.sigla}</p>
                    <p><strong>Marca:</strong> ${nuovoTreno.marca}</p>
                    <img src="${nuovoTreno.immagine}" alt="Immagine del Treno" class="train-image" style="max-width: 100%; height: auto; border-radius: 5px;">
                </div>
            </c:if>
        </div>
    </div>

    <!-- Sezione Lista Treni (a destra) -->
    <div class="train-list-container">
        <c:forEach var="treno" items="${listaTreniUtente}">
            <div class="card train-card">
                <h5 class="card-title">${treno.nome}</h5>
                <button type="button" class="btn btn-primary btn-sm" onclick="openModal(${treno.idTreno})">Modifica</button>
            </div>

            <!-- Modal principale per le opzioni -->
            <div id="modal-${treno.idTreno}" class="custom-modal">
                <div class="custom-modal-content">
                    <span class="close-button" onclick="closeModal(${treno.idTreno})">&times;</span>
                    <h5>Seleziona Operazione per ${treno.nome}</h5>
                    <button class="btn btn-primary" onclick="openOperationModal('aggiungiVagone', ${treno.idTreno})">Aggiungi Vagone</button>
                    <button class="btn btn-warning" onclick="openOperationModal('rimuoviVagone', ${treno.idTreno})">Rimuovi Vagone</button>
                    <button class="btn btn-secondary" onclick="openOperationModal('copiaTreno', ${treno.idTreno})">Copia Treno</button>
                    <button class="btn btn-danger" onclick="openOperationModal('cancellaTreno', ${treno.idTreno})">Cancella Treno</button>
                    <button class="btn btn-success" onclick="openOperationModal('invertiTreno', ${treno.idTreno})">Inverti Treno</button>
                </div>
            </div>

            <!-- Modal specifico per Aggiungi Vagone -->
            <div id="operation-aggiungiVagone-${treno.idTreno}" class="operation-modal">
                <div class="operation-modal-content">
                    <span class="close-button" onclick="closeOperationModal('aggiungiVagone', ${treno.idTreno})">&times;</span>
                    <h5>Aggiungi Vagone a ${treno.nome}</h5>
                    <form action="${pageContext.request.contextPath}/treni/modifica/aggiungi" method="post">
                        <input type="hidden" name="idTrenoM" value="${treno.idTreno}">
                        <div class="form-group">
                            <label for="tipoVagone_${treno.idTreno}">Tipo di Vagone</label>
                            <select class="form-control" id="tipoVagone_${treno.idTreno}" name="tipoVagone" required>
                                <option value="Passeggeri">Passeggeri</option>
                                <option value="Ristorante">Ristorante</option>
                                <option value="Cargo">Cargo</option>
                                <option value="Motrice">Motrice</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Conferma Aggiunta</button>
                    </form>
                </div>
            </div>

            <!-- Modal specifico per Rimuovi Vagone -->
            <div id="operation-rimuoviVagone-${treno.idTreno}" class="operation-modal">
                <div class="operation-modal-content">
                    <span class="close-button" onclick="closeOperationModal('rimuoviVagone', ${treno.idTreno})">&times;</span>
                    <h5>Rimuovi Vagone da ${treno.nome}</h5>
                    <form action="${pageContext.request.contextPath}/treni/modifica/rimuovi" method="post">
                        <input type="hidden" name="idTrenoM" value="${treno.idTreno}">
                        <div class="form-group">
                            <label for="selezionaVagone_${treno.idTreno}">Seleziona Vagone da Rimuovere</label>
                            <select class="form-control" id="selezionaVagone_${treno.idTreno}" name="idVagone" required>
                                <c:forEach var="vagone" items="${listaVagoni}">
                                    <option value="${vagone.id}">${vagone.nome} - Posizione: ${vagone.posizione}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-warning">Conferma Rimozione</button>
                    </form>
                </div>
            </div>

            <!-- Modal per Copia Treno -->
            <div id="operation-copiaTreno-${treno.idTreno}" class="operation-modal">
                <div class="operation-modal-content">
                    <span class="close-button" onclick="closeOperationModal('copiaTreno', ${treno.idTreno})">&times;</span>
                    <h5>Copia Treno - ${treno.nome}</h5>
                    <form action="${pageContext.request.contextPath}/treni/modifica/copia" method="post">
                        <button type="submit" class="btn btn-secondary">Conferma Copia</button>
                    </form>
                </div>
            </div>

            <!-- Modal per Cancella Treno -->
            <div id="operation-cancellaTreno-${treno.idTreno}" class="operation-modal">
                <div class="operation-modal-content">
                    <span class="close-button" onclick="closeOperationModal('cancellaTreno', ${treno.idTreno})">&times;</span>
                    <h5>Cancella Treno - ${treno.nome}</h5>
                    <form action="${pageContext.request.contextPath}/treni/modifica/cancella" method="post">
                        <button type="submit" class="btn btn-danger">Conferma Cancellazione</button>
                    </form>
                </div>
            </div>

            <!-- Modal per Inverti Treno -->
            <div id="operation-invertiTreno-${treno.idTreno}" class="operation-modal">
                <div class="operation-modal-content">
                    <span class="close-button" onclick="closeOperationModal('invertiTreno', ${treno.idTreno})">&times;</span>
                    <h5>Inverti Treno - ${treno.nome}</h5>
                    <form action="${pageContext.request.contextPath}/treni/modifica/inverti" method="post">
                        <button type="submit" class="btn btn-success">Conferma Inversione</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script>
    // Funzioni per aprire e chiudere il modal principale
    function openModal(id) {
        document.getElementById("modal-" + id).style.display = "flex";
    }

    function closeModal(id) {
        document.getElementById("modal-" + id).style.display = "none";
    }

    // Funzioni per aprire e chiudere i modals delle operazioni
    function openOperationModal(action, id) {
        document.getElementById(`operation-${action}-${id}`).style.display = "flex";
        closeModal(id); // Chiude il modal principale quando si apre l'operazione specifica
    }

    function closeOperationModal(action, id) {
        document.getElementById(`operation-${action}-${id}`).style.display = "none";
    }
</script>

</body>
</html>

 
 
 
 
 
  --%>





<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestione Treni - Crea Treno</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<style>
/* Layout della pagina */
.page-content {
	display: flex;
	justify-content: space-between;
	padding: 20px;
}

/* Container Creazione Treno */
.form-section-container {
	width: 50%;
	margin-right: 20px;
}

/* Container Lista Treni */
.train-list-container {
	width: 20%;
	margin-left: auto;
}

.train-card {
	margin-bottom: 15px;
	padding: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	display: flex;
	align-items: center;
	justify-content: space-between;
}
</style>
</head>
<body>

	<div class="page-content">
		<!-- Form per la creazione di un nuovo treno -->
		<div class="form-section-container">
			<div class="form-section">
				<h3>Crea Nuovo Treno</h3>
				<form id="creaTrenoForm"
					action="${pageContext.request.contextPath}/treni/crea"
					method="post">
					<div class="form-group">
						<label for="nomeTreno">Nome Treno</label> <input type="text"
							class="form-control" id="nomeTreno" name="nomeTreno" required
							placeholder="Inserisci il nome del treno">
					</div>
					<div class="form-group">
						<label for="input">Sigla Treno</label> <input type="text"
							class="form-control" id="input" name="input" required
							title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
							placeholder="Es. HPPPP o HCCCCC, una R">
					</div>
					<div class="form-group">
						<label for="marca">Marca</label> <select class="form-control"
							id="marca" name="marca" required>
							<option value="Italiano">Italiano</option>
							<option value="Francese">Francese</option>
							<option value="Tedesco">Tedesco</option>
						</select>
					</div>
					<button type="submit" class="btn btn-primary btn-create">Crea
						Treno</button>
				</form>

				<!-- Dettagli del treno appena creato -->
				<c:if test="${not empty nuovoTreno}">
					<div class="train-details-card">
						<h4>Dettagli del Treno Creato</h4>
						<p>
							<strong>Nome:</strong> ${nuovoTreno.nome}
						</p>
						<p>
							<strong>Sigla:</strong> ${nuovoTreno.sigla}
						</p>
						<p>
							<strong>Marca:</strong> ${nuovoTreno.marca}
						</p>
						<img src="${nuovoTreno.immagine}" alt="Immagine del Treno"
							class="train-image"
							style="max-width: 100%; height: auto; border-radius: 5px;">
					</div>
				</c:if>
			</div>
		</div>
		<c:if test="${not empty errorMessage}">
			<div class="text-danger mt-3">${errorMessage}</div>
		</c:if>
		<c:if test="${not empty successMessage}">
			<div class="text-success mt-3">${successMessage}</div>
		</c:if>


		<!-- Lista Treni con pulsanti per ogni azione -->
		<div class="train-list-container">

			<c:forEach var="treno" items="${listaTreniUtente}">
				<div class="train-card">
					<h5>${treno.nome}</h5>
					<button type="button" class="btn btn-primary btn-sm"
						data-bs-toggle="modal"
						data-bs-target="#optionsModal-${treno.idTreno}">Modifica</button>
				</div>

				<!-- Modal delle Opzioni -->
				<div class="modal fade" id="optionsModal-${treno.idTreno}"
					tabindex="-1" aria-labelledby="optionsModalLabel-${treno.idTreno}"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="optionsModalLabel-${treno.idTreno}">Operazioni
									per ${treno.nome}</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<!-- Bottoni per ogni operazione -->
								<button class="btn btn-primary" data-bs-toggle="modal"
									data-bs-target="#aggiungiVagoneModal-${treno.idTreno}"
									data-bs-dismiss="modal">Aggiungi Vagone</button>
								<button class="btn btn-warning" data-bs-toggle="modal"
									data-bs-target="#rimuoviVagoneModal-${treno.idTreno}"
									data-bs-dismiss="modal">Rimuovi Vagone</button>
								<button class="btn btn-secondary" data-bs-toggle="modal"
									data-bs-target="#copiaTrenoModal-${treno.idTreno}"
									data-bs-dismiss="modal">Copia Treno</button>
								<button class="btn btn-danger" data-bs-toggle="modal"
									data-bs-target="#cancellaTrenoModal-${treno.idTreno}"
									data-bs-dismiss="modal">Cancella Treno</button>
								<button class="btn btn-success" data-bs-toggle="modal"
									data-bs-target="#invertiTrenoModal-${treno.idTreno}"
									data-bs-dismiss="modal">Inverti Treno</button>

							</div>
						</div>
					</div>
				</div>

				<!-- Modal Aggiungi Vagone -->
				<div class="modal fade" id="aggiungiVagoneModal-${treno.idTreno}"
					tabindex="-1"
					aria-labelledby="aggiungiVagoneModalLabel-${treno.idTreno}"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"
									id="aggiungiVagoneModalLabel-${treno.idTreno}">Aggiungi
									Vagone a ${treno.nome}</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form
									action="${pageContext.request.contextPath}/treni/modifica/aggiungi"
									method="post">
									<input type="hidden" name="idTrenoM" value="${treno.idTreno}">
									<div class="form-group">
										<label for="tipoVagone-${treno.idTreno}">Tipo di
											Vagone</label> <select class="form-control"
											id="tipoVagone-${treno.idTreno}" name="tipoVagone" required>
											<option value="Passeggeri">Passeggeri</option>
											<option value="Ristorante">Ristorante</option>
											<option value="Cargo">Cargo</option>
											<option value="Motrice">Motrice</option>
										</select>
									</div>
									<button type="submit" class="btn btn-primary">Conferma
										Aggiunta</button>
								</form>
							</div>
						</div>
					</div>
				</div>

				<div class="modal fade" id="rimuoviVagoneModal-${treno.idTreno}"
    tabindex="-1"
    aria-labelledby="rimuoviVagoneModalLabel-${treno.idTreno}"
    aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title"
                    id="rimuoviVagoneModalLabel-${treno.idTreno}">Rimuovi
                    Vagone da ${treno.nome}</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                    aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/treni/modifica/rimuovi" method="post">
                    <input type="hidden" name="idTrenoM" value="${treno.idTreno}">
                    <div class="form-group">
                        <label for="selezionaVagone-${treno.idTreno}">Seleziona Vagone da Rimuovere</label>
                        <select class="form-control" id="selezionaVagone-${treno.idTreno}" name="idVagone" required>
                            <c:forEach var="vagone" items="${treno.vagoni}" varStatus="status">
                                <option value="${vagone.idVagone}">
                                Posizione: ${status.index + 1} - ${vagoneTypeMap[vagone.idVagone]}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-warning">Conferma Rimozione</button>
                </form>
            </div>
        </div>
    </div>
</div>

				<!-- Modal Copia Treno -->
				<div class="modal fade" id="copiaTrenoModal-${treno.idTreno}"
					tabindex="-1"
					aria-labelledby="copiaTrenoModalLabel-${treno.idTreno}"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"
									id="copiaTrenoModalLabel-${treno.idTreno}">Copia Treno -
									${treno.nome}</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form
									action="${pageContext.request.contextPath}/treni/modifica/copia"
									method="post">
									<input type="hidden" name="idTrenoM" value="${treno.idTreno}">
									<button type="submit" class="btn btn-secondary">Conferma
										Copia</button>
								</form>
							</div>
						</div>
					</div>
				</div>




				<!-- Modal Cancella Treno -->
				<div class="modal fade" id="cancellaTrenoModal-${treno.idTreno}"
					tabindex="-1"
					aria-labelledby="cancellaTrenoModalLabel-${treno.idTreno}"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"
									id="cancellaTrenoModalLabel-${treno.idTreno}">Cancella
									Treno - ${treno.nome}</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form
									action="${pageContext.request.contextPath}/treni/modifica/cancella"
									method="post">
									<input type="hidden" name="idTrenoM" value="${treno.idTreno}">
									<button type="submit" class="btn btn-danger">Conferma
										Cancellazione</button>
								</form>
							</div>
						</div>
					</div>
				</div>





				<!-- Modal Inverti Treno -->
				<div class="modal fade" id="invertiTrenoModal-${treno.idTreno}"
					tabindex="-1"
					aria-labelledby="invertiTrenoModalLabel-${treno.idTreno}"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"
									id="invertiTrenoModalLabel-${treno.idTreno}">Inverti Treno
									- ${treno.nome}</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form
									action="${pageContext.request.contextPath}/treni/modifica/inverti"
									method="post">
									<input type="hidden" name="idTrenoM" value="${treno.idTreno}">
									<p>
										Sei sicuro di voler invertire la disposizione dei vagoni per
										il treno <strong>${treno.nome}</strong>?
									</p>
									<button type="submit" class="btn btn-success">Conferma
										Inversione</button>
								</form>
							</div>
						</div>
					</div>
				</div>


			</c:forEach>
		</div>
	</div>

</body>
<script>
    setTimeout(function() {
        const alertMessages = document.querySelectorAll('.alert');
        alertMessages.forEach(alert => {
            alert.style.display = 'none';
        });
    }, 5000); // 5000 ms = 5 secondi
</script>




</html>




