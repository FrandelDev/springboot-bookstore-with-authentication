import { getRecentBooks,recentBooks } from "./GetRecentBooks.js";
import { booksRender } from "./booksRender.js";
import { backToRecentBooks } from "./backToRecentBooks.js";
import { getMyBooks,myBooks } from "./getMyBooks.js";
import { bookInfo } from "./bookInfo.js";

await getRecentBooks();
console.log(recentBooks);
booksRender(recentBooks)

