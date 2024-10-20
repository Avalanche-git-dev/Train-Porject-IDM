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
                <a class="nav-link" href="${pageContext.request.contextPath}/treni/catalogo">Catalogo</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/market">Market</a>
            </li>
        </ul>
        <form class="form-inline" action="${pageContext.request.contextPath}/user/logout"method="post">
            <button class="btn logout-btn my-2 my-sm-0" type="submit">Logout</button>
            <i class="fas fa-cog settings-icon" title="Impostazioni"></i>
        </form>
    </div>
</nav>

<style>
    .navbar {
        background-color: #f0f0f0; /* Grigio chiaro */
        padding: 15px;
        border-bottom: 1px solid #ddd;
    }

    .navbar-brand {
        color: #333; /* Grigio scuro */
        padding: 10px 20px;
        font-size: 18px;
        font-weight: bold;
        border-radius: 5px;
        cursor: pointer;
        transition: color 0.3s ease, background-color 0.3s ease;
    }

    .navbar-brand:hover {
        color: #0275d8; /* Blu per hover */
        background-color: #e0e0e0; /* Grigio chiaro per hover */
    }

    .navbar-nav .nav-item .nav-link {
        color: #444; /* Colore leggermente più scuro per differenziarlo da Dashboard */
        margin-right: 20px;
        font-size: 18px;
        transition: color 0.3s ease, background-color 0.3s ease;
    }

    .navbar-nav .nav-item .nav-link:hover {
        color: #0275d8; /* Blu per hover */
        background-color: #f0f0f0; /* Grigio chiaro per hover */
        border-radius: 5px;
    }

    .logout-btn {
        background-color: #777; /* Grigio più scuro per evidenziarlo */
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 20px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .logout-btn:hover {
        background-color: #0275d8; /* Blu per hover */
        color: #fff;
    }

    .settings-icon {
        font-size: 22px;
        color: #0275d8;
        margin-left: 10px;
        cursor: pointer;
        transition: color 0.3s ease;
    }

    .settings-icon:hover {
        color: #025aa5;
    }
</style>
