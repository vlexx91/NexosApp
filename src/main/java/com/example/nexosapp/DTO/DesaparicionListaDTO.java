package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparicionListaDTO {
    private Integer id;
    private String urlFotoCara;
    private String nombre;
    private String apellidos;
    private String fecha;
    private String direccion;
    private String estado;
    private String descripcion;
    private String complexion;
    private String fechaNacimiento;
    private String sexo;
}
