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
}


.main-container {
    display: flex;
    gap: 20px;
    margin-top: -15px;
}

/* Griglia di carte per i treni */
.train-grid {
    flex-basis: 70%;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 15px;
}
.train-card-details {
    position: relative;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border: none; /* Rimosso il bordo */
    border-radius: 5px; /* Arrotondamento leggero */
    overflow: hidden;
    height: 230px;
    width: 230px;
    text-align: center;
}




/* Immagine della card, a schermo pieno */
.train-card-details img {
    width: 100%;
    height: 100%;
}

/* Titolo del treno, stile più accattivante */
.train-card-details .title {
    position: absolute;
    top: 10px;
    left: 50%;
    transform: translateX(-50%);
    color: white;
    font-weight: bold;
    padding: 5px 10px;
    border-radius: 3px;
    font-size: 1.1em; /* Leggermente più grande */
    font-family: 'Arial', sans-serif;
    letter-spacing: 0.5px;
}

/* Posizionamento del rating (stellina) */
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

/* Bottone "Vedi Dettagli" nella parte inferiore della card */
.train-card-details .card-img-overlay {
    position: absolute;
    bottom: -190px; /* Posizionato nella parte inferiore */
    width: 100%;
    padding: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    transition: background 0.3s ease;
}

.train-card-details .btn-dettagli {
    font-size: 0.9em;
    color: white;
    background-color: transparent;
    border: 1px solid white; /* Bordo bianco per maggiore visibilità */
    padding: 5px 15px;
    border-radius: 5px;
}

.train-card-details .card-img-overlay:hover {
    background: rgba(0, 0, 0, 0.8); /* Sfondo leggermente più scuro al passaggio del mouse */
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
    margin-left: 10px;
}

.pagination-controls {
    margin-top: 20px;
    display: flex;
    justify-content: center;
}
</style>

</head>
<body>

<div class="page-content-details">
   <h1 class="mb-3 ml-1 p-2"style="margin-top:-8px;">Visualizza Treni</h1>
    <div class="main-container">
    
    
    
    <div class="filter-section">
    <h4>Filtra i Treni</h4>
    <form action="${pageContext.request.contextPath}/treni/filtro" method="get">
        <!-- Filtro per Nome -->
        <div class="form-group">
            <label for="nome">Nome del Treno</label>
            <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control">
        </div>
        
        <!-- Filtro per Valore -->
        <div class="form-group">
            <label for="valore">Valore</label>
            <input type="number" id="valore" name="valore" value="${trenoFilter.valore}" class="form-control" min="0">
        </div>
        
        <!-- Filtro per Media Valutazioni -->
        <div class="form-group">
            <label for="mediaValutazioni">Media Valutazioni</label>
            <input type="number" id="mediaValutazioni" name="mediaValutazioni" value="${trenoFilter.mediaValutazioni}" class="form-control" min="1" max="5" step="0.1">
        </div>
        
        <!-- Filtro per Lunghezza -->
        <div class="form-group">
            <label for="lunghezza">Lunghezza (massima)</label>
            <input type="number" id="lunghezza" name="lunghezza" value="${trenoFilter.lunghezza}" class="form-control" min="0">
        </div>
        
        <!-- Filtro per Peso Variabile -->
        <div class="form-group">
            <label for="pesoVariabile">Peso Variabile</label>
            <input type="number" id="pesoVariabile" name="pesoVariabile" value="${trenoFilter.pesoVariabile}" class="form-control" min="0">
        </div>
        
        <!-- Pulsante per Applica Filtro -->
        <button type="submit" class="btn btn-primary">Applica Filtro</button>
    </form>

    <!-- Opzioni di Ordinamento -->
    <div class="sort-section mt-3">
        <h5>Ordina per:</h5>
        <form action="${pageContext.request.contextPath}/treni/filtro" method="get">
            <!-- Ordinamento per Valore -->
            <div class="form-check">
                <input type="radio" id="ordinaValore" name="ordine" value="valore" class="form-check-input">
                <label for="ordinaValore" class="form-check-label">Valore</label>
            </div>
            
            <!-- Ordinamento per Media Valutazioni -->
            <div class="form-check">
                <input type="radio" id="ordinaMediaValutazioni" name="ordine" value="mediaValutazioni" class="form-check-input">
                <label for="ordinaMediaValutazioni" class="form-check-label">Media Valutazioni</label>
            </div>
            
            <!-- Ordinamento per Lunghezza -->
            <div class="form-check">
                <input type="radio" id="ordinaLunghezza" name="ordine" value="lunghezza" class="form-check-input">
                <label for="ordinaLunghezza" class="form-check-label">Lunghezza</label>
            </div>
            
            <!-- Ordinamento per Peso Variabile -->
            <div class="form-check">
                <input type="radio" id="ordinaPesoVariabile" name="ordine" value="pesoVariabile" class="form-check-input">
                <label for="ordinaPesoVariabile" class="form-check-label">Peso Variabile</label>
            </div>
            
            <!-- Pulsante per Ordinamento -->
            <button type="submit" class="btn btn-secondary mt-2">Ordina</button>
        </form>
    </div>
