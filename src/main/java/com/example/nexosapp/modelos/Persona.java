package com.example.nexosapp.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
    private Timestamp fechaNacimiento;
    @Column(name = "sexo", nullable = false)
    private String sexo;
    @Column(name = "altura", nullable = false)
    private float altura;
    @Column(name = "complexion", nullable = false)
    private String complexion;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
}
