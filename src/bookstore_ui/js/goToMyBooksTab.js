
import { booksRender } from "./booksRender.js";
import { getMyBooks } from "./getMyBooks.js";
import { removeOwnedBooks } from "./removeOwnedBooks.js";

const tabRecent = document.querySelector("#tab-recent");
const searchbar = document.querySelector("#search");
const tabs = document.querySelector("#tabs");

function enableBookTab(){
    tabs.insertAdjacentHTML('beforeend',`
        <li class="nav-item">
        <a id="tab-my-books" class="nav-link text-success" href="#">My Books</a>
        </li>
        `);
     document.querySelector("#tab-my-books").addEventListener('click',goToMyBooksTab);
}

async function goToMyBooksTab(){
    const tabMyBooks = document.querySelector('#tab-my-books');
    document.querySelector("#search-tab")?.remove();
    searchbar.value = '';
    tabMyBooks.classList.remove('text-success');
    tabMyBooks.classList.add('active');
    tabRecent.classList.remove('active');
    tabRecent.classList.add('text-success');
    await removeOwnedBooks();
   const getBooks = await getMyBooks();
  
   booksRender(getBooks);
   document.querySelectorAll(".card-body .price").forEach(p => p.remove());
}

export {goToMyBooksTab,enableBookTab}