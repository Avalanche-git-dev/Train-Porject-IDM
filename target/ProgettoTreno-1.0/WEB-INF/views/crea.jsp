

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
.page-content-crea {
	display: flex;
	padding: 10px;
	height: 100%;
	/* Nasconde il contenuto che esce dai limiti del contenitore */
}

.form-section-container {
	width: 32%;
	margin-right: 20px;
	margin-bottom: 0;
	height: auto;
}

.navbar {
	margin-bottom: 20px; /* Assicura spaziatura uniforme sotto la navbar */
}

body, html {
	height: 100%;
	overflow: hidden;
	/* Imposta l'altezza al 100% */
}
</style>
</head>
<body>

	<div class="page-content-crea">
		<!-- Form per la creazione di un nuovo treno -->
		<div class="form-section-container">
			<div class="form-section-crea">
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
					<div class="train-details-card-crea">
						<h4>Dettagli del Treno Creato</h4>
						<p>
							<strong>Nome:</strong> ${nuovoTreno.nome}
						</p>
						<p>
							<strong>Sigla:</strong> ${nuovoTreno.sigla}
						</p>
						<p>
							<strong>Marca:</strong> ${nuovoTreno.marca}</ p> <img
								src="${nuovoTreno.immagine}" alt="Immagine del Treno"
								style="width: 275px; height: 250px; object-fit: cover; border-radius: 4px;">
					</div>
				</c:if>
				<!-- Dettagli del treno appena modificato -->
				<c:if test="${not empty modificaTreno}">
					<div class="train-details-card-crea">
						<h4>Dettagli del Treno Modificato</h4>
						<img src="${modificaTreno.immagine}" alt="Immagine del Treno"
							style="width: 275px; height: 250px; object-fit: cover; border-radius: 4px;">
						<p>
							<strong>Nome:</strong> ${modificaTreno.nome}
						</p>
						<p>
							<strong>Sigla:</strong> ${modificaTreno.sigla}
						</p>
						<p>
							<strong>Valutazioni:</strong> ${modificaTreno.mediaValutazioni}
						</p>

					</div>
				</c:if>
			</div>
		</div>
		<c:if test="${not empty errorMessage}">
			<div class="text-danger mt-3  text-align:center">${errorMessage}</div>
		</c:if>
		<c:if test="${not empty successMessage}">
			<div class="text-success mt-3">${successMessage}</div>
		</c:if>

		<!-- Lista Treni con pulsanti per ogni azione -->

		<div class="train-grid-container">
			<div class="row">
				<c:forEach var="treno" items="${listaTreniUtente}"
					varStatus="status">
					<div class="col-md-4 mb-0" data-index="${status.index}">
						<div class="train-card-crea">
							<h5>${treno.nome}</h5>
							<button type="button" class="btn btn-primary btn-sm"
								data-bs-toggle="modal"
								data-bs-target="#optionsModal-${treno.idTreno}">Modifica</button>
						</div>
					</div>

					<!-- Modal delle Opzioni -->
					<div class="modal fade" id="optionsModal-${treno.idTreno}"
						tabindex="-1" aria-labelledby="optionsModalLabel-${treno.idTreno}"
						aria-hidden="true">
						<div
							class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="optionsModalLabel-${treno.idTreno}">Operazioni
										per ${treno.nome}</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								
								<!-- modal body  -->
								<div class="modal-body">
									<img src="${treno.immagine}" alt="Immagine del Treno"
										style="width: 275px; height: 250px; object-fit: cover; border-radius: 4px;">
									<p>
										<strong>Sigla:</strong> ${treno.sigla}
									</p>
								</div>
								
								<!-- modal footer  -->
								<div class="modal-footer">
									<div class="d-flex justify-content-between">
										<!-- Bottoni per ogni operazione -->
										<button class="btn btn-primary ml-1" data-bs-toggle="modal"
											data-bs-target="#aggiungiVagoneModal-${treno.idTreno}"
											data-bs-dismiss="modal">Aggiungi Vagone</button>
										<button class="btn btn-warning ml-1" data-bs-toggle="modal"
											data-bs-target="#rimuoviVagoneModal-${treno.idTreno}"
											data-bs-dismiss="modal">Rimuovi Vagone</button>
										<button class="btn btn-secondary ml-1" data-bs-toggle="modal"
											data-bs-target="#copiaTrenoModal-${treno.idTreno}"
											data-bs-dismiss="modal">Copia Treno</button>
										<button class="btn btn-danger ml-1" data-bs-toggle="modal"
											data-bs-target="#cancellaTrenoModal-${treno.idTreno}"
											data-bs-dismiss="modal">Cancella Treno</button>
										<button class="btn btn-success ml-1" data-bs-toggle="modal"
											data-bs-target="#invertiTrenoModal-${treno.idTreno}"
											data-bs-dismiss="modal">Inverti Treno</button>
									</div>


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
												<option value="P">Passeggeri</option>
												<option value="R">Ristorante</option>
												<option value="C">Cargo</option>
												<option value="H">Motrice</option>
											</select>
										</div>
										<button type="submit" class="btn btn-primary">Conferma
											Aggiunta</button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<!-- Modal Rimuovi Vagone -->
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
									<form
										action="${pageContext.request.contextPath}/treni/modifica/rimuovi"
										method="post">
										<input type="hidden" name="idTrenoM" value="${treno.idTreno}">
										<div class="form-group">
											<label for="selezionaVagone-${treno.idTreno}">Seleziona
												Vagone da Rimuovere</label> <select class="form-control"
												id="selezionaVagone-${treno.idTreno}" name="idVagone"
												required>
												<c:forEach var="vagone" items="${treno.vagoni}"
													varStatus="status">
													<option value="${vagone.idVagone}">Posizione:
														${status.index + 1} - ${vagoneTypeMap[vagone.idVagone]}</option>
												</c:forEach>
											</select>
										</div>
										<button type="submit" class="btn btn-warning">Conferma
											Rimozione</button>
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
										id="copiaTrenoModal Label-${treno.idTreno}">Copia Treno -
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
										id="invertiTrenoModalLabel-${treno.idTreno}">Inverti
										Treno - ${treno.nome}</h5>
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



			<div class="pagination-controls">
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<li class="page-item" id="firstBtn"><a class="page-link"
							href="#">First</a></li>
						<li class="page-item" id="prevBtn"><a class="page-link"
							href="#">Previous</a></li>
						<li class="page-item" id="nextBtn"><a class="page-link"
							href="#">Next</a></li>
						<li class="page-item" id="lastBtn"><a class="page-link"
							href="#">Last</a></li>
					</ul>
				</nav>
			</div>




		</div>
	</div>


	<script>
    const cards = document.querySelectorAll('.train-card-crea');
    const cardsPerPage = 9; // Numero di card da visualizzare per pagina
    let currentPage = 0;
    const totalPages = Math.ceil(cards.length / cardsPerPage); // Calcola il numero totale di pagine

    function showPage(page) {
        const start = page * cardsPerPage;
        const end = start + cardsPerPage;

        cards.forEach((card, index) => {
            card.style.display = (index >= start && index < end) ? 'block' : 'none';
        });

        // Abilita o disabilita i pulsanti di navigazione
        document.getElementById('prevBtn').classList.toggle('disabled', page === 0);
        document.getElementById('nextBtn').classList.toggle('disabled', page === totalPages - 1);
        document.getElementById('firstBtn').classList.toggle('disabled', page === 0);
        document.getElementById('lastBtn').classList.toggle('disabled', page === totalPages - 1);
    }

    document.getElementById('firstBtn').addEventListener('click', (e) => {
        e.preventDefault();
        currentPage = 0; // Vai alla prima pagina
        showPage(currentPage);
    });

    document.getElementById('prevBtn').addEventListener('click', (e) => {
        e.preventDefault();
        if (currentPage > 0) {
            currentPage--;
            showPage(currentPage);
        }
    });

    document.getElementById('nextBtn').addEventListener('click', (e) => {
        e.preventDefault();
        if (currentPage < totalPages - 1) {
            currentPage++;
            showPage(currentPage);
        }
    });

    document.getElementById('lastBtn').addEventListener('click', (e) => {
        e.preventDefault();
        currentPage = totalPages - 1; // Vai all'ultima pagina
        showPage(currentPage);
    });

    // Mostra la prima pagina all'inizio
    showPage(currentPage);
</script>




</body>
</html>



