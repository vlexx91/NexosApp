package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CivilCrearDTO {
    private String dni;
    private String telefono;
    private String nombre;
    private String apellido;
    private UsuarioCrearDTO usuarioCrearDTO;

}
