var global1 = "global1";

function unaFuncion() {
    var local = 'local';
    global2 = "global2"; // Es global, aunque se crea cuando se ejecuta la función
    console.log("Dentro de la función (local): " + local);
    console.log("Dentro de la función (global1): " + global1);
}

unaFuncion();

var global3 = "global3";
console.log("Fuera de la función (global1): " + global1);
console.log("Fuera de la función (global2): " + global2);
console.log("Fuera de la función (global3): " + global3);
console.log("Fuera de la función (local): " + local); // ERROR