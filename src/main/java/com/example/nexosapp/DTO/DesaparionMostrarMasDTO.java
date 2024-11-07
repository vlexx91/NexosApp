package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparionMostrarMasDTO {
    private Integer id;
    private List<String> fotos;
    private String nombre;
    private String apellido;
    private String fecha;
    private String descripcion;
    private LugarLatLongDTO lugar;
}
