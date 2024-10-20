<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acquisto Completato</title>
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
            font-size: 36px;
            color: #333;
            margin-bottom: 20px;
        }

        .main p {
            font-size: 20px;
            margin-bottom: 30px;
        }

        .btn-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .cta-btn {
            background-color: #0275d8;
            color: white;
            padding: 10px 20px;
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
    </style>
</head>
<body>

    <div class="main">
        <h1>Acquisto Completato</h1>
        <p>Hai acquistato il treno con successo!</p>
        <div class="btn-container">
            <a href="${pageContext.request.contextPath}/treni" class="cta-btn">Torna ai Treni</a>
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
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

</body>
</html>
