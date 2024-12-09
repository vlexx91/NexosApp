package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioListarDTO {
    private Integer id;
    private String texto;
    private String nombre;
    private String email;
    private Integer telefono;
    private List<String> fotos;
}
