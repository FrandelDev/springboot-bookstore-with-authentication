import { items } from "./index.js";
import { goToMyBooksTab, enableBookTab } from "./goToMyBooksTab.js";
import { getRecommendedBooks } from "./getRecommendedBooks.js";
import { getMyBooks } from "./getMyBooks.js";
import { removeOwnedBooks } from "./removeOwnedBooks.js";
import { booksRender } from "./booksRender.js";
import { decode } from "./jwt_decoder.js";

let usernameInput = document.querySelector("#username");
let passwordInput = document.querySelector("#password");
let loginBtn = document.querySelector("#loginBtn");
const toggleLoginFormBtn = document.querySelector("#toggle-login-form");
const loginForm = document.querySelector("#login");


loginBtn.addEventListener('click', authenticate);

async function authenticate() {
    console.log('hola');
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
            alterLoginForm();
            const JWT = response.headers.get('Authorization');
            localStorage.setItem("jwt", JWT)
            detectStoreLoginSession();
            await renderRecommendedBooksAndMyBooks();
        } else {
            console.error('Error:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

toggleLoginFormBtn.addEventListener('click', alterLoginForm)
function alterLoginForm() {
    loginForm.classList.toggle('visible');
}

function detectStoreLoginSession() {
    if (localStorage.getItem('jwt')) {
        toggleLoginFormBtn.innerHTML = `<div id="toggle-login-form"><img src="./assets/user.svg" alt=""><p>${decode(localStorage.getItem('jwt'))}</p></div>`;
        loginForm.innerHTML = ' <button type="button" class="btn btn-secondary" id="logOutBtn" style="width:150px;border:none">↺ Log Out</button>'
        document.querySelector('#logOutBtn').addEventListener('click', () => {
            localStorage.removeItem('jwt');
            toggleLoginFormBtn.innerHTML = '<div id="toggle-login-form">Log In</div>'
            loginForm.innerHTML = `
            <form>
                <div class="">
                  <label for="exampleInputEmail1" class="form-label">Username</label>
                  <input type="text" class="form-control" id="username">
                </div>
                <div class="mb-3">
                  <label for="exampleInputPassword1" class="form-label">Password</label>
                  <input type="password" class="form-control" id="password">
                </div>
                <button type="button" class="btn btn-success" id="loginBtn">Login</button>
        
              </form>
            `
            usernameInput = document.querySelector("#username");
            passwordInput = document.querySelector("#password");
            loginBtn = document.querySelector("#loginBtn").addEventListener('click', authenticate);
            document.querySelector('#tab-my-books').remove();
        })
    }
}

async function renderRecommendedBooksAndMyBooks() {
    !document.querySelector('#tab-my-books') ? enableBookTab() : null;

    const userBooks = await getMyBooks();
    let redefinedAllBooks = []
    redefinedAllBooks.push(...items.allData)
    redefinedAllBooks.push(...await getRecommendedBooks());
    redefinedAllBooks.sort((a, b) => b.pages - a.pages);
    items.updateData = redefinedAllBooks;
    if (userBooks) await removeOwnedBooks();
    booksRender(redefinedAllBooks);
}

export { authenticate, alterLoginForm, detectStoreLoginSession, renderRecommendedBooksAndMyBooks };
