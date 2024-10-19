<!-- dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbarhome.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ffffff;
            color: #333;
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
        footer {
            background-color: #f0f0f0;
            padding: 20px;
            text-align: center;
            border-top: 1px solid #ddd;
            color: #333;
            width: 100%;
        }
    </style>
</head>
<body>

<!-- Main content -->
<div class="main">
    <h1>Benvenuto, ${utente.username}</h1>
    <p>Questa è la tua dashboard personale.</p>
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
