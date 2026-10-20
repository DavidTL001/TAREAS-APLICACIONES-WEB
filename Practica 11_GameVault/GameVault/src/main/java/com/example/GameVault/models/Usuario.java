package com.example.GameVault.models;

@Data
@NoAegsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @column(nullable = false)
    private String contrasenia;

    @OneToMany(mappedBy = "usuario", cascadeType.ALL)
    List<Juego> juegos;




}