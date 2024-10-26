
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">
<style>
 @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap'); 

body {
	font-family: 'Roboto', sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	flex-direction: column;
}

/* Navbar styling */
.navbar {
	width: 100%;
	display: flex;
	justify-content: center;
	padding: 15px 0;
	position: absolute;
	top: 0;
	left: 0;
}

.home-btn {
	padding: 10px 25px;
	border: none;
	border-radius: 30px;
	cursor: pointer;
	font-size: 18px;
	font-weight: 500;
	transition: background-color 0.3s ease, transform 0.2s ease;
}

.home-btn:hover {
	transform: scale(1.05);
}

/* Form container */
.login-container {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
	height: 100%;
	width: 100%;
}

.login-form {
	padding: 40px;
	border-radius: 15px;
	box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
	width: 350px;
	display: flex;
	flex-direction: column;
	align-items: center; /* Centra gli elementi del form */
	animation: fadeIn 1s ease-in-out;
}

.login-form h2 {
	text-align: center;
	font-size: 28px;
	margin-bottom: 20px;
	font-weight: 700;
}

.login-form input {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border-radius: 5px;
	font-size: 16px;
}

.login-form .login-btn {
	width: 100%;
	padding: 12px;
	margin-top: 20px;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	font-weight: 500;
	cursor: pointer;
	transition: background-color 0.3s ease, transform 0.2s ease;
}

.login-form .login-btn:hover {
	transform: scale(1.05);
}

/* Animation for input focus */
.login-form input:focus {
	box-shadow: 0 0 5px rgba(2, 117, 216, 0.3);
	outline: none;
}

#logo{
	width: 60px;
}

@keyframes fadeIn {from { opacity:0;
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


<%-- <div class="navbar navbar-brand navbar-dark bg-primary py-1">
    <!-- Logo immagine -->
    <a href="${pageContext.request.contextPath}/" id="home-button">
        <img src="${pageContext.request.contextPath}/resources/images/icon.jpg" alt="logo" id="logo"/>
    </a>
</div> --%>



	<!-- Navbar -->
	<div class="navbar navbar-brand navbar-dark bg-primary py-1">
		<a href="${pageContext.request.contextPath}/" >
			<img src="${pageContext.request.contextPath}/resources/images/train-svgrepo-com.svg" alt="logo" id="logo">
		</a>
	</div>

	<!-- Login form -->
	<div class="login-container">
		<form class="login-form"
			action="${pageContext.request.contextPath}/user/login" method="post">
			<h2>Log In</h2>
			<input type="text" name="username" placeholder="Username" required>
			<input type="password" name="password" placeholder="Password"
				required>
			<button type="submit" class="login-btn btn btn-secondary">Log In</button>
		</form>

		<!-- Display errors if any -->
		<c:if test="${not empty errorMessage}">
			<div class="text-danger">${errorMessage}</div>
		</c:if>
		
		<c:if test="${not empty logoutMessage}">
    <div class="text-success mt-3">${logoutMessage}</div>
    </c:if>
	<%
    String sessioneScaduta = request.getParameter("sessioneScaduta");
    if ("true".equals(sessioneScaduta)) {
%>
    <div class="text-danger mt-3">La tua sessione è scaduta. Per favore, accedi di nuovo.</div>
<%
    }
%>
	</div>
	


</body>
</html>
 
 
 
 