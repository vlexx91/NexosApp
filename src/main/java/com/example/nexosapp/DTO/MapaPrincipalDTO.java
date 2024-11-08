package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapaPrincipalDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private double lat;
    private double lon;
}
