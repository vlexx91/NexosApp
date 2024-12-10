package com.example.nexosapp.DTO;

import com.example.nexosapp.enumerados.ESTADO;
import com.example.nexosapp.modelos.Foto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparicionEditarAutoridadDTO {
    private String descripcion;
    @Enumerated(EnumType.ORDINAL)
    private ESTADO estado;
    private LugarLatLongDTO lugarLatLongDTO;
    private List<Foto> fotos;

}
