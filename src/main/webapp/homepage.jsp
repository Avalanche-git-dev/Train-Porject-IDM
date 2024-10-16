<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* Barra di navigazione */
        .navbar {
            display: flex;
            justify-content: space-between;
            background-color: #333;
            padding: 10px 20px;
            color: white;
            position: relative;
        }

        .navbar .menu {
            cursor: pointer;
            font-size: 18px;
            color: white;
        }

        /* Dropdown del menu nascosto */
        .menu-items {
            display: none;
            position: absolute;
            top: 40px;
            left: 20px;
            background-color: #333;
            padding: 10px;
            border-radius: 4px;
        }

        .menu-items li {
            list-style-type: none;
            margin: 5px 0;
        }

        .menu-items li a {
            color: white;
            text-decoration: none;
        }

        .menu-items li a:hover {
            text-decoration: underline;
        }

        .navbar .auth-links {
            display: flex;
        }

        .navbar .auth-links a {
            margin-left: 15px;
            color: white;
            text-decoration: none;
        }

        .navbar .auth-links a:hover {
            text-decoration: underline;
        }

        /* Container principale */
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            color: #333;
        }

        input, select {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 15px;
        }

        .success-message {
            color: green;
            text-align: center;
            margin-top: 15px;
        }

        .legend {
            margin-top: 20px;
            font-size: 14px;
            color: #666;
            text-align: center;
        }
    </style>

    <script>
        function toggleMenu() {
            var menuItems = document.getElementById("menu-items");
            if (menuItems.style.display === "block") {
                menuItems.style.display = "none";
            } else {
                menuItems.style.display = "block";
            }
        }
    </script>
</head>
<body>

    <!-- Barra di navigazione -->
    <div class="navbar">
        <div class="menu" onclick="toggleMenu()">Menu</div>

        <!-- Menu nascosto -->
        <ul id="menu-items" class="menu-items">
            <li><a href="#">I tuoi treni</a></li>
            <li><a href="/ProgettoTreno/marketplace">Marketplace</a></li>
        </ul>

        <div class="auth-links">
            <a href="/ProgettoTreno-1.0/login.jsp">Login</a>
            <a href="/ProgettoTreno/register">Registrati</a>
        </div>
    </div>

    <div class="container">
        <!-- Benvenuto centrato -->
        <h1>Benvenuto!</h1>

        <h2>Crea qui il tuo Treno</h2>

        <!-- Form per la creazione del treno -->
        <form action="homepage" method="post">
            <label for="sigla">Sigla del Treno:</label>
            <input type="text" id="sigla" name="sigla" value="${trenoDto.sigla}" required>

            <label for="marca">Marca:</label>
            <select id="marca" name="marca" required>
                <option value="">Seleziona una Marca</option>
                <option value="Francese" ${trenoDto.marca == 'Francese' ? 'selected' : ''}>Francese</option>
                <option value="Italiano" ${trenoDto.marca == 'Italiano' ? 'selected' : ''}>Italiano</option>
                <option value="Tedesco" ${trenoDto.marca == 'Tedesco' ? 'selected' : ''}>Tedesco</option>
            </select>

            <input type="submit" value="Crea Treno">
        </form>

        <!-- Mostra eventuali messaggi di successo o errore -->
        <c:if test="${not empty message}">
            <div class="success-message">${message}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <!-- Leggenda -->
        <div class="legend">
            Leggenda: H (Motrice), C (Cargo), P (Vagone passeggeri), R (Vagone ristorante)
        </div>
    </div>
</body>
</html>
