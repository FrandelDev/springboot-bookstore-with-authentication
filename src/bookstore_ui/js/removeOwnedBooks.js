import { items, Singleton } from "./index.js";
import { getMyBooks, myBooks } from "./getMyBooks.js";


let updatedItems;
let updatedBooks;
async function removeOwnedBooks(){
   const singletonInstance = new Singleton();
    updatedItems = singletonInstance.allData;
    updatedBooks = await getMyBooks();
    updatedBooks.forEach(book =>{
        updatedItems.forEach(item =>{
            if(item.id == book.id){
                updatedItems.splice(updatedItems.indexOf(item),1);
            }
        });
    });
    singletonInstance.updateData = updatedItems;
}


export {removeOwnedBooks,updatedItems}