import { booksRender } from "./booksRender.js";
import { items } from "./index.js";

const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");

tabRecent.addEventListener('click',backToRecentBooks)
function backToRecentBooks(){
    tabRecent.classList.remove('text-success');
    tabRecent.classList.toggle('active');
    tabMyBooks.classList.remove('active');
    tabMyBooks.classList.add('text-success');
    booksRender(items);
}

export {backToRecentBooks}