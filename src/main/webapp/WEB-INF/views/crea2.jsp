<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea Nuovo Treno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        // Funzione per validare l'input del treno senza regex
        function validaTreno(input) {
            // Controlla che inizi con 'H'
            if (input[0] !== 'H') {
                return false;
            }

            // Controlla che la seconda lettera sia 'P' o 'C'
            if (input[1] !== 'P' && input[1] !== 'C') {
                return false;
            }

            // Controlla che ci sia solo una 'R'
            let rCount = 0;
            for (let i = 0; i < input.length; i++) {
                if (input[i] === 'R') {
                    rCount++;
                }
            }
            if (rCount > 1) {
                return false;
            }

            // Se tutti i controlli passano, ritorna true
            return true;
        }

        // Funzione per mostrare un messaggio di conferma e validare il form
        function confermaCreazione(event) {
            event.preventDefault();  // Blocca l'invio del modulo temporaneamente
            var inputTreno = document.getElementById("input").value;

            // Se l'input non è valido, mostra un errore e blocca l'invio
            if (!validaTreno(inputTreno)) {
                alert("Errore: La stringa del treno deve iniziare con 'H', la seconda lettera deve essere 'P' o 'C', e può contenere una sola 'R'.");
                return false;
            }

            // Mostra un messaggio di conferma se tutto è corretto
            var conferma = confirm("Treno creato con successo! Vuoi procedere?");
            
            if (conferma) {
                document.getElementById("creaTrenoForm").submit();  // Se confermato, invia il modulo
            }
        }
    </script>
</head>
<body>

    <div class="container mt-5">
        <h2>Crea un nuovo treno</h2>
        <!-- Form per la creazione del treno -->
        <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea" method="post" onsubmit="confermaCreazione(event)">
            <!-- Campo input per la stringa del treno -->
            <div class="form-group">
                <label for="input">Input Treno</label>
                <input type="text" class="form-control" id="input" name="input" required
                       title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'."
                       placeholder="Es. HPPPP o HCCCCC, una R">
            </div>

            <!-- Campo per la selezione della marca -->
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
    </div>

</body>
</html>
