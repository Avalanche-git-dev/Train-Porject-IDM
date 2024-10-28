<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Treni dell'Utente</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<script>
    function toggleVenditaForm(id) {
        var form = document.getElementById("venditaForm-" + id);
        form.style.display = form.style.display === "none" ? "block" : "none";
    }
</script>
</head>
<body>
	<div class="container">
		<h1>Collezione Treni dell'Utente</h1>
		<c:forEach var="treno" items="${treniDto}">
			<div class="card mb-3">
				<div class="card-body">
					<h5 class="card-title">${treno.nome}</h5>
					<p class="card-text">Marca: ${treno.marca}</p>
					<p class="card-text">Media Valutazioni: ${treno.mediaValutazioni} / 5</p>
					
					<!-- Pulsante "Vedi Dettagli" -->
					<form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post" style="display: inline;">
						<input type="hidden" name="idTreno" value="${treno.idTreno}" />
						<button type="submit" class="btn btn-primary">Vedi Dettagli</button>
					</form>

					<!-- Pulsante "Metti in Vendita" -->
					<button type="button" class="btn btn-warning" onclick="toggleVenditaForm(${treno.idTreno})">Metti in Vendita</button>
					
					<!-- Form di vendita nascosto che si apre al clic -->
					<form id="venditaForm-${treno.idTreno}" action="${pageContext.request.contextPath}/market/vendi" method="post" style="display: none; margin-top: 10px;">
						<input type="hidden" name="idTreno" value="${treno.idTreno}" />
						<div class="input-group mb-3">
							<input type="number" name="prezzoVendita" class="form-control" placeholder="Prezzo di Vendita" required />
							<div class="input-group-append">
								<button type="submit" class="btn btn-success">Conferma Vendita</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html> --%>


















<%-- 

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Collezione Treni dell'Utente</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        /* Griglia di carte per i treni */
        .train-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
            margin-top: 40px;
        }

        .train-card {
    margin-bottom: 15px;
    padding: 20px; /* Aumentato il padding per una migliore spaziatura */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column; /* Cambiato per disporre gli elementi in colonna */
    align-items: center; /* Centrato gli elementi orizzontalmente */
    justify-content: center; /* Centrato gli elementi verticalmente */
    border: 1px solid #ccc; /* Aggiunto un bordo per definire meglio la card */
    border-radius: 5px; /* Arrotondato gli angoli */
    height: 200px; /* Altezza fissa per le card */
    text-align: center; /* Centra il testo */
}

        .train-card h5 {
            margin-bottom: 10px;
            font-size: 18px;
            font-weight: bold;
        }

        .train-card p {
            margin: 5px 0;
            font-size: 14px;
        }

        /* Bottone dettagli */
        .btn-dettagli {
            margin-top: 10px;
            width: 100%;
        }

        /* Stile modale */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
        }

        .modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            width: 300px;
            text-align: left;
            overflow-y: auto;
        }

        .modal-header {
            font-size: 18px;
            margin-bottom: 15px;
        }

        .close-btn {
            float: right;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
        }
    </style>
    <script>
        function openModal(id) {
            document.getElementById("modal-" + id).style.display = "flex";
        }

        function closeModal(id) {
            document.getElementById("modal-" + id).style.display = "none";
        }
    </script>
</head>
<body>

