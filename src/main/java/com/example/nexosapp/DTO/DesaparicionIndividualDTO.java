package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparicionIndividualDTO {
    private Integer id;
    private List<String> fotos;
    private String fecha;
    private String descripcion;
    private PersonaDTO persona;
}
