package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CivilDTO {
    private String dni;
    private String nombre;
    private String apellido;
    private Integer telefono;
}
