<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            background-color: #ffffff;
            color: #333; /* Grigio scuro */
        }

        /* Navbar styling */
        .navbar {
            background-color: #f0f0f0; /* Grigio chiaro */
            padding: 15px;
            border-bottom: 1px solid #ddd;
            display: flex;
            justify-content: space-between;
        }

        .navbar .nav-links {
            display: flex;
            align-items: center;
        }

        .navbar .nav-links a {
            text-decoration: none;
            color: #333; /* Grigio scuro */
            margin-right: 20px;
        }

        .navbar .register-btn {
            background-color: #0275d8; /* Blu */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .navbar .register-btn:hover {
            background-color: #025aa5; /* Hover Blu */
        }

        /* Main content */
        .main {
            text-align: center;
            padding: 80px 20px;
            background-color: #ffffff; /* Sfondo bianco */
        }

        .main h1 {
            font-size: 48px;
            color: #333; /* Grigio scuro */
        }

        .main p {
            font-size: 18px;
            color: #333; /* Grigio scuro */
            margin-bottom: 50px;
        }

        /* Buttons for "Crea" and "Login" */
        .main .cta-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .main .cta-btn {
            background-color: #0275d8; /* Blu */
            color: white;
            padding: 15px 30px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .main .cta-btn:hover {
            background-color: #025aa5; /* Hover Blu */
        }

        /* Footer */
        footer {
            background-color: #f0f0f0; /* Grigio chiaro */
            padding: 20px;
            text-align: center;
            border-top: 1px solid #ddd;
            color: #333; /* Grigio scuro */
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <div class="nav-links">
            <a href="#">Home</a>
            <a href="#">About</a>
            <a href="#">Contact</a>
        </div>
        <button class="register-btn" onclick="window.location.href='register.jsp'">Registrati</button>
    </div>

    <!-- Main content -->
    <div class="main">
        <h1>Welcome to Our Website</h1>
        <p>Explore our services and join us today.</p>
        <div class="cta-buttons">
            <button class="cta-btn" onclick="window.location.href='crea.jsp'">Crea</button>
            <button class="cta-btn" onclick="window.location.href='login.jsp'">Log In</button>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        &copy; 2024 YourWebsite. All rights reserved.
    </footer>

</body>
</html>
