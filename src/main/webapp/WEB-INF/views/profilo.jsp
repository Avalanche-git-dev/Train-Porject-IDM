<%-- 




 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo Utente</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">

<style>
    body {
        font-family: 'Roboto', sans-serif; 
    }

    .error-message {
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        text-align: center;
        font-weight: bold;
    }

    .success-message {
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        text-align: center;
        font-weight: bold;
    }

    .profile-container {
        position: relative;
        left: 0; /
        * 
        justify-content: flex-start; /* Allinea a sinistra */
        display: flex;
        flex-direction: column;
        margin-top: 35px;
        padding-left: 0; /* Rimuove eventuali margini a sinistra */
        margin-left: -350px;  /* Margine a sinistra */
        width: 100%;
    }

    .profile-section {
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        width: 35%;
       /* margin-right: 20px; /* Distanza tra le sezioni */
        margin-bottom: 20px;
    }

    .sidebar-section {
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        width: 30%;
        position: relative;
    }

    .btn-action {
        padding: 10px 20px;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        width: 100%;
        margin-bottom: 15px;
    }

    .cancel-btn {
        padding: 10px 20px;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        position: fixed;
        bottom: 20px;
        left: 20px;
    }

    .section-content {
        display: none;
        padding: 20px;
        border-radius: 10px;
        position: absolute;
        left: 100%;
        top: 0;
        width: 300px;
        z-index: 1000;
    }

    .button-group {
        display: flex;
        flex-direction: column;
        margin-top: 30px;
        width: 100%;
    }
    


</style>
</head>
<body class="bg-light">

    <div class="container">
        <div class="profile-container">
            <!-- Profilo utente -->
            <div class="profile-section bg-primary">
                <h5 class="text-center mt-5 text-white">Informazioni Utente</h5>
                <table class="table table-borderless">
                    <tr>
                        <th class="text-white">Nome:</th>
                        <td class="text-secondary">${userInfo.nome}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Cognome:</th>
                        <td class="text-secondary">${userInfo.cognome}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Username:</th>
                        <td class="text-secondary">${userInfo.username}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Portafoglio:</th>
                        <td class="text-secondary mr-1">${userInfo.portafoglio} €</td>
                    </tr>
                </table>
            </div>

            <!-- Sidebar per azioni -->
            <div class="sidebar-section bg-primary">
                <!-- Messaggi di errore e successo -->
                <c:if test="${not empty errorMessage}">
                    <div class="error-message text-danger" role="alert">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty sessionScope.errorMessage}">
                    <div class="error-message text-danger" role="alert">${sessionScope.errorMessage}</div>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <div class="success-message text-success" role="alert">${successMessage}</div>
                </c:if>

                <!-- Opzioni -->
                <div class="button-group">
                    <button class="btn-action btn-secondary" onclick="toggleSection('editSection')">Modifica Profilo</button>
                    <button class="btn-action btn-secondary" onclick="toggleSection('walletSection')">Portafoglio</button>
                    <button class="btn-action btn-secondary" onclick="toggleSection('searchSection')">Cerca Profilo</button>
                </div>

                <!-- Sezione di modifica -->
                <div id="editSection" class="section-content bg-primary ml-3">
                    <form action="${pageContext.request.contextPath}/profilo/modifica" method="post">
                        <div class="form-group">
                            <label class="text-white" for="passwordVecchia">Password Vecchia:</label>
                            <input type="password" class="form-control" id="passwordVecchia" name="passwordVecchia">
                        </div>
                        <div class="form-group">
                            <label class="text-white" for="passwordNuova">Nuova Password:</label>
                            <input type="password" class="form-control" id="passwordNuova" name="passwordNuova">
                        </div>
                        <div class="form-group">
                            <label class="text-white" for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                        <div class="form-group">
                            <label class="text-white" for="telefono">Telefono:</label>
                            <input type="text" class="form-control" id="telefono" name="telefono">
                        </div>
                        <button type="submit" class="btn btn-action btn-secondary">Salva Modifiche</button>
                    </form>
                </div>

                <!-- Sezione Portafoglio -->
                <div id="walletSection" class="section-content bg-primary ml-3">
                    <p class="text-white">Saldo attuale:</p>
                    <div class="d-flex justify-content-start">
                        <p class="text-secondary mr-2">${userInfo.portafoglio}</p> <p class="text-white">€</p>
                    </div>
                    
                    <form action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi" method="post">
                        <div class="form-group">
                            <label class="text-white" for="importo">Aggiungi denaro:</label>
                            <input type="number" class="form-control" id="importo" name="importo" placeholder="Inserisci importo" min="1" required>
                        </div>
                        <button type="submit" class="btn btn-action btn-secondary">Ricarica</button>
                    </form>
                </div>

                <!-- Sezione Cerca Profilo -->
                <div id="searchSection" class="section-content bg-primary ml-3">
                    <form action="${pageContext.request.contextPath}/profilo/mostra/utente" method="post">
                        <div class="form-group">
                            <label class="text-white" for="searchUsername">Inserisci Username:</label>
                            <input type="text" class="form-control" name="username" placeholder="Cerca profilo utente">
                        </div>
                        <button type="submit" class="btn btn-primary btn-secondary">Cerca Profilo</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Pulsante Cancella Account in fondo alla pagina -->
        <button class="cancel-btn btn-danger" onclick="confirmDeleteAccount()">Cancella Account</button>
    </div>

    <!-- Script -->
    <script>
        function toggleSection(sectionId) {
            const sections = document.querySelectorAll('.section-content');
            sections.forEach(section => {
                section.style.display = 'none';
            });
            document.getElementById(sectionId).style.display = 'block';
        }

        function confirmDeleteAccount() {
            if (confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')) {
                window.location.href = '${pageContext.request.contextPath}/profilo/elimina';
            }
        }
    </script>
