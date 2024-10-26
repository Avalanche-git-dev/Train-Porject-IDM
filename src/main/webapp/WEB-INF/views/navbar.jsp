<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/catalogo">Catalogo</a>
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
</style> --%>

 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-primary bg-primary text-white custom-navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/dashboard">
        <img src="${pageContext.request.contextPath}/resources/images/train-svgrepo-com.svg" alt="logo" id="logo" class="logo">
    </a>
    
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link nav-link-hover text-white" href="${pageContext.request.contextPath}/profilo">Profilo</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link nav-link-hover dropdown-toggle text-white" href="#" id="dropdown" role="button">Treni</a>
                <ul class="dropdown-menu" id="dropdown-content">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/treni/crea">Creazione</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/treni/visualizza">Gestione</a></li>
                </ul>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-hover text-white" href="${pageContext.request.contextPath}/catalogo">Catalogo</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-hover text-white" href="${pageContext.request.contextPath}/market">Market</a>
            </li>
        </ul>
         <form class="form-inline" action="${pageContext.request.contextPath}/user/logout" method="post">
            <button type="submit" class="logout-btn btn btn-secondary">Logout</button>
            <i class="fas fa-cog settings-icon" title="Impostazioni"></i>
        </form>
    </div>
</nav>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap'); 
    /* Arrotondamento ed effetto hover */
    .nav-link-hover {
        transition: background-color 0.3s ease, transform 0.3s ease;
        padding: 8px 20px;
        border-radius: 30px;
        color: #ffffff !important; /* Forza colore del testo in bianco */
    }

    .nav-link-hover:hover {
        background-color: #FF4500;
        transform: scale(1.05);
    }

    /* Custom navbar styles */
    .custom-navbar {
        padding: 8px 16px; /* Ridotta altezza della navbar */
    }

    /* Logo styling */
    .logo {
        height: 50px; /* Ridotta altezza dell’immagine logo */
        width: auto; /* Mantiene le proporzioni */
    }

     .logout-btn {
        padding: 10px 25px;
        border-radius: 30px !important; /* Arrotonda il pulsante */
        font-size: 16px;
        font-weight: 500;
        transition: background-color 0.3s ease, transform 0.2s ease;
        color: #333; /* Colore del testo */
        border: none; /* Rimuove i bordi */
    }

    .logout-btn:hover {
        background-color: #FF4500; /* Colore di hover per il pulsante */
        transform: scale(1.05); /* Effetto hover */
    }


    /* Dropdown menu styling */
    .dropdown-menu {
        display: none;
        position: absolute;
        background-color: #ffffff;
        border-radius: 15px;
        margin-top: 10px;
        box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    /* Visualizzazione dropdown attivo */
    .dropdown-menu.show {
        display: block;
    }

    /* Icon settings for settings cog */
    .settings-icon {
        color: #fff;
        margin-left: 15px;
        font-size: 18px;
        cursor: pointer;
    }
    
    
    
   /*  .fixed-logout {
        position: fixed;
        right: 20px;
        top: 10px;
        z-index: 1000;
    } */
</style>




<script>
    document.addEventListener("DOMContentLoaded", function () {
        const dropdownLink = document.getElementById("dropdown");
        const dropdownMenu = document.getElementById("dropdown-content");

        dropdownLink.addEventListener("click", function (event) {
            event.preventDefault();
            dropdownMenu.classList.toggle("show");
        });

        // Chiude il menu quando si clicca fuori dal dropdown
        document.addEventListener("click", function (event) {
            if (!dropdownLink.contains(event.target) && !dropdownMenu.contains(event.target)) {
                dropdownMenu.classList.remove("show");
            }
        });
    });
</script>
