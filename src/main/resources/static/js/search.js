function performSearch() {
    const query = document.getElementById("searchInput").value.trim();

    if (!query) {
        alert("Please enter a search term!");
        return;
    }

    // Simulate a simple search result (mocked for now)
    const resultsDiv = document.getElementById("searchResults");
    resultsDiv.innerHTML = '';

    // In real app: fetch('/api/search?q=' + query).then(...)
    const dummyResults = [
        { surah: "Al-Baqara", ayah: "This is the Book about which there is no doubt..." },
        { surah: "Al-Fatiha", ayah: "Guide us to the straight path..." }
    ];

    dummyResults.forEach(result => {
        const resultElement = document.createElement('div');
        resultElement.className = 'result-item';
        resultElement.innerHTML = `
            <h3>Surah: ${result.surah}</h3>
            <p>${result.ayah}</p>
        `;
        resultsDiv.appendChild(resultElement);
});
}