<!-- dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
    <style>
    @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap'); 
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fafafa;
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


</body>
</html>
