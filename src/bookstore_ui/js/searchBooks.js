import { booksRender } from "./booksRender.js";

const btnSearch = document.querySelector("#btn-search");
const searchbar = document.querySelector("#search");

const url = "http://localhost:8383/api/bookstore/search?criteria=";

let itemsFromSearch;

btnSearch.addEventListener('click', searchBooks);
async function searchBooks(){

    try {
        const res = await fetch(url+searchbar.value, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!res.ok) {
            const error = await res.json();
            throw new Error(error.message);
        } else {
            const data = await res.json();
            booksRender(data);
            itemsFromSearch = data;
            return data;
        }
    } catch (error) {
        console.error('Error:', error);
    }
}
export {searchBooks,itemsFromSearch}