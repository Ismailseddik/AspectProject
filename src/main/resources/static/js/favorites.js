window.onload = function () {
  fetch('/api/favorites')
    .then(response => response.json())
    .then(favorites => {
      const favoritesList = document.getElementById('favoritesList');
      favoritesList.innerHTML = '';

      favorites.forEach(item => {
        const favElement = document.createElement('div');
        favElement.className = 'result-item';
        favElement.innerHTML = `
          <div class="favorite-card">
            <h3>${item.verseReference} - ${item.reciterName}</h3>
            <p><strong>Ayah:</strong> ${item.ayahText}</p>
            <button onclick="deleteFavorite(${item.id})">Remove</button>
          </div>
        `;
        favoritesList.appendChild(favElement);
      });
    })
    .catch(error => {
      console.error('Error loading favorites:', error);
    });
}

function deleteFavorite(id) {
  fetch(`/api/favorites/${id}`, {
    method: 'DELETE'
  })
    .then(() => window.location.reload())
    .catch(error => console.error('Error deleting favorite:', error));
}
