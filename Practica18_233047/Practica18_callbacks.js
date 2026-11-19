console.log("1.- Inicio");

setTimeout(function(){
    console.log("2.- Pasó un segundo");
},1000)

console.log("3.- Fin");

function prepararCafe (callback){
    console.log("preparando cafe");
    setTimeout(function(){
        console.log("Café listo");
        callback();
    },3000);


}  
  prepararCafe(function(){
        console.log("Ahora si, es  hora de desayunar");
    });