package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "aviso", schema = "nexo_app", catalog = "postgres")
//etiquetas de lombok
@Getter
@Setter
@ToString(exclude = {"usuario"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"usuario"})
@Schema(description = "Modelo de Aviso")
public class Aviso {
    @Schema(description = "Identificador del aviso", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Schema(description = "texto del aviso", example = "Aviso de prueba", required = true)
    @Column(name = "texto", nullable = false)
    private String texto;

    @Schema(description = "fecha del aviso", example = "2021-09-01", required = true)
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Schema(description = "usuario que creo el aviso")
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;


    @Schema(description = "fotos del aviso")
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Foto.class)
    @JoinTable(
            name = "foto_aviso",
            schema = "nexo_app",
            joinColumns = {@JoinColumn(name = "id_aviso", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_foto", nullable = false)}
    )
    private Set<Foto> fotos;
}

