<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');
        
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
            height: 100vh; /* Assicura che il body riempia tutta l'altezza della finestra */
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .navbar {
            width: 100%;
            display: flex;
            justify-content: center;
            padding: 15px 0;
            background-color: #f0f0f0;
            border-bottom: 1px solid #ddd;
            position: absolute;
            top: 0;
            left: 0;
        }

        .home-btn {
            background-color: #333;
            color: white;
            padding: 10px 25px;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            font-size: 18px;
            font-weight: 500;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .home-btn:hover {
            background-color: #555;
            transform: scale(1.05);
        }

        .register-container {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            width: 100%;
            height: 100%; /* Fa in modo che il container riempia tutto lo spazio disponibile */
        }

        .register-form {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
            width: 350px;
            display: flex;
            flex-direction: column;
            align-items: center; /* Centro all'interno del form */
            animation: fadeIn 1s ease-in-out;
        }

        .register-form h2 {
            text-align: center;
            color: #333;
            font-size: 28px;
            margin-bottom: 20px;
            font-weight: 700;
        }

        .register-form input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        .register-form .register-btn {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            border: none;
            border-radius: 5px;
            background-color: #0275d8;
            color: white;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
        }

        .register-form .register-btn:hover {
            background-color: #025aa5;
            transform: scale(1.05);
        }

        .register-form input:focus {
            border-color: #0275d8;
            box-shadow: 0 0 5px rgba(2, 117, 216, 0.3);
            outline: none;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }

    </style>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <a href="<c:url value='/' />" class="home-btn">Home</a>
    </div>

    <!-- Register form -->
    <div class="register-container">
        <form class="register-form" action="<c:url value= '/user/registrati' />" method="post">
            <h2>Registrazione</h2>
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="text" name="nome" placeholder="Nome" required>
            <input type="text" name="cognome" placeholder="Cognome" required>
            <input type="text" name="telefono" placeholder="Numero di Telefono" required>
            <input type="email" name="email" placeholder="Email" required>
            <button type="submit" class="register-btn">Registrati</button>
        </form>

        <!-- Display errors if any -->
        <c:if test="${not empty errorMessage}">
            <div class="error-message">${errorMessage}</div>
        </c:if>
    </div>

</body>
</html>
