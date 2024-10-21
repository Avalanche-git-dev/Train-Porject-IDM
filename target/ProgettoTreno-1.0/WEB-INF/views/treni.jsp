<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Treni</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #ffffff;
	color: #333; /* Grigio scuro */
	min-height: 100vh;
	display: flex;
	flex-direction: column;
}

.main {
	text-align: center;
	padding: 80px 20px;
	background-color: #ffffff;
	flex: 1;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.main h1 {
	font-size: 48px;
	color: #333;
	margin-bottom: 30px;
}

.main .btn-container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 30px;
}

.cta-btn {
	background-color: #0275d8;
	color: white;
	padding: 20px 40px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 18px;
	width: 300px;
}

.cta-btn:hover {
	background-color: #025aa5;
}

footer {
	background-color: #f0f0f0;
	padding: 20px;
	text-align: center;
	border-top: 1px solid #ddd;
	color: #333;
	width: 100%;
}

footer p {
	margin: 0;
}

.spacer {
	margin-top: 50px;
}
</style>
</head>
<body>

	<!-- Main content -->
	<div class="main">
		<h1>Gestione Treni</h1>
		<div class="btn-container">
			<!-- Pulsante "Crea un nuovo treno" -->
			<button class="cta-btn"
				onclick="window.location.href='${pageContext.request.contextPath}/treni/crea'">Crea
				un nuovo treno</button>
			<!-- Pulsante "Visualizza i tuoi treni" -->
			<button class="cta-btn"
				onclick="window.location.href='${pageContext.request.contextPath}/treni/visualizza'">Visualizza
				i tuoi treni</button>
			<!-- Pulsante "Vedi i treni di tutti gli utenti" -->
			<div class="spacer"></div>
			<button class="cta-btn"
				onclick="window.location.href='${pageContext.request.contextPath}/catalogo'">Vedi
				 Catalogo </button>
		</div>
	</div>

	<!-- Footer -->
	<footer>
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
