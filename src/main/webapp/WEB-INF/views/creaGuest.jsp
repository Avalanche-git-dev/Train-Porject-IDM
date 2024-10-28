<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestione Treni - Crea Treno (Guest)</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        /* Stili della pagina */
        .page-content-crea {
            display: flex;
            padding: 10px;
            height: 100%;
        }

        .form-section-container {
            width: 32%;
            margin-right: 20px;
            height: auto;
        }

        .train-grid-container {
            width: 40%;
            height: auto;
            margin-left: auto;
            margin-right: 30px;
        }

        .train-image {
            display: block;
            margin: 0 auto;
            max-width: 100%;
            height: auto;
        }
    </style>
</head>
<body>

<div class="page-content-crea">
    <!-- Form per la creazione di un nuovo treno -->
    <div class="form-section-container">
        <div class="form-section-crea">
            <h3>Crea Nuovo Treno</h3>
            <form id="creaTrenoForm" action="${pageContext.request.contextPath}/treni/crea/guest" method="post">
                <div class="form-group">
                    <label for="nomeTreno">Nome Treno</label>
                    <input type="text" class="form-control" id="nomeTreno" name="nomeTreno" required placeholder="Inserisci il nome del treno">
                </div>
                <div class="form-group">
                    <label for="input">Sigla Treno</label>
                    <input type="text" class="form-control" id="input" name="input" required title="La stringa del treno deve iniziare con 'H', contenere una sola 'R', e la seconda lettera deve essere 'P' o 'C'." placeholder="Es. HPPPP o HCCCCC, una R">
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

            <!-- Pulsante Registrati solo per ospiti -->
            <button onclick="location.href='${pageContext.request.contextPath}/user/registrati'" class="btn btn-secondary mt-3">Registrati</button>

            <!-- Dettagli del treno appena creato -->
            <c:if test="${not empty nuovoTreno}">
                <div class="train-details-card">
                    <h4>Dettagli del Treno Creato</h4>
                    <p><strong>Nome:</strong> ${nuovoTreno.nome}</p>
                    <p><strong>Sigla:</strong> ${nuovoTreno.sigla}</p>
                    <p><strong>Marca:</strong> ${nuovoTreno.marca}</p>
                    <img src="${nuovoTreno.immagine}" alt="Immagine del Treno" class="train-image">
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

    <!-- Sezione per la lista dei treni dell'utente (se necessaria) -->
        <!-- Contenuto della lista treni se necessario -->
</div>

</body>
</html>
