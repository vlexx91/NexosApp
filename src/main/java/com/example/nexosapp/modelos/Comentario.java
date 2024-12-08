package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="comentario", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de Comentario")
public class Comentario {
    @Schema(description = "Identificador del comentario", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Schema(description = "Texto del comentario", example = "Comentario de prueba", required = true)
    @Column(name = "texto",nullable = false)
    private String texto;
    @Schema(description = "Nombre del autor del comentario", example = "Juan", required = true)
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Schema(description = "Fecha del comentario", example = "Perez", required = true)
    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;
    @Schema(description = "Email del autor del comentario", example = "hola@gmail.com", required = true)
    @Column(name = "email",nullable = false)
    private String email;
    @Schema(description = "Telefono del autor del comentario", example = "123456789")
    @Column(name = "telefono")
    private Integer telefono;

    @Schema(description = "Desaparicion a la que esta unido el comentario")
    @ManyToOne
    @JoinColumn(name = "id_desaparicion")
    private Desaparicion desaparicion;

    @Schema(description = "Fotos del comentario")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Foto.class)
    @JoinTable(name = "foto_comentario" ,schema = "nexo_app", catalog = "postgres", joinColumns = {@JoinColumn(name = "id_comentario", nullable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_foto", nullable = false)})
    private Set<Foto> fotos;
}
