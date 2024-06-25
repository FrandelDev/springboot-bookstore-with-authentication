
const loader = document.querySelector("#loading");

const url = "http://localhost:8384/get-recommended-books/";
let recommendedBooks;
async function getRecommendedBooks(){
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
            recommendedBooks = data;
        }
    } catch (error) {
        console.error('Error:', error);
    }
}



export {getRecommendedBooks,recommendedBooks}