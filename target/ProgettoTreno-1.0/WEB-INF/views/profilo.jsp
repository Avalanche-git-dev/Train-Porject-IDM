<%-- 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profilo Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.error-message {
	color: #d9534f;
	background-color: #f8d7da;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.profile-container {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	margin-top: 50px;
}

.profile-section {
	padding: 40px;
	background-color: #f8f9fa;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 800px;
	position: relative;
}

.btn-action {
	background-color: #0275d8;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	margin: 10px;
}

.btn-action:hover {
	background-color: #025aa5;
}

.cancel-btn {
	background-color: #d9534f;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	margin: 10px;
}

.cancel-btn:hover {
	background-color: #c9302c;
}

.wallet-content, .edit-content {
	display: none;
	margin-top: 30px;
}

.button-group {
	display: flex;
	justify-content: center;
	margin-top: 30px;
}
</style>
</head>
<body>

	<div class="container">
		<div class="profile-container">
			<!-- Sezione Profilo Utente -->
			<div class="profile-section">
				<!-- Messaggio di errore, se presente -->
				<!-- Per i messaggi di errore -->
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger" role="alert">${errorMessage}</div>
				</c:if>

				<!-- Per i messaggi di successo -->
				<c:if test="${not empty successMessage}">
					<div class="alert alert-success" role="alert">${successMessage}</div>
				</c:if>



				<!-- Informazioni utente -->
				<h5 class="text-center mt-5">Informazioni Utente</h5>
				<table class="table table-borderless">
					<tr>
						<th>Nome:</th>
						<td>${userInfo.nome}</td>
					</tr>
					<tr>
						<th>Cognome:</th>
						<td>${userInfo.cognome}</td>
					</tr>
					<tr>
						<th>Email:</th>
						<td>${userInfo.email}</td>
					</tr>
					<tr>
						<th>Telefono:</th>
						<td>${userInfo.telefono}</td>
					</tr>
					<tr>
						<th>Username:</th>
						<td>${userInfo.username}</td>
					</tr>
					<tr>
						<th>Portafoglio:</th>
						<td>${userInfo.portafoglio}€</td>
					</tr>
				</table>

				<!-- Sezioni di modifica e portafoglio -->
				<div class="edit-content" id="editContent">
					<form action="${pageContext.request.contextPath}/profilo/modifica"
						method="post" onsubmit="return validateForm()">
						<div class="form-group">
							<label for="passwordVecchia">Password Vecchia:</label> <input
								type="password" class="form-control" id="passwordVecchia"
								name="passwordVecchia">
						</div>
						<div class="form-group">
							<label for="passwordNuova">Nuova Password:</label> <input
								type="password" class="form-control" id="passwordNuova"
								name="passwordNuova">
						</div>
						<div class="form-group">
							<label for="email">Email:</label> <input type="email"
								class="form-control" id="email" name="email"
								value="${userInfo.email}">
						</div>
						<div class="form-group">
							<label for="telefono">Telefono:</label> <input type="text"
								class="form-control" id="telefono" name="telefono"
								value="${userInfo.telefono}">
						</div>
						<button type="submit" class="btn btn-action">Salva
							Modifiche</button>
					</form>
				</div>

				<div class="wallet-content" id="walletContent">
					<p>Saldo attuale: ${userInfo.portafoglio} €</p>
					<form
						action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi"
						method="post">
						<div class="form-group">
							<label for="importo">Aggiungi denaro:</label> <input
								type="number" step="0.01" min="0" class="form-control"
								id="importo" name="importo" required>
						</div>
						<button type="submit" class="btn btn-action">Ricarica</button>
					</form>
				</div>

				<!-- Gruppo di bottoni azione -->
				<div class="button-group">
					<button class="btn-action" onclick="toggleEdit()">Modifica
						Profilo</button>
					<button class="btn-action" onclick="toggleWallet()">Portafoglio</button>
					<button class="btn-action"
						onclick="window.location.href='${pageContext.request.contextPath}/treni/visualizza'">Vai
						a Treni</button>
					<button class="cancel-btn" onclick="confirmDeleteAccount()">Cancella
						Account</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Script per gestire l'espansione del portafoglio e modifica profilo -->
	<script>
		function toggleWallet() {
			const walletContent = document.getElementById('walletContent');
			walletContent.style.display = (walletContent.style.display === 'none' || walletContent.style.display === '') ? 'block'
					: 'none';
		}

		function toggleEdit() {
			const editContent = document.getElementById('editContent');
			editContent.style.display = (editContent.style.display === 'none' || editContent.style.display === '') ? 'block'
					: 'none';
		}

		function confirmDeleteAccount() {
			if (confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')) {
				window.location.href = '${pageContext.request.contextPath}/profilo/elimina';
			}
		}

		function validateForm() {
			const passwordVecchia = document.getElementById('passwordVecchia').value;
			const passwordNuova = document.getElementById('passwordNuova').value;

			// Se uno dei campi della password è riempito, l'altro deve esserlo
			if ((passwordVecchia && !passwordNuova)
					|| (!passwordVecchia && passwordNuova)) {
				alert("Per cambiare la password, devi inserire sia la vecchia che la nuova password.");
				return false;
			}

			// Se nessuno dei campi è riempito, va bene, non obblighiamo a cambiare la password
			return true;
		}
	</script>

	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>



