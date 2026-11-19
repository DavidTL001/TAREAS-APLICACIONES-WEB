
console.log("Seccion de funciones");
//funcion declarada
function saludar(nombre = "Invitado", edad=0){
    return "Hola: " + nombre + " Edad: " + edad;
}

console.log(saludar("David", 23));

console.log(saludar());

//funcion anonima
let suma = function(num1, num2){
    return num1 + num2;
}
console.log("Resultado suma: "+ suma(1,63));

//funcion flecha
let resta = (num1,num2) => {
    return num1 - num2;
}
console.log("Resultado resta: " + resta(5,1));

//Funcion autoinvocada
(async function () {
    console.log("Hola desde funcion autoinvocada");
})();

console.log("Arreglos Dinámicos");

let numeros = [1,2,3];
console.log("Arreglo inicial ", numeros);
numeros.push(4);
console.log("Arreglo después del push: ", numeros);

let ultimo = numeros.pop();
console.log("Arreglo despues del pop ", numeros);

let primero = numeros.shift();
console.log("Arreglo después del shift ", numeros);

console.log("Objetos")

const persona = {
    nombre: "David",
    edad: 23,
    carrera: "ISW",
    hobbies: ["musica", "vieojuegos", "bici"]
};
console.log("Nombre: ", persona.nombre);
persona.nombre = "Alejandro";
console.log("Nombre modificado: ", persona.nombre);
