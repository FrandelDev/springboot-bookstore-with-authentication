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

await getRecommendedBooks();
await getRecentBooks();
const mibooks = await getMyBooks();
console.log(mibooks);
let allBooks = []
allBooks.push(...recentBooks);
allBooks.push(...recommendedBooks);
allBooks.sort((a,b) => b.pages - a.pages);
const items = new Singleton();
items.updateData = allBooks;
await removeOwnedBooks();
booksRender(items.allData);








export {items,Singleton}

