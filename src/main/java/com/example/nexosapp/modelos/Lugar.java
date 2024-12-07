package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lugar", schema = "nexo_app", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de Lugar")
public class Lugar {
    @Schema(description = "Identificador del lugar", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Schema(description = "Provincia del lugar", example = "Cordoba")
    @Column(name = "provincia")
    private String provincia;

    @Schema(description = "Localidad del lugar", example = "Villa Maria")
    @Column(name = "localidad")
    private String localidad;

    @Schema(description = "Calle del lugar", example = "San Martin")
    @Column(name = "calle")
    private String calle;

    @Schema(description = "Latitud del lugar", example = "-31.431")
    @Column(name = "lat")
    private Double latitud;

    @Schema(description = "Longitud del lugar", example = "-64.183")
    @Column(name = "lon")
    private Double longitud;

}
