<%-- <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crea Nuovo Treno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

    <div class="container mt-5">
        <h2>Crea un nuovo treno</h2>
        <form action="${pageContext.request.contextPath}/treni/crea" method="post">
            <!-- Campo input per la stringa del treno -->
            <div class="form-group">
                <label for="input">Input Treno</label>
                <input type="text" class="form-control" id="input" name="input" required>
            </div>

            <!-- Campo per la selezione della marca -->
            <div class="form-group">
                <label for="marca">Marca</label>
                <select class="form-control" id="marca" name="marca" required>
                    <option value="Italiano">Italiano</option>
                    <option value="Francese">Francese</option>
                    <option value="Tedesco">Tedesco</option>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Crea Treno</button>
        </form>
    </div>

</body>
</html>
 --%>