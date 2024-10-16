<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Treni - Crea e Modifica Treno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        // Funzione per validare l'input del treno senza regex
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

        // Funzione per mostrare un messaggio di conferma e validare il form
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

        // Funzione per mostrare/chiudere il pannello di modifica del treno
        function toggleModificaTreno() {
            const modificaPanel = document.getElementById("modificaPanel");
            modificaPanel.style.display = (modificaPanel.style.display === "none" || modificaPanel.style.display === "") ? "block" : "none";
        }

        // Funzione per confermare la modifica del treno
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
    
    <!-- Form per la creazione del treno -->
    <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea" method="post" onsubmit="confermaCreazione(event)">
        <div class="form-group">
            <label for="input">Nome Treno</label>
            <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
        </div>
        <div class="form-group">
            <label for="input">Input Treno</label>
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
        <button type="submit" class="btn btn-primary">Crea Treno</button>
    </form>

    <!-- Bottone per modificare un treno esistente -->
    <button class="btn btn-warning mt-4" onclick="toggleModificaTreno()">Modifica Treno</button>

    <!-- Pannello per la modifica del treno -->
    <div id="modificaPanel" class="mt-4" style="display: none;">
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
                <label for="modificaInput">Modifica Treno</label>
                <input type="text" class="form-control" id="modificaInput" name="modificaInput" required
                       title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                       placeholder="Es. HPPPP o HCCCCC, una R">
            </div>
            <button type="submit" class="btn btn-success">Salva Modifiche</button>
        </form>
    </div>
</div>

</body>
</html>
