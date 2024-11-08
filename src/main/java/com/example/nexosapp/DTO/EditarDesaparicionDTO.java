package com.example.nexosapp.DTO;

import com.example.nexosapp.enumerados.ESTADO;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditarDesaparicionDTO {

    @Enumerated(EnumType.ORDINAL)
    private ESTADO estado;

    private LugarLatLongDTO lugarLatLongDTO;
}