<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profilo Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.error-message {
	color: #d9534f;
	background-color: #f8d7da;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.success-message {
	color: #28a745;
	background-color: #d4edda;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.profile-container {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	margin-top: 50px;
}

.profile-section {
	padding: 40px;
	background-color: #f8f9fa;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	width: 100%;
	max-width: 800px;
	position: relative;
}

.btn-action {
	background-color: #0275d8;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	margin: 10px;
}

.btn-action:hover {
	background-color: #025aa5;
}

.cancel-btn {
	background-color: #d9534f;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	margin: 10px;
}

.cancel-btn:hover {
	background-color: #c9302c;
}

.wallet-content, .edit-content {
	display: none;
	margin-top: 30px;
}

.button-group {
	display: flex;
	justify-content: center;
	margin-top: 30px;
}

.input-error {
	border-color: #d9534f !important;
	background-color: #f8d7da !important;
}
</style>
</head>
<body>

	<div class="container">
		<div class="profile-container">
			<!-- Sezione Profilo Utente -->
			<div class="profile-section">
				<!-- Messaggio di errore, se presente -->
				<c:if test="${not empty errorMessage}">
					<div class="error-message" role="alert">${errorMessage}</div>
				</c:if>

				<!-- Messaggio di successo, se presente -->
				<c:if test="${not empty successMessage}">
					<div class="success-message" role="alert">${successMessage}</div>
				</c:if>

				<!-- Informazioni utente -->
				<h5 class="text-center mt-5">Informazioni Utente</h5>
				<table class="table table-borderless">
					<tr>
						<th>Nome:</th>
						<td>${userInfo.nome}</td>
					</tr>
					<tr>
						<th>Cognome:</th>
						<td>${userInfo.cognome}</td>
					</tr>
					<tr>
						<th>Username:</th>
						<td>${userInfo.username}</td>
					</tr>
					<tr>
						<th>Portafoglio:</th>
						<td>${userInfo.portafoglio}€</td>
					</tr>
				</table>

				<!-- Sezioni di modifica e portafoglio -->
				<div class="edit-content" id="editContent">
					<form action="${pageContext.request.contextPath}/profilo/modifica" method="post" onsubmit="return validateForm()">
						<div class="form-group">
							<label for="passwordVecchia">Password Vecchia:</label>
							<input type="password" class="form-control ${not empty errorMessage ? 'input-error' : ''}" id="passwordVecchia" name="passwordVecchia">
						</div>
						<div class="form-group">
							<label for="passwordNuova">Nuova Password:</label>
							<input type="password" class="form-control ${not empty errorMessage ? 'input-error' : ''}" id="passwordNuova" name="passwordNuova">
						</div>
						<div class="form-group">
							<label for="email">Email:</label>
							<input type="email" class="form-control ${not empty errorMessage ? 'input-error' : ''}" id="email" name="email" value="${userInfo.email}">
						</div>
						<div class="form-group">
							<label for="telefono">Telefono:</label>
							<input type="text" class="form-control ${not empty errorMessage ? 'input-error' : ''}" id="telefono" name="telefono" value="${userInfo.telefono}">
						</div>
						<button type="submit" class="btn btn-action">Salva Modifiche</button>
					</form>
				</div>

				<div class="wallet-content" id="walletContent">
					<p>Saldo attuale: ${userInfo.portafoglio} €</p>
					<form action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi" method="post">
						<div class="form-group">
							<label for="importo">Aggiungi denaro:</label>
							<input type="number" class="form-control ${not empty errorMessage ? 'input-error' : ''}" id="importo" name="importo" required>
						</div>
						<button type="submit" class="btn btn-action">Ricarica</button>
					</form>
				</div>

				<!-- Gruppo di bottoni azione -->
				<div class="button-group">
					<button class="btn-action" onclick="toggleEdit()">Modifica Profilo</button>
					<button class="btn-action" onclick="toggleWallet()">Portafoglio</button>
					<button class="btn-action" onclick="closeAllForms()">Chiudi Tutti</button>
					<button class="btn-action" onclick="submitMostraUtente()">Mostra Utente</button>
					<button class="cancel-btn" onclick="confirmDeleteAccount()">Cancella Account</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Script per gestire l'espansione del portafoglio e modifica profilo -->
	<script>
		function toggleWallet() {
			const walletContent = document.getElementById('walletContent');
			walletContent.style.display = (walletContent.style.display === 'none' || walletContent.style.display === '') ? 'block' : 'none';
		}

		function toggleEdit() {
			const editContent = document.getElementById('editContent');
			editContent.style.display = (editContent.style.display === 'none' || editContent.style.display === '') ? 'block' : 'none';
		}

		function closeAllForms() {
			document.getElementById('editContent').style.display = 'none';
			document.getElementById('walletContent').style.display = 'none';
		}

		function submitMostraUtente() {
			const username = prompt("Inserisci lo username dell'utente:");
			if (username) {
				const form = document.createElement('form');
				form.method = 'post';
				form.action = '${pageContext.request.contextPath}/profilo/mostra/utente';
				const input = document.createElement('input');
				input.type = 'hidden';
				input.name = 'username';
				input.value = username;
				form.appendChild(input);
				document.body.appendChild(form);
				form.submit();
			}
		}

		function confirmDeleteAccount() {
			if (confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')) {
				window.location.href = '${pageContext.request.contextPath}/profilo/elimina';
			}
		}

		function validateForm() {
			const passwordVecchia = document.getElementById('passwordVecchia').value;
			const passwordNuova = document.getElementById('passwordNuova').value;

			// Se uno dei campi della password è riempito, l'altro deve esserlo
			if ((passwordVecchia && !passwordNuova) || (!passwordVecchia && passwordNuova)) {
				alert("Per cambiare la password, devi inserire sia la vecchia che la nuova password.");
				return false;
			}
			return true;
		}
	</script>

	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
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
<title>Profilo Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.error-message {
	color: #d9534f;
	background-color: #f8d7da;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.success-message {
	color: #28a745;
	background-color: #d4edda;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.profile-container {
	display: flex;
	justify-content: center; /* Centro la pagina */
	margin-top: 50px;
	width: 100%;
}

