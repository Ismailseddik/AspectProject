window.onload = function() {
    fetch('/api/favorites')
        .then(response => response.json())
        .then(favorites => {
            const favoritesList = document.getElementById('favoritesList');
            favoritesList.innerHTML = '';

            favorites.forEach(item => {
                const favElement = document.createElement('div');
                favElement.className = 'result-item';
                favElement.innerHTML = `
                    <h3>${item}</h3>
                `;
                favoritesList.appendChild(favElement);
            });
        })
        .catch(error => {
            console.error('Error loading favorites:', error);
});
}