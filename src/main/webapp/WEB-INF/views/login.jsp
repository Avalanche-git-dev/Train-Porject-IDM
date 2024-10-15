<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');
        
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333; /* Grigio scuro */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Navbar styling */
        .navbar {
            background-color: #f0f0f0; /* Grigio chiaro */
            padding: 15px;
            border-bottom: 1px solid #ddd;
            width: 100%;
            position: fixed;
            top: 0;
            display: flex;
            justify-content: flex-start;
        }

        .navbar a {
            text-decoration: none;
            color: #333; /* Grigio scuro */
            margin-right: 20px;
            padding: 10px;
            font-weight: 500;
            font-size: 18px;
        }

        .navbar .home-btn {
            background-color: #333; /* Grigio scuro */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .navbar .home-btn:hover {
            background-color: #555; /* Hover Grigio scuro */
        }

        /* Form container */
        .login-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            width: 100%;
            flex-direction: column;
        }

        .login-form {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            width: 350px;
        }

        .login-form h2 {
            text-align: center;
            color: #333; /* Grigio scuro */
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: 700;
        }

        .login-form input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
        }

        .login-form .login-btn {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            border: none;
            border-radius: 5px;
            background-color: #0275d8; /* Blu */
            color: white;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-form .login-btn:hover {
            background-color: #025aa5; /* Hover Blu */
        }

        /* Animation for input focus */
        .login-form input:focus {
            border-color: #0275d8;
            box-shadow: 0 0 5px rgba(2, 117, 216, 0.3);
            outline: none;
        }

        /* Add some subtle animation to make it more appealing */
        .login-form {
            animation: fadeIn 1s ease-in-out;
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

    </style>
</head>
<body>

    <!-- Navbar -->
    <div class="navbar">
        <a href="index" class="home-btn">Home</a>
    </div>

    <!-- Login form -->
    <div class="login-container">
        <form class="login-form" action="${pageContext.request.contextPath}/login" method="post">
            <h2>Log In</h2>
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <button type="submit" class="login-btn">Log In</button>
        </form>

        <!-- Display errors if any -->
        <c:if test="${not empty errorMessage}">
            <div style="color:red; text-align: center; margin-top: 10px;">${errorMessage}</div>
        </c:if>
    </div>

</body>
</html>

