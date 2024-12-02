package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionCampana {
    private Integer id;
    private Integer idDesaparicion;
    private String tipo;
    private String fecha;
    private String texto;
}
