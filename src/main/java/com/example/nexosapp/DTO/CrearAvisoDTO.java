package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearAvisoDTO {

    private String texto;
//    private String fecha;
    private Integer idUsuario;

}
