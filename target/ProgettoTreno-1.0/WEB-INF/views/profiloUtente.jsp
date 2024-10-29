
<%-- 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Profilo Utente</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<style>
/* Sezione profilo nell'angolo sinistro sotto la navbar */
.profile-section {
	position: fixed;
	top: 100px; /* Aggiustato per essere sotto la navbar */
	left: 20px;
	width: 300px;
	background-color: #f8f9fa;
	padding: 15px;
	border-radius: 8px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

/* Filtro come colonna laterale destra attaccata al margine destro */
.filter-section {
	position: fixed;
	top: 100px;
	right: 20px;
	background-color: #e9ecef;
	padding: 15px;
	border-radius: 8px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
	width: 300px;
}

/* Sezione centrale per la lista dei treni */
.train-list-section {
	margin: 60px auto;
	max-width: 800px; /* Larghezza fissa per la lista */
}

.scrollable-list {
	max-height: 600px;
	overflow-y: auto;
}

/* Stile per il titolo della lista, centrato e allineato con la lista */
.train-list-title {
	text-align: center;
	margin-bottom: 20px;
}

.card {
	margin-bottom: 10px;
}

/* Pulsante "Torna indietro" sotto la sezione profilo, non incluso nella colonna */
.back-button {
	position: absolute;
	left: 20px;
	top: 310px;
	width: 170px;
	white-space: nowrap; /* Regolato per stare sotto la sezione profilo */
}



 body {
        font-family: 'Roboto', sans-serif;
        background-color: #f8f8ff; 
    }
</style>
</head>
<body>
	<div class="container">
		<!-- Visualizza eventuali errori -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger">${errorMessage}</div>
		</c:if>

		<!-- Sezione principale con layout centrato -->
		<div class="main-section">
			<!-- Sezione del profilo utente nell'angolo sinistro -->
			<div class="profile-section">
				<h4>Profilo Utente</h4>
				<p>
					<strong>Nome:</strong> ${nome}
				</p>
				<p>
					<strong>Username:</strong> ${username}
				</p>
				<p>
					<strong>Treni posseduti :</strong> ${numeroTreni}
				</p>
			</div>

			<!-- Pulsante "Torna alla pagina precedente" sotto il profilo -->
			<div class="back-button">
				<button class="btn btn-primary" onclick="window.history.back()">Indietro</button>
			</div>

			<!-- Lista dei treni dell'utente centrata -->
			<div class="train-list-section">
				<!-- Titolo della lista dei treni -->
				<h2 class="train-list-title">I Treni dell'Utente</h2>

				<div class="scrollable-list">
					<c:forEach var="treno" items="${listaTreni}">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">${treno.nome}</h5>
								<p class="card-text">Marca: ${treno.marca}</p>
								<p class="card-text">Valutazione Media:
									${treno.mediaValutazioni} / 5</p>
								<p class="card-text">Lunghezza: ${treno.lunghezza} m</p>
								<p class="card-text">Peso Totale: ${treno.pesoTotale} kg</p>
								<p class="card-text">Costo Totale: ${treno.costoTotale} €</p>
								<form
									action="${pageContext.request.contextPath}/treni/visualizza/treno"
									method="post">
									<input type="hidden" name="idTreno" value="${treno.idTreno}">
									<button type="submit" class="btn btn-primary">Vedi
										Dettagli</button>
								</form>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>

			<!-- Sezione filtro nell'angolo destro -->
			<div class="filter-section">
				<h4>Filtra i Treni</h4>
				<form action="${pageContext.request.contextPath}/catalogo/filtro"
					method="get">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text" id="nome"
							name="nome" value="${trenoFilter.nome}" class="form-control">
					</div>
					<div class="form-group">
						<label for="marca">Marca</label> <select id="marca" name="marca"
							class="form-control">
							<option value="">Tutte</option>
							<option value="Marca A"
								${trenoFilter.marca == 'italiano' ? 'selected' : ''}>Marca
								A</option>
							<option value="Marca B"
								${trenoFilter.marca == 'francese' ? 'selected' : ''}>Marca
								B</option>
							<option value="Marca C"
								${trenoFilter.marca == 'tedesco' ? 'selected' : ''}>Marca
								C</option>
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
		</div>
	</div>
</body>
</html>
 --%>
 
 
 
 
 
 
 
 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Profilo Utente</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
        /* Layout principale e stili generali */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f8ff;
        }
        .container {
            margin-top: 100px;
        }
        .profile-info, .train-list-section, .filter-section {
            background-color: #f8f9fa;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .profile-info {
            position: fixed;
            top: 100px;
            left: 20px;
            width: 300px;
        }
        .train-list-section {
            margin-left: 360px;
            margin-right: 340px;
        }
        .filter-section {
            position: fixed;
            top: 100px;
            right: 20px;
            width: 300px;
        }
        .train-list-title {
            text-align: center;
            font-size: 1.8em;
            font-weight: bold;
            color: #333;
        }

        /* Stili card treni */
        .train-card {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 15px;
            padding: 15px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .train-card:hover {
            transform: scale(1.02);
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
        }
        .train-card h5 {
            font-size: 1.25em;
            font-weight: bold;
            color: #333;
        }
        .train-card p {
            color: #555;
        }
        .train-card .btn-primary {
            background-color: #FF4500;
            border: none;
            border-radius: 20px;
            padding: 8px 16px;
        }

        /* Stili pulsante indietro */
        .back-button {
            margin-top: 10px;
        }

    </style>
</head>


<body>
<div class="container">
    <!-- Visualizza eventuali errori -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <!-- Sezione del profilo utente -->
    <div class="profile-info">
        <h4>Profilo Utente</h4>
        <p><strong>Nome:</strong> ${nome}</p>
        <p><strong>Username:</strong> ${username}</p>
        <p><strong>Treni posseduti:</strong> ${numeroTreni}</p>
        <button class="btn btn-secondary back-button" onclick="window.history.back()">Indietro</button>
    </div>

    <!-- Lista dei treni dell'utente -->
    <div class="train-list-grid">
        <h2 class="train-list-title">I Treni dell'Utente</h2>
        <div class="scrollable-list">
            <c:forEach var="treno" items="${listaTreni}">
                <div class="train-card">
                    <h5>${treno.nome}</h5>
                    <p>Marca: ${treno.marca}</p>
                    <p>Valutazione Media: ${treno.mediaValutazioni} / 5</p>
                    <p>Peso Totale: ${treno.pesoTotale} kg</p>
                    <p>Costo Totale: ${treno.costoTotale} €</p>
                    <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post">
                        <input type="hidden" name="idTreno" value="${treno.idTreno}">
                        <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Sezione filtro treni -->
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
                    <option value="italiano" ${trenoFilter.marca == 'italiano' ? 'selected' : ''}>Marca A</option>
                    <option value="francese" ${trenoFilter.marca == 'francese' ? 'selected' : ''}>Marca B</option>
                    <option value="tedesco" ${trenoFilter.marca == 'tedesco' ? 'selected' : ''}>Marca C</option>
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
</body>
</html>
 