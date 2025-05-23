window.onload = function() {
    fetch('/api/reciters')
        .then(response => response.json())
        .then(reciters => {
            const grid = document.getElementById('recitersGrid');
            grid.innerHTML = '';

            reciters.forEach(reciter => {
                const reciterCard = document.createElement('div');
                reciterCard.className = 'reciter-card';
                reciterCard.innerHTML = `
                    <img src="${reciter.imageUrl}" alt="${reciter.name}">
                    <h3>${reciter.name}</h3>
                `;
                grid.appendChild(reciterCard);
            });
        });
}