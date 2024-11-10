package com.example.nexosapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

    private String texto;
    private String nombre;
    private String email;
    private Integer telefono;
    private Integer desaparicionId;

}
