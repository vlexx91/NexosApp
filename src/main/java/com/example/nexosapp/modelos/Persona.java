package com.example.nexosapp.modelos;
import com.example.nexosapp.enumerados.Complexion;
import com.example.nexosapp.enumerados.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "persona", schema ="nexo_app", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dni", nullable = false)
    private String dni;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellido", nullable = false)
    private String apellido;
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;
    @Column(name = "altura", nullable = false)
    private float altura;
    @Column(name = "complexion", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Complexion complexion;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
