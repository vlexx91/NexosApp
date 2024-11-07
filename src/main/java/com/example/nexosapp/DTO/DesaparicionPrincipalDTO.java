package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparicionPrincipalDTO {
    private Integer id;
    private List<String> foto;
    private String nombre;
    private String apellido;
    private Timestamp fecha;
    private String descripcion;
    private LugarLatLongDTO lugar;
}
