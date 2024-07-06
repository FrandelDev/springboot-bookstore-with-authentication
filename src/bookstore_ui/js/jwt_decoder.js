

export function decode(token){
  
const base64Url = token.split('.')[1];
const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
const decodedPayload = decodeURIComponent(atob(base64).split('').map((c) => `%${`00${c.charCodeAt(0).toString(16)}`.slice(-2)}`).join(''));


const payload = JSON.parse(decodedPayload);
const username = payload.sub; 

console.log(`Nombre de usuario: ${username}`);
return username;
}

