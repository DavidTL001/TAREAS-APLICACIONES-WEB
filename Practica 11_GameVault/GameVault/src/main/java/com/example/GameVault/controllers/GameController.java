package com.example.GameVault.controllers;

import com.example.GameVault.models.Juego;
import com.example.GameVault.service.JuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;




@Controller
public class GameController {

    @Autowired
    private JuegoService juegoService;

    @Autowired
    private UsuarioService usuarioService;


    private static List<Juego> juegosDb = new ArrayList<>();
    private static long idCounter = 1;

    private static final String UPLOAD_DIR = "src/main/uploads/static/uploads";

    static{
        // juegosDb.add(new Juego(idCouter++, "The Legend of Zelda", "Aventura épica.", "zelda.jpg"));
    }


    @GetMapping("/fragments-demo")
    public String fragmentsDemo(){ return "fragments-demo";}

    @GetMapping({"/", "/juegos"})
    public String listarJuegos( @RequestParam(name="buscar", required = false) String buscar, Model model){
        if (buscar != null && !buscar.isEmpty()){

            model.addAttribute("juegos", juegoService.buscarPorTitulo(buscar));
            model.addAttribute("busquedaActual", buscar);

        }else{
            model.addAttribute("juegos", juegoService.obtenerTodos());
        }

        return "juegos";
    }

    @GetMapping("/juegos/nuevo")
    public String mostrarFormulario(Modelmodel) {
        model.addAttribute("juego", new Juego());
        return "formulario";
    }

    @GetMapping("/mis-juegos")
    public String dashboard(Model model){
        Usuario logueado = UsuarioService.obtenerUsuarioLogueado();
        model.addAtribute("juegos", juegoService.obtenerJuegosPorUsuario(logueado.getId()));
        model.addAtribute("usuario", logueado);

        return "mis-juegos";
    }

    @PostMapping("/juegos")
    public String guardarJuego(@RequestParam("titulo") String titulo,
                               @RequestParam("descripcion") String descripcion,
                                @RequestParam("precio") Double precio,
                                @RequestParam("categoria") String categoria,
                                @RequestParam("portada") MultipartFile portada){

        Juego nuevoJuego = new Juego();
        nuevoJuego.setTitulo(titulo);
        nuevoJuego.setDescripcion(descripcion);
        nuevoJuego.setPrecio(precio);
        nuevojuego.setCategoriacategoria(categoria);

        nuevoJuego.setUsuario(usuarioService.obtenerUsuarioLogueado());

        juegoService.guardarJuego(nuevoJuego, portada);

        return "redirect:/mis-juegos";

    }




}
