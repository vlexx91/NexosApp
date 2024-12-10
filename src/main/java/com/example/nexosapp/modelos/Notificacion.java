package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Modelo de Notificación")
public class Notificacion {
    @Schema(description = "Identificador del lugar", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Schema(description = "Fecha de la creación", example = "2021-09-01T00:00:00")
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Schema(description = "Tipo de notificación", example = "COMENTARIO")
    @Column(name = "tipo")
    private String tipo;

    @Schema(description = "Texto de la notificación", example = "Se ha realizado un comentario en la desaparición")
    @Column(name = "texto")
    private String texto;

    @Schema(description = "Estado de la notificación", example = "false")
    @Column(name = "leida")
    private Boolean leida;

    @Schema(description = "Usuario al que pertenece la notificación")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Schema(description = "Desaparición a la que pertenece la notificación")
    @ManyToOne
    @JoinColumn(name = "id_desaparicion")
    private Desaparicion desaparicion;

}
