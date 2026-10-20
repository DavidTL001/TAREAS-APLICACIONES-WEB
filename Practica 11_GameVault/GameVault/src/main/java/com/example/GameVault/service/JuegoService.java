package com.example.GameVault.service;

import com.example.GameVault.models.Juego;
import com.example.GameVault.repository.JuegoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    private static final String UPLOAD_DIR =
            "src/main/resources/static/uploads";

    public List<Juego> obtenerTodos() {
        return juegoRepository.findAll();
    }

    public void guardarJuego(Juego juego, MultipartFile portada) {
        String nombreArchivo = juego.getPortadaUrl() != null ?
        juego.getPortadaUrl(): "default.png";
        if (portada != null && !portada.isEmpty()) {
            nombreArchivo = guardarImagenLocal(portada);
        }

        juego.setPortadaUrl(nombreArchivo);
        juegoRepository.save(juego);
    }

    public Juego ObtenerJuegoPorId(Long id){
        return juegoRepository.findBy(id).orElse(null);
    }

    @PostMapping("/juegos/eliminar/{id}")
    public String eliminarJuego(@PathVariable Long id) {
        Juego juego = juegoService.obtenerJuegoPorId(id);
        Usuario logueado = usuarioService.obtenerUsuarioLogueado();

        // Solo procedemos a borrar si el usuario logueado es el dueño
        if (juego != null && juego.getUsuario().getId().equals(logueado.getId())) {
            juegoService.eliminarJuego(id);
        }

        return "redirect:/mis-juegos";
    }


    public void editarJuego(@PathVariable Long id, Model model){
        Juego juego= juegoService.obtenerJuegoPorId(id);
        Usuario logueado = usuarioService.obtenerUsuarioLogueado();

        //Si el juego existe y el dueño coincide, mostramos el formulario
        if (juego != null && juego.getUsuario().getId().equals(logueado.getId())){
            model.addAttribute("juego", juego);
            return "formulario";//Reutilizamos nuestra vista de formulario
        }
        //si intenta editar un juego ajeno, lo devolvemos a su panel
        return "redirect:/mis-juegos";
    }

    @PostMapping("/juegos/editar/{id}")
    public String actualizarjuego(@PathVariable Long id, @RequestParam("titulo") String titulo,
                                  @RequestParam("descripcion") String descripcion,(
                                  @RequestParam("precio") Double precio,
                                  @RequestParam("categoria") String categoria,
                                  @RequestParam(value = "portada",required = false) MultipartFile portada){

        Juego juegoExistente = juegoService.obtenerJuegoPorId(id);
        Usuario usuario = usuarioService.obtenerUsuarioLogueado();

        //Verificamos propiedad nuevamente antes de modificar
        if (juegoExistente != null && juegoExistente.getUsuario().getId.equals(logueado.getId())){
            juegoExistente.setTitulo(titulo);
            juegoExistente.setDescripcion(descripcion);
            juegoExistente.setPrecio(precio);
            juegoExistente.Categoria(categoria);
            juegoService.guardarJuego(juegoExistente, portada);
        }

        return "redirect:/mis-juegos";
    }




    public List<Juego> obtenerJuegosPorUsuario(Long usuarioId){
        return juegoRepository.findByUsuarioId(usuarioId);
    }



    public String guardarImagenLocal(MultipartFile portada) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String nombreOriginal = Paths.get(
                    portada.getOriginalFilename() != null
                            ? portada.getOriginalFilename()
                            : "imagen.png"
            ).getFileName().toString();

            String nombreArchivo =
                    UUID.randomUUID() + "-" + nombreOriginal;

            Path filePath = uploadPath.resolve(nombreArchivo);

            Files.copy(
                    portada.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return nombreArchivo;

        } catch (IOException e) {
            e.printStackTrace();
            return "default.png";
        }
    }

    public List<Juego> buscarPorTitulo(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) {
            return obtenerTodos();
        }

        return juegoRepository
                .findByTituloContainingIgnoreCase(palabra);
    }
}
