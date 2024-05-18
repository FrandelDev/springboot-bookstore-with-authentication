import { bookInfo } from "./bookInfo.js";

const booksSection = document.querySelector("#books");



function booksRender(RequiredBooksList){
    booksSection.innerHTML = '';
    RequiredBooksList.forEach(book =>{
        if(book.priceWithDiscount){
            booksSection.innerHTML += `
            <div id="col-${book.id}" class="col">
            <div id="${book.id}" class="card recommended-book" style="width: 13.5rem;">
                <img src="${book.image}" class="card-img-top" alt="...">
                <div class="card-body d-flex flex-column text-center">
                    <div id="Recommended">Recommended</div>
                    <h6 class="card-title lh-sm">${book.title}</h6>
                    <p class="card-text lh-sm" id="discount"><b>$${book.price}</b></p>
                    <p class="card-text lh-sm"><b>${book.priceWithDiscount}</b></p>
                </div>
            </div>
        </div>
            `
        }
        else{
            booksSection.innerHTML += `
        <div id="col-${book.id}" class="col">
            <div id="${book.id}" class="card" style="width: 13.5rem;">
                <img src="${book.image}" class="card-img-top" alt="...">
                <div class="card-body d-flex flex-column text-center">
                    <h6 class="card-title lh-sm">${book.title}</h6>
                    <p class="card-text lh-sm"><b>$${book.price}</b></p>
                </div>
            </div>
        </div>
        `
        }
    });
    document.querySelectorAll('.card').forEach(card =>{
        card.addEventListener('click',bookInfo)
    });
}

export {booksRender}