<div class="container">
    <h1>Collezione Treni dell'Utente</h1>

    <div class="train-grid">
        <c:forEach var="treno" items="${treniDto}">
            <div class="train-card">
                <h5>${treno.nome}</h5>
                <p>Marca: ${treno.marca}</p>
                <p>Media Valutazioni: ${treno.mediaValutazioni} / 5</p>
                
                <!-- Bottone per aprire il modale -->
                <button type="button" class="btn btn-primary btn-dettagli" onclick="openModal(${treno.idTreno})">Vedi Dettagli</button>
            </div>

            <!-- Modale per i dettagli del treno -->
            <div id="modal-${treno.idTreno}" class="modal">
                <div class="modal-content">
                    <span class="close-btn" onclick="closeModal(${treno.idTreno})">&times;</span>
                    <div class="modal-header">Dettagli del Treno</div>
                    <p><strong>Nome:</strong> ${treno.nome}</p>
                    <p><strong>Marca:</strong> ${treno.marca}</p>
                    <p><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
                    <p><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
                    <p><strong>Costo Totale:</strong> ${treno.costoTotale} €</p>
                    <p><strong>Lunghezza:</strong> ${treno.lunghezzaTotale} m</p>
                    <p><strong>Posti :</strong> ${treno.postiTotali}</p>
                    
                      <!-- pesoTrainabile parte ? capcità o posti per passeggeri o cargo problema immagine -->
                        <!-- Form per richiamare il controller con il bottone di dettagli -->
                    
                    <!-- Form per richiamare il controller con il bottone di dettagli -->
                    <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post">
                        <input type="hidden" name="idTreno" value="${treno.idTreno}">
                        <button type="submit" class="btn btn-primary">Dettagli Completi</button>
                    </form>
                </div>
            </div>
        </c:forEach>
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
    <title>Collezione Treni dell'Utente</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        .main-container {
            display: flex;
            gap: 20px;
            margin-top: 40px;
        }
