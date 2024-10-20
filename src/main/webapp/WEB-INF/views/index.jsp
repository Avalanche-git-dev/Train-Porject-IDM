<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Benvenuto su ProgettoTreno</title>
   <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
    <header>
        <div style="text-align: right; padding: 10px;">
            <!-- Inserisci qui il percorso del controller per la registrazione -->
            <a href="/ProgettoTreno/user/registrati">
                <button>Registrati</button>
            </a>
            <!-- Inserisci qui il percorso del controller per il login -->
            <a href="/ProgettoTreno/user/login">
                <button>Login</button>
            </a>
        </div>
    </header>

    <main>
        <section style="text-align: center; margin-top: 20%;">
            <h1>Benvenuto su ProgettoTreno!</h1>
            <p>Gestisci i tuoi treni, acquista e vendi vagoni, e interagisci con altri appassionati di treni.</p>
        </section>
    </main>

    <footer>
        <p style="text-align: center;">All rights reserved - ProgettoTreno</p>
    </footer>
</body>
</html>
