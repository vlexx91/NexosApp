package com.example.nexosapp.modelos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="civil", schema="nexo_app",catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de Civil")
public class Civil {
    @Schema(description = "Identificador del civil", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Schema(description = "DNI del civil", example = "12345678v", required = true)
    @Column(name = "dni",nullable = false)
    private String dni;
    @Schema(description = "Telefono del civil", example = "123456789", required = true)
    @Column(name = "telefono",nullable = false)
    private Integer telefono;
    @Schema(description = "Nombre del civil", example = "Juan", required = true)
    @Column(name = "nombre",nullable = false)
    private String nombre;
    @Schema(description = "Apellido del civil", example = "Perez", required = true)
    @Column(name = "apellido",nullable = false)
    private String apellido;
    @Schema(description = "Usuario al que esta unido el civil")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
}
