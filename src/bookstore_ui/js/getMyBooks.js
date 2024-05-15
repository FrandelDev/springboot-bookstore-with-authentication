
import { booksRender } from "./booksRender.js";

const tabMyBooks = document.querySelector("#tab-my-books");
const tabRecent = document.querySelector("#tab-recent");

let myBooks;

tabMyBooks.addEventListener('click',getMyBooks);
const url = "http://localhost:8383/api/bookstore/";
async function getMyBooks(){
    tabMyBooks.classList.remove('text-success');
    tabMyBooks.classList.toggle('active');
    tabRecent.classList.remove('active');
    tabRecent.classList.add('text-success');
    try {
        const res = await fetch(url, {
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
            myBooks = data;
            booksRender(data);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

export {getMyBooks,myBooks}