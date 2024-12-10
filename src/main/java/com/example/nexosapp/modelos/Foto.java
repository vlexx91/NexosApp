package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="foto", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de Foto")
public class Foto {
    @Schema(description = "Identificador de la foto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Schema(description = "URL de la foto", example = "https://www.google.com", required = true)
    @Column(name = "url",nullable = false)
    private String url;
    @Schema(description = "Bool para comprobar si es la foto de cara del desaparecido", example = "true", required = true)
    @Column(name = "es_cara",nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean esCara;
}
