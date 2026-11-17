package com.example.PokemonApi.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio y no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "el tipo es obligatorio")
    @Size(min = 1,max = 100, message = "el tipo debe de tener entre 1 y 100 carteres")
    private String tipo;

    //Validaciones desde el lado del servidor
    @NotNull(message = "El nivel es obligatorio")
    @Min(value = 1, message = "el nivel minimo es 1")
    @Max(value = 100,message = "El nivel máximo es 100")
    private Integer nivel;

    @Min(value = 0, message = "El ataque no puede ser negativo")
    private Integer ataque;

    @Min(value = 0,message = "la defensa no puede ser negativa")
    private Integer defensa;

    @Size(max = 255, message = "La descripción no puede pasar de 255 caracteres")
    private String descripcion;

    @URL(message = "La imagen debe ser una URL válida")
    private String imagenUrl;

    @Email(message = "El formato del correo debe de ser válido")
    private String email;
}
