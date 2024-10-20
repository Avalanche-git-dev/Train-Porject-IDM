

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
				<c:if test="${not empty error}">
					<div class="error-message" role="alert">${error}</div>
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

