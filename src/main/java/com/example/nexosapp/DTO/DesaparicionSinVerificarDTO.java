package com.example.nexosapp.DTO;

import com.example.nexosapp.enumerados.ESTADO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparicionSinVerificarDTO {
    private List<String> fotos;
    private String fecha;
    private String descripcion;
    private PersonaDTO persona;
    private ESTADO estado;
    private LugarDTO lugar;
}
