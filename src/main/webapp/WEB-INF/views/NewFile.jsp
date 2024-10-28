
 
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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

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


.modal-content {
    position: relative; 
    background-color: #fff;
    border-radius: 8px;
    width: 100%;
    max-height: 90%;
    overflow-y: hidden;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}





 




.close-btn {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 1.5rem;
    cursor: pointer;
    color: #333;
    font-weight: bold;
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
            <c:forEach var="treno" items="${treniDto}">
            
                <div class="train-card-details">
                
                    <!--  commento per div per modal aperto  -->
                    <div class="container container-card-detail">
                    <div class="title">${treno.nome}</div>
                    <img src="${treno.immagine}" alt="Immagine del Treno">
                    <div class="rating">&#9733; ${treno.mediaValutazioni}</div>
                   
                    <div class="card-img-overlay">
                   <button type="button" class="btn btn-primary btn-dettagli" onclick="openModal(${treno.idTreno})">Vedi Dettagli</button>
                        <button type="button" class="btn btn-primary  btn-dettagli" data-bs-toggle="modal-dialog" data-bs-target="modal-${treno.idTreno}">Dettagli</button>
                    </div>
                     <!--  commento per div per modal aperto  --></div>

                <!-- Modale per i dettagli del treno -->
               <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered" id="modal-${treno.idTreno}">
                    <div class="modal-content">
                    <div class="modal-header">
                        <span class="close-btn" onclick="closeModal(${treno.idTreno})">&times;</span>
                       
                       
                       
                       <button type="button" class="btn-close" data-bs-dismiss="modal-dialog"aria-label="Close"></button>
										
                        <h2>Dettagli del Treno</h2>
                        </div>
                        <div class="modal-body">
                        <img src="${treno.immagine}" alt="Immagine del Treno">
                        <p><strong>Nome:</strong> ${treno.nome}</p>
                        <p><strong>Marca:</strong> ${treno.marca}</p>
                        <p><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
                        <p><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
                        <p><strong>Costo Totale:</strong> ${treno.costoTotale} €</p>
                        <p><strong>Lunghezza:</strong> ${treno.lunghezzaTotale} m</p>
                        <p><strong>Posti:</strong> ${treno.postiTotali}</p>
                        </div>
                        <div class="moadl-footer">
                        
                        <button class="btn" type="button"></button>
                        
                        </div>
                    </div>
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
    
    
    
  

</script>

</body>
</html>
 
  