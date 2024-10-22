<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        td.azioni, th.azioni {
            width: 150px; /* Imposta una larghezza massima */
            white-space: nowrap; /* Evita che il testo vada a capo */
            overflow: hidden; /* Nascondi il testo che eccede */
            text-overflow: ellipsis; /* Aggiunge "..." se il testo è troppo lungo */
        }
        .btn {
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            background-color: #28a745;
            border-radius: 5px;
        }
        .btn-block {
            background-color: #dc3545;
        }
        .btn-activate {
            background-color: #007bff;
        }
        footer {
            text-align: center;
            padding: 10px 0;
            background-color: #f1f1f1;
            margin-top: 20px;
        }
    </style>
    <script>
        function bloccaUtente(userId, button, row) {
            fetch(`${pageContext.request.contextPath}/admin/bloccaUtente`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'userId=' + encodeURIComponent(userId)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(userId + ' Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                alert(data);
                // Cambia il pulsante in Sblocca
                button.textContent = 'Sblocca';
                button.classList.remove('btn-block'); // Rimuovi classe di blocco
                button.classList.add('btn-activate'); // Aggiungi classe di attivazione
                button.setAttribute('onclick', `sbloccaUtente(${userId}, this, row)`); // Cambia l'evento onclick

                // Cambia lo stato nella tabella
                row.cells[3].innerText = 'Locked'; // Aggiorna lo stato in Locked
                location.reload();
            })
            .catch(error => {
                alert('Si è verificato un errore: ' + error.message);
            });
        }

        function sbloccaUtente(userId, button, row) {
            fetch(`${pageContext.request.contextPath}/admin/sbloccaUtente`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'userId=' + encodeURIComponent(userId)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(userId + ' Network response was not ok');
                }
                return response.text();
            })
            .then(data => {
                alert(data);
                // Cambia il pulsante in Blocca
                button.textContent = 'Blocca';
                button.classList.remove('btn-activate'); // Rimuovi classe di attivazione
                button.classList.add('btn-block'); // Aggiungi classe di blocco
                button.setAttribute('onclick', `bloccaUtente(${userId}, this, row)`); // Cambia l'evento onclick

                // Cambia lo stato nella tabella
                row.cells[3].innerText = 'Unlocked'; // Aggiorna lo stato in Unlocked
                location.reload();
            })
            .catch(error => {
                alert('Si è verificato un errore: ' + error.message);
            });
        }
    </script>
</head>
<body>

    <div class="container">
        <h1 style="text-align: center;">Benvenuto Admin!</h1>
        <p style="text-align: center;">Controlla e gestisci le attività degli utenti registrati.</p>

        <c:if test="${not empty param.message}">
            <div style="color: green; text-align: center;">${param.message}</div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID Utente</th>
                    <th>Nome Utente</th>
                    <th>Email</th>
                    <th>Stato</th>
                    <th class="azioni">Azioni</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listaUtenti}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.stato == 'locked'}">
                                Locked
                            </c:when>
                            <c:otherwise>
                                Unlocked
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="azioni">
                        <c:choose>
                            <c:when test="${user.stato == 'locked'}">
                                <button class="btn btn-activate" onclick="sbloccaUtente(${user.userId}, this, this.parentNode.parentNode)">Sblocca</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-block" onclick="bloccaUtente(${user.userId}, this, this.parentNode.parentNode)">Blocca</button>
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:void(0);" class="btn" style="background-color: #28a745; margin-left: 5px;" onclick="openGestisciAttivitaModal(${user.userId})">Vai all'utente</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    
    <footer>
        <p>&copy; 2024 YourWebsite. All rights reserved.</p>
    </footer>

</body>
</html>
