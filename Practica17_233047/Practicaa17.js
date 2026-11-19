console.log("Hello world");

let x2 = "David";
console.log("Valor inicial de x2: ", x2, " Tipo: ", typeof x2 );

x2 = 7;
console.log("Valor inicial de x2: ", x2, " Tipo: ", typeof x2);

const constante = 10;
console.log("valor constante: ", constante);

console.log("Operadores logicos");

/**
 *  JavaScript: cualquier valor puede ser verdadero (Truthly)
 *  hay valores que son Falsy:
 *  - false
 *  - 0 (cero)
 *  - "" o " "
 *  - null
 *  - undefined
 *  - NaN (Not a Number) 
 */

let bariableBool = false;
if(variableBool){
    console.log("1. Entra en el bloque si/true")
} else{
    console.log("2. Entra en el bloque False")
}


let mensaje = "hola";
if(mensaje){
    console.log("3. Entra en el bloque si/true")
} else{
    console.log("4. Entra en el bloque False")
}

let numero6 = 6;
let string6 = "6";

console.log("Comparación debil (6 == '6')", numero6 == string6);
console.log("Comparación debil (6 === '6')", numero6 === string6);
