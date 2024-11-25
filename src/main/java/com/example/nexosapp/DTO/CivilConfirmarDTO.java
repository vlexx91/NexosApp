package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CivilConfirmarDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String nombreUsuario;
    private Integer idUsuario;
}
