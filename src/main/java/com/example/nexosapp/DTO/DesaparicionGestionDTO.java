package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesaparicionGestionDTO {
    private Integer id;
    private String nombre;
    private String apellidos;
    private String fecha;
}
