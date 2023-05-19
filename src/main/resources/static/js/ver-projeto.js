// Get the button and overlay elements
var button = document.getElementById("open-chat");
var overlay = document.getElementById("myOverlay");

// Get the close button element
var closeButton = overlay.querySelector(".close");

// Show the overlay when the button is clicked
button.addEventListener("click", function() {
    overlay.style.display = "block";
});

// Hide the overlay when the close button is clicked
closeButton.addEventListener("click", function() {
    overlay.style.display = "none";
});