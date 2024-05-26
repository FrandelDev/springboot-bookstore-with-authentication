


let myBooks;

const loader = document.querySelector("#loading");
const url = "http://localhost:8383/api/bookstore/";
async function getMyBooks(){

    try {
        const res = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!res.ok) {
            loader.style.visibility = 'hidden';
            const error = await res.json();
            throw new Error(error.message);
        } else {
            const data = await res.json();
            myBooks = data;
            return data.reverse();
        }
    } catch (error) {
        console.error('Error:', error);
    }
}



export {getMyBooks,myBooks}