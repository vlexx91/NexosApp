package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCrearDTO {
    private String usuario;
    private String email;
    private String contrasenya;
    private String repContrasenya;
}
