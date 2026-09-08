package com.tirado.MiPrimerServidorWeb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api") // https://MiPagina.com/api
public class HelloController {

    // GET https://MiPagina.com/api/hello
    @GetMapping ("/hello") //endPoint
    public String hello (){
        return "Hola desde mi primer servidor" ;
    }

    @GetMapping ("/saludo")
    public String saludo (@RequestParam String nombre){
        return "hola desde el servidor de " + nombre;
    }


}
