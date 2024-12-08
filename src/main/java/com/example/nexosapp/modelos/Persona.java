package com.example.nexosapp.modelos;
import com.example.nexosapp.enumerados.Complexion;
import com.example.nexosapp.enumerados.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "persona", schema ="nexo_app", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(description = "Modelo de Persona")
public class Persona {
    @Schema(description = "Identificador de la persona", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Schema(description = "DNI de la persona", example = "12345678", required = true)
    @Column(name = "dni", nullable = false)
    private String dni;
    @Schema(description = "Nombre de la persona", example = "Juan", required = true)
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Schema(description = "Apellidos de la persona", example = "Perez Castro", required = true)
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Schema(description = "Fecha de nacimiento de la persona", example = "1999-12-31", required = true)
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Schema(description = "Sexo de la persona", example = "HOMBRE", required = true)
    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;
    @Schema(description = "Altura de la persona", example = "1.80", required = true)
    @Column(name = "altura", nullable = false)
    private float altura;
    @Schema(description = "Complexión de la persona", example = "FUERTE", required = true)
    @Column(name = "complexion", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Complexion complexion;
    @Schema(description = "Descripción física de la persona para aportar más datos", example = "Ojos azules, pelo moreno largo hasta los hombros", required = true)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Schema(description = "Fotos de la persona")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Foto.class)
    @JoinTable(name = "foto_persona", schema = "nexo_app", catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_persona", nullable = false)} ,
            inverseJoinColumns ={@JoinColumn(name = "id_foto", nullable = false)})
    private Set<Foto> fotos;
}
