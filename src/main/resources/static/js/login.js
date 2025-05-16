document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    })
    .then(response => {
        if (response.ok) {
        localStorage.setItem("username", username);
            window.location.href = '/home';
        } else {
            alert("Invalid login credentials.");
        }
    })
    .catch(error => {
        console.error('Login error:', error);
        alert("Login failed. Try again later.");
    });
});