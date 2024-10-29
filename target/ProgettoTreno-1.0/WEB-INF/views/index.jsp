  
  
  
  
  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TrenoLab</title>

 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">




 <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');
        
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            height: 100vh;
        	background-color: #f8f8ff; 
        }

        /* Navbar styling */
        .navbar {
            width: 100%;
            display: flex;
            justify-content: space-between;
            padding: 10px 20px; /* Ridotto il padding */
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
            padding: 10px 22px; /* Aumentato leggermente il padding */
            font-size: 16px; /* Aumentato leggermente il font */
        }

        .navbar a button:hover {
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
            margin-bottom: 20px;
        }

        main p {
            font-size: 18px;
            margin-bottom: 30px;
        }

        /* Crea button */
        .create-btn {
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
            transform: scale(1.05);
        }

        #BG {
            filter: blur(2px);
            background-size: cover;
        }

        #wtext{
            position: absolute;
            text-align: center;
            top: 25%;
            

        }

        /* Footer */
        footer {
            text-align: center;
            padding: 15px 0; /* Aumentato leggermente il padding */
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        footer p {
            margin: 0;
            font-size: 16px; /* Aumentato leggermente il font */
        }
    </style>
</head>
<body>

<body>

    <!-- Navbar -->
    <div class="navbar bg-primary navbar-primary">
        <div class="btn-btn-secondary">
            <!-- Tasto "Catalogo" in alto a sinistra -->
            <a href="${pageContext.request.contextPath}/catalogo">
            <button type="button" class="btn btn-secondary">Catalogo</button>
            </a>
        </div>
        

        <div class="right-buttons" >
            <!-- Pulsanti "Registrati" e "Login" a destra con stile modificato -->
            <a href="/ProgettoTreno/user/registrati">
                <button type="button" class="btn btn-secondary">Registrati</button>
            </a>
            <a href="/ProgettoTreno/user/login">
                <button type="button" class="btn btn-secondary">Login</button>
            </a>
        </div>
    </div>

    <!-- Main content -->
    <main>
        <div class="card text-white shadow-lg mt-4">
            <img    id="BG"
                    class="card-img-top"
                    src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ca9fc89d-1413-4a81-a39b-aef1ed5af25c/d716d8d-b4a90a39-6f33-4602-8771-d0ee7f230af7.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2NhOWZjODlkLTE0MTMtNGE4MS1hMzliLWFlZjFlZDVhZjI1Y1wvZDcxNmQ4ZC1iNGE5MGEzOS02ZjMzLTQ2MDItODc3MS1kMGVlN2YyMzBhZjcuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.HQA4di0Qt0s1nNGk_DBaTnm8aagBmEoPng1A5RtKzeE" 
                    alt="background">
            <div class="card-img-overlay" id="wtext">
            <div class="d-flex justify-content-center" >
                <h1 class="pr-2">Benvenuto su</h1><h1 class="text-warning">Treno Lab!</h1>
            </div>
            <p>Gestisci i tuoi treni, acquista e vendi vagoni, e interagisci con altri appassionati di treni.</p>

            <!-- Tasto "Crea" sotto il messaggio di benvenuto -->
             <form action="${pageContext.request.contextPath}/treni/crea/guest" method="get">
            <button class="btn btn-secondary create-btn">Crea</button>
            </form>
            </div>
        </div>
    </main>

    <!-- Footer -->
    <footer>
        <p>All rights reserved - Treno Lab</p>
    </footer>



</body>
</html>