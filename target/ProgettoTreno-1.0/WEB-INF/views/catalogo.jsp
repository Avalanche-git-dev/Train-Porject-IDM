






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
		<h1>Tutti i Treni Disponibili</h1>

		<!-- Form di Filtro per il Catalogo dei Treni -->
		<form action="${pageContext.request.contextPath}/catalogo/filtro"
			method="get" class="mb-4">
			<div class="form-row">
				<div class="form-group col-md-3">
					<label for="nome">Nome del Treno</label> <input type="text"
						id="nome" name="nome" value="${trenoFilter.nome}"
						class="form-control" />
				</div>
				<div class="form-group col-md-3">
					<label for="marca">Marca</label> <input type="text" id="marca"
						name="marca" value="${trenoFilter.marca}" class="form-control" />
				</div>
				<%-- <div class="form-group col-md-3">
					<label for="Valore">Valore</label> <input
						type="number" id="costoTotale" name="costoTotale"
						value="${trenoFilter.costoTotale}" class="form-control" />
				</div> --%>
				<div class="form-group col-md-3">
					<label for="valutazioneMassima">Media Valutazioni (massimo)</label>
					<input type="number" id="valutazioneMassima"
						name="valutazioneMassima" value="${trenoFilter.valutazioni}"
						class="form-control" />
				</div>
				<div class="form-group col-md-3">
					<label for="nomeOwner">Proprietario</label> <input type="text"
						id="NomeOwner" name="nomeOwner" value="${trenoFilter.nomeOwner}"
						class="form-control" />
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Applica Filtro</button>
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
							<p class="card-text">Propietario : ${sessionScope.usernameOwner}</p>
							
							<%-- <p class="card-text">Peso: ${treno.pesoTotale} Kg</p>
							<p class="card-text">Valore: ${treno.costoTotale} $</p>
							<p class="card-text">Marca: ${treno.pesoTotale}</p> --%>
							
							<p class="card-text">Media Valutazioni:
								${treno.mediaValutazioni}</p>
						</div>
						<div class="col-md-4 text-right">
							<!-- Form per inviare l'ID del treno e visualizzare i dettagli -->
							<form
								action="${pageContext.request.contextPath}/treni/visualizza/treno"
								method="post" class="d-inline">
								<input type="hidden" name="idTreno" value="${treno.idTreno}" />
								<button type="submit" class="btn btn-primary">Vedi
									Dettagli</button>
							</form>
							<!-- Pulsante "Valuta" con il form di valutazione -->
							<button class="btn btn-success ml-2"
								onclick="toggleValutaForm(${treno.idTreno})">Valuta</button>
							<!-- Form di valutazione, inizialmente nascosto -->
							<div id="valutaForm${treno.idTreno}"
								style="display: none; margin-top: 10px;">
								<form
									action="${pageContext.request.contextPath}/catalogo/valutaTreno"
									method="post">
									<!-- Passaggio dell'ID del treno come input nascosto -->
									<input type="hidden" name="trenoId" value="${treno.idTreno}">
									<label for="voto${treno.idTreno}">Inserisci la tua
										valutazione (1-5):</label> <input type="number"
										id="voto${treno.idTreno}" name="voto" min="1" max="5"
										class="form-control" required>
									<button type="submit" class="btn btn-primary mt-2">Invia
										Valutazione</button>
								</form>
								<!-- Mostra il messaggio di successo se presente -->
								<c:if test="${not empty successMessage}">
									<div class="alert alert-success mt-2">${successMessage}</div>
								</c:if>
								<!-- Mostra l'errore se presente -->
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
            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        }
    </script>
=======
       <%--  <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Sigla del Treno</label>
                    <input type="text" id="nome" name="sigla" value="${trenoFilter.sigla}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Valore (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.costoTotale}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
                
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                    <!-- Pulsante per Vedere i Dettagli del Treno -->
                    <a href="${pageContext.request.contextPath}/treni/dettagli/${treno.idTreno}" class="btn btn-primary">Vedi Dettagli</a>
                    <!-- Pulsante "Valuta" -->
                    <a href="${pageContext.request.contextPath}/valutazioni/valutaTreno/${treno.idTreno}" class="btn btn-success ml-2">Valuta</a>
                </div>
            </div>
        </c:forEach>
    </div>
>>>>>>> origin/biagionuovo
</body>
</html> --%>