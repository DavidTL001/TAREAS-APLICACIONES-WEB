package com.example.GameVault.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Juegos")

public class Juego {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
        private String titulo;

    @Column(length = 1000)
        private String descripcion = "descripcion";

    @Column(name = "portada_url")
    private String portadaUrl = "portadaUrl";

    @Column
    private Double precio;

    @Column
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "usuario.id")
    private Usuario usuario;

}
