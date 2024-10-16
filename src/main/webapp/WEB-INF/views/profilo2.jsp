<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profilo Utente</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .profile-section {
            margin-bottom: 40px;
            padding: 20px;
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .train-list {
            margin-top: 30px;
        }

        .train-item {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 15px;
            background-color: #fff;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .train-item img {
            width: 80px;
            height: auto;
            margin-right: 20px;
        }

        .details-btn {
            background-color: #0275d8;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .details-btn:hover {
            background-color: #025aa5;
        }

        .no-trains {
            margin-top: 20px;
            text-align: center;
            color: #6c757d;
        }
    </style>
</head>
<body>

    <div class="container mt-5">
        <div class="row">
            <!-- Sezione Profilo Utente -->
            <div class="col-md-12 profile-section">
                <h5>Informazioni Utente</h5>
                <table class="table table-borderless">
                    <tr><th>Nome:</th><td>${userInfo.nome}</td></tr>
                    <tr><th>Cognome:</th><td>${userInfo.cognome}</td></tr>
                    <tr><th>Email:</th><td>${userInfo.email}</td></tr>
                    <tr><th>Telefono:</th><td>${userInfo.telefono}</td></tr>
                    <tr><th>Username:</th><td>${userInfo.username}</td></tr>
                    <tr><th>Portafoglio:</th><td>${userInfo.portafoglio}</td></tr>
                </table>
                <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary">Torna alla Dashboard</a>
            </div>
        </div>

        <!-- Controllo se la lista dei treni è vuota -->
        <c:if test="${empty trainList}">
            <div class="no-trains">
                <p>Non ci sono treni da visualizzare.</p>
            </div>
        </c:if>
        <!-- Lista dei Treni -->
        
        
        
        



<%--         <!-- Lista dei Treni -->
        <div class="train-list row">
            <c:forEach var="treno" items="${trainList}">
                <div class="col-md-4">
                    <div class="train-item">
                        <img src="<c:out value='${treno.imageUrl != null ? treno.imageUrl : "/images/default-train.png"}'/>" alt="Immagine Treno">
                        <div>
                            <h5>${treno.nome}</h5>
                            <p>Media Valutazioni: 
                                <c:choose>
                                    <c:when test="${not empty treno.valutazioni}">
                                        <c:out value="${treno.mediaValutazioni}" /> / 5
                                    </c:when>
                                    <c:otherwise>
                                        Non valutato
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                        <button class="details-btn" 
                                onclick="window.location.href='${pageContext.request.contextPath}/treni/dettagli/${treno.id}'">
                            Dettagli
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>
         --%>
         <!-- Lista statica dei Treni -->
<div class="train-list row">
    <div class="col-md-4">
        <div class="train-item">
            <img src="/images/default-train.png" alt="Immagine Treno">
            <div>
                <h5>Treno 1</h5>
                <p>Media Valutazioni: 4.5 / 5</p>
            </div>
            <button class="details-btn" 
                    onclick="window.location.href='${pageContext.request.contextPath}/treni/dettagli/1'">
                Dettagli
            </button>
        </div>
    </div>

    <div class="col-md-4">
        <div class="train-item">
            <img src="/images/default-train.png" alt="Immagine Treno">
            <div>
                <h5>Treno 2</h5>
                <p>Media Valutazioni: Non valutato</p>
            </div>
            <button class="details-btn" 
                    onclick="window.location.href='${pageContext.request.contextPath}/treni/dettagli/2'">
                Dettagli
            </button>
        </div>
    </div>

    <div class="col-md-4">
        <div class="train-item">
            <img src="/images/default-train.png" alt="Immagine Treno">
            <div>
                <h5>Treno 3</h5>
                <p>Media Valutazioni: 3.8 / 5</p>
            </div>
            <button class="details-btn" 
                    onclick="window.location.href='${pageContext.request.contextPath}/treni/dettagli/3'">
                Dettagli
            </button>
        </div>
    </div>
</div>


<!--  treni statitici di prova  -->
<div>
<script>

treni.forEach(t => {
    t.getImmagine();
    t.getMediaValutazioni();
});



</script>




</div>

         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         
    </div>

 
 
 
 
 
 
 
</body>
</html>
