package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisoDTO {
    private LocalDate fecha;
    private String texto;
    private Integer usuarioId;
    private Set<FotoUrlDTO> fotos;
}
