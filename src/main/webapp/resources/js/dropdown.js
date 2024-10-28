document.getElementById("dropdown").addEventListener("click", function(event) {
    event.preventDefault(); // Previene il comportamento di default del link
    const dropdownContent = document.getElementById("dropdown-content");
    dropdownContent.style.display = dropdownContent.style.display === "block" ? "none" : "block";
});

window.addEventListener("click", function(event) {
    if (!event.target.matches('#dropdown') && !event.target.closest('.dropdown-menu')) {
        document.getElementById("dropdown-content").style.display = "none";
    }
});