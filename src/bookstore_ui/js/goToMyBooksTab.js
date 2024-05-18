
import { booksRender } from "./booksRender.js";
import { getMyBooks } from "./getMyBooks.js";

const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");

tabMyBooks.addEventListener('click',goToMyBooksTab);
async function goToMyBooksTab(){
    tabMyBooks.classList.remove('text-success');
    tabMyBooks.classList.toggle('active');
    tabRecent.classList.remove('active');
    tabRecent.classList.add('text-success');
   const getBooks = await getMyBooks();
  
   booksRender(getBooks);
}

export {goToMyBooksTab}