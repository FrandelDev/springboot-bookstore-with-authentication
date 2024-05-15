import { bookSelected } from "./bookInfo.js";

const toBuy = document.querySelector("#toBuy");
const shoppingCart = document.querySelector("#shopping-cart");
const placeHolderCart = document.querySelector("#placeHolderCart");
const subtotal = document.querySelector("#subtotal");
const totalDiscount = document.querySelector("#total-discount");
const totalToPay = document.querySelector("#total-to-pay");
const bookInfoCard = document.querySelector("#book-info");

let cart = [];
let totals = {
    subtotal: 0,
    totalDiscount: 0,
    totalToPay: 0
}

toBuy.addEventListener('click',addToCart)
function addToCart(){
    placeHolderCart.style.visibility = 'hidden'
    cart.push(bookSelected);
    
    if(bookSelected.priceWithDiscount){
        totals.subtotal += bookSelected.price
        totals.totalDiscount += (bookSelected.price - bookSelected.priceWithDiscount);
        shoppingCart.insertAdjacentHTML('afterbegin',`
            <div id="${bookSelected.id}" class="card book-in-cart book-with-discount" style="width: 8rem;">
                <img src="${bookSelected.image}" class="card-img-top" alt="...">
                <div class="card-body text-center" ">
              <h6 class=" card-title lh-sm">${bookSelected.title}</h6>
                    <p class="card-text lh-sm"><b>$${bookSelected.price}</b></p>
                    <p class="card-text"><b>$${bookSelected.priceWithDiscount}</b></p>
                </div>
    `);
    subtotal.style.display = 'block';
    totalDiscount.style.display = 'block';
    subtotal.innerHTML = "<b>Subtotal: </b>$"+totals.subtotal.toFixed(2);
    totalDiscount.innerHTML = "<b>Ahorrado: </b>$"+totals.totalDiscount.toFixed(2);
    }
    else{
        totals.subtotal += bookSelected.price
        shoppingCart.insertAdjacentHTML('afterbegin',`
            <div id="${bookSelected.id}" class="card book-in-cart" style="width: 8rem;">
                <img src="${bookSelected.image}" class="card-img-top" alt="...">
                <div class="card-body text-center" ">
              <h6 class=" card-title lh-sm">${bookSelected.title}</h6>
                    <p class="card-text lh-sm"><b>$${bookSelected.price}</b></p>
                </div>
    `);
}

totals.totalToPay = totals.subtotal - totals.totalDiscount;
totalToPay.innerHTML = "<b>Total: </b>$"+totals.totalToPay.toFixed(2);

    shoppingCart.scrollTop = 0;
    bookInfoCard.style.visibility = 'hidden'
   const booksInCart = document.querySelectorAll('.book-in-cart');
   booksInCart.forEach(book =>{
    book.addEventListener('click',removeFromCart);
   })
}

function removeFromCart(event){
    const bookId = cart.findIndex(book => book.id == event.currentTarget.id);
    if(cart[bookId].priceWithDiscount){
        totals.totalDiscount -= (cart[bookId].price - cart[bookId].priceWithDiscount);
        totals.subtotal -= cart[bookId].price
        subtotal.innerHTML = "<b>Subtotal: </b>$"+totals.subtotal.toFixed(2);
        totalDiscount.innerHTML = "<b>Ahorrado: </b>$"+totals.totalDiscount.toFixed(2);
    }
    else{
        totals.subtotal -= cart[bookId].price
        subtotal.innerHTML = "<b>Subtotal: </b>$"+totals.subtotal.toFixed(2);
    }
    totals.totalToPay = totals.subtotal - totals.totalDiscount;
    totalToPay.innerHTML = "<b>Total: </b>$"+totals.totalToPay.toFixed(2);

    cart.splice(bookId,1);
    event.currentTarget.remove()
    if(cart.length == 0) placeHolderCart.style.visibility = 'visible';
}
export {addToCart,cart}