</body>
</html>

  --%>
 
 
 




 
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="navbar.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profilo Utente</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Industrial-final.css">

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap'); 




    body {
        font-family: 'Roboto', sans-serif;
        background-color: #f8f8ff; 
    }

    .error-message {
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        text-align: center;
        font-weight: bold;
    }

    .success-message {
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 15px;
        text-align: center;
        font-weight: bold;
    }

    .profile-container {
        position: relative;
        left: 0; /
        justify-content: flex-start; /* Allinea a sinistra */
        display: flex;
        flex-direction: column;
        margin-top: 25px;
        padding-left: 0; /* Rimuove eventuali margini a sinistra */
        margin-left: -350px;  /* Margine a sinistra */
        width: 100%;
    }

    .profile-section {
        padding: 7px;
        border-radius: 10px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        width: 30%;
        margin-right: 20px; /* Distanza tra le sezioni */
        margin-bottom: 20px;
    }

    .sidebar-section {
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
        width: 30%;
        position: relative;
    }

    .btn-action {
        padding: 10px 20px;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        width: 100%;
        margin-bottom: 15px;
    }

    .cancel-btn {
        padding: 10px 20px;
        border-radius: 5px;
        border: none;
        cursor: pointer;
        position: fixed;
        bottom: 20px;
        left: 20px;
    }

    .section-content {
        display: none;
        padding: 20px;
        border-radius: 10px;
        position: absolute;
        left: 100%;
        top: 0;
        width: 300px;
        z-index: 1000;
    }

    .button-group {
        display: flex;
        flex-direction: column;
        margin-top: 30px;
        width: 100%;
    }
    
   .transazioni-tabs {
    position: absolute;
    top: 20px; /* Distanza dall'alto */
    right: -650px; /* Distanza dal lato destro */
    width: 700px; /* Larghezza della tab */
    background-color: #f8f9fa;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1););
}
 

.transazioni-tabs  {
    width: 100%; /* Adatta alla larghezza del contenitore */
}
</style>
</head>
<body>

    <div class="container">
        <div class="profile-container">
        
        
        <div class="transazioni-tabs mt-5 d-flex">
    <div class="nav flex-column nav-tabs" id="nav-tab" role="tablist">
        <a class="nav-link active" id="transazioni-vendita-tab" data-toggle="tab" href="#transazioni-vendita" role="tab">Transazioni Vendita</a>
        <a class="nav-link" id="transazioni-acquisto-tab" data-toggle="tab" href="#transazioni-acquisto" role="tab">Transazioni Acquisto</a>
    </div>

    <!-- Contenuto delle Tab -->
    <div class="tab-content ml-3" id="nav-tabContent">
        <!-- Tab Transazioni Vendita -->
        <div class="tab-pane fade show active" id="transazioni-vendita" role="tabpanel">
            <h4>Transazioni di Vendita</h4>
            <ul>
                <c:forEach var="transazione" items="${transazioniVendita}">
                    <li>ID: ${transazione.idTransazione}, Prezzo: ${transazione.importo}€, Data: ${transazione.data}, Treno: ${transazione.trenoNome}, Venditore: ${transazione.venditoreUsername}, Acquirente: ${transazione.acquirenteUsername}</li>
                </c:forEach>
            </ul>
        </div>

        <!-- Tab Transazioni Acquisto -->
        <div class="tab-pane fade" id="transazioni-acquisto" role="tabpanel">
            <h4>Transazioni di Acquisto</h4>
            <ul>
                <c:forEach var="transazione" items="${transazioniAcquisto}">
                    <li>ID: ${transazione.idTransazione}, Prezzo: ${transazione.importo}€, Data: ${transazione.data}, Treno: ${transazione.trenoNome}, Venditore: ${transazione.venditoreUsername}, Acquirente: ${transazione.acquirenteUsername}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>


