package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaCrearDTO {
    private String dni;
    private String nombre;
    private String apellido;
    private Timestamp fechaNacimiento;
    private String sexo;
    private float altura;
    private String complexion;
    private String descripcion;
}
