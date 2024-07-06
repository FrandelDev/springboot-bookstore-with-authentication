import { authenticate,alterLoginForm,detectStoreLoginSession,renderRecommendedBooksAndMyBooks } from "./login.js";
import { getRecentBooks,recentBooks } from "./GetRecentBooks.js";
import { booksRender } from "./booksRender.js";
import { goToRecentBooks } from "./goToRecentBooksTab.js";
import { goToMyBooksTab } from "./goToMyBooksTab.js";
import { getMyBooks,myBooks } from "./getMyBooks.js";
import { getRecommendedBooks,recommendedBooks } from "./getRecommendedBooks.js";
import { bookInfo } from "./bookInfo.js";
import { addToCart,cart} from "./addToCart.js";
import { postBooks } from "./postBooks.js";
import { removeOwnedBooks } from "./removeOwnedBooks.js";
import { searchBooks } from "./searchBooks.js";
detectStoreLoginSession();
const loader = document.querySelector("#loading");
let instance = null;

class Singleton {
    constructor() {
        if (!instance) {
            this.data =[]
            instance = this
        }
        return instance;
    }
    get allData(){
        return this.data;
    }
    /**
     * @param {any} newData
     */
    set updateData(newData){
        this.data = newData;
    }
}
loader.style.visibility = 'visible';

let allBooks = []
const items = new Singleton();

allBooks.push(...await getRecentBooks());

if(localStorage.getItem('jwt') != null){
     renderRecommendedBooksAndMyBooks();
}

loader.style.visibility = 'hidden';

allBooks.sort((a,b) => b.pages - a.pages);
items.updateData = allBooks;
booksRender(items.allData);








export {items,Singleton}

