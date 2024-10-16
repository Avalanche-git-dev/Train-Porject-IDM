<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizza Treni</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .train-card {
            margin-bottom: 20px;
        }
        .train-card img {
            max-width: 100%;
            height: auto;
        }
        .details-btn {
            background-color: #0275d8;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }
        .details-btn:hover {
            background-color: #025aa5;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h1 class="text-center">I tuoi treni</h1>
    <div class="row" id="trainList">
        <!-- La lista dei treni sarà inserita qui da JavaScript -->
    </div>
</div>

<script>
    // Recupera la lista dei treni passata dal backend in formato JSON
    const treniUtente = JSON.parse('${treniJson}');  // Popola con la lista passata dal controller

    // Funzione per popolare la lista dei treni
    function populateTrainList() {
        const trainListContainer = document.getElementById('trainList');
        trainListContainer.innerHTML = ''; // Pulisce l'elemento

        treniUtente.forEach(treno => {
            const mediaValutazioni = treno.mediaValutazioni ? treno.mediaValutazioni : "Non valutato";
            const imageUrl = treno.immagineUrl ? treno.immagineUrl : "/images/default-train.png";

            const trainCard = `
                <div class="col-md-4 train-card">
                    <div class="card">
                        <img src="${imageUrl}" class="card-img-top" alt="${treno.nome}">
                        <div class="card-body">
                            <h5 class="card-title">${treno.nome}</h5>
                            <p class="card-text">Media Valutazioni: ${mediaValutazioni} / 5</p>
                            <button class="details-btn" onclick="window.location.href='/treni/dettagli/${treno.id}'">Dettagli</button>
                        </div>
                    </div>
                </div>
            `;
            trainListContainer.innerHTML += trainCard;
        });
    }

    // Popola la lista dei treni al caricamento della pagina
    populateTrainList();
</script>

</body>
</html>