body {
            overflow: hidden; /* Disabilita lo scorrimento della pagina */
        }
        /* Griglia di carte per i treni */
        .train-grid {
            flex-basis: 70%;
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }

        .train-card {
            position: relative;
            width: 100%;
            max-width: 500px;
            border: 1px solid #ccc;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .train-card img {
            width: 100%;
            height: auto;
        }

        .card-img-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            color: #fff;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
        }

        .card-title {
            font-size: 24px;
            margin-bottom: 10px;
        }

        .btn-dettagli {
            margin-top: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px;
            cursor: pointer;
            text-decoration: none;
        }

        /* Sezione filtro */
        .filter-section {
            flex-basis: 30%;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            background-color: #f9f9f9;
            position: sticky;
            top: 0;
            height: fit-content;
        }

        .filter-section h4 {
            margin-bottom: 20px;
        }

        /* Stile modale */
        .modal {
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

        .modal-content {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            width: 60%;
            max-height: 80%;
            overflow-y: auto;
        }

        .modal-header {
            font-size: 24px;
            margin-bottom: 15px;
        }

        .close-btn {
            float: right;
            font-size: 24px;
            font-weight: bold;
            cursor: pointer;
        }

        .sell-train-btn {
            margin-top: 20px;
            width: 100%;
        }
    </style>
    <script>
    <script>
    function openModal(id) {
        document.getElementById("modal-" + id).style.display = "flex";
    }

    function closeModal(id) {
        document.getElementById("modal-" + id).style.display = "none";
    }

    let currentPage = 0;
    const cardsPerPage = 12;
    let cards;
    const totalPages = Math.ceil(cards.length / cardsPerPage);

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
    document.addEventListener('DOMContentLoaded', () => {
        cards = document.querySelectorAll('.train-card');
        showPage(currentPage);
    });
</script>
</head>
<body>

<div class="container">
    <h1>Collezione Treni dell'Utente</h1>
    <div class="main-container">

        <!-- Griglia con le carte dei treni -->
        <div class="train-grid">
            <c:forEach var="treno" items="${treniDto}">
                <div class="train-card">
                    <img class="card-img-top" src="${treno.immagine}" alt="Card image">
                    <div class="card-img-overlay">
                        <h4 class="card-title">${treno.nome}</h4>
                        <button type="button" class="btn btn-primary btn-dettagli" onclick="openModal(${treno.idTreno})">Vedi Dettagli</button>
                    </div>
                </div>

                <!-- Modale per i dettagli del treno -->
                <div id="modal-${treno.idTreno}" class="modal">
                    <div class="modal-content">
                        <span class="close-btn" onclick="closeModal(${treno.idTreno})">&times;</span>
                        <div class="modal-header">Dettagli del Treno</div>
                        <img src="${treno.immagine}" alt="${treno.nome} Image" style="width: 100%; height: auto; margin-bottom: 20px;"/>
                        <p><strong>Nome:</strong> ${treno.nome}</p>
                        <p><strong>Marca:</strong> ${treno.marca}</p>
                        <p><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
                        <p><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
                        <p><strong>Costo Totale:</strong> ${treno.costoTotale} €</p>
                        <p><strong>Lunghezza:</strong> ${treno.lunghezzaTotale} m</p>
                        <p><strong>Posti :</strong> ${treno.postiTotali}</p>

                        <!-- Form per richiamare il controller con il bottone di dettagli -->
                        <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post">
                            <input type="hidden" name="idTreno" value="${treno.idTreno}">
                            <button type="submit" class="btn btn-primary">Dettagli Completi</button>
                        </form>
                        <!-- Bottone per mettere in vendita il treno -->
                        <button type="button" class="btn btn-danger sell-train-btn">Vendi Treno</button>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- Sezione filtro nell'angolo destro -->
        <div class="filter-section">
            <h4>Filtra i Treni</h4>
            <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="marca">Marca</label>
                    <select id="marca" name="marca" class="form-control">
                        <option value="">Tutte</option>
                        <option value="italiano" ${trenoFilter.marca == 'italiano' ? 'selected' : ''}>Italiano</option>
                        <option value="francese" ${trenoFilter.marca == 'francese' ? 'selected' : ''}>Francese</option>
                        <option value="tedesco" ${trenoFilter.marca == 'tedesco' ? 'selected' : ''}>Tedesco</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="valutazioneMedia">Valutazione Media (massima)</label>
                    <input type="number" id="valutazioneMedia" name="valutazioneMedia" value="${trenoFilter.valutazioneMedia}" class="form-control" min="1" max="5">
                </div>
                <div class="form-group">
                    <label for="lunghezza">Lunghezza (massima)</label>
                    <input type="number" id="lunghezza" name="lunghezza" value="${trenoFilter.lunghezza}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="pesoTotale">Peso Totale (massimo)</label>
                    <input type="number" id="pesoTotale" name="pesoTotale" value="${trenoFilter.pesoTotale}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="costoTotale">Costo Totale (massimo)</label>
                    <input type="number" id="costoTotale" name="costoTotale" value="${trenoFilter.costoTotale}" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Applica Filtro</button>
            </form>
        </div>

    </div>
    
</div>

</body>
</html>
 --%>

<%-- 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gestione Treni - Visualizza Treni</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<style>
.main-container {
    display: flex;
    gap: 20px;
    margin-top: -20px;
}

/* Griglia di carte per i treni */
.train-grid {
	flex-basis: 70%;
	display: grid;
	grid-template-columns: repeat(4, 1fr);
	gap: 20px;
}

.page-content-details {
            display: flex;
            padding: 10px;
            height: 100%; 
             /* Nasconde il contenuto che esce dai limiti del contenitore */
        }





.train-card-details {
	margin-bottom: 15px;
	padding: 0px; /* Aumentato il padding per una migliore spaziatura */
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	display: absolute;
	flex-direction: column;
	/* Cambiato per disporre gli elementi in colonna */
	align-items: center; /* Centrato gli elementi orizzontalmente */
	justify-content: center; /* Centrato gli elementi verticalmente */
	border: 1px solid #ccc;
	/* Aggiunto un bordo per definire meglio la card */
	border-radius: 5px; /* Arrotondato gli angoli */
	height: 250px;
	width: 200px; /* Altezza fissa per le card */
	text-align: center;
	overflow: hidden;
	position: relative;
	
}

.train-card-details .card-img-overlay {
    position: absolute;
    bottom: 0;
    width: 100%;
    background: transparent;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
}

.train-card-details .btn-dettagli {
    font-size: 0.9em;
}

.train-card-details .rating {
	position: absolute;
	top: 10px;
	right: 10px;
	font-size: 1em;
	color: gold;
	background-color: rgba(0, 0, 0, 0.6);
	border-radius: 50%;
	padding: 5px;
}

/* Sezione filtro */
.filter-section {
    flex-basis: 25%;
    max-width: 300px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
    position: sticky;
    top: 0;
    height: fit-content;
}

.filter-section h4 {
	margin-bottom: 20px;
}




  body, html {
    height: 100%;
    overflow: hidden;
     /* Imposta l'altezza al 100% */
}
</style>

</head>
<body>


<div class="page-content-detilas">
		<h1>Visualizza Treni</h1>
		<div class="main-container">

			<!-- Sezione filtro nell'angolo sinistro -->
			<div class="filter-section">
				<h4>Filtra i Treni</h4>
				<form action="${pageContext.request.contextPath}/treni/filtro"
					method="get">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text" id="nome"
							name="nome" value="${trenoFilter.nome}" class="form-control">
					</div>
					<div class="form-group">
						<label for="marca">Marca</label> <select id="marca" name="marca"
							class="form-control">
							<option value="">Tutte</option>
							<option value="italiano"
								${trenoFilter.marca == 'italiano' ? 'selected' : ''}>Italiano</option>
							<option value="francese"
								${trenoFilter.marca == 'francese' ? 'selected' : ''}>Francese</option>
							<option value="tedesco"
								${trenoFilter.marca == 'tedesco' ? 'selected' : ''}>Tedesco</option>
						</select>
					</div>
					<div class="form-group">
						<label for="valutazioneMedia">Valutazione Media (massima)</label>
						<input type="number" id="valutazioneMedia" name="valutazioneMedia"
							value="${trenoFilter.valutazioneMedia}" class="form-control"
							min="1" max="5">
					</div>
					<div class="form-group">
						<label for="lunghezza">Lunghezza (massima)</label> <input
							type="number" id="lunghezza" name="lunghezza"
							value="${trenoFilter.lunghezza}" class="form-control">
					</div>
					<div class="form-group">
						<label for="pesoTotale">Peso Totale (massimo)</label> <input
							type="number" id="pesoTotale" name="pesoTotale"
							value="${trenoFilter.pesoTotale}" class="form-control">
					</div>
					<div class="form-group">
						<label for="costoTotale">Costo Totale (massimo)</label> <input
							type="number" id="costoTotale" name="costoTotale"
							value="${trenoFilter.costoTotale}" class="form-control">
					</div>
					<button type="submit" class="btn btn-primary">Applica
						Filtro</button>
				</form>
			</div>

			<!-- Griglia con le carte dei treni -->
			<div class="train-grid">
				<c:forEach var="treno" items="${treniDto}">
					<div class="train-card-deatils">
						<img src="${treno.immagine}" alt="Immagine del Treno" style="width: 220px; height: 220px;object-fit: cover; border-radius: 4px;">
						<div class="card-img-overlay">
							<h4 class="title">${treno.nome}</h4>
							<button type="button" class="btn btn-primary btn-dettagli"
								onclick="openModal(${treno.idTreno})">Vedi Dettagli</button>
								</div>
						</div>


                    <div class="col-md-4 mb-0"data-index="${status.index}">
					<div class="train-card-details">
						<img src="${treno.immagine}" alt="Immagine del Treno"
							style="width: 220px; height: 220px; object-fit: cover; border-radius: 4px;">
						<!-- Valutazione media in alto a destra -->
						<div class="rating">&#9733; ${treno.mediaValutazioni}</div>
						<!-- Sovrapposizione con titolo e pulsante -->

						<h4 class="title">${treno.nome}</h4>
						<div class="card-img-overlay">
                        <button type="button" class="btn btn-primary btn-dettagli"  onclick="openModal(${treno.idTreno})">Vedi Dettagli</button>
                    </div>

					</div>
					</div>



			


                    <div id="modal-${treno.idTreno}" class="modal">
                    <div class="modal-content">
                        <span class="close-btn" onclick="closeModal(${treno.idTreno})">&times;</span>
                        <h2>Dettagli del Treno</h2>
                        <img src="${treno.immagine}" alt="${treno.nome} Image" style="width: 100%; height: auto; margin-bottom: 20px;">
                        <p><strong>Nome:</strong> ${treno.nome}</p>
                        <p><strong>Marca:</strong> ${treno.marca}</p>
                        <p><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
                        <p><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
                        <p><strong>Costo Totale:</strong> ${treno.costoTotale} €</p>
                        <p><strong>Lunghezza:</strong> ${treno.lunghezzaTotale} m</p>
                        <p><strong>Posti:</strong> ${treno.postiTotali}</p>
                    </div>
                </div>




				</c:forEach>
			</div>
		</div>

		<!-- Controlli di paginazione -->
		    
         <div class="pagination-controls">
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item" id="firstBtn"><a class="page-link" href="#">First</a></li>
            <li class="page-item" id="prevBtn"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item" id="nextBtn"><a class="page-link" href="#">Next</a></li>
            <li class="page-item" id="lastBtn"><a class="page-link" href="#">Last</a></li>
        </ul>
    </nav>
</div>



	</div>

</body>



<script>
    const cards = document.querySelectorAll('.train-card-details');
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
<title>Gestione Treni - Visualizza Treni</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<style>
body, html {
    height: 100%;
    overflow: hidden;
}

.main-container {
    display: flex;
    gap: 20px;
    margin-top: -20px;
}

/* Griglia di carte per i treni */
.train-grid {
    flex-basis: 70%;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 15px;
}

/* Stile per le card dei treni, ridotte nelle dimensioni */
.train-card-details {
    position: relative;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border: 1px solid #ccc;
    border-radius: 5px;
    overflow: hidden;
    height: 230px; /* Ridotto rispetto ai 250px precedenti */
    width: 230px; /* Ridotto rispetto ai 200px precedenti */
    text-align: center;
    display: absolute;
    text-align: center;
    border-radius: 2px;
    
}




/* Nome del treno sopra l'immagine */
.train-card-details .title {
    position: absolute;
    top: 10px;
    left: 50%;
    transform: translateX(-50%);
    color: white;
    font-weight: bold;
    background: transparent;
    padding: 5px;
    border-radius: 3px;
}

/* Immagine della card */
.train-card-details img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

/* Bottone "Vedi Dettagli" nella parte inferiore della card */
.train-card-details .card-img-overlay {
    position: absolute;
    bottom: 0;
    width: 100%;
    background: transparent;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.train-card-details .btn-dettagli {
    font-size: 0.9em;
}

.train-card-details .rating {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 1em;
    color: gold;
    background-color: rgba(0, 0, 0, 0.6);
    border-radius: 50%;
    padding: 5px;
}

/* Sezione filtro */
.filter-section {
    flex-basis: 25%;
    max-width: 300px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
    position: sticky;
    top: 0;
    height: fit-content;
}

.pagination-controls {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}
</style>

</head>
<body>

<div class="page-content-detilas">
    <h1>Visualizza Treni</h1>
    <div class="main-container">

         <!-- Sezione filtro con i campi aggiuntivi -->
        <div class="filter-section">
            <h4>Filtra i Treni</h4>
            <form action="${pageContext.request.contextPath}/treni/filtro" method="get">
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="marca">Marca</label>
                    <select id="marca" name="marca" class="form-control">
                        <option value="">Tutte</option>
                        <option value="italiano" ${trenoFilter.marca == 'italiano' ? 'selected' : ''}>Italiano</option>
                        <option value="francese" ${trenoFilter.marca == 'francese' ? 'selected' : ''}>Francese</option>
                        <option value="tedesco" ${trenoFilter.marca == 'tedesco' ? 'selected' : ''}>Tedesco</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="valutazioneMedia">Valutazione Media (massima)</label>
                    <input type="number" id="valutazioneMedia" name="valutazioneMedia" value="${trenoFilter.valutazioneMedia}" class="form-control" min="1" max="5">
                </div>
                <div class="form-group">
                    <label for="lunghezza">Lunghezza (massima)</label>
                    <input type="number" id="lunghezza" name="lunghezza" value="${trenoFilter.lunghezza}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="pesoTotale">Peso Totale (massimo)</label>
                    <input type="number" id="pesoTotale" name="pesoTotale" value="${trenoFilter.pesoTotale}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="costoTotale">Costo Totale (massimo)</label>
                    <input type="number" id="costoTotale" name="costoTotale" value="${trenoFilter.costoTotale}" class="form-control">
                </div>
                <button type="submit" class="btn btn-primary">Applica Filtro</button>
            </form>
        </div>

        <!-- Griglia con le carte dei treni -->
        <div class="train-grid" id="trainGrid">
            <c:forEach var="treno" items="${treniDto}" varStatus="status">
            
                <div class="train-card-details">
                    <div class="title">${treno.nome}</div>
                    <img src="${treno.immagine}" alt="Immagine del Treno">
                    <div class="rating">&#9733; ${treno.mediaValutazioni}</div>
                    <div class="card-img-overlay">
                   <button type="button" class="btn btn-primary btn-dettagli" onclick="openModal(${treno.idTreno})">Vedi Dettagli</button>
                    </div>
                </div>

                <!-- Modale per i dettagli del treno -->
               <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered" id="modal-${treno.idTreno}">
                    <div class="modal-content">
                        <span class="close-btn" onclick="closeModal(${treno.idTreno})">&times;</span>
                        <h2>Dettagli del Treno</h2>
                        <img src="${treno.immagine}" alt="${treno.nome} Image" style="width: 200%; height: auto; margin-bottom: 20px;">
                        <p><strong>Nome:</strong> ${treno.nome}</p>
                        <p><strong>Marca:</strong> ${treno.marca}</p>
                        <p><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
                        <p><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
                        <p><strong>Costo Totale:</strong> ${treno.costoTotale} €</p>
                        <p><strong>Lunghezza:</strong> ${treno.lunghezzaTotale} m</p>
                        <p><strong>Posti:</strong> ${treno.postiTotali}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Controlli di paginazione -->
    <div class="pagination-controls">
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item" id="firstBtn"><a class="page-link" href="#">Prima</a></li>
                <li class="page-item" id="prevBtn"><a class="page-link" href="#">Indietro</a></li>
                <li class="page-item" id="nextBtn"><a class="page-link" href="#">Avanti</a></li>
                <li class="page-item" id="lastBtn"><a class="page-link" href="#">Ultima</a></li>
            </ul>
        </nav>
    </div>
</div>

<script>
    const cardsPerPage = 12; // Ora obbligatorio 12 card per pagina
    const cards = Array.from(document.querySelectorAll('.train-card-details'));
    let currentPage = 0;
    let totalPages = Math.ceil(cards.length / cardsPerPage);

    function showPage(page) {
        const start = page * cardsPerPage;
        const end = start + cardsPerPage;
        const pageCards = cards.slice(start, end);

        document.getElementById('trainGrid').innerHTML = '';
        pageCards.forEach(card => document.getElementById('trainGrid').appendChild(card));

        while (document.getElementById('trainGrid').children.length < cardsPerPage) {
            const placeholder = document.createElement('div');
            placeholder.className = 'train-card-details placeholder';
            document.getElementById('trainGrid').appendChild(placeholder);
        }

        document.getElementById('prevBtn').classList.toggle('disabled', page === 0);
        document.getElementById('nextBtn').classList.toggle('disabled', page === totalPages - 1);
    }

    document.getElementById('firstBtn').addEventListener('click', e => { e.preventDefault(); currentPage = 0; showPage(currentPage); });
    document.getElementById('prevBtn').addEventListener('click', e => { e.preventDefault(); if (currentPage > 0) currentPage--; showPage(currentPage); });
    document.getElementById('nextBtn').addEventListener('click', e => { e.preventDefault(); if (currentPage < totalPages - 1) currentPage++; showPage(currentPage); });
    document.getElementById('lastBtn').addEventListener('click', e => { e.preventDefault(); currentPage = totalPages - 1; showPage(currentPage); });

    showPage(currentPage);
    
    
    
    
    
    function openModal(id) {
        document.getElementById("modal-" + id).style.display = "flex"; // Mostra il modale
    }

    function closeModal(id) {
        document.getElementById("modal-" + id).style.display = "none"; // Nasconde il modale
    }
</script>

</body>
</html>
 