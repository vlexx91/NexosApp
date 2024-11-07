package com.example.nexosapp.DTO;

import com.example.nexosapp.modelos.Persona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesaparicionDTO {
    private String fecha;
    private String descripcion;
    private String estado;
    private Boolean aprobada;
    private PersonaDTO personaDTO;
    private Integer id_usuario;
    private LugarDTO lugarDTO;
}

/*
Esto sirve para que en el mapeador de desaparicion se pueda mapeaar la fecha de string a localdate

DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(dto.getFechaNacimiento(), formatter);

 */
