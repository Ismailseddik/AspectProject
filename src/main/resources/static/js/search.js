document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("searchInput");
    const searchButton = document.getElementById("searchBtn");
    const resultsDiv = document.getElementById("searchResults");

    const arabicRegex = /[\u0600-\u06FF]/;

    // Set RTL or LTR based on input
    searchInput.addEventListener("input", () => {
        const text = searchInput.value;
        if (arabicRegex.test(text)) {
            searchInput.style.direction = "rtl";
            searchInput.style.textAlign = "right";
        } else {
            searchInput.style.direction = "ltr";
            searchInput.style.textAlign = "left";
        }
    });

    searchButton.addEventListener("click", performSearch);

    function performSearch() {
        const query = searchInput.value.trim();
        resultsDiv.innerHTML = '<p>Searching...</p>';

        if (!query) {
            alert("Please enter a search term!");
            return;
        }

        const encodedQuery = encodeURIComponent(query);

        Promise.all([
            fetch(`/api/surahs/search?keyword=${encodedQuery}`).then(res => res.json()),
            fetch(`/api/ayahs/search?keyword=${encodedQuery}`).then(res => res.json())
        ])
        .then(([surahs, ayahs]) => {
            resultsDiv.innerHTML = '';

            // Surahs
            if (surahs.results && surahs.results.length > 0) {
                const surahHeader = document.createElement('h2');
                surahHeader.textContent = `Surah Matches (${surahs.totalResults})`;
                resultsDiv.appendChild(surahHeader);

                surahs.results.forEach(surah => {
                    const div = document.createElement('div');
                    div.className = 'result-item';
                    div.innerHTML = `
                        <h3>${surah.name} <span class="arabic-text">${surah.nameArabic || ''}</span></h3>
                        <p><strong>Verses:</strong> ${surah.numberOfVerses || 'N/A'}</p>
                        <p><strong>Revelation Type:</strong> ${surah.revelationType || 'Unknown'}</p>
                    `;
                    resultsDiv.appendChild(div);
                });
            }

            // Ayahs
            if (ayahs.results && ayahs.results.length > 0) {
                const ayahHeader = document.createElement('h2');
                ayahHeader.textContent = `Ayah Matches (${ayahs.totalResults})`;
                resultsDiv.appendChild(ayahHeader);

                ayahs.results.forEach(ayah => {
                    const div = document.createElement('div');
                    div.className = 'result-item';
                    div.innerHTML = `
                        <h3>Surah: ${ayah.surahName || 'Unknown'} - Ayah ${ayah.verseNumber || ''}</h3>
                        <p class="arabic-text" dir="rtl">${ayah.textArabic || ''}</p>
                        <p>${ayah.text || ''}</p>
                    `;
                    resultsDiv.appendChild(div);
                });
            }

            if ((!surahs.results || surahs.results.length === 0) && (!ayahs.results || ayahs.results.length === 0)) {
                resultsDiv.innerHTML = '<p>No matches found.</p>';
            }
        })
        .catch(err => {
            console.error("Search failed:", err);
            resultsDiv.innerHTML = '<p>Something went wrong. Please try again.</p>';
        });
    }
});