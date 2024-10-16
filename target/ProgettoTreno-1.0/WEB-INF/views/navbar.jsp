<!-- navbar.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profilo">Profilo</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/treni">Treni</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/market">Market</a>
            </li>
        </ul>
        <form class="form-inline" action="${pageContext.request.contextPath}/logout" method="post">
            <button class="btn logout-btn my-2 my-sm-0" type="submit">Logout</button>
            <i class="fas fa-cog settings-icon" title="Impostazioni"></i>
        </form>
    </div>
</nav>

<style>
    .navbar {
        position: sticky;
        top: 0;
        z-index: 1000;
        background-color: #f0f0f0; /* Grigio chiaro */
        padding: 15px;
        border-bottom: 1px solid #ddd;
    }
    .navbar-brand, .nav-link {
        color: #333; /* Grigio scuro */
        transition: color 0.3s ease;
    }
    .navbar-brand:hover, .nav-link:hover {
        color: #0275d8; /* Blu */
    }
    .logout-btn {
        background-color: #333;
        color: white;
        border: none;
        border-radius: 20px;
        cursor: pointer;
    }
    .logout-btn:hover {
        background-color: #555;
    }
    .settings-icon {
        font-size: 22px;
        color: #0275d8;
        margin-left: 10px;
        cursor: pointer;
    }
</style>
