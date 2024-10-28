
 
 
 <!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Benvenuto su ProgettoTreno</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');
        
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
            display: flex;
            flex-direction: column;
            height: 100vh;
        }

        /* Navbar styling */
        .navbar {
            width: 100%;
            display: flex;
            justify-content: space-between;
            padding: 10px 20px; /* Ridotto il padding */
            background-color: #f0f0f0;
            border-bottom: 1px solid #ddd;
            position: absolute;
            top: 0;
            left: 0;
            box-sizing: border-box; /* Assicura che il padding non aggiunga ulteriore spazio */
        }

        .navbar .left-buttons,
        .navbar .right-buttons {
            display: flex;
            align-items: center;
        }

        .right-buttons {
            margin-left: auto; /* Allinea a sinistra */
        }

        .navbar button {
            background-color: #333;
            color: white;
            padding: 10px 25px; /* Aumentato leggermente il padding */
            border: none;
            border-radius: 30px;
            cursor: pointer;
            font-size: 18px; /* Aumentato leggermente il font */
            font-weight: 500;
            margin: 0 5px;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        /* Stile pulsanti "Login" e "Registrati" */
        .navbar a button {
            background-color: #333;
            padding: 10px 22px; /* Aumentato leggermente il padding */
            font-size: 16px; /* Aumentato leggermente il font */
        }

        .navbar a button:hover {
            background-color: #555;
            transform: scale(1.05);
        }

        /* Main content */
        main {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 20px;
        }

        main h1 {
            font-size: 36px;
            color: #333;
            margin-bottom: 20px;
        }

        main p {
            font-size: 18px;
            color: #333;
            margin-bottom: 30px;
        }

        /* Crea button */
        .create-btn {
            background-color: #0275d8;
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            font-size: 18px;
            font-weight: 500;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .create-btn:hover {
            background-color: #025aa5;
            transform: scale(1.05);
        }

        /* Footer */
        footer {
            background-color: #f9f9f9;
            text-align: center;
            padding: 15px 0; /* Aumentato leggermente il padding */
            border-top: 1px solid #ddd;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        footer p {
            color: #333;
            margin: 0;
            font-size: 16px; /* Aumentato leggermente il font */
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
       <form action="${pageContext.request.contextPath}/catalogo" method="get">
    <button type="submit">Vai al Catalogo</button>
</form>


        <div class="right-buttons">
            <!-- Pulsanti "Registrati" e "Login" a destra con stile modificato -->
            <a href="/ProgettoTreno/user/registrati">
                <button>Registrati</button>
            </a>
            <a href="/ProgettoTreno/user/login">
                <button>Login</button>
            </a>
        </div>
    </div>

    <!-- Main content -->
    <main>
        <h1>Benvenuto su ProgettoTreno!</h1>
        <p>Gestisci i tuoi treni, acquista e vendi vagoni, e interagisci con altri appassionati di treni.</p>

        <!-- Tasto "Crea" sotto il messaggio di benvenuto -->
        <form action="${pageContext.request.contextPath}/treni/crea/guest" method="get">
    <button class="create-btn" type="submit">Crea</button>
</form>

    </main>

    <!-- Footer -->
    <footer>
        <p>All rights reserved - ProgettoTreno</p>
    </footer>

</body>
</html>
 