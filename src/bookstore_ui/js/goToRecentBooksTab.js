import { booksRender } from "./booksRender.js";
import { removeOwnedBooks} from "./removeOwnedBooks.js";
import { Singleton } from "./index.js";

const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");

tabRecent.addEventListener('click',goToRecentBooks)
async function goToRecentBooks(){
    tabRecent.classList.remove('text-success');
    tabRecent.classList.toggle('active');
    tabMyBooks.classList.remove('active');
    tabMyBooks.classList.add('text-success');
    const singletonInstance = new Singleton();
    await removeOwnedBooks();
    console.log(singletonInstance.allData);
    booksRender(singletonInstance.allData);
}

export {goToRecentBooks}