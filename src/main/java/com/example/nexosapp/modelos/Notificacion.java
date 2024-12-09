package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion", schema = "nexo_app", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"usuario", "desaparicion"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"usuario", "desaparicion"})
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "texto")
    private String texto;

    @Column(name = "leida")
    private Boolean leida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_desaparicion")
    private Desaparicion desaparicion;

}
