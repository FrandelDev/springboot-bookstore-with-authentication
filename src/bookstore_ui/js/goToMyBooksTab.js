
import { booksRender } from "./booksRender.js";
import { getMyBooks } from "./getMyBooks.js";
import { removeOwnedBooks } from "./removeOwnedBooks.js";

const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");
const searchbar = document.querySelector("#search");


tabMyBooks.addEventListener('click',goToMyBooksTab);
async function goToMyBooksTab(){
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

export {goToMyBooksTab}