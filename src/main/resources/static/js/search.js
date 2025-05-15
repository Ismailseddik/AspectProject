document.addEventListener("DOMContentLoaded", () => {
    const searchButton = document.getElementById("searchBtn");
    searchButton.addEventListener("click", performSearch);
});

function performSearch() {
    const query = document.getElementById("searchInput").value.trim();
    const resultsDiv = document.getElementById("searchResults");
    resultsDiv.innerHTML = '<p>Searching...</p>';

    if (!query) {
        alert("Please enter a search term!");
        return;
    }

    // Try both endpoints
    Promise.all([
        fetch(`/api/surahs/search?keyword=${encodeURIComponent(query)}`).then(res => res.json()),
        fetch(`/api/ayahs/search?keyword=${encodeURIComponent(query)}`).then(res => res.json())
    ]).then(([surahs, ayahs]) => {
        resultsDiv.innerHTML = '';

        // Render surah results
        if (surahs.results && surahs.results.length > 0) {
            const surahTitle = document.createElement('h2');
            surahTitle.textContent = `Surah Matches (${surahs.totalResults})`;
            resultsDiv.appendChild(surahTitle);

            surahs.results.forEach(surah => {
                const div = document.createElement('div');
                div.className = 'result-item';
                div.innerHTML = `
                    <h3>${surah.name} <span class="arabic-text">${surah.nameArabic || ''}</span></h3>
                    <p><strong>Verses:</strong> ${surah.numberOfVerses}</p>
                    <p><strong>Revelation Type:</strong> ${surah.revelationType}</p>
                `;
                resultsDiv.appendChild(div);
            });
        }

        // Render ayah results
        if (ayahs.results && ayahs.results.length > 0) {
            const ayahTitle = document.createElement('h2');
            ayahTitle.textContent = `Ayah Matches (${ayahs.totalResults})`;
            resultsDiv.appendChild(ayahTitle);

            ayahs.results.forEach(ayah => {
                const div = document.createElement('div');
                div.className = 'result-item';
                div.innerHTML = `
                    <h3>Surah: ${ayah.surahName || 'Unknown'} - Ayah ${ayah.verseNumber}</h3>
                    <p class="arabic-text">${ayah.textArabic || ''}</p>
                    <p>${ayah.text || ''}</p>
                `;
                resultsDiv.appendChild(div);
            });
        }

        if ((!surahs.results || surahs.results.length === 0) && (!ayahs.results || ayahs.results.length === 0)) {
            resultsDiv.innerHTML = '<p>No matches found.</p>';
        }

    }).catch(err => {
        console.error("Search failed:", err);
        resultsDiv.innerHTML = '<p>Something went wrong. Please try again.</p>';
    });
}