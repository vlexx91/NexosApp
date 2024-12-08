package com.example.nexosapp.modelos;

import com.example.nexosapp.enumerados.ESTADO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Modelo de Desaparicion")
public class Desaparicion {
    @Schema(description = "Identificador de la desaparicion", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Schema(description = "Fecha de la desaparicion", example = "2021-09-01", required = true)
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Schema(description = "Descripcion de la desaparicion", example = "Desaparicion de prueba", required = true)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Schema(description = "Estado de la desaparicion", example = "DESAPARECIDO", required = true)
    @Column(name = "estado")
    @Enumerated(EnumType.ORDINAL)
    private ESTADO estado;

    @Schema(description = "Aprobacion de la desaparicion", example = "true", required = true)
    @Column(name = "aprobada" )
    private Boolean aprobada;

    @Schema(description = "Eliminacion de la desaparicion", example = "false", required = true)
    @Column(name = "eliminada")
    private Boolean eliminada;

    @Schema(description = "Persona a la que esta unida la desaparicion")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private Persona persona;

    @Schema(description = "Usuario al que esta unida la desaparicion")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Schema(description = "Lugar al que esta unida la desaparicion")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;


}