.profile-section {
	padding: 40px;
	background-color: #f8f9fa;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	width: 35%; /* Profilo utente ridotto */
}

.sidebar-section {
	padding: 20px;
	background-color: #f1f1f1;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	width: 30%; /* Sidebar ridotta */
	margin-left: 20px;
	position: relative;
}

.btn-action {
	background-color: #0275d8;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	width: 100%; /* Full width for button */
	margin-bottom: 15px;
}

.btn-action:hover {
	background-color: #025aa5;
}

.cancel-btn {
	background-color: #d9534f;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	position: fixed;
	bottom: 20px;
	right: 20px;
}

.cancel-btn:hover {
	background-color: #c9302c;
}

.section-content {
	display: none;
	background-color: #ffffff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	position: absolute;
	left: 100%; /* Open to the right side */
	top: 0;
	width: 300px;
	z-index: 1000;
}

.button-group {
	display: flex;
	flex-direction: column; /* Stack buttons vertically */
	margin-top: 30px;
	width: 100%; /* Ensure full width for buttons */
}
</style>
</head>
<body>

	<div class="container">
		<div class="profile-container">
			<!-- Profilo utente -->
			<div class="profile-section">
				<h5 class="text-center mt-5">Informazioni Utente</h5>
				<table class="table table-borderless">
					<tr>
						<th>Nome:</th>
						<td>${userInfo.nome}</td>
					</tr>
					<tr>
						<th>Cognome:</th>
						<td>${userInfo.cognome}</td>
					</tr>
					<tr>
						<th>Username:</th>
						<td>${userInfo.username}</td>
					</tr>
					<tr>
						<th>Portafoglio:</th>
						<td>${userInfo.portafoglio}€</td>
					</tr>
				</table>
			</div>

			<!-- Sidebar per azioni -->
			<div class="sidebar-section">
				<!-- Messaggi di errore e successo -->
				<c:if test="${not empty errorMessage}">
					<div class="error-message" role="alert">${errorMessage}</div>
				</c:if>

				<c:if test="${not empty successMessage}">
					<div class="success-message" role="alert">${successMessage}</div>
				</c:if>

				<!-- Opzioni -->
				<div class="button-group">
					<button class="btn-action" onclick="toggleSection('editSection')">Modifica Profilo</button>
					<button class="btn-action" onclick="toggleSection('walletSection')">Portafoglio</button>
					<button class="btn-action" onclick="toggleSection('searchSection')">Cerca Profilo</button>
				</div>

				<!-- Sezione di modifica -->
				<div id="editSection" class="section-content">
					<form action="${pageContext.request.contextPath}/profilo/modifica" method="post">
						<div class="form-group">
							<label for="passwordVecchia">Password Vecchia:</label>
							<input type="password" class="form-control" id="passwordVecchia" name="passwordVecchia">
						</div>
						<div class="form-group">
							<label for="passwordNuova">Nuova Password:</label>
							<input type="password" class="form-control" id="passwordNuova" name="passwordNuova">
						</div>
						<div class="form-group">
							<label for="email">Email:</label>
							<input type="email" class="form-control" id="email" name="email">
						</div>
						<div class="form-group">
							<label for="telefono">Telefono:</label>
							<input type="text" class="form-control" id="telefono" name="telefono">
						</div>
						<button type="submit" class="btn btn-action">Salva Modifiche</button>
					</form>
				</div>

				<!-- Sezione Portafoglio -->
				<div id="walletSection" class="section-content">
					<p>Saldo attuale: ${userInfo.portafoglio} €</p>
					<form action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi" method="post">
						<div class="form-group">
							<label for="importo">Aggiungi denaro:</label>
							<input type="number" class="form-control" id="importo" name="importo" placeholder="Inserisci importo" min="1" required>
						</div>
						<button type="submit" class="btn btn-action">Ricarica</button>
					</form>
				</div>

				<!-- Sezione Cerca Profilo -->
				<div id="searchSection" class="section-content">
					<form action="${pageContext.request.contextPath}/profilo/mostra/utente" method="post">
						<div class="form-group">
							<label for="searchUsername">Inserisci Username:</label>
							<input type="text" class="form-control" name="username" placeholder="Cerca profilo utente">
						</div>
						<button type="submit" class="btn btn-primary">Cerca Profilo</button>
					</form>
				</div>
			</div>
		</div>

		<!-- Pulsante Cancella Account in fondo alla pagina -->
		<button class="cancel-btn" onclick="confirmDeleteAccount()">Cancella Account</button>
	</div>

	<!-- Script -->
	<script>
		function toggleSection(sectionId) {
			const sections = document.querySelectorAll('.section-content');
			sections.forEach(section => {
				section.style.display = 'none';
			});
			document.getElementById(sectionId).style.display = 'block';
		}

		function confirmDeleteAccount() {
			if (confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')) {
				window.location.href = '${pageContext.request.contextPath}/profilo/elimina';
			}
		}
	</script>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>












