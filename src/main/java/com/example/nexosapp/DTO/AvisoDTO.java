package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvisoDTO {


    private LocalDateTime fecha;
    private String texto;
    private Integer id_usuario;
    private Set<FotoUrlDTO> fotos;
}
