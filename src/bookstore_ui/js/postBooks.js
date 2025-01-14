
import { cart, totals } from "./addToCart.js";
import { booksRender } from "./booksRender.js";
import { myBooks } from "./getMyBooks.js";
import { goToMyBooksTab } from "./goToMyBooksTab.js";
import { goToRecentBooks } from "./goToRecentBooksTab.js";
import { Singleton } from "./index.js";
import { removeOwnedBooks } from "./removeOwnedBooks.js";


const placeHolderCart = document.querySelector("#placeHolderCart");
const subtotal = document.querySelector("#subtotal");
const totalDiscount = document.querySelector("#total-discount");
const totalToPay = document.querySelector("#total-to-pay");
const added = document.querySelector("#added");

const url = "http://localhost:8383/api/bookstore/book/admin";
async function postBooks(books) {

    books.forEach(async book => {
        try {
            const res = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer "+ localStorage.getItem("jwt")
                },
                body: JSON.stringify(book)
            });
            if (!res.ok) {
                const error = await res.json();
                throw new Error(error.message);
            }
            else{
           document.querySelector("#col-"+book.id).remove();
           added.style.visibility = 'visible';
           setTimeout(()=>added.style.visibility = 'hidden',3000);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
    cart.splice(0, cart.length);
    document.querySelectorAll('.book-in-cart').forEach(card => card.remove());
    totals.subtotal = 0;
    totals.totalDiscount = 0;
    totals.totalToPay = 0;
    subtotal.innerHTML = "<b>Subtotal: </b>$";
    totalDiscount.innerHTML = "<b>Saved: </b>$";
    totalToPay.innerHTML = "<b>Total: </b>$0.00";
    subtotal.style.display = 'none';
    totalDiscount.style.display = 'none';
    placeHolderCart.style.visibility = 'visible';
}

export { postBooks }