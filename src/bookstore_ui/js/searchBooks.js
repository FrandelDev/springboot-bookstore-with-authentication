import { booksRender } from "./booksRender.js";

const btnSearch = document.querySelector("#btn-search");
const searchbar = document.querySelector("#search");
const booksSection = document.querySelector("#books");
const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");
const tabs = document.querySelector("#tabs");
const loader = document.querySelector("#loading");

const url = "http://localhost:8383/api/bookstore/search?criteria=";

let itemsFromSearch;

btnSearch.addEventListener('click', searchBooks);
async function searchBooks(){
    document.querySelector("#search-tab")?.remove();
    tabs.insertAdjacentHTML('beforeend',`
    <li id="search-tab" class="nav-item">
            <a id="tab-recent" class="nav-link active" aria-current="page" href="#">Search Books</a>
        </li>
    `);
    booksSection.innerHTML = '';
    tabRecent.classList.add('text-success');
    tabRecent.classList.remove('active');
    tabMyBooks.classList.remove('active');
    tabMyBooks.classList.add('text-success');

    try {
        loader.style.visibility = 'visible';
        const res = await fetch(url+searchbar.value, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!res.ok) {
            loader.style.visibility = 'hidden';
            const error = await res.json();
            throw new Error(error.message);
        } else {
            const data = await res.json();
            booksRender(data);
            itemsFromSearch = data;
            loader.style.visibility = 'hidden';
            return data;
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
export {searchBooks,itemsFromSearch}