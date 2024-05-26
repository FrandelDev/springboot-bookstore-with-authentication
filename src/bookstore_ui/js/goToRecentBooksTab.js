import { booksRender } from "./booksRender.js";
import { removeOwnedBooks} from "./removeOwnedBooks.js";
import { Singleton } from "./index.js";

const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");
const searchbar = document.querySelector("#search");
const close = document.querySelector(".close");
const toBuy = document.querySelector("#toBuy");

tabRecent.addEventListener('click',goToRecentBooks)
async function goToRecentBooks(){
    document.querySelector("#search-tab")?.remove();
    searchbar.value = '';
    tabRecent.classList.remove('text-success');
    tabRecent.classList.add('active');
    tabMyBooks.classList.remove('active');
    tabMyBooks.classList.add('text-success');
    const singletonInstance = new Singleton();
    await removeOwnedBooks();
    console.log(singletonInstance.allData);
    booksRender(singletonInstance.allData);
}

export {goToRecentBooks}