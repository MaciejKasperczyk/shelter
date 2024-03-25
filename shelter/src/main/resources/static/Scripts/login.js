document.getElementById('login-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent form submission

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const response = await fetch('http://localhost:8090/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userName: username, userPassword: password })
    });

    if (response.ok) {
        window.location.href = '/Index.html'; // Redirect to dashboard on successful login
    } else {
        const errorMessage = await response.text();
        document.getElementById('error-message').textContent = errorMessage;
    }
});