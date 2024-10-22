<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifica Profilo</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h1>Modifica Profilo</h1>

        <!-- Form di modifica del profilo -->
        <form action="${pageContext.request.contextPath}/profilo/modifica" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${userInfo.email}">
            </div>

            <div class="form-group">
                <label for="telefono">Telefono</label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${userInfo.telefono}">
            </div>

            <div class="form-group">
                <label for="passwordVecchia">Password Vecchia</label>
                <input type="password" class="form-control" id="passwordVecchia" name="passwordVecchia">
            </div>

            <div class="form-group">
                <label for="passwordNuova">Password Nuova</label>
                <input type="password" class="form-control" id="passwordNuova" name="passwordNuova">
            </div>

            <button type="submit" class="btn btn-primary">Salva Modifiche</button>
        </form>
    </div>
</body>
</html>
