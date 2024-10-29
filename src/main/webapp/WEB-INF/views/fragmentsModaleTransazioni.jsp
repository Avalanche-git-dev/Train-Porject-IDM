<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<table class="table table-striped">
    <thead>
        <tr>
            <th>ID Transazione</th>
            <th>Acquirente</th>
            <th>Venditore</th>
            <th>Importo</th>
            <th>Data</th>
            <th>Gestione</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="transazione" items="${transazioniUtente}">
            <tr>
                <td>${transazione.idTransazione}</td>
                <td>${transazione.acquirenteUsername}</td>
                <td>${transazione.venditoreUsername}</td>
                <td>${transazione.importo}</td>
                <td>${transazione.data}</td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/admin/annulla">
                        <input type="hidden" name="idTransazione" value="${transazione.idTransazione}">
                        <button type="submit" class="btn btn-secondary btn-sm">Annulla</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


