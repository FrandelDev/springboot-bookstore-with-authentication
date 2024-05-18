import { recentBooks } from "./GetRecentBooks.js";
import { items } from "./index.js";
import { myBooks } from "./getMyBooks.js";
import { itemsFromSearch } from "./searchBooks.js";

const bookInfoCard = document.querySelector("#book-info");
const title = document.querySelector("#title");
const subtitle = document.querySelector("#subtitle");
const description = document.querySelector("#description");
const authors = document.querySelector("#authors");
const publisher = document.querySelector("#publisher");
const year = document.querySelector("#year");
const pages = document.querySelector("#pages");
const image = document.querySelector("#image");
const categories = document.querySelector("#categories");
const close = document.querySelector(".close");

let bookSelected;

function bookInfo(event) {

    const findInItems = items.data.find(book => book.id == event.currentTarget.id);
    const findInMyBooks = myBooks?.find(book => book.id == event.currentTarget.id);
    const findInItemFromSearch = itemsFromSearch?.find(book => book.id == event.currentTarget.id);


    if (findInItems) {
        fillAllFields(findInItems);
        return;
    }
    else if (findInMyBooks) {
        fillAllFields(findInMyBooks);
        return;
    }
    else if(findInItemFromSearch){
        fillAllFields(findInItemFromSearch);
        return;
    }
    else{
        console.error('Not Found');
    }
}
function fillAllFields(source) {
    bookSelected = source;
    title.innerText = source.title;
    subtitle.innerText = source.subtitle;
    description.innerText =  source.description;
    authors.innerHTML = "<b>Authors: </b>" + source.authors;
    publisher.innerHTML = "<b>Publisher: </b>" + source.publisher;
    year.innerText = source.year;
    pages.innerText = "Pages: " + source.pages;
    image.src = source.image
    categoriesRender(source.categories);

    bookInfoCard.style.visibility = 'visible';
    close.addEventListener('click', () => bookInfoCard.style.visibility = 'hidden');
}

function categoriesRender(categoriesSource) {
    categories.innerHTML = '';
    let badgesCicle = 0;
    const badgesStyles = ["text-bg-primary", "text-bg-danger", "text-bg-warning", "text-bg-success", "text-bg-light", "text-bg-dark", "text-bg-info", "text-bg-secondary"]
    categoriesSource.forEach(category => {
        categories.innerHTML += `<span class="badge rounded-pill ${badgesStyles[badgesCicle]}">${category}</span>`;

        badgesCicle == 7 ? badgesCicle = 0 : badgesCicle++;
    });
}

export { bookInfo,bookSelected }