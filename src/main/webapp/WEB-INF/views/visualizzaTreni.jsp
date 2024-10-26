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

        /* Stile delle singole carte */
        .train-card {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 15px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
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