<!-- Fine Transazioni -->






            <!-- Profilo utente -->
            <div class="profile-section bg-primary">
                <h5 class="text-center mt-5 text-white">Informazioni Utente</h5>
                <table class="table table-borderless">
                    <tr>
                        <th class="text-white">Nome:</th>
                        <td class="text-secondary">${userInfo.nome}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Cognome:</th>
                        <td class="text-secondary">${userInfo.cognome}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Username:</th>
                        <td class="text-secondary">${userInfo.username}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Portafoglio:</th>
                        <td class="text-secondary mr-1">${userInfo.portafoglio} €</td>
                    </tr>
                    <tr>
                        <th class="text-white">Email:</th>
                        <td class="text-secondary">${userInfo.email}</td>
                    </tr>
                     <tr>
                        <th class="text-white">Telefono:</th>
                        <td class="text-secondary">${userInfo.telefono}</td>
                    </tr>
                    <tr>
                        <th class="text-white">Password:</th>
                        <td class="text-secondary">${userInfo.password}</td>
                    </tr>
                </table>
            </div>

            <!-- Sidebar per azioni -->
            <div class="sidebar-section bg-primary">
           
                <!-- Messaggi di errore e successo -->
                <c:if test="${not empty errorMessage}">
                    <div class="error-message text-danger" role="alert">${errorMessage}</div>
                </c:if>
                <c:if test="${not empty sessionScope.errorMessage}">
                    <div class="error-message text-danger" role="alert">${sessionScope.errorMessage}</div>
                </c:if>

                <c:if test="${not empty successMessage}">
                    <div class="success-message text-success" role="alert">${successMessage}</div>
                </c:if>

                <!-- Opzioni -->
                <div class="button-group">
                    <button class="btn-action btn btn-secondary" onclick="toggleSection('editSection')">Modifica Profilo</button>
                    <button class="btn-action btn-secondary" onclick="toggleSection('walletSection')">Portafoglio</button>
                    <button class="btn-action btn-secondary" onclick="toggleSection('searchSection')">Cerca Profilo</button>
                </div>

                <!-- Sezione di modifica -->
                <div id="editSection" class="section-content bg-primary ml-3">
                    <form action="${pageContext.request.contextPath}/profilo/modifica" method="post">
                        <div class="form-group">
                            <label class="text-white" for="passwordVecchia">Password Vecchia:</label>
                            <input type="password" class="form-control" id="passwordVecchia" name="passwordVecchia">
                        </div>
                        <div class="form-group">
                            <label class="text-white" for="passwordNuova">Nuova Password:</label>
                            <input type="password" class="form-control" id="passwordNuova" name="passwordNuova">
                        </div>
                        <div class="form-group">
                            <label class="text-white" for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                        <div class="form-group">
                            <label class="text-white" for="telefono">Telefono:</label>
                            <input type="text" class="form-control" id="telefono" name="telefono">
                        </div>
                        <button type="submit" class="btn btn-action btn-secondary">Salva Modifiche</button>
                    </form>
                </div>

                <!-- Sezione Portafoglio -->
                <div id="walletSection" class="section-content bg-primary ml-3">
                    <p class="text-white">Saldo attuale:</p>
                    <div class="d-flex justify-content-start">
                        <p class="text-secondary mr-2">${userInfo.portafoglio}</p> <p class="text-white">€</p>
                    </div>
                    
                    <form action="${pageContext.request.contextPath}/profilo/portafoglio/aggiungi" method="post">
                        <div class="form-group">
                            <label class="text-white" for="importo">Aggiungi denaro:</label>
                            <input type="number" class="form-control" id="importo" name="importo" placeholder="Inserisci importo" min="1" required>
                        </div>
                        <button type="submit" class="btn btn-action btn-secondary">Ricarica</button>
                    </form>
                </div>

                <!-- Sezione Cerca Profilo -->
                <div id="searchSection" class="section-content bg-primary ml-3">
                    <form action="${pageContext.request.contextPath}/profilo/mostra/utente" method="post">
                        <div class="form-group">
                            <label class="text-white" for="searchUsername">Inserisci Username:</label>
                            <input type="text" class="form-control" name="username" placeholder="Cerca profilo utente">
                        </div>
                        <button type="submit" class="btn btn-primary btn-secondary">Cerca Profilo</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Pulsante Cancella Account in fondo alla pagina -->
        <button class="cancel-btn btn-danger" onclick="confirmDeleteAccount()">Cancella Account</button>
    </div>

    <!-- Script -->
    <script>
        function toggleSection(sectionId) {
            const sections = document.querySelectorAll('.section-content');
            sections.forEach(section => {
                section.style.display = 'none';
            });
            document.getElementById(sectionId).style.display = 'block';
        }

        function confirmDeleteAccount() {
            if (confirm('Sei sicuro di voler cancellare il tuo account? Questa azione è irreversibile.')) {
                window.location.href = '${pageContext.request.contextPath}/profilo/elimina';
            }
        }
    </script>
</body>
</html>

 
 

 