package com.example.nexosapp.DTO;

import com.example.nexosapp.enumerados.Complexion;
import com.example.nexosapp.enumerados.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private Sexo sexo;
    private float altura;
    private Complexion complexion;
    private String descripcion;
}