</div>
    

        

















        <!-- Griglia con le carte dei treni -->
        <div class="train-grid" id="trainGrid">
            <c:forEach var="treno" items="${treniDto}">
                <div class="train-card-details">
                    <div class="container container-card-detail">
                        <div class="title">${treno.nome}</div>
                        <img src="${treno.immagine}" alt="Immagine del Treno">
                        <div class="rating">&#9733; ${treno.mediaValutazioni}</div>
                        <div class="card-img-overlay">
                            <!-- Pulsante che apre il modale -->
                            <button type="button" class="btn btn-primary btn-dettagli" 
                                    data-bs-toggle="modal" 
                                    data-bs-target="#modal-${treno.idTreno}">
                                Vedi Dettagli
                            </button>
                        </div>
                    </div>
                </div>

               <div class="modal fade" id="modal-${treno.idTreno}" tabindex="-1" aria-labelledby="modalLabel-${treno.idTreno}" 
         aria-hidden="true" data-bs-backdrop="static" data-bs-keyboard="false">
        <div class="modal-dialog modal-dialog-scrollable modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalLabel-${treno.idTreno}">Dettagli del Treno</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <img src="${treno.immagine}" alt="Immagine del Treno" class="img-fluid mb-3">
                    <p><strong>Nome:</strong> ${treno.nome}</p>
                    <p><strong>Marca:</strong> ${treno.marca}</p>
                    <p><strong>Media Valutazioni:</strong> ${treno.mediaValutazioni} / 5</p>
                    <p><strong>Peso Totale:</strong> ${treno.pesoTotale} kg</p>
                    <p><strong>Costo Totale:</strong> ${treno.costoTotale} €</p>
                    <p><strong>Lunghezza:</strong> ${treno.lunghezzaTotale} m</p>
                    <p><strong>Posti:</strong> ${treno.postiTotali}</p>
                </div>
                <div class="modal-footer">
                    <!-- Pulsante che apre il modale per inserire il prezzo di vendita -->
                    <button type="button" class="btn btn-success" 
                            data-bs-toggle="modal" 
                            data-bs-target="#prezzoVenditaModal-${treno.idTreno}">
                        Vendi
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modale per l'inserimento del prezzo di vendita per ciascun treno -->
    <div class="modal fade" id="prezzoVenditaModal-${treno.idTreno}" tabindex="-1" aria-labelledby="prezzoVenditaModalLabel-${treno.idTreno}" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="prezzoVenditaModalLabel-${treno.idTreno}">Inserisci il Prezzo di Vendita</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="${pageContext.request.contextPath}/treni/vendi" method="post">
                        <!-- Campo nascosto per passare l'id del treno specifico -->
                        <input type="hidden" name="idTreno" value="${treno.idTreno}">
                        
                        <div class="form-group">
                            <label for="prezzoVendita-${treno.idTreno}">Prezzo di Vendita:</label>
                            <input type="number" step="0.01" min="0" name="prezzoVendita" 
                                   id="prezzoVendita-${treno.idTreno}" 
                                   class="form-control" required>
                        </div>
                        
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Conferma Vendita</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annulla</button>
                        </div>
                    </form>
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

</body>



<script>


function mostraCampoPrezzo() {
    // Seleziona il form e rendilo visibile
    const vendiForm = document.getElementById("vendiForm");
    vendiForm.style.display = "block";
    
    // Nascondi il bottone "Vendi" per evitare duplicazioni
    const vendiButton = document.getElementById("vendiButton");
    vendiButton.style.display = "none";
}




const cardsPerPage = 12; // Numero di card per pagina
const cards = Array.from(document.querySelectorAll('.train-card-details'));
let currentPage = 0;
let totalPages = Math.ceil(cards.length / cardsPerPage);

function showPage(page) {
    const start = page * cardsPerPage;
    const end = start + cardsPerPage;

    // Mostra solo le card della pagina corrente, nasconde le altre
    cards.forEach((card, index) => {
        card.style.display = (index >= start && index < end) ? 'block' : 'none';
    });

    // Gestione dei pulsanti di navigazione
    document.getElementById('prevBtn').classList.toggle('disabled', page === 0);
    document.getElementById('nextBtn').classList.toggle('disabled', page === totalPages - 1);
}

// Event listeners per la navigazione della paginazione
document.getElementById('firstBtn').addEventListener('click', e => { e.preventDefault(); currentPage = 0; showPage(currentPage); });
document.getElementById('prevBtn').addEventListener('click', e => { e.preventDefault(); if (currentPage > 0) currentPage--; showPage(currentPage); });
document.getElementById('nextBtn').addEventListener('click', e => { e.preventDefault(); if (currentPage < totalPages - 1) currentPage++; showPage(currentPage); });
document.getElementById('lastBtn').addEventListener('click', e => { e.preventDefault(); currentPage = totalPages - 1; showPage(currentPage); });

// Mostra la prima pagina all'avvio
showPage(currentPage);

</script>
   
</html>
  