<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Treni</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

	<!-- Sezione per visualizzare tutti i treni disponibili -->
	<div class="container">
    <h1>Tutti i treni in vendita</h1>
    
    <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/market/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVendita}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>
    
    <c:forEach var="treno" items="${treniInVendita}">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">${treno.nome}</h5>
                <p class="card-text">Marca: ${treno.marca}</p> <!-- Gestione dei valori nulli -->
                <p class="card-text">Peso Totale: ${treno.pesoTotale} kg</p>
                <p class="card-text">Prezzo di Vendita: ${treno.prezzoVendita}€</p>
                <!-- Contenitore per i pulsanti -->
                <div>
                    <a href="${pageContext.request.contextPath}/treni/dettagli/${treno.idTreno}" class="btn btn-primary">Vedi Dettagli</a>
                    <form action="${pageContext.request.contextPath}/acquista" method="post" style="display: inline;">
                        <input type="hidden" name="idTreno" value="${treno.idTreno}" />
                        <button type="submit" class="btn btn-primary">Acquista Treno</button>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
	</div>

	<!-- Footer -->
	<footer class="text-center">
		<p>&copy; 2024 YourWebsite. All rights reserved.</p>
	</footer>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="https://kit.fontawesome.com/a076d05399.js"></script>

</body>
</html>
