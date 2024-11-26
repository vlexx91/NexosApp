package com.example.nexosapp.DTO;


import com.example.nexosapp.enumerados.ROL;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearAutoridadDTO {

    private String identificador;
    private UsuarioCrearDTO usuarioCrearDTO;
}
