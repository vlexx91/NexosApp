package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="comentario", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "texto",nullable = false)
    private String texto;
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Column(name = "fecha",nullable = false)
    private LocalDate fecha;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "telefono")
    private Integer telefono;
    //private Desaparicion desaparicion;
}
