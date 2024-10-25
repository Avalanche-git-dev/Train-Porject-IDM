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
