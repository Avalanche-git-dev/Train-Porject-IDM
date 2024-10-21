 
 <%-- 
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                    <a href="${pageContext.request.contextPath}/treni/dettagli/${treno.idTreno}" class="btn btn-primary">Vedi Dettagli</a>
                    <a href="${pageContext.request.contextPath}/treni/visualizza/treno" class="btn btn-primary">Vedi Dettagli</a>
                    <!-- Pulsante "Valuta" -->
                    <a href="${pageContext.request.contextPath}/valutazioni/valutaTreno/${treno.idTreno}" class="btn btn-success ml-2">Valuta</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
  --%>
  
  
  
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tutti i Treni Disponibili</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Tutti i Treni Disponibili</h1>

        <!-- Form di Filtro per il Catalogo dei Treni -->
        <form action="${pageContext.request.contextPath}/catalogo/filtro" method="get" class="mb-4">
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="nome">Nome del Treno</label>
                    <input type="text" id="nome" name="nome" value="${trenoFilter.nome}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" value="${trenoFilter.marca}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="prezzoVendita">Prezzo di Vendita (massimo)</label>
                    <input type="number" id="prezzoVendita" name="prezzoVendita" value="${trenoFilter.prezzoVendita}" class="form-control"/>
                </div>
                <div class="form-group col-md-3">
                    <label for="valutazioneMassima">Media Valutazioni (massimo)</label>
                    <input type="number" id="valutazioneMassima" name="valutazioneMassima" value="${trenoFilter.valutazioni}" class="form-control"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Applica Filtro</button>
        </form>

        <!-- Lista dei Treni Disponibili -->
        <c:forEach var="treno" items="${treni}">
            <div class="card mb-3">
                <div class="card-body">
                    <h5 class="card-title">${treno.nome}</h5>
                    <p class="card-text">Marca: ${treno.marca}</p>
                    <p class="card-text">Media Valutazioni: ${treno.mediaValutazioni}</p>
                    <!-- Pulsante per Vedere i Dettagli del Treno -->
                    <a href="${pageContext.request.contextPath}/treni/dettagli/${treno.idTreno}" class="btn btn-primary">Vedi Dettagli</a>
                    <!-- Pulsante "Valuta" -->
                    <a href="${pageContext.request.contextPath}/valutazioni/valutaTreno/${treno.idTreno}" class="btn btn-success ml-2">Valuta</a>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
  