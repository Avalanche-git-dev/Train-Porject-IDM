<%-- 
 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Profilo utente nell'angolo sinistro sotto la navbar */
        .profile-section {
            position: absolute;
            top: 100px; /* Aggiustato per essere sotto la navbar */
            left: 20px;
            width: 300px;
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Contenitore principale per allineare profilo, lista e filtro */
        .main-section {
            display: flex;
            justify-content: space-between;
            margin-top: 100px; /* Spazio sotto la navbar */
        }

        /* Filtro come colonna laterale destra attaccata al margine destro */
        .filter-section {
            background-color: #e9ecef;
            padding: 10px;
            border-radius: 8px;
            margin-left: 20px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            width: 300px; /* Larghezza fissa della colonna del filtro */
        }

        /* Campo del filtro con label e form uno sotto l'altro */
        .filter-section form {
            display: block;
        }

        .filter-section .form-group {
            margin-bottom: 15px;
            width: 100%;
        }

        .filter-section label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        /* Lista dei treni centrata */
        .train-list-section {
            flex: 1;
            margin-left: 20px;
            margin-right: 20px;
        }

        .scrollable-list {
            max-height: 600px;
            overflow-y: auto;
        }

        .card {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Visualizza eventuali errori -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <!-- Sezione principale con profilo a sinistra, lista centrata, filtro a destra -->
        <div class="main-section">
            <!-- Sezione del profilo utente nell'angolo sinistro -->
            <div class="profile-section">
                <h4>Profilo Utente</h4>
                <p><strong>Nome:</strong> ${nome}</p>
                <p><strong>Username:</strong> ${username}</p>
                <p><strong>Numero di Treni:</strong> ${numeroTreni}</p>
            </div>

            <!-- Lista dei treni dell'utente centrata -->
            <div class="train-list-section">
                <h2>I Treni dell'Utente</h2>
                <div class="scrollable-list">
                    <c:forEach var="treno" items="${listaTreni}">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${treno.nome}</h5>
                                <p class="card-text">Marca: ${treno.marca}</p>
                                <p class="card-text">Valutazione Media: ${treno.mediaValutazioni} / 5</p>
                                <p class="card-text">Lunghezza: ${treno.lunghezza} m</p>
                                <p class="card-text">Peso Totale: ${treno.pesoTotale} kg</p>
                                <p class="card-text">Costo Totale: ${treno.costoTotale} €</p>
                                <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post">
                                    <input type="hidden" name="idTreno" value="${treno.idTreno}">
                                    <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Sezione filtro nell'angolo destro -->
            <div class="filter-section">
                <h2>Filtra i Treni</h2>
                <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get">
                    <div class="form-group">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="marca">Marca</label>
                        <select id="marca" name="marca" class="form-control">
                            <option value="">Tutte</option>
                            <option value="Marca A" ${trenoFilter.marca == 'Marca A' ? 'selected' : ''}>Marca A</option>
                            <option value="Marca B" ${trenoFilter.marca == 'Marca B' ? 'selected' : ''}>Marca B</option>
                            <option value="Marca C" ${trenoFilter.marca == 'Marca C' ? 'selected' : ''}>Marca C</option>
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




<!-- --------------------------------------------------------------------------------------------------------------------------------------------- -->

<%-- 
 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
            margin: 100px auto;
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

        /* Pulsante "Torna indietro" centrato sopra la lista */
        .back-button {
            text-align: center;
            margin-bottom: 20px;
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
                <p><strong>Nome:</strong> ${nome}</p>
                <p><strong>Username:</strong> ${username}</p>
                <p><strong>Numero di Treni:</strong> ${numeroTreni}</p>
            </div>

            <!-- Lista dei treni dell'utente centrata -->
            <div class="train-list-section">
                <!-- Pulsante per tornare indietro -->
                <div class="back-button">
                    <button class="btn btn-secondary" onclick="window.history.back()">Torna alla pagina precedente</button>
                </div>

                <!-- Titolo della lista dei treni -->
                <h2 class="train-list-title">I Treni dell'Utente</h2>
                
                <div class="scrollable-list">
                    <c:forEach var="treno" items="${listaTreni}">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${treno.nome}</h5>
                                <p class="card-text">Marca: ${treno.marca}</p>
                                <p class="card-text">Valutazione Media: ${treno.mediaValutazioni} / 5</p>
                                <p class="card-text">Lunghezza: ${treno.lunghezza} m</p>
                                <p class="card-text">Peso Totale: ${treno.pesoTotale} kg</p>
                                <p class="card-text">Costo Totale: ${treno.costoTotale} €</p>
                                <form action="${pageContext.request.contextPath}/treni/visualizza/treno" method="post">
                                    <input type="hidden" name="idTreno" value="${treno.idTreno}">
                                    <button type="submit" class="btn btn-primary">Vedi Dettagli</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
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
                            <option value="Marca A" ${trenoFilter.marca == 'Marca A' ? 'selected' : ''}>Marca A</option>
                            <option value="Marca B" ${trenoFilter.marca == 'Marca B' ? 'selected' : ''}>Marca B</option>
                            <option value="Marca C" ${trenoFilter.marca == 'Marca C' ? 'selected' : ''}>Marca C</option>
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


<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Profilo Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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
								<%-- <p class="card-text">Lunghezza: ${treno.lunghezza} m</p> --%>
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
