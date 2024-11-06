package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "desaparicion", schema = "nexo_app", catalog = "postgres")
//etiquetas de lombok
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Desaparicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "aprobada" )
    private Boolean aprobada;

//    @Column(name = "id_persona", nullable = false)
//    private Persona persona;
//
//    @Column(name = "id_usuario", nullable = false)
//    private Usuario usuario;
//
//    @Column(name = "id_lugar", nullable = false)
//    private Lugar lugar;
}
// conectar tablas id