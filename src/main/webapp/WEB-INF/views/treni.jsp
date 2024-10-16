<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Treni</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

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

        /* Navbar styling */
        .navbar {
            background-color: #f0f0f0; /* Grigio chiaro */
            padding: 15px;
            border-bottom: 1px solid #ddd;
        }

        .navbar-brand {
            color: #333; /* Grigio scuro */
            padding: 10px 20px;
            font-size: 18px;
            font-weight: bold; /* Più spesso rispetto agli altri */
            border-radius: 5px;
            cursor: pointer;
            transition: color 0.3s ease;
        }

        .navbar-brand:hover {
            color: #0275d8; /* Blu per hover */
        }

        .navbar-nav .nav-item .nav-link {
            color: #333; /* Grigio scuro */
            margin-right: 20px;
            font-size: 18px;
        }

        .navbar-nav .nav-item .nav-link:hover {
            color: #0275d8; /* Blu per hover */
        }

        .logout-btn {
            background-color: #f0f0f0; /* Grigio chiaro */
            color: #333; /* Grigio scuro */
            padding: 10px 20px;
            border: 2px solid #ddd; /* Bordo */
            border-radius: 20px; /* Arrotondato */
            cursor: pointer;
            font-size: 16px;
        }

        .logout-btn:hover {
            background-color: #e0e0e0; /* Hover Grigio leggermente più scuro */
        }

        .settings-icon {
            font-size: 22px;
            color: #0275d8;
            margin-left: 10px;
            cursor: pointer;
        }

        /* Main content */
        .main {
            text-align: center;
            padding: 80px 20px;
            background-color: #ffffff; /* Sfondo bianco */
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .main h1 {
            font-size: 48px;
            color: #333; /* Grigio scuro */
            margin-bottom: 30px;
        }

        .main .btn-container {
            display: flex;
            justify-content: center;
            gap: 30px;
        }

        .cta-btn {
            background-color: #0275d8; /* Blu */
            color: white;
            padding: 15px 30px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 250px;
        }

        .cta-btn:hover {
            background-color: #025aa5; /* Hover Blu */
        }

        footer {
            background-color: #f0f0f0; /* Grigio chiaro */
            padding: 20px;
            text-align: center;
            border-top: 1px solid #ddd;
            color: #333; /* Grigio scuro */
            width: 100%;
        }

        footer p {
            margin: 0;
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/profilo">Profilo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/treni">Treni</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/market">Market</a>
                </li>
            </ul>
            <form class="form-inline" action="${pageContext.request.contextPath}/logout" method="post">
                <button class="btn logout-btn my-2 my-sm-0" type="submit">Logout</button>
                <i class="fas fa-cog settings-icon" title="Impostazioni"></i>
            </form>
        </div>
    </nav>

    <!-- Main content -->
    <div class="main">
        <h1>Gestione Treni</h1>
        <div class="btn-container">
            <!-- Pulsante "Crea un nuovo treno" mappato al metodo crea -->
            <button class="cta-btn" onclick="window.location.href='${pageContext.request.contextPath}/treni/crea'">Crea un nuovo treno</button>
            <!-- Pulsante "Visualizza treni" mappato alla pagina di visualizzazione treni -->
            <button class="cta-btn" onclick="window.location.href='${pageContext.request.contextPath}/visualizzaTreni'">Visualizza treni</button>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2024 YourWebsite. All rights reserved.</p>
    </footer>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Font Awesome for settings icon -->
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

    <script>
        document.querySelector('.navbar-brand').addEventListener('click', function() {
            document.querySelector('.navbar-collapse').classList.add('d-none'); /* Nasconde la navbar al click su Dashboard */
            window.location.href = '${pageContext.request.contextPath}/dashboard'; /* Reindirizza alla dashboard */
        });
    </script>
</body>
</html>
