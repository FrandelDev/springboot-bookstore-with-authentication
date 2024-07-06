


let myBooks;

const loader = document.querySelector("#loading");

const url = "http://localhost:8383/api/bookstore/admin";
async function getMyBooks(){

    try {
        const res = await fetch(url, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer "+ localStorage.getItem("jwt")
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