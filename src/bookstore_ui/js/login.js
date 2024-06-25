const usernameInput = document.querySelector("#username");
const passwordInput = document.querySelector("#password");
const loginBtn = document.querySelector("#loginBtn");
const toggleLoginFormBtn = document.querySelector("#toggle-login-form");
const loginForm = document.querySelector("#login");


loginBtn.addEventListener('click', authenticate);

async function authenticate() {
    try {
        const response = await fetch('http://localhost:8383/api/bookstore/auth/login', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: usernameInput.value,
                password: passwordInput.value
            })
        });

        if (response.ok) {
            const JWT = response.headers.get('Authorization');
            localStorage.setItem("jwt",JWT)
        } else {
            console.error('Error:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

toggleLoginFormBtn.addEventListener('click',alterLoginForm)
function alterLoginForm(){
    loginForm.classList.toggle('visible');
}

export { authenticate,alterLoginForm };