<%-- <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profilo Utente</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.error-message {
	color: #d9534f;
	background-color: #f8d7da;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.success-message {
	color: #28a745;
	background-color: #d4edda;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 15px;
	text-align: center;
	font-weight: bold;
}

.profile-container {
	display: flex;
	justify-content: flex-start; /* Inizio pagina, sinistra */
	margin-top: 20px; /* Spazio dalla navbar */
	width: 100%;
}

.profile-section {
	padding: 40px;
	background-color: #f8f9fa;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	width: 30%; /* Profilo utente più stretto */
	margin-left: 10px; /* Spazio sotto la navbar */
}

.sidebar-section {
	padding: 20px;
	background-color: #f1f1f1;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	width: 35%; /* Sidebar leggermente più stretta */
	margin-left: 20px;
	margin-top: 30px; /* Più bassa rispetto alla navbar */
	position: relative;
}

.btn-action {
	background-color: #0275d8;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	width: 100%;
	margin-bottom: 15px;
}

.btn-action:hover {
	background-color: #025aa5;
}

.cancel-btn {
	background-color: #d9534f;
	color: white;
	padding: 10px 20px;
	border-radius: 5px;
	border: none;
	cursor: pointer;
	position: fixed;
	bottom: 20px;
	right: 20px;
}

