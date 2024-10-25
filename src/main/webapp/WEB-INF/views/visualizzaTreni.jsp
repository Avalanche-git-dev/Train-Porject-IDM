<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Treni dell'Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
</html>
