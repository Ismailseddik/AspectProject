
document.addEventListener("DOMContentLoaded", () => {
  loadFavorites();

  document.getElementById("searchBtn").addEventListener("click", () => {
    const query = document.getElementById("searchInput").value.trim();
    if (!query) return;

    const encoded = encodeURIComponent(query);
    Promise.all([
      fetch(`/api/surahs/search?keyword=${encoded}`).then(res => res.json()),
      fetch(`/api/ayahs/search?keyword=${encoded}`).then(res => res.json())
    ]).then(([surahs, ayahs]) => {
      const container = document.getElementById("searchResults");
      container.innerHTML = '';

      surahs.results?.forEach(surah => {
        const div = document.createElement('div');
        div.className = 'result-item';
        div.innerHTML = `
          <h3>${surah.name} <span class="arabic-text">${surah.nameArabic || ''}</span></h3>
          <p><strong>Verses:</strong> ${surah.numberOfVerses}</p>
          <p><strong>Revelation:</strong> ${surah.revelationType}</p>
          <button class="cta">Add to Favorites</button>
        `;
        div.querySelector('button').addEventListener('click', () => {
        const fav = {
          surahId: surah.id,
          ayahNumber: 1, // or whatever default you choose
          ayahText: `Surah with ${surah.numberOfVerses} verses`,
          ayahTextArabic: "",
          surahName: surah.name,
          surahNameArabic: surah.nameArabic,
          username: localStorage.getItem("username")
        };
          addFavorite(fav);
        });
        container.appendChild(div);
      });

      ayahs.results?.forEach(ayah => {
        const div = document.createElement('div');
        div.className = 'result-item';
        div.innerHTML = `
          <h3>Surah: ${ayah.surahName} - Ayah ${ayah.verseNumber}</h3>
          <p class="arabic-text">${ayah.textArabic}</p>
          <p>${ayah.text}</p>
          <button class="cta">Add to Favorites</button>
        `;
        div.querySelector('button').addEventListener('click', () => {
        const fav = {
          surahId: ayah.surahId,
          ayahNumber: ayah.verseNumber,
          ayahText: ayah.text,
          ayahTextArabic: ayah.textArabic,
          surahName: ayah.surahName,
          surahNameArabic: ayah.surahNameArabic,
          username: localStorage.getItem("username")
        };
          addFavorite(fav);
        });
        container.appendChild(div);
      });

      if ((!surahs.results?.length) && (!ayahs.results?.length)) {
        container.innerHTML = '<p>No matches found.</p>';
      }
    });
  });
});

function loadFavorites() {
  const username = localStorage.getItem("username");
  if (!username) {
    console.error("No username in localStorage.");
    return;
  }

  fetch(`/api/favorites?username=${encodeURIComponent(username)}`)
    .then(res => res.json())
    .then(favorites => {
      const list = document.getElementById('favoritesList');
      list.innerHTML = '';

      favorites.forEach(fav => {
        const div = document.createElement('div');
        div.className = 'result-item';
        div.innerHTML = `
          <div class="favorite-card">
            <h3>${fav.surahName} <span class="arabic-text">${fav.surahNameArabic}</span> - Ayah ${fav.ayahNumber}</h3>
            <p class="arabic-text">${fav.ayahTextArabic}</p>
            <p>${fav.ayahText}</p>
            <button class="cta" onclick="deleteFavorite(${fav.id})">Remove</button>
          </div>
        `;
        list.appendChild(div);
      });
    })
    .catch(err => console.error('Error loading favorites:', err));
}

function addFavorite(favoriteData) {
  const username = localStorage.getItem("username");
  if (!username) {
    alert("Not logged in");
    return;
  }

    const payload = {
      username,
      surahId: favoriteData.surahId,
      ayahNumber: favoriteData.ayahNumber,
      ayahText: favoriteData.ayahText,
      ayahTextArabic: favoriteData.ayahTextArabic,
      surahName: favoriteData.surahName,
      surahNameArabic: favoriteData.surahNameArabic
    };

  fetch('/api/favorites', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(payload)
  })
  .then(response => {
    if (!response.ok) throw new Error("Bad Request");
    return response.json();
  })
  .then(data => {
    console.log("Favorite added:", data);
    loadFavorites();
  })
  .catch(err => console.error("Failed to add favorite:", err));
}

function deleteFavorite(id) {
  fetch(`/api/favorites/${id}`, { method: 'DELETE' })
    .then(() => loadFavorites())
    .catch(err => console.error('Delete error:', err));
}