.cancel-btn:hover {
	background-color: #c9302c;
}

.section-content {
	display: none;
	background-color: #ffffff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
	position: absolute;
	left: 100%; /* Open to the right side */
	top: 0;
	width: 300px;
	z-index: 1000;
}

.button-group {
	display: flex;
	flex-direction: column; /* Stack buttons vertically */
	margin-top: 30px;
	width: 100%;
}

.container {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}
</style>
</head>
<body>

	<div class="container">
		<div class="profile-container">
			<!-- Profilo utente -->
			<div class="profile-section">
				<h5 class="text-center mt-5">Informazioni Utente</h5>
				<table class="table table-borderless">
					<tr>
						<th>Nome:</th>
						<td>${userInfo.nome}</td>
					</tr>
					<tr>
						<th>Cognome:</th>
						<td>${userInfo.cognome}</td>
					</tr>
					<tr>
						<th>Username:</th>
						<td>${userInfo.username}</td>
					</tr>
					<tr>
						<th>Portafoglio:</th>
						<td>${userInfo.portafoglio}€</td>
					</tr>
				</table>
			</div>

			<!-- Sidebar per azioni -->
			<div class="sidebar-section">
				<!-- Messaggi di errore e successo -->
				<c:if test="${not empty errorMessage}">
					<div class="error-message" role="alert">${errorMessage}</div>
				</c:if>

				<c:if test="${not empty successMessage}">
					<div class="success-message" role="alert">${successMessage}</div>
				</c:if>

				<!-- Opzioni -->
				<div class="button-group">
					<button class="btn-action" onclick="toggleSection('editSection')">Modifica Profilo</button>
					<button class="btn-action" onclick="toggleSection('walletSection')">Portafoglio</button>
					<button class="btn-action" onclick="toggleSection('searchSection')">Cerca Profilo</button>
				</div>

				<!-- Sezione di modifica -->
				<div id="editSection" class="section-content">
					<form action="${pageContext.request.contextPath}/profilo/modifica" method="post">
						<div class="form-group">
							<label for="passwordVecchia">Password Vecchia:</label>
							<input type="password" class="form-control" id="passwordVecchia" name="passwordVecchia">
						</div>
						<div class="form-group">
							<label for="passwordNuova">Nuova Password:</label>
							<input type="password" class="form-control" id="passwordNuova" name="passwordNuova">
						</div>
						<div class="form-group">
							<label for="email">Email:</label>
							<input type="email" class="form-control" id="email" name="email">
						</div>
						<div class="form-group">
							<label for="telefono">Telefono:</label>
							<input type="text" class="form-control" id="telefono" name="telefono">
						</div>
						<button type="submit" class="btn btn-action">Salva Modifiche</button>
					</form>
				</div>

				<!-- Sezione Portafoglio -->
				<div id="walletSection" class="section-content">
					<p>Saldo attuale: ${userInfo.portafoglio} €</p>
					<form action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi" method="post">
						<div class="form-group">
							<label for="importo">Aggiungi denaro:</label>
							<input type="number" class="form-control" id="importo" name="importo" placeholder="Inserisci importo" min="1" required>
						</div>
						<button type="submit" class="btn btn-action">Ricarica</button>
					</form>
				</div>

				<!-- Sezione Cerca Profilo -->
				<div id="searchSection" class="section-content">
					<form action="${pageContext.request.contextPath}/profilo/mostra/utente" method="post">
						<div class="form-group">
							<label for="searchUsername">Inserisci Username:</label>
							<input type="text" class="form-control" name="username" placeholder="Cerca profilo utente">
						</div>
						<button type="submit" class="btn btn-primary">Cerca Profilo</button>
					</form>
				</div>
			</div>
		</div>

		<!-- Pulsante Cancella Account in fondo alla pagina -->
		<button class="cancel-btn" onclick="confirmDeleteAccount()">Cancella Account</button>
	</div>

	<!-- Script -->
	<script>
		function toggleSection(sectionId) {
			const sections = document.querySelectorAll('.section-content');
			sections.forEach(section => {
				section.style.display = 'none';
			});
			document.getElementById(sectionId).style.display = 'block';
		}

		function confirmDeleteAccount() {
			if (confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')) {
				window.location.href = '${pageContext.request.contextPath}/profilo/elimina';
			}
		}
	</script>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
 --%>
