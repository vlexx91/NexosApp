package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparionMostrarMasDTO {

    private Integer id;
    private String urlFotoCara;
    private String nombre;
    private String apellidos;
    private LocalDate fecha;
}
