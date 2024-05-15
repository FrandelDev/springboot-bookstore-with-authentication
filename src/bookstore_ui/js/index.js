import { getRecentBooks,recentBooks } from "./GetRecentBooks.js";
import { booksRender } from "./booksRender.js";
import { backToRecentBooks } from "./backToRecentBooks.js";
import { getMyBooks,myBooks } from "./getMyBooks.js";
import { getRecommendedBooks,recommendedBooks } from "./getRecommendedBooks.js";
import { bookInfo } from "./bookInfo.js";
import { addToCart } from "./addToCart.js";

await getRecentBooks();
await getRecommendedBooks();

let items = []
items.push(...recentBooks)
items.push(...recommendedBooks)
items.sort((a,b) => b.pages - a.pages)
console.log(items);
booksRender(items)

export {items}

