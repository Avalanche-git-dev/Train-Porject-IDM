<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ffffff;
            color: #333;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .navbar {
            background-color: #f0f0f0; /* Grigio chiaro */
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }

        .navbar-brand {
            color: #333; /* Grigio scuro */
            padding: 10px 20px;
            font-size: 18px;
            font-weight: bold;
            border-radius: 5px;
            cursor: pointer;
            transition: color 0.3s ease, background-color 0.3s ease;
        }

        .navbar-brand:hover {
            color: #0275d8; /* Blu per hover */
            background-color: #e0e0e0; /* Grigio chiaro per hover */
        }

        .logout-btn {
            background-color: #777; /* Grigio più scuro per evidenziarlo */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #0275d8; /* Blu per hover */
            color: #fff;
        }

        .settings-icon {
            font-size: 22px;
            color: #0275d8;
            margin-left: 10px;
            cursor: pointer;
            transition: color 0.3s ease;
        }

        .settings-icon:hover {
            color: #025aa5;
        }

        #creaTrenoForm {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            background-color: #f9f9f9;
        }
        
        /* Centraggio del modulo */
        .form-container {
            max-width: 500px; /* Larghezza massima del modulo */
            margin: 20px auto; /* Centra il modulo orizzontalmente */
            padding: 20px; /* Padding interno */
        }

        /* Margine superiore per il container */
        .container {
            margin-top: 20px; /* Puoi modificare questo valore per spostare il container sotto la navbar */
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
            var inputTreno = document.getElementById("siglaTreno").value;

            if (!validaTreno(inputTreno)) {
                alert("Errore: La stringa del treno deve iniziare con 'H', la seconda lettera deve essere 'P' o 'C', e può contenere una sola 'R'.");
                return false;
            }

            var conferma = confirm("Treno creato con successo! Vuoi procedere?");
            if (conferma) {
                document.getElementById("creaTrenoForm").submit();
            }
        }
    </script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/treni/catalogo">Catalogo</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <div class="ml-auto d-flex">
            <a class="nav-link" href="${pageContext.request.contextPath}/login" style="color: black;">Login</a>
            <a class="nav-link ml-2" href="${pageContext.request.contextPath}/registrazione" style="color: black;">Registrati</a>
        </div>
    </div>
</nav>

<!-- Main content -->
<div class="container">

    <!-- Form per la creazione del treno -->
    <div class="form-container">
        <h2 class="text-center">Benvenuto!<br>Crea qui il tuo nuovo treno</h2>
        <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea" method="post" onsubmit="confermaCreazione(event)">
            <div class="form-group">
                <label for="nomeTreno">Nome Treno</label>
                <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
            </div>
            <div class="form-group">
                <label for="siglaTreno">Sigla Treno</label>
                <input type="text" class="form-control" id="siglaTreno" name="siglaTreno" required
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
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

</body>
</html>
