console.log("Baraka Frontend Loaded");
document.addEventListener("DOMContentLoaded", function () {
    const audio = document.querySelector("audio");
    const speedSelector = document.getElementById("speed");

    speedSelector.addEventListener("change", function () {
        const speed = parseFloat(this.value);
        audio.playbackRate = speed;
    });
});

document.getElementById('reciterSelect').addEventListener('change', function () {
    const selectedReciter = this.value;
    const surahId = document.getElementById('surahId').value;

    // Rebuild URL with selected reciter
    const newUrl = `/home?surahId=${surahId}&reciter=${encodeURIComponent(selectedReciter)}`;
    window.location.href = newUrl;
});
