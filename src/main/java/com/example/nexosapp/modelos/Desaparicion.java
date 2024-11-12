package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ESTADO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "desaparicion", schema = "nexo_app", catalog = "postgres")
//etiquetas de lombok
@Getter
@Setter
@ToString(exclude = {"persona", "usuario", "lugar"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"persona", "usuario", "lugar",})
public class Desaparicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado")
    @Enumerated(EnumType.ORDINAL)
    private ESTADO estado;

    @Column(name = "aprobada" )
    private Boolean aprobada;

    @Column(name = "eliminada")
    private Boolean eliminada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;


}
