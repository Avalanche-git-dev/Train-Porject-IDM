<!-- profilo.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
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
        .profile-image {
            position: absolute;
            top: -80px;
            left: 50%;
            transform: translateX(-50%);
            width: 120px;
            height: 120px;
            border-radius: 50%;
            border: 4px solid #f8f9fa;
            object-fit: cover;
        }
        .wallet-btn, .edit-btn {
            position: absolute;
            top: 0;
            right: 20px;
            background-color: #0275d8;
            color: white;
            padding: 8px 12px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .wallet-btn:hover, .edit-btn:hover {
            background-color: #025aa5;
        }
        .train-btn {
            display: block;
            margin: 40px auto;
            background-color: #0275d8;
            color: white;
            padding: 15px 30px;
            font-size: 18px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .train-btn:hover {
            background-color: #025aa5;
        }
        .wallet-content {
            display: none;
            margin-top: 10px;
        }
        .dashboard-btn {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="profile-container">
        <!-- Sezione Profilo Utente -->
        <div class="profile-section">
            <!-- Foto utente (se presente) -->
            <img src="/images/default-user.png" alt="Foto Utente" class="profile-image">

            <!-- Tasto Modifica Profilo -->
            <button class="edit-btn" onclick="window.location.href='${pageContext.request.contextPath}/profilo/modifica'">
                <i class="fas fa-cog"></i> Modifica Profilo
            </button>

            <!-- Informazioni utente -->
            <h5 class="text-center mt-5">Informazioni Utente</h5>
            <table class="table table-borderless">
                <tr><th>Nome:</th><td>${userInfo.nome}</td></tr>
                <tr><th>Cognome:</th><td>${userInfo.cognome}</td></tr>
                <tr><th>Email:</th><td>${userInfo.email}</td></tr>
                <tr><th>Telefono:</th><td>${userInfo.telefono}</td></tr>
                <tr><th>Username:</th><td>${userInfo.username}</td></tr>
                <tr><th>Portafoglio:</th><td>${userInfo.portafoglio} €</td></tr>
            </table>

            <!-- Tasto Portafoglio -->
            <button class="wallet-btn" onclick="toggleWallet()">Portafoglio</button>
            <div class="wallet-content" id="walletContent">
                <p>Saldo attuale: ${userInfo.portafoglio} €</p>
                <form action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi" method="post">
                    <div class="form-group">
                        <label for="importo">Aggiungi denaro:</label>
                        <input type="number" step="0.01" min="0" class="form-control" id="importo" name="importo" required>
                    </div>
                    <button type="submit" class="btn btn-success">Ricarica</button>
                </form>
            </div>

            <!-- Tasto Vedi i tuoi treni -->
            <button class="train-btn" onclick="window.location.href='${pageContext.request.contextPath}/treni/visualizza'">
                Vedi i tuoi treni
            </button>

            <!-- Tasto Dashboard -->
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary dashboard-btn">Torna alla Dashboard</a>
        </div>
    </div>
</div>

<!-- Script per gestire l'espansione del portafoglio -->
<script>
    function toggleWallet() {
        const walletContent = document.getElementById('walletContent');
        if (walletContent.style.display === 'none' || walletContent.style.display === '') {
            walletContent.style.display = 'block';
        } else {
            walletContent.style.display = 'none';
        }
    }
</script>

<